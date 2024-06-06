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

  public void welcomeMessage();

  public void printHelp();

  public void goodbyeMessage();

}
