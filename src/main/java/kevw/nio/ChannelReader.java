package kevw.nio;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;


/**
 * Class for reading char streams from Channels.  Based on java.io.Reader and java.io.DataInput.
 *
 * @author Kevin B Wise
 * @version 1.0, 09/17/2003
 */
public class ChannelReader extends Reader {
	/** The associated ReadableByteChannel */
	private ReadableByteChannel channel;

	/** The Charset used to convert this Channel's bytes to chars */
	private Charset charset;

	/** The CharsetDecoder used to convert this Channel's bytes to chars */
	private CharsetDecoder decoder;

	/** The input buffer associated with this Reader. */
	private ByteBuffer buffer = null;

	/** The output buffer associated with this Reader. */
	private CharBuffer charBuffer = null;

	/** Whether or not to allocate direct buffers. */
	private boolean allocateDirect;

	/** Whether this Reader is closed or not. */
	private boolean closed;

	/** Whether or not we have reached end of stream */
	private boolean endOfStream;

	/** The line separator sequence for this Reader. */
	private String linesep;

	/** The length of the line separator sequence for this Reader. */
	private int linesepLength;

	/** The first char of the line separator sequence for this Reader. */
	private char linesep1;

	/** The second char of the line separator sequence for this Reader (if there is one). */
	private char linesep2;

	/**
	 * Create a new character-stream ReadableByteChannel reader whose critical sections will
	 * synchronize on the reader itself, using the system-default Charset and with a direct buffer
	 * of size 8192.
	 *
	 * @param channel The ReadableByteChannel from which char data will be read.
	 */
	public ChannelReader(ReadableByteChannel channel) {
		this(channel, null, 8192);
	}

	/**
	 * Create a new character-stream ReadableByteChannel reader whose critical sections will
	 * synchronize on the reader itself, and with a direct buffer of size bufferSize.
	 *
	 * @param channel The ReadableByteChannel from which char data will be read.
	 * @param charset The Charset that will be used to convert bytes.
	 * @param bufferSize The size (in bytes) of the buffer for this Reader.
	 */
	public ChannelReader(ReadableByteChannel channel, Charset charset, int bufferSize) {
		this(channel, charset, bufferSize, true, null, null);
	}

	/**
	 * Create a new character-stream reader whose critical sections will synchronize on the given
	 * object. For efficiency, a character-stream object may use an object other than itself to
	 * protect critical sections.  A subclass should therefore use the <tt>lock</tt>object rather
	 * than <tt>this</tt> or a synchronized method.
	 *
	 * @param channel The ReadableByteChannel from which char data will be read.
	 * @param charset The Charset that will be used to convert bytes.
	 * @param bufferSize The size (in bytes) of the buffer for this Reader.
	 * @param allocateDirect Whether or not to allocate a direct buffer.
	 * @param lock The Object on which to synchronize.  Pass in null to synchronize on
	 * 		  <tt>this</tt>.
	 * @param linesep The line separator that will be used for <tt>readLine()</tt>.  If null, the
	 * 		  system default is used.
	 */
	public ChannelReader(ReadableByteChannel channel, Charset charset, int bufferSize, boolean allocateDirect, Object lock, String linesep) {
		if (lock == null) {
			this.lock = this;
		} else {
			this.lock = lock;
		}

		if (charset == null) {
			this.charset = Charset.forName(new OutputStreamWriter(System.out).getEncoding());
		} else {
			this.charset = charset;
		}

		if (channel == null)
			throw new IllegalArgumentException("channel can not be null");

		this.channel = channel;
		this.decoder = charset.newDecoder();
		decoder.onMalformedInput(CodingErrorAction.REPLACE);
		decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
		this.allocateDirect = allocateDirect;

		//Allocate input buffer
		if (allocateDirect) {
			buffer = ByteBuffer.allocateDirect(bufferSize);
		} else {
			buffer = ByteBuffer.allocate(bufferSize);
		}

		buffer.clear();
		buffer.limit(0);
		buffer.mark();

		//Start with an output buffer of equal size to the input buffer
		createNewCharBuffer(bufferSize);

		setLineSeparator(linesep);
		closed = false;
		endOfStream = false;
	}

	/**
	 * Skip characters.  This method will block until some characters are available, an I/O error
	 * occurs, or the end of the stream is reached.
	 *
	 * @param n The number of characters to skip
	 *
	 * @return The number of characters actually skipped
	 *
	 * @exception IllegalArgumentException If <code>n</code> is negative.
	 * @exception IOException If an I/O error occurs
	 */
	public int skip(int n) throws IOException {
		if (n < 0L)
			throw new IllegalArgumentException("skip value is negative");

		synchronized (lock) {
			if (closed)
				throw new ReaderClosedException();

			int actualRemaining = ensureRemaining(n);
			n = actualRemaining > n ? n : actualRemaining;
			charBuffer.position(charBuffer.position() + n);

			return n;
		}
	}

	/**
	 * Skip characters.  This method will block until some characters are available, an I/O error
	 * occurs, or the end of the stream is reached.
	 *
	 * @param n The number of characters to skip
	 *
	 * @return The number of characters actually skipped
	 *
	 * @exception IllegalArgumentException If <code>n</code> is negative or greater than Integer.MAX_VALUE.
	 * @exception IOException If an I/O error occurs
	 */
	public long skip(long n) throws IOException {
		if (n != ((long) (int) n))
			throw new IllegalArgumentException("Tried to skip too many characters");

		return (long) skip((int) n);
	}

	/**
	 * Reset the stream.  If the stream has been marked, then attempt to reposition it at the mark.
	 * If the stream has not been marked, then attempt to reset it in some way appropriate to the
	 * particular stream, for example by repositioning it to its starting point.  Not all
	 * char-input streams support the reset() operation, and some support reset() without
	 * supporting mark().
	 *
	 * @exception IOException If the stream has not been marked, or if the mark has been
	 * 			  invalidated, or if the stream does not support reset(), or if some other I/O
	 * 			  error occurs
	 */
	public void reset() throws IOException {
		synchronized (lock) {
			if (closed)
				throw new ReaderClosedException();

			charBuffer.reset();
		}
	}

	/**
	 * Tell whether this stream is ready to be read.
	 *
	 * @return True if the next read() is guaranteed not to block for input, false otherwise.  Note
	 * 		   that returning false does not guarantee that the next read will block.
	 *
	 * @exception IOException If an I/O error occurs
	 */
	public boolean ready() throws IOException {
		synchronized (lock) {
			if (closed)
				throw new ReaderClosedException();

			return charBuffer.remaining() > 1;
		}
	}

	/**
	 * Read characters into a portion of an array.  This method will block until some input is
	 * available, an I/O error occurs, or the end of the stream is reached.  This method transfers
	 * characters from this buffer into the given destination array. If there are fewer characters
	 * remaining in the buffer than are required to satisfy the request, that is, if length >
	 * remaining(), then the return value will reflect how many characters were transferred.
	 *
	 * @param cbuf Destination buffer
	 * @param offset Offset at which to start storing characters
	 * @param length Maximum number of characters to read
	 *
	 * @return DOCUMENT ME!
	 *
	 * @exception IOException If an I/O error occurs
	 */
	public int read(char[] cbuf, int offset, int length) throws IOException {
		synchronized (lock) {
			if (closed)
				throw new ReaderClosedException();

			int remaining = ensureRemaining(length);

			if (remaining < 1 && endOfStream)
				return -1;

			length = remaining > length ? length : remaining;

			charBuffer.get(cbuf, offset, length);

			return length;
		}
	}

	/**
	 * Read characters into an array.  This method will block until some input is available, an I/O
	 * error occurs, or the end of the stream is reached.
	 *
	 * @param cbuf Destination buffer
	 *
	 * @return The number of characters read, or -1  if the end of the stream has been reached
	 *
	 * @exception IOException If an I/O error occurs
	 */
	public int read(char[] cbuf) throws IOException {
		return read(cbuf, 0, cbuf.length);
	}

	/**
	 * Read a single char.  This method will block until a char is available, an I/O error occurs,
	 * or the end of the stream is reached.
	 * 
	 * <p>
	 * Subclasses that intend to support efficient single-char input should override this method.
	 * </p>
	 *
	 * @return The char read, as an integer in the range 0 to 65535 (<tt>0x00-0xffff</tt>), or -1
	 * 		   if the end of the stream has been reached
	 *
	 * @exception IOException If an I/O error occurs
	 */
	public int read() throws IOException {
		synchronized (lock) {
			if (closed)
				throw new ReaderClosedException();

			int i = ensureRemaining(1);

			if (i < 1)
				return -1;

			return charBuffer.get();
		}
	}

	/**
	 * Tell whether this stream supports the mark() operation. The default implementation always
	 * returns false. Subclasses should override this method.
	 *
	 * @return true if and only if this stream supports the mark operation.
	 */
	public boolean markSupported() {
		return true;
	}

	/**
	 * Mark the present position in the stream.  Subsequent calls to reset() will attempt to
	 * reposition the stream to this point.  Not all char-input streams support the mark()
	 * operation.
	 *
	 * @param readAheadLimit Limit on the number of characters that may be read while still
	 * 		  preserving the mark.  After reading this many characters, attempting to reset the
	 * 		  stream may fail.
	 *
	 * @exception IOException If the stream does not support mark(), or if some other I/O error
	 * 			  occurs
	 */
	public void mark(int readAheadLimit) throws IOException {
		synchronized (lock) {
			if (closed)
				throw new ReaderClosedException();

			if (readAheadLimit > 0)
				ensureRemaining(readAheadLimit);

			charBuffer.mark();
		}
	}

	/**
	 * Close the stream.  Once a stream has been closed, further read(), readLine(), ready(),
	 * mark(), or reset() invocations will throw an IOException. Closing a previously-closed
	 * stream, however, has no effect.
	 *
	 * @exception IOException If an I/O error occurs
	 */
	public void close() throws IOException {
		synchronized (lock) {
			closed = true;

			//WORK-- do I really want to do this?
			//Apparently not, it throws an exception
			//decoder.flush(charBuffer);
			buffer = null;
			charBuffer = null;
		}
	}

	/**
	 * Returns true if this Reader is closed.
	 *
	 * @return True if this Reader is closed.
	 */
	public boolean isClosed() {
		return closed;
	}

	/**
	 * Sets the line separator that will be used for <tt>readLine</tt>.
	 *
	 * @param linesep The new line separator.  If null, the system default is used.
	 */
	public void setLineSeparator(String linesep) {
		if (linesep == null) {
			this.linesep = System.getProperty("line.separator");
		} else {
			this.linesep = linesep;
		}

		linesepLength = linesep.length();
		linesep1 = linesep.charAt(0);
		linesep2 = linesepLength > 1 ? linesep.charAt(1) : (char) 0;
	}

	/**
	 * Reads from the current position until the line separator is found.
	 *
	 * @return Returns everything from the former position up to and including the line separator.
	 */
	public CharBuffer readLine() throws IOException {
		StringBuffer testBuff = new StringBuffer();

		synchronized (lock) {
			if (closed)
				throw new ReaderClosedException();

			char currChar;
			boolean foundFirst = false;
			int startingPos = charBuffer.position() - 1;
			int i;

			for (i = 1;; i++) {
				if (charBuffer.remaining() < i) {
					if (ensureRemaining(i) < i) { //This will most likely get more than just one more char

						//That's all the characters we can get right now
						if (endOfStream)
							break; //This is all we're going to get, so go ahead and return it

						//There isn't a line yet, but we might still get one.
						return null;
					}
				}

				currChar = charBuffer.get(startingPos + i);

				if (foundFirst && currChar == linesep2) {
					break; //Found a 2-char linsep
				}

				if (currChar == linesep1) {
					if (linesepLength == 1)
						break; //Found a 1-char linsep

					foundFirst = true;
				} else
					foundFirst = false;

				testBuff.append(currChar);
			}

			//Get the buffer representing the line
			int charsToGet = i - linesepLength;
			int remaining = charBuffer.remaining() - linesepLength;

			if (charsToGet < 0)
				charsToGet = 0;

			if (remaining < 0)
				remaining = 0;

			CharBuffer retBuffer = (CharBuffer) charBuffer.subSequence(0, charsToGet > remaining ? remaining : charsToGet);

			//Move to the position after the line
			int newPosition = startingPos + i + 1;
			int limit = charBuffer.limit();
			charBuffer.position(newPosition > limit ? limit : newPosition);

			return retBuffer;
		}
	}

	/**
	 * Allocates a new character buffer.  If there is already a character buffer allocated, this
	 * allocates a new one and copies the remaining contents of the old to the new. For this
	 * reason it should be used sparingly.
	 *
	 * @param size The number of characters that the new buffer should hold.  This is rounded up to
	 * 		  the next multiple of 16.
	 */
	private void createNewCharBuffer(int size) {
		int newBufferSize = ((int) Math.ceil(size / 16)) * 16; //The next multiple of 16
		CharBuffer newCharBuffer;

		assert (charBuffer == null || newBufferSize > charBuffer.capacity()) : "New buffer must be larger than the old.";

		if (allocateDirect) {
			//Notice, since size for direct buffers is in bytes, we have to multiply by 2 to get the right size in chars
			newCharBuffer = ByteBuffer.allocateDirect(size * 2).asCharBuffer();
		} else {
			newCharBuffer = CharBuffer.allocate(size);
		}

		newCharBuffer.clear();
		newCharBuffer.limit(0);
		newCharBuffer.mark();

		//Check if already allocated a buffer
		if (charBuffer != null) {
			//Copy remaining contents of old buffer to new
			char[] temp = new char[charBuffer.remaining()];

			if (temp.length > 0) {
				charBuffer.get(temp);
				newCharBuffer.put(temp);
				newCharBuffer.rewind();
			}
		}

		charBuffer = newCharBuffer;
	}

	/**
	 * Ensures that there are at least <tt>size</tt> characters remaining, if possible. If not
	 * possible, then it returns the number of characters which were made available.
	 *
	 * @param size The number of characters
	 *
	 * @return The lesser of <tt>size</tt> and <tt>charBuffer.remaining()</tt>.
	 *
	 * @throws IOException If there is an error while reading from the channel.
	 */
	private int ensureRemaining(int size) throws IOException {
		synchronized (lock) {
			//Check endOfStream
			if (endOfStream) {
				return -1;
			}

			//Ensure that charBuffer is large enough to hold at least the requested number of characters
			if (size > charBuffer.capacity()) {
				createNewCharBuffer(size);
			}

			//If amount requested is less than what we already have remaining, nothing needed
			int remaining = charBuffer.remaining();

			if (remaining >= size) {
				return size;
			}
			 //else: Requested more than we had, so we must get more

			//Ensure there is enough contiguous space
			if ((charBuffer.capacity() - charBuffer.position() - charBuffer.remaining()) < size) {
				charBuffer.compact();
				charBuffer.limit(charBuffer.position());
				charBuffer.rewind();
			}

			//Move to limit, where additional data will go
			int markPos = charBuffer.position();

			//not: charBuffer.mark(); because that might interfere with another use of mark
			charBuffer.position(charBuffer.limit());
			charBuffer.limit(charBuffer.capacity());

			//Read enough data to ensure the remaining size
			CoderResult cr;
			int readResult = 0;

			while (true) {
				//Decode as much as possible from buffer to charBuffer
				cr = decoder.decode(buffer, charBuffer, false);

				if ((charBuffer.position() - markPos) >= size)
					break; //We have obtained the desired size

				if (readResult == -1) {
					endOfStream = true;

					break; //End-of-stream reached on the previous loop iteration
				}

				if (cr.isUnderflow()) { //buffer didn't hold enough

					//Read another buffer-worth from the channel
					buffer.clear();
					readResult = channel.read(buffer);

					//WORK-- do I need this?  Is this blocking or non-blocking?
					while (readResult == 0) {
						//WORK-- Sleep??
						readResult = channel.read(buffer);
					}

					buffer.flip();
				} else if (cr.isOverflow()) { //charBuffer wasn't big enough
					assert false : new AssertionError("Laws of physics were violated"); //Shouldn't ever happen
				}
			}

			//Move back where we started from (mark)
			charBuffer.limit(charBuffer.position());
			charBuffer.position(markPos);

			//NOT: charBuffer.reset(); See above comment where markPos was set
			return charBuffer.remaining();
		}
	}
}


/**
 * Thrown when read(), readLine(), ready(), mark(), or reset() are called after calling close().
 */
class ReaderClosedException extends IOException {}
