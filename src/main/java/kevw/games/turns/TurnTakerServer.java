package kevw.games.turns;
import java.nio.channels.ServerSocketChannel;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;
import java.io.IOException;
import kevw.games.turns.Turn;
import kevw.games.turns.TurnTakingSocketChannel;

public class TurnTakerServer implements Runnable {
	private ServerSocketChannel serverChannel;
	private int connectionCount;
	private int acceptedCount;
	private TurnTakingSocketChannel acceptedChannels[];
	
	public TurnTakerServer(int connectionCount) {
		this.connectionCount = connectionCount;
		acceptedChannels = new TurnTakingSocketChannel[connectionCount];
	}

	public void run() {
		try {
			serverChannel = ServerSocketChannel.open();
			ServerSocket serverSocket = serverChannel.socket();

			//TODO: get the address to listen to from a config file
			serverSocket.bind(new InetSocketAddress(6555));

			for (acceptedCount = 0; acceptedCount < connectionCount; acceptedCount++) {
				acceptedChannels[acceptedCount] = new TurnTakingSocketChannel(serverChannel.accept());
			}

			Turn currentTurn = null;

			//As long as the game is running
			while (currentTurn != Turn.stopGameTurn) {
				//Take turns
				for (int i = 0; i < acceptedCount; i++) {
					currentTurn = acceptedChannels[i].takeTurn();
					
					for (int j = 0; j < acceptedCount; j++) {
						acceptedChannels[i].notifyOfTurn(currentTurn);
					}

					if (currentTurn == Turn.stopGameTurn)
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}