package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

/**
 * A command to create a portfolio.
 */
public class AddPortfolio extends APortfolioCommand {

  /**
   * Constructs this command.
   *
   * @param portfolioName the name of the portfolio
   */
  public AddPortfolio(String portfolioName) {
    super(portfolioName);
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
