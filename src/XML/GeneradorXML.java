package XML;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


/**
 *
 * @author Benavent
 */
public class GeneradorXML {

    public static void main(String[] args) {
        String nombre_archivo = "archivos";
        ArrayList directorio = new ArrayList();
        ArrayList archivo = new ArrayList();
        ArrayList votos = new ArrayList();

        String sDirectorio = "D:\\Musica\\music";
        File f = new File(sDirectorio);

        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (int x = 0; x < ficheros.length; x++) {
                //  System.out.println(ficheros[x].getName());
                directorio.add(sDirectorio);
                archivo.add(ficheros[x].getName());
                votos.add(0);

            }
        }

        try {
            generate(nombre_archivo, directorio, archivo, votos);
        } catch (Exception e) {
        }
    }

    public static void generate(String name, ArrayList<String> key, ArrayList<String> value, ArrayList<Integer> votos) throws Exception {

        if (key.isEmpty() || value.isEmpty() || key.size() != value.size()) {
            System.out.println("ERROR empty ArrayList");
            return;
        } else {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, name, null);
            document.setXmlVersion("1.0");
            
            //Main Node
            Element raiz = document.getDocumentElement();
            //Por cada key creamos un item que contendrá la key y el value
            for (int i = 0; i < key.size(); i++) {
                //Item Node
                Element itemNode = document.createElement("Archivo");
                //Key Node
                Element keyNode = document.createElement("Directorio");
                Text nodeKeyValue = document.createTextNode(key.get(i));
                keyNode.appendChild(nodeKeyValue);
                //Value Node
                Element valueNode = document.createElement("Nombre");
                Text nodeValueValue = document.createTextNode(value.get(i));
                valueNode.appendChild(nodeValueValue);
                //Value Node
                Element votosNode = document.createElement("Votos");
                Text nodeVotosValue = document.createTextNode(votos.get(i).toString());
                votosNode.appendChild(nodeVotosValue);
                //append keyNode and valueNode to itemNode
                itemNode.appendChild(keyNode);
                itemNode.appendChild(valueNode);
                itemNode.appendChild(votosNode);
                //append itemNode to raiz
                raiz.appendChild(itemNode); //pegamos el elemento a la raiz "Documento"
            }
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File(name + ".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
    }
}
