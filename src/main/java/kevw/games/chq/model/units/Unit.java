package kevw.games.chq.model.units;

import java.util.ArrayList;
import java.util.List;
import kevw.games.chq.event.UnitChangeEvent;
import kevw.games.chq.event.UnitChangeListener;
import kevw.games.chq.model.Location;
import kevw.games.chq.model.Player;

public abstract class Unit {

  protected Location loc;
  protected Player player;
  protected static transient ArrayList<UnitChangeListener> unitChangeListeners =
      new ArrayList<>(2);

  public Unit() {
    fireUnitAdded();
  }

  public static synchronized void addUnitChangeListener(UnitChangeListener l) {
    if (!unitChangeListeners.contains(l)) {
      unitChangeListeners.add(l);
    }
  }

  public static synchronized void removeUnitChangeListener(UnitChangeListener l) {
    unitChangeListeners.remove(l);
  }

  protected void fireUnitAdded() {
    List listeners = (List) unitChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((UnitChangeListener) listeners.get(i)).unitAdded(new UnitChangeEvent(this));
    }
  }

}