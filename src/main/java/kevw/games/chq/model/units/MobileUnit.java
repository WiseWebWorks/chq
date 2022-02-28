package kevw.games.chq.model.units;

import kevw.games.chq.event.TurnListener;
import kevw.games.chq.model.Location;
import kevw.games.chq.model.Player;

public abstract class MobileUnit extends Unit implements TurnListener {

  public MobileUnit(Location location, Player player) {
    super(location, player);
  }

  public static boolean canTraverse(Location loc) {
    return true;
  }

  public static double getCurrentSpeed() {
    return 0;
  }

  public Location getCurrentLocation() {
    return location;
  }

  public abstract void attack(Unit otherUnit);

  public boolean setDestination(Location newLocation) {
    return true;
  }

  public static float getScanningRadius() {
    return 0;
  }

  public static float getAttackRadius() {
    return 0;
  }

  /**
   * Signals that this unit should process it's next command
   */
  public void takeTurn() {
  }

}
