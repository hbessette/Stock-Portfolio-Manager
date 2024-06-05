package stocks.model.macros;

import stocks.model.StockDayStatus;
import stocks.model.Stocks;

import java.util.Date;

/**
 * A stock macro which determines the gain or loss of a stock over a specified time.
 * This gain or loss is represented as a decimal.
 */
public class StockMacroPriceChange implements StockMacro<Double> {
  Date startDate;
  Date endDate;

  /**
   * Initializes this macro with a specified start and end date.
   *
   * @param startDate the beginning date
   * @param endDate the ending date
   * @throws IllegalArgumentException if the end date is less than the start date
   */
  public StockMacroPriceChange(Date startDate, Date endDate) throws IllegalArgumentException {
    if (startDate.compareTo(endDate)>0) {
      throw new IllegalArgumentException("End date cannot be less than start date.");
    }

    this.startDate = startDate;
    this.endDate = endDate;
  }

  /**
   * Applies this macro on a specified stock.
   *
   * @param stock the stock to apply the macro to
   * @return the gain (or loss) of the stock as a decimal
   */
  @Override
  public Double apply(Stocks stock) {
    StockDayStatus start = stock.getStockDayStatus(startDate);
    StockDayStatus end = stock.getStockDayStatus(endDate);

    return end.getClosingTime() - start.getClosingTime();
  }
}
