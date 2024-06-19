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

  /**
   * Displays some message in a GUI, as confirmation for the action of creating a portfolio.
   *
   * @param message some confirmation or error message
   */
  void createPortfolioMessage(String message);

  /**
   * Displays some message in a GUI, as confirmation for the action of saving or loading a portfolio from a file.
   *
   * @param message some confirmation or error message
   */
  void saveLoadPortfolioMessage(String message);

  /**
   * Displays some message in a GUI, as confirmation for the action of buying or selling a stock.
   *
   * @param message some confirmation or error message
   */
  void buySellStockMessage(String message);

  /**
   * Displays some message in a GUI, should be a value and composition of a portfolio.
   *
   * @param message some value and composition of a portfolio message.
   */
  void ValueCompositionMessage(String message);
}
