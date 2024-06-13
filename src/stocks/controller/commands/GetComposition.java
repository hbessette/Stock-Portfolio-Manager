package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

/**
 * To represent getting the composition for a specific portfolio on a specific date.
 */
public class GetComposition implements StockControllerCommand {
  private final Date date;
  private final String portfolioName;

  /**
   * To create a get composition command.
   *
   * @param view : to prompt user inputs.
   * @param s    : to take in user inputs.
   */
  public GetComposition(Scanner s, StockView view) {
    view.show("Enter the portfolio name to get composition for: ");
    this.portfolioName = s.next();
    view.show("Enter the year to get composition for: ");
    int year = s.nextInt();
    view.show("Enter the month to get composition for: ");
    int month = s.nextInt();
    view.show("Enter the day to get composition for: ");
    int day = s.nextInt();
    this.date = new Date(year, month, day);
  }

  @Override
  public void start(StockView view, StockModel model) {
    String[] output = model.getCompositionForPortfolio(this.portfolioName, this.date);
    for (String o : output) {
      view.show(o);
    }
  }
}
