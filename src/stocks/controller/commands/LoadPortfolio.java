package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * To load a portfolio from StockPortfolio folder.
 */
public class LoadPortfolio implements StockControllerCommand {

  private final String portfolioName;

  /**
   * To create a load portfolio object.
   * @param s : to take in user inputs
   * @param view : to prompt user inputs
   */
  public LoadPortfolio(Scanner s, StockView view) {
    view.show("Enter the portfolio name to load: ");
    this.portfolioName = s.next();
  }

  @Override
  public void start(StockView view, StockModel model) {
    try {
      model.loadPortfolio(this.portfolioName);
      view.show(this.portfolioName + " successfully loaded.");
    } catch (FileNotFoundException e) {
      view.show("Portfolio not found.");
    }
  }
}
