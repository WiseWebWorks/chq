package kevw.net.http;

/**
 * Title:        Project A- Webserver<p>
 * Description:  A simple web server that implements GET, HEAD and POST.
 * The only status codes it responds properly with are 200, 403, and 404. <p>
 * Copyright:    Copyright (c) Kevin Wise<p>
 * Company:      USC CSCI571<p>
 * @author Kevin Wise
 * @version 1.0
 */

/**
 * This class holds all the constants for HTTP.  The class consists of bunches of static variables.
 */
public final class HTTP {
    /** An array of HTTP method type strings. */
    public static final String[] requestTypeString = {"GET", "HEAD", "POST", "OPTIONS", "PUT", "DELETE", "TRACE", "UNKNOWN"};
    /** The constant index into <code>requestTypeString</code> for an HTTP <B>GET</B> request. */
    public static final int GET = 0;
    /** The constant index into <code>requestTypeString</code> for an HTTP <B>HEAD</B> request. */
    public static final int HEAD = 1;
    /** The constant index into <code>requestTypeString</code> for an HTTP <B>POST</B> request. */
    public static final int POST = 2;
    /** The constant index into <code>requestTypeString</code> for an HTTP <B>OPTIONS</B> request. */
    public static final int OPTIONS = 3;
    /** The constant index into <code>requestTypeString</code> for an HTTP <B>PUT</B> request. */
    public static final int PUT = 4;
    /** The constant index into <code>requestTypeString</code> for an HTTP <B>DELETE</B> request. */
    public static final int DELETE = 5;
    /** The constant index into <code>requestTypeString</code> for an HTTP <B>TRACE</B> request. */
    public static final int TRACE = 6;
    /** The constant index into <code>requestTypeString</code> for an HTTP <B>UNKNOWN</B> request. */
    public static final int UNKNOWN = 7;

    /** An array of HTTP header strings (UNUSED). */
    public static final String[] header = {
        "Accept",               "Accept-Charset",   "Accept-Encoding",      "Accept-Language",      "Accept-Ranges",
        "Age",                  "Allow",            "Authorization",        "Cache-Control",        "Connection",
        "Content-Encoding",     "Content-Language", "Content-Length",       "Content-Location",     "Content-MD5",
        "Content-Range",        "Content-Type",     "Date",                 "ETag",                 "Expect",
        "Expires",              "From",             "Host",                 "If-Match",             "If-Modified-Since",
        "If-None-Match",        "If-Range",         "If-Unmodified-Since",  "Last-Modified",        "Location",
        "Max-Forwards",         "Pragma",           "Proxy-Authenticate",   "Proxy-Authorization",  "Range",
        "Referer",              "Retry-After",      "Server",               "TE",                   "Trailer",
        "Transfer-Encoding",    "Upgrade",          "User-Agent",           "Vary",                 "Via",
        "Warning",              "WWW-Authenticate"
    };

    /** An array of HTTP status codes values. */
    public static final int[] statusCode = {100,101,200,201,202,203,204,205,206,300,301,302,303,304,305,306,307,400,401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417,500,501,502,503,504,505};
    /** An array of HTTP status codes strings. */
    public static final String[] statusString = {
        "Continue" /*100*/,                         "Switching Protocols" /*101*/,  "OK" /*200*/,
        "Created" /*201*/,                          "Accepted" /*202*/,             "Non-Authoritative information" /*203*/,
        "No Content" /*204*/,                       "Reset Content" /*205*/,        "Partial Content" /*206*/,
        "Multiple Choices" /*300*/,                 "Moved Permanently" /*301*/,    "Found" /*302*/,
        "See Other" /*303*/,                        "Not Modified" /*304*/,         "Use Proxy" /*305*/,
        "Unused" /*no 306 def*/,                    "Temporary Redirect" /*307*/,   "Bad Request" /*400*/,
        "Unauthorized" /*401*/,                     "Payment Required" /*402*/,     "Forbidden" /*403*/,
        "Not Found" /*404*/,                        "Method Not Allowed" /*405*/,   "Not Acceptable" /*406*/,
        "Proxy Authentication Required" /*407*/,    "Request Timeout" /*408*/,      "Conflict" /*409*/,
        "Gone" /*410*/,                             "Length Required" /*411*/,      "Precondition Failed" /*412*/,
        "Request Entity Too Large" /*413*/,         "Request-URI Too Long" /*414*/, "Unsupported Media Type" /*415*/,
        "Requested Range Not Satisfiable" /*416*/,  "Expectation Failed" /*417*/,   "Internal Server Error" /*500*/,
        "Not Implemented" /*501*/,                  "Bad Gateway" /*502*/,          "Service Unavailable" /*503*/,
        "Gateway Timeout" /*504*/,                  "HTTP Version Not Supported" /*505*/
    };
    //We are only implementing 200, 403, and 404.  This gives the index into the arrays above.
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>CONTINUE</B> status. */
    public static final int CONTINUE = 0;                       /*100*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>SWITCHING_PROTOCOLS</B> status. */
    public static final int SWITCHING_PROTOCOLS = 1;            /*101*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>OK</B> status. */
    public static final int OK = 2;                             /*200*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>CREATED</B> status. */
    public static final int CREATED = 3;                        /*201*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>ACCEPTED</B> status. */
    public static final int ACCEPTED = 4;                       /*202*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>NONAUTHORITATIVE_INFORMATION</B> status. */
    public static final int NONAUTHORITATIVE_INFORMATION = 5;   /*203*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>NO_CONTENT</B> status. */
    public static final int NO_CONTENT = 6;                     /*204*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>RESET_CONTENT</B> status. */
    public static final int RESET_CONTENT = 7;                  /*205*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>PARTIAL_CONTENT</B> status. */
    public static final int PARTIAL_CONTENT = 8;                /*206*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>MULTIPLE_CHOICES</B> status. */
    public static final int MULTIPLE_CHOICES = 9;               /*300*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>MOVED_PERMANENTLY</B> status. */
    public static final int MOVED_PERMANENTLY = 10;             /*301*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>FOUND</B> status. */
    public static final int FOUND = 11;                         /*302*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>SEE_OTHER</B> status. */
    public static final int SEE_OTHER = 12;                     /*303*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>NOT_MODIFIED</B> status. */
    public static final int NOT_MODIFIED = 13;                  /*304*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>USE_PROXY</B> status. */
    public static final int USE_PROXY = 14;                     /*305*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>UNUSED1</B> status. */
    public static final int UNUSED1 = 15;                       /*306*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>TEMPORARY_ACCESS</B> status. */
    public static final int TEMPORARY_ACCESS = 16;              /*307*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>BAD_REQUEST</B> status. */
    public static final int BAD_REQUEST = 17;                   /*400*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>UNAUTHORIZED</B> status. */
    public static final int UNAUTHORIZED = 18;                  /*401*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>PAYMENT_REQUIRED</B> status. */
    public static final int PAYMENT_REQUIRED = 19;              /*402*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>FORBIDDEN</B> status. */
    public static final int FORBIDDEN = 20;                     /*403*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>NOT_FOUND</B> status. */
    public static final int NOT_FOUND = 21;                     /*404*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>METHOD_NOT_ALLOWED</B> status. */
    public static final int METHOD_NOT_ALLOWED = 22;            /*405*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>NOT_ACCEPTABLE</B> status. */
    public static final int NOT_ACCEPTABLE = 23;                /*406*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>PROXY_AUTH_REQ</B> status. */
    public static final int PROXY_AUTH_REQ = 24;                /*407*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>REQUEST_TIMEOUT</B> status. */
    public static final int REQUEST_TIMEOUT = 25;               /*408*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>CONFLICT</B> status. */
    public static final int CONFLICT = 26;                      /*409*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>GONE</B> status. */
    public static final int GONE = 27;                          /*410*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>LENGTH_REQUIRED</B> status. */
    public static final int LENGTH_REQUIRED = 28;               /*411*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>PRECONDITION_FAILED</B> status. */
    public static final int PRECONDITION_FAILED = 29;           /*412*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>REQUEST_ENTITY_TOO_LARGE</B> status. */
    public static final int REQUEST_ENTITY_TOO_LARGE = 30;      /*413*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>REQUEST_URI_TOO_LARGE</B> status. */
    public static final int REQUEST_URI_TOO_LARGE = 31;         /*414*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>UNSUPPORTED_MEDIA_TYPE</B> status. */
    public static final int UNSUPPORTED_MEDIA_TYPE = 32;        /*415*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>REQUEST_RANGE_NOT_SATISFIABLE</B> status. */
    public static final int REQUEST_RANGE_NOT_SATISFIABLE = 33; /*416*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>EXPECTATION_FAILED</B> status. */
    public static final int EXPECTATION_FAILED = 34;            /*417*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>INTERNAL_SERVER_ERROR</B> status. */
    public static final int INTERNAL_SERVER_ERROR = 35;         /*500*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>NOT_IMPLEMENTED</B> status. */
    public static final int NOT_IMPLEMENTED = 36;               /*501*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>BAD_GATEWAY</B> status. */
    public static final int BAD_GATEWAY = 37;                   /*502*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>SERVICE_UNAVAILABLE</B> status. */
    public static final int SERVICE_UNAVAILABLE = 38;           /*503*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>GATEWAY_TIMEOUT</B> status. */
    public static final int GATEWAY_TIMEOUT = 39;               /*504*/
    /** The constant index into <code>statusCode</code> and <code>statusString</code> for an HTTP <B>HTTP_VERSION_NOT_SUPPORTED</B> status. */
    public static final int HTTP_VERSION_NOT_SUPPORTED = 40;    /*505*/

    /**
     * The do-nothing constructor.
     */
    HTTP() {
    }
}