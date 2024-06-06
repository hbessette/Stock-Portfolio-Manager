package stocks.model.portfolio.shares;

import stocks.model.stock.Stocks;

/**
 * Includes a stock and a number of shares, for easy use in getting the two from a portfolio.
 */
public class StockAndShares {
  private final Stocks stock;
  private int shares;

  /**
   * Creates a new StockAndShares.
   *
   * @param stock a stock
   * @param shares a number of shares
   */
  public StockAndShares(Stocks stock, int shares) {
    this.stock = stock;
    this.shares = shares;
  }

  /**
   * Gets the stock.
   *
   * @return the stock
   */
  public Stocks getStock() {
    return this.stock;
  }

  /**
   * Gets the shares.
   *
   * @return the shares
   */
  public int getShares() {
    return this.shares;
  }

  /**
   * Adds shares.
   *
   * @param amount the amount of shares to add
   */
  public void addShares(int amount) {
    this.shares += amount;
  }

  /**
   * Removes shares and returns true if this made the amount of shares owned 0.
   *
   * @param amount the amount of shares to remove
   * @return true if the amount of shares is now 0.
   * @throws IllegalArgumentException if it is attempted to remove more shares than you have
   */
  public boolean removeShares(int amount) throws IllegalArgumentException {
    if (this.shares - amount < 0) {
      throw new IllegalArgumentException("You cannot remove more shares than you have.");
    }
    this.shares -= amount;

    return this.shares == 0;
  }
}
