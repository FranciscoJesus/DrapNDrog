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
 * @author FranciscoJesús
 */
public class Asignatura {

    public String id;
    public String nombre;
    public String idProfesor;
    public List<String> idAlumnos = new ArrayList<>();

    public Asignatura() {

    }

}
