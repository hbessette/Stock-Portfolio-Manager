package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Scanner;

/**
 * A command to create a portfolio.
 */
public class AddPortfolio implements StockControllerCommand {

  private final String portfolioName;

  /**
   * Constructs this command.
   *
   * @param s : scanner for input
   * @param view : to display prompts for user
   */
  public AddPortfolio(Scanner s, StockView view) {
    view.show("Enter portfolio name: ");
    this.portfolioName = s.next();
  }

  /**
   * Creates a portfolio.
   *
   * @param view  a view to display a message
   * @param model a model to process the command
   */
  @Override
  public void start(StockView view, StockModel model) {
    model.addPortfolio(this.portfolioName);
    view.show("Successfully added " + this.portfolioName + ".");
  }
}
