package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class StockPortfolioImpl implements StockPortfolio {
  private Map<Stocks, Integer> portfolio;
  private StringBuilder log;

  public StockPortfolioImpl() {
    this.portfolio = new HashMap<Stocks, Integer>();
    this.log = new StringBuilder();
  }

  @Override
  public List<Stocks> getAllStocks() {
    Set<Stocks> setStocks = portfolio.keySet();
    return new ArrayList<Stocks>(setStocks);
  }

  @Override
  public List<StockAndShares> getAllStocksAndShares() {

    List<StockAndShares> returnList = new ArrayList<StockAndShares>();
    for (Stocks stock : this.getAllStocks()) {
      returnList.add(
              new StockAndShares(stock, this.portfolio.get(stock))
      );
    }

    return returnList;
  }

  @Override
  public void addStock(Stocks stock, int shares) {
    if (this.portfolio.containsKey(stock)) {
      this.portfolio.put(stock, this.portfolio.get(stock) + shares);
    }
    else {
      this.portfolio.put(stock, shares);
    }
    this.log.append(stock.toString()).append(shares);
  }

  @Override
  public void removeStock(Stocks stock) {
    this.portfolio.remove(stock);
  }

  @Override
  public void removeStockShares(Stocks stock, int shares) throws IllegalArgumentException {
    if (!this.portfolio.containsKey(stock)) {
      return;
    }

    if (this.portfolio.get(stock) < shares) {
      throw new IllegalArgumentException("Cannot remove more shares than you have.");
    }

    this.portfolio.put(stock, this.portfolio.get(stock) - shares);
  }

  public Stocks getStockByName(String symbol) throws NoSuchElementException {
    for (Stocks stock : this.getAllStocks()) {
      if (stock.getSymbol().equals(symbol)) {
        return stock;
      }
    }
    throw new NoSuchElementException(symbol + " is not found in portfolio.");
  }

  @Override
  public StockAndShares getStockAndSharesByName(String symbol) throws NoSuchElementException {
    for (Stocks stock : this.getAllStocks()) {
      if (stock.getSymbol().equals(symbol)) {
        return new StockAndShares(stock, this.portfolio.get(stock));
      }
    }
    throw new NoSuchElementException(symbol + " is not found in portfolio.");
  }

  @Override
  public String returnLog() {
    return this.log.toString();
  }

  @Override
  public double evaluate(Date date) {
    double sum = 0;
    for (Stocks stock : this.getAllStocks()) {
      sum += stock.getStockDayStatus(date).getClosingTime() * this.portfolio.get(stock);
    }
    return sum;
  }
}
