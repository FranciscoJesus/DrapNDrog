/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FranciscoJesús
 */
@XmlRootElement
public class Problema {

    public String id;
    public String idProfesor;
    public String idAsignatura;
    public String nombreAsignatura;
    public String titulo;
    public String enunciado;
    public ArrayList<Pieza> piezas = new ArrayList<>();
    public ArrayList<Pieza> solucion = new ArrayList<>();

    public Problema() {

    }

    public String generarJSON(ArrayList<Pieza> piezas){
        String json = "[";

        for (Pieza pieza : piezas) {

            json += "{\"inputs\": [";
            List<Input> inputs = new ArrayList<Input>();
            inputs = pieza.inputs;
            for (Input tag : inputs) {
                json += tag.generarJSON() + ",";
            }
            json += "]},";
        }

        json = json.substring(0, json.length() - 1);
        json += "]";
                   
        return json;
    }
    
    public String generarJSONPiezas(){
        return generarJSON(this.piezas);
    }
    
    public String generarJSONSolucion(){
        return generarJSON(this.solucion);
    }
    
}
