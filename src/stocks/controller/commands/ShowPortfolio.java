package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.NoSuchElementException;

public class ShowPortfolio extends AStockControllerCommand {
  private final String portfolioName;

  public ShowPortfolio(String portfolioName, String symbol) {
    super(symbol);
    this.portfolioName = portfolioName;
  }

  @Override
  public void start(StockView view, StockModel model) {
    try {
      view.show(model.getPortfolioByName(this.portfolioName).getAllStocks().toString());
    } catch (NoSuchElementException e) {
      view.show(e.getMessage());
    }
  }
}
