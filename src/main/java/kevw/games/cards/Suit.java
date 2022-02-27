package kevw.games.cards;

import kevw.util.Enum;


/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public abstract class Suit extends Enum {
	//~ Constructors -------------------------------------------------------------------------------

	/**
	 * Creates a new Suit object.
	 *
	 * @param value DOCUMENT ME!
	 */
	protected Suit(String value) {
		super(value);
	}

	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param ordinal DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static Suit getSuit(int ordinal) {
		return (Suit) get(ordinal);
	}
}
