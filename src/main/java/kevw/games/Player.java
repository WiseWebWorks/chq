package kevw.games;

/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public abstract class Player {

  protected static int playerNumber = 0;

  private String name;

  public Player() {
    name = "Player #" + Integer.toString(playerNumber++);
  }

  public Player(String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract int getScore();

}
