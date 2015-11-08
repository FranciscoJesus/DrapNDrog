/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BD.MongoDB;
import Entities.Profesor;
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
@Path("Profesor")
public class ServicioProfesor {

    @POST
    @Path("insertarProfesor")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Profesor insertarProfesor(Profesor p) {

        try {
            MongoDB.insert(p);
        } catch (Exception e) {
            return null;
        }
        return p;
    }

    @GET
    @Path("buscarProfesor")
    @Produces("application/json")
    public Profesor leerProfesor(@QueryParam("id") String id) {

        return MongoDB.findById(id, Profesor.class);
    }
    
    @GET
    @Path("eliminarProfesor")
    public int eliminarProfesor(@QueryParam("id") String id) {

        return MongoDB.delete(id, Profesor.class);
    }
}
