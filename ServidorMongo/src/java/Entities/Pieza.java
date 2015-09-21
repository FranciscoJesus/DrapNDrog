/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Input;
import com.mongodb.BasicDBList;
import java.util.ArrayList;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
public class Pieza { 
    @QueryParam("tipo")
    public ArrayList<Input> inputs = new ArrayList<>();
    
    public Pieza(){}
    
    /**
     * Método que se encarga de convertir un objeto Pieza en un objeto JSON
     * @return 
     */
    public Document converADocument(){
        
        Document res = new Document();
        BasicDBList input = new BasicDBList();
        for(Input i : inputs){
            input.add(i.converADocument());
        }
        res.append("inputs", input);
                
        return res;
    }
    
}