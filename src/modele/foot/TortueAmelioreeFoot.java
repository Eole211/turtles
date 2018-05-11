package modele.foot;
import modele.Modele;
import modele.TortueAmelioree;

import java.awt.*;
import java.util.HashMap;

/**
 * Tortue du match de foot
 */
public class TortueAmelioreeFoot extends TortueAmelioree {

    public int minDYawToMove=70;
    Team m_team;
    public double m_rotationSpeed;
    Point objectif=null;
    int radiusFormationAttaque=150;
    double ratioChangementObjectif=0.001;
    HashMap<String,Integer> m_caracts=new HashMap<>();

    /**
     * Constructeur : crée la tortue et génère ses caractéristique aléatoirement
     * en fonction des min et max attribués à l'équipe
     * @param m Modèle
     * @param t Equipe
     */
    public TortueAmelioreeFoot(Modele m, Team t){
        super(m);
        setColor(t.getColor());
        this.m_team=t;
       initParams();

    }

    /**
     * Initialisation des paramètre :
     * Les paramètre sont choisis aléatoirement entre les bornes
     * qui leurs son assignées dans l'équipe
     */
    public void initParams(){
        Team t=m_team;
        int minSpeed=t.getMinSpeed();
        int maxSpeed=t.getMaxSpeed();
        m_speed=minSpeed+rnd.nextInt(maxSpeed+1-minSpeed);
        m_rotationSpeed=t.getMinRotationSpeed()+rnd.nextInt(t.getMaxRotationSpeed()+1-t.getMinRotationSpeed());
        HashMap<String,Caracteristique> caractsModele=((ModeleFoot) m_modele).getCaracts();
        //Génération des caractèristiques supplémentaires
        for(String c:caractsModele.keySet()) {
            if (this.getTeam().m_goalSide)
                m_caracts.put(c, caractsModele.get(c).generateValue2());
            else
                m_caracts.put(c, caractsModele.get(c).generateValue1());
        }

    }

    /**
     * Mise à jour de la tortue
     * Si la tortue a le ballon : se dirige vers les cages ou fait une passe s'il y a une tortue de l'équipe à proximité qui esr mieux placée
     * Sinon si l'équipe a le ballon : se dirige vers des objectifs aléatoire autour du but adverse
     * Sinon si l'équipe n'a pas le ballon : se dirige vers le ballon
     */
    @Override
    public void update(){
        BalleFoot balle=(BalleFoot) m_modele.getBalle();
        //Si l'équipe de latortue a le ballon
        double dyaw;

        //Si c'est l'équipe de la tortue qui a le ballon
            if (balle.getTeam() == this.getTeam()) {
                    //Si tortue a le ballon => on fonce vers les cages ou on fait la passe e une tortue proche
                    if (this.m_balle != null) {
                        double dCages=this.getDistance(m_team.getOppoGoalX(),m_team.getOppoGoalY());
                        for(TortueAmelioree t :m_team.getTortues()){
                           if(dCages>t.getDistance(m_team.getOppoGoalX(),m_team.getOppoGoalY())
                               &&this.getDistance(t)<getCaract("passeDist")){
                                m_balle.setNewProprio(t);
                               this.m_balle=null;
                               break;
                            }

                        }
                        dyaw = Math.abs(smoothPointAt(m_team.getOppoGoalX(), m_team.getOppoGoalY(), 2*m_rotationSpeed));
                        if (dyaw < minDYawToMove) {
                            //On avance uniquement si la rotation à éffectuer vers la cible n'est pas trop importante
                            avancer(m_speed);
                        }

                    }//Si La tortue n'a pas le ballon, elle choisit un objectif proche des cages adversaire et s'y rend
                    else{
                        if (objectif == null || rnd.nextDouble() < ratioChangementObjectif) {
                            double angle = -70 + rnd.nextInt(141);
                            angle*=ratioDegRad;
                            if (m_team.getOppoGoalSide()) {
                                objectif = new Point(  (int) Math.round(radiusFormationAttaque * Math.cos(angle)),
                                        m_team.getOppoGoalY()+(int) Math.round(radiusFormationAttaque * Math.sin(angle)));
                            } else {
                                objectif = new Point(m_team.getOppoGoalX() - (int) Math.round(radiusFormationAttaque * Math.cos(angle)),
                                        m_team.getOppoGoalY()+(int) Math.round(radiusFormationAttaque * Math.sin(angle)));
                            }
                        }
                        int dist=(int)Math.ceil(getDistance((int)objectif.getX(), (int)objectif.getY()));
                        if(dist>m_speed) {
                            dyaw = Math.abs(smoothPointAt((int) objectif.getX(), (int) objectif.getY(), m_rotationSpeed));
                            if (dyaw < minDYawToMove) {
                                avancer(m_speed);
                            }
                        }
                        else{
                            //Arrivée de la tortue sur l'objectif
                            if(dist>0) {
                                pointAt((int) objectif.getX(), (int) objectif.getY());
                                this.x=(int)objectif.getX();
                                this.y=(int)objectif.getY();
                            }

                        }

                }
            } else { // Si la balle est dans le camp adverse, on se dirige vers elle
                dyaw = Math.abs(this.smoothPointAt(balle.getX(), balle.getY(), m_rotationSpeed));
                if (dyaw < minDYawToMove) {
                    avancer(m_speed);

                }
            }

        //Les tortues en dehors du terrain son rappellées à l'ordre
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

        //Collisions entre tortues
        updateCollisions();

    }

    public int getCaract(String nom){
        return m_caracts.get(nom);
    }

    public void setCaract(String nom, int value){
        m_caracts.remove(nom);
        m_caracts.put(nom,value);
    }
    public double getRotationSpeed(){
        return m_rotationSpeed;
    }
    public void setRotationSpeed(double rotationSpeed){
        m_rotationSpeed=rotationSpeed;
    }

    public Team getTeam(){
        return m_team;
    }

}
