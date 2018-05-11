package vue.foot;

import modele.foot.ModeleFoot;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Léo on 17/06/2014.
 */
public class Scores extends JFrame implements Observer {
    private JPanel Score;
    private JLabel labelTeam1Name;
    private JLabel labelTeam2Name;
    private JLabel labelTeam1Score;
    private JLabel labelTeam2Score;
    private ModeleFoot m_modele;

    public Scores(ModeleFoot m, JFrame mainWindow)
    {
        super("Scores");
        this.setContentPane(this.Score);
        this.m_modele=m;

        this.pack();
        this.setVisible(true);
        this.setLocation(mainWindow.getWidth()/2-this.getWidth()/2,mainWindow.getHeight());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labelTeam1Name.setText("Equipe "+ m_modele.getTeam1().getName());
        labelTeam2Name.setText("Equipe "+ m_modele.getTeam2().getName());
        m_modele.getTeam1().addObserver(this);
        m_modele.getTeam2().addObserver(this);

    }

    public Scores(){

    }





    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void update(Observable o, Object arg) {
        labelTeam1Score.setText(""+m_modele.getTeam1().getPoints()+"");
        labelTeam2Score.setText(""+m_modele.getTeam2().getPoints()+"");
    }
}
