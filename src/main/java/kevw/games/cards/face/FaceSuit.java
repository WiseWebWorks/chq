package kevw.games.cards.face;

import java.util.List;
import kevw.games.cards.Suit;
import kevw.util.Enum;


/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public class FaceSuit extends Suit {
	//~ Static fields/initializers -----------------------------------------------------------------

	/** DOCUMENT ME! */
	public static final FaceSuit CLUBS = new FaceSuit("clubs");

	/** DOCUMENT ME! */
	public static final FaceSuit DIAMONDS = new FaceSuit("diamonds");

	/** DOCUMENT ME! */
	public static final FaceSuit HEARTS = new FaceSuit("hearts");

	/** DOCUMENT ME! */
	public static final FaceSuit SPADES = new FaceSuit("spades");

	/** DOCUMENT ME! */
	private static final Enum[] PRIVATE_VALUES = new Enum[] { CLUBS, DIAMONDS, HEARTS, SPADES };

	//~ Constructors -------------------------------------------------------------------------------

	/**
	 * Creates a new FaceSuit object.
	 *
	 * @param name DOCUMENT ME!
	 */
	private FaceSuit(String name) {
		super(name);
	}
}
