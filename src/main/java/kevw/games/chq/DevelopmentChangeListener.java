package kevw.games.chq;

import java.util.EventListener;

public interface DevelopmentChangeListener extends EventListener {

  void developmentConquered(DevelopmentChangeEvent e);

  void developmentBombed(DevelopmentChangeEvent e);

  void developmentAided(DevelopmentChangeEvent e);

}
