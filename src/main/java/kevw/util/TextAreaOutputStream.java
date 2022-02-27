package kevw.util;

import java.io.OutputStream;
import javax.swing.JTextArea;


/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 * @version 1.0
 */
public class TextAreaOutputStream extends OutputStream {
	//~ Instance fields ----------------------------------------------------------------------------

	/** DOCUMENT ME! */
	JTextArea mTextArea;

	//~ Constructors -------------------------------------------------------------------------------

	/**
	 * Creates a new TextAreaOutputStream object.
	 *
	 * @param textArea DOCUMENT ME!
	 */
	public TextAreaOutputStream(JTextArea textArea) {
		super();
		mTextArea = textArea;
	}

	//~ Methods ------------------------------------------------------------------------------------

	/**
	 * DOCUMENT ME!
	 *
	 * @param b DOCUMENT ME!
	 */
	public void write(byte[] b) {
		write(b, 0, b.length);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param b DOCUMENT ME!
	 * @param off DOCUMENT ME!
	 * @param len DOCUMENT ME!
	 */
	public void write(byte[] b, int off, int len) {
		mTextArea.append(new String(b, off, len));
		mTextArea.repaint();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param b DOCUMENT ME!
	 */
	public void write(int b) {
		byte[] bArray = { (byte) b };
		write(bArray, 0, bArray.length);
	}
}
