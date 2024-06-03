package stocks.model;

import java.util.Date;

import stocks.model.StockMacro;

public interface Stocks {
  public void execute(StockMacro stockMacro);

  public StockDayStatus getStockDayStatus(Date date);
}
