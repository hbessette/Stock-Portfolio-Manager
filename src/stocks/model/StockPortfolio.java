package stocks.model;

import java.util.List;

public interface StockPortfolio {
  List<Stocks> getAllStocks();

  void addStock(Stocks stock);

  void removeStock(Stocks stock);

  Stocks getStockByName(String symbol);

  String returnLog();
}
