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
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
@Entity("Usuarios")
public class Usuario{

    @Id
    public String id = new ObjectId().toString();
    public String nombreUsuario;
    public String password;
    public int rol;

    public Usuario() {

    }   
  
    /**
     * Método que se utiliza para la encriptación del campos "password"
     *
     * @return
     */
    public String Encriptar() {
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
     * Método que sirve para pasar los byte de la password ya encriptada a
     * String
     *
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
