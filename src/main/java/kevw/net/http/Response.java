package kevw.net.http;

import java.util.Date;

public class Response {
	/** The status of this response. */
	private int status = HTTP.OK;  //Status holds the index into the arrays in HTTP.java, given by the constants specified.
	
	/** The headers to be sent with this response. */
	private Headers headers = new Headers();
	
	/** The HTTP version of the response. */
	private String strHTTPVersion = "";
	
	/** The error description given in the case of an internal server error (500). */
	private String serverErrorCode = "";
	
	/** The content length of the requested resource as it is served back to the client.*/
	private long contentLength;
	
	/** An input buffer that holds the response message body, if any. */
	private StringBuffer messageBody;
	
	/** The mime type of the resource that is requested. */
	private String mimeType = "";
	
	/** Holds the last modified date of the resource, if applicable. */
	private Date fileLastModifiedDate;
	
	public Response(int status, StringBuffer messageBody, String mimeType) {
		this.status = status;
		this.messageBody = messageBody;
		this.mimeType = mimeType;
	}
}