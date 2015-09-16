/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

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
import org.bson.BasicBSONObject;
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
            abrirConexion();
            //Accedemos a la tabla
            MongoCollection<Document> problemas = mongoDB.getCollection("Usuarios");
            //insertamos el problema
            problemas.insertOne(u.converADocument());
            //cerramos conexión
            cerrarConexion();
        } catch (Exception e) {
            return null;
        }
        return u;
    }

    @POST
    @Path("Login")
    @Consumes({"application/xml", "application/json"})
    public String Login(Usuario u) {
        Document res = new Document();
        try {
            abrirConexion();
            //Accedemos a la tabla
            MongoCollection<Document> problemas = mongoDB.getCollection("Usuarios");
            MongoCollection<Document> profesores = mongoDB.getCollection("Profesor");
            //buscamos un usuario y contraseña que concuerden
            BasicDBObject user = new BasicDBObject("usuario",u.usuario);
            user.append("rol",u.rol);
            user.append("password",u.Encriptar());
            res = problemas.find(user).first();
            if(u.rol==2){
                profesores.find(new BasicDBObject("id",res.getString("id")));
            }
            res.getString("");
            //cerramos conexión
            cerrarConexion();
            
            return res.toJson();
            
        } catch (Exception e) {
            return new Document("salida", e.toString()).toJson();
        }
    }
}
