package modele.foot;

import modele.Modele;
import modele.TortueAmelioree;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * Modèle de la partie de Foot
 */
public class ModeleFoot extends Modele  {
    private TortueAmelioree courante;
    private CouranteObservable couranteObservable;
    private Team m_team1;
    private Team m_team2;
    private Rectangle m_zone1;
    private Rectangle m_zone2;
    private int m_offsetMiddle=30;
    private int m_offsetLeftRight=30;
    private int m_offsetTopBot=100;
    private int m_nbTortues;
    private boolean suivreBalle=false;
    private int m_goalHalfSize =55;
    private int m_goalThickness =10;
    private HashMap<String,Caracteristique> caracts=new HashMap<>();



    /**
     * Constructeur : initialisation des équipes et de leurs paramètres
     * @param tortuePerTeam nombre de tortues par équipe
     */
    public ModeleFoot( int tortuePerTeam){
        initCaracts();
        m_nbTortues=2*tortuePerTeam;
        m_width=750;
        m_height=524;
        m_balle=new BalleFoot(this, m_width /2, m_height /2);
        initZones();
        m_team1=new Team("Orange",this,m_zone1,8);
        m_team1.setSpeeds(2,4);
        //System.out.println("team1 max speed :"+m_team1.getMaxSpeed());
        m_team1.setRotationSpeed(6,12);
        m_team2=new Team("Rose",this,m_zone2,10);
        m_team2.setSpeeds(2,4);
        m_team2.setRotationSpeed(6,17);
        m_team1.setOpponents(m_team2);
        m_team2.setOpponents(m_team1);
        m_team1.initFormation();
        m_team2.initFormation();
       couranteObservable=new CouranteObservable();
    }

    public void initCaracts(){
        caracts.put("catchDist", new Caracteristique(25, 35));
        caracts.put("passeDist", new Caracteristique(35,42,35,38));
    }

    public void addObserverToCourante(Observer o){
        couranteObservable.addObserver(o);
    }

    /**
     * Notify la vue d'edition d'une mise  jour des caractéristiques
     */
    public void notifyOberversOfCourante(){
        setCourante((TortueAmelioreeFoot)courante);
    }

    public HashMap<String, Caracteristique> getCaracts() {
        return caracts;
    }

    public boolean isSuivreBalle() {
        return suivreBalle;
    }

    public void setSuivreBalle(boolean suivreBalle) {
        this.suivreBalle = suivreBalle;
        if(suivreBalle)
            this.courante=m_balle.getProprio();
    }

    public class CouranteObservable extends Observable{
        public void setChanged(){
            super.setChanged();
        }
    }

    /**
     * Redémarrage du match depuis le début
     * les caractéristiques des tortues sont conservées
     */
    public void reInitialize(){
        m_team1.reInit();
        m_team2.reInit();
        m_team1.reInitializePoints();
        m_team2.reInitializePoints();
        ((BalleFoot)m_balle).reInit();
        notifyOberversOfCourante();
    }

    /**
     * Met à jour les buts et remet les équie en position si nécéssaire
     */
    void updateGoals(){
        if(Math.abs(m_balle.getY()-m_team1.getOppoGoalY())<m_goalHalfSize) {
            if (Math.abs(m_balle.getX() - m_team1.getOppoGoalX()) < m_goalHalfSize) {
                m_team1.incrementPoints();
                ((BalleFoot)m_balle).reInit();
                m_team1.reinitFormation();
                m_team2.reinitFormation();
            } else if (Math.abs(m_balle.getX() - m_team2.getOppoGoalX()) < m_goalHalfSize) {
                m_team2.incrementPoints();
                ((BalleFoot)m_balle).reInit();
                m_team1.reinitFormation();
                m_team2.reinitFormation();
            }
        }
    }

    /**
     *  Initialisation des zones où chaque équipe commence la partie
     */
    public void initZones(){
        m_zone1=new Rectangle(m_offsetLeftRight,m_offsetTopBot,
                                m_width/2-m_offsetLeftRight-m_offsetMiddle,
                                m_height-2*m_offsetTopBot);
        m_zone2=new Rectangle(m_width/2+m_offsetMiddle,m_offsetTopBot,
                m_width/2-m_offsetLeftRight-m_offsetMiddle,
                m_height-2*m_offsetTopBot);
    }




    /**
     * Mise à jour du modèle et notification des observers
     */
    @Override
    public void update(){
        updateTurtles();
        m_balle.update();
        updateGoals();
        setChanged();
        notifyObservers();
    }


    /**
     * Mise à jour des tortues
     */
    public void updateTurtles(){
        for(TortueAmelioree t : m_team1.getTortues()){
            t.update();
        }
        for(TortueAmelioree t : m_team2.getTortues()){
            t.update();
        }
        
    }

    public void setCourante(TortueAmelioreeFoot t){
        courante=t;
        if(courante!=null)
            //System.out.println.println("courante changée :\n"+getCourante().getNom());
        couranteObservable.setChanged();
        couranteObservable.notifyObservers();
    }

    public TortueAmelioree getCourante(){
        return courante;
    }

    @Override
    public ArrayList<TortueAmelioree>  get_tortues(){
        ArrayList<TortueAmelioree> total=new ArrayList<TortueAmelioree>(m_team1.getTortues());
        if(m_team2!=null)
             total.addAll(m_team2.getTortues());
        return  total;
    }
    @Override
    public int getNbTortues(){
        return m_nbTortues;
    }

    public Team  getTeam1(){
        return m_team1;
    }

    public Team getTeam2(){
        return m_team2;
    }


}
