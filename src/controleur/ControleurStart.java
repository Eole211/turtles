package controleur;

import vue.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controleur de la fenêtre de départ ( choix Party/Foot)
 */
public class ControleurStart implements ActionListener {
     Start startView;


    /**
     *  Gestion des actions des boutons  : lancement du Controleur adapté avec le nombre de tortues en paramètre lors d'un appuie.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object s=e.getSource();
        String name;
        if(s.getClass()== JButton.class){
            name=((JButton)s).getName();
            if(name.equals("tortuePartyButton")){
                int nbTortue=startView.getTortuePartyNb();
                if(nbTortue>0) {
                    new ControleurParty(nbTortue);
                    startView.dispose();
                }
            }
            else if (name.equals("matchDeFootButton")){
                int nbTortue=startView.getTortueFootNb();
                if(nbTortue>0) {
                    new ControleurFoot(nbTortue);
                    startView.dispose();
                }
                //System.out.println(name);
            }
        }
    }

    public void setVue(Start startView){
        this.startView=startView;
    }
}
