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
 * @author Javier Ordoñez  
 */
@XmlRootElement
@Entity("Borrador")
public class Borrador {

    @Id
    public String id = new ObjectId().toString();
    
    public String idAlumno;
    public String idProblema;
    public String nombre;
    public String apellidos;
    @Embedded
    public ArrayList<Pieza> piezas = new ArrayList<>();
    
    public Borrador(){
        
    }
}