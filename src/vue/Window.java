package vue;

import controleur.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Fenètre principale abstraite
 */
public class Window extends JFrame {
    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);
    protected Controleur m_al;
    JSlider speedBar;

    public Window(String title, Controleur al){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_al=al;
    }

    /**
     * Barre de vitesse d'éxécution
     * @param component
     */
    public  void initSpeedBar(JComponent component){
        speedBar=new JSlider(JSlider.HORIZONTAL,0,m_al.maxRefreshRate,(int)m_al.getRefreshRate());
        speedBar.addChangeListener(m_al);
        speedBar.createStandardLabels(1);
        speedBar.setMinorTickSpacing(1);
        speedBar.setMajorTickSpacing(10);
        speedBar.setPaintTicks(true);
        speedBar.setPaintLabels(true);
        component.add(new JLabel("Vitesse : "));
        component.add(speedBar);
    }

    protected void init(){
        getContentPane().setLayout(new BorderLayout(10,10));
    }

    /**
     *  utilitaires pour installer des boutons et des menus
     */
    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton)p.add(new JButton(name));
        }
        else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon (u);
                b = (JButton) p.add(new JButton(im));
            }
            else
                b = (JButton) p.add(new JButton(name));
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0,0,0,0));
        b.addActionListener(m_al);
    }

    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(m_al);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE)
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            else
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
        }
    }
}
