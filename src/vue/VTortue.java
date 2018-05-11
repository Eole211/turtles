package vue;

import modele.*;
import utils.Utils;


import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by leo on 21/05/14.
 */
public class VTortue {

    public static void drawSegment(Graphics graph, Segment seg) {

        if (graph==null)
            return;
        graph.setColor(seg.color);
        graph.drawLine(seg.ptStart.x, seg.ptStart.y, seg.ptEnd.x, seg.ptEnd.y);
    }


    public static void drawTurtles(ArrayList<TortueAmelioree> lt, TortueAmelioree courante , Graphics g) {
       //On dessine les segments avant les tortues pour qu'ils appparaissent en dessous
        for (Iterator it = lt.iterator(); it.hasNext(); ) {
            TortueAmelioree t = (TortueAmelioree) it.next();

            VTortue.drawSegments(g, t);
        }
        for (Iterator it = lt.iterator(); it.hasNext(); ) {
            TortueAmelioree t = (TortueAmelioree) it.next();
            VTortue.drawTurtle(g, t);
        }

    }

    public static void drawSelectionCircle(Entity t,Graphics g){
        float alpha=.5f;
        g.setColor(new Color(120,20,20,120));
        g.fillOval(t.getX()-12,t.getY()-12,24,24);
    }
    public static void  drawSegments(Graphics graph, Entity tortue){
        synchronized (tortue.sync) {
            if (graph == null)
                return;
            ArrayList<Segment> listSegments = tortue.getListSegments();
            // Dessine les segments
            for (Iterator it = listSegments.iterator(); it.hasNext(); ) {
                Segment seg = (Segment) it.next();
                drawSegment(graph, seg);
            }
        }
    }

    public static void drawTurtle (Graphics graph, TortueAmelioree tortue) {
        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p      System.out.println("amelioree avancee");
        Point p = new Point(tortue.getX(),tortue.getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droiterepaint
        double theta= Entity.ratioDegRad*(-tortue.getDir());
        //Demi angle au sommet du triangle
        double alpha=Math.atan( (float) Entity.rb / (float) Entity.rp );
        //Rayon de la fleche
        double r=Math.sqrt( Entity.rp* Entity.rp + Entity.rb* Entity.rb );
        //Sens de la fleche

        //Pointe
        Point p2=new Point((int) Math.round(p.x+r*Math.cos(theta)),
                (int) Math.round(p.y-r*Math.sin(theta)));
        arrow.addPoint(p2.x,p2.y);
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
                (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        arrow.addPoint(p2.x,p2.y);
        graph.setColor(Utils.decodeColor(tortue.getColor()));
        graph.fillPolygon(arrow);
        tortue.set_polygon(arrow);
        if(tortue instanceof TortueAmelioree)
            graph.drawString(( tortue).getNom(), tortue.getX(), tortue.getY() + 15);




        //if(!tortue.getSomethingToSay().equals(""))
           // drawTextSpokenByTurtle(graph,tortue,tortue.getSomethingToSay());
    }
/*
    public static void drawTextSpokenByTurtle(Graphics graph, TortueAmelioree tortue, String text){
        Color c= graph.getColor();
        graph.setColor(Color.black);
        graph.drawString(text, tortue.getX(), tortue.getY()-15);
       graph.setColor(c);
    }
    */
}
