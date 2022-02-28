package kevw.games.chq.model.units;

import kevw.games.chq.model.Location;
import kevw.games.chq.model.Player;

public abstract class SeaUnit extends MobileUnit {

  public SeaUnit(Location location, Player player) {
    super(location, player);
  }

}
