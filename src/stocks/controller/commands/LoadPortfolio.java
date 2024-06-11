package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.io.FileNotFoundException;

public class LoadPortfolio extends APortfolioCommand {

  public LoadPortfolio(String portfolioName) {
    super(portfolioName);
  }

  @Override
  public void start(StockView view, StockModel model) {
    try {
      model.loadPortfolio(this.portfolioName);
    } catch (FileNotFoundException e) {
      view.show("File not found");
    }
    view.show(this.portfolioName + " successfully loaded.");
  }
}
