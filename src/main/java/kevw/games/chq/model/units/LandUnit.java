package kevw.games.chq.model.units;

import kevw.games.chq.model.Location;
import kevw.games.chq.model.Player;

public abstract class LandUnit extends MobileUnit {

  public LandUnit(Location location, Player player) {
    super(location, player);
  }

}
