package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;


/**
 * A command to display the contents of a single portfolio.
 */

public class ShowPortfolio extends APortfolioCommand {

  /**
   * Constructs this command.
   *
   * @param portfolioName the name of the portfolio to show contents of
   */
  public ShowPortfolio(String portfolioName) {
    super(portfolioName);
  }

  /**
   * Displays the contents of the portfolio used to construct this object.
   *
   * @param view  a view to display to
   * @param model a model to get portfolios from
   */
  @Override
  public void start(StockView view, StockModel model) {
    view.show(model.getPortfolioByName(this.portfolioName).getAllStocks().toString());
  }
}
