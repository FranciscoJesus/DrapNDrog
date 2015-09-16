/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.mongodb.BasicDBList;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */

@XmlRootElement
public class Problema {

    public String id;
    public String idProfesor;
    public String enunciado;
    public ArrayList<Pieza> piezas = new ArrayList<>();
    public Solucion solucion;

    public Problema() {
    }
    
    /**
     * Método que se encarga de convertir un objeto problema en un objeto JSON
     * (Document)
     * @return 
     */
    public Document converADocument() {
        
        //inicializacion de la variable a devolver
        Document res = new Document();
        
        res.append("idProfesor", idProfesor);
        //añadimos el enunciado
        res.append("enunciado", enunciado);
        //creamos un array donde vamos a almacenar la lista de piezas
        BasicDBList pieza = new BasicDBList();
        for (Pieza i : piezas) {
            pieza.add(i.converADocument());
        }
        //añadimos las piezas
        res.append("piezas", pieza);
        //añadimos la solución
        res.append("solucion", solucion.converADocument());

        return res;
    }
}
