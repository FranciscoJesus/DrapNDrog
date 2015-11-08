/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author FranciscoJesús
 */
@Entity("Profesor")
@XmlRootElement
public class Profesor {

    @Id
    public String id;
    public String nombre;
    public String apellido;
    public String idUsuario;
    public List<String> asignaturas = new ArrayList<>();

    public Profesor() {

    }

}
