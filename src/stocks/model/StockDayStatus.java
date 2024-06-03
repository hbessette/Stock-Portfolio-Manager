package stocks.model;

import java.util.Date;

public interface StockDayStatus {
  public Date getDate();
  public double getClosingTime();
  public int getVolume();
}
