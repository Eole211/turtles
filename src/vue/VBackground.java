package vue;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
/**
 * Chargement et affichage de l'image de fond
 */
public class VBackground {
    static Image image;

    public static void init(String path){
        try {
            image = ImageIO.read(new File(path));
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void draw(Graphics graphics){
        graphics.drawImage(image,0,0,null);
    }
}
