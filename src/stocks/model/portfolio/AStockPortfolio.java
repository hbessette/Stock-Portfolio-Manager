package stocks.model.portfolio;

import java.util.Date;

abstract public interface AStockPortfolio {
  /**
   * Evaluates the value of this portfolio on the given day.
   * This is determined by the prices of each stock in the portfolio and the shares owned.
   *
   * @param date the date to evaluate at
   * @return the value of this portfolio on the given day
   */
  public double evaluate(Date date);

  /**
   * Returns the stock addition log.
   *
   * @return the log
   */
  public String returnLog();
}
