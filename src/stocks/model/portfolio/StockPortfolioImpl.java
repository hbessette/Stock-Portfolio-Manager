package stocks.model.portfolio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;

/**
 * Represents a portfolio of stocks.
 * Stocks can be added to a portfolio with a specified number of shares.
 * More shares can be added, or shares can be removed.
 * The value of the portfolio can be evaluated on any given date.
 */
public class StockPortfolioImpl implements StockPortfolio {
  private Map<Stocks, Integer> portfolio;
  private StringBuilder log;

  /**
   * Creates a new stock portfolio. It is initialized with no stocks.
   */
  public StockPortfolioImpl() {
    this.portfolio = new HashMap<Stocks, Integer>();
    this.log = new StringBuilder();
  }

  /**
   * Lists all stocks in this portfolio.
   *
   * @return all stocks in the portfolio
   */
  @Override
  public List<Stocks> getAllStocks() {
    Set<Stocks> setStocks = portfolio.keySet();
    return new ArrayList<Stocks>(setStocks);
  }

  /**
   * Lists all stocks and their shares in this portfolio.
   *
   * @return all stocks and their shares
   */
  @Override
  public List<StockAndShares> getAllStocksAndShares() {

    List<StockAndShares> returnList = new ArrayList<StockAndShares>();
    for (Stocks stock : this.getAllStocks()) {
      returnList.add(
              new StockAndShares(stock, this.portfolio.get(stock))
      );
    }

    return returnList;
  }

  /**
   * Adds a stock to the portfolio, or adds more of it if it has already been added.
   *
   * @param stock  the stock to add
   * @param shares the amount of shares to add
   */
  @Override
  public void addStock(Stocks stock, int shares) {
    if (this.portfolio.containsKey(stock)) {
      this.portfolio.put(stock, this.portfolio.get(stock) + shares);
    } else {
      this.portfolio.put(stock, shares);
    }
    this.log.append("Added:").append(stock.getSymbol())
            .append(",Shares:").append(shares).append(".");
  }

  /**
   * Removes a stock from the portfolio entirely.
   *
   * @param stock a stock to remove
   */
  @Override
  public void removeStock(Stocks stock) {
    this.portfolio.remove(stock);
  }

  /**
   * Removes shares from a stock in the portfolio.
   * If the amount of shares is reduced to 0, the stock is fully removed.
   *
   * @param stock  the stock to remove shares from
   * @param shares the amount of shares to remove
   * @throws IllegalArgumentException if the amount of shares to remove exceeds the owned shares
   */
  @Override
  public void removeStockShares(Stocks stock, int shares) throws IllegalArgumentException {
    if (!this.portfolio.containsKey(stock)) {
      return;
    }

    if (this.portfolio.get(stock) < shares) {
      throw new IllegalArgumentException("Cannot remove more shares than you have.");
    }

    this.portfolio.put(stock, this.portfolio.get(stock) - shares);
    this.log.append("Removed:").append(stock.getSymbol())
            .append(",Shares:").append(shares).append(".");
  }

  /**
   * Gets a stock within the portfolio by its symbol.
   *
   * @param symbol the symbol of the stock to get
   * @return the stock
   * @throws NoSuchElementException if the stock is not in this portfolio
   */
  public Stocks getStockByName(String symbol) throws NoSuchElementException {
    for (Stocks stock : this.getAllStocks()) {
      if (stock.getSymbol().equals(symbol)) {
        return stock;
      }
    }
    throw new NoSuchElementException(symbol + " is not found in portfolio.");
  }

  /**
   * Gets a stock and its shares within the portfolio by its symbol.
   *
   * @param symbol the symbol of the stock to get
   * @return a StockAndShares object containing the stock and its shares
   * @throws NoSuchElementException if the stock does not exist in this portfolio
   */
  @Override
  public StockAndShares getStockAndSharesByName(String symbol) throws NoSuchElementException {
    for (Stocks stock : this.getAllStocks()) {
      if (stock.getSymbol().equals(symbol)) {
        return new StockAndShares(stock, this.portfolio.get(stock));
      }
    }
    throw new NoSuchElementException(symbol + " is not found in portfolio.");
  }

  /**
   * Returns the stock addition log.
   *
   * @return the log
   */
  @Override
  public String returnLog() {
    return this.log.toString();
  }

  /**
   * Evaluates the value of this portfolio on the given day.
   * This is determined by the prices of each stock in the portfolio and the shares owned.
   *
   * @param date the date to evaluate at
   * @return the value of this portfolio on the given day
   */
  @Override
  public double evaluate(Date date) {
    double sum = 0;
    for (Stocks stock : this.getAllStocks()) {
      sum += stock.getStockDayStatus(date).getClosingTime() * this.portfolio.get(stock);
    }
    return sum;
  }
}
