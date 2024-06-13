package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;

import java.util.List;

public class StockPortfolioTimeStatus {
  private List<StockAndShares> stocksAndShares;

  public StockPortfolioTimeStatus(List<StockAndShares> stocksAndShares) {
    this.stocksAndShares = stocksAndShares;
  }

  public List<StockAndShares> getStocksAndShares() {
    return stocksAndShares;
  }
}
