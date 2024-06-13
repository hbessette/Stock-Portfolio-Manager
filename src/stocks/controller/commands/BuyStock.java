package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

public class BuyStock implements StockControllerCommand {
  private final Date date;
  private final int amount;
  private final String portfolioName;
  private final String symbol;

  public BuyStock(Scanner s, StockView view) {
    view.show("Enter the portfolio name to buy stock for: ");
    this.portfolioName = s.next();
    view.show("Enter the symbol to buy stock for: ");
    this.symbol = s.next();
    view.show("Enter the amount to buy stock for: ");
    this.amount = s.nextInt();
    view.show("Enter the year to buy stock for: ");
    int year = s.nextInt();
    view.show("Enter the month to buy stock for: ");
    int month = s.nextInt() - 1;
    view.show("Enter the day to buy stock for: ");
    int day = s.nextInt();
    this.date = new Date(year, month, day);
  }

  @Override
  public void start(StockView view, StockModel model) {
    model.purchaseStockForPortfolio(this.portfolioName, this.symbol, this.date, this.amount);
    view.show(String.format("Successfully bought %d shares of %s on %d-%d-%d for %s.",
            this.amount, this.symbol, this.date.getMonth() + 1, this.date.getDate(),
            this.date.getYear(), this.portfolioName));
  }
}