package kevw.games.chq.service;

import java.util.ArrayList;
import java.util.List;
import kevw.games.chq.event.DevelopmentChangeEvent;
import kevw.games.chq.event.DevelopmentChangeListener;
import kevw.games.chq.model.places.Development;

public class DevelopmentService {

  private static ArrayList<DevelopmentChangeListener> developmentChangeListeners =
      new ArrayList<>(2);

  public static synchronized void addDevelopmentChangeListener(DevelopmentChangeListener l) {
    if (!developmentChangeListeners.contains(l)) {
      developmentChangeListeners.add(l);
    }
  }

  public static synchronized void removeDevelopmentChangeListener(DevelopmentChangeListener l) {
    developmentChangeListeners.remove(l);
  }

  protected void fireDevelopmentConquered(Development development) {
    List listeners = (List) developmentChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((DevelopmentChangeListener) listeners.get(i)).developmentConquered(
          new DevelopmentChangeEvent(development));
    }
  }

  protected void fireDevelopmentBombed(Development development) {
    List listeners = (List) developmentChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((DevelopmentChangeListener) listeners.get(i)).developmentBombed(
          new DevelopmentChangeEvent(development));
    }
  }

  protected void fireDevelopmentAided(Development development) {
    List listeners = (List) developmentChangeListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((DevelopmentChangeListener) listeners.get(i)).developmentAided(
          new DevelopmentChangeEvent(development));
    }
  }

}
