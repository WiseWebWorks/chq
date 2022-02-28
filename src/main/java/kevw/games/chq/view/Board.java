package kevw.games.chq.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputListener;
import kevw.games.chq.model.Location;
import kevw.games.chq.model.World;
import kevw.games.chq.model.places.Development;
import kevw.games.chq.model.units.Unit;

public class Board extends JComponent implements MouseInputListener, ActionListener, KeyListener {

  public static final double SIZE_FACTOR = 5.0d;
  private static final int ACTION_SELECT_LOCATION = ActionEvent.ACTION_FIRST + 1;
  private static final int ACTION_ZOOM = ActionEvent.ACTION_FIRST + 2;

  private final DevelopmentView developmentView;
  private final UnitView unitView;
  private final JLabel statusLabel;
  private final World world;
  private final Dimension size;
  private final BufferedImage worldMapWithGeography;
  private final BufferedImage worldMapNoGeography;
  private final ArrayList<ActionListener> actionListeners = new ArrayList<>(2);
  private boolean geographyShown = true;

  public Board(JLabel statusLabel) {
    developmentView = new DevelopmentView();
    unitView = new UnitView();
    this.statusLabel = statusLabel;

    try {
      Image image = Toolkit.getDefaultToolkit().getImage(Board.class.getResource("board.png"));
      MediaTracker tracker = new MediaTracker(this);
      tracker.addImage(image, 0);
      tracker.waitForAll();
      world = new World(image);

      size = new Dimension((int) (world.getWidth() * SIZE_FACTOR),
          (int) (world.getHeight() * SIZE_FACTOR));
      setSize(size);
      setMinimumSize(size);
      setMaximumSize(size);
      setPreferredSize(size);

      worldMapNoGeography = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
      worldMapWithGeography = new BufferedImage(size.width, size.height,
          BufferedImage.TYPE_INT_RGB);

      Graphics2D worldMapGraphicsNoGeography = worldMapNoGeography.createGraphics();
      Graphics2D worldMapGraphicsWithGeography = worldMapWithGeography.createGraphics();

      world.getLocations().forEach(currLoc -> {
        final Point point = currLoc.getPoint();

        worldMapGraphicsNoGeography.setColor(currLoc.getColor(false));
        worldMapGraphicsWithGeography.setColor(currLoc.getColor(true));

        Rectangle.Double rect = new Rectangle2D.Double(point.getX() * SIZE_FACTOR,
            point.getY() * SIZE_FACTOR,
            SIZE_FACTOR,
            SIZE_FACTOR);
        worldMapGraphicsNoGeography.fill(rect);
        worldMapGraphicsWithGeography.fill(rect);
      });
    } catch (InterruptedException ex) {
      throw new IllegalStateException(ex);
    }

    addMouseListener(this);
    addMouseMotionListener(this);

    KeyStroke F7 = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);
    registerKeyboardAction(this, "Copy", F7, WHEN_IN_FOCUSED_WINDOW);

    registerKeyboardAction(this, "Toggle Show Geography", KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0),
        WHEN_IN_FOCUSED_WINDOW);
    registerKeyboardAction(this, KeyStroke.getKeyStroke('f', 0), WHEN_IN_FOCUSED_WINDOW);

    KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0);

    this.getInputMap().put(key, key);
    //this.getActionMap().put(key, this);
    addKeyListener(this);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension dim = tk.getBestCursorSize((int) (13 * SIZE_FACTOR), (int) (13 * SIZE_FACTOR));
    BufferedImage crosshairImage = new BufferedImage(dim.width, dim.height,
        BufferedImage.TYPE_INT_ARGB);
    Graphics2D crosshairImageGraphics = crosshairImage.createGraphics();
    Color transparent = new Color(255, 255, 255, 0); //Transparent
    crosshairImageGraphics.setPaint(transparent);
    crosshairImageGraphics.fillRect(0, 0, dim.width, dim.height);

    double thickness = SIZE_FACTOR;
    double halfthickness = Math.floor(SIZE_FACTOR / 2);
    double antithickness = Math.floor(
        Math.min((dim.width - (7 * thickness)) / 2 + (2 * thickness),
            (13 * SIZE_FACTOR / 2) - (SIZE_FACTOR * 1.5f)));

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
    crosshairImageGraphics.setStroke(new BasicStroke((float) SIZE_FACTOR));
    crosshairImageGraphics.draw(top);
    crosshairImageGraphics.draw(bottom);
    crosshairImageGraphics.draw(left);
    crosshairImageGraphics.draw(right);

    Point hotspot = new Point(dim.width / 2, dim.height / 2);
    Cursor crosshairCursor = tk.createCustomCursor(crosshairImage, hotspot, "crosshair");
    setCursor(crosshairCursor);
  }

  public void actionPerformed(ActionEvent e) {
    int i = 0;

    if (Objects.equals(e.getActionCommand(), "Toggle Show Geography")) {
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

  public synchronized void addActionListener(ActionListener l) {
    if (!actionListeners.contains(l)) {
      actionListeners.add(l);
    }
  }

  public void keyPressed(KeyEvent e) {
    e = e;
  }

  public void keyReleased(KeyEvent e) {
    e = e;
  }

  public void keyTyped(KeyEvent e) {
    e = e;
  }

  public void mouseClicked(MouseEvent e) {
    if (e != null) {
      int modifiers = e.getModifiers();

      if ((modifiers & MouseEvent.BUTTON1_MASK) != 0) { //Left click
        fireActionPerformed(
            new ActionEvent(e.getSource(), ACTION_SELECT_LOCATION, "Select location"));
      } else if ((modifiers & MouseEvent.BUTTON2_MASK) != 0) {
      } else if ((modifiers & MouseEvent.BUTTON3_MASK) != 0) { //Right click
        fireActionPerformed(new ActionEvent(e.getSource(), ACTION_ZOOM, "Zoom"));
      }
    }
  }

  public void mouseDragged(MouseEvent e) {
    e = e;
  }

  public void mouseEntered(MouseEvent e) {
    e = e;
  }

  public void mouseExited(MouseEvent e) {
    e = e;
  }

  public void mouseMoved(MouseEvent e) {
    e = e;
    Point p = e.getPoint();
    if (statusLabel != null) {
      int i, j;
      i = (int) (p.x / SIZE_FACTOR);
      j = (int) (p.y / SIZE_FACTOR);
      Location currLoc = world.getLocationAt(i, j);
      if (currLoc != null) {
        statusLabel.setText("(" + i + "," + j + ") " + currLoc.getTerrainTypeString());
      }
    }
  }

  public void mousePressed(MouseEvent e) {
    e = e;
  }

  public void mouseReleased(MouseEvent e) {
    e = e;
  }

  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    if (geographyShown) {
      // Draws the buffered image to the screen.
      g2.drawImage(worldMapWithGeography, 0, 0, this);
    } else {
      // Draws the buffered image to the screen.
      g2.drawImage(worldMapNoGeography, 0, 0, this);
    }

    for (Development development : world.getDevelopments()) {
      developmentView.paint(development, g2);
    }

    for (Unit unit : world.getUnits()) {
      unitView.paint(unit, g2);
    }
  }

  public synchronized void removeActionListener(ActionListener l) {
    actionListeners.remove(l);
  }

  protected void fireActionPerformed(ActionEvent e) {
    List listeners = (List) actionListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((ActionListener) listeners.get(i)).actionPerformed(e);
    }
  }

  protected void paintBorder(Graphics g) {
    // TODO:  Override this javax.swing.JComponent method
    super.paintBorder(g);
  }

  protected void paintChildren(Graphics g) {
    // TODO:  Override this javax.swing.JComponent method
    super.paintChildren(g);
  }

  protected void paintComponent(Graphics g) {
    // TODO:  Override this javax.swing.JComponent method
    super.paintComponent(g);
  }

  public void toggleGeographyShown() {
    geographyShown = !geographyShown;
    repaint();
  }

  public void registerKeyboardAction(ActionListener anAction, String aCommand, KeyStroke aKeyStroke,
      int aCondition) {
    // TODO:  Override this javax.swing.JComponent method
    super.registerKeyboardAction(anAction, aCommand, aKeyStroke, aCondition);
  }

  public void registerKeyboardAction(ActionListener anAction, KeyStroke aKeyStroke,
      int aCondition) {
    // TODO:  Override this javax.swing.JComponent method
    super.registerKeyboardAction(anAction, aKeyStroke, aCondition);
  }

}
