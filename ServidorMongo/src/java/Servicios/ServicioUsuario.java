/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Alumno;
import Entities.EntityMongo;
import Entities.Profesor;
import Entities.Usuario;
import static Servicios.MongoDB.abrirConexion;
import static Servicios.MongoDB.cerrarConexion;
import static Servicios.MongoDB.mongoDB;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.bson.Document;

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
            MongoDB.insert(u, "Usuarios");
        } catch (Exception e) {
            return null;
        }
        return u;
    }

    @POST
    @Path("LoginAlumno")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Alumno LoginAlumno(Usuario u) {
        Document res;
        Alumno resObject = null;
        try {
            abrirConexion();
            //Accedemos a la tabla
            MongoCollection<Document> problemas = mongoDB.getCollection("Usuarios");
            //buscamos un usuario y contraseña que concuerden
            BasicDBObject user = new BasicDBObject("usuario", u.usuario);
            user.append("password", u.Encriptar());
            user.append("rol", u.rol);
            res = problemas.find(user).first();
            /*si el resultado es nulo significa que no existe ningun usuario con
             esa contraseña*/

            if (res != null) {

                int rol = res.getInteger("rol");

                if (rol == 1) {
                    MongoCollection<Document> alumnos = mongoDB.getCollection("Alumno");
                    res = alumnos.find(new BasicDBObject("id", res.getString("id"))).first();
                    resObject = new Alumno(res);
                }
            } else {
                //si el resultado es nulo devolvemos un mensaje en el
            }
            //cerramos conexión
            cerrarConexion();

            //return res.toJson();
            return resObject;
        } catch (Exception e) {
            //return new Document("salida", e.toString()).toJson();
            return null;
        }
    }
    
    @POST
    @Path("LoginProfesor")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Profesor LoginProfesor(Usuario u) {
        Document res;
        Profesor resObject = null;
        try {
            abrirConexion();
            //Accedemos a la tabla
            MongoCollection<Document> problemas = mongoDB.getCollection("Usuarios");
            //buscamos un usuario y contraseña que concuerden
            BasicDBObject user = new BasicDBObject("usuario", u.usuario);
            user.append("password", u.Encriptar());
            user.append("rol", u.rol);
            res = problemas.find(user).first();
            /*si el resultado es nulo significa que no existe ningun usuario con
             esa contraseña*/

            if (res != null) {

                int rol = res.getInteger("rol");

                if (rol == 2) {
                    MongoCollection<Document> alumnos = mongoDB.getCollection("Alumno");
                    res = alumnos.find(new BasicDBObject("id", res.getString("id"))).first();
                    resObject = new Profesor(res);
                }
            } else {
                //si el resultado es nulo devolvemos un mensaje en el
            }
            //cerramos conexión
            cerrarConexion();

            //return res.toJson();
            return resObject;
        } catch (Exception e) {
            //return new Document("salida", e.toString()).toJson();
            return null;
        }
    }
}
