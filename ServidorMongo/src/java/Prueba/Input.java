/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
class Input {
    
    public String type;
    public ArrayList<String> value;
    
    public Input(){
        
    }
    
    /**
     * Metodo que convierte un objeto input a un objeto JSON
     * @return 
     */
    public Document converADocument(){
        //creamos el objeto que vamos a devolver
        Document res = new Document();
        //añadimos los campos       
        res.append("type", type);
        res.append("value", value);       
        
        //devolvemos el objeto
        return res;
    }
}
