package modele.party;

import modele.*;

import java.util.ArrayList;

/**
 * Tortue du mode party
 */
public  class TortueAmelioreeParty extends TortueAmelioree {
    final double c_distDetection=15;

    private ArrayList<TortueAmelioree> m_tortuesConnues=new ArrayList<TortueAmelioree>();
    //private String somethingToSay="";

    public TortueAmelioreeParty(ModeleParty m) {
        super(m);
        this.coul=rnd.nextInt(11)+1;
        m_speed = 3;
    }

    /**
     * Mise à jour de la tortue
     * Avance et prend des virages aléatoirement*
     * Application de collisions
     */
    @Override
        public void update() {
            double rndVal=rnd.nextDouble();



            this.avancer(this.m_speed);
             if(rndVal<0.05){

                if(rndVal<0.025) {
                    rndVal = rnd.nextDouble() * 180;
                    this.virage(rndVal,2);                }
                else {
                    rndVal = rnd.nextDouble() * 180;
                    this.virage(-rndVal,2);
                }
            }
            updateVirage();

        updateCollisions();

        //On evite que les tortues sortent de l'écran
        if (this.isOutside()) {
            double sx=rnd.nextDouble();
            double sy=rnd.nextDouble();
            sx=(sx*m_modele.getWidth()*0.6)+0.2*m_modele.getWidth();
            sy=(sy*m_modele.getHeight()*0.6)+0.2*m_modele.getHeight();
            pointAt((int)sx,(int)sy);
            do {

                this.avancer(this.m_speed);

            }
            while(this.isOutside());
        }

        }



        /* Dialogue tortues
        for(Entity t : m_modele.get_tortues()){
            if(t instanceof TortueAmelioree) {
                if (this.getDistance(t) <= c_distDetection) {
                    System.out.println("amelioree avancee");
                    m_modele.getFeuilleDessin().drawTextSpokenByTurtle(this,"Salut " + ((TortueAmelioree)t).getNom() +" !\n Est-ce que tu pourrais avancer un peu ?");
                    m_modele.getFeuilleDessin().repaint();
                }
            }
        }
        */



   // public String getSomethingToSay(){
      //  return somethingToSay;
    //}
/*
    public void addTortueConnue(TortueAmelioreeParty tortue){
        if(!tortue.m_tortuesConnues.contains(this))
            tortue.m_tortuesConnues.add(this);
        if(!m_tortuesConnues.contains(tortue)) {
            m_tortuesConnues.add(tortue);
        }
    }
*/





}
