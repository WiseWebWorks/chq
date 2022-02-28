/*
 * GameMouseEvent.java
 *
 * Created on June 9, 2005, 6:31 PM
 */

package kevw.games.chq.event;

import java.awt.Component;
import kevw.games.chq.model.Location;
import kevw.games.chq.model.places.Development;
import kevw.games.chq.model.units.Unit;

public class GameMouseEvent extends java.awt.event.MouseEvent {

  public GameMouseEvent(Component source, int id, long when, int modifiers, int x, int y,
      int clickCount, boolean popupTrigger, int button, Location location) {
    super(source, id, when, modifiers, x, y, clickCount, popupTrigger, button);
    this.location = location;
  }

  public GameMouseEvent(Component source, int id, long when, int modifiers, int x, int y,
      int clickCount, boolean popupTrigger, Location location) {
    super(source, id, when, modifiers, x, y, clickCount, popupTrigger);
    this.location = location;
  }

  private Location location;
  private Unit unit;
  private Development development;

  public Location getLocation() {
    return this.location;
  }

  public Unit getUnit() {
    return this.unit;
  }

  public Development getDevelopment() {
    return this.development;
  }

}
