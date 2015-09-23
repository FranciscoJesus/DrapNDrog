/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.EntityMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author FranciscoJesús
 */
public class MongoDB {

    public static MongoClient mongoClient;

    public static MongoDatabase mongoDB;

    /**
     * Método que se utiliza para abrir conexión con la base de datos
     */
    public static void abrirConexion() {
        mongoClient = new MongoClient("localhost");
        //accedemos a la base de datos específica
        mongoDB = mongoClient.getDatabase("Prueba");
    }

    /**
     * Método que se utiliza para cerrar la conexión con la base de datos
     */
    public static void cerrarConexion() {
        mongoClient.close();
    }

    public static Document findById(String id, String collection) {
        Document res;
        try {
            abrirConexion();
            //Accedemos a la tabla
            MongoCollection<Document> collectionDB = mongoDB.getCollection(collection);

            /*creación de un objeto "ObjectId" el cual nos permitirá hacer una
             búsqueda de un problema por su ID*/
            ObjectId objetoId = new ObjectId(id);
            BasicDBObject query = new BasicDBObject("_id", objetoId);
            res = collectionDB.find(query).first();
//            if (res == null) {
//                res = new Document("salida", "no se ha encontrado ningun objeto con este id");
//            }
            
            //cerramos conexión
            cerrarConexion();

            return res;

        } catch (Exception e) {
            return null;
        }
    }

    public static <T extends EntityMongo> void insert(T object, String collectionName) {

        abrirConexion();
        //Accedemos a la tabla
        MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
        //insertamos el problema
        collection.insertOne(object.converADocument());
        //cerramos conexión
        cerrarConexion();

    }
}
