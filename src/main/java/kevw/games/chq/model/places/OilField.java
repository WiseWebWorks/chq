package kevw.games.chq.model.places;

import java.awt.Color;
import java.awt.Point;
import kevw.games.chq.model.Location;
import kevw.games.chq.view.Board;

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

  public void paint(java.awt.Graphics2D g) {
    g.setColor(Color.black);
    g.setBackground(Color.black);
    Point p = locations[0].getPoint();
    int x = (int) (p.x * Board.SIZE_FACTOR);
    int y = (int) (p.y * Board.SIZE_FACTOR);
    g.drawRect(x, y, (int) Board.SIZE_FACTOR, (int) Board.SIZE_FACTOR);
    super.paint(g);
  }

}
