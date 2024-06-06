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
   * @param input the text to display
   */
  @Override
  public void show(String input) {
    out.println(input);
  }

  /**
   * Prints a welcome message.
   */
  public void welcomeMessage() {
    out.println("Welcome to Stock Manager");
    out.println("Enter help or h to see list of commands.");
  }

  public void printHelp() {
    out.println("List of Commands: ");
    out.println("add-portfolio (portfolioName)");
    out.println("remove-portfolio (portfolioName)");
    out.println("show-portfolio (portfolioName)");
    out.println("show-all-portfolios");
<<<<<<< Updated upstream
    out.println("evaluate-portfolio (portfolioName) (startDay) (startYear) (endMonth)");
=======
    out.println("evaluate-portfolio (portfolioName) (month) (day) (year)");
>>>>>>> Stashed changes
    out.println("show-portfolio-stocks (portfolioName)");
    out.println("add-stock (portfolioName) (stockName) (shares)");
    out.println("remove-stock (portfolioName) (stockName)");
    out.println("remove-stock-shares (portfolioName) (stockName) (shares)");
    out.println("get-price-change (stockName) (startMonth) (startDay) (startYear) (endMonth) " +
            "(endDay) (endYear)");
    out.println("get-x-day-crossovers (stockName) (startMonth) (startDay) (startYear) (xDays)");
    out.println("get-x-day-average (stockName) (startMonth) (startDay) (startYear) (xDays)");
  }

  @Override
  public void goodbyeMessage() {
    out.println("Thank You For Using Stock Manager");
  }
}
