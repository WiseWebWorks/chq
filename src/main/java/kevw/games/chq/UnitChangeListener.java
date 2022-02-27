package kevw.games.chq;
import java.util.EventListener;

public interface UnitChangeListener extends EventListener  {
	public void unitAdded(UnitChangeEvent e);

	public void unitDied(UnitChangeEvent e);

	public void unitMoved(UnitChangeEvent e);

	public void unitAttacked(UnitChangeEvent e);

	public void unitFortified(UnitChangeEvent e);

	public void unitParadropped(UnitChangeEvent e);

	public void unitPathChanged(UnitChangeEvent e);
}