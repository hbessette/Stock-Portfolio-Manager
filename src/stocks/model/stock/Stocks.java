package stocks.model.stock;

import stocks.model.daystatus.StockDayStatus;

import java.util.Date;
import java.util.Set;

/**
 * Represents a single stock.
 * Includes all of this stock's available data as StockDayStatuses.
 */
public interface Stocks {
  /**
   * Gets the StockDayStatus at a specified date.
   * A StockDayStatus includes the volume of trade and closing price of a stock.
   *
   * @param date the date queried
   * @return the StockDayStatus
   * @throws IllegalArgumentException if no data exists for the provided date
   */
  public StockDayStatus getStockDayStatus(Date date) throws IllegalArgumentException;

  /**
   * Gets all the valid dates of this stock's data.
   *
   * @return a set of all valid dates
   */
  public Set<Date> getValidDates();

  /**
   * Gets the symbol of this stock (e.g. "GOOG" for Google).
   *
   * @return this stock's symbol
   */
  public String getSymbol();
}
