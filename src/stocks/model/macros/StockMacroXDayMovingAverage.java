package stocks.model.macros;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import stocks.model.StockDayStatus;
import stocks.model.Stocks;

/**
 * A stock macro which determines the x-day moving average of a stock.
 * The x-day moving average is the average starting price of a stock,
 * of the last x days (starting from the given date).
 * This is determined by the last x days when stock prices are available.
 */
public class StockMacroXDayMovingAverage implements StockMacro<Double> {
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
  public Double apply(Stocks<Double> stock) throws IllegalArgumentException {
    Set<Date> validDatesSet = stock.getValidDates();

    if (!validDatesSet.contains(this.startDate)) {
      throw new IllegalArgumentException("Start date is not valid.");
    }

    ArrayList<Date> sortedValidDates = new ArrayList<Date>(validDatesSet);
    Collections.sort(sortedValidDates, Collections.reverseOrder());
    Iterator<Date> iterateDates = sortedValidDates.iterator();

    while (iterateDates.next() != this.startDate) {
      iterateDates.next();
    }

    ArrayList<Date> xDates = new ArrayList<Date>();
    for (int i = 0; i < this.xDays; i++) {
      try {
        xDates.add(iterateDates.next());
      }
      catch (NoSuchElementException e) {
        e.printStackTrace();
      }
    }

    double xDayAverage = 0;
    for (Date xDate : xDates) {
      xDayAverage += stock.getStockDayStatus(xDate).getClosingTime();
    }
    return xDayAverage / xDates.size();
  }
}
