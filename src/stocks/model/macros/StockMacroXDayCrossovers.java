package stocks.model.macros;

import stocks.model.stock.Stocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A stock macro which determines the x-day crossovers of a stock.
 * x-day crossovers are any days when the stock closing price is above the x-day moving average.
 * The x-day moving average is the average starting price of a stock,
 * of the last x days (starting from the given date).
 * This is determined by the last x days when stock prices are available.
 */
public class StockMacroXDayCrossovers extends AbstractStockMacroXDay
        implements StockMacro<Date[]> {
  Date startDate;
  int xDays;

  /**
   * Initializes this macro with a specified start date and X value.
   *
   * @param startDate the start date
   * @param xDays     the number of days to compute the average of
   */
  public StockMacroXDayCrossovers(Date startDate, int xDays) {
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
  public Date[] apply(Stocks stock) {
    double xDayAvg = getXDayAverage(stock, this.startDate, this.xDays);
    List<Date> validDates = getDatesInRange(stock, this.startDate, this.xDays);
    List<Date> returnDates = new ArrayList<Date>();
    for (Date validDate : validDates) {
      if (stock.getStockDayStatus(validDate).getClosingTime() > xDayAvg) {
        returnDates.add(validDate);
      }
    }

    Date[] returnVal = new Date[returnDates.size()];
    for (int i = 0; i < returnDates.size(); i++) {
      returnVal[i] = returnDates.get(i);
    }

    return returnVal;
  }
}
