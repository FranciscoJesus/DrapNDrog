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
public class Pieza {

    public ArrayList<Input> inputs = new ArrayList<>();

    public Pieza() {

    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Pieza other = (Pieza) obj;
        return Objects.equals(this.inputs, other.inputs);
    }

}
