/*
 * GameMouseEventListener.java
 *
 * Created on June 9, 2005, 6:54 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package kevw.games.chq;

import java.util.EventListener;

/**
 *
 * @author p28369
 */
public interface GameMouseEventListener extends EventListener {
	public void objectSelected(GameMouseEvent e);
	
	public void objectDropped(GameMouseEvent e);
	
	public void objectHover(GameMouseEvent e);
	
	public void objectMouseover(GameMouseEvent e);
}