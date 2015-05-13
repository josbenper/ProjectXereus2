/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

/**
 *
 * @author Benavent
 */
public class Archivo {
    private String directorio;
    private String nombre;
    private int votos;

    public Archivo() {
    }

    public Archivo(String directorio, String nombre, int votos) {
        this.directorio = directorio;
        this.nombre = nombre;
        this.votos = votos;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
    
    
}
