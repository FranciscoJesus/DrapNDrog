/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
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
    public String id;
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

        int i = 0;
        for (i = 0; i < arrayPiezas.size(); i++) {
            Pieza Alumno = piezas.get(i);
            if (arrayPiezas.get(i).equals(Alumno)) {
                for (Input inp : Alumno.inputs) {
                    notaAlumno = notaAlumno + 1 / notaMaxima;
                }
            }
        }
        double notaARestar = 0;
        for (; i < piezas.size(); i++) {

            for (Input inp : piezas.get(i).inputs) {
                notaARestar = notaARestar + 1 / notaMaxima;
            }
        }

        nota = String.valueOf((notaAlumno - notaARestar) * 10);

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
