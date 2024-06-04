package stocks.model;

import java.util.Date;

import stocks.model.macros.StockMacro;

public interface Stocks<T> {
  public T execute(StockMacro<T> stockMacro);

  public StockDayStatus getStockDayStatus(Date date);

  public String getSymbol();
}
