package stocks.model.portfolio;

import stocks.model.stock.Stocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class StockPortfolioImpl implements StockPortfolio {
  private List<Stocks> portfolio;
  private StringBuilder log;

  StockPortfolioImpl() {
    this.portfolio = new ArrayList<Stocks>();
    this.log = new StringBuilder();
  }

  @Override
  public List<Stocks> getAllStocks() {
    return new ArrayList<>(this.portfolio);
  }

  @Override
  public void addStock(Stocks stock) {
    this.portfolio.add(stock);
    this.log.append(stock.toString());
  }

  @Override
  public void removeStock(Stocks stock) {
    this.portfolio.remove(stock);
  }

  public Stocks getStockByName(String symbol) throws NoSuchElementException {
    for (Stocks stock : this.portfolio) {
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

  @Override
  public double evaluate(Date date) {
    double sum = 0;
    for (Stocks stock : this.portfolio) {
      sum += stock.getStockDayStatus(date).getClosingTime();
    }
    return sum;
  }
}
