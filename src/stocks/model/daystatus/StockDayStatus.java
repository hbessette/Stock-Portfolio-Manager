package stocks.model.daystatus;

import java.util.Date;

/**
 * Represents the status of a given stock on at a specific date.
 */
public interface StockDayStatus {
  /**
   * Gets the date which this StockDayStatus concerns.
   *
   * @return the Date of this StockDayStatus
   */
  public Date getDate();

  /**
   * Gets the closing price of the stock on this day.
   *
   * @return the closing price
   */
  public double getClosingTime();

  /**
   * Gets the volume of trade on this day (number of stock bought/sold).
   *
   * @return the volume of trade
   */
  public int getVolume();
}
