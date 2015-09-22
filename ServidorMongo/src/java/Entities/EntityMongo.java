/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
public interface EntityMongo {
    
    public Document converADocument();
    
}
