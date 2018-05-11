package vue.foot;

import controleur.Controleur;
import vue.Window;

import javax.swing.*;

/**
 * Created by Léo on 16/06/2014.
 */
public class WindowFoot extends Window
{

    public WindowFoot(Controleur al){
        super("Tortoise Football",al);
        addWindowListener(al);

    }

    public void  init(){
        super.init();
        JToolBar toolBar = new JToolBar();
        initSpeedBar(toolBar);
        toolBar.add(Box.createRigidArea(HGAP));
        getContentPane().add(toolBar, "North");
        addButton(toolBar, "Réinitialiser", "Redémarre le match depuis le début",null);
        JCheckBox chkSuivre=new JCheckBox("Suivre la balle :");
        chkSuivre.setActionCommand("Suivre");
        chkSuivre.addActionListener(m_al);
        toolBar.add(chkSuivre);
    }


}
