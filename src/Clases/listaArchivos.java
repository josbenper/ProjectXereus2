/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import java.util.ArrayList;

/**
 *
 * @author Benavent
 */
public class listaArchivos{
    private ArrayList<Archivo> archivos;

    public listaArchivos() {
        archivos = new ArrayList<Archivo>();
    }

    
    public ArrayList<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(Archivo cancion) {
        this.archivos = archivos;
    }
     
}
