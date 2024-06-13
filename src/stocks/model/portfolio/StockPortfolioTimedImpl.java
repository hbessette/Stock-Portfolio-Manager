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

  // Gets the most recent StockPortfolioTimeStatus since the date specified.
  // Useful for getting the "currently active" data.
  // Do not call if the date specified is a date already in the stockCompositions map.
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

  // Gets all the StockPortfolioTimeStatuses after the specified date.
  // Returns an empty map if none are after.
  private Map<Date, StockPortfolioTimeStatus> portfolioStatusesAfterDate(Date date) {
    if (this.stockCompositions.isEmpty()) {
      return new HashMap<Date, StockPortfolioTimeStatus>();
    }

    Set<Date> dateSet = this.stockCompositions.keySet();

    Map<Date, StockPortfolioTimeStatus> returnMap = new HashMap<Date, StockPortfolioTimeStatus>();

    for (Date statusDate : dateSet) {
      if (statusDate.compareTo(date) > 0) {
        returnMap.put(statusDate, this.stockCompositions.get(statusDate));
      }
    }

    return returnMap;
  }

  @Override
  public void purchase(String name, Date date, double shares) {
    List<StockAndShares> stocksAndShares;

    if (this.stockCompositions.containsKey(date)) {
      // If the date specified already has data for it
      stocksAndShares = this.stockCompositions.get(date).getStocksAndShares();
      purchaseHelper(date, stocksAndShares, name, shares);
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
      }
      else {
        stocksAndShares = timeStatus.getStocksAndShares();
        purchaseHelper(date, stocksAndShares, name, shares);
      }
    }

    Map<Date, StockPortfolioTimeStatus> afterStatuses = portfolioStatusesAfterDate(date);
    for (Date afterDate : afterStatuses.keySet()) {
      purchaseHelper(afterDate, this.stockCompositions.get(afterDate).getStocksAndShares(),
              name, shares);
    }
  }

  private void purchaseHelper(Date date, List<StockAndShares> stocksAndShares,
                              String name, double shares) {
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
  public void sellAll(String name, Date date) throws IllegalArgumentException {
    List<StockAndShares> sasList = this.stockCompositions.get(date).getStocksAndShares();
    for (StockAndShares sas : sasList) {
      if (sas.getStock().getSymbol().equals(name)) {
        sell(name, date, sas.getShares());
        return;
      }
    }
    throw new IllegalArgumentException("You have no shares of this stock.");
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

    Map<Date, StockPortfolioTimeStatus> afterStatuses = portfolioStatusesAfterDate(date);
    for (Date afterDate : afterStatuses.keySet()) {
      List<StockAndShares> dateCurrentStockAndShares =
              this.stockCompositions.get(afterDate).getStocksAndShares();

      List<StockAndShares> futureStocksAndShares = new ArrayList<StockAndShares>();
      boolean futureFound = false;
      for (StockAndShares sas : dateCurrentStockAndShares) {
        if (sas.getStock().getSymbol().equals(name)) {
          if (sas.getShares() < shares) {
            throw new IllegalArgumentException(
                    "You have already input that you sold an amount of this stock at a later date. "
                            + "Selling it on the date you have now provided would make this "
                            + "impossible; you are selling enough such that your future sale would"
                            + "be selling stocks you do not have. This is not allowed."
            );
          }
          futureFound = true;
          futureStocksAndShares.add(new StockAndShares(
                  sas.getStock(),
                  sas.getShares() - shares
          ));
        }
        else {
          futureStocksAndShares.add(sas);
        }
      }


      if (!futureFound) {
        throw new IllegalArgumentException(
              "You have already input that you sold an amount of this stock at a later date. "
                      + "Selling it on the date you have now provided would make this "
                      + "impossible; you are selling enough such that your future sale would"
                      + "be selling stocks you do not have. This is not allowed."
        );
      }
      this.stockCompositions.put(afterDate, new StockPortfolioTimeStatus(futureStocksAndShares));
    }

    this.stockCompositions.put(date, new StockPortfolioTimeStatus(newStocksAndShares));
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
  private List<StockAndShares> getSASForDistribution(Date date) throws IllegalArgumentException {
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
  public void rebalance(Date date, Map<String, Double> percentages)
          throws IllegalArgumentException
  {
    double total = 0;
    for (Double percentage : percentages.values()) {
      total += percentage;
    }
    if (total != 100) {
      throw new IllegalArgumentException("Percentages do not add up to 100%");
    }

    // total evaluation of each stock
    double totalValue = 0;
    // map of each stocks value
    Map<StockAndShares, Double> composition = new HashMap<>();
    for (StockPortfolioTimeStatus sts : this.stockCompositions.values()) {
      for (StockAndShares sas : sts.getStocksAndShares()) {
        double value = sas.getStock().getStockDayStatus(date).getClosingTime() * sas.getShares();
        composition.put(sas, value);
        totalValue += value;
      }
    }

    for (StockAndShares sas : composition.keySet()) {
      double percentage = percentages.get(this.);
      double value = composition.get(sas);
      double portion = value / totalValue;
      if (portion != percentage) {
        double stocksToBuyOrSell = (portion - percentage) * sas.getShares();
        if (stocksToBuyOrSell > 0) {
          this.sell(sas.getStock().getSymbol(), date, stocksToBuyOrSell);
        } else {
          this.purchase(sas.getStock().getSymbol(), date, Math.abs(stocksToBuyOrSell));
        }
      }
    }



  }

  @Override
  public String performanceOverTime(Date dateStart, Date dateEnd) throws IllegalArgumentException {
    if (dateStart.compareTo(dateEnd) >= 0) {
      throw new IllegalArgumentException("The start date you specified is either the same date"
              + " as the end date, or is after the end date. Please order your arguments such that"
              + " the end date is after the start date.");
    }

    String mode;
    int monthsBetween = (((dateEnd.getYear() -  dateStart.getYear()) * 12) +
            (dateEnd.getMonth() - dateStart.getMonth()));

    if (monthsBetween > 5*12) {
      mode = "years";
    }
    else if (monthsBetween > 16) {
      mode = "quarters";
    }
    else if (monthsBetween > 1) {
      mode = "months";
    }
    else {
      mode = "days";
    }

    Map<Date, Double> evaluationsAtKeyPoints = new HashMap<Date, Double>();
    long millisecondConversionNumber = (1000 * 60 * 60 * 24);
    long startTimeDays = dateStart.getTime() / millisecondConversionNumber;
    long endTimeDays = dateEnd.getTime() / millisecondConversionNumber;
    int iterAmountDays;
    switch (mode) {
      case "years" :
        iterAmountDays = 366;
        break;
      case "quarters" :
        iterAmountDays = 92;
        break;
      case "months" :
        iterAmountDays = 31;
        break;
      default :
        iterAmountDays = 1;
    }

    double maxVal = 0;
    for (long i = startTimeDays; i < endTimeDays; i+= iterAmountDays) {
      Date dd = new Date();
      dd.setTime(i * millisecondConversionNumber);
      double value = evaluate(dd);

      if (value > maxVal) {
        maxVal = value;
      }

      evaluationsAtKeyPoints.put(dd, value);
    }

    String scaler = String.valueOf((int)Math.ceil(maxVal / 20));

    int starScale = Integer.parseInt(scaler.charAt(0) + "0".repeat(scaler.length() - 1));

    // Will be returned
    StringBuilder returnStrings = new StringBuilder();

    Set<Date> keyPointDateSet =  evaluationsAtKeyPoints.keySet();
    List<Date> keyPointDatesOrdered = new ArrayList<Date>(keyPointDateSet);
    Collections.sort(keyPointDatesOrdered);

    for (Date keyDate : keyPointDatesOrdered) {
      String dateDisplay;
      String[] monthsIdx = new String[] {
              "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
      };
      switch (mode) {
        case "years" :
          dateDisplay = String.valueOf(keyDate.getYear());
          break;
        case "quarters" :
        case "months" :
          dateDisplay = monthsIdx[keyDate.getMonth()] + " " + String.valueOf(keyDate.getYear());
          break;
        default :
          String day_ = String.valueOf(keyDate.getDate());
          if (day_.length() == 1) {
            day_ = "0" + day_;
          }
          dateDisplay = day_ + " " + monthsIdx[keyDate.getMonth()] + " "
                  + String.valueOf(keyDate.getYear());
      }

      String lineAdd = dateDisplay + ": " +
              "*".repeat((int) Math.ceil(evaluationsAtKeyPoints.get(keyDate) / starScale));

      returnStrings.append(lineAdd).append("\n");
    }

    returnStrings.append("\n").append("Scale: * = $").append(String.valueOf(starScale));
    return returnStrings.toString();
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

  @Override
  public String[] getStockNames() {
    StringBuilder returnOutput = new StringBuilder();
    for (StockPortfolioTimeStatus s : this.stockCompositions.values()) {
    }
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
