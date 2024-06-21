package stocks.model.portfolio;

import java.util.Date;
import java.util.Map;

/**
 * A stock portfolio that takes timing into consideration (when a stock was bought/sold, etc.).
 */
public interface StockPortfolioTimed extends AStockPortfolio {
  /**
   * Buy a stock at a specific time.
   *
   * @param name the stock symbol
   * @param date the date to buy at
   * @param shares the amount of shares
   */
  public void purchase(String name, Date date, double shares);

  /**
   * Sell a stock at a specific time.
   *
   * @param name the stock symbol
   * @param date the date to sell at
   * @param shares the amount of shares
   */
  public void sell(String name, Date date, double shares);

  /**
   * Sell a stock at a specific time.
   *
   * @param name the stock symbol
   * @param date the date to sell at
   */
  public void sellAll(String name, Date date);

  /**
   * Gets a portfolio's composition; the stocks in it and the shares of them owned.
   *
   * @param date the date to query
   * @return the composition of a portfolio
   */
  public String[] getComposition(Date date);

  /**
   * Gets a portfolio's distribution; the stocks in it and their monetary value.
   *
   * @param date the date to query
   * @return the distribution of a portfolio
   */
  public String[] getDistribution(Date date);

  /**
   * Rebalanes this portfolio by a specified map of the stocks and desired percentages.
   *
   * @param date the date to rebalance at
   * @param percentages a map of stock symbols and their percentages
   */
  public void rebalance(Date date, Map<String, Double> percentages);

  /**
   * returns a bar graph of the performance over time for this portfolio,
   * over a specified period of time.
   *
   * @param dateStart the start date
   * @param dateEnd the end date
   * @return the bar graph
   */
  public String performanceOverTime(Date dateStart, Date dateEnd);

  /**
   * Gets data for this portfolio, so it can be saved to a file.
   *
   * @return this portfolio's data
   */
  public String[] getData();

  /**
   * Gets all stocks owned in this portfolio at a specific time.
   *
   * @param date the date to query
   * @return symbols of all stocks owned
   */
  public String[] getStockNames(Date date);

  /**
   * Returns the monetary value of this portfolio at the specified date.
   *
   * @param date the date to query
   * @return the monetary value of this portfolio
   */
  public double getValue(Date date);
}
