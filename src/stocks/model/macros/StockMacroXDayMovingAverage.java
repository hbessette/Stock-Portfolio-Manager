package stocks.model.macros;

import java.util.Date;

import stocks.model.stock.Stocks;

/**
 * A stock macro which determines the x-day moving average of a stock.
 * The x-day moving average is the average starting price of a stock,
 * of the last x days (starting from the given date).
 * This is determined by the last x days when stock prices are available.
 */
public class StockMacroXDayMovingAverage extends AbstractStockMacroXDay
        implements StockMacro<Double> {
  Date startDate;
  int xDays;

  /**
   * Initializes this macro with a specified start date and X value.
   *
   * @param startDate the start date
   * @param xDays the number of days to compute the average of
   */
  public StockMacroXDayMovingAverage(Date startDate, int xDays) {
    this.startDate = startDate;
    this.xDays = xDays;
  }

  /**
   * Applies this macro on a specified stock.
   *
   * @param stock the stock to apply the macro to
   * @return the x-day moving average of this stock
   * @throws IllegalArgumentException if the start date is not valid
   */
  @Override
  public Double apply(Stocks stock) throws IllegalArgumentException {
    return getXDayAverage(stock, this.startDate, this.xDays);
  }
}
