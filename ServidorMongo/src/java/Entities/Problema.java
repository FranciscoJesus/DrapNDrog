/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
@Entity("Problemas")
public class Problema {

    @Id
    public ObjectId id;
    public String idProfesor;
    public String idAsignatura;
    public String nombreAsignatura;
    public String titulo;
    public String enunciado;
    @Embedded
    public ArrayList<Pieza> piezas = new ArrayList<>();
    @Embedded
    public ArrayList<Pieza> solucion = new ArrayList<>();

    public Problema() {

    }
}
