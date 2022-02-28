package kevw.games;

/**
 * DOCUMENT ME!
 *
 * @author Kevin B Wise
 */
public class GameStateException extends Exception {
  //~ Static fields/initializers -----------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  public static final GameStateException gameAlreadyFinished =
      new GameStateException("Game is already finished");

  /**
   * DOCUMENT ME!
   */
  public static final GameStateException gameAlreadyStarted =
      new GameStateException("Game is already in progress");

  /**
   * DOCUMENT ME!
   */
  public static final GameStateException gameStillInProgress =
      new GameStateException("Game is still in progress");

  /**
   * DOCUMENT ME!
   */
  public static final GameStateException gameNotStarted =
      new GameStateException("Game is not yet started");

  //~ Constructors -------------------------------------------------------------------------------

  /**
   * Creates a new GameStateException object.
   */
  public GameStateException() {
    super();
  }

  /**
   * Creates a new GameStateException object.
   *
   * @param message DOCUMENT ME!
   */
  public GameStateException(String message) {
    super(message);
  }

  /**
   * Creates a new GameStateException object.
   *
   * @param message DOCUMENT ME!
   * @param cause   DOCUMENT ME!
   */
  public GameStateException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new GameStateException object.
   *
   * @param cause DOCUMENT ME!
   */
  public GameStateException(Throwable cause) {
    super(cause);
  }

}
