package modele;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Balle simple
 */
public class TortueBalle extends Entity {
    protected TortueAmelioree m_proprio=null;
    protected int m_speed=4;
    protected TortueAmelioree m_newProprio=null;
    public TortueBalle(Modele m, int x, int y){
        super(m, x,y);
    }
    protected long m_timeChangement=0;
    protected int m_delayChangement=200;
    private final int c_distCatch=32;
    public TortueBalle(Modele m){
        super(m);
    }

    /**
     * Mouvement vers le nouveau propriétaire
     * Acutalisation de m_proprio quand m_newProprio assez près
     */
    public void moveToNewProprio(){
        int speed;
        if(m_speed>m_newProprio.getSpeed()){
            speed=m_speed;
        }
        else{
            speed=(int)Math.ceil(m_newProprio.getSpeed()*1.2);
        }
        if(getDistance(m_newProprio)>speed) {
            pointAt(m_newProprio);
                this.avancer(speed);

        }
        else{
            m_proprio=m_newProprio;
           m_timeChangement=System.currentTimeMillis();
            m_proprio.setBalle(this);
          m_newProprio=null;
        }

    }

    /**
     * Si la tortue est à une distance inférieure à celled onnée en paramètre => suppression proprio => assignation nouveau proprio
     * @param t
     * @param dist
     * @return vrai si m_newProprio assigné
     */
    protected boolean closeFromTortue(TortueAmelioree t, int dist){
        if (t.getDistance(this) <=dist) {
            if (t != m_proprio) {
                // System.out.println("c'est pas le proprio! ");
                if (m_proprio != null) {
                    m_proprio.setBalle(null);
                    m_proprio = null;
                    // System.out.println("ancien proprio balle à null");
                }
                m_newProprio = t;
                return true;
                // t.setBalle(this);
            }
        }
        return false;
    }

    /**
     * Mise à jour de la balle :
     * Elle se déplace vers son nouveau propriétaire si une tortue est assez proche
     *Sinon elle reste à la même position que son proprio
     */
    public void update(){
        if (System.currentTimeMillis()-m_timeChangement>m_delayChangement &&m_newProprio == null) {
            ArrayList<TortueAmelioree> tortues = m_modele.get_tortues();
            Collections.shuffle(tortues);
            for (TortueAmelioree t :tortues) {
               if(closeFromTortue(t,c_distCatch))
                   break;

            }
        }
        if(m_newProprio!=null){
            moveToNewProprio();
        }
        if(m_proprio!=null){
                this.x = m_proprio.getX();
                this.y = m_proprio.getY();
        }
    }

    public void setNewProprio(TortueAmelioree t){
        m_newProprio=t;
        m_proprio=null;
    }

    public TortueAmelioree getProprio(){
        return m_proprio;
    }



    }



