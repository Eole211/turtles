package modele.foot;

import modele.Modele;
import modele.TortueAmelioree;
import modele.TortueBalle;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Balle match de foot
 */
public class BalleFoot extends TortueBalle {
    private int m_xStart;
    private int m_yStart;
    Team m_team=null;

    /**
     * Met à jour l'équipe possédant la balle
     */
    public void updateTeam(){
        if(m_proprio!=null){
            m_team=((TortueAmelioreeFoot)m_proprio).getTeam();
        }
    }

    /**
     * Constructeur. Crée la balle à la position spécifié.
     * @param m Modèle
     * @param x position sur l'axe X
     * @param y position sur l'axe Y
     */
    public BalleFoot(Modele m, int x, int y){
        super(m,x,y)
        ;
        m_xStart=x;
        m_yStart=y;
    }

    /**
     * retour de la balle à sa position de départ
     * et mise du propriétaire de la balle à null.
     */
    public void reInit(){
        x=m_xStart;
        y=m_yStart;
        m_proprio=null;
        m_newProprio=null;
        for(TortueAmelioree t : m_modele.get_tortues()){
            t.setBalle(null);
        }
        m_team=null;
    }

    /**
     * Bouge la balle vers son nouveau propriétaire.
     * Eventuelle mise à jour de la tortue courante si on suit la balle
     */
    @Override
    public void moveToNewProprio(){
        super.moveToNewProprio();
        if(m_newProprio==null&&  ((ModeleFoot)m_modele).isSuivreBalle()){
            ((ModeleFoot) m_modele).setCourante((TortueAmelioreeFoot) m_proprio);
             ((ModeleFoot) m_modele).notifyOberversOfCourante();
        }
    }

    /**
     * Mise à jour de la balle.
     * Idem que TortueBalle.update() mais avec
     * la prise en compte de la caractéristique distToCatch de la Tortue
     * et la mise à jour de l'équipe détenant la balle.
     */
    @Override
    public void update(){
        //System.out.println.println("goodUpdate !");
        if (System.currentTimeMillis()-m_timeChangement>m_delayChangement &&m_newProprio == null) {
            ArrayList<TortueAmelioree> tortues = m_modele.get_tortues();
            Collections.shuffle(tortues);
            for (TortueAmelioree t :tortues) {
                if(closeFromTortue(t,((TortueAmelioreeFoot)t).getCaract("catchDist"))) {
                    break;
                }
            }
        }
        if(m_newProprio!=null){
            moveToNewProprio();
        }
        if(m_proprio!=null){
            this.x = m_proprio.getX();
            this.y = m_proprio.getY();
        }
        updateTeam();
    }

    public Team getTeam(){
        return m_team;
    }
}
