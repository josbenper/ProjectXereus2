/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import Clases.Archivo;
import Clases.ListaArchivos;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Benavent
 */
public class LeerXML {

   
    public static void leer(ListaArchivos listaAr, int codigo) {
  
        Archivo cancion;
        String directorio="", nombre="";
        int votos=0;
        
        try {
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse("archivos.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "Archivo"
            NodeList listaEmpleados = raiz.getElementsByTagName("Archivo");
            //Recorrer la lista de archivos
            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                //Obtener de la lista un empleado tras otro
                Node empleado = listaEmpleados.item(i);
                if (codigo==0) {
                    System.out.println("Mp3 " + i);
                    System.out.println("==========");
                }
              //  pos++;
                //Obtener la lista de los datos que contiene esa cancion
                NodeList datosEmpleado = empleado.getChildNodes();
                //Recorrer la lista de los datos que contiene la cancion
                for (int j = 0; j < datosEmpleado.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosEmpleado.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if (dato.getNodeType() == Node.ELEMENT_NODE) {
                        //Mostrar el nombre del tipo de dato
                        if (codigo==0) {
                            System.out.print(dato.getNodeName() + ": ");
                        }
                        //El valor está contenido en un hijo del nodo Element
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el valor contenido en el nodo que debe ser de tipo Text
                        
                        if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
                            if (codigo==0) {
                               System.out.println(datoContenido.getNodeValue());
                            }
                            
                        }
                        if (codigo==1) {
                            if (dato.getNodeName().toString().equalsIgnoreCase("Directorio")) {
                            directorio=datoContenido.getNodeValue();
                            }
                            if (dato.getNodeName().toString().equalsIgnoreCase("Nombre")) {
                                 nombre=datoContenido.getNodeValue();
                            }
                            if (dato.getNodeName().toString().equalsIgnoreCase("Votos")) {
                                votos=Integer.parseInt(datoContenido.getNodeValue());
                            }

                            cancion = new Archivo(directorio, nombre, votos);
                            listaAr.getArchivos().add(cancion);
                        }
                        
                        
                    }
                }
                //Se deja un salto de línea de separación entre cada mp3
                System.out.println();
            }
            //   System.out.println("pos: "+posMax+"\nvotos: "+max);

        } catch (SAXException ex) {
            System.out.println("ERROR: El formato XML del fichero no es correcto\n" + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Se ha producido un error el leer el fichero\n" + ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
