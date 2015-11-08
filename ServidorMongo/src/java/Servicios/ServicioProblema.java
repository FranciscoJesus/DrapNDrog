/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BD.MongoDB;
import Entities.Asignatura;
import Entities.Problema;
import java.util.ArrayList;
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
            MongoDB.insert(n);
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

        return MongoDB.findById(id, Problema.class);
    }

    @GET
    @Path("eliminarProblema")
    public int eliminarProblema(@QueryParam("id") String id) {

        return MongoDB.delete(id, Problema.class);
    }

    @GET
    @Path("buscarProblemasProfesor")
    @Produces("application/json")
    public List<Problema> leerProblemasProfesor(@QueryParam("id") String id) {

        Map<String, String> where = new TreeMap<>();
        where.put("idProfesor", id);
        List<Problema> res = MongoDB.find(where, Problema.class);

        return res;
    }

    @GET
    @Path("buscarProblemasAlumno")
    @Produces("application/json")
    public List<Problema> leerProblemasAlumno(@QueryParam("id") String id) {

        List<Problema> res = new ArrayList<>();
        Map<String, String> where = new TreeMap<>();
        where.put("idAlumnos", id);
        List<Asignatura> resAsignatura = MongoDB.find(where, Asignatura.class);

        for (Asignatura a : resAsignatura) {
            where.clear();
            where.put("idAsignatura", a.id);
            res.addAll(MongoDB.find(where, Problema.class));
        }

        return res;
    }

    @GET
    @Path("buscarProblemasAsignatura")
    @Produces("application/json")
    public List<Problema> leerProblemasAsignatura(@QueryParam("id") String idAsignatura) {

        Map<String, String> where = new TreeMap<>();
        where.put("idAsignatura", idAsignatura);
        List<Problema> res = MongoDB.find(where, Problema.class);

        return res;
    }
}
