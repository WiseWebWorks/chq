package kevw.games.chq;

import java.util.EventListener;

public interface GameStateListener extends EventListener {

  void gameStarted(GameStateEvent e);

  void gameWon(GameStateEvent e);

  void gameCancelled(GameStateEvent e);

  void gamePaused(GameStateEvent e);

  void gameResumed(GameStateEvent e);

  void gameSaved(GameStateEvent e);

  void gameLoaded(GameStateEvent e);

}
