package kevw.games.chq.model.units;

import java.awt.Point;
import kevw.games.chq.model.Location;
import kevw.games.chq.model.Player;

public abstract class Unit {

  protected final Location location;
  protected final Player player;

  public Unit(Location location, Player player) {
    this.location = location;
    this.player = player;
  }

  public Point getLocation() {
    return location.getPoint();
  }

  public Player getPlayer() {
    return player;
  }

}
