package kevw.games.chq;
import java.util.Set;
import java.awt.*;

public class Player  {
	private double income;
	private int funds;
	
	/**
	 * <TODO: Comment for Association here>
	 * 
	 * @association <kevw.games.chq.PlayerUnits> kevw.games.chq.units.Unit
	 */
	private Set pieces;
	/**
	 * <TODO: Comment for Association here>
	 * 
	 * @association <kevw.games.chq.PlayerCities> kevw.games.chq.Development
	 */
	protected Set developments;
	City capital;
	Color color;

}