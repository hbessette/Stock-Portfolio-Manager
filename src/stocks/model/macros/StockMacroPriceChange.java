package stocks.model.macros;

import stocks.model.StockDayStatus;
import stocks.model.Stocks;

import java.util.Date;

public class StockMacroPriceChange implements StockMacro<Double> {
  Date startDate;
  Date endDate;

  public StockMacroPriceChange(Date startDate, Date endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public Double apply(Stocks<Double> stock) {
    StockDayStatus start = stock.getStockDayStatus(startDate);
    StockDayStatus end = stock.getStockDayStatus(endDate);

    return end.getClosingTime() - start.getClosingTime();
  }
}
