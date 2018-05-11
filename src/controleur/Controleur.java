package controleur;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import vue.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import modele.*;

/**
 * Controleur abstrait
 * Reçoit les évènement d'une vue et met à jour le modèle
 */
public  abstract class Controleur implements ActionListener, ChangeListener,WindowListener{
    protected FeuilleDessin feuille;
    protected Modele m_modele;
    public final int maxRefreshRate=60 ;
    private int refreshRate=60;
    private Timer updateTimer;
    protected Window window;

    /**
     * Lance la tâche de rafraichissement du modèle
     */
    public void start() {
        //System.out.println("rate : "+refreshRate);
        start(1000/refreshRate);
    }

    /**
     * Lance la tâche de rafraichissement du modèle à la vitesse donnée en paramètre
     * @param rate mise à jour par secondes
     */
    public void start(int rate){
        updateTimer=new Timer();
        TimerTask updater=new Updater();
        this.refreshRate=rate;
        updateTimer.schedule(updater, 0, rate);
    }

    /**
     * Quitte
     */
    protected void quitter() {
        System.exit(0);
    }

    /**
     * Met en pause le rafraichissement
     */
    public void pause(){
        updateTimer.cancel();
    }


    /**
     * Traitement du slider de la vitesse d'exécution.
     * Réception de l'évènelment et mise a jour de la vitesse de rafraichissement
     * @param evt
     */
    @Override
    public void stateChanged(ChangeEvent evt) {
       int fps=((JSlider)evt.getSource()).getValue();
           pause();

        if(fps!=0 )
            start((int)(Math.round(1000.0/(fps))));
        else
            pause();
    }

    /**
     * TimerTask permettant la mise à jour du modèle
     */
    class Updater extends TimerTask {
        @Override
        public void run(){
                m_modele.update();
        }
    }

    public long getRefreshRate(){return  refreshRate;}

    //Methodes d'interfaces
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Arrêt du rafraichissement si fermeture fenètre
     * @param e
     */
    @Override
    public void windowClosing(WindowEvent e){
        //System.out.println.println("closing'");
        this.pause();
    }

    /**
     * Arrêt du rafraichissement si fermeture fenètre
     * @param e
     */
    @Override
    public void windowClosed(WindowEvent e) {
        //System.out.println.println("closed'");
        this.pause();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
