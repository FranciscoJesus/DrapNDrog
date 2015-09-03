/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
@Path("Profesor")
public class ServicioPrueba {
    
    @GET
    @Produces("application/json")
    public String Hello(){
        return "Hello";
    }
    
    @POST
    @Path("insertarProblema")
    @Consumes({"application/xml", "application/json"})
    @Produces("application/json")
    public Problema insertar(Problema n){
//        MongoClient mongoClient = new MongoClient( "localhost" );
//        //accedemos a la base de datos específica
//        MongoDatabase mongoDB = mongoClient.getDatabase("Prueba");
//        //Accedemos a la tabla
//        MongoCollection<Document> nombres = mongoDB.getCollection("Nombres");
//        //creamos la query
//        BasicDBObject query = new BasicDBObject("Nombre",n.nombre);        
//        Document res = nombres.find(query).first();
//        if(res==null){
//            //Creamos un objeto
//            res = new Document("Nombre", n.nombre);
//            
//            //Lo insertamos
//            nombres.insertOne(res);
//        }       
//        Solucion nombre = new Solucion(res.getString("Nombre"));
//        return nombre;
        return n;
    }
}
