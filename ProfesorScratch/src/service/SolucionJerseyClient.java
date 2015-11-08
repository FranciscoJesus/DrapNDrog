/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import Entities.Solucion;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:ServicioSolucion
 * [Solucion]<br>
 * USAGE:
 * <pre>
 *        SolucionJerseyClient client = new SolucionJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Sobremesa
 */
public class SolucionJerseyClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ServidorMongo/API";

    public SolucionJerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Solucion");
    }

    public <T> T insertarSolucion_XML(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarSolucion").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    public <T> T insertarSolucion_JSON(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarSolucion").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }
    
    public List<Solucion> getSoluciones(String id){
        SolucionJerseyClient cliente = new SolucionJerseyClient();
        GenericType<List<Solucion>> gType = new GenericType<List<Solucion>>(){};
        List<Solucion> lista = (List<Solucion>) cliente.SolucionesUnProblema(gType,id);
        cliente.close();
        return lista;
    }
    
    public <T> T SolucionesUnProblema(GenericType<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("SolucionesDeProblema");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
        
    public <T> T SolucionesUnProblema(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("SolucionesDeProblema");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String eliminarSolucion(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("eliminarSolucion");
        //return resource.get(String.class);
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public <T> T cambiarNota(Class<T> responseType, String id, String nota) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        if (nota != null) {
            resource = resource.queryParam("nota", nota);
        }
        resource = resource.path("cambiarNota");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T buscarSolucion(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("buscarSolucion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
