/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import Entities.Problema;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:ServicioProblema
 * [Problema]<br>
 * USAGE:
 * <pre>
 *        ProblemasJerseyClient client = new ProblemasJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Sobremesa
 */
public class ProblemasJerseyClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ServidorMongo/API";

    public List<Problema> getProblemasProfesor(String id){
        ProblemasJerseyClient cliente = new ProblemasJerseyClient();
        GenericType<List<Problema>> gType = new GenericType<List<Problema>>(){};
        List<Problema> lista = (List<Problema>) cliente.leerProblemasProfesor(gType,id);
        cliente.close();
        return lista;
    }
    
    public ProblemasJerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Problema");
    }
    
    public <T> T leerProblemasProfesor(GenericType<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("buscarProblemasProfesor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T insertar_XML(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarProblema").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    public <T> T insertar_JSON(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarProblema").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T leerProblema(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("buscarProblema");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}