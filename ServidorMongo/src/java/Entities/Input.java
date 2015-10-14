/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
public class Input implements EntityMongo {

    public String type;
    public ArrayList<String> value;
    public int opcion;

    public Input() {

    }

    public Input(Document object) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        type = object.getString("type");
        value = object.get("value", ArrayList.class);
    }

    /**
     * Metodo que convierte un objeto input a un objeto JSON
     *
     * @return
     */
    @Override
    public Document converADocument() {
        //creamos el objeto que vamos a devolver
        Document res = new Document();
        //añadimos los campos       
        res.append("type", type);
        res.append("value", value);

        //devolvemos el objeto
        return res;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.type);
        hash = 23 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Input other = (Input) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return Objects.equals(this.value, other.value);
    }
}
