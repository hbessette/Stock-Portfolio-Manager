package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

public class AddPortfolio extends APortfolioCommand {

  public AddPortfolio(String portfolioName) {
    super(portfolioName);
  }

  @Override
  public void start(StockView view, StockModel model) {
      model.addPortfolio(this.portfolioName);
      view.show("Successfully added " + this.portfolioName + ".");
  }
}
