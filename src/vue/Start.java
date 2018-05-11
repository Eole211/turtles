package vue;

import javax.swing.*;

import controleur.*;

/**
 * Created by Léo on 03/06/2014.
 */
public class Start extends JFrame{
    private JPanel Start;
    private JButton tortuePartyButton;
    private JTextField nbTortuesField;
    private JFrame frame;


    public Start(ControleurStart controleur) {
        super("Start");
        this.setContentPane(this.Start);
        tortuePartyButton.addActionListener(controleur);
        matchDeFootButton.addActionListener(controleur);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }




    public int getTortuePartyNb(){
        try{
            return Math.abs(Integer.parseInt(nbTortuesField.getText()));
        }
        catch (Exception e){
            return -1;
        }
    }

    public int getTortueFootNb(){
        try{
            return Math.abs(Integer.parseInt(tortuesEquipeField.getText()));
        }
        catch (Exception e){
            return -1;
        }
    }


    private JButton matchDeFootButton;
    private JTextField tortuesEquipeField;
    public Start(){

    }




    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
