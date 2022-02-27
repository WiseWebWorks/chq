package kevw.games.chq;
import java.util.EventListener;

public interface DevelopmentChangeListener extends EventListener  {
	public void developmentConquered(DevelopmentChangeEvent e);

	public void developmentBombed(DevelopmentChangeEvent e);

	public void developmentAided(DevelopmentChangeEvent e);
}