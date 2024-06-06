package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

public class RemovePortfolio extends APortfolioCommand {

  public RemovePortfolio(String portfolioName) {
    super(portfolioName);
  }

  @Override
  public void start(StockView view, StockModel model) {
      model.removePortfolio(this.portfolioName);
      view.show("Successfully removed portfolio " + this.portfolioName);
  }
}
