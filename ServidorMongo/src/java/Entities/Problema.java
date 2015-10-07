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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
@Entity("Problemas")
public class Problema implements EntityMongo {

    @Id
    public String id;
    public String idProfesor;
    public String idAsignatura;
    public String nombreAsignatura;
    public String titulo;
    public String enunciado;
    @Embedded
    public ArrayList<Pieza> piezas = new ArrayList<>();
    @Embedded
    public ArrayList<Pieza> solucion = new ArrayList<>();

    public Problema() {

    }

    public Problema(Document object) {

        id = object.getObjectId("_id").toString();
        idProfesor = object.getString("idProfesor");
        idAsignatura = object.getString("idAsignatura");
        enunciado = object.getString("enunciado");
        ArrayList<Document> pieza = object.get("piezas", ArrayList.class);
        for (Document p : pieza) {
            piezas.add(new Pieza(p));
        }
        ArrayList<Document> soluciones = object.get("solucion", ArrayList.class);
        for (Document p : soluciones) {
            solucion.add(new Pieza(p));
        }
    }

    /**
     * Método que se encarga de convertir un objeto problema en un objeto JSON
     * (Document)
     *
     * @return
     */
    @Override
    public Document converADocument() {

        //inicializacion de la variable a devolver
        Document res = new Document();

        res.append("idProfesor", idProfesor);
        res.append("idAsignatura", idAsignatura);
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
        pieza = new BasicDBList();
        for (Pieza i : solucion) {
            pieza.add(i.converADocument());
        }
        res.append("solucion", pieza);

        return res;
    }

}
