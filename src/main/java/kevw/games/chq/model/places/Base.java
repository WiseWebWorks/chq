package kevw.games.chq.model.places;

import kevw.games.chq.model.Location;

public class Base extends AbstractCity {

  public Base(Location[] locs) {
    super(locs);
  }

  public int getOilOutput() {
    return 0;
  }

  public int getProductionOutput() {
    return 0;
  }

}
