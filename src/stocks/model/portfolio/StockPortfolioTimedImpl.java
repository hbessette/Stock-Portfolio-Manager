package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StockPortfolioTimedImpl implements StockPortfolioTimed {
  private Map<Date, StockPortfolioTimeStatus> stockCompositions;
  private StringBuilder log;

  /**
   * Creates a new stock portfolio. It is initialized with no stocks.
   */
  public StockPortfolioTimedImpl() {
    this.stockCompositions = new HashMap<Date, StockPortfolioTimeStatus>();
    this.log = new StringBuilder();
  }

  public StockPortfolioTimedImpl(String[] data) {
    this.stockCompositions = loadData(data);
  }

  private StockPortfolioTimeStatus lastTimeSinceDate(Date date) {
    if (this.stockCompositions.isEmpty()) {
      return null;
    }
    Set<Date> dateSet = this.stockCompositions.keySet();
    List<Date> datesOrdered = new ArrayList<Date>(dateSet);
    Collections.sort(datesOrdered);
    return null;
  }

  @Override
  public void purchase(String name, Date date, int shares) {

  }

  @Override
  public void sell(String name, Date date, double shares) {

  }

  @Override
  public String[] getComposition(Date date) {
    return null;
  }

  @Override
  public String[] getDistribution(Date date) {
    return null;
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
    return null;
  }

  @Override
  public String returnLog() {
    return null;
  }

  @Override
  public String[] getData() {
    StringBuilder returnData = new StringBuilder();
    for (Date date : this.stockCompositions.keySet()) {
      returnData.append((date.getMonth() + 1)).append(",").append(date.getDate()).append(",")
              .append(date.getYear()).append(",");
      for (StockAndShares shares : this.stockCompositions.get(date).getStocksAndShares()) {
        returnData.append(shares.getStock().getSymbol()).append(",").append(shares.getShares())
                .append(",");
      }
      returnData.append(System.lineSeparator());
    }
    return returnData.toString().split(System.lineSeparator());
  }

  private Map<Date, StockPortfolioTimeStatus> loadData(String[] data) {
    Map<Date, StockPortfolioTimeStatus> returnCompositions = new HashMap<>();
    for (String line : data) {
      String[] parsedLine = line.split(",");
      Date date = new Date(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[0]),
              Integer.parseInt(parsedLine[1]));
      returnCompositions.put(date, new StockPortfolioTimeStatus(new ArrayList<>()));
      for (int idx = 3; idx < parsedLine.length; idx += 2) {
        returnCompositions.get(date).getStocksAndShares().add(new StockAndShares(new StocksImpl(parsedLine[idx]), Double.parseDouble(parsedLine[idx + 1])));
      }
    }
    return returnCompositions;
  }

  @Override
  public double evaluate(Date date) {
    return 0;
  }
}
