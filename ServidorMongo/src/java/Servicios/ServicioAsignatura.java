/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BD.MongoDB;
import Entities.Asignatura;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

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
            MongoDB.insert(a);
        } catch (Exception e) {
            return null;
        }
        return a;
    }

    @GET
    @Path("buscarAsignatura")
    @Produces("application/json")
    public Asignatura buscarAsignatura(@QueryParam("id") String id) {

        return MongoDB.findById(id, Asignatura.class);
    }

    @GET
    @Path("eliminarAsignatura")
    public int eliminarAsignatura(@QueryParam("id") String id) {

        return MongoDB.delete(id, Asignatura.class);
    }

    @GET
    @Path("buscarAsignaturaProfesor")
    @Produces("application/json")
    public List<Asignatura> buscarAsignaturaProfesor(@QueryParam("id") String id) {

        Map<String, String> where = new TreeMap<>();
        where.put("idProfesor", id);
        List<Asignatura> res = MongoDB.find(where, Asignatura.class);

        return res;
    }

}
