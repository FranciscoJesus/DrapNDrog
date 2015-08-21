/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
public class Nombre {
    
    @QueryParam("nombre")
    public String nombre;
    
    public Nombre(){};
    
    public Nombre(String n){
        nombre = n;
    }
}
