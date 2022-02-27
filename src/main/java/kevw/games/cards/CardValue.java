package kevw.games.cards;

import kevw.util.Enum;


/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public class CardValue extends Enum {
	//~ Constructors -------------------------------------------------------------------------------

	/**
	 * Creates a new CardValue object.
	 *
	 * @param value DOCUMENT ME!
	 */
	protected CardValue(String value) {
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
	public static CardValue getCardValue(int ordinal) {
		return (CardValue) get(ordinal);
	}
}
