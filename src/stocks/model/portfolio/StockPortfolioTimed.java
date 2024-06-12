package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.Map;

public interface StockPortfolioTimed extends AStockPortfolio {
  public void purchase(String name, Date date, double shares);

  public void sell(String name, Date date, double shares);

  public void sellAll(String name, Date date);

  public String[] getComposition(Date date);

  public String[] getDistribution(Date date);

  public void rebalance(Date date, Map<String, Double> percentages);

  public String performanceOverTime(Date dateStart, Date dateEnd);

  public String[] getData();

  public String[] getStockNames();
}
