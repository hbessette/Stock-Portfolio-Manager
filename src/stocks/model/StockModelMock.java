package stocks.model;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * To mock the model to confirm inputs from controller.
 */
public class StockModelMock implements StockModel {
  private StringBuilder log;

  public StockModelMock() {
    this.log = new StringBuilder();
  }

  @Override
  public void addPortfolio(String name) {
    this.log.append("name: ").append(name).append(System.lineSeparator());
  }

  @Override
  public void removePortfolio(String name) {
    this.log.append("name: ").append(name).append(System.lineSeparator());
  }

  @Override
  public double evaluatePortfolio(String name, Date date) {
    this.log.append(String.format("name: %s, year: %d, month: %d, day: %d", name, date.getYear(),
            date.getMonth() + 1, date.getDate())).append(System.lineSeparator());
    return 0;
  }

  @Override
  public Set<String> getAllPortfolios() {
    this.log.append("getAllPortfolios() successfully called.").append(System.lineSeparator());
    return Set.of();
  }

  @Override
  public String returnLog() {
    return this.log.toString();
  }

  @Override
  public void savePortfolio(String name) throws NoSuchElementException {
    this.log.append("name: ").append(name).append(System.lineSeparator());
  }

  @Override
  public void loadPortfolio(String name) throws FileNotFoundException {
    this.log.append("name: ").append(name).append(System.lineSeparator());
  }

  @Override
  public String[] getCompositionForPortfolio(String portfolioName, Date date) {
    this.log.append(String.format("name: %s, year: %d, month: %d, day: %d", portfolioName,
            date.getYear(),
            date.getMonth() + 1, date.getDate())).append(System.lineSeparator());
    return new String[0];
  }

  @Override
  public String[] getDistributionForPortfolio(String portfolioName, Date date) {
    this.log.append(String.format("name: %s, year: %d, month: %d, day: %d", portfolioName,
            date.getYear(),
            date.getMonth() + 1, date.getDate())).append(System.lineSeparator());
    return new String[0];
  }

  @Override
  public void purchaseStockForPortfolio(String portfolioName, String symbol, Date date,
                                        double amount) {
    this.log.append(String.format("name: %s, symbol: %s, year: %d, month: %d, day: %d, amount: %f",
            portfolioName, symbol, date.getYear(),
            date.getMonth() + 1, date.getDate(), amount)).append(System.lineSeparator());
  }

  @Override
  public void sellStockForPortfolio(String portfolioName, String symbol, Date date, double amount) {
    this.log.append(String.format("name: %s, symbol: %s, year: %d, month: %d, day: %d, amount: %f",
            portfolioName, symbol, date.getYear(),
            date.getMonth() + 1, date.getDate(), amount)).append(System.lineSeparator());
  }

  @Override
  public double getPriceChangeForStock(String symbol, Date startDate, Date endDate) {
    this.log.append(String.format("symbol: %s, startYear: %d, startMonth: %d, startDay: %d, " +
            "endYear: %d, endMonth: %d, endDay: %d", symbol, startDate.getYear(),
            startDate.getMonth() + 1, startDate.getDate(), endDate.getYear(),
            endDate.getMonth() + 1, endDate.getDate())).append(System.lineSeparator());
    return 0;
  }

  @Override
  public double getXDayMovingAverage(String symbol, Date startDate, int xDays) {
    this.log.append(String.format("symbol: %s, startYear: %d, startMonth: %d, startDay: %d, " +
                    "xDays: %d", symbol, startDate.getYear(),
            startDate.getMonth() + 1, startDate.getDate(), xDays)).append(System.lineSeparator());
    return 0;
  }

  @Override
  public String[] getXDayCrossovers(String symbol, Date startDate, int xDays) {
    this.log.append(String.format("symbol: %s, startYear: %d, startMonth: %d, startDay: %d, " +
                    "xDays: %d", symbol, startDate.getYear(),
            startDate.getMonth() + 1, startDate.getDate(), xDays)).append(System.lineSeparator());
    return new String[0];
  }

  @Override
  public void rebalancePortfolio(String portfolioName, Date date, Map<String, Double> percentages) {
    StringBuilder s = new StringBuilder();
    for (String f : percentages.keySet()) {
      s.append(f).append(":").append(percentages.get(f)).append(",");
    }
    this.log.append(String.format("name: %s, year: %d, month: %d, day: %d," + s,
            portfolioName, date.getYear(), date.getMonth() + 1, date.getDate())).append(System.lineSeparator());
  }

  @Override
  public String[] getStockNamesForPortfolio(String portfolioName, Date date) {
    this.log.append(String.format("name: %s, year: %d, month: %d, day: %d", portfolioName,
            date.getYear(),
            date.getMonth() + 1, date.getDate())).append(System.lineSeparator());
    return new String[0];
  }

  @Override
  public void sellAllStockForPortfolio(String portfolioName, String symbol, Date date) {
    this.log.append(String.format("name: %s, symbol: %s, year: %d, month: %d, day: %d",
            portfolioName, symbol, date.getYear(),
            date.getMonth() + 1, date.getDate())).append(System.lineSeparator());
  }

  @Override
  public String performanceOverTimeForPortfolio(String portfolioName, Date startDate,
                                                Date endDate) {
    this.log.append(String.format("name: %s, startYear: %d, startMonth: %d, startDay: %d, " +
                    "endYear: %d, endMonth: %d, endDay: %d", portfolioName, startDate.getYear(),
            startDate.getMonth() + 1, startDate.getDate(), endDate.getYear(),
            endDate.getMonth() + 1, endDate.getDate())).append(System.lineSeparator());
    return "";
  }
}
