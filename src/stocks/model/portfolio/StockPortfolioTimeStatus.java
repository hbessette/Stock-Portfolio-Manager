package stocks.model.portfolio;

import java.util.ArrayList;
import java.util.List;

import stocks.model.portfolio.shares.StockAndShares;

public class StockPortfolioTimeStatus {
  List<StockAndShares> stocksAndShares;

  public StockPortfolioTimeStatus(ArrayList<StockAndShares> stocksAndShares) {
    this.stocksAndShares = stocksAndShares;
  }

  public List<StockAndShares> getStocksAndShares() {
    return stocksAndShares;
  }
}
