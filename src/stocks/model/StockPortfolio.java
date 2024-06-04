package stocks.model;

import java.util.List;

public interface StockPortfolio<T> {
  List<Stocks<T>> getAllStocks();

  void addStock(Stocks<T> stock);

  void removeStock(Stocks<T> stock);

  Stocks<T> getStockByName(String symbol);

  String returnLog();
}
