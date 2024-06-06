package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.portfolio.StockPortfolio;
import stocks.model.stock.StocksImpl;
import stocks.view.StockView;

import java.util.NoSuchElementException;

public class AddStock extends ASymbolControllerCommand {
  private final String portfolioName;
  private final int shares;

  public AddStock(String portfolioName, String symbol, int shares) {
    super(symbol);
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
