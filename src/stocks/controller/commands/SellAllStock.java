package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

public class SellAllStock implements StockControllerCommand {
  private final String portfolioName;
  private final Date date;
  private final String symbol;

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

  }
}
