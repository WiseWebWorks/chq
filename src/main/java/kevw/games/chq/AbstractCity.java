package kevw.games.chq.model.places;

import kevw.games.chq.model.Location;
import kevw.games.chq.model.units.PlaneHolder;

public abstract class AbstractCity extends Development implements PlaneHolder {

  public AbstractCity(Location[] locs) {
    super(locs);
  }

}
