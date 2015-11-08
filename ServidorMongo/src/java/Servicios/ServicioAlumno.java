/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BD.MongoDB;
import Entities.Alumno;
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
@Path("Alumno")
public class ServicioAlumno {

    @POST
    @Path("insertarAlumno")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Alumno insertarUsuario(Alumno a) {

        try {
            MongoDB.insert(a);
        } catch (Exception e) {
            a.nombre = "error";
            return a;
        }
        return a;
    }

    @GET
    @Path("buscarAlumno")
    @Produces("application/json")
    public Alumno buscarAlumno(@QueryParam("id") String id) {

        return MongoDB.findById(id, Alumno.class);

    }

    @GET
    @Path("eliminarAlumno")
    public int eliminarAlumno(@QueryParam("id") String id) {

        return MongoDB.delete(id, Alumno.class);
    }
}
