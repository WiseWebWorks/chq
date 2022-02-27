package kevw.games.cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import kevw.games.cards.Card;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public class CardStack implements Collection<Card>/*, List<Card>*/ {
	//~ Instance fields ----------------------------------------------------------------------------

	/** DOCUMENT ME! */
	protected Vector<Card> cards;

	//~ Constructors -------------------------------------------------------------------------------

	/**
	 * Creates a new Stack object.
	 */
	public CardStack() {
		cards = new Vector<Card>();
	}

	/**
	 * Creates a new CardStack object.
	 *
	 * @param cardArray DOCUMENT ME!
	 */
	public CardStack(Card[] cardArray) {
		cards = new Vector<Card>(Arrays.asList(cardArray));
	}

	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param card DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public boolean add(Card card) {
		return cards.add(card);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param index DOCUMENT ME!
	 * @param card DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public void add(int index, Card card) {
		cards.add(index, card);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param c DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public boolean addAll(Collection<? extends Card> c) {
		return cards.addAll(c);
	}

	/**
	 * DOCUMENT ME!
	 */
	public void clear() {
		cards.clear();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param o DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public boolean contains(Object o) {
		if (!(o instanceof Card))
			return false;

		return cards.contains((Card)o);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param c DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public boolean containsAll(Collection c) {
		return cards.containsAll(c);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param card DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public int indexOf(Object card) {
		return cards.indexOf(card);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param card DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public int lastIndexOf(Object card) {
		return cards.lastIndexOf(card);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param card DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public int lastIndexOf(Object card, int index) {
		return cards.lastIndexOf(card,  index);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public Iterator<Card> iterator() {
		return cards.iterator();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public ListIterator<Card> listIterator() {
		return cards.listIterator();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param index DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public ListIterator<Card> listIterator(int index) {
		return cards.listIterator(index);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param card DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public boolean remove(Card card) {
		return cards.removeElement(card);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param index DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public Card remove(int index) {
		return cards.remove(index);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param o DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public boolean remove(Object o) {
		if (!(o instanceof Card))
			throw new IllegalArgumentException();

		return cards.remove(o);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param c DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public boolean removeAll(Collection c) {
		return cards.removeAll(c);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param c DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public boolean retainAll(Collection c) {
		return cards.retainAll(c);
	}

	/**
	 * DOCUMENT ME!
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public int size() {
		return cards.size();
	}

	/**
	 * DOCUMENT ME!
	 */
	public void sort() {
		Collections.<Card>sort((List<Card>)cards);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param fromIndex DOCUMENT ME!
	 * @param toIndex DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public List<Card> subList(int fromIndex, int toIndex) {
		return cards.subList(fromIndex, toIndex);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public Card takeCard() {
		return (Card) cards.remove(0);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param cardCount DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public CardStack takeCards(int cardCount) {
		Card[] cardArray = new Card[cardCount];

		for (int i = 0; i < cardCount; i++) {
			cardArray[i] = takeCard();
		}

		return new CardStack(cardArray);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param a DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public <T> T[] toArray(T[] a) {
		return cards.toArray(a);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public Object[] toArray() {
		return cards.toArray();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public Card[] toCardArray() {
		return (Card[]) cards.toArray(new Card[0]);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();

		for (int i = 0; i < cards.size(); i++) {
			s.append(i + 1).append(": ").append(cards.get(i).toString()).append("\n");
		}

		return s.toString();
	}
}
