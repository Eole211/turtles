package controleur;

import modele.*;
import modele.foot.*;
import vue.foot.*;

import java.awt.event.*;

/**
 * Controleur principal de la partie de Foot
 * Implémente MouseListener pour détecter les clicks de souris
 */
public class ControleurFoot extends Controleur implements MouseListener
{
    /**
     * Fenêtre des scores
     */
    Scores windowScores;

    /**
     * Fenètre d'édition des caractéristiques
     */
    Edition windowEdition;

    /**
     * Constructeyr
     * @param nbTortues Nombre totall de tortues
     */
    ControleurFoot(int nbTortues){
        logoInit(nbTortues);
    }

    /**
     * initialisation  des fenètres et du modèle
     * @param nbTortues Nombre total de tortues
     */
    public void logoInit(int nbTortues) {

        //Création fenètre
        window=new WindowFoot(this);
        ((WindowFoot)window).init();

        //Création Modèle
        m_modele=new ModeleFoot(nbTortues);

        //Feuille de dessin
        feuille = new FeuilleDessinFoot(m_modele,window);
        feuille.addMouseListener(this); // Ecoute clicks souris
        window.getContentPane().add(feuille, "Center");

        //La vue observe le modèle
        m_modele.addObserver(feuille);

        //finitions fenètre
        window.pack();
        window.setVisible(true);

        //Fenêtre des scores
        windowScores=new Scores((ModeleFoot)m_modele,window);
        ControleurEdition controleurEdition= new ControleurEdition((ModeleFoot)m_modele); //COntroleur de la fenêtre des scores

        //Fenètre d'édition des caractéristiques
        windowEdition =new Edition((ModeleFoot)m_modele,controleurEdition,window);
        ((ModeleFoot)m_modele).addObserverToCourante(windowEdition); // Contrôleur fenètre d'édition
        this.start();
        //setVisible(true);
    }

    /**
     * Ecoute action du bouton reinitialiser et de la checkbox Suivre
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals("Réinitialiser")) {
            ((ModeleFoot)m_modele).reInitialize();

        }
        else if(c.equals(("Suivre"))){
            ((ModeleFoot)m_modele).setSuivreBalle(!((ModeleFoot)m_modele).isSuivreBalle());
            ((ModeleFoot)m_modele).notifyOberversOfCourante();
        }

    }

    /**
     * Ecoute clicks souris
     * Si on clique sur une tortue, celle-ci devient sélectionnée. Elle est stockée dans m_modele.courante
     * @param e évènement de click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!((ModeleFoot)m_modele).isSuivreBalle()) {
            //System.out.println.println("Mouse clicked !!");
            int mx = e.getX();//-feuille.getX();
            int my = e.getY();//-feuille.getY();
            for (TortueAmelioree t : m_modele.get_tortues()) {
                if (t.getDistance(mx, my) < 20) {//.get_polygon().contains(mx,my)){
                    //System.out.println.println("Tortue selectionnée ?!");
                    ((ModeleFoot) m_modele).setCourante((TortueAmelioreeFoot) t);
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
