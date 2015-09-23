/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Asignatura;
import Entities.EntityMongo;
import Entities.Problema;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
@Path("Asignatura")
public class ServicioAsignatura {

    @POST
    @Path("insertarAsignatura")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Asignatura insertarAsignatura(Asignatura a) {

        try {
            MongoDB.insert(a, "Asignaturas");
        } catch (Exception e) {
            return null;
        }
        return a;
    }

    @GET
    @Path("buscarAsignatura")
    @Produces("application/json")
    public Asignatura buscarAsignatura(@QueryParam("id") String id) {

        Document res = MongoDB.findById(id, "Asignaturas");
        return res != null ? new Asignatura(MongoDB.findById(id, "Asignaturas")) : null;
    }

    @GET
    @Path("buscarAsignaturaProfesor")
    @Produces("application/json")
    public ArrayList<Asignatura> buscarAsignaturaProfesor(@QueryParam("id") String id) {

        BasicDBObject where = new BasicDBObject("idProfesor", id);
        ArrayList<Asignatura> asignaturasProfesor = new ArrayList<>();

        MongoCursor<Document> res = MongoDB.find(where, "Asignaturas");

        if (res != null) {
            while (res.hasNext()) {
                Document d = res.next();
                asignaturasProfesor.add(new Asignatura(d));
            }
        }

        return asignaturasProfesor;
    }

}
