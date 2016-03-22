/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ServicioUsuario [Usuario]<br>
 * USAGE:
 * <pre>
 *        LoginJerseyClient client = new LoginJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Edgar Pérez Ferrando
 */
public class LoginJerseyClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ServidorMongo/API";

    public LoginJerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Usuario");
    }

    public <T> T LoginProfesor_XML(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("LoginProfesor").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    public <T> T LoginProfesor_JSON(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("LoginProfesor").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T LoginAlumno_XML(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("LoginAlumno").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    public <T> T LoginAlumno_JSON(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("LoginAlumno").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T insertarUsuario_XML(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarUsuario").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), responseType);
    }

    public <T> T insertarUsuario_JSON(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("insertarUsuario").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public void close() {
        client.close();
    }
    
}
