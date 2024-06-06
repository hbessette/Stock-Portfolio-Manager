package stocks.model;

import stocks.model.portfolio.StockPortfolio;
import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.Set;

public class StockModelMock implements StockModel {
  private StringBuilder log;

  StockModelMock() {
    this.log = new StringBuilder();
  }

  @Override
  public void addPortfolio(String name) {
    this.log.append("Portfolio ").append(name).append(" added.").append(System.lineSeparator());
  }

  @Override
  public void removePortfolio(String name) {
    this.log.append("Portfolio ").append(name).append(" removed.").append(System.lineSeparator());
  }

  @Override
  public StockPortfolio getPortfolioByName(String name) {
    this.log.append("Portfolio ").append(name).append(" received.").append(System.lineSeparator());
    return null;
  }

  @Override
  public double evaluatePortfolio(String name, Date date) {
    this.log.append("Portfolio ").append(name).append(" received.").append("Date: ")
            .append(date.toString()).append(System.lineSeparator());
    return 0;
  }

  @Override
  public Stocks getStockByName(String symbol) {
    this.log.append("Stock ").append(symbol).append(" received.").append(System.lineSeparator());
    return null;
  }

  @Override
  public Set<String> getAllPortfolios() {
    this.log.append("getAllPortfolios() successfully called.").append(System.lineSeparator());
    return Set.of();
  }

  @Override
  public String returnLog() {
    return this.log.toString();
  }
}
