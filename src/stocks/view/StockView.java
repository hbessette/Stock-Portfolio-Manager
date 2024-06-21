package stocks.view;

/**
 * A view that provides some way of showing the user text.
 */
public interface StockView {

  /**
   * Display text to the user.
   *
   * @param input the text to display
   */
  public void show(String input);

  /**
   * Displays some welcome message.
   */
  void welcomeMessage();

  /**
   * Displays some help message.
   */
  void printHelp();

  /**
   * Displays some goodbye message.
   */
  void goodbyeMessage();

  /**
   * Displays some options for the text UI.
   */
  void printOptions();
}
