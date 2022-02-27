//package kevw.games.turns;
//import java.io.IOException;
//import sun.net.www.protocol.http.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.ProtocolException;
//
//public class ModeratorConnection extends HttpURLConnection {
//	HttpURLConnection conn;
//
//	static {
//		HttpURLConnection.setFollowRedirects(false);
//		HttpURLConnection.setDefaultAllowUserInteraction(true);
//	}
//
//	public ModeratorConnection(String spec) throws MalformedURLException, ProtocolException, IOException {
//		super(new URL(spec), (sun.net.www.protocol.http.Handler)null);
//
//		setRequestMethod("POST");
//		setDoInput(true);
//		setDoOutput(true);
//		setUseCaches(false);
//	}
//
//	public void connect(String connectString) throws MalformedURLException, ProtocolException, IOException {
//		conn = (HttpURLConnection)(new URL(connectString).openConnection());
//		conn.setRequestMethod("POST");
//		conn.setDoInput(true);
//		conn.setDoOutput(true);
//		conn.setUseCaches(false);
////		conn.setAllowUserInteraction(true);
////		conn.setFollowRedirects(false);
////		conn.setInstanceFollowRedirects(false);
//	}
//
//	public static void main(String argv[]) {
//		try {
//			ModeratorConnection a = new ModeratorConnection("http://www.cnn.com");
//			a.connect("http://www.cnn.com");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	public void disconnect() {
//		// TODO:  Implement this java.net.HttpURLConnection abstract method
//	}
//
//	public void connect() throws IOException {
//		// TODO:  Implement this java.net.URLConnection abstract method
//	}
//}
