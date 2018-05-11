package vue;

import modele.*;
import modele.TortueAmelioree;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Feuille à dessin servant à l'affichage
 */
public abstract class FeuilleDessin extends JPanel  implements Observer {
    private ArrayList<TortueAmelioree> tortues; // la liste des tortues enregistrees
    protected TortueBalle balle;
    protected Graphics m_graphics=this.getGraphics();
    protected Modele m_modele;
    protected Window mainWindow;


    int width, height;

    /**
     * Constructeur : initialisation des paramètres
     * @param modele
     * @param window
     */
    public FeuilleDessin(Modele modele, Window window
    ){
        super();
        this.mainWindow=window;
        this.tortues=modele.get_tortues();
        this.balle=modele.getBalle();
        this.m_modele=modele;
        this.width=m_modele.getWidth();
        this.height=m_modele.getHeight();
        setBackground(Color.white);
        setSize(new Dimension(width,height));
        setPreferredSize(new Dimension(width,height));
    }

    /**
     * Nettoie le fond.
     * @param g
     */
    public void fillBackground(Graphics g){
        Color c = g.getColor();
        Dimension dim = getSize();
        g.fillRect(0,0, dim.width, dim.height);
        g.setColor(c);
    }


    /**
     * Affiche les tortues et la balle
     * @param g
     */
    public void drawEntities(Graphics g){
        VBalle.drawBalle(g, balle);
        VTortue.drawTurtles(tortues,m_modele.getCourante(), g);
    }




}
