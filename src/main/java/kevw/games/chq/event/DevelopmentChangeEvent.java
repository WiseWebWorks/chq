package kevw.games.chq.event;

import java.util.EventObject;
import kevw.games.chq.model.places.Development;

public class DevelopmentChangeEvent extends EventObject {

  public DevelopmentChangeEvent(Development source) {
    super(source);
  }

}
