/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Entities.Problema;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 *
 * @author FranciscoJesús
 */
public class MongoDB {

    public final static Morphia morphia = new Morphia();

    public static Datastore ds;

    public static MongoClient mongoClient;

    /**
     * Método que se utiliza para abrir conexión con la base de datos
     */
    public static void abrirConexion() {
        mongoClient = new MongoClient("localhost");

        ds = morphia.createDatastore(mongoClient, "Prueba");
    }

    /**
     * Método que se utiliza para cerrar la conexión con la base de datos
     */
    public static void cerrarConexion() {
        mongoClient.close();
    }

    /**
     * Método que busca en la base de datos un objeto del tipo T con el id
     *
     * @param <T>
     * @param id
     * @param object
     * @return
     */
    public static <T> T findById(String id, Class<T> object) {

        try {
            //Abrimos conexion
            abrirConexion();

            T res;
            res = ds.get(object, id);

            cerrarConexion();

            return res;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método que busca en la base de dates una lista de elemento según que
     * condiciones
     *
     * @param <T>
     * @param wheres
     * @param clas
     * @return
     */
    public static <T> List<T> find(Map<String, String> wheres, Class<T> clas) {

        abrirConexion();

        List<T> array;

        Query<T> query = ds.createQuery(clas);
        for (Entry<String, String> s : wheres.entrySet()) {
            query = query.field(s.getKey()).equal(s.getValue());
        }

        array = query.asList();

        MongoDB.cerrarConexion();

        return array;
    }

    /**
     * Se inserta un objeto en la base de datos
     *
     * @param <T>
     * @param object
     */
    public static <T> void insert(T object) {

        abrirConexion();
        //Accedemos a la tabla
        ds.save(object);
        //cerramos conexión
        cerrarConexion();

    }

    /**
     * Update de la base de datos usando la API de Morphia
     *
     * @param <T>
     * @param id
     * @param object
     * @param change
     */
    public static <T> void update(String id, Class<T> object,
            Map<String, Object> change) {

        abrirConexion();
        //Accedemos a la tabla
        UpdateOperations<T> ops = ds.createUpdateOperations(object);
        Query<T> elem = ds.createQuery(object).field("_id").equal(id);

        for (Entry<String, Object> s : change.entrySet()) {
            ops = ops.set(s.getKey(), s.getValue());
        }
        
        ds.update(elem, ops);

        //cerramos conexión
        cerrarConexion();
    }

    public static <T> int delete(String id, Class<T> entity) {

        try{
            int res;
            abrirConexion();
            //eliminamos el elemento de la tabla entity 
            Query<T> elem = ds.createQuery(entity).field("_id").equal(id);
            WriteResult d = ds.delete(elem);

            res = d.getN();
            //cerramos conexión
            cerrarConexion();

            return res;
        }catch(Exception e){
            return -1;
        }
    }
    
    public static void insertPrueba(){
        Problema p1,p2;
        p1 = new Problema();
        p2 = new Problema();
        
        abrirConexion();
        
        p1.enunciado = "Esto es una prueba ";
        p1.nombreAsignatura="asdasd";
        p2.enunciado = "Esto es una prueba 2";
        p2.nombreAsignatura="asdasd";
        
        ds.save(p1);
        p2.id = p1.id;
        
        ds.save(p2);
        
        cerrarConexion();
        
    }
    

}
