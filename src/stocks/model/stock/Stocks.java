package stocks.model.stock;

import java.util.Date;
import java.util.Set;

import stocks.model.daystatus.StockDayStatus;

public interface Stocks {
  public StockDayStatus getStockDayStatus(Date date);

  public Set<Date> getValidDates();

  public String getSymbol();
}
