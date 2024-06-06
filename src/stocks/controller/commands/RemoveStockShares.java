package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

/**
 * To remove a certain number of shares from the specified portfolio's given stock.
 */
public class RemoveStockShares extends ASymbolControllerCommand {
  private final String portfolioName;
  private final int shares;

  /**
   * To create a removeStockShares command.
   * @param portfolioName target portfolio
   * @param symbol target stock
   * @param shares number of shares to remove
   */
  public RemoveStockShares(String portfolioName, String symbol, int shares) {
    super(symbol);
    this.portfolioName = portfolioName;
    this.shares = shares;
  }

  @Override
  public void start(StockView view, StockModel model) {
      model.getPortfolioByName(this.portfolioName).removeStockShares(model.getStockByName(this.symbol), this.shares);
      view.show("Removed " + this.shares + " shares from " + this.symbol + " in " + this.portfolioName + ".");
  }
}
