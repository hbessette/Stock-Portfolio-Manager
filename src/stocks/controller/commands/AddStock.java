package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.portfolio.StockPortfolio;
import stocks.model.stock.StocksImpl;
import stocks.view.StockView;

/**
 * To add a stock to a portfolio with a certain amount of shares. If the stock is already in the
 * portfolio, it adds to the existing stock.
 */
public class AddStock extends ASymbolControllerCommand {
  private final String portfolioName;
  private final int shares;

  /**
   * To create an addStock object.
   * @param portfolioName name of target portfolio
   * @param symbol name of target stock symbol
   * @param shares number of shares to add
   * @exception IllegalArgumentException if shares is negative
   */
  public AddStock(String portfolioName, String symbol, int shares) throws IllegalArgumentException {
    super(symbol);
    if (shares < 0) {
      throw new IllegalArgumentException("Invalid number of shares.");
    }
    this.portfolioName = portfolioName;
    this.shares = shares;
  }

  @Override
  public void start(StockView view, StockModel model) {
    StockPortfolio portfolio = model.getPortfolioByName(this.portfolioName);
    try {
      portfolio.addStock(portfolio.getStockByName(this.symbol), this.shares);
    } catch (Exception e) {
      portfolio.addStock(new StocksImpl(this.symbol), this.shares);
    }
    view.show("Successfully added " + this.symbol + " with " + this.shares + " shares to " +
            this.portfolioName);
  }
}
