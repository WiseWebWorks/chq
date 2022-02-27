package kevw.util;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public abstract class Enum implements Comparable, Serializable {
	//~ Static fields/initializers -----------------------------------------------------------------

	//Ordinal of next enum value to be created

	/** DOCUMENT ME! */
	protected static int nextOrdinal = 0;

	/** DOCUMENT ME! */
	private static final Enum[] PRIVATE_VALUES = new Enum[0];

	//~ Instance fields ----------------------------------------------------------------------------

	/** DOCUMENT ME! */
	protected String value;

	//Assign an ordinal to this enum value

	/** DOCUMENT ME! */
	protected final int ordinal = nextOrdinal++;

	//~ Constructors -------------------------------------------------------------------------------

	//protected static List VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
	protected Enum(String value) {
		this.value = value;
	}

	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static List getPrivateValues() {
		return Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public String getValue() {
		return value;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param o DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public int compareTo(Object o) {
		return ordinal - ((Enum) o).ordinal;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param ordinal DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static Enum get(int ordinal) {
		return PRIVATE_VALUES[ordinal];
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public String toString() {
		return value;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 *
	 * @throws ObjectStreamException DOCUMENT ME!
	 */
	private Object readResolve() throws ObjectStreamException {
		return PRIVATE_VALUES[ordinal]; //Canonicalize
	}
}
