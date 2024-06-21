package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

/**
 * A command to display the x-day moving average.
 * The x-day moving average is the average starting price of a stock,
 * of the last x days (starting from the given date).
 * This is determined by the last x days when stock prices are available.
 */

public class XDayMovingAverage implements StockControllerCommand {

  private final String symbol;
  private final Date startDate;
  private final int xDays;

  /**
   * Constructs this command.
   *
   * @param s    : scanner for input
   * @param view : to display prompts for user
   */
  public XDayMovingAverage(Scanner s, StockView view) {
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
   * displays the x-day moving average to the view.
   *
   * @param view  the view to display to
   * @param model the model to use to process the command
   */
  @Override
  public void start(StockView view, StockModel model) {
    double average = model.getXDayMovingAverage(this.symbol, this.startDate, this.xDays);
    view.show("Moving average for " + this.symbol + " from " + (this.startDate.getMonth() + 1) +
            "-" + this.startDate.getDate() + "-" + this.startDate.getYear() +
            " is $" + (Math.round(average * 100.00) / 100.00) + ".");
  }
}
