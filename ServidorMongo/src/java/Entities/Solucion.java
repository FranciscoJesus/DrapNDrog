/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author FranciscoJesús
 */
@Entity("Soluciones")
@XmlRootElement
public class Solucion {

    @Id
    public String id = new ObjectId().toString();
    public String nota;
    public String idAlumno;
    public String idProblema;
    public String nombre;
    public String apellidos;
    @Embedded
    public ArrayList<Pieza> piezas = new ArrayList<>();

    public Solucion() {

    }

    public void ponerNota(List<Pieza> arrayPiezas) {

        double notaMaxima = notaMaxima(arrayPiezas);
        double notaAlumno = 0.0;
        double notaAlumnoMejor = 0.0;
        double notaARestar = 0;
        int i = 0;

        for (int k = 0; k < arrayPiezas.size(); k++) {
            i = 0;
            for (; ((i + k) < arrayPiezas.size()) && (i < piezas.size()); i++) {
                if (i < piezas.size()) {
                    Pieza Alumno = piezas.get(i);
                    if (arrayPiezas.get(i + k).equals(Alumno)) {
                        for (Input inp : Alumno.inputs) {
                            notaAlumno = notaAlumno + 1 / notaMaxima;
                        }
                    }
                }
            }
            notaARestar = 0;
            for (; i < piezas.size(); i++) {
                for (Input inp : piezas.get(i).inputs) {
                    notaARestar = notaARestar + 1 / notaMaxima;
                }
            }
            notaAlumno = notaAlumno - notaARestar;
            if (notaAlumno > notaAlumnoMejor) {
                notaAlumnoMejor = notaAlumno;
                notaAlumno = 0.0;
            }
        }

        nota = String.valueOf(notaAlumnoMejor * 10);

    }

    public double notaMaxima(List<Pieza> arrayPiezas) {

        double notaMaxima = 0.0d;

        for (Pieza p : arrayPiezas) {
            for (Input i : p.inputs) {
                notaMaxima++;
            }
        }

        return notaMaxima;
    }
}
