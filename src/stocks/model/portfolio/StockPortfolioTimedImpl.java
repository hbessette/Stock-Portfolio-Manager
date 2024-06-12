package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import java.util.*;

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
    this.log = new StringBuilder();
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
    List<StockAndShares> stocksAndShares;
    try {
      stocksAndShares = getSASForDistribution(date);
    } catch (IllegalArgumentException e) {
      return new String[]{"No stocks are owned at this time."};
    }

    List<String> returnLines = new ArrayList<String>();

    for (StockAndShares sas : stocksAndShares) {
      double shares = sas.getShares();
      String sharesWord;
      if (shares == 1) {
        sharesWord = " share";
      } else {
        sharesWord = " shares";
      }
      returnLines.add(sas.getStock().getSymbol() + ": " + String.valueOf(shares) + sharesWord);
    }

    return returnLines.toArray(new String[1]);
  }

  @Override
  public String[] getDistribution(Date date) {
    List<StockAndShares> stocksAndShares;
    try {
      stocksAndShares = getSASForDistribution(date);
    } catch (IllegalArgumentException e) {
      return new String[] {"No stocks are owned at this time."};
    }
    List<String> returnLines = new ArrayList<String>();

    for (StockAndShares sas : stocksAndShares) {
      returnLines.add(sas.getStock().getSymbol() + ": $"
              + String.valueOf(sas.getStock().getStockDayStatus(date).getClosingTime()));
    }

    return returnLines.toArray(new String[1]);
  }

  // Designed specifically for use in getDistribution and getComposition.
  // It is probably not a good idea to use this for anything else.
  private List<StockAndShares> getSASForDistribution(Date date) {
    List<StockAndShares> stocksAndShares = new ArrayList<StockAndShares>();
    if (this.stockCompositions.containsKey(date)) {
      stocksAndShares = this.stockCompositions.get(date).getStocksAndShares();
    }
    else {
      StockPortfolioTimeStatus timeStatus = lastStatusSinceDate(date);
      if (timeStatus == null) {
        throw new IllegalArgumentException("No stocks are owned at this time.");
      }
      stocksAndShares = timeStatus.getStocksAndShares();
    }
    return stocksAndShares;
  }

  /**
   * Returns the value of a portfolio on a specific date.
   * Value is determined by stocks owned * their closing prices on that date.
   * If you evaluate a portfolio on a date before anything was bought, the value is 0.
   *
   * @param date the date to evaluate at
   * @return the value of the portfolio on the given date
   */
  @Override
  public double evaluate(Date date) {
    List<StockAndShares> stocksAndShares = new ArrayList<StockAndShares>();
    if (this.stockCompositions.containsKey(date)) {
      stocksAndShares = this.stockCompositions.get(date).getStocksAndShares();
    }
    else {
      StockPortfolioTimeStatus timeStatus = lastStatusSinceDate(date);
      if (timeStatus == null) {
        return 0;
      }
      stocksAndShares = timeStatus.getStocksAndShares();
    }
    double value = 0;
    for (StockAndShares sas : stocksAndShares) {
      value += sas.getShares() * sas.getStock().getStockDayStatus(date).getClosingTime();
    }
    return value;
  }

  @Override
  public void rebalance(Date startDate, Date balanceDate, Map<Stocks, Double> percentages)
          throws IllegalArgumentException
  {
//    double total = 0;
//    for (Double percentage : percentages.values()) {
//      total += percentage;
//    }
//    if (total != 100) {
//      throw new IllegalArgumentException("Percentages do not add up to 100%");
//    }
//

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
    return this.log.toString();
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
        Date date = new Date(Integer.parseInt(parsedLine[2]), Integer.parseInt(parsedLine[0]) - 1,
                Integer.parseInt(parsedLine[1]));
        returnCompositions.put(date,
                new StockPortfolioTimeStatus(new ArrayList<>()));
        for (int idx = 3; idx < parsedLine.length; idx += 2) {
          returnCompositions.get(date).getStocksAndShares()
                  .add(new StockAndShares(new StocksImpl(parsedLine[idx]),
                          Double.parseDouble(parsedLine[idx + 1])));
        }
      }
    return returnCompositions;
  }

}
