package kevw.games.chq;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

/**
 * The base class for Cities, Bases, and Resources
 */
public abstract class CBR {

  public CBR(Location[] locs) {
    locations = locs;
  }

  private CBR() {
    //Prevent others from using this
  }

  protected Location locations[];
  private static transient ArrayList<DevelopmentChangeListener> developmentChangeListeners = new ArrayList<DevelopmentChangeListener>(
      2);
  protected Player owner;
  protected Area area;

  public abstract int getOilOutput();

  public abstract int getProductionOutput();

  public void paint(Graphics2D g) {
    g.setColor(Color.black);
    g.setBackground(new Color(0, 0, 0, 0)); //Transparent
    g.draw(area);
  }

  public Color getOwnerColor() {
    return Color.blue;
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public static synchronized void addDevelopmentChangeListener(DevelopmentChangeListener l) {
    if (!developmentChangeListeners.contains(l)) {
      developmentChangeListeners.add(l);
    }
  }

  public static synchronized void removeDevelopmentChangeListener(DevelopmentChangeListener l) {
    developmentChangeListeners.remove(l);
  }

  protected void fireDevelopmentConquered() {
    List listeners = (List) developmentChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((DevelopmentChangeListener) listeners.get(i)).developmentConquered(
          new DevelopmentChangeEvent(this));
    }
  }

  protected void fireDevelopmentBombed() {
    List listeners = (List) developmentChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((DevelopmentChangeListener) listeners.get(i)).developmentBombed(
          new DevelopmentChangeEvent(this));
    }
  }

  protected void fireDevelopmentAided() {
    List listeners = (List) developmentChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((DevelopmentChangeListener) listeners.get(i)).developmentAided(
          new DevelopmentChangeEvent(this));
    }
  }

}
