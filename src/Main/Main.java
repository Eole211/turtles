package Main;

import vue.*;
import controleur.*;
/**
 * main class : création StartWindow
 */
public class Main {
    public static void main(String[] args) {
        //System.out.println( "Logo demarre!" );
        ControleurStart controleurStart =new ControleurStart();
        Start startView=new Start(controleurStart);
        controleurStart.setVue(startView);

    }
}
