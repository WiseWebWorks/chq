package kevw.games.chq;

import java.util.EventListener;

public interface UnitChangeListener extends EventListener {

  void unitAdded(UnitChangeEvent e);

  void unitDied(UnitChangeEvent e);

  void unitMoved(UnitChangeEvent e);

  void unitAttacked(UnitChangeEvent e);

  void unitFortified(UnitChangeEvent e);

  void unitParadropped(UnitChangeEvent e);

  void unitPathChanged(UnitChangeEvent e);

}
