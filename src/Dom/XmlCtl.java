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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class XmlCtl {
    private Document m_doc;
    
    public XmlCtl(){
        
    }
 
    public XmlCtl(String xmlfile) throws Exception 
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        m_doc = builder.parse(new FileInputStream(new File(xmlfile)));
    }
 
    public void LeerXml() throws Exception{
        XmlCtl doc = new XmlCtl("archivos.xml");
        
        int count = doc.getChildCount("archivos", 0, "Cancion");
        for (int i = 0; i < count; i++) {
            System.out.println("Cancion "+i);
         //   System.out.println("book category   - "+doc.getChildAttribute("bookstore", 0, "book", i, "category"));
            System.out.println("Directorio\t- "+doc.getChildValue("Cancion", i, "Directorio", 0));
         //   System.out.println("book title lang - "+doc.getChildAttribute("Cancion", i, "title", 0, "lang"));
            System.out.println("Nombre\t- "+doc.getChildValue("Cancion", i, "Nombre", 0));
            System.out.println("Votos\t- "+doc.getChildValue("Cancion", i, "Votos", 0));
            System.out.println("Album\t- "+doc.getChildValue("Datos", i, "Album", 0));
            System.out.println("Artista\t- "+doc.getChildValue("Datos", i, "Artista", 0));
         //  System.out.println("book price      - "+doc.getChildValue("Cancion", i, "price", 0));
            System.out.println("");
        }
    }
    
    public int getChildCount(String parentTag, int parentIndex, String childTag)
    {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        NodeList childList = parent.getElementsByTagName(childTag);
        return childList.getLength();
    }
 
    public String getChildValue(String parentTag, int parentIndex, String childTag,
                                int childIndex)
    {
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
 
    public String getChildAttribute(String parentTag, int parentIndex, 
                                  String childTag, int childIndex,
                                  String attributeTag) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        NodeList childList = parent.getElementsByTagName(childTag);
        Element element = (Element) childList.item(childIndex);
        return element.getAttribute(attributeTag);
    }
}
