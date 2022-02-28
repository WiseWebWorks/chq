package kevw.games.chq.model;

public class GameState {

  private Player players[];
  private World world;

  public GameState() {
  }

  public Player[] getPlayers() {
    return players;
  }

  public void setPlayers(Player newPlayers[]) {
    players = newPlayers;
  }

}
