package modele;

import java.awt.Color;
import java.awt.Point;

/**
 * Segment pour afficher les races des tortues dans le mode party
 */
public class Segment {
	public Point ptStart, ptEnd;
	public Color color;
	
	public Segment() {
		ptStart = new Point(0,0);
		ptEnd = new Point(0,0);
	}
}
