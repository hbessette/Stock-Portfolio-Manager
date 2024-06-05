package stocks.model;

import java.util.Date;

/**
 * Represents the status of a given stock on at a specific date.
 */
public class StockDayStatusImpl implements StockDayStatus {
  private final Date date;
  private final double closing;
  private final int volume;

  /**
   * Creates a new StockDayStatus.
   *
   * @param date the date this data corresponds with
   * @param closing the closing price of this stock
   * @param volume the volume of trade
   */
  public StockDayStatusImpl(Date date, double closing, int volume) {
    this.date = date;
    this.closing = closing;
    this.volume = volume;
  }

  /**
   * Gets the date which this StockDayStatus concerns.
   *
   * @return the Date of this StockDayStatus
   */
  @Override
  public Date getDate() {
    return new Date(date.getYear(), date.getMonth(), date.getDate());
  }

  /**
   * Gets the closing price of the stock on this day.
   *
   * @return the closing price
   */
  @Override
  public double getClosingTime() {
    return closing;
  }

  /**
   * Gets the volume of trade on this day (number of stock bought/sold).
   *
   * @return the volume of trade
   */
  @Override
  public int getVolume() {
    return volume;
  }
}
