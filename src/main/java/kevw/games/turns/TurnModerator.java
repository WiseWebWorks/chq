package kevw.games.turns;

public interface TurnModerator {

  void startGame(int turnTakerCount);

  void stopGame();

  void connectTurnTaker(RemoteTurnTaker turnTaker);

  void processNextTurn();

  Object getGameState();

  void takeTurn(Turn turn);

  int getTurnTakerCount();

}
