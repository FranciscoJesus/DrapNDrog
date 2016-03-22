/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author FranciscoJesús
 */
@Entity("Alumno")
public class Alumno {

    @Id
    public String id = new ObjectId().toString();
    public String nombre;
    public String apellidos;
    public String dni;
    public String idUsuario;
    public List<String> idAsignaturas = new ArrayList<>();

    public Alumno() {

    }

}
