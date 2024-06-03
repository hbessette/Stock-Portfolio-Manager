package stocks.model;

import java.util.Date;

public class StockDayStatusImpl implements StockDayStatus {
  private final Date date;
  private final double closing;
  private final int volume;

  public StockDayStatusImpl(Date date, double closing, int volume) {
    this.date = date;
    this.closing = closing;
    this.volume = volume;
  }

  @Override
  public Date getDate() {
    return new Date(date.getYear(), date.getMonth(), date.getDate());
  }

  @Override
  public double getClosingTime() {
    return closing;
  }

  @Override
  public int getVolume() {
    return volume;
  }
}
