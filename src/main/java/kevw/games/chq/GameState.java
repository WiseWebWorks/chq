package kevw.games.chq;

import java.util.ArrayList;
import java.util.List;

public class GameState {

  private Player players[];
  private ArrayList<GameStateListener> gameStateListeners = new ArrayList<GameStateListener>(2);
  private ArrayList<TurnListener> gameClockListeners = new ArrayList<TurnListener>(2);

  public GameState() {
  }

  public Player[] getPlayers() {
    return players;
  }

  public void setPlayers(Player newPlayers[]) {
    players = newPlayers;
  }

  public synchronized void addGameStateListener(GameStateListener l) {
    if (!gameStateListeners.contains(l)) {
      gameStateListeners.add(l);
    }
  }

  public synchronized void removeGameStateListener(GameStateListener l) {
    gameStateListeners.remove(l);
  }

  protected void fireGameStarted(GameStateEvent e) {
    List listeners = (List) gameStateListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((GameStateListener) listeners.get(i)).gameStarted(e);
    }
  }

  protected void fireGameWon(GameStateEvent e) {
    List listeners = (List) gameStateListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((GameStateListener) listeners.get(i)).gameWon(e);
    }
  }

  protected void fireGameCancelled(GameStateEvent e) {
    List listeners = (List) gameStateListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((GameStateListener) listeners.get(i)).gameCancelled(e);
    }
  }

  protected void fireGamePaused(GameStateEvent e) {
    List listeners = (List) gameStateListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((GameStateListener) listeners.get(i)).gamePaused(e);
    }
  }

  protected void fireGameResumed(GameStateEvent e) {
    List listeners = (List) gameStateListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((GameStateListener) listeners.get(i)).gameResumed(e);
    }
  }

  protected void fireGameSaved(GameStateEvent e) {
    List listeners = (List) gameStateListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((GameStateListener) listeners.get(i)).gameSaved(e);
    }
  }

  protected void fireGameLoaded(GameStateEvent e) {
    List listeners = (List) gameStateListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((GameStateListener) listeners.get(i)).gameLoaded(e);
    }
  }

  protected void fireGameClockTicked() {
    List listeners = (List) gameStateListeners.clone();
    int count = listeners.size();

    for (int i = 0; i < count; i++) {
      ((TurnListener) listeners.get(i)).takeTurn();
    }
  }

  public synchronized void addGameClockListener(TurnListener l) {
    if (!gameClockListeners.contains(l)) {
      gameClockListeners.add(l);
    }
  }

  public synchronized void removeGameClockListener(TurnListener l) {
    gameClockListeners.remove(l);
  }

}
