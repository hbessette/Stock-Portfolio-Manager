package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.portfolio.shares.StockAndShares;
import stocks.view.StockView;

import java.util.Arrays;
import java.util.List;

public class ShowPortfolioStocks extends APortfolioCommand {

  public ShowPortfolioStocks(String portfolioName) {
    super(portfolioName);
  }

  @Override
  public void start(StockView view, StockModel model) {
    List<StockAndShares> stockAndShares =
            model.getPortfolioByName(this.portfolioName).getAllStocksAndShares();
    for (StockAndShares s : stockAndShares) {
      view.show(s.getStock().getSymbol() + ": " + s.getShares());
    }
  }
}
