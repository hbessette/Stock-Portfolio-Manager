package stocks.model;

import stocks.model.macros.StockMacroPriceChange;
import stocks.model.macros.StockMacroXDayCrossovers;
import stocks.model.macros.StockMacroXDayMovingAverage;
import stocks.model.portfolio.StockPortfolioTimed;
import stocks.model.portfolio.StockPortfolioTimedImpl;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A model which allows the user to create portfolios.
 * Stocks can be added to a portfolio with a specified number of shares.
 * More shares can be added, or shares can be removed.
 * The value of the portfolio can be evaluated on any given date.
 * Allows one to get a portfolio by name, and evaluate it on any date.
 */
public class StockModelImpl implements StockModel {
  private Map<String, StockPortfolioTimed> portfolios;

  /**
   * Creates a new StockModel. It is initialized with no portfolios.
   */
  public StockModelImpl() {
    this.portfolios = new HashMap<String, StockPortfolioTimed>();
  }

  /**
   * Adds a new portfolio to the model.
   *
   * @param name the name of the portfolio
   */
  @Override
  public void addPortfolio(String name) {
    this.portfolios.put(name, new StockPortfolioTimedImpl());
  }

  /**
   * removes a portfolio from the model.
   *
   * @param name the name of the portfolio
   */
  @Override
  public void removePortfolio(String name) {
    if (this.portfolios.containsKey(name)) {
      this.portfolios.remove(name);
    }
  }

  /**
   * Gets a portfolio by its name, if it exists in this model.
   *
   * @param name the name of the portfolio
   * @return the portfolio
   */
  private StockPortfolioTimed getPortfolioByName(String name) throws NoSuchElementException {
    try {
      return this.portfolios.get(name);
    } catch (NullPointerException e) {
      throw new NoSuchElementException("No portfolio exists.");
    }
  }

  /**
   * Evaluates a portfolio on any date.
   * This gets the total value of the portfolio,
   * determined by the prices of stocks on that date and the number of shares owned.
   *
   * @param name the name of the portfolio
   * @param date the date to evaluate at
   * @return the value of the portfolio
   */
  @Override
  public double evaluatePortfolio(String name, Date date) throws NoSuchElementException {
    try {
      return this.portfolios.get(name).evaluate(date);
    } catch (NullPointerException e) {
      throw new NoSuchElementException("No portfolio exists.");
    }
  }

  /**
   * Creates and returns a new stock by its symbol.
   *
   * @param symbol a stock symbol (e.g. "GOOG" for Google)
   * @return the new stock
   */
  private Stocks getStockByName(String symbol) {
    return new StocksImpl(symbol);
  }

  /**
   * Gets a set of all portfolios names.
   *
   * @return a set of all portfolios names.
   */
  @Override
  public Set<String> getAllPortfolios() {
    return this.portfolios.keySet();
  }

  /**
   * Gets the log of what has been added and removed.
   *
   * @return the log
   */
  @Override
  public String returnLog() {
    return "";
  }

  @Override
  public void savePortfolio(String name) throws NoSuchElementException {
    StockPortfolioTimed portfolio;
    if (!this.portfolios.containsKey(name)) {
      throw new NoSuchElementException("No portfolio exists.");
    } else {
      portfolio = this.portfolios.get(name);
    }
    String[] output = portfolio.getData();
    File file = new File("StockPortfolios/" + name + ".txt");
    try {
      if (file.exists()) {
        file.delete();
      }
      file.createNewFile();
      FileWriter fileWriter = new FileWriter("StockPortfolios/" + name + ".txt");
      for (String line : output) {
        fileWriter.write(line + System.lineSeparator());
      }
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void loadPortfolio(String name) throws FileNotFoundException {
    StringBuilder output = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(
              "StockPortfolios/" + name + ".txt"
      ));
      String line = reader.readLine();

      while (line != null) {
        output.append(line + System.lineSeparator());
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      throw new FileNotFoundException("Portfolio does not exist");
    }
    StockPortfolioTimed portfolio =
            new StockPortfolioTimedImpl(output.toString().split(System.lineSeparator()));
    this.portfolios.put(name, portfolio);
  }

  @Override
  public String[] getCompositionForPortfolio(String portfolioName, Date date) {
    return this.getPortfolioByName(portfolioName).getComposition(date);
  }

  @Override
  public String[] getDistributionForPortfolio(String portfolioName, Date date) {
    return this.getPortfolioByName(portfolioName).getDistribution(date);
  }

  @Override
  public void purchaseStockForPortfolio(String portfolioName, String symbol, Date date,
                                        double amount) {
    this.getPortfolioByName(portfolioName).purchase(symbol, date, amount);
  }

  @Override
  public void sellStockForPortfolio(String portfolioName, String symbol, Date date, double amount) {
    this.getPortfolioByName(portfolioName).sell(symbol, date, amount);
  }

  @Override
  public double getPriceChangeForStock(String symbol, Date startDate, Date endDate) {
    double priceChange = new StockMacroPriceChange(startDate, endDate).apply(
            this.getStockByName(symbol));
    priceChange = Math.round(priceChange * 100.00) / 100.00;
    return priceChange;
  }

  @Override
  public double getXDayMovingAverage(String symbol, Date startDate, int xDays) {
    return new StockMacroXDayMovingAverage(startDate, xDays).apply(
            this.getStockByName(symbol));
  }

  @Override
  public String[] getXDayCrossovers(String symbol, Date startDate, int xDays) {
    Date[] dates = new StockMacroXDayCrossovers(startDate, xDays)
            .apply(this.getStockByName(symbol));
    StringBuilder s = new StringBuilder();
    for (Date date : dates) {
      s.append((date.getMonth() + 1)).append("-").append(date.getDate()).append("-")
              .append(date.getYear()).append(System.lineSeparator());
    }
    return s.toString().split(System.lineSeparator());
  }

  @Override
  public void rebalancePortfolio(String portfolioName, Date date, Map<String, Double> percentages) {
    this.getPortfolioByName(portfolioName).rebalance(date, percentages);
  }

  @Override
  public String[] getStockNamesForPortfolio(String portfolioName, Date date) {
    return this.getPortfolioByName(portfolioName).getStockNames(date);
  }

  @Override
  public void sellAllStockForPortfolio(String portfolioName, String symbol, Date date) {
    this.getPortfolioByName(portfolioName).sellAll(symbol, date);
  }

  @Override
  public String performanceOverTimeForPortfolio(String portfolioName, Date startDate,
                                                  Date endDate) {
    return this.getPortfolioByName(portfolioName).performanceOverTime(startDate, endDate);
  }

  @Override
  public double getPortfolioValue(String portfolioName, Date date) {
    return this.getPortfolioByName(portfolioName).getValue(date);
  }
}
