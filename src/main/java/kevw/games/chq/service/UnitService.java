package kevw.games.chq.service;

import java.util.ArrayList;
import java.util.List;
import kevw.games.chq.event.UnitChangeEvent;
import kevw.games.chq.event.UnitChangeListener;
import kevw.games.chq.model.units.Tank;
import kevw.games.chq.model.units.Unit;

public class UnitService {

  protected static transient ArrayList<UnitChangeListener> unitChangeListeners =
      new ArrayList<>(2);

  public Unit addUnit() {
    final Unit unit = new Tank(null, null);
    fireUnitAdded(unit);
    return unit;
  }

  public static synchronized void addUnitChangeListener(UnitChangeListener l) {
    if (!unitChangeListeners.contains(l)) {
      unitChangeListeners.add(l);
    }
  }

  public static synchronized void removeUnitChangeListener(UnitChangeListener l) {
    unitChangeListeners.remove(l);
  }

  protected void fireUnitAdded(Unit unit) {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitAdded(new UnitChangeEvent(unit));
    }
  }

  protected void fireUnitDied(Unit unit) {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitDied(new UnitChangeEvent(unit));
    }
  }

  protected void fireUnitMoved(Unit unit) {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitMoved(new UnitChangeEvent(unit));
    }
  }

  protected void fireUnitAttacked(Unit unit) {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitAttacked(new UnitChangeEvent(unit));
    }
  }

  protected void fireUnitFortified(Unit unit) {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitFortified(new UnitChangeEvent(unit));
    }
  }

  protected void fireUnitParadropped(Unit unit) {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitParadropped(new UnitChangeEvent(unit));
    }
  }

  protected void fireUnitPathChanged(Unit unit) {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitPathChanged(new UnitChangeEvent(unit));
    }
  }

}
