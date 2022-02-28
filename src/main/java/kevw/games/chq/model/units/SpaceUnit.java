package kevw.games.chq.model.units;

import kevw.games.chq.model.Location;
import kevw.games.chq.model.Player;

public abstract class SpaceUnit extends MobileUnit {

  public SpaceUnit(Location location, Player player) {
    super(location, player);
  }

}
