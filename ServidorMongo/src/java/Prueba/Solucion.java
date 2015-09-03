/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
public class Solucion {
    
    @QueryParam("nombre")
    public String nombre;
    
    @QueryParam("piezas")
    public ArrayList<Pieza> piezas = new ArrayList<>();
    
    public Solucion(){};
    
    public Solucion(String n,ArrayList<Pieza> p){
        nombre = n;
        piezas = p;
    }
}
