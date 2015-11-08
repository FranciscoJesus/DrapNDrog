/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
public class Input {

    public String type;
    public ArrayList<String> value;
    public int opcion;

    public Input() {

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
