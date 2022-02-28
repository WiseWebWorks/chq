/*
 * GameMouseEventListener.java
 *
 * Created on June 9, 2005, 6:54 PM
 */

package kevw.games.chq.event;

import java.util.EventListener;

public interface GameMouseEventListener extends EventListener {

  void objectSelected(GameMouseEvent e);

  void objectDropped(GameMouseEvent e);

  void objectHover(GameMouseEvent e);

  void objectMouseover(GameMouseEvent e);

}
