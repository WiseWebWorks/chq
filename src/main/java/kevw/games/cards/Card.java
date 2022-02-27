package kevw.games.cards;

import kevw.games.Player;


/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public abstract class Card implements Comparable {
	//~ Instance fields ----------------------------------------------------------------------------

	/** DOCUMENT ME! */
	protected CardValue value;

	/** DOCUMENT ME! */
	protected Player owner;

	/** DOCUMENT ME! */
	protected Suit suit;

	//~ Constructors -------------------------------------------------------------------------------

	/**
	 * Creates a new Card object.
	 *
	 * @param suit DOCUMENT ME!
	 * @param value DOCUMENT ME!
	 */
	public Card(Suit suit, CardValue value) {
		this.suit = suit;
		this.value = value;
	}

	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public abstract boolean isFaceUp();

	/**
	 * DOCUMENT ME!
	 *
	 * @param owner DOCUMENT ME!
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public abstract int getPoints();

	/**
	 * DOCUMENT ME!
	 *
	 * @param newSuit DOCUMENT ME!
	 */
	public void setSuit(Suit newSuit) {
		suit = newSuit;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public String getSuitString() {
		return suit.getValue();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param newvalue DOCUMENT ME!
	 */
	public void setValue(CardValue newvalue) {
		value = newvalue;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public CardValue getValue() {
		return value;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public String getValueString() {
		return value.getValue();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param otherCard DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public abstract boolean beats(Card otherCard);

	/**
	 * DOCUMENT ME!
	 *
	 * @param otherCard DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public abstract boolean canBePlayedOn(Card otherCard);

	/**
	 * DOCUMENT ME!
	 *
	 * @param o DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public abstract int compareTo(Object o);

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public String toString() {
		return (getValueString() + " of " + getSuitString() + ", worth " + getPoints() + " points");
	}
}
