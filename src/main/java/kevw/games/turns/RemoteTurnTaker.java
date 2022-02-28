package kevw.games.turns;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTurnTaker extends Remote {

  Turn takeTurn() throws RemoteException;

  void notifyOfTurn(Turn turn) throws RemoteException;

}
