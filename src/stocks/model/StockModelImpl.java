package stocks.model;

import stocks.model.portfolio.StockPortfolio;
import stocks.model.portfolio.StockPortfolioImpl;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import java.util.*;

public class StockModelImpl implements StockModel {
  private Map<String, StockPortfolio> portfolios;
  private StringBuilder log;

  public StockModelImpl() {
    this.portfolios = new HashMap<String, StockPortfolio>();
    this.log = new StringBuilder();
  }

  @Override
  public void addPortfolio(String name) {
    this.portfolios.put(name, new StockPortfolioImpl());
    this.log.append("Portfolio ").append(name).append(" added.").append(System.lineSeparator());
  }

  @Override
  public void removePortfolio(String name) {
    if (this.portfolios.containsKey(name)) {
      this.portfolios.remove(name);
      this.log.append("Portfolio ").append(name).append(" removed.").append(System.lineSeparator());
    }
  }

  @Override
  public StockPortfolio getPortfolioByName(String name) throws NoSuchElementException {
    try {
      this.log.append("Portfolio ").append(name).append(" received.").append(System.lineSeparator());
      return this.portfolios.get(name);
    } catch (NullPointerException e) {
      throw new NoSuchElementException("No portfolio exists.");
    }
  }

  @Override
  public double evaluatePortfolio(String name, Date date) throws NoSuchElementException {
    try {
      this.log.append("Portfolio ").append(name).append(" received.").append(" Date: ")
              .append(date.getYear()).append("-").append((date.getMonth() + 1)).append("-")
              .append(date.getDate()).append(System.lineSeparator());
      return this.portfolios.get(name).evaluate(date);
    } catch (NullPointerException e) {
      throw new NoSuchElementException("No portfolio exists.");
    }
  }

  @Override
  public Stocks getStockByName(String symbol) {
    this.log.append("Stock ").append(symbol).append(" received.").append(System.lineSeparator());
    return new StocksImpl(symbol);
  }

  @Override
  public Set<String> getAllPortfolios() {
    this.log.append("getAllPortfolios() successfully called.").append(System.lineSeparator());
    return this.portfolios.keySet();
  }

  @Override
  public String returnLog() {
    return this.log.toString();
  }
}
