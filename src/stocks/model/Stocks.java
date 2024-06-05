package stocks.model;

import java.util.Date;
import java.util.Set;

import stocks.model.macros.StockMacro;

public interface Stocks {
  public StockDayStatus getStockDayStatus(Date date);

  public Set<Date> getValidDates();

  public String getSymbol();
}
