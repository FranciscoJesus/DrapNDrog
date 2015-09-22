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

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
public class Profesor implements EntityMongo {

    public String id;
    public String nombre;
    public String apellido;
    public String idUsuario;
    public List<String> asignaturas = new ArrayList<>();

    public Profesor() {

    }

    /**
     * Método que se encarga de convertir un objeto Profesor en un objeto JSON
     *
     * @return
     */
    @Override
    public Document converADocument() {

        Document res = new Document();

        res.append("nombre", nombre);
        res.append("apellido", apellido);
        res.append("idUsuario", idUsuario);
        BasicDBList input = new BasicDBList();
        for (String i : asignaturas) {
            input.add(i);
        }
        res.append("asignaturas", input);

        return res;
    }

    /**
     *
     * @param object
     * @return
     */
    public Profesor(Document object) {

        id = object.getObjectId("_id").toString();
        nombre = object.getString("nombre");
        apellido = object.getString("apellido");
        idUsuario = object.getString("idUsuario");
        asignaturas = object.get("asignatura", ArrayList.class);
    }
}
