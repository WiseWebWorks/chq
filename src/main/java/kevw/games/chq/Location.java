package kevw.games.chq;

import java.awt.Color;
import java.awt.Point;
import kevw.games.chq.units.Unit;

public class Location {

  private int attritionLevel;
  private int terrainType;
  private Point point;

  /**
   * <TODO: Comment for Association here>
   *
   * @association <kevw.games.chq.BoardLocations> kevw.games.chq.Board
   */
  protected Board board;
  /**
   * <TODO: Comment for Association here>
   *
   * @association <kevw.games.chq.UnitLocation> kevw.games.chq.units.Unit
   */
  protected Unit piece;
  /**
   * <TODO: Comment for Association here>
   *
   * @association <kevw.games.chq.DevelopmentLocations> kevw.games.chq.CBR
   */
  private CBR development;

  public static final int TERRAIN_OCEAN = 1;
  public static final int TERRAIN_FOREST = 2;
  public static final int TERRAIN_ARCTIC = 3;
  public static final int TERRAIN_PLAIN = 4;
  public static final int TERRAIN_DESERT = 5;
  public static final int TERRAIN_JUNGLE = 6;
  public static final int TERRAIN_MOUNTAIN = 7;
  public static final int TERRAIN_DEVELOPED = 8;

  public static final Color COLOR_OCEAN = new Color(84, 252, 252);
  public static final Color COLOR_FOREST = new Color(84, 252, 84);
  public static final Color COLOR_ARCTIC = new Color(252, 252, 252);
  public static final Color COLOR_PLAIN = new Color(0, 168, 0);
  public static final Color COLOR_DESERT = new Color(252, 252, 84);
  public static final Color COLOR_JUNGLE = new Color(0, 168, 168);
  public static final Color COLOR_MOUNTAIN = new Color(168, 168, 168);
  public static final Color COLOR_DEVELOPED = new Color(84, 84, 84);

  public Location(int x, int y, int terrainType) throws IllegalArgumentException {
    this.point = new Point(x, y);
    switch (terrainType) {
      case TERRAIN_OCEAN:
      case TERRAIN_FOREST:
      case TERRAIN_ARCTIC:
      case TERRAIN_PLAIN:
      case TERRAIN_DESERT:
      case TERRAIN_JUNGLE:
      case TERRAIN_MOUNTAIN:
      case TERRAIN_DEVELOPED:
        break;
      default:
        throw new IllegalArgumentException("Unknown terrain type:" + terrainType);
    }
    this.terrainType = terrainType;
  }

  public Location(int x, int y, Color color) {
    this(x, y, getTerrainTypeFromColor(color));
  }

  public Color getColor(boolean geographyShown) {
    if (geographyShown) {
      return getColorFromTerrainType(terrainType);
    } else {
      return getOwnerColor();
    }
  }

  private Color getOwnerColor() {
    if (terrainType == Location.TERRAIN_OCEAN) {
      return COLOR_OCEAN;
    } else {
      if (development == null) {
        return COLOR_ARCTIC;
      } else {
        return development.getOwnerColor();
      }
    }
  }

  public static int getTerrainTypeFromColor(Color color) {
    if (color != null) {
      if (color.equals(COLOR_OCEAN)) {
        return TERRAIN_OCEAN;
      } else if (color.equals(COLOR_FOREST)) {
        return TERRAIN_FOREST;
      } else if (color.equals(COLOR_ARCTIC)) {
        return TERRAIN_ARCTIC;
      } else if (color.equals(COLOR_PLAIN)) {
        return TERRAIN_PLAIN;
      } else if (color.equals(COLOR_DESERT)) {
        return TERRAIN_DESERT;
      } else if (color.equals(COLOR_JUNGLE)) {
        return TERRAIN_JUNGLE;
      } else if (color.equals(COLOR_MOUNTAIN)) {
        return TERRAIN_MOUNTAIN;
      } else if (color.equals(COLOR_DEVELOPED)) {
        return TERRAIN_DEVELOPED;
      }
    }
    return 0;
  }

  public static Color getColorFromTerrainType(int terrainType) {
    switch (terrainType) {
      case TERRAIN_OCEAN:
        return COLOR_OCEAN;
      case TERRAIN_FOREST:
        return COLOR_FOREST;
      case TERRAIN_ARCTIC:
        return COLOR_ARCTIC;
      case TERRAIN_PLAIN:
        return COLOR_PLAIN;
      case TERRAIN_DESERT:
        return COLOR_DESERT;
      case TERRAIN_JUNGLE:
        return COLOR_JUNGLE;
      case TERRAIN_MOUNTAIN:
        return COLOR_MOUNTAIN;
      case TERRAIN_DEVELOPED:
        return COLOR_DEVELOPED;
      default:
        return null;
    }
  }

  public static String getTerrainTypeString(int terrainType) {
    switch (terrainType) {
      case TERRAIN_OCEAN:
        return "OCEAN";
      case TERRAIN_FOREST:
        return "FOREST";
      case TERRAIN_ARCTIC:
        return "ARCTIC";
      case TERRAIN_PLAIN:
        return "PLAIN";
      case TERRAIN_DESERT:
        return "DESERT";
      case TERRAIN_JUNGLE:
        return "JUNGLE";
      case TERRAIN_MOUNTAIN:
        return "MOUNTAIN";
      case TERRAIN_DEVELOPED:
        return "DEVELOPED";
      default:
        return "";
    }
  }

  public double distance(Location otherLoc) {
    return point.distance(otherLoc.point);
  }

  public void setDevelopment(CBR development) {
    this.development = development;
  }

  public CBR getDevelopment() {
    return development;
  }

  public Point getPoint() {
    return point;
  }

  public int getTerrainType() {
    return terrainType;
  }

  public String getTerrainTypeString() {
    return Location.getTerrainTypeString(terrainType);
  }

}
