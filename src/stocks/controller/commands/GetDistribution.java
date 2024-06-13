package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

/**
 * To get the distribution of stocks that a portfolio holds in a specific timeframe.
 */
public class GetDistribution implements StockControllerCommand {
  private final Date date;
  private final String portfolioName;

  /**
   * To create a getDistribution object.
   *
   * @param s    : to get user inputs
   * @param view : to prompt user inputs
   */
  public GetDistribution(Scanner s, StockView view) {
    view.show("Enter portfolio name: ");
    this.portfolioName = s.next();
    view.show("Enter year: ");
    int year = s.nextInt();
    view.show("Enter month: ");
    int month = s.nextInt() - 1;
    view.show("Enter day: ");
    int day = s.nextInt();
    this.date = new Date(year, month, day);
  }

  @Override
  public void start(StockView view, StockModel model) {
    String[] distribution = model.getDistributionForPortfolio(this.portfolioName, this.date);
    for (String s : distribution) {
      view.show(s);
    }
  }
}
