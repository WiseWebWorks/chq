package kevw.games.chq;

import java.awt.Color;
import java.awt.Point;

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

  @Override
  public void paint(java.awt.Graphics2D g) {
    g.setColor(Color.black);
    Point p = locations[0].getPoint();
    int x = (int) (p.x * Board.SIZE_FACTOR);
    int y = (int) (p.y * Board.SIZE_FACTOR);
    g.drawRect(x, y, (int) Board.SIZE_FACTOR, (int) Board.SIZE_FACTOR);
    super.paint(g);
  }

}
