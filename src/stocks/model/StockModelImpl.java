package stocks.model;

import stocks.model.portfolio.StockPortfolio;
import stocks.model.portfolio.StockPortfolioImpl;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class StockModelImpl implements StockModel {
  Map<String, StockPortfolio> portfolios;
  StringBuilder log;

  public StockModelImpl() {
    this.portfolios = new HashMap<String, StockPortfolio>();
    this.log = new StringBuilder();
  }

  @Override
  public void addPortfolio(String name) {
    this.portfolios.put(name, new StockPortfolioImpl());
    this.log.append("Successfully added ").append(name).append(System.lineSeparator());
  }

  @Override
  public void removePortfolio(String name) {
    if (this.portfolios.containsKey(name)) {
      this.portfolios.remove(name);
      this.log.append("Successfully removed ").append(name).append(System.lineSeparator());
    }
  }

  @Override
  public StockPortfolio getPortfolioByName(String name) throws NoSuchElementException {
    try {
      return this.portfolios.get(name);
    } catch (NullPointerException e) {
      throw new NoSuchElementException("No portfolio exists.");
    }
  }

  @Override
  public double evaluatePortfolio(String name, Date date) {
    return this.portfolios.get(name).evaluate(date);
  }

  @Override
  public Stocks getStockByName(String symbol) {
    return new StocksImpl(symbol);
  }

  @Override
  public String returnLog() {
    return this.log.toString();
  }
}
