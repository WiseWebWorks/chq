package kevw.games.turns;

public interface TurnModerator  {
	public void startGame(int turnTakerCount);

	public void stopGame();

	public void connectTurnTaker(RemoteTurnTaker turnTaker);

	public void processNextTurn();

	public Object getGameState();
	
	public void takeTurn(Turn turn);

	public int getTurnTakerCount();
}