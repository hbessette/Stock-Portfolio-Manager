package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

/**
 * To remove a portfolio from the user's list of portfolios.
 */
public class RemovePortfolio extends APortfolioCommand {

  /**
   * To create a RemovePortfolio command.
   * @param portfolioName target portfolio to remove
   */
  public RemovePortfolio(String portfolioName) {
    super(portfolioName);
  }

  @Override
  public void start(StockView view, StockModel model) {
      model.removePortfolio(this.portfolioName);
      view.show("Successfully removed portfolio " + this.portfolioName);
  }
}
