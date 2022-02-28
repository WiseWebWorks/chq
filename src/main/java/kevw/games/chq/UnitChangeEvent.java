package kevw.games.chq;

import java.util.EventObject;
import kevw.games.chq.units.Unit;

public class UnitChangeEvent extends EventObject {

  public UnitChangeEvent(Unit source) {
    super(source);
  }

}
