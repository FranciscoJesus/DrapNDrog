/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import Entities.Asignatura;
import Entities.Problema;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:ServicioAsignatura
 * [Asignatura]<br>
 * USAGE:
 * <pre>
 *        AsignaturaJerseyClient client = new AsignaturaJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Sobremesa
 */
public class AsignaturaJerseyClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ServidorMongo/API";

    public AsignaturaJerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Asignatura");
    }

    public List<Asignatura> getAsignaturasProfesor(String id){
        AsignaturaJerseyClient cliente = new AsignaturaJerseyClient();
        GenericType<List<Asignatura>> gType = new GenericType<List<Asignatura>>(){};
        List<Asignatura> lista = (List<Asignatura>) cliente.buscarAsignaturaProfesor(gType,id);
        cliente.close();
        return lista;
    }
    
    public <T> T buscarAsignaturaProfesor(GenericType<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("buscarAsignaturaProfesor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T insertarAsignatura_XML(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarAsignatura").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    public <T> T insertarAsignatura_JSON(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarAsignatura").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T buscarAsignaturaProfesor(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("buscarAsignaturaProfesor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
