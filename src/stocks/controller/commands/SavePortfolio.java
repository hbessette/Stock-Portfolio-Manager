package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

public class SavePortfolio extends APortfolioCommand {

  public SavePortfolio(String portfolioName) {
    super(portfolioName);
  }

  @Override
  public void start(StockView view, StockModel model) {
    model.savePortfolio(this.portfolioName);
    view.show(this.portfolioName + " successfully saved.");
  }
}
