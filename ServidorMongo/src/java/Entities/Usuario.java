/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
public class Usuario {
    
    public String id;

    public String usuario;

    public String password;

    public int rol;

    public Usuario() {

    }

    public Document converADocument() {
        Document res = new Document();

        res.append("id", id);
        res.append("usuario", usuario);
        res.append("password", Encriptar());
        res.append("rol", rol);

        return res;
    }

    /**
     * Método que se utiliza para la encriptación del campos "password"
     * @return 
     */
    private String Encriptar() {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }
        return sha1;
    }

    /**
     * Método que sirve para pasar los byte de la password ya encriptada
     * a String
     * @param hash
     * @return 
     */
    private static String byteToHex(final byte[] hash) {
        String result;
        try (Formatter formatter = new Formatter()) {
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            result = formatter.toString();
        }
        return result;
    }
}
