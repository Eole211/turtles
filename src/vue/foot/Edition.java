package vue.foot;

import modele.foot.ModeleFoot;
import modele.foot.TortueAmelioreeFoot;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Léo on 17/06/2014.
 */
public class Edition extends JFrame implements Observer{
    private JPanel EditionPannel;
    private JSpinner spinnerVitesse;
    private JLabel labelNomJoueur;
    private JSpinner spinnerRotationSpeed;
    private JSpinner spinnerDistCatch;
    private JSpinner spinnerPasseDist;
    private ModeleFoot m_modele;



    public Edition(ModeleFoot m, ChangeListener al, JFrame mainWindow){
        super("Edition des joueurs");
        this.setContentPane(this.EditionPannel);
        spinnerVitesse.addChangeListener(al);
        spinnerRotationSpeed.addChangeListener(al);
        spinnerDistCatch.addChangeListener(al);
        spinnerPasseDist.addChangeListener(al);
        this.m_modele=m;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(mainWindow.getWidth(),0);
        this.pack();
        this.setVisible(true);
    }


    @Override
    public void update(Observable o, Object arg) {
        TortueAmelioreeFoot courante=(TortueAmelioreeFoot)m_modele.getCourante();
        if(courante!=null) {
            labelNomJoueur.setText("Joueur : " + courante.getNom());
            spinnerVitesse.setValue(courante.getSpeed());
            spinnerRotationSpeed.setValue((courante.getRotationSpeed()));
            spinnerDistCatch.setValue(courante.getCaract("catchDist"));
            spinnerPasseDist.setValue(courante.getCaract("passeDist"));
        }

    }

    public Edition(){

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
