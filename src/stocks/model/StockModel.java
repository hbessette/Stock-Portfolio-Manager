package stocks.model;

import stocks.model.portfolio.StockPortfolio;
import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.Set;

/**
 * A model which allows the user to create portfolios.
 * Stocks can be added to a portfolio with a specified number of shares.
 * More shares can be added, or shares can be removed.
 * The value of the portfolio can be evaluated on any given date.
 * Allows one to get a portfolio by name, and evaluate it on any date.
 */
public interface StockModel {
  /**
   * Adds a new portfolio to the model.
   *
   * @param name the name of the portfolio
   */
  public void addPortfolio(String name);

  /**
   * removes a portfolio from the model.
   *
   * @param name the name of the portfolio
   */
  public void removePortfolio(String name);

  /**
   * Gets a portfolio by its name, if it exists in this model.
   *
   * @param name the name of the portfolio
   * @return the portfolio
   */
  public StockPortfolio getPortfolioByName(String name);

  /**
   * Evaluates a portfolio on any date.
   * This gets the total value of the portfolio,
   * determined by the prices of stocks on that date and the number of shares owned.
   *
   * @param name the name of the portfolio
   * @param date the date to evaluate at
   * @return the value of the portfolio
   */
  public double evaluatePortfolio(String name, Date date);

  /**
   * Creates and returns a new stock by its symbol.
   *
   * @param symbol a stock symbol (e.g. "GOOG" for Google)
   * @return the new stock
   */
  public Stocks getStockByName(String symbol);

  /**
   * Gets a set of all portfolios names.
   *
   * @return a set of all portfolios names.
   */
  public Set<String> getAllPortfolios();

  /**
   * Gets the log of what has been added and removed.
   *
   * @return the log
   */
  public String returnLog();

  public void savePortfolio(String name);

  public void loadPortfolio(String name);
}
