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
public class Alumno {

    public String id;
    public String nombre;
    public String apellido;
    public String dni;
    public String idUsuario;
    public List<String> idAsignaturas;
    
    public Alumno() {
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
