package kevw.games.chq.event;

import java.util.EventListener;

public interface DevelopmentChangeListener extends EventListener {

  void developmentConquered(DevelopmentChangeEvent e);

  void developmentBombed(DevelopmentChangeEvent e);

  void developmentAided(DevelopmentChangeEvent e);

}
