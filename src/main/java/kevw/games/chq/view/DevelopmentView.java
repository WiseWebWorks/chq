package kevw.games.chq.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import kevw.games.chq.model.places.AbstractCity;
import kevw.games.chq.model.places.Development;

public class DevelopmentView {

  public void paint(Development development, Graphics2D g) {
    g.setBackground(new Color(0, 0, 0, 0)); //Transparent
    if (development instanceof AbstractCity) {
      g.setColor(Color.BLUE);
      Point p = development.getLocation();
      int x = (int) (p.x * Board.SIZE_FACTOR);
      int y = (int) (p.y * Board.SIZE_FACTOR);
      g.drawRect(x - (int) Board.SIZE_FACTOR, y - (int) Board.SIZE_FACTOR,
          (int) (3 * Board.SIZE_FACTOR),
          (int) (3 * Board.SIZE_FACTOR));
      g.setColor(Color.BLACK);
      final Area area = development.getArea().createTransformedArea(
          AffineTransform.getScaleInstance(Board.SIZE_FACTOR, Board.SIZE_FACTOR));
      g.draw(area);
    } else if (development != null) {
      g.setColor(Color.ORANGE);
      g.setBackground(Color.ORANGE);
      Point p = development.getLocation();
      int x = (int) (p.x * Board.SIZE_FACTOR);
      int y = (int) (p.y * Board.SIZE_FACTOR);
      g.drawRect(x, y, (int) Board.SIZE_FACTOR, (int) Board.SIZE_FACTOR);
    }
  }

}
