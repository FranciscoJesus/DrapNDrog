/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import com.mongodb.BasicDBList;
import java.util.ArrayList;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
public class Solucion {

    public int id;

    @QueryParam("nombre")
    public String nombre;

    @QueryParam("piezas")
    public ArrayList<Pieza> piezas = new ArrayList<>();

    public Solucion() {
    }

    public Solucion(String n, ArrayList<Pieza> p) {
        nombre = n;
        piezas = p;
    }
    
    /**
     * Método que se encarga de convertir un objeto Solucion en un objeto JSON
     * @return 
     */
    public Document converADocument() {

        Document res = new Document();
        BasicDBList pieza = new BasicDBList();

        for (Pieza i : piezas) {
            pieza.add(i.converADocument());
        }
        res.append("piezas", pieza);

        return res;
    }
}
