package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

public class ShowPortfolio extends APortfolioCommand {

  public ShowPortfolio(String portfolioName) {
    super(portfolioName);
  }

  @Override
  public void start(StockView view, StockModel model) {
    view.show(model.getPortfolioByName(this.portfolioName).getAllStocks().toString());
  }
}
