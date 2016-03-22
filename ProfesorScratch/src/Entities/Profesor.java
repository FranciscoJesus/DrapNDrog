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
public class Profesor {

    public String id;
    public String nombre;
    public String apellidos;
    public String idUsuario;
    public List<String> asignaturas = new ArrayList<>();

    public Profesor() {
    }

}
