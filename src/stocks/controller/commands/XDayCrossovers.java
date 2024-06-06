package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroXDayCrossovers;
import stocks.view.StockView;


import java.util.Date;

/**
 * A command to show all X-Day crossovers of a stock over a time period.
 * x-day crossovers are any days when the stock closing price is above the x-day moving average.
 * The x-day moving average is the average starting price of a stock,
 * of the last x days (starting from the given date).
 * This is determined by the last x days when stock prices are available.
 */
public class XDayCrossovers extends AXDayCommand {

  /**
   * Constructs this command.
   *
   * @param symbol The symbol of the stock to check
   * @param month  the month of the date to check
   * @param day    the day of the date to check
   * @param year   the year of the date to check
   * @param xDays  the X number of days to go *back* from the starting date, minus 1.
   */
  public XDayCrossovers(String symbol, int month, int day, int year, int xDays) {
    super(symbol, month, day, year, xDays);
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
