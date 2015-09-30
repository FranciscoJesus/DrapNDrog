/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entities.EntityMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author FranciscoJesús
 */
public class MongoDB {

    public final static Morphia morphia = new Morphia();

    public static Datastore ds;

    public static MongoClient mongoClient;

    public static MongoDatabase mongoDB;

    /**
     * Método que se utiliza para abrir conexión con la base de datos
     */
    public static void abrirConexion() {
        mongoClient = new MongoClient("localhost");
        //accedemos a la base de datos específica
        mongoDB = mongoClient.getDatabase("Prueba");

        ds = morphia.createDatastore(mongoClient, "Prueba");
    }

    /**
     * Método que se utiliza para cerrar la conexión con la base de datos
     */
    public static void cerrarConexion() {
        mongoClient.close();
    }

    public static <T> T findById(String id, Class<T> object) {

        try {
            //Abrimos conexion
            abrirConexion();

            T res;
            ObjectId oid = new ObjectId(id);
            res = ds.get(object, oid);

            cerrarConexion();

            return res;

        } catch (Exception e) {
            return null;
        }
    }

    public static MongoCursor<Document> find(BasicDBObject where, String collectionName) {

        MongoCursor<Document> resIterator = null;

        abrirConexion();

        MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
        FindIterable<Document> res = collection.find(where);

        if (res != null) {
            resIterator = res.iterator();
        }
        MongoDB.cerrarConexion();

        return resIterator;
    }

    public static <T> List<T> find(Map<String, String> wheres, Class<T> clas) {

        abrirConexion();

        List<T> array;

        Query<T> query = ds.createQuery(clas);
        for(Entry<String,String> s : wheres.entrySet()){
            query = query.field(s.getKey()).equal(s.getValue());
        }
        
        array = query.asList();
        
        MongoDB.cerrarConexion();

        return array;
    }

    public static <T> void insert(T object) {

        abrirConexion();
        //Accedemos a la tabla
        ds.save(object);
        //cerramos conexión
        cerrarConexion();

    }

    public static <T extends EntityMongo> void update(String id, T object, String collectionName) {

        abrirConexion();
        //Accedemos a la tabla
        MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
        //insertamos el problema
        collection.findOneAndUpdate(new BasicDBObject("_id", new ObjectId(id)), object.converADocument());
        //cerramos conexión
        cerrarConexion();
    }

    public static <T extends EntityMongo> void delete(String id, String collectionName) {

        abrirConexion();
        //Accedemos a la tabla
        MongoCollection<Document> collection = mongoDB.getCollection(collectionName);
        //insertamos el problema
        collection.findOneAndDelete(new BasicDBObject("_id", new ObjectId(id)));
        //cerramos conexión
        cerrarConexion();
    }

}
