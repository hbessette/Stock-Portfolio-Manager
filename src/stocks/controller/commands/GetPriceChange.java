package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroPriceChange;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

/**
 * To get the price change of a certain stock between two dates. Value can be negative or positive.
 */
public class GetPriceChange implements StockControllerCommand {
  private final Date startDate;
  private final Date endDate;
  private final String symbol;

  /**
   * To create a get price change object.
   * @param s : to take in user inputs
   * @param view : to prompt user inputs
   */
  public GetPriceChange(Scanner s, StockView view) {
    view.show("Enter the stock symbol for price change: ");
    this.symbol = s.next();
    view.show("Enter the year for the start date: ");
    int startYear = s.nextInt();
    view.show("Enter the month for the start date: ");
    int startMonth = s.nextInt() - 1;
    view.show("Enter the day for the start date: ");
    int startDay = s.nextInt();
    view.show("Enter the year for the end date: ");
    int endYear = s.nextInt();
    view.show("Enter the month for the end date: ");
    int endMonth = s.nextInt() - 1;
    view.show("Enter the day for the end date: ");
    int endDay = s.nextInt();
    this.startDate = new Date(startYear, startMonth, startDay);
    this.endDate = new Date(endYear, endMonth, endDay);
  }

  @Override
  public void start(StockView view, StockModel model) {
    double priceChange =
            new StockMacroPriceChange(this.startDate, this.endDate).apply(
                    model.getStockByName(this.symbol));
    priceChange = Math.round(priceChange * 100.00) / 100.00;
    view.show("Price change for " + this.symbol + " between " + (this.startDate.getMonth() + 1) +
            "-" + this.startDate.getDate() + "-" + this.startDate.getYear() +
            " and " + (this.endDate.getMonth() + 1) +
            "-" + this.endDate.getDate() + "-" + this.endDate.getYear() + " is $" + priceChange +
            ".");
  }
}
