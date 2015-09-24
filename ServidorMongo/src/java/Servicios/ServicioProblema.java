/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Asignatura;
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
@Path("Problema")
public class ServicioProblema {

    /**
     * Método que inserta un problema en la base de datos
     *
     * @param n
     * @return
     */
    @POST
    @Path("insertarProblema")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Problema insertar(Problema n) {

        try {
            MongoDB.insert(n, "Problemas");
        } catch (Exception e) {
            return null;
        }
        return n;
    }

    /**
     * Servicio el cual a partir de un id se devuelve un problema específico
     *
     * @param id
     * @return
     */
    @GET
    @Path("buscarProblema")
    @Produces("application/json")
    public Problema leerProblema(@QueryParam("id") String id) {

        Document res = MongoDB.findById(id, "Problemas");
        return res != null ? new Problema(res) : null;
    }

    @GET
    @Path("buscarProblemasProfesor")
    @Produces("application/json")
    public ArrayList<Problema> leerProblemasProfesor(@QueryParam("id") String id) {

        BasicDBObject where = new BasicDBObject("idProfesor", id);
        ArrayList<Problema> problemasProfesor = new ArrayList<>();

        MongoCursor<Document> res = MongoDB.find(where, "Problemas");

        if (res != null) {
            while (res.hasNext()) {
                Document d = res.next();
                problemasProfesor.add(new Problema(d));
            }
        }

        return problemasProfesor;
    }

    @GET
    @Path("buscarProblemasAlumno")
    @Produces("application/json")
    public ArrayList<Problema> leerProblemasAlumno(@QueryParam("id") String id) {

        BasicDBObject where = new BasicDBObject("idAlumnos", id);
        ArrayList<Asignatura> AsignaturasAlumno = new ArrayList<>();
        ArrayList<Problema> ProblemasAlumno = new ArrayList<>();

        MongoCursor<Document> res = MongoDB.find(where, "Asignaturas");

        if (res != null) {
            while (res.hasNext()) {
                Document d = res.next();
                AsignaturasAlumno.add(new Asignatura(d));

                where = new BasicDBObject("idAsignatura", d.getObjectId("_id").toString());
                MongoCursor<Document> resProblemas = MongoDB.find(where, "Problemas");

                if (null != resProblemas) {
                    while (resProblemas.hasNext()) {

                        Document dAlumno = resProblemas.next();
                        ProblemasAlumno.add(new Problema(dAlumno));
                    }
                }

            }
        }

        return ProblemasAlumno;
    }
}
