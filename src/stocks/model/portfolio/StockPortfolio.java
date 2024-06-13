package stocks.model.portfolio;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a portfolio of stocks.
 * Stocks can be added to a portfolio with a specified number of shares.
 * More shares can be added, or shares can be removed.
 * The value of the portfolio can be evaluated on any given date.
 */
public interface StockPortfolio extends AStockPortfolio {

  /**
   * Lists all stocks in this portfolio.
   *
   * @return all stocks in the portfolio
   */
  public List<Stocks> getAllStocks();

  /**
   * Lists all stocks and their shares in this portfolio.
   *
   * @return all stocks and their shares
   */
  public List<StockAndShares> getAllStocksAndShares();

  /**
   * Adds a stock to the portfolio, or adds more of it if it has already been added.
   *
   * @param stock  the stock to add
   * @param shares the amount of shares to add
   */
  public void addStock(Stocks stock, int shares);

  /**
   * Removes a stock from the portfolio entirely.
   *
   * @param stock a stock to remove
   */
  public void removeStock(Stocks stock);

  /**
   * Removes shares from a stock in the portfolio.
   * If the amount of shares is reduced to 0, the stock is fully removed.
   *
   * @param stock  the stock to remove shares from
   * @param shares the amount of shares to remove
   * @throws IllegalArgumentException if the amount of shares to remove exceeds the owned shares
   */
  public void removeStockShares(Stocks stock, int shares) throws IllegalArgumentException;

  /**
   * Gets a stock within the portfolio by its symbol.
   *
   * @param symbol the symbol of the stock to get
   * @return the stock
   * @throws NoSuchElementException if the stock is not in this portfolio
   */
  public Stocks getStockByName(String symbol) throws NoSuchElementException;

  /**
   * Gets a stock and its shares within the portfolio by its symbol.
   *
   * @param symbol the symbol of the stock to get
   * @return a StockAndShares object containing the stock and its shares
   * @throws NoSuchElementException if the stock does not exist in this portfolio
   */
  public StockAndShares getStockAndSharesByName(String symbol) throws NoSuchElementException;
}
