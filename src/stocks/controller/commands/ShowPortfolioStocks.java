package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.portfolio.shares.StockAndShares;
import stocks.view.StockView;

import java.util.List;

/**
 * A command to show all stocks in a portfolio, and their respective shares owned.
 */
public class ShowPortfolioStocks extends APortfolioCommand {

  /**
   * Constructs this command.
   *
   * @param portfolioName the portfolio to show the contents of
   */
  public ShowPortfolioStocks(String portfolioName) {
    super(portfolioName);
  }

  /**
   * Displays the stock symbols and their shares in the portfolio used to construct this object.
   *
   * @param view  a view to display to
   * @param model a model to get portfolios from
   */
  @Override
  public void start(StockView view, StockModel model) {
    List<StockAndShares> stockAndShares =
            model.getPortfolioByName(this.portfolioName).getAllStocksAndShares();
    for (StockAndShares s : stockAndShares) {
      view.show(s.getStock().getSymbol() + ": " + s.getShares());
    }
  }
}
