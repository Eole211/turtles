package modele.foot;

import java.util.Random;

/**
 * Caractéristique pouvant être ajoutées au modèle
 * Les bornes de la valeur peuvent être propres à chaque équipe
 * Les tortues obtiennent une valeur prise aléatoire entre les bornes de son équipe
 */
public class Caracteristique {
    /**
     * valeur min de la caractéristique pour l'équipe 1
     */
     private int minValueTeam1;

    /**
     * valeur min de la caractéristique pour l'équipe 2
     */
    private int minValueTeam2;

    /**
     * valeur max de la caractéristique pour l'équipe 1
     */
    private int maxValueTeam1;

    /**
     * valeur max de la caractéristique pour l'équipe 2
     */
     private int maxValueTeam2;

    private Random rand=new Random();

    /**
     * Constructeur avec les même valeurs pour chaque équipe
     * @param minValue valeur min de la caractéristique pour les équipes
     * @param maxValue valeur max de la caractéristique pour les équipes
     */
    Caracteristique(int minValue, int maxValue){
        minValueTeam1=(minValueTeam2=minValue);
        maxValueTeam1=(maxValueTeam2=maxValue);
    }

    /**
     * Constructeur avec des valeurs différentes pour chaque équipe
     * @param minValueT1 valeur min de la caractéristique pour l'équipe 1
     * @param maxValueT1 valeur max de la caractéristique pour les équipe 1
     */
    Caracteristique(int minValueT1, int maxValueT1,int minValueT2, int maxValueT2){
        minValueTeam1=minValueT1;
        maxValueTeam1=maxValueT1;
        minValueTeam2=minValueT2;
        maxValueTeam2=maxValueT2;
    }

    /**
     *  Retourne une valeur de la caractéristique  pour l'équipe 1 tirée aléatoirement entre ses bornes
     * @return valeur
     */
    public int generateValue1(){
        return minValueTeam1+rand.nextInt(maxValueTeam1-minValueTeam1+1);
    }

    /**
     * Retourne une valeur de la caractéristique  pour l'équipe 2 tirée aléatoirement entre ses bornes
     * @return valeur
     */
    public int generateValue2(){
        return minValueTeam2+rand.nextInt(maxValueTeam2-minValueTeam2+1);
    }


    public int getMinValueTeam1() {
        return minValueTeam1;
    }

    public int getMinValueTeam2() {
        return minValueTeam2;
    }

    public int getMaxValueTeam1() {
        return maxValueTeam1;
    }

    public int getMaxValueTeam2() {
        return maxValueTeam2;
    }
}
