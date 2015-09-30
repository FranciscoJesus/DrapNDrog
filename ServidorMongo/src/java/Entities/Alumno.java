/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.mongodb.BasicDBList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author FranciscoJesús
 */
@Entity("Alumno")
@XmlRootElement
public class Alumno implements EntityMongo {

    @Id
    public String id;
    public String nombre;
    public String apellido;
    public String dni;
    public String idUsuario;
    public List<String> idAsignaturas = new ArrayList<>();

    public Alumno() {

    }

    public Alumno(Document object) {

        id = object.getObjectId("_id").toString();
        nombre = object.getString("nombre");
        apellido = object.getString("apellido");
        dni = object.getString("dni");
        idUsuario = object.getString("idUsuario");
        idAsignaturas = object.get("idAsignaturas", ArrayList.class);
    }

    /**
     * Método que se utiliza para devolver una instancia de Asignatura en un
     * objeto Mongo
     *
     * @return
     */
    @Override
    public Document converADocument() {

        Document res = new Document();

        res.append("nombre", nombre);
        res.append("apellido", apellido);
        res.append("dni", dni);
        res.append("idUsuario", idUsuario);
        BasicDBList asignaturas = new BasicDBList();
        for (String i : idAsignaturas) {
            asignaturas.add(i);
        }
        res.append("idAsignaturas", asignaturas);

        return res;
    }

}
