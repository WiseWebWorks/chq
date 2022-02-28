package kevw.games.turns;

import java.rmi.RemoteException;

public class ServerTurnModerator implements TurnModerator {

  private int turnTakerCount;
  private RemoteTurnTaker turnTakers[];
  private int connectedTurnTakerCount;
  private boolean inGame;

  public ServerTurnModerator() {
    inGame = false;
  }

  public void startGame(int turnTakerCount) {
    this.turnTakerCount = turnTakerCount;
    connectedTurnTakerCount = 0;
    turnTakers = new RemoteTurnTaker[turnTakerCount];
  }

  public void stopGame() {
    notifyAllTurnTakers(Turn.stopGameTurn);
    inGame = false;
  }

  public void connectTurnTaker(RemoteTurnTaker turnTaker) {
    if (connectedTurnTakerCount < turnTakerCount) {
      turnTakers[connectedTurnTakerCount++] = turnTaker;
    }
    if (connectedTurnTakerCount == turnTakerCount) {
      inGame = true;
    }
  }

  public void processNextTurn() {
    if (!inGame) {
      return;
    }
    for (int i = 0; i < turnTakers.length; i++) {
      try {
        Turn turn = turnTakers[i].takeTurn();
        if (!turn.isNoTurn()) {
          notifyAllTurnTakers(turn);
        }
      } catch (RemoteException ex) {
        //WORK--
        ex.printStackTrace();
      }
    }
  }

  private void notifyAllTurnTakers(Turn turn) {
    RemoteTurnTaker currTurnTaker;
    for (int i = 0; i < turnTakers.length; i++) {
      currTurnTaker = turnTakers[i];
      try {
        if (currTurnTaker != null) {
          currTurnTaker.notifyOfTurn(turn);
        }
      } catch (RemoteException ex) {
        //WORK--
        ex.printStackTrace();
      }

    }
  }

  public Object getGameState() {
    return null;
  }

  public void takeTurn(Turn turn) {

  }

  public int getTurnTakerCount() {
    return turnTakerCount;
  }

}
