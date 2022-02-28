/*
 * World.java
 *
 * Created on June 24, 2005, 12:41 PM
 */
package kevw.games.chq.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import kevw.games.chq.model.places.Base;
import kevw.games.chq.model.places.City;
import kevw.games.chq.model.places.Development;
import kevw.games.chq.model.places.OilField;
import kevw.games.chq.model.units.Tank;
import kevw.games.chq.model.units.Unit;

public class World {

  private static final int MAP_WIDTH = 320;
  private static final int MAP_HEIGHT = 136;
  private static final int NUM_BASE = 12;
  private static final int NUM_RESOURCE = 20;
  private static final int NUM_CITY = 50;
  private static final int NUM_CAPITAL = 2;
  private static final int NUM_CBR = NUM_CAPITAL + NUM_CITY + NUM_BASE + NUM_RESOURCE;
  private static final int NUM_REGIONS = 185;

  private final Image image;
  private final Location[][] locations = new Location[MAP_WIDTH][MAP_HEIGHT];
  private final Development[] developments;
  private final City[] cities;
  private final Base[] bases;
  private final OilField[] resources;
  private final List<Unit> units;

  public World(Image image) {
    this.image = image;
    this.units = new ArrayList<>();

    BufferedImage worldMapData = new BufferedImage(MAP_WIDTH, MAP_HEIGHT,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D worldMapDataGraphics = worldMapData.createGraphics();
    worldMapDataGraphics.drawImage(image, 0, 0, null);
    Raster raster = worldMapData.getData();
    DataBuffer buff = raster.getDataBuffer();
    SampleModel sm = raster.getSampleModel();
    int[] pixelData = new int[3];
    Color pixelColor;

    for (int i = 0; i < MAP_WIDTH; i++) {
      for (int j = 0; j < MAP_HEIGHT; j++) {
        pixelData = sm.getPixel(i, j, pixelData, buff);
        pixelColor = new Color(pixelData[0], pixelData[1], pixelData[2]);

        try {
          locations[i][j] = new Location(i, j, pixelColor);
        } catch (IllegalArgumentException ex) {
          System.out.println(
              "(" + i + "," + j + "):" + pixelData[0] + "," + pixelData[1] + "," + pixelData[2]
                  + ";\t");
        }
      }
    }

    developments = new Development[NUM_REGIONS];
    cities = new City[NUM_CITY];
    bases = new Base[NUM_BASE];
    resources = new OilField[NUM_RESOURCE];
    readRegions();

    units.add(new Tank(locations[48][49], null));
  }

  private int readRegions() {
    try {
      FileInputStream regionFile = new FileInputStream("K:\\Games\\CHQ\\regions.dat");
      DataInputStream ois = new DataInputStream(regionFile);

      for (int i = 0; i < developments.length; i++) {
        short x = ois.readShort();
        short y = ois.readShort();
        Location loc = locations[x][y];
        Development development = new City(new Location[]{loc});
        developments[i] = development;
        loc.setDevelopment(development);

        // x/y coordinates for resource regions
        Polygon regionPoly = new Polygon();
        for (int j = 0; j < 4; j++) {
          int x1 = ois.readShort();
          int y1 = ois.readShort();
          switch (j) {
            case 0:
              x1++;
              break;
            case 1:
              x1++;
              y1++;
              break;
            case 2:
              y1++;
              break;
          }
          regionPoly.addPoint(x1, y1);
        }
        developments[i].setArea(new Area(regionPoly));
        System.out.println(developments[i].toString());
      }
      ois.close();
      regionFile.close();
    } catch (IOException ex) {
      System.err.println("Could not open the data file: regions.dat.\n");
      return 0;
    }

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

  public Unit getUnitAt(int x, int y) {
    return null;
  }

  public int getHeight() {
    return MAP_HEIGHT;
  }

  public int getWidth() {
    return MAP_WIDTH;
  }

  public Stream<Location> getLocations() {
    return Arrays.stream(locations).flatMap(Arrays::stream);
  }

  public Location getLocationAt(int x, int y) {
    return locations[x][y];
  }

  public Development[] getDevelopments() {
    return developments;
  }

  public List<Unit> getUnits() {
    return Collections.unmodifiableList(units);
  }

}
