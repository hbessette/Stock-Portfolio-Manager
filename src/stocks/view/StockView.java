package stocks.view;

import java.awt.event.ActionListener;

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

  /**
   * Sets up all listeners for the provided ActionListener. For use with a GUI view.
   *
   * @param listener an ActionListener to set up listeners for
   */
  void startListeners(ActionListener listener);

  /**
   * For use with a GUI view. Returns the current text of some component, such as a text area.
   *
   * @param componentName the name of the component
   * @return the text of the component
   * @throws IllegalArgumentException if a component with the passed name does not exist
   */
  String getComponentText(String componentName) throws IllegalArgumentException;

  /**
   * Updates any GUI components with a list of portfolios to show the provided new portfolio name
   *
   * @param loadedPortfolio name of a portfolios
   */
  void addPortfolioGUI(String loadedPortfolio);
}
