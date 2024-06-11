package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class StockPortfolioTimedImpl implements StockPortfolioTimed{
  private Map<Date, StockPortfolioTimeStatus> stockCompositions;
  private StringBuilder log;

  /**
   * Creates a new stock portfolio. It is initialized with no stocks.
   */
  public StockPortfolioTimedImpl() {
    this.stockCompositions = new HashMap<Date, StockPortfolioTimeStatus>();
    this.log = new StringBuilder();
  }

  private StockPortfolioTimeStatus lastTimeSinceDate(Date date) {
    if (this.stockCompositions.isEmpty()) {
      return null;
    }
    Set<Date> dateSet = this.stockCompositions.keySet();
    List<Date> datesOrdered = new ArrayList<Date>(dateSet);
    Collections.sort(datesOrdered);

  }

  @Override
  public void purchase(String name, Date date, int shares) {

  }

  @Override
  public void sell(String name, Date date, double shares) {

  }

  @Override
  public String[] getComposition(Date date) {

  }

  @Override
  public String[] getDistribution(Date date) {

  }

  @Override
  public void rebalance(Date date, Map<Stocks, Double> percentages) {

  }

  @Override
  public String performanceOverTime(Date dateStart, Date dateEnd) {
    // Should return string with:
    // Performance of portfolio XXX from YYY to ZZZ\n
    // (performance bar graph)
    // Scale: * = $(scale)
  }

  @Override
  public String returnLog() {

  }

  @Override
  public double evaluate(Date date) {

  }
}
