package modele;
// package logo;

import modele.party.ModeleParty;
import utils.Utils;
import java.awt.*;

import java.util.*;


/**
 * Représente un objet mouvant dans l'espace : balle, tortue...
 * Coordonnées X,Y et direction
 * fonctions de mouvements et virages
**/

public abstract class Entity
{
    public final Object sync=new Object();
	// Attributs statiques	
	public static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
	public static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
	
	// Attributs
	private ArrayList<Segment> listSegments; // Trace de la tortue
	
	protected int x, y;	// Coordonnees de la tortue
	
	protected double dir;	// Direction de la tortue (angle en degres)

    protected double angleToTurn;
    protected double angleUpdate=0;

	protected boolean crayon; // par defaut on suppose qu'on dessine
	protected int coul;

	protected Modele m_modele;


    private Polygon m_polygon;


    /**
     * Entity crée en (x,y) avec pour modèle m
     * @param m
     * @param x
     * @param y
     */
    public Entity(Modele m, int x, int y){
        this(m);
        setPosition(x,y);
    }


    public Entity(Modele m) {
        this.m_modele=m;
        listSegments = new ArrayList<Segment>();
        reset();
    }

    /**
     * on initialise la position de la tortue
     */
	public void reset() {
		x = 0;
		y = 0;
		dir = -90;
		coul = 0;
		crayon = true;
		listSegments.clear();
  	}

    /**
     * Positionne l'entité à la position indiquée
     * @param newX
     * @param newY
     */
	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
	}


    /**
     * distance avec une autre entité
     * @param t autre entité
     * @return
     */
	public double getDistance(Entity t){
       return getDistance(t.getX(),t.getY());
    }

    /**
     * distance par rapport à un point
     * @param x coordonées x du point
     * @param y coordonées y du point
     * @return
     */
    public double getDistance(int x, int y){
        int dx=x-this.getX();
        int dy=y-this.getY();
        return Math.sqrt(dx*dx+dy*dy);
    }

    /**
     * tourne la tortue vers l'entité en paramètre
     * @param e
     */
    public void pointAt(Entity e){
        pointAt(e.getX(),e.getY());
    }

    /**
     * tourne la tortue vers un point
     * @param x
     * @param y
     */
    public void pointAt(int x, int y){
        int dx=x-this.x;
        int dy=y-this.y;
        if(dx!=0)
             dir=Math.atan(((double)(dy))/((dx)))/ratioDegRad;
        else if(dy>0){
            dir=0;
        }
        else if (dy<0)
           dir=180;
        if(dx<0) {
            dir=(dir+180)%360;
        }

    }

    /**
     * Retourne l'angle de rotation nécéssaire à l'entité pour se retrouver en face du point en paramètre
     * @param x
     * @param y
     * @return
     */
    public double deltaYaw(int x, int y){
        double angle=dir;
        this.pointAt(x,y);
        double r=dir-angle;
        this.dir=angle;
        if(r>180){
            r=r-360;
        }
        else if(r<-180){
            r+=360;
        }

        return r;
    }


    /**
     * L'entité se tourne vers le point en paramètre à la vitesse angulaire en paramètre
     * @param x
     * @param y
     * @param speed vitesse de rotation
     * @return
     */
    public double smoothPointAt(int x, int y, double speed) {
        double dyaw = deltaYaw(x, y);
        if(Math.abs(dyaw)<=speed){
          dir+=dyaw;

        }

        else if(dyaw!=0){
            dir+=speed*(dyaw/Math.abs(dyaw));
        }


        return dyaw;
    }


    /**
     * Fait avancer la tortue de la distance en pramètre
     * Met éventuellement à jour les segmentq
     * @param dist
     */
	public void avancer(int dist) {
        int newX = (int) Math.round(x + dist * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(y + dist * Math.sin(ratioDegRad * dir));

        if (m_modele.getUpdateSegments()) {
            if (crayon) {
                Segment seg = new Segment();

                seg.ptStart.x = x;
                seg.ptStart.y = y;
                seg.ptEnd.x = newX;
                seg.ptEnd.y = newY;
                seg.color = Utils.decodeColor(coul);
                synchronized (this.sync) {
                    listSegments.add(seg);
                    if (listSegments.size() > 20000 / m_modele.get_tortues().size())
                        listSegments.remove(0);
                    }
                }
            }
        x = newX;
        y = newY;
    }

    /**
     * Mise à jour d'un virage
     * @return
     */
    public boolean updateVirage(){
        if(angleUpdate!=0) {
            double delta = angleToTurn - dir;
            double turning = angleUpdate * delta / Math.abs(delta);
            if (turning > 0) {
                if (dir + delta > angleToTurn) {
                    dir = angleToTurn;
                } else
                    dir += turning;
            } else {
                if (dir + delta < angleToTurn) {
                    dir = angleToTurn;
                } else
                    dir += turning;
            }
            if(dir==angleToTurn){
                angleUpdate=0;
            }
            return true;
        }
        return false;
    }

    /**
     * Fait éxécuter un virage d'un angle donnée, en se tournant de angleUpdate à chaque frame
     * @param angleTotal
     * @param angleUpdate
     */
    public void virage(double angleTotal, double angleUpdate){
        angleToTurn=dir+angleTotal;
        this.angleUpdate=angleUpdate;
    }
    public abstract void update();


    public void resetSegments(){
        listSegments=new ArrayList<Segment>();
    }
	public ArrayList<Segment> getListSegments() {
		return listSegments;
	}


    public void setModele(ModeleParty m){
        m_modele=m;
    }
    public void set_polygon(Polygon m_polygon) {
        this.m_polygon = m_polygon;
    }

    public Modele getModele(){
        return m_modele;
    }
    public void setDir(double dir){
        this.dir=dir;
    }

    // Methodes
    public void setColor(int n) {coul = n;}
    public int getColor() {return coul;}

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public double getDir(){
        return dir;
    }


}
