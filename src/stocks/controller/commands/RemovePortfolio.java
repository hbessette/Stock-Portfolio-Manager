package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

public class RemovePortfolio extends APortfolioCommand {

  public RemovePortfolio(String portfolioName) {
    super(portfolioName);
  }

  @Override
  public void start(StockView view, StockModel model) {
    try {
      model.removePortfolio(this.portfolioName);
      view.show(model.returnLog());
    } catch (Exception e) {
      view.show(e.getMessage());
    }
  }
}
