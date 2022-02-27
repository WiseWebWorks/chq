package kevw.net.http;

/**
 * Title:        Project A- Webserver<p>
 * Description:  A simple web server that implements GET, HEAD and POST.
 * The only status codes it responds properly with are 200, 403, and 404. <p>
 * Copyright:    Copyright (c) Kevin Wise<p>
 * Company:      USC CSCI571<p>
 * @author Kevin Wise
 * @version 1.0
 *
 * ******* This is modified from the java.util.Properties source code.
 */

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Pattern;
import kevw.nio.ChannelReader;

/**
 * This class is an extension of a hashtable that holds all the headers of an HTTP request.
 * This is modified from the java.util.Properties source code, with a lot of borrowing but also a lot of unique code.
 */
public class Headers {
    /** The hashtable that holds the headers. */
	Hashtable<String,Vector<String>> headers;
	
    /** The character that separates keys from values in the input. */
    private static final Pattern keyValuePattern = Pattern.compile(":");

    /**
     * Creates an empty header list.
     */
    public Headers() {
		headers = new Hashtable<String,Vector<String>>();
    }

    /**
     * Reads a header list (name and element pairs) from the input stream.
     * <p>
     * Every header occupies one line of the input stream. Each line
     * is terminated by a line terminator (<code>\n</code> or <code>\r</code>
     * or <code>\r\n</code>). Lines from the input stream are processed until
     * a blank line (containing only whitespace) is reached on the input stream.
     * <p>
     * Every line describes one header to be added to the table.  The name
     * consists of all the characters in the line starting with the first
     * non-whitespace character and up to, but not including, the first ASCII
     * <code>:</code> character.
     * Any whitespace after the name is skipped; if the first non-whitespace
     * character after the name is <code>:</code>, then it
     * is ignored and any whitespace characters after it are also skipped.
     * All remaining characters on the line become part of the associated
     * element string.
     * <p>
     * As an example, each of the following four lines specifies the name
     * <code>"Truth"</code> and the associated element value
     * <code>"Beauty"</code>:
     * <p>
     * <pre>
     * Truth : Beauty
     *	Truth:Beauty
     * Truth			:Beauty
     * </pre>
     * As a second example, the line:
     * <p>
     * <pre>cheeses
     * </pre>
     * specifies that the name is <code>"cheeses"</code> and the associated
     * element is the empty string.<p>
     *
     * @param      in   the input stream.
     * @exception  IOException  if an error occurred when reading from the
     *               input stream.
     */
    public Headers(ChannelReader in) throws IOException {
		this();
		while (true) {
            // Get next line
            CharBuffer line = in.readLine();
            if(line == null || line.length() < 1)
                return;

			String lineSplit[] = keyValuePattern.split(line, 2);
			if (lineSplit.length != 2)
				continue; //WORK-- Invalid header, what to do?

            // Store name and value
            addHeader(lineSplit[0].trim().toLowerCase(), lineSplit[1].trim());
		}
    }

    /**
     * Sets the value of the specified request header. If the request already 
	 * included a header of the specified name, this method adds a new value to
	 * the list of values for the specified header name.
     *
     * @param name		a <code>String</code> specifying the header name
     * @param value		a <code>String</code> specifying the header value
     *
     * @return			an <code>int</code> specifying the number of values
     *					for the requested header after adding a new value
     * @exception	IllegalArgumentException	If the header name or value is 
	 * 					<code>null</code>
     */
    public int addHeader(String name, String value) {
		if (name == null) throw new IllegalArgumentException();
		if (value == null) throw new IllegalArgumentException();

		Vector<String> values = (Vector<String>)headers.get(name);
		if (values == null) {
			values = new Vector<String>(1, 10);
			values.add(value);
			headers.put(name, values);
			return 1;
		} else {
			values.add(value);
			//headers.put(name, newValues);
			return values.size();
		}
		//return 0;
    }

    /**
     *
     * Returns the value of the specified request header
     * as a <code>String</code>. If the request did not include a header
     * of the specified name, this method returns <code>null</code>.
     * If there are multiple headers with the same name, this method
     * returns the first head in the request.
     * The header name is case insensitive. You can use
     * this method with any request header.
     *
     * @param name		a <code>String</code> specifying the
     *				header name
     *
     * @return			a <code>String</code> containing the
     *				value of the requested
     *				header, or <code>null</code>
     *				if the request does not
     *				have a header of that name
	 *              
     * @exception	IllegalArgumentException	If the header name is <code>null</code>
     *
     */			
    public String getHeader(String name) throws IllegalArgumentException {
		if (name == null) throw new IllegalArgumentException();

		Vector<String> values = (Vector<String>)headers.get(name);
		if (values == null || values.size() == 0) return null;
		return values.get(0);
    }

    /**
     *
     * Returns the value of the specified request header
     * as an <code>int</code>. If the request does not have a header
     * of the specified name, this method returns -1. If the
     * header cannot be converted to an integer, this method
     * throws a <code>NumberFormatException</code>.
     *
     * <p>The header name is case insensitive.
     *
     * @param name		a <code>String</code> specifying the name
     *				of a request header
     *
     * @return			an integer expressing the value 
     * 				of the request header or -1
     *				if the request doesn't have a
     *				header of this name
     *
     * @exception	NumberFormatException		If the header value
     *							can't be converted
     *							to an <code>int</code>
     */
    public int getIntHeader(String name) throws IllegalArgumentException, NumberFormatException {
		if (name == null) throw new IllegalArgumentException();

		String value = getHeader(name);
		if (value == null) return -1;

		return Integer.parseInt(value);
    }

    /**
     *
     * Returns the value of the specified request header
     * as a <code>long</code> value that represents a 
     * <code>Date</code> object. Use this method with
     * headers that contain dates, such as
     * <code>If-Modified-Since</code>. 
     *
     * <p>The date is returned as
     * the number of milliseconds since January 1, 1970 GMT.
     * The header name is case insensitive.
     *
     * <p>If the request did not have a header of the
     * specified name, this method returns -1. If the header
     * can't be converted to a date, the method throws
     * an <code>IllegalArgumentException</code>.
     *
     * @param name		a <code>String</code> specifying the
     *				name of the header
     *
     * @return			a <code>long</code> value
     *				representing the date specified
     *				in the header expressed as
     *				the number of milliseconds
     *				since January 1, 1970 GMT,
     *				or -1 if the named header
     *				was not included with the
     *				reqest
     *
     * @exception	IllegalArgumentException	If the header value
     *							can't be converted
     *							to a date
     *
     */
	public long getDateHeader(String name) throws IllegalArgumentException, NumberFormatException {
		if (name == null) throw new IllegalArgumentException();

		String value = getHeader(name);
		if (value == null) return -1;

		return FastHttpDateFormat.parseDate(value, FastHttpDateFormat.formats);
	}
	
    /**
     * Returns an enumeration of all the keys in this header list.
     *
     * @return  an enumeration of all the keys in this header listt.
     * @see     java.util.Enumeration
     * @see     java.util.Headers#defaults
     */
    public Enumeration<String> getHeaderNames() {
	    return headers.keys();
    }

    /**
     * Returns an enumeration of all the headers in this header list.
     *
     * @return  an enumeration of all the keys in this header list, including
     *          the keys in the default header list.
     * @see     java.util.Enumeration
     * @see     java.util.Headers#defaults
     */
    public Enumeration<String> getHeaders(String name) {
		if (name == null) throw new IllegalArgumentException();

		Vector<String> values = (Vector<String>)headers.get(name);
		if (values == null || values.size() == 0) return null;
		return values.elements();
    }

    /**
     * Writes this header list (name and element pairs) in this
     * <code>Headers</code> table to the output stream in a format suitable
     * for loading into a <code>Headers</code> table using the
     * <code>load</code> method.
     * <p>
     * Every entry in this <code>Headers</code> table is written out,
     * one per line. For each entry the name string is written, then an ASCII
     * <code>:</code>, then a space, and then the associated element string.
     * <p>
     * After the entries have been written, the output stream is flushed.  The
     * output stream remains open after this method returns.
     *
     * @param   out      an output stream.
     * @exception  ClassCastException  if this <code>Headers</code> object
     *             contains any keys or values that are not <code>Strings</code>.
     */
    public void store(Writer out, String lineSep) throws IOException {
		String name;
	    for (Enumeration e = getHeaderNames() ; e.hasMoreElements() ;) {
	        name = (String)e.nextElement();
	        out.write(name);
	        out.write(": ");
	        out.write(getHeader(name));
	        out.write(lineSep);
        }
        out.flush();
    }

    /**
     * Prints this header list out to the specified writer.
     * This method is useful for debugging.
     *
     * @param   out an output writer.
     */
    public void list(Writer out) throws IOException {
		String lineSep = System.getProperty("line.separator", "\n");
	    out.write("-- listing Headers --" + lineSep);
	    store(out, lineSep);
    }
}
