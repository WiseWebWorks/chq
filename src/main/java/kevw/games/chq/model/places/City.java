package kevw.games.chq.model.places;

import kevw.games.chq.model.Location;

public class City extends AbstractCity {

  public City(Location[] locs) {
    super(locs);
  }

  @Override
  public int getProductionOutput() {
    return 1;
  }

  @Override
  public int getOilOutput() {
    return 0;
  }

}
