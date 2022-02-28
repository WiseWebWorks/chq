package kevw.games.chq.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class CursorView {

  private final BufferedImage crosshairImage;
  private final double sizeFactor;

  public CursorView(double sizeFactor) {
    this.sizeFactor = sizeFactor;
    Dimension dim = new Dimension((int) (13 * sizeFactor), (int) (13 * sizeFactor));
    crosshairImage = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D crosshairImageGraphics = crosshairImage.createGraphics();
    Color transparent = new Color(255, 255, 255, 0); //Transparent
    crosshairImageGraphics.setPaint(transparent);
    crosshairImageGraphics.fillRect(0, 0, dim.width, dim.height);

    double thickness = sizeFactor;
    double halfthickness = Math.floor(sizeFactor / 2);
    double antithickness = Math.floor(
        Math.min((dim.width - (7 * thickness)) / 2 + (2 * thickness),
            (13 * sizeFactor / 2) - (sizeFactor * 1.5f)));

    double topX = antithickness + halfthickness;
    double topY = halfthickness;
    double topWidth = 2 * thickness;
    double topHeight = antithickness - thickness;

    crosshairImageGraphics.setPaint(Color.white);

    //crosshairImageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    Rectangle.Double top = new Rectangle.Double(topX, topY, topWidth, topHeight);
    Rectangle.Double bottom = new Rectangle.Double(topX, topY + topHeight + (4 * thickness),
        topWidth,
        topHeight);
    Rectangle.Double left = new Rectangle.Double(topY, topX, topHeight, topWidth);
    Rectangle.Double right = new Rectangle.Double(topY + topHeight + (4 * thickness), topX,
        topHeight,
        topWidth);

    crosshairImageGraphics.fill(top);
    crosshairImageGraphics.fill(bottom);
    crosshairImageGraphics.fill(left);
    crosshairImageGraphics.fill(right);

    crosshairImageGraphics.setPaint(Color.black);
    crosshairImageGraphics.setStroke(new BasicStroke((float) sizeFactor));
    crosshairImageGraphics.draw(top);
    crosshairImageGraphics.draw(bottom);
    crosshairImageGraphics.draw(left);
    crosshairImageGraphics.draw(right);
  }

  public void paint(Point mousePoint, Graphics2D g, ImageObserver imageObserver) {
    if (mousePoint != null) {
      final int mouseX = (int) (mousePoint.getX() - crosshairImage.getWidth() / 2d);
      final int mouseY = (int) (mousePoint.getY() - crosshairImage.getHeight() / 2d);
      final int x = mouseX - (mouseX % (int) sizeFactor);
      final int y = mouseY - (mouseY % (int) sizeFactor);
      g.drawImage(crosshairImage, x, y, imageObserver);
    }
  }

}
