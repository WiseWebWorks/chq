package kevw.games.chq.model.places;

import kevw.games.chq.model.Location;

public class OilField extends Development {

  public OilField(Location[] locs) {
    super(locs);
  }

  public int getProductionOutput() {
    return 0;
  }

  public int getOilOutput() {
    return 1;
  }

}
