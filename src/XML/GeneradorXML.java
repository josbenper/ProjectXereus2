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
import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v1.ID3V1_0Tag;
import org.blinkenlights.jid3.v2.ID3V2_3_0Tag;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


/**
 *
 * @author Benavent
 */
public class GeneradorXML {

    
    public static void generar(){
        String nombre_archivo = "archivos";
        ArrayList directorio = new ArrayList();
        ArrayList archivo = new ArrayList();
        ArrayList votos = new ArrayList();

        String sDirectorio = "D:\\Musica\\music";
        File f = new File(sDirectorio);

        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (int x = 0; x < ficheros.length; x++) {
                if (ficheros[x].getName().contains(".mp3")) {
                    directorio.add(sDirectorio);
                    archivo.add(ficheros[x].getName());
                    votos.add(0);
                }
                //  System.out.println(ficheros[x].getName());
                
               // String fichero = sDirectorio+"/"+ficheros[x].getName();
                //System.out.println(fichero);
               // readTags(sDirectorio+"/"+ficheros[x].getName());
                
               

            }
        }

        try {
            generate(nombre_archivo, directorio, archivo, votos);
        } catch (Exception e) {
        }
    }
    
    private static void generate(String name, ArrayList<String> key, ArrayList<String> value, ArrayList<Integer> votos) throws Exception {

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
                Element itemNode = document.createElement("Cancion");
                Element itemNode2 = document.createElement("Datos");
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
                
                Element albumNode = document.createElement("Album");
                Text nodeAlbumValue = document.createTextNode(readAlbumTag(key.get(i)+"/"+value.get(i)));
                albumNode.appendChild(nodeAlbumValue);
                
                Element artistaNode = document.createElement("Artista");
                Text nodeArtistaValue = document.createTextNode(readArtistaTag(key.get(i)+"/"+value.get(i)));
                artistaNode.appendChild(nodeArtistaValue);
                
                
                //append keyNode and valueNode to itemNode
                itemNode.appendChild(keyNode);
                itemNode.appendChild(valueNode);
                itemNode2.appendChild(albumNode);
                itemNode2.appendChild(artistaNode);
                itemNode.appendChild(itemNode2);
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
    
    private static String readArtistaTag(String file){
        MediaFile mediaFile = new MP3File(new File(file));
        String album = "Null";
        try {
         for (Object obj : mediaFile.getTags()) {
          if (obj instanceof ID3V1_0Tag)
          album = ReadArtistV1(obj);
          else if (obj instanceof ID3V2_3_0Tag)
          album = ReadArtistV2(obj);
         }
        } catch (ID3Exception e1) {
         e1.printStackTrace();
        }
        return album;
    }
    
    private static String readAlbumTag(String file){
        MediaFile mediaFile = new MP3File(new File(file));
        String album = "Null";
        try {
         for (Object obj : mediaFile.getTags()) {
          if (obj instanceof ID3V1_0Tag)
          album = ReadAlbumV1(obj);
          else if (obj instanceof ID3V2_3_0Tag)
          album = ReadAlbumV2(obj);
         }
        } catch (ID3Exception e1) {
         e1.printStackTrace();
        }
        return album;
    }
    
    private static String ReadAlbumV1(Object obj){
        ID3V1_0Tag oID3V1_0Tag = (ID3V1_0Tag) obj;
        /* if (oID3V1_0Tag.getAlbum() != null)
         System.out.println("Album: "+oID3V1_0Tag.getAlbum());
        */
        return oID3V1_0Tag.getAlbum();
    }
    
    private static String ReadArtistV1(Object obj) {
        ID3V1_0Tag oID3V1_0Tag = (ID3V1_0Tag) obj;
       /* if (oID3V1_0Tag.getArtist() != null)
         System.out.println("Artista: "+oID3V1_0Tag.getArtist());
        */
        return oID3V1_0Tag.getArtist();
    }
    
    private static String ReadAlbumV2(Object obj){
        ID3V2_3_0Tag oID3V2_3_0Tag = (ID3V2_3_0Tag) obj;
        /* if (oID3V2_3_0Tag.getAlbum() != null)
         System.out.println("Album: "+oID3V2_3_0Tag.getAlbum());
        */
         return oID3V2_3_0Tag.getAlbum();
    }
    
    private static String ReadArtistV2(Object obj) {
        ID3V2_3_0Tag oID3V2_3_0Tag = (ID3V2_3_0Tag) obj;
       /* if (oID3V2_3_0Tag.getArtist() != null)
         System.out.println("Artista: "+oID3V2_3_0Tag.getArtist());
               */
         return oID3V2_3_0Tag.getArtist();
    }
    

}
