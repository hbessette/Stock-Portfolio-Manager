package stocks.model.portfolio;

import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.List;

public interface StockPortfolio {
  List<Stocks> getAllStocks();

  void addStock(Stocks stock);

  void removeStock(Stocks stock);

  Stocks getStockByName(String symbol);

  String returnLog();

  double evaluate(Date date);
}
