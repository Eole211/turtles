package vue.foot;

import modele.*;
import modele.foot.ModeleFoot;
import vue.*;
import vue.Window;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Feuille A Dessin de la partie de foot
 * Affichage de tortues, de la balle, d'un cercle de sélection avec une image de terrain de foot en fond.
 */
public class FeuilleDessinFoot extends FeuilleDessin {
    private ArrayList<TortueAmelioree> tortues;
    private Window mainWindow;

    public FeuilleDessinFoot(Modele modele, Window window) {
        super(modele, window);
        VBackground.init("images/stadium.png");
    }

    @Override
    public void update(Observable observable, Object o) {

        this.repaint();
    }

    /**
     * Affichage de la feuille
     * @param g
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        m_graphics=g;
        VBackground.draw(g);
        if(((ModeleFoot)m_modele).isSuivreBalle()){
            VTortue.drawSelectionCircle(m_modele.getBalle(),g);
        }
        else{
            if(m_modele.getCourante()!=null) {
                VTortue.drawSelectionCircle(m_modele.getCourante(), g);
            }
        }
        drawEntities(g);



    }



}
