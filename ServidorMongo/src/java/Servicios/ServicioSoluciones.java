/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Solucion;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author FranciscoJesús
 */
@Path("Solucion")
public class ServicioSoluciones {

    @POST
    @Path("insertarSolucion")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Solucion insertarSolucion(Solucion sol) {
        try {
            MongoDB.abrirConexion();
            //Accedemos a la tabla
            MongoCollection<Document> problemas = MongoDB.mongoDB.getCollection("Soluciones");
            //insertamos el problema
            problemas.insertOne(sol.converADocument());
            //cerramos conexión
            MongoDB.cerrarConexion();
        } catch (Exception e) {
            return null;
        }
        return sol;
    }

    @GET
    @Path("buscarSolucion")
    @Produces("application/json")
    public String buscarSolucion(@QueryParam("id") String id) {
        Document res = null;
        try {
            MongoDB.abrirConexion();
            //Accedemos a la tabla
            MongoCollection<Document> problemas = MongoDB.mongoDB.getCollection("Soluciones");
            //insertamos el problema
            ObjectId objetoId = new ObjectId(id);
            BasicDBObject query = new BasicDBObject("_id", objetoId);
            res = problemas.find(query).first();

            if (res == null) {
                res = new Document("salida", "No existe ningun problema con ese id");
            }
            //cerramos conexión
            MongoDB.cerrarConexion();
        } catch (Exception e) {
            return null;
        }
        return res.toJson();
    }

}
