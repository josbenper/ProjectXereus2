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
public class ListaArchivos{
    private ArrayList<Archivo> archivos;

    public ListaArchivos() {
        archivos = new ArrayList<Archivo>();
    }

    
    public ArrayList<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(Archivo cancion) {
        this.archivos = archivos;
    }

    void clearCanciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Archivo maxPoints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     
}
