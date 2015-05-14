/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import XML.LeerXML;
import XML.PuntuarXML;
import java.util.Scanner;

/**
 *
 * @author Benavent
 */
public class Menu {
    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        
        LeerXML leerXml = new LeerXML();
        PuntuarXML puntual = new PuntuarXML();
        
        int op;
        
        menu();
        op = t.nextInt();
        System.out.println("");
        
        while (op != 0) {
            switch (op) {
                case 1:
                    /** Menu reproductor **/
                    menuReproductor();
                    op = t.nextInt();
                    System.out.println("");

                    while (op != 0) {
                        switch (op) {
                            case 1:
                                System.out.println("Play");
                                break;
                            case 2:
                                System.out.println("Pause");
                                break;
                            case 3:
                                System.out.println("Stop");
                                break;
                            default:
                                System.err.println("**************************");
                                System.err.println("**** OPCION NO VALIDA ****");
                                System.err.println("**************************");
                                break;
                        }
                        menuReproductor();
                        op = t.nextInt();
                        System.out.println("");
                    }
                    break;
                case 2:
                    /** Menu votacion **/
                    menuVotar();
                    op = t.nextInt();
                    System.out.println("");
        
                    while (op != 0) {
                        switch (op) {
                            case 1:
                                System.out.println("Listado de canciones.");
                                LeerXML.leer();
                                break;
                            case 2:
                                System.out.println("Introducir voto.");
                                PuntuarXML.votar();
                                break;
                            default:
                                System.err.println("**************************");
                                System.err.println("**** OPCION NO VALIDA ****");
                                System.err.println("**************************");
                                break;
                        }
                        menuVotar();
                        op = t.nextInt();
                        System.out.println("");
                    }
                    break;
                default:
                    System.err.println("**************************");
                    System.err.println("**** OPCION NO VALIDA ****");
                    System.err.println("**************************");
                    break;
            }
            menu();
            op = t.nextInt();
            System.out.println("");
        }
        
    }

    private static void menu() {
        System.out.println("\n*********** MENU ***********");
        System.out.println("1- Reproductor.");
        System.out.println("2- Votar.");
        System.out.println("0- Salir.");
        System.out.print("Opcion: ");
    }
    
    private static void menuReproductor() {
        System.out.println("\n\t******** REPRODUCTOR *******");
        System.out.println("\t1- Reproducir.");
        System.out.println("\t2- Pausar.");
        System.out.println("\t3- Stop.");
        System.out.println("\t0-Salir.");
        System.out.print("\tOpcion: ");
    }
    
    private static void menuVotar() {
        System.out.println("\n\t*********** VOTAR **********");
        System.out.println("\t1- Listar canciones.");
        System.out.println("\t2- Introducir voto.");
        System.out.println("\t0- Salir.");
        System.out.print("\tOpcion: ");
    }
}