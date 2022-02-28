package kevw.games.chq.model.units;

import kevw.games.chq.model.Location;
import kevw.games.chq.model.Player;

public class Carrier extends SeaUnit implements PlaneHolder {

  public Carrier(Location location, Player player) {
    super(location, player);
  }

  public void attack(Unit otherUnit) {
    // TODO:  Implement this kevw.games.chq.Piece abstract method
  }

}
