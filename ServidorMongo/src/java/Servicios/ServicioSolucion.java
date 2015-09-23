/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Solucion;
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
@Path("Solucion")
public class ServicioSolucion {

    @POST
    @Path("insertarSolucion")
    @Consumes({"application/json", "application/xml"})
    @Produces("application/json")
    public Solucion insertarSolucion(Solucion sol) {
        try {
            MongoDB.insert(sol, "Soluciones");
        } catch (Exception e) {
            return null;
        }
        return sol;
    }

    @GET
    @Path("buscarSolucion")
    @Produces("application/json")
    public Solucion buscarSolucion(@QueryParam("id") String id) {

        Document res = MongoDB.findById(id, "Soluciones");
        return res != null ? new Solucion(res) : null;
    }

}
