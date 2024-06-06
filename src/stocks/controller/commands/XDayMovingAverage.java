package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroXDayMovingAverage;
import stocks.view.StockView;

/**
 * A command to display the x-day moving average.
 * The x-day moving average is the average starting price of a stock,
 * of the last x days (starting from the given date).
 * This is determined by the last x days when stock prices are available.
 */

public class XDayMovingAverage extends AXDayCommand {

  /**
   * Constructs this command.
   *
   * @param symbol the symbol of the stock to check
   * @param month  the month of the date to check
   * @param day    the day of the date to check
   * @param year   the year of the date to check
   * @param xDays  the number of x days to go *back* from the starting date, minus 1.
   */
  public XDayMovingAverage(String symbol, int month, int day, int year, int xDays) {
    super(symbol, month, day, year, xDays);
  }

  /**
   * displays the x-day moving average to the view.
   *
   * @param view  the view to display to
   * @param model the model to use to process the command
   */
  @Override
  public void start(StockView view, StockModel model) {
    double average =
            new StockMacroXDayMovingAverage(this.startDate, this.xDays).apply(
                    model.getStockByName(this.symbol));
    view.show("Moving average for " + this.symbol + " from " + (this.startDate.getMonth() + 1) +
            "-" + this.startDate.getDate() + "-" + this.startDate.getYear() +
            " is " + (Math.round(average * 100.00) / 100.00) + ".");
  }
}
