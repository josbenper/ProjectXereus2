package reproductor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Benavent
 */
public class ReproductorMP3 {

    static boolean pausa = false;

    public static void main(String[] args) throws FileNotFoundException,
            JavaLayerException {

        String sDirectorio = "D:\\Musica\\music";
        File f = new File(sDirectorio);

        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (int x = 0; x < ficheros.length; x++) {
                System.out.println(ficheros[x].getName());
                
                Player apl = new Player(new FileInputStream(
                        sDirectorio+"\\"+ficheros[x].getName()));

                apl.play();
                
            }
        } else {
        }
        /*
         Player apl = new Player(new FileInputStream(
         "D:\\Musica\\Radioactive.mp3"));

         apl.play();
         */
    }

   
}
