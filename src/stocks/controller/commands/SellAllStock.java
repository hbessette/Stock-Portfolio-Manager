package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

/**
 * To sell all of a specific stock in a portfolio on a date.
 */
public class SellAllStock implements StockControllerCommand {
  private final String portfolioName;
  private final Date date;
  private final String symbol;

  /**
   * To create a sellAllStock object.
   *
   * @param s    : to get user input
   * @param view : to prompt user input
   */
  public SellAllStock(Scanner s, StockView view) {
    view.show("Enter the portfolio name to sell stock for: ");
    this.portfolioName = s.next();
    view.show("Enter the stock to sell all for: ");
    this.symbol = s.next();
    view.show("Enter the year to sell stock for: ");
    int year = s.nextInt();
    view.show("Enter the month to sell stock for: ");
    int month = s.nextInt() - 1;
    view.show("Enter the day to sell stock for: ");
    int day = s.nextInt();
    this.date = new Date(year, month, day);
  }

  @Override
  public void start(StockView view, StockModel model) {
    model.sellAllStockForPortfolio(this.portfolioName, this.symbol, this.date);
    view.show(String.format("Successfully sold all %s in %s on %d-%d-%d.", this.symbol,
            this.portfolioName, this.date.getMonth() + 1, this.date.getDate(),
            this.date.getYear()));
  }
}
