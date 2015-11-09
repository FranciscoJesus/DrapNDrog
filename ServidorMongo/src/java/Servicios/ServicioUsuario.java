/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import BD.MongoDB;
import Entities.Alumno;
import Entities.Profesor;
import Entities.Usuario;
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
@Path("Usuario")
public class ServicioUsuario {

    @POST
    @Path("insertarUsuario")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Usuario insertarUsuario(Usuario u) {

        try {
            u.password = u.Encriptar();
            MongoDB.insert(u);
        } catch (Exception e) {
            return null;
        }
        return u;
    }

    @GET
    @Path("buscarUsuario")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Usuario buscarUsuario(@QueryParam("id") String id) {

        Usuario u = MongoDB.findById(id, Usuario.class);

        return u == null ? new Usuario() : u;
    }
    
    @GET
    @Path("eliminarUsuario")
    public int eliminarUsuario(@QueryParam("id") String id) {

        return MongoDB.delete(id, Usuario.class);
    }

    @POST
    @Path("LoginAlumno")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Alumno LoginAlumno(Usuario u) {
        Alumno a = null;
        try {
            Map<String, String> where = new TreeMap<>();
            where.put("password", u.Encriptar());
            where.put("nombreUsuario", u.nombreUsuario);

            u = MongoDB.find(where, Usuario.class).get(0);

            if (u.rol == 1) {
                where.clear();
                where.put("idUsuario", u.id);
                a = MongoDB.find(where, Alumno.class).get(0);
            }

            return a;
        } catch (Exception e) {

            return null;
        }
    }

    @POST
    @Path("LoginProfesor")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Profesor LoginProfesor(Usuario u) {
        Profesor p = new Profesor();
        try {
            Map<String, String> where = new TreeMap<>();
            where.put("password", u.Encriptar());
            where.put("nombreUsuario", u.nombreUsuario);

            u = MongoDB.find(where, Usuario.class).get(0);

            if (u.rol == 2) {
                where.clear();
                where.put("idUsuario", u.id);
                p = MongoDB.find(where, Profesor.class).get(0);
            }

            return p;
        } catch (Exception e) {

            return null;
        }
    }
}
