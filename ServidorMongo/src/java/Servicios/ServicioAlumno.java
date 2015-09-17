/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Alumno;
import static Servicios.MongoDB.abrirConexion;
import static Servicios.MongoDB.cerrarConexion;
import static Servicios.MongoDB.mongoDB;
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
@Path("Alumno")
public class ServicioAlumno {

    @POST
    @Path("insertarAlumno")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Alumno insertarUsuario(Alumno a) {

        try {
            abrirConexion();
            //Accedemos a la tabla
            MongoCollection<Document> problemas = mongoDB.getCollection("Alumno");
            //insertamos el problema
            problemas.insertOne(a.converADocument());
            //cerramos conexión
            cerrarConexion();
        } catch (Exception e) {
            return null;
        }
        return a;
    }
}
