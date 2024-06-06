package stocks.model.portfolio.shares;

import stocks.model.stock.Stocks;

public class StockAndShares {
  private final Stocks stock;
  private int shares;

  public StockAndShares(Stocks stock, int shares) {
    this.stock = stock;
    this.shares = shares;
  }

  public Stocks getStock() {
    return this.stock;
  }

  public int getShares() {
    return this.shares;
  }

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
