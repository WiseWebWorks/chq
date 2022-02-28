package kevw.games.turns;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Turn implements Serializable {

  private int turnType;

  static final int TURN_TYPE_NO_TURN = 0;
  static final int TURN_TYPE_MOVE_TO = 1;
  static final int TURN_TYPE_UNSET = -1;

  static final public Turn stopGameTurn = new Turn(TURN_TYPE_NO_TURN);

  public Turn() {
    this.turnType = TURN_TYPE_UNSET;
  }

  private Turn(int turnType) {
    this.turnType = turnType;
  }

  public static Turn createMoveToTurn(int x, int y) {
    return new Turn(TURN_TYPE_MOVE_TO);
  }

  public boolean isNoTurn() {
    return turnType == TURN_TYPE_NO_TURN;
  }

  private void writeObject(ObjectOutputStream out) throws IOException {
    //TODO:
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    //TODO:
  }

  public int getTurnType() {
    return turnType;
  }

  public void setTurnType(int newTurnType) {
    turnType = newTurnType;
  }

}
