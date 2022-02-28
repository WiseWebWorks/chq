package kevw.games.chq.model.units;

import java.util.List;
import kevw.games.chq.event.TurnListener;
import kevw.games.chq.event.UnitChangeEvent;
import kevw.games.chq.event.UnitChangeListener;
import kevw.games.chq.model.Location;

public abstract class MobileUnit extends Unit implements TurnListener {

  public static boolean canTraverse(Location loc) {
    return true;
  }

  public static double getCurrentSpeed() {
    return 0;
  }

  public Location getCurrentLocation() {
    return loc;
  }

  public abstract void attack(Unit otherUnit);

  public boolean setDestination(Location newLocation) {
    return true;
  }

  public static float getScanningRadius() {
    return 0;
  }

  public static float getAttackRadius() {
    return 0;
  }

  protected void fireUnitDied() {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitDied(new UnitChangeEvent(this));
    }
  }

  protected void fireUnitMoved() {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitMoved(new UnitChangeEvent(this));
    }
  }

  protected void fireUnitAttacked() {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitAttacked(new UnitChangeEvent(this));
    }
  }

  protected void fireUnitFortified() {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitFortified(new UnitChangeEvent(this));
    }
  }

  protected void fireUnitParadropped() {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitParadropped(new UnitChangeEvent(this));
    }
  }

  protected void fireUnitPathChanged() {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitPathChanged(new UnitChangeEvent(this));
    }
  }

  /**
   * Signals that this unit should process it's next command
   */
  public void takeTurn() {
  }

}
