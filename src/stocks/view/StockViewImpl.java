package stocks.view;

import java.io.PrintStream;

/**
 * A simple view which prints text to a PrintStream.
 */
public class StockViewImpl implements StockView {
  private PrintStream out;

  /**
   * Initializes this view.
   *
   * @param out a PrintStream to print to
   */
  public StockViewImpl(PrintStream out) {
    this.out = out;
  }

  /**
   * Prints the given text to the PrintStream.
   *
   * @param input the text to display
   */
  @Override
  public void show(String input) {
    out.println(input);
  }

  /**
   * Shows a welcome message.
   */
  public void welcomeMessage() {
    out.println("Welcome to Stock Manager");
    out.println("Enter help or h to see list of commands.");
  }

  /**
   * Shows a list of commands.
   */
  public void printHelp() {
    out.println("List of Commands: ");
    out.println("show-all-portfolios (to show current portfolios)");
    out.println("add-portfolio (to add new portfolio)");
    out.println("remove-portfolio (to remove a portfolio)");
    out.println("save-portfolio (to save a portfolio for later use)");
    out.println("load-portfolio (to load a portfolio from storage)");
    out.println("buy-stock (to buy a stock for specific portfolio)");
    out.println("sell-stock (to sell a stock for specific portfolio)");
    out.println("sell-all-stock (to sell all of a stock in specific portfolio)");
    out.println("evaluate-portfolio (to evaluate total value of a specific portfolio)");
    out.println("get-composition (to get the composition of stocks of a specific portfolio)");
    out.println("get-price-change (to get the price change of a stock from and to specific dates)");
    out.println("get-x-day-crossovers (to get the x day crossovers of a stock)");
    out.println("get-x-day-average (to get the average of a stock over a specific period)");
  }

  /**
   * Shows a goodbye message.
   */
  @Override
  public void goodbyeMessage() {
    out.println("Thank You For Using Stock Manager");
  }
}
