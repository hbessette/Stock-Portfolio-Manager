package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class StockPortfolioTimedImpl implements StockPortfolioTimed{
  @Override
  public void purchase(String name, Date date, int shares) {

  }

  @Override
  public void sell(String name, Date date, double shares) {

  }

  @Override
  public String[] getComposition(Date date) {
    return new String[0];
  }

  @Override
  public String[] getDistribution(Date date) {
    return new String[0];
  }

  @Override
  public void rebalance(Date date, Map<Stocks, Double> percentages) {

  }

  @Override
  public List<Stocks> getAllStocks() {
    return List.of();
  }

  @Override
  public List<StockAndShares> getAllStocksAndShares() {
    return List.of();
  }

  @Override
  public void addStock(Stocks stock, int shares) {

  }

  @Override
  public void removeStock(Stocks stock) {

  }

  @Override
  public void removeStockShares(Stocks stock, int shares) throws IllegalArgumentException {

  }

  @Override
  public Stocks getStockByName(String symbol) throws NoSuchElementException {
    return null;
  }

  @Override
  public StockAndShares getStockAndSharesByName(String symbol) throws NoSuchElementException {
    return null;
  }

  @Override
  public String returnLog() {
    return "";
  }

  @Override
  public double evaluate(Date date) {
    return 0;
  }
}
