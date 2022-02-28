package kevw.games.chq.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import kevw.games.chq.model.units.Unit;

public class UnitView {

  public void paint(Unit unit, Graphics2D g) {
    Point p = unit.getLocation();
    int x = (int) (p.x * Board.SIZE_FACTOR);
    int y = (int) (p.y * Board.SIZE_FACTOR);
    g.setBackground(Color.RED);
    g.setColor(Color.RED);
    g.fillRect(x, y, (int) Board.SIZE_FACTOR, (int) Board.SIZE_FACTOR);
  }

}
