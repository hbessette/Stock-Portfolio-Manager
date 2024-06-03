package stocks.model;

import java.util.Date;

public class StockMacroPriceChange implements StockMacro {
  Date startDate;
  Date endDate;

  public StockMacroPriceChange(Date startDate, Date endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public Double apply(Stocks stock) {
    StockDayStatus start = stock.getStockDayStatus(startDate);
    StockDayStatus end = stock.getStockDayStatus(endDate);

    return (end.getClosingTime() - start.getClosingTime());
  }
}
