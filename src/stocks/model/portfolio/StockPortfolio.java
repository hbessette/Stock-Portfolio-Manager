package stocks.model.portfolio;

import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public interface StockPortfolio {
  List<Stocks> getAllStocks();

  void addStock(Stocks stock);

  void removeStock(Stocks stock);

  Stocks getStockByName(String symbol) throws NoSuchElementException;

  String returnLog();

  double evaluate(Date date);
}
