/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.mongodb.BasicDBList;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
public class Profesor {

    public String nombre;
    public String apellido;
    public String idUsuario;
    public List<String> asignaturas = new ArrayList<>();

    public Profesor() {

    }

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
}
