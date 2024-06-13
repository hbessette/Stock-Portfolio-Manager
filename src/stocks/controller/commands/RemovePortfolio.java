package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Scanner;

/**
 * To remove a portfolio from the user's list of portfolios.
 */
public class RemovePortfolio implements StockControllerCommand {

  private final String portfolioName;

  /**
   * To create a remove portfolio object.
   * @param s : to take in user inputs
   * @param view : to prompt user inputs
   */
  public RemovePortfolio(Scanner s, StockView view) {
    view.show("Enter the portfolio name to remove: ");
    this.portfolioName = s.next();
  }

  @Override
  public void start(StockView view, StockModel model) {
    model.removePortfolio(this.portfolioName);
    view.show("Successfully removed portfolio " + this.portfolioName);
  }
}
