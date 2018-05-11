package vue.party;
import modele.*;
import modele.party.*;
import vue.*;
// package logo;


import java.awt.*;


import java.util.*;

/**
 * Feuille à dessin du mode party : affichage des tortues à l'écran
 */
public class FeuilleDessinParty extends FeuilleDessin implements Observer  {


    public FeuilleDessinParty(ModeleParty modele, WindowParty window) {
           super(modele, window);
    }


    /**
     * Affichage du fond et des tortues
     * @param g graphics
     */
@Override
    public void paintComponent(Graphics g) {
         m_graphics=g;
        super.paintComponents(g);
        fillBackground(g);
        drawEntities(g);
    }

    /**
     * Mise à jour du modèle => on rafraichit l'affichage
     * @param observable
     * @param o
     */
@Override
    public void update(Observable observable, Object o) {
        this.repaint();;
    }

    /*
    public void drawTextSpokenByTurtle( Entity tortue, String text){
        m_graphics.setColor(Color.black);
        m_graphics.drawString(text, tortue.getX(), tortue.getY()-15);
    }
*/
/*
    @Override
    public void update(Observable observable, Object o) {
            if(o!= null && o.getClass()==TortueAmelioree.class){
                ((WindowParty)this.mainWindow).getDialogTextArea().append(((TortueAmelioreeParty) o).getSomethingToSay());
                }
        else{
                this.repaint();
            }
    }
    */
}

