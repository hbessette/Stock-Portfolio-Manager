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
    out.println("show-all-portfolios");
    out.println("add-portfolio (portfolioName)");
    out.println("remove-portfolio (portfolioName)");
    out.println("save-portfolio (portfolioName)");
    out.println("load-portfolio (portfolioName)");
    out.println("buy-stock (portfolioName) (stockName) (month) (day) (year) (integerAmount)");
    out.println("sell-stock (portfolioName) (stockName) (month) (day) (year) (decimalAmount)");
    out.println("sell-all-stock (portfolioName) (stockName) (month) (day) (year)");
    out.println("evaluate-portfolio (portfolioName) (month) (day) (year)");
    out.println("get-price-change (stockName) (startMonth) (startDay) (startYear) (endMonth) " +
            "(endDay) (endYear)");
    out.println("get-x-day-crossovers (stockName) (startMonth) (startDay) (startYear) (xDays)");
    out.println("get-x-day-average (stockName) (startMonth) (startDay) (startYear) (xDays)");
  }

  /**
   * Shows a goodbye message.
   */
  @Override
  public void goodbyeMessage() {
    out.println("Thank You For Using Stock Manager");
  }
}
