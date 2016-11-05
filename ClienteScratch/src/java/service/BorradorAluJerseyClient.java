/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entities.Borrador;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:ServicioBorrador
 * [Borrador]<br>
 * USAGE:
 * <pre>
 *        BorradorAluJerseyClient client = new BorradorAluJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Javier Ordoñez Martín
 */
public class BorradorAluJerseyClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ServidorMongo/API";

    public BorradorAluJerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Borrador");
    }

    public <T> T insertarBorrador_XML(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarBorrador").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    public <T> T insertarBorrador_JSON(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarBorrador").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public String eliminarBorrador(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("eliminarBorrador");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public List<Borrador> getBorradores(String id) {
        BorradorAluJerseyClient cliente = new BorradorAluJerseyClient();
        GenericType<List<Borrador>> gType = new GenericType<List<Borrador>>() {
        };
        List<Borrador> lista = (List<Borrador>) cliente.BorradoresUnAlumno(gType, id);
        cliente.close();
        return lista;
    }

    public <T> T BorradoresUnAlumno(GenericType<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("BorradoresUnAlumno");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T BorradoresUnAlumno(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("BorradoresUnAlumno");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T buscarBorrador(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (id != null) {
            resource = resource.queryParam("id", id);
        }
        resource = resource.path("buscarBorrador");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }

}
