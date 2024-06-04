package stocks.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StockPortfolioImpl<T> implements StockPortfolio<T> {
  private List<Stocks<T>> portfolio;
  private StringBuilder log;

  StockPortfolioImpl() {
    this.portfolio = new ArrayList<Stocks<T>>();
    this.log = new StringBuilder();
  }

  @Override
  public List<Stocks<T>> getAllStocks() {
    return new ArrayList<>(this.portfolio);
  }

  @Override
  public void addStock(Stocks<T> stock) {
    this.portfolio.add(stock);
    this.log.append(stock.toString());
  }

  @Override
  public void removeStock(Stocks<T> stock) {
    this.portfolio.remove(stock);
  }

  public Stocks<T> getStockByName(String symbol) throws NoSuchElementException {
    for (Stocks<T> stock : this.portfolio) {
      if (stock.getSymbol().equals(symbol)) {
        return stock;
      }
    }
    throw new NoSuchElementException(symbol + " is not found in portfolio.");
  }

  @Override
  public String returnLog() {
    return this.log.toString();
  }


}
