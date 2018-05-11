package modele.party;

import modele.*;
import vue.party.FeuilleDessinParty;

import java.util.*;

/**
 * Modele du mode party
 */
public class ModeleParty extends Modele {

   private ArrayList<TortueAmelioreeParty> m_tortues=new ArrayList<TortueAmelioreeParty>();

    private Object sync=new Object();

   private FeuilleDessinParty m_feuilleDessin;


    public ModeleParty(){
        //TortueAmelioree t=ajouterTortue();
        m_balle=new TortueBalle(this,200,200);
    }

    /**
     * constructeur : Ajoute le nombre de tortues donné en paramètre à des positions aléatoires sur l'écran.
     * @param nbTortues
     */
    public ModeleParty(int nbTortues){
        this();
        for(int i=0;i<nbTortues;i++){
            ajouterTortue();
        }
        updateSegments=true;
    }


    /**
     * Ajout d'une tortue dans la liste
     * @param tortue
     */
    public void ajouterTortue(TortueAmelioreeParty tortue){
        m_tortues.add(tortue);
        tortue.setModele(this);
    }

    /**
     * Notifie le changement de caractéristique d'une tortue à la vue d'édition
     * @param tA
     */
    public void notifyTortue(TortueAmelioree tA){
        this.setChanged();
        notifyObservers(tA);
    }

    /**
     * Suppression de la trace de la tortue
     */
    public void eraseSegments()
    {
        for(TortueAmelioree ta : get_tortues()){
            ta.resetSegments();
        }

    }

    /**
     * Ajoute une tortue à une position aléaotire à l'écran
     * @return
     */
    public TortueAmelioree ajouterTortue(){

        Random rnd=new Random();
        TortueAmelioreeParty tortue = new TortueAmelioreeParty(this);
        /*
        for(TortueAmelioreeParty ta : get_tortuesParty()){
            ta.addTortueConnue(tortue) ;
            tortue.addTortueConnue(ta);
        }
        */
        tortue.setModele(this);
        tortue.setPosition((int)(rnd.nextDouble()*m_width), (int)(rnd.nextDouble()*m_height));

        m_tortues.add(tortue);
        return tortue;
    }

    @Override
    public void update(){

        updateTurtles();
        m_balle.update();
        setChanged();
        notifyObservers();



    }


    /**
     * Change la couleur de toutes les tortues
     * @param color
     */
    public void changeColor(int color){
        for(TortueAmelioree ta : get_tortues()){
            ta.setColor(color);
        }
    }


    /**
     * Mise à jour des tortues
     */
    public void updateTurtles(){
        try{
        for(TortueAmelioree t : m_tortues){
                t.update();
            }

        }
        catch(ConcurrentModificationException e){
                e.printStackTrace();
        }

        long timeCol=System.currentTimeMillis();

    }

    @Override
    public ArrayList<TortueAmelioree> get_tortues() {
        return  (ArrayList<TortueAmelioree>)(ArrayList<?>) m_tortues;
    }

    public ArrayList<TortueAmelioreeParty> get_tortuesParty() {
        return   m_tortues;
    }
    public void set_tortues(ArrayList<TortueAmelioreeParty> m_tortues) {
        this.m_tortues = m_tortues;
    }

    @Override
    public int getNbTortues(){
        return m_tortues.size();
    }

    @Override
    public TortueAmelioree getCourante(){
        return  m_balle.getProprio();
    }
}
