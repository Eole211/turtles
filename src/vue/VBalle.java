package vue;

import modele.TortueBalle;

import java.awt.*;

/**
 * Created by leo on 21/05/14.
 */
public class VBalle{
        public static void drawBalle(Graphics g, TortueBalle balle){
            if (g==null)
                return;
            g.setColor(Color.black);
            g.fillOval(balle.getX()-9,balle.getY()-9,18,18);
            g.setColor(Color.white);
            g.drawOval(balle.getX()-10,balle.getY()-10,20,20);
        }
}
