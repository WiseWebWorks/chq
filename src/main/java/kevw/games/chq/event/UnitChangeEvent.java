package kevw.games.chq.event;

import java.util.EventObject;
import kevw.games.chq.model.units.Unit;

public class UnitChangeEvent extends EventObject {

  public UnitChangeEvent(Unit source) {
    super(source);
  }

}
