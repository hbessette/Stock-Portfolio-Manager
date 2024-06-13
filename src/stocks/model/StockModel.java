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

  public void savePortfolio(String name) throws NoSuchElementException;

  public void loadPortfolio(String name) throws FileNotFoundException;

  public String[] getCompositionForPortfolio(String portfolioName, Date date);

  public String[] getDistributionForPortfolio(String portfolioName, Date date);

  public void purchaseStockForPortfolio(String portfolioName, String symbol, Date date,
                                        double amount);

  public void sellStockForPortfolio(String portfolioName, String symbol, Date date, double amount);

  public double getPriceChangeForStock(String symbol, Date startDate, Date endDate);

  public double getXDayMovingAverage(String symbol, Date startDate, int xDays);

  public String[] getXDayCrossovers(String symbol, Date startDate, int xDays);

  public void rebalancePortfolio(String portfolioName, Date date, Map<String, Double> percentages);

  public String[] getStockNamesForPortfolio(String portfolioName, Date date);

  public void sellAllStockForPortfolio(String portfolioName, String symbol, Date date);

  public String performanceOverTimeForPortfolio(String portfolioName, Date startDate,
                                                  Date endDate);
}
