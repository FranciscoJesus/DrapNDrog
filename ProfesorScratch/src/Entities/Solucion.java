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
 * @author FranciscoJesús
 */
@XmlRootElement
public class Solucion{

    public String id;
    public String nota;
    public String idAlumno;
    public String idProblema;
    public String nombre;

    public ArrayList<Pieza> piezas = new ArrayList<>();

    public Solucion() {

    }

}
