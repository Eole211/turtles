package controleur;

// package logo;


import javax.swing.*;
import modele.*;
import modele.party.ModeleParty;
import vue.party.FeuilleDessinParty;
import vue.party.WindowParty;

import java.awt.event.*;
import java.util.*;

/**
 * Controleur principal du mode party
 */
public class ControleurParty extends Controleur {

    /**
     * Liste des tortues
     */
    private ArrayList<TortueAmelioree> tortues=new ArrayList<TortueAmelioree>();

    /**
     * Constructeur => initialisation avec le nb de tortue en paramètre
     * @param nbTortues nombre de tortues
     */
    public ControleurParty(int nbTortues) {
        //super("un logo tout simple");
        logoInit(nbTortues);
    }


    /**
     * Initialisation des fenètre et du modèle
     * @param nbTortues nombre de tortues
     */
	public void logoInit(int nbTortues) {
		window=new WindowParty(this);
        ((WindowParty)window).init();
        m_modele=new ModeleParty(nbTortues);
		feuille = new FeuilleDessinParty((ModeleParty)m_modele,((WindowParty)window)); //500, 400);
        m_modele.addObserver(feuille);
		window.getContentPane().add(feuille, "Center");
		window.pack();
		window.setVisible(true);
        this.start();
	}


	/** la gestion des actions des boutons */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==((WindowParty)window).getColorList()){
			JComboBox cb = (JComboBox)e.getSource();
			int n = cb.getSelectedIndex();
            ((ModeleParty)m_modele).changeColor(n);
		}

        else {
            String c = e.getActionCommand();
            if (c.equals("Effacer"))
                effacer();
            else if (c.equals("Quitter"))
                quitter();
            else if (c.equals("Ajouter tortue")) {
                TortueAmelioree t =((ModeleParty)m_modele).ajouterTortue();
                this.tortues=m_modele.get_tortues();
            }
        }
        m_modele.update();
		//feuille.repaint();
	}


    /**
     *    efface tout et reinitialise la feuille
      */
	public void effacer() {
        ((ModeleParty)m_modele).eraseSegments();

	}

}


