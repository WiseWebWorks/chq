/*
 * GameMouseEvent.java
 *
 * Created on June 9, 2005, 6:31 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package kevw.games.chq;

import java.awt.Component;
import java.util.EventObject;
import kevw.games.chq.units.Unit;

/**
 *
 * @author p28369
 */
public class GameMouseEvent extends java.awt.event.MouseEvent  {
	public GameMouseEvent(Component source, int id, long when, int modifiers, int x, int y, int clickCount, boolean popupTrigger, int button, Location location) {
		super(source, id, when, modifiers, x, y, clickCount, popupTrigger, button);
		this.location = location;
	}

	public GameMouseEvent(Component source, int id, long when, int modifiers, int x, int y, int clickCount, boolean popupTrigger, Location location) {
		super(source, id, when, modifiers, x, y, clickCount, popupTrigger);
		this.location = location;
	}

	/**
	 * Holds value of property location.
	 */
	private Location location;

	/**
	 * Holds value of property unit.
	 */
	private Unit unit;

	/**
	 * Holds value of property development.
	 */
	private CBR development;

	/**
	 * Getter for property location.
	 * @return Value of property location.
	 */
	public Location getLocation() {
		return this.location;
	}

	/**
	 * Getter for property unit.
	 * @return Value of property unit.
	 */
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * Getter for property development.
	 * @return Value of property development.
	 */
	public CBR getDevelopment() {
		return this.development;
	}}