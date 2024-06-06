package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class StockPortfolioMock implements StockPortfolio {
  private StringBuilder log;

  public StockPortfolioMock() {
    this.log = new StringBuilder();
  }

  @Override
  public List<Stocks> getAllStocks() {
    this.log.append("getAllStocks() successfully called").append(System.lineSeparator());
    return List.of();
  }

  @Override
  public List<StockAndShares> getAllStocksAndShares() {
    this.log.append("getAllStocksAndShares() successfully called").append(System.lineSeparator());
    return List.of();
  }

  @Override
  public void addStock(Stocks stock, int shares) {
    this.log.append(shares).append(" shares for ").append(stock.getSymbol()).append(" added.")
            .append(System.lineSeparator());
  }

  @Override
  public void removeStock(Stocks stock) {
    this.log.append("Stock ").append(stock.getSymbol()).append(" removed.")
            .append(System.lineSeparator());
  }

  @Override
  public void removeStockShares(Stocks stock, int shares) {
    this.log.append(shares).append(" shares for ").append(stock.getSymbol()).append(" removed.")
            .append(System.lineSeparator());
  }

  @Override
  public Stocks getStockByName(String symbol) throws NoSuchElementException {
    this.log.append("getStockByName(").append(symbol).append(") successfully called").append(System
            .lineSeparator());
    return null;
  }

  @Override
  public StockAndShares getStockAndSharesByName(String symbol) throws NoSuchElementException {
    this.log.append("getStockAndSharesByName(").append(symbol).append(") successfully called")
            .append(System
                    .lineSeparator());
    return null;
  }

  @Override
  public String returnLog() {
    return this.log.toString();
  }

  @Override
  public double evaluate(Date date) {
    this.log.append("evaluate(").append(date.toString()).append(") successfully called")
            .append(System.lineSeparator());
    return 0;
  }
}
