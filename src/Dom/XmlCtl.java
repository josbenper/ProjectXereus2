/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dom;

/**
 *
 * @author lliurex
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XmlCtl {

    private Document m_doc;

    public XmlCtl(String xmlFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        m_doc = builder.parse(new FileInputStream(new File(xmlFile)));
    }
/** Lectura XML **/
    
    public void LeerXml() throws Exception {
       // XmlCtl doc = new XmlCtl("archivos.xml");

        int count = getChildCount("archivos", 0, "Cancion");
        for (int i = 0; i < count; i++) {
            System.out.println("Cancion " + i);
            System.out.println("Directorio\t- " + getChildValue("Cancion", i, "Directorio", 0));
            System.out.println("Nombre\t- " + getChildValue("Cancion", i, "Nombre", 0));
            System.out.println("Votos\t- " + getChildValue("Cancion", i, "Votos", 0));
            System.out.println("Album\t- " + getChildValue("Datos", i, "Album", 0));
            System.out.println("Artista\t- " + getChildValue("Datos", i, "Artista", 0));
            System.out.println("");
        }
    }

    private int getChildCount(String parentTag, int parentIndex, String childTag) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        NodeList childList = parent.getElementsByTagName(childTag);
        return childList.getLength();
    }

    private String getChildValue(String parentTag, int parentIndex, String childTag,
            int childIndex) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        NodeList childList = parent.getElementsByTagName(childTag);
        Element field = (Element) childList.item(childIndex);
        Node child = field.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    private String getChildAttribute(String parentTag, int parentIndex,
            String childTag, int childIndex,
            String attributeTag) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        NodeList childList = parent.getElementsByTagName(childTag);
        Element element = (Element) childList.item(childIndex);
        return element.getAttribute(attributeTag);
    }
    
    /** Creacion XML **/
    
    public void crearXml() throws Exception{
        String sDirectorio = "D:\\Musica\\music";
        File f = new File(sDirectorio);
        
        ArrayList directorio = new ArrayList();
        ArrayList archivo = new ArrayList();
        ArrayList votos = new ArrayList();
        
        
        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (int x = 0; x < ficheros.length; x++) {
                if (ficheros[x].getName().contains(".mp3")) {
                    directorio.add(sDirectorio);
                    archivo.add(ficheros[x].getName());
                    votos.add(0);
                }
            }
        }
        
        
        addChildElement(null, 0, "archivos", null);
        for (int i = 0; i < directorio.size(); i++) {
            addChildElement("archivos", 0, "Cancion", null);
            setAttributeValue("Cancion", 0, "Directorio", "Numero");
            addChildElement("Cancion", 0, "Directorio", directorio.get(i).toString());
            addChildElement("Cancion", 0, "Nombre", archivo.get(i).toString());
            addChildElement("Cancion", 0, "Datos", null);
            addChildElement("Datos", 0, "Album", null);
            addChildElement("Datos", 0, "Artista", null);
            addChildElement("Cancion", 0, "Votos", votos.get(i).toString());
        }
        
            
            
            
            
            setAttributeValue("title", 0, "lang", "en");
            addChildElement("Cancion", 0, "author", "Giada De Laurentis");
            addChildElement("Cancion", 0, "year", "2005");
            addChildElement("Cancion", 0, "price", "30.00");
            addChildElement("bookstore", 0, "book", null);
            setAttributeValue("Cancion", 1, "category", "children");
            addChildElement("Cancion", 1, "title", "Harry Potter and the Half-Blood Prince");
            setAttributeValue("title", 1, "lang", "es");
            addChildElement("Cancion", 1, "author", "J. K. Rowling");
            addChildElement("Cancion", 1, "year", "2005");
            addChildElement("Cancion", 1, "price", "29.99");
            System.out.println(renderXml());
    }
    
    
    private XmlCtl() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        m_doc = builder.newDocument();
    }
 
    private void addChildElement(String parentTag, int parentIndex, String childTag, String childValue) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        Element child = m_doc.createElement(childTag);
        if (childValue != null) {
            child.appendChild(m_doc.createTextNode(childValue));
        }
        if (parent == null) {
            m_doc.appendChild(child);
        }
        else {
            parent.appendChild(child);
        }
    }
 
    private void setAttributeValue(String elementTag, int elementIndex, String attributeTag,
                                  String attributeValue) {
        NodeList list = m_doc.getElementsByTagName(elementTag);
        Element element = (Element) list.item(elementIndex);
        element.setAttribute(attributeTag, attributeValue);
    }
 
    private String renderXml() throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(m_doc), new StreamResult(out));
        return out.toString();
    }
}
