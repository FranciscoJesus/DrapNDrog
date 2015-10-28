/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Alumno;
import Entities.Problema;
import Entities.Solucion;
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
@Path("Solucion")
public class ServicioSolucion {

    @POST
    @Path("insertarSolucion")
    @Consumes({"application/json", "application/xml"})
    @Produces("application/json")
    public Solucion insertarSolucion(Solucion sol) {
        try {
            Problema p = MongoDB.findById(sol.idProblema, Problema.class);
            sol.ponerNota(p.solucion);
            MongoDB.insert(sol);
        } catch (Exception e) {
            return null;
        }
        return sol;
    }

    @GET
    @Path("buscarSolucion")
    @Produces("application/json")
    public Solucion buscarSolucion(@QueryParam("id") String id) {

        return MongoDB.findById(id, Solucion.class);
    }

    @GET
    @Path("eliminarSolucion")
    public int eliminarSolucion(@QueryParam("id") String id) {

        return MongoDB.delete(id, Solucion.class);
    }

    @GET
    @Path("cambiarNota")
    @Produces("application/json")
    public Solucion cambiarNota(@QueryParam("id") String idSolucion, @QueryParam("nota") String nota) {

        Solucion res = null;

        Map<String, String> campos = new TreeMap<>();

        campos.put("nota", nota);

        try {
            MongoDB.update(idSolucion, Solucion.class, campos);
        } catch (Exception e) {
            res = new Solucion();
        }

        return res;
    }

    @GET
    @Path("SolucionesDeProblema")
    @Produces("application/json")
    public Map<Solucion, Alumno> SolucionesUnProblema(@QueryParam("id") String idProblema) {

        Map<Solucion, Alumno> res = new TreeMap<>();
        Map<String, String> where = new TreeMap<>();

        where.put("idProblema", idProblema);

        List<Solucion> soluciones = MongoDB.find(where, Solucion.class);
        for (Solucion s : soluciones) {
            Alumno a = MongoDB.findById(s.idAlumno, Alumno.class);
            res.put(s, a);
        }
        return res;
    }

}
