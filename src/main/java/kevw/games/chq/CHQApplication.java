/*
 * CHQApplication.java
 *
 * Created on June 8, 2005, 11:22 PM
 */

package kevw.games.chq;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import kevw.games.chq.view.CHQApplicationFrame;

public class CHQApplication {

  public static void main(String[] args) {

    javax.swing.SwingUtilities.invokeLater(() -> {
      final JFrame frame = new CHQApplicationFrame();
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = frame.getSize();
      frame.setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
      frame.setVisible(true);
    });
  }

}
