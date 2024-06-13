package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Scanner;

/**
 * To save a portfolio in StockPortfolio folder.
 */
public class SavePortfolio implements StockControllerCommand {

  private final String portfolioName;

  /**
   * To create a save portfolio object.
   * @param s : to take in user inputs
   * @param view : to prompt user inputs
   */
  public SavePortfolio(Scanner s, StockView view) {
    view.show("Enter the portfolio name to save: ");
    this.portfolioName = s.next();
  }

  @Override
  public void start(StockView view, StockModel model) {
    model.savePortfolio(this.portfolioName);
    view.show(this.portfolioName + " successfully saved.");
  }
}
