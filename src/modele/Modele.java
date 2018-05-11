package modele;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Observable;

/**
 * Modèle abstrait : interface commune aux modèles et paramètres communs
 */
public abstract class Modele extends Observable {
    protected TortueBalle m_balle;
    protected int m_width=800;
    protected int m_height=600;
    protected boolean updateSegments=false;

    /**
     *
     * @return
     */
    public abstract TortueAmelioree getCourante();
    public abstract ArrayList<TortueAmelioree> get_tortues();
    public abstract void update();
    public abstract int getNbTortues();

    public boolean getUpdateSegments(){
        return updateSegments;
    }
    public TortueBalle getBalle()  {
        return m_balle;
    }
    public int getHeight() {
        return m_height;
    }
    public int getWidth() {
        return m_width;
    }

}
