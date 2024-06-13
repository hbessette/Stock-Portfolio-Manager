package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

/**
 * To evaluate the total price of a portfolio based on the date, its number of shares, and
 * closing prices of all stocks.
 */
public class EvaluatePortfolio implements StockControllerCommand {
  private final String portfolioName;
  private final Date date;

  /**
   * To create an EvaluatePortfolio object.
   *
   * @param s : to take in user inputs
   * @param view : to prompt the user
   */
  public EvaluatePortfolio(Scanner s, StockView view) {
    view.show("Enter the portfolio name to evaluate: ");
    this.portfolioName = s.next();
    view.show("Enter the year to evaluate: ");
    int year = s.nextInt();
    view.show("Enter the month to evaluate: ");
    int month = s.nextInt() - 1;
    view.show("Enter the day to evaluate: ");
    int day = s.nextInt();
    this.date = new Date(year, month, day);
  }

  @Override
  public void start(StockView view, StockModel model) {
    double evaluation = model.evaluatePortfolio(this.portfolioName, this.date);
    view.show("Evaluation of " + this.portfolioName + " on " + (this.date.getMonth() + 1)
            + "-" + this.date.getDate() + "-" + this.date.getYear() + " is $"
            + evaluation + ".");
  }
}