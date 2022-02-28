package kevw.games.chq.model.places;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Area;
import kevw.games.chq.model.Location;
import kevw.games.chq.model.Player;

/**
 * The base class for Cities, Bases, and Resources
 */
public abstract class Development {

  public Development(Location[] locs) {
    locations = locs;
  }

  protected Location locations[];
  protected Player owner;
  protected Area area;

  public abstract int getOilOutput();

  public abstract int getProductionOutput();

  public Color getOwnerColor() {
    return Color.PINK;
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public Point getLocation() {
    return locations[0].getPoint();
  }

}
