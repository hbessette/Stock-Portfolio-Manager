package stocks.model;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
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

  /**
   * Saves a portfolio to a file.
   *
   * @param name the name of the portfolio
   * @throws NoSuchElementException if no such portfolio has been created
   */
  public void savePortfolio(String name) throws NoSuchElementException;

  /**
   * loads a portfolio from a file.
   *
   * @param name the name of the portfolio
   * @throws FileNotFoundException if no file exists for this portfolio
   */
  public void loadPortfolio(String name) throws FileNotFoundException;

  /**
   * Returns the composition of this portfolio (each stock and their shares owned).
   *
   * @param portfolioName the name of the portfolio
   * @param date the date to query the composition at
   * @return the composition of the portfolio
   */
  public String[] getCompositionForPortfolio(String portfolioName, Date date);

  /**
   * Returns the distribution of this portfolio (each stock and their monetary value).
   *
   * @param portfolioName the name of the portfolio
   * @param date the date to query the distribution at
   * @return the distribution of the portfolio
   */
  public String[] getDistributionForPortfolio(String portfolioName, Date date);

  /**
   * buys a stock for a portfolio at a specified date.
   *
   * @param portfolioName the name of the portfolio
   * @param symbol the stock symbol
   * @param date the date to buy at
   * @param amount the amount of shares to buy
   */
  public void purchaseStockForPortfolio(String portfolioName, String symbol, Date date,
                                        double amount);

  /**
   * sells a stock for a portfolio at a specified date.
   *
   * @param portfolioName the name of the portfolio
   * @param symbol the stock symbol
   * @param date the date to sell at
   * @param amount the amount of shares to sell
   */
  public void sellStockForPortfolio(String portfolioName, String symbol, Date date, double amount);

  /**
   * Returns the price change for a stock over a period of time.
   *
   * @param symbol a stock symbol
   * @param startDate the start date of the period of time
   * @param endDate the end date of the period of time
   * @return the price change for a stock over a period of time
   */
  public double getPriceChangeForStock(String symbol, Date startDate, Date endDate);

  /**
   * Returns the x-day moving average of this stock at a date for a specified x-days.
   *
   * @param symbol a stocks symbol
   * @param startDate the start date
   * @param xDays the x-days backwards from the start date, minus 1.
   * @return the x-day moving average of this stock
   */
  public double getXDayMovingAverage(String symbol, Date startDate, int xDays);

  /**
   * Returns all x-day crossovers of this stock at a date for a specified x-days.
   *
   * @param symbol a stocks symbol
   * @param startDate the start date
   * @param xDays the x-days backwards from the start date, minus 1.
   * @return all x-day crossovers of this stock
   */
  public String[] getXDayCrossovers(String symbol, Date startDate, int xDays);

  /**
   * Rebalanes a portfolio by a specified map of the stocks and desired percentages.
   *
   * @param portfolioName the name of the portfolio
   * @param date the date to rebalance at
   * @param percentages a map of stock symbols and their percentages
   */
  public void rebalancePortfolio(String portfolioName, Date date, Map<String, Double> percentages);

  /**
   * Gets all the stocks in a specified portfolio at a date.
   *
   * @param portfolioName the name of the portfolio
   * @param date the date to get all stocks during
   * @return all stocks in the portfolio
   */
  public String[] getStockNamesForPortfolio(String portfolioName, Date date);

  /**
   * Sells all shares of a specified stock in a portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param symbol the stock symbol to sell
   * @param date the date to sell at
   */
  public void sellAllStockForPortfolio(String portfolioName, String symbol, Date date);

  /**
   * returns a bar graph of the performance over time for the specified portfolio,
   * over a specified period of time.
   *
   * @param portfolioName the portfolio name
   * @param startDate the start date
   * @param endDate the end date
   * @return the bar graph
   */
  public String performanceOverTimeForPortfolio(String portfolioName, Date startDate,
                                                  Date endDate);

  /**
   * gets the monetary value of a portfolio at a specified date.
   *
   * @param portfolioName the portfolio name
   * @param date the date to get the value at
   * @return  the monetary value of the portfolio
   */
  public double getPortfolioValue(String portfolioName, Date date);
}
