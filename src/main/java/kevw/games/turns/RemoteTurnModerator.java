package kevw.games.turns;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteTurnModerator implements TurnModerator {

  private HttpURLConnection conn;
  private ObjectInputStream in;
  private ObjectOutputStream out;

  public RemoteTurnModerator(String url) throws MalformedURLException, IOException {
    URL u = new URL(url);
    conn = (HttpURLConnection) u.openConnection();
    conn.setDoInput(true);
    conn.setDoOutput(true);

    out = new ObjectOutputStream(conn.getOutputStream());
  }

  public void startGame(int turnTakerCount) {
    try {
      out.writeUTF("start");
      out.writeInt(turnTakerCount);
      out.flush();
      ObjectInputStream in = getIn();
      boolean result = in.readBoolean();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void stopGame() {
    try {
      out.writeUTF("stop");
      out.flush();
      ObjectInputStream in = getIn();
      boolean result = in.readBoolean();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void connectTurnTaker(RemoteTurnTaker turnTaker) {
  }

  public void processNextTurn() {
  }

  public Object getGameState() {
    return null;
  }

  public void takeTurn(Turn turn) {
  }

  public int getTurnTakerCount() {
    return 0;
  }

  private ObjectInputStream getIn() throws IOException {
    if (in == null) {
      in = new ObjectInputStream(conn.getInputStream());
    }
    return in;
  }

}
