package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;

import java.util.List;

/**
 * Represents a list of portfolio stocks and their shares on a specific day.
 */
public class StockPortfolioTimeStatus {
  private List<StockAndShares> stocksAndShares;

  public StockPortfolioTimeStatus(List<StockAndShares> stocksAndShares) {
    this.stocksAndShares = stocksAndShares;
  }

  public List<StockAndShares> getStocksAndShares() {
    return stocksAndShares;
  }
}
