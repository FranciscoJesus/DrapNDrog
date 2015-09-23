/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

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
public class Solucion implements EntityMongo {

    public String id;
    public String idAlumno;
    public String idProblema;
    public String nombre;
    public ArrayList<Pieza> piezas = new ArrayList<>();

    public Solucion() {

    }

    public Solucion(Document object) {

        id = object.getObjectId("_id").toString();
        idAlumno = object.getString("idAlumno");
        idProblema = object.getString("idProblema");
        //nombre = object.getString("nombre");
        ArrayList<Document> pieza = object.get("piezas", ArrayList.class);
        if (pieza != null) {
            for (Document d : pieza) {
                piezas.add(new Pieza(d));
            }
        }
    }

    /**
     * Método que se encarga de convertir un objeto Solucion en un objeto JSON
     *
     * @return
     */
    @Override
    public Document converADocument() {

        Document res = new Document();

        res.append("idAlumno", idAlumno);
        res.append("idProblema", idProblema);
        BasicDBList pieza = new BasicDBList();
        for (Pieza i : piezas) {
            pieza.add(i.converADocument());
        }
        res.append("piezas", pieza);

        return res;
    }
}
