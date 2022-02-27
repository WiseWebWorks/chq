package kevw.games.chq;

import java.awt.Point;
import java.awt.Color;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class Base extends AbstractCity {
	
	public Base(Location[] locs) {
		super(locs);
	}
	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public int getOilOuput() {
		return 0;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public int getProductionOutput() {
		return 0;
	}

	public void paint(java.awt.Graphics2D g) {
		g.setColor(Color.black);
//		g.setBackground(Color.);
		Point p = locations[0].getPoint();
		int x = (int)(p.x*Board.sizeFactor);
		int y = (int)(p.y*Board.sizeFactor);
		g.drawRect(x, y, (int)Board.sizeFactor, (int)Board.sizeFactor);
		super.paint(g);
	}
}
