package kevw.games.chq;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
/*import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.logger.SystemOutLogger;
import org.apache.catalina.startup.Embedded;*/

public class CHQAppletOld extends JApplet  {

	private BorderLayout borderLayout1 = new BorderLayout();
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JLabel jLabel1 = new JLabel();
	private Board board = new Board();
	//private Embedded tomcatEmbedded;

	public CHQAppletOld() {
	}

	public void init() {
		try {
			jbInit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main2(String args[]) {
		final CHQAppletFrame frame = new CHQAppletFrame();
		frame.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		frame.setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
		frame.setTitle("Command HQ");
		CHQAppletOld applet = new CHQAppletOld();
		applet.init();
		applet.start();

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}
	
	public static void main(String[] args) {
		CHQAppletOld applet = new CHQAppletOld();
		JFrame frame = new CHQAppletFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(applet, BorderLayout.CENTER);
		frame.setTitle("Command HQ");
		applet.init();
		applet.start();
		//frame.setSize(660, 500);
		frame.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		frame.setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	public void start() {
		/*//Instantiate a new instance of this class.
		tomcatEmbedded = new Embedded(new SystemOutLogger(), null);

		//Set the relevant properties of this object itself. In particular, you will want to establish the default Logger
		//to be used, as well as the default Realm if you are using container-managed security.
		tomcatEmbedded.setCatalinaBase("C:\\Apache\\Tomcat 5.0");
		tomcatEmbedded.setCatalinaHome("C:\\Apache\\Tomcat 5.0");
		//tomcatEmbedded.setRealm();
		
		//Call createEngine() to create an Engine object, and then call its property setters as desired.
		Engine engine = tomcatEmbedded.createEngine();
		
		//Call createHost() to create at least one virtual Host associated with the newly created Engine, and then call 
		//its property setters as desired. After you customize this Host, add it to the corresponding Engine with 
		//engine.addChild(host).
		Host host = tomcatEmbedded.createHost("localhost", "webapps");
		try {
			host.addAlias(InetAddress.getLocalHost().getCanonicalHostName());
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		engine.addChild(host);
		
		//Call createContext() to create at least one Context associated with each newly created Host, and then call its
		//property setters as desired. You SHOULD create a Context with a pathname equal to a zero-length string, which 
		//will be used to process all requests not mapped to some other Context. After you customize this Context, add 
		//it to the corresponding Host with host.addChild(context).
		Context context = tomcatEmbedded.createContext("/", "TurnTaker");
		host.addChild(context);
//		context = tomcatEmbedded.createContext("", "TurnTaker");
//		host.addChild(context);
//		context = tomcatEmbedded.createContext("/admin", "../server/webapps/admin");
//		host.addChild(context);

		//Call addEngine() to attach this Engine to the set of defined Engines for this object.
		tomcatEmbedded.addEngine(engine);
		
		//Call createConnector() to create at least one TCP/IP connector, and then call its property setters as desired.
		Connector connector = tomcatEmbedded.createConnector("localhost", 80, "HTTP");
		
		//Call addConnector() to attach this Connector to the set of defined Connectors for this object. The added 
		//Connector will use the most recently added Engine to process its received requests.
		tomcatEmbedded.addConnector(connector);
		
		//Repeat the above series of steps as often as required (although there will typically be only one Engine instance created).
		//Call start() to initiate normal operations of all the attached components.
		try {
			tomcatEmbedded.start();
		} catch (LifecycleException ex) {
			ex.printStackTrace();
		}
		
		//Thread.sleep(60000);
		
		//tomcatEmbedded.stop();*/

	}

	public void stop() {
		/*try {
			tomcatEmbedded.stop();
		} catch (LifecycleException ex) {
			ex.printStackTrace();
		}*/
	}

	public void destroy() {
	}

	private void jbInit() throws Exception {
		Dimension size = new Dimension(640,480);
		this.setSize(size);
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		this.getContentPane().setLayout(borderLayout1);
		//jLabel1.setText("Hello World");
		//jPanel1.add(board, null);
		//this.getContentPane().add(jPanel1, BorderLayout.CENTER);
		//jPanel2.add(jLabel1, null);
		//this.getContentPane().add(jPanel2, BorderLayout.NORTH);
	}

	static   {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch(Exception e) {
		}
	}
}