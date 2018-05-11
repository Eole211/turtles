package controleur;

import modele.foot.ModeleFoot;
import modele.foot.TortueAmelioreeFoot;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Controleur de la fenètre d'édition
 */
public class ControleurEdition implements ChangeListener
{
    ModeleFoot m_modele;

    public ControleurEdition(ModeleFoot m){
       m_modele=m;
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        Object s = e.getSource();
        TortueAmelioreeFoot courante = ((TortueAmelioreeFoot) m_modele.getCourante());
        String name;
        if (s.getClass() == JSpinner.class) {
            JSpinner spin = (JSpinner) s;
            name = spin.getName();
            if (name.equals("spinnerVitesse")) {
                if(courante!=null) {
                    if (((int) spin.getValue()) >= 0) {
                        courante.setSpeed((int) spin.getValue());
                    } else {
                        spin.setValue(0);
                    }
                }
            }
            else if(name.equals("spinnerRotationSpeed")){
                if(courante!=null) {
                    if (Double.parseDouble(spin.getValue().toString()) >= 0) {
                        courante.setRotationSpeed(Double.parseDouble((String)spin.getValue().toString()));
                    } else {
                        spin.setValue(0);
                    }
                }
            }
            else if(name.equals("spinnerDistCatch")){
                if(courante!=null) {
                    if (((int) spin.getValue()) >= 0) {
                        courante.setCaract("catchDist",(int)spin.getValue());
                    } else {
                        spin.setValue(0);
                    }
                }
            }

            else if(name.equals("spinnerPasseDist")){
                if(courante!=null) {
                    if (((int) spin.getValue()) >= 0) {
                        courante.setCaract("passeDist",(int)spin.getValue());
                    } else {
                        spin.setValue(0);
                    }
                }
            }
        }
    }
}
