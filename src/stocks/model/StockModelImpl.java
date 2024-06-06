package stocks.model;

import stocks.model.portfolio.StockPortfolio;
import stocks.model.portfolio.StockPortfolioImpl;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import java.util.*;

/**
 * A model which allows the user to create portfolios.
 * Stocks can be added to a portfolio with a specified number of shares.
 * More shares can be added, or shares can be removed.
 * The value of the portfolio can be evaluated on any given date.
 * Allows one to get a portfolio by name, and evaluate it on any date.
 */
public class StockModelImpl implements StockModel {
  private Map<String, StockPortfolio> portfolios;
  private StringBuilder log;

  /**
   * Creates a new StockModel. It is initialized with no portfolios.
   */
  public StockModelImpl() {
    this.portfolios = new HashMap<String, StockPortfolio>();
    this.log = new StringBuilder();
  }

  /**
   * Adds a new portfolio to the model.
   *
   * @param name the name of the portfolio
   */
  @Override
  public void addPortfolio(String name) {
    this.portfolios.put(name, new StockPortfolioImpl());
    this.log.append("Portfolio ").append(name).append(" added.").append(System.lineSeparator());
  }

  /**
   * removes a portfolio from the model.
   *
   * @param name the name of the portfolio
   */
  @Override
  public void removePortfolio(String name) {
    if (this.portfolios.containsKey(name)) {
      this.portfolios.remove(name);
      this.log.append("Portfolio ").append(name).append(" removed.").append(System.lineSeparator());
    }
  }

  /**
   * Gets a portfolio by its name, if it exists in this model.
   *
   * @param name the name of the portfolio
   * @return the portfolio
   */
  @Override
  public StockPortfolio getPortfolioByName(String name) throws NoSuchElementException {
    try {
      this.log.append("Portfolio ").append(name).append(" received.").append(System.lineSeparator());
      return this.portfolios.get(name);
    } catch (NullPointerException e) {
      throw new NoSuchElementException("No portfolio exists.");
    }
  }

  /**
   * Evaluates a portfolio on any date.
   * This gets the total value of the portfolio,
   * determined by the prices of stocks on that date and the number of shares owned.
   *
   * @param name the name of the portfolio
   * @param date the date to evaluate at
   * @return the value of the portfolio
   */
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

  /**
   * Creates and returns a new stock by its symbol.
   *
   * @param symbol a stock symbol (e.g. "GOOG" for Google)
   * @return the new stock
   */
  @Override
  public Stocks getStockByName(String symbol) {
    this.log.append("Stock ").append(symbol).append(" received.").append(System.lineSeparator());
    return new StocksImpl(symbol);
  }

  /**
   * Gets a set of all portfolios names.
   *
   * @return a set of all portfolios names.
   */
  @Override
  public Set<String> getAllPortfolios() {
    this.log.append("getAllPortfolios() successfully called.").append(System.lineSeparator());
    return this.portfolios.keySet();
  }

  /**
   * Gets the log of what has been added and removed.
   *
   * @return the log
   */
  @Override
  public String returnLog() {
    return this.log.toString();
  }
}
