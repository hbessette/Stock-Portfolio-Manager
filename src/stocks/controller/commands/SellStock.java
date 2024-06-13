package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.portfolio.StockPortfolioTimed;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

/**
 * To sell stock for a specific portfolio at a specified date and amount.
 */
public class SellStock implements StockControllerCommand {
  private final String portfolioName;
  private final Date date;
  private final double amount;
  private final String symbol;

  /**
   * To create a sell stock object.
   * @param s : to take in user inputs
   * @param view : to prompt user inputs
   */
  public SellStock(Scanner s, StockView view) {
    view.show("Enter the portfolio name to sell stock for: ");
    this.portfolioName = s.next();
    view.show("Enter the symbol to sell stock for: ");
    this.symbol = s.next();
    view.show("Enter the year to sell stock for: ");
    int year = s.nextInt();
    view.show("Enter the month to sell stock for: ");
    int month = s.nextInt() - 1;
    view.show("Enter the day to sell stock for: ");
    int day = s.nextInt();
    view.show("Enter the amount of stock to sell (fractional values are acceptable): ");
    this.amount = s.nextDouble();
    this.date = new Date(year, month, day);
  }

  @Override
  public void start(StockView view, StockModel model) {
    StockPortfolioTimed portfolio = model.getPortfolioByName(this.portfolioName);
    portfolio.sell(this.symbol, this.date, this.amount);
    view.show(String.format("Successfully sold %f shares of %s on %d-%d-%d for %s.",
            this.amount, this.symbol, this.date.getMonth() + 1, this.date.getDate(),
            this.date.getYear(), this.portfolioName));
  }
}
