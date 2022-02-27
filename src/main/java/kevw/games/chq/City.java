package kevw.games.chq;

import java.awt.Point;
import java.awt.Color;

public class City extends AbstractCity  {
	public City(Location[] locs) {
		super(locs);
	}
	
	public int getProductionOutput() {
		return 1;
	}

	public int getOilOuput() {
		return 0;
	}

	public void paint(java.awt.Graphics2D g) {
		g.setColor(Color.black);
		Point p = locations[0].getPoint();
		int x = (int)(p.x*Board.sizeFactor);
		int y = (int)(p.y*Board.sizeFactor);
		g.drawRect(x, y, (int)Board.sizeFactor, (int)Board.sizeFactor);
		super.paint(g);
	}
}