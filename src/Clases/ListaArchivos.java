/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import XML.LeerXML;
import java.util.ArrayList;

/**
 *
 * @author Benavent
 */
public class ListaArchivos{
    private ArrayList<Archivo> archivos;
    LeerXML leerXml;

    public ListaArchivos() {
        archivos = new ArrayList<Archivo>();
    }

    
    public ArrayList<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(Archivo cancion) {
        this.archivos = archivos;
    }
    
    public Archivo maxPoints(){
        //cargarXml();
       
        Archivo max = archivos.get(0);
       
        
        for (int i = 0; i < archivos.size(); i++) {
            if (max.getVotos()<archivos.get(i).getVotos()) {
                max = archivos.get(i);
            }
        }
        return max;
       
    }

    private void cargarXml() {
       // leerXml.leer();
    }
    
    public void clearCanciones(){
        archivos.clear();
    }
}
