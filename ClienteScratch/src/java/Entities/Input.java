/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author  Javier Ordoñez Martín
 */
@XmlRootElement
public class Input {

    public String type;
    public ArrayList<String> value;
    public int opcion;

    public Input() {

    }
    
    public String generarJSON(){
        
        String json = "{\"type\":\""+ type +"\",\"value\":";
        
        if("select".equals(type)) json += "[";
        
        for(String s : value){
            json += "\"" + s + "\"";
            if( (value.size()-value.indexOf(s))-1 > 0) 
                json += ",";
        }
        
        if("select".equals(type)) json += "] , \"opcion\":" + this.opcion ; 
        json += "}";
        
        return json;
    }
    
}
