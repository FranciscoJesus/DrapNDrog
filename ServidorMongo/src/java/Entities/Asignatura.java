/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.mongodb.BasicDBList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
public class Asignatura {

    public String id;
    public String nombre;
    public String idProfesor;
    public List<String> idAlumnos;

    public Asignatura() {

    }
    
    /**
     * Método que se utiliza para devolver una instancia de Asignatura en un objeto
     * Mongo
     * @return 
     */
    public Document converADocument() {
        Document res = new Document();

        res.append("id", id);
        res.append("nombre", nombre);
        res.append("idProfesor", idProfesor);
        BasicDBList alumnos = new BasicDBList();
        for (String i : idAlumnos) {
            alumnos.add(i);
        }
        res.append("idAlumnos", alumnos);

        return res;
    }
}
