package modele;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Léo on 15/06/2014.
 */
public abstract class TortueAmelioree extends Entity {
    final protected static String[] c_noms={"Luigi","Wario","Robert","Antonio","Mustafa","Alfred","Roger","Ernandez","Bjorn","Lemmy","Iggy","Ozzy"};
    protected static LinkedList<String> nomDejaDonnes=new LinkedList<String>();
    protected String m_nom=c_noms[0];
    protected int radius=15;
    protected Random rnd;
    protected TortueBalle m_balle;
    protected int m_speed;
    private int m_margin=5;

    public void setRadius(int radius) {
        this.radius = radius;
    }




    public boolean isOutside(){
        return this.getX() < m_margin || getX() > m_modele.getWidth() - 5
                || getY() < m_margin || getY() > m_modele.getHeight() - 5;
    }


    public void updateCollisions(){
        TortueAmelioree t2;
        double radiusesSum,d;
        try {
            for (int j = 1; j < m_modele.get_tortues().size(); j++) {
                t2 =m_modele.get_tortues().get(j);
                if (t2 != this) {
                    radiusesSum = t2.getRadius() + getRadius();
                    d = t2.getDistance(this);
                     if (d < radiusesSum) {

                        double dir = getDir();
                        pointAt(t2);
                        avancer((int) Math.round(d - radiusesSum)/2);
                        setDir(dir);
                        dir = t2.getDir();
                        t2.pointAt(this);
                        t2.avancer((int) Math.round(d - radiusesSum) / 2);
                        t2.setDir(dir);
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public TortueAmelioree(Modele m){

        super(m);
        rnd=new Random();
        String nom;
        if(nomDejaDonnes.size()<c_noms.length-1) {
            do {

                nom = c_noms[(int) (rnd.nextDouble() * (c_noms.length ))];
            }
            while (nomDejaDonnes.contains(nom));
            this.m_nom = nom;
            //System.out.println(nom);
            nomDejaDonnes.add(m_nom);
        }
        else{
            nom = c_noms[(int)( rnd.nextDouble() * (c_noms.length ))];
            int nbExisting=1;
            for(TortueAmelioree t : m_modele.get_tortues()){
                if(t.getNom().startsWith(nom)){
                    nbExisting++;
                }
            }
            m_nom=nom+" "+nbExisting;
        }




    }

    public int getRadius() {
        return radius;
    }


    public void setBalle(TortueBalle tb){
        this.m_balle=tb;
        if(m_balle!=null)
            m_balle.setDir(this.getDir());
    }

    public String getNom(){
        return m_nom;
    }

    public int getSpeed(){
        return m_speed;
    }


    public void setSpeed(int speed){
        m_speed=speed;
    }
}
