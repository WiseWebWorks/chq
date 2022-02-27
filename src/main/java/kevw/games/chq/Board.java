package kevw.games.chq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;
import kevw.games.chq.units.Unit;
import kevw.games.chq.units.LandUnit;
import kevw.games.chq.units.Tank;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.Cursor;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.io.*;

/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public class Board extends JComponent implements MouseInputListener, ActionListener, KeyListener {
	//~ Static fields/initializers -----------------------------------------------------------------

	/** DOCUMENT ME! */
	private static final int mapWidth = 320;

	/** DOCUMENT ME! */
	private static final int mapHeight = 136;

	/** DOCUMENT ME! */
	protected static final float sizeFactor = 6.0f;

	/** DOCUMENT ME! */
	private static final int ACTION_SELECT_LOCATION = ActionEvent.ACTION_FIRST + 1;

	/** DOCUMENT ME! */
	private static final int ACTION_ZOOM = ActionEvent.ACTION_FIRST + 2;

	private static final int NUM_BASE = 12;
	private static final int NUM_RESOURCE = 20;
	private static final int NUM_CITY = 50;
	private static final int NUM_CAPITAL = 2;
	private static final int NUM_CBR = NUM_CAPITAL + NUM_CITY + NUM_BASE + NUM_RESOURCE;
	private static final int NUM_REGION = 184;
	private static final int MAXREGIONS = NUM_REGION;

	//~ Instance fields ----------------------------------------------------------------------------

	/**
	 * <TODO: Comment for Association here>
	 *
	 * @association <kevw.games.chq.BoardLocations> kevw.games.chq.Location
	 */
	protected Location[][] locations = new Location[mapWidth][mapHeight];

	private CBR[] cbrs;
	private City redCapital;
	private City blueCapital;
	private City[] cities;
	private Base[] bases;
	private OilField[] resources;

	/** DOCUMENT ME! */
	//private transient ArrayList<BoardListener> boardListeners = new ArrayList<BoardListener>(2);

	/** DOCUMENT ME! */
	private transient ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>(2);

	/** DOCUMENT ME! */
	private BufferedImage worldMapWithGeography;
	private BufferedImage worldMapNoGeography;

	/** DOCUMENT ME! */
	private boolean geographyShown = true;

	private Dimension size;

	private JLabel statusLabel;

	//~ Constructors -------------------------------------------------------------------------------

	/**
	 * Creates a new Board object.
	 */
	public Board() {
		try {
			Image image = Toolkit.getDefaultToolkit().getImage(Board.class.getResource("board.png"));
			MediaTracker tracker = new MediaTracker(this);
			tracker.addImage(image, 0);
			tracker.waitForAll();

			size = new Dimension((int) (mapWidth * sizeFactor), (int) (mapHeight * sizeFactor));
			setSize(size);
			setMinimumSize(size);
			setMaximumSize(size);
			setPreferredSize(size);

			BufferedImage worldMapData = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D worldMapDataGraphics = worldMapData.createGraphics();
			worldMapDataGraphics.drawImage(image, 0, 0, null);

			Raster raster = worldMapData.getData();
			DataBuffer buff = raster.getDataBuffer();
			SampleModel sm = raster.getSampleModel();
			int[] pixelData = new int[3];
			Color pixelColor;

			for (int i = 0; i < mapWidth; i++) {
				for (int j = 0; j < mapHeight; j++) {
					pixelData = sm.getPixel(i, j, pixelData, buff);
					pixelColor = new Color(pixelData[0], pixelData[1], pixelData[2]);

					try {
						locations[i][j] = new Location(i, j, pixelColor);
					} catch (IllegalArgumentException ex) {
						System.out.println("(" + i + "," + j + "):" + pixelData[0] + "," + pixelData[1] + "," + pixelData[2] + ";\t");
					}
				}
			}


			worldMapNoGeography = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
			worldMapWithGeography = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);

			Graphics2D worldMapGraphicsNoGeography = worldMapNoGeography.createGraphics();
			Graphics2D worldMapGraphicsWithGeography = worldMapWithGeography.createGraphics();

			Location currLoc;

			for (int i = 0; i < locations.length; i++) {
				for (int j = 0; j < locations[i].length; j++) {
					currLoc = locations[i][j];

					if (currLoc == null) {
						System.out.println("Null at:" + i + "," + j);
					} else {
						worldMapGraphicsNoGeography.setColor(currLoc.getColor(false));
						worldMapGraphicsWithGeography.setColor(currLoc.getColor(true));

						Rectangle.Float rect = new Rectangle.Float(i * sizeFactor, j * sizeFactor, sizeFactor, sizeFactor);
						worldMapGraphicsNoGeography.fill(rect);
						worldMapGraphicsWithGeography.fill(rect);
					}
				}
			}
		} catch (InterruptedException ex) {}

		addMouseListener(this);
		addMouseMotionListener(this);

		KeyStroke F7 = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);
		registerKeyboardAction(this, "Copy", F7, WHEN_IN_FOCUSED_WINDOW);

		registerKeyboardAction(this, "Toggle Show Geography", KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), WHEN_IN_FOCUSED_WINDOW);
		registerKeyboardAction(this, KeyStroke.getKeyStroke('f', 0), WHEN_IN_FOCUSED_WINDOW);

		KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0);

		this.getInputMap().put(key, key);
		//this.getActionMap().put(key, this);
		addKeyListener(this);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getBestCursorSize((int) (13 * sizeFactor), (int) (13 * sizeFactor));
		BufferedImage crosshairImage = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D crosshairImageGraphics = crosshairImage.createGraphics();
		Color transparent = new Color(255, 255, 255, 0); //Transparent
		crosshairImageGraphics.setPaint(transparent);
		crosshairImageGraphics.fillRect(0, 0, dim.width, dim.height);

		float thickness = sizeFactor;
		float halfthickness = (float) Math.floor(sizeFactor / 2);
		float antithickness = (float) Math.floor(Math.min((dim.width - (7 * thickness)) / 2 + (2 * thickness), (13 * sizeFactor / 2) - (sizeFactor * 1.5f)));

		float topX = antithickness + halfthickness;
		float topY = halfthickness;
		float topWidth = 2 * thickness;
		float topHeight = antithickness - thickness;

		crosshairImageGraphics.setPaint(Color.white);

		//crosshairImageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Rectangle.Float top = new Rectangle.Float(topX, topY, topWidth, topHeight);
		Rectangle.Float bottom = new Rectangle.Float(topX, topY + topHeight + (4 * thickness), topWidth, topHeight);
		Rectangle.Float left = new Rectangle.Float(topY, topX, topHeight, topWidth);
		Rectangle.Float right = new Rectangle.Float(topY + topHeight + (4 * thickness), topX, topHeight, topWidth);

		crosshairImageGraphics.fill(top);
		crosshairImageGraphics.fill(bottom);
		crosshairImageGraphics.fill(left);
		crosshairImageGraphics.fill(right);

		crosshairImageGraphics.setPaint(Color.black);
		crosshairImageGraphics.setStroke(new BasicStroke(sizeFactor));
		crosshairImageGraphics.draw(top);
		crosshairImageGraphics.draw(bottom);
		crosshairImageGraphics.draw(left);
		crosshairImageGraphics.draw(right);

		Point hotspot = new Point(dim.width / 2, dim.height / 2);
		Cursor crosshairCursor = tk.createCustomCursor(crosshairImage, hotspot, "crosshair");
		setCursor(crosshairCursor);

		cbrs = new CBR[NUM_CBR];
		cities = new City[NUM_CITY];
		bases = new Base[NUM_BASE];
		resources = new OilField[NUM_RESOURCE];

		Read_Regions();

		Tank t = new Tank();

		if (t instanceof LandUnit) {
			t.attack(t);
		}
	}

	public int Read_Regions() {
		try {
			FileInputStream regionFile = new FileInputStream("K:\\Games\\CHQ\\regions.dat");
			DataInputStream ois = new DataInputStream(regionFile);

			cbrs = new CBR[MAXREGIONS];
			for (int i=0 ; i<cbrs.length ; i++)	{
				short x = ois.readShort();
				short y = ois.readShort();
				Location loc = locations[x][y];
				CBR cbr = new City(new Location[] {loc});
				cbrs[i] = cbr;
				loc.setDevelopment(cbr);

				// x/y coordinates for resource regions
				Polygon regionPoly = new Polygon();
				for (int j=0; j<4 ; j++){
					int x1 = (int)(ois.readShort()*sizeFactor);
					int y1 = (int)(ois.readShort()*sizeFactor);
					switch (j) {
						case 0:
							x1 += sizeFactor;
							break;
						case 1:
							x1 += sizeFactor;
							y1 += sizeFactor;
							break;
						case 2:
							y1 += sizeFactor;
							break;
					}
					regionPoly.addPoint(x1, y1);
				}
				cbrs[i].setArea(new Area(regionPoly));
				System.out.println(cbrs[i].toString());
			}
			ois.close();
			regionFile.close();
		} catch (IOException ex) {
			System.err.println("Could not open the data file: regions.dat.\n");
			return 0;
		}

		redCapital = new City(new Location[] {locations[0][0]});
		blueCapital = new City(new Location[] {locations[0][1]});

		/* Initialize cities using random_city_seed. */
//		srand(random_city_seed);	/* Re-seed random number generator.*/
//
//		for ( i=0; i<NUM_CBR; i++ ) {
//			try_again:
//
//			/* FR: Corrected City Random for position of cities in "even" 0-319. */
//			/* FR: Last was limited to 0-255. At least 1/3 of right side of map  */
//			/* FR: is not place any cities/oils. */
//			x= getrandom( 1, MAX_X-2) ;
//			y= getrandom( 1, MAX_Y-2) ;
//			if( 0==validate_point( &x, &y, i ) )
//				 goto try_again;
//
//			/* Got good point.  Create region for it.  */
//			Add_Region( x, y, i ) ;
//		}	/* For each of the NUM_CBR regions. */

		return 1;
	}
	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param x DOCUMENT ME!
	 * @param y DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public Unit getUnitAt(int x, int y) {
		return null;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void actionPerformed(ActionEvent e) {
		int i = 0;

		if (e.getActionCommand() == "Toggle Show Geography") {
			toggleGeographyShown();
		}
		/*if (e instanceof KeyEvent) {
			KeyEvent ke = (KeyEvent)e;
			switch (ke.getKeyCode()) {
				case KeyEvent.VK_F7:
					toggleGeographyShown();
					break;
				default:
					break;
			}
		}*/
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param l DOCUMENT ME!
	 */
	public synchronized void addActionListener(ActionListener l) {
		if (!actionListeners.contains(l)) {
			actionListeners.add(l);
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void keyPressed(KeyEvent e) {
		e = e;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void keyReleased(KeyEvent e) {
		e = e;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void keyTyped(KeyEvent e) {
		e = e;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void mouseClicked(MouseEvent e) {
		if (e != null) {
			int modifiers = e.getModifiers();

			if ((modifiers & MouseEvent.BUTTON1_MASK) != 0) { //Left click
				fireActionPerformed(new ActionEvent(e.getSource(), ACTION_SELECT_LOCATION, "Select location"));
			} else if ((modifiers & MouseEvent.BUTTON2_MASK) != 0) {} else if ((modifiers & MouseEvent.BUTTON3_MASK) != 0) { //Right click
				fireActionPerformed(new ActionEvent(e.getSource(), ACTION_ZOOM, "Zoom"));
			}
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void mouseDragged(MouseEvent e) {
		e = e;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void mouseEntered(MouseEvent e) {
		e = e;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void mouseExited(MouseEvent e) {
		e = e;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void mouseMoved(MouseEvent e) {
		e = e;
		Point p = e.getPoint();
		if (statusLabel != null) {
			int i, j;
			i = (int)(p.x/sizeFactor);
			j = (int)(p.y/sizeFactor);
			Location currLoc = locations[i][j];
			if (currLoc != null) {
				statusLabel.setText("("+i+","+j+") "+currLoc.getTerrainTypeString());
			}
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void mousePressed(MouseEvent e) {
		e = e;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	public void mouseReleased(MouseEvent e) {
		e = e;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param g DOCUMENT ME!
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (geographyShown) {
			// Draws the buffered image to the screen.
			g2.drawImage(worldMapWithGeography, 0, 0, this);
		} else {
			// Draws the buffered image to the screen.
			g2.drawImage(worldMapNoGeography, 0, 0, this);
		}

		for (CBR cbr : cbrs) {
			cbr.paint(g2);
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param l DOCUMENT ME!
	 */
	public synchronized void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param e DOCUMENT ME!
	 */
	protected void fireActionPerformed(ActionEvent e) {
		List listeners = (List) actionListeners.clone();
		int count = listeners.size();

		for (int i = 0; i < count; i++) {
			((ActionListener) listeners.get(i)).actionPerformed(e);
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param g DOCUMENT ME!
	 */
	protected void paintBorder(Graphics g) {
		// TODO:  Override this javax.swing.JComponent method
		super.paintBorder(g);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param g DOCUMENT ME!
	 */
	protected void paintChildren(Graphics g) {
		// TODO:  Override this javax.swing.JComponent method
		super.paintChildren(g);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param g DOCUMENT ME!
	 */
	protected void paintComponent(Graphics g) {
		// TODO:  Override this javax.swing.JComponent method
		super.paintComponent(g);
	}

	/**
	 * DOCUMENT ME!
	 */
	public void toggleGeographyShown() {
		geographyShown = !geographyShown;
		repaint();
	}

	/**
	 * DOCUMENT ME!
	 * @param anAction
	 * @param aCommand
	 * @param aKeyStroke
	 * @param aCondition
	 */
	public void registerKeyboardAction(ActionListener anAction, String aCommand, KeyStroke aKeyStroke, int aCondition) {
		// TODO:  Override this javax.swing.JComponent method
		super.registerKeyboardAction(anAction, aCommand, aKeyStroke, aCondition);
	}

	/**
	 * DOCUMENT ME!
	 * @param anAction
	 * @param aKeyStroke
	 * @param aCondition
	 */
	public void registerKeyboardAction(ActionListener anAction, KeyStroke aKeyStroke, int aCondition) {
		// TODO:  Override this javax.swing.JComponent method
		super.registerKeyboardAction(anAction, aKeyStroke, aCondition);
	}

	/**
	 * DOCUMENT ME!
	 * @return
	 */
	public JLabel getStatusLabel() {
		return statusLabel;
	}

	/**
	 * DOCUMENT ME!
	 * @param statusLabel
	 */
	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}
}
