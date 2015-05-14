/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Benavent
 */
public class PuntuarXML {

    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        String cancion;
        String filePath = "archivos.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Nombre cancion");
            cancion = t.next();

            updateElementValue(doc, cancion);

            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("archivos.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }
    
    public static void votar(){
        Scanner t = new Scanner(System.in);
        String cancion;
        String filePath = "archivos.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Nombre cancion");
            cancion = t.next();

            updateElementValue(doc, cancion);

            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("archivos.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    private static void updateElementValue(Document doc, String cancion) {
        NodeList employees = doc.getElementsByTagName("Archivo");
        Element emp = null;
        int puntos;
        String puntosSting;
        //loop for each employee
        for (int i = 0; i < employees.getLength(); i++) {

            emp = (Element) employees.item(i);
            Node name = emp.getElementsByTagName("Votos").item(0).getFirstChild();
            Node song = emp.getElementsByTagName("Nombre").item(0).getFirstChild();
            if (song.getNodeValue().toString().equalsIgnoreCase(cancion + ".mp3")) {
                puntos = Integer.parseInt(name.getNodeValue());
                puntos++;
                puntosSting = Integer.toString(puntos);
                name.setNodeValue(puntosSting);
            }

        }
    }
}
