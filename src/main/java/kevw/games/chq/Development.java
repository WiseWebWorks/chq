//package kevw.games.chq;
//import java.util.ArrayList;
//import kevw.games.chq.DevelopmentChangeEvent;
//import kevw.games.chq.DevelopmentChangeListener;
//import java.util.List;
//import java.awt.Color;
//
//public abstract class Development  {
//	/**
//	 * <TODO: Comment for Association here>
//	 *
//	 * @association <kevw.games.chq.DevelopmentLocations> kevw.games.chq.Location
//	 */
//	protected Location locations[];
//	/**
//	 * <TODO: Comment for Association here>
//	 *
//	 * @association <kevw.games.chq.PlayerCities> kevw.games.chq.Player
//	 */
//	protected Player owner;
//	private static transient ArrayList<DevelopmentChangeListener> developmentChangeListeners = new ArrayList<DevelopmentChangeListener>(2);
//
//	public abstract int getOilOuput();
//
//	public abstract int getProductionOutput();
//
//	public Color getOwnerColor() {
//		return Color.blue;
//	}
//
//	public static synchronized void addDevelopmentChangeListener(DevelopmentChangeListener l) {
//		if (!developmentChangeListeners.contains(l)) {
//			developmentChangeListeners.add(l);
//		}
//	}
//
//	public static synchronized void removeDevelopmentChangeListener(DevelopmentChangeListener l) {
//		developmentChangeListeners.remove(l);
//	}
//
//	protected void fireDevelopmentConquered() {
//		List listeners = (List)developmentChangeListeners.clone();
//		int count = listeners.size();
//
//		for (int i = 0;i < count;i++) {
//			((DevelopmentChangeListener)listeners.get(i)).developmentConquered(new DevelopmentChangeEvent(this));
//		}
//	}
//
//	protected void fireDevelopmentBombed() {
//		List listeners = (List)developmentChangeListeners.clone();
//		int count = listeners.size();
//
//		for (int i = 0;i < count;i++) {
//			((DevelopmentChangeListener)listeners.get(i)).developmentBombed(new DevelopmentChangeEvent(this));
//		}
//	}
//
//	protected void fireDevelopmentAided() {
//		List listeners = (List)developmentChangeListeners.clone();
//		int count = listeners.size();
//
//		for (int i = 0;i < count;i++) {
//			((DevelopmentChangeListener)listeners.get(i)).developmentAided(new DevelopmentChangeEvent(this));
//		}
//	}
//
//}
