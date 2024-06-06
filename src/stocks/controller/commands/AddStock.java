package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.stock.StocksImpl;
import stocks.view.StockView;

import java.util.NoSuchElementException;

public class AddStock extends AStockControllerCommand {
  private final String portfolioName;

  public AddStock(String portfolioName, String symbol) {
    super(symbol);
    this.portfolioName = portfolioName;
  }

  @Override
  public void start(StockView view, StockModel model) {
    try {
      model.getPortfolioByName(this.portfolioName).addStock(new StocksImpl(this.symbol));
    } catch (NoSuchElementException | IllegalStateException e) {
      view.show(e.getMessage());
    }
  }
}
