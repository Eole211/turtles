package modele.foot;

import java.util.ArrayList;
import java.awt.Rectangle;
import java.util.Observable;

import modele.Modele;

/**
 * Classe représentant une équipe de Tortues
 */
public class Team extends Observable{
    /**
     * Vitesse minimale des tortues
     */
    protected int m_minSpeed;

    /**
     * Vitesse max des tortues
     */
    protected int m_maxSpeed;

    /**
     * Vitesse de rotation mininmale des tortues
     */
    protected  int m_minRotationSpeed;

    /**
     * Vitesse de rotation maximale des tortues
     */
    protected int m_maxRotationSpeed;

    /**
     * tortues de l'équipe
     */
    ArrayList<TortueAmelioreeFoot> m_tortues=new ArrayList<TortueAmelioreeFoot>();

    /**
     * zone de déploiement
     */
    Rectangle m_zone;

    /**
     * Couleur de l'équipe
     */
    public int m_color;

    /**
     * Nombre de tortue
     */
    public int m_nbTortues;

    /**
     * Formation de départ possibles
     */
    static enum Formation{CIRCLE}

    /**
     * Formation de départ
     */
    private Formation m_formation;

    /**
     * modèle
     */
    private Modele m_modele;

    /**
     * Equipe adverse
     */

    //Dimensions cage adverse
    public Team m_opponents;
    private int m_oppoGoalX;
    private int m_oppoGoalY;
    protected boolean m_goalSide;

    private int m_points=0;

    /**
     * Nom
     */
    private String m_name;


    //0 pour gauche, 1 pour droite

    /**
     * IConstructeur : initialisation taille des cages et autres paramètres.
     * @param name Nom de l'équipe
     * @param m Modèle
     * @param zone Zone où commence l'équipe
     * @param color Couleur des tortues
     */
    public  Team(String name, Modele m, Rectangle zone,int color){
        this.m_name=name;
        m_zone=zone;
        m_color=color;
        m_modele=m;
        m_nbTortues=m.getNbTortues()/2;
        m_formation=Formation.CIRCLE;
        m_oppoGoalY =(int)Math.round(((double)m_modele.getHeight())/2);
       if(zone.x>m_modele.getWidth()/2){
           m_oppoGoalX =0;
           m_goalSide=true;
       }
       else{
           m_oppoGoalX =m_modele.getWidth();
           m_goalSide=false;
       }

    }


    /**
     * Initialise la formation en fonction de m_formation
     */
    public void initFormation(){
                for(int i=0;i<m_nbTortues;i++){
                    TortueAmelioreeFoot t = new TortueAmelioreeFoot(m_modele,this);
                    m_tortues.add(t);
                    t.setColor(this.getColor());

                }
        reinitFormation();
    }

    /**
     * retour à la forpmation de départ
     */
    public void reinitFormation(){
        int centerX= (int)m_zone.getCenterX();
        int centerY= (int)m_zone.getCenterY();
        int radius=(int)Math.min(m_zone.getWidth(),m_zone.getHeight())/2;
        double angleRatio=2*Math.PI/m_nbTortues;
        switch(m_formation){
            case CIRCLE:
                for(int i=0;i<m_nbTortues;i++){
                    TortueAmelioreeFoot t=m_tortues.get(i);

                    t.setPosition(centerX+(int)Math.round(radius*Math.cos(i*angleRatio)),
                            centerY+(int)Math.round(radius*Math.sin(i*angleRatio)));
                }
                break;
        }
    }

    public void reInit(){
        reinitFormation();
        for(TortueAmelioreeFoot t:m_tortues){
            t.initParams();
        }
        setChanged();
        notifyObservers();
    }

    public ArrayList<TortueAmelioreeFoot> getTortues(){
        return m_tortues;
    }

    public void setSpeeds(int minSpeed, int maxSpeed){
        m_minSpeed=minSpeed;
        m_maxSpeed=maxSpeed;
    }



    public void setRotationSpeed(int minRotationSpeed, int maxRotationSpeed){
       m_minRotationSpeed=minRotationSpeed;
        m_maxRotationSpeed=maxRotationSpeed;
    }

    public int getMinSpeed(){
        return m_minSpeed;
    }

    public int getMaxSpeed(){
        return m_maxSpeed;
    }



    public int getColor(){
        return m_color;
    }

    public void setOpponents(Team t){
        m_opponents=t;
    }

    public int getOppoGoalX(){
        return m_oppoGoalX;
    }

    public int getMinRotationSpeed(){
        return m_minRotationSpeed;
    }

    public  int getMaxRotationSpeed(){
        return m_maxRotationSpeed;
    }

    public int getOppoGoalY(){
        return m_oppoGoalY;
    }
    public boolean getOppoGoalSide(){
        return m_goalSide;
    }


    public String getName(){
        return m_name;
    }

    public int getPoints() {
        return m_points;
    }
    public void incrementPoints(){
        m_points++;
        setChanged();
        notifyObservers();
    }

    public void reInitializePoints(){
        m_points=0;
        setChanged();
        notifyObservers();
    }


}
