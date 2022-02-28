package kevw.games.turns;

import java.nio.channels.SocketChannel;

public class TurnTakingSocketChannel implements RemoteTurnTaker {

  private SocketChannel channel;

  protected TurnTakingSocketChannel(SocketChannel channel) {
    this.channel = channel;
  }

  public void notifyOfTurn(Turn turn) /*throws RemoteException*/ {
  }

  public Turn takeTurn() /*throws RemoteException*/ {
    //channel.socket().setKeepAlive();
    return null;
  }

}
