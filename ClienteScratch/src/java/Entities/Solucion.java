/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Javier Ordoñez Martín
 */
public class Solucion {

    public String id;
    public String nota;
    public String idAlumno;
    public String idProblema;
    public String nombre;
    public String apellidos;
    
    public ArrayList<Pieza> piezas = new ArrayList<>();

    public Solucion() {

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
        
    public String generarJSONSolucion(){
        return generarJSON(this.piezas);
    }
    
}
