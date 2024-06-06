package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.stock.StocksImpl;
import stocks.view.StockView;

public class RemoveStock extends ASymbolControllerCommand {
  private final String portfolioName;

  public RemoveStock(String portfolioName, String symbol) {
    super(symbol);
    this.portfolioName = portfolioName;
  }

  @Override
  public void start(StockView view, StockModel model) {
    model.getPortfolioByName(this.portfolioName).removeStock(new StocksImpl(this.symbol));
    view.show("Successfully removed " + this.symbol + " from " + this.portfolioName);
  }
}
