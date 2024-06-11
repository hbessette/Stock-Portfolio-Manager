package stocks.model.portfolio;

import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.Map;

public interface StockPortfolioTimed extends AStockPortfolio {
  public void purchase(String name, Date date, int shares);

  public void sell(String name, Date date, double shares);

  public String[] getComposition(Date date);

  public String[] getDistribution(Date date);

  public void rebalance(Date date, Map<Stocks, Double> percentages);

  public String performanceOverTime(Date dateStart, Date dateEnd);
}
