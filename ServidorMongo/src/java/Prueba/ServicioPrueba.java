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
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
@Path("Prueba")
public class ServicioPrueba {
    
    @GET
    @Produces("application/json")
    public String Hello(){
        return "Hello";
    }
    
    @GET
    @Path("insertar")
    @Produces("application/json")
    public Nombre insertar(@BeanParam Nombre n){
        MongoClient mongoClient = new MongoClient( "localhost" );
        //accedemos a la base de datos específica
        MongoDatabase mongoDB = mongoClient.getDatabase("Prueba");
        //Accedemos a la tabla
        MongoCollection<Document> nombres = mongoDB.getCollection("Nombres");
        //creamos la query
        BasicDBObject query = new BasicDBObject("Nombre",n.nombre);        
        Document res = nombres.find(query).first();
        if(res==null){
            //Creamos un objeto
            res = new Document("Nombre", n.nombre);
            //Lo insertamos
            nombres.insertOne(res);
        }       
        Nombre nombre = new Nombre(res.getString("Nombre"));
        return nombre;
    }
}
