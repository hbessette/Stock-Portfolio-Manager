package stocks.model.macros;

import stocks.model.stock.Stocks;

import java.util.*;

/**
 * An abstact class with methods useful to calculating x-day averages.
 * An x-day average is the average closing price of a stock over the last x days,
 * starting from the given date.
 */
public abstract class AbstractStockMacroXDay {

  /**
   * Gets the x-day average of the given stock over a given time period.
   * An x-day average is the average closing price of a stock over the last x days,
   * starting from the given date.
   *
   * @param stock     a stock to use as data
   * @param startDate the start date
   * @param xDays     the number of days to include. Goes backwards in time from startDate.
   * @return the x-day average
   */
  protected Double getXDayAverage(Stocks stock, Date startDate, int xDays) {
    List<Date> computedDates = getDatesInRange(stock, startDate, xDays);

    double xDayAverage = 0;
    for (Date xDate : computedDates) {
      xDayAverage += stock.getStockDayStatus(xDate).getClosingTime();
    }
    return xDayAverage / xDays;
  }

  /**
   * Gets all the valid dates (dates with stock data) for a given stock over a given time period.
   *
   * @param stock     the stock to use as data
   * @param startDate the start date
   * @param xDays     the number of days to include. Goes backwards in time from startDate.
   * @return the valid dates
   * @throws IllegalArgumentException if the start date is not valid
   */
  protected List<Date> getDatesInRange(Stocks stock, Date startDate, int xDays)
          throws IllegalArgumentException {
    Set<Date> validDatesSet = stock.getValidDates();

    if (!validDatesSet.contains(startDate)) {
      throw new IllegalArgumentException("Start date is not valid.");
    }

    ArrayList<Date> sortedValidDates = new ArrayList<Date>(validDatesSet);
    Collections.sort(sortedValidDates, Collections.reverseOrder());
    Iterator<Date> iterateDates = sortedValidDates.iterator();

    return sortedValidDates.subList(
            sortedValidDates.indexOf(startDate),
            sortedValidDates.indexOf(startDate) + xDays
    );
  }

}
