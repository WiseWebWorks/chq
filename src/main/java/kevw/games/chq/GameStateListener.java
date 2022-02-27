package kevw.games.chq;
import java.util.EventListener;

public interface GameStateListener extends EventListener  {
	public void gameStarted(GameStateEvent e);

	public void gameWon(GameStateEvent e);

	public void gameCancelled(GameStateEvent e);

	public void gamePaused(GameStateEvent e);

	public void gameResumed(GameStateEvent e);

	public void gameSaved(GameStateEvent e);

	public void gameLoaded(GameStateEvent e);
}