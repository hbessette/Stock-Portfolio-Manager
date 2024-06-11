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
  /**
   * Returns the composition data for the last date of which there is composition data in this
   * portfolio stored from the given date.
   * (i.e. if given date is 6/11/2024 and there is data for 6/8/2024,
   * and none of the days in between, 6/8/2024's data will be returned).
   * If there is no previous data (i.e. this date is further in the past than anything stored in
   * the portfolio, or the portfolio is empty of data), null is returned.
   *
   * @param date the date to look back from
   * @return the most recent StockPortfolioTimeStatus since the provided date, or null if none.
   */

  private StockPortfolioTimeStatus lastStatusSinceDate(Date date) {

    if (this.stockCompositions.isEmpty()) {
      return null;
    }
    Set<Date> dateSet = this.stockCompositions.keySet();
    List<Date> datesOrdered = new ArrayList<Date>(dateSet);

    if (datesOrdered.contains(date)) {
      throw new IllegalArgumentException(
              "lastStatusSinceDate was called with a date already in the portfolio.");
    }

    datesOrdered.add(date);

    Collections.sort(datesOrdered);

    int indexThisDate = datesOrdered.indexOf(date);

    if (indexThisDate == 0) {
      return null;
    }

    Date dateAt = datesOrdered.get(indexThisDate-1);

    return this.stockCompositions.get(dateAt);
  }

  @Override
  public void purchase(String name, Date date, int shares) {
    List<StockAndShares> stocksAndShares;

    if (this.stockCompositions.containsKey(date)) {
      // If the date specified already has data for it
      stocksAndShares = this.stockCompositions.get(date).getStocksAndShares();
    }
    else {
      // If the date specified does not already have data for it
      StockPortfolioTimeStatus timeStatus = lastStatusSinceDate(date);

      // If there is no previous time status, just make a new one for this date with only
      // the added stock.
      if (timeStatus == null) {
        List<StockAndShares> new_ = new ArrayList<StockAndShares>();
        new_.add(new StockAndShares(
                new StocksImpl(name), shares
        ));
        this.stockCompositions.put(date,
                new StockPortfolioTimeStatus(new_));
        return;
      }

      stocksAndShares = timeStatus.getStocksAndShares();
    }

    List<StockAndShares> newStocksAndShares = new ArrayList<StockAndShares>();

    boolean found = false;
    for (StockAndShares sas : stocksAndShares) {
      if (sas.getStock().getSymbol().equals(name)) {
        found = true;
        newStocksAndShares.add(new StockAndShares(
                sas.getStock(),
                sas.getShares() + shares
        ));
      }
      else {
        newStocksAndShares.add(sas);
      }
    }
    if (!found) {
      newStocksAndShares.add(new StockAndShares(
              new StocksImpl(name),
              shares
      ));
    }

    this.stockCompositions.put(date, new StockPortfolioTimeStatus(newStocksAndShares));
  }

  @Override
  public void sell(String name, Date date, double shares) throws IllegalArgumentException {
    List<StockAndShares> stocksAndShares;

    if (this.stockCompositions.containsKey(date)) {
      // If the date specified already has data for it
      stocksAndShares = this.stockCompositions.get(date).getStocksAndShares();
    }
    else {
      // If the date specified does not already have data for it
      StockPortfolioTimeStatus timeStatus = lastStatusSinceDate(date);

      if (timeStatus == null) {
        throw new IllegalArgumentException(
                "You cannot sell shares at this date because no shares are owned on this date."
        );
      }
      stocksAndShares = timeStatus.getStocksAndShares();
    }
    List<StockAndShares> newStocksAndShares = new ArrayList<StockAndShares>();
    boolean found = false;
    for (StockAndShares sas : stocksAndShares) {
      if (sas.getStock().getSymbol().equals(name)) {
        if (sas.getShares() < shares) {
          throw new IllegalArgumentException(
                  "You cannot sell more shares than you have. On this date you have "
                          + sas.getShares()
          );
        }
        found = true;
        newStocksAndShares.add(new StockAndShares(
                sas.getStock(),
                sas.getShares() - shares
        ));
      }
      else {
        newStocksAndShares.add(sas);
      }
    }
    if (!found) {
      throw new IllegalArgumentException("You cannot sell shares of a stock you have not bought.");
    }

    this.stockCompositions.put(date, new StockPortfolioTimeStatus((newStocksAndShares)));

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
