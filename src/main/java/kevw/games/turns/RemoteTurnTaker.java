package kevw.games.turns;

import java.rmi.Remote; 
import java.rmi.RemoteException; 
import kevw.games.turns.Turn;

public interface RemoteTurnTaker extends Remote {
	public Turn takeTurn() throws RemoteException;

	public void notifyOfTurn(Turn turn) throws RemoteException;
}