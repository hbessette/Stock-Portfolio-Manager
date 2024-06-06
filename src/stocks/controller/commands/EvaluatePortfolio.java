package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;

/**
 * To evaluate the total price of a portfolio based on the date, its number of shares, and
 * closing prices of all stocks.
 */
public class EvaluatePortfolio extends APortfolioCommand {
  private final Date date;

  /**
   * To create an EvaluatePortfolio object.
   *
   * @param portfolioName target portfolio
   * @param month         target month
   * @param day           target day
   * @param year          target year
   */
  public EvaluatePortfolio(String portfolioName, int month, int day, int year) {
    super(portfolioName);
    this.date = new Date(year, month, day);
  }

  @Override
  public void start(StockView view, StockModel model) {
    double evaluation = model.evaluatePortfolio(this.portfolioName, this.date);
    view.show("Evaluation of " + this.portfolioName + " on " + (this.date.getMonth() + 1)
            + "-" + this.date.getDate() + "-" + this.date.getYear() + " is "
            + evaluation + ".");
  }
}