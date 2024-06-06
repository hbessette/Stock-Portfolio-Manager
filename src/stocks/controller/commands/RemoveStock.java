package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.stock.StocksImpl;
import stocks.view.StockView;

/**
 * To completely remove a stock from a portfolios stock list.
 */
public class RemoveStock extends ASymbolControllerCommand {
  private final String portfolioName;

  /**
   * To create a RemoveStock command.
   *
   * @param portfolioName target portfolio
   * @param symbol        target stock to remove from portfolio
   */
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
