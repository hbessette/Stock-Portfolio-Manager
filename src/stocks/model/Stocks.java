package stocks.model;

import java.util.Date;
import java.util.Set;

import stocks.model.macros.StockMacro;

public interface Stocks<T> {
  public T execute(StockMacro<T> stockMacro);

  public StockDayStatus getStockDayStatus(Date date);

  public Set<Date> getValidDates();

  public String getSymbol();
}
