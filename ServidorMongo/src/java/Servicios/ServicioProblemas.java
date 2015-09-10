/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.Problema;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
@Path("Problema")
public class ServicioProblemas {

    MongoClient mongoClient;

    MongoDatabase mongoDB;

    /**
     * Método que inster un problema en la base de datos
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
            abrirConexion();
            //Accedemos a la tabla
            MongoCollection<Document> problemas = mongoDB.getCollection("Problemas");
            //insertamos el problema
            problemas.insertOne(n.converADocument());
            //cerramos conexión
            cerrarConexion();
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
    public String leerProblema(@QueryParam("id") String id) {
        
        /*Se abré la conexión con la base de datos y
        obtenemos la colleccion "Problemas"*/
        abrirConexion();
        
        MongoCollection<Document> problemas = mongoDB.getCollection("Problemas");
        
        /*creación de un objeto "ObjectId" el cual nos permitirá hacer una
        búsqueda de un problema por su ID*/
        ObjectId objetoId = new ObjectId(id);
        BasicDBObject query = new BasicDBObject("_id", objetoId);
        Document res = problemas.find(query).first();
        
        /*si no existe ese problema devolvemos un JSON
        que nos advierte que no hay salida*/
        if (res == null) {
            res = new Document("salida", "No existe ningun problema con ese id");
        }
        cerrarConexion();
        //Devolvemos el JSON del objeto Document
        return res.toJson();
    }
    

    /**
     * Método que se utiliza para abrir conexión con la base de datos
     */
    private void abrirConexion() {
        mongoClient = new MongoClient("localhost");
        //accedemos a la base de datos específica
        mongoDB = mongoClient.getDatabase("Prueba");
    }

    /**
     * Método que se utiliza para cerrar la conexión con la base de datos
     */
    private void cerrarConexion() {
        mongoClient.close();
    }
}
