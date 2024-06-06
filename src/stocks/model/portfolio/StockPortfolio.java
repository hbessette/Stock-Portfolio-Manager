package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public interface StockPortfolio {
  List<Stocks> getAllStocks();

  List<StockAndShares> getAllStocksAndShares();

  void addStock(Stocks stock, int shares);

  void removeStock(Stocks stock);

  void removeStockShares(Stocks stock, int shares) throws IllegalArgumentException;

  Stocks getStockByName(String symbol) throws NoSuchElementException;

  StockAndShares getStockAndSharesByName(String symbol) throws NoSuchElementException;

  String returnLog();

  double evaluate(Date date);
}
