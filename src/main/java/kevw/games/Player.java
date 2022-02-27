package kevw.games;

/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public abstract class Player {
	//~ Static fields/initializers -----------------------------------------------------------------

	/** DOCUMENT ME! */
	protected static int playerNumber = 0;

	//~ Instance fields ----------------------------------------------------------------------------

	/** DOCUMENT ME! */
	private String name;

	//~ Constructors -------------------------------------------------------------------------------

	/**
	 * Creates a new Player object.
	 */
	public Player() {
		name = "Player #" + Integer.toString(playerNumber++);
	}

	/**
	 * Creates a new Player object.
	 *
	 * @param name DOCUMENT ME!
	 */
	public Player(String name) {
		this.name = name;
	}

	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param name DOCUMENT ME!
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public String getName() {
		return name;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public abstract int getScore();
}
