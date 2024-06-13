package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroXDayCrossovers;
import stocks.view.StockView;


import java.util.Date;
import java.util.Scanner;

/**
 * A command to show all X-Day crossovers of a stock over a time period.
 * x-day crossovers are any days when the stock closing price is above the x-day moving average.
 * The x-day moving average is the average starting price of a stock,
 * of the last x days (starting from the given date).
 * This is determined by the last x days when stock prices are available.
 */
public class XDayCrossovers implements StockControllerCommand {

  private final String symbol;
  private final Date startDate;
  private final int xDays;


  public XDayCrossovers(Scanner s, StockView view) {
    view.show("Enter the stock symbol: ");
    this.symbol = s.next();
    view.show("Enter the year: ");
    int year = s.nextInt();
    view.show("Enter the month: ");
    int month = s.nextInt() - 1;
    view.show("Enter the day: ");
    int day = s.nextInt();
    view.show("Enter the number of days: ");
    this.xDays = s.nextInt();
    this.startDate = new Date(year, month, day);
  }

  /**
   * Shows the x-day crossovers to the view.
   *
   * @param view  the view to display to
   * @param model the model to use to process the command
   */
  @Override
  public void start(StockView view, StockModel model) {
    Date[] dates =
            new StockMacroXDayCrossovers(this.startDate, this.xDays)
                    .apply(model.getStockByName(this.symbol));
    for (Date date : dates) {
      view.show((date.getMonth() + 1) + "-" + date.getDate() + "-" + date.getYear());
    }
  }
}
