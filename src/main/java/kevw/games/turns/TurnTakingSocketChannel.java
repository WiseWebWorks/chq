package kevw.games.turns;

import java.nio.channels.SocketChannel;
import java.rmi.RemoteException;
import kevw.games.turns.Turn;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class TurnTakingSocketChannel implements RemoteTurnTaker {
	//~ Instance fields ----------------------------------------------------------------------------

	/** DOCUMENT ME! */
	private SocketChannel channel;

	//~ Constructors -------------------------------------------------------------------------------

	/**
	 * Creates a new TurnTakingSocketChannel object.
	 *
	 * @param channel DOCUMENT ME!
	 */
	protected TurnTakingSocketChannel(SocketChannel channel) {
		this.channel = channel;
	}

	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param turn DOCUMENT ME!
	 *
	 * @throws RemoteException DOCUMENT ME!
	 */
	public void notifyOfTurn(Turn turn) /*throws RemoteException*/ {
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 *
	 * @throws RemoteException DOCUMENT ME!
	 */
	public Turn takeTurn() /*throws RemoteException*/ {
		//channel.socket().setKeepAlive();
		return null;
	}
}
