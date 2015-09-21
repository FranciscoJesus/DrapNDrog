/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Asignatura;
import Entities.EntityMongo;
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
            MongoDB.insert(a, "Asignaturas");
        } catch (Exception e) {
            return null;
        }
        return a;
    }

    @GET
    @Path("buscarAsignatura")
    @Produces("application/json")
    public EntityMongo buscarAsignatura(@QueryParam("id") String id) {

        return new Asignatura(MongoDB.findById(id, "Asignaturas"));
    }

}
