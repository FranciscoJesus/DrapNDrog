/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BD.MongoDB;
import Entities.Borrador;
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
 * @author Javier Ordoñez 
 */
@Path("Borrador")
public class ServicioBorrador {

    /**
     * Método que inserta una Solucion Parcial  en la base de datos
     *
     * @param bor
     * @return bor
     */
    @POST
    @Path("insertarBorrador")
    @Consumes({"application/json", "application/xml"})
    @Produces("application/json")
    public Borrador insertarBorrador(Borrador bor) {
        try {

            
            MongoDB.insert(bor);

            return bor;

        } catch (Exception e) {
            return bor;
        }
    }
    

    /**
     * Servicio el cual a partir de un id se devuelve una Solucion Parcial específica
     *
     * @param id
     * @return
     */
    @GET
    @Path("buscarBorrador")
    @Produces("application/json")
    public Borrador buscarBorrador(@QueryParam("id") String id) {

        return MongoDB.findById(id, Borrador.class);
    }

    @GET
    @Path("eliminarBorrador")
    public String eliminarBorrador(@QueryParam("id") String id) {

        return String.valueOf(MongoDB.delete(id, Borrador.class));
    }

   

    @GET
    @Path("BorradoresUnAlumno")
    @Produces("application/json")
    public List<Borrador> BorradoresUnAlumno(@QueryParam("id") String idAlumno) {

      
        Map<String, String> where = new TreeMap<>();
        where.put("idAlumno", idAlumno);

        return MongoDB.find(where, Borrador.class);
    }

}
