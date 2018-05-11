package vue.party;
import controleur.*;

import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class WindowParty extends vue.Window {
	JComboBox colorList;
	public JTextArea dialogTextArea;
	private JTextField inputValue;


	
	
	public JComboBox getColorList(){
		return colorList;
	}
		
	public WindowParty(Controleur al){
		super("Tortoise party",al);
	}
	
	
	
	
	public void init(){
        super.init();
        // Boutons
		JToolBar toolBar = new JToolBar();
        initSpeedBar(toolBar);


				getContentPane().add(toolBar, "North");

				//getContentPane().add(buttonPanel,"North");
				addButton(toolBar,"Effacer","Nouveau dessin","/icons/New24.gif");

				toolBar.add(Box.createRigidArea(HGAP));


				String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
										 "vert", "gris clair", "magenta", "orange",
										 "gris", "rose", "jaune"};

				// Create the combo box
				toolBar.add(Box.createRigidArea(HGAP));
				JLabel colorLabel = new JLabel("   Couleur: ");
				toolBar.add(colorLabel);
				 colorList = new JComboBox(colorStrings);
				toolBar.add(colorList);

				colorList.addActionListener(m_al);
                addButton(toolBar, "Ajouter tortue", "Ajouter une nouvelle tortue",null);

				// Menus
				JMenuBar menubar=new JMenuBar();
				setJMenuBar(menubar);	// on installe le menu bar
				JMenu menuFile=new JMenu("File"); // on installe le premier menu
				menubar.add(menuFile);

				addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
				addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);




                 JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
				menubar.add(menuHelp);
				addMenuItem(menuHelp, "Aide", "Help", -1);
				addMenuItem(menuHelp, "A propos", "About", -1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//setDefaultCloseOperation(EXIT_ON_CLOSE);

				// les boutons du bas
				//JPanel p2 = new JPanel(new GridLayout());

                dialogTextArea =new JTextArea(5,50);
             JScrollPane scrollPane = new JScrollPane( dialogTextArea);
        DefaultCaret caret = (DefaultCaret)dialogTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        //p2.add(scrollPane);
          //  dialogTextArea.setRows(4);


				getContentPane().add(scrollPane,"South");


				
	}
	
	public String getInputValue(){
		return(inputValue.getText());
	}
	
	



    public JTextArea getDialogTextArea(){
        return dialogTextArea;
    }
}
