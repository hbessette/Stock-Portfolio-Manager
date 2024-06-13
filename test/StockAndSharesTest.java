import org.junit.Before;
import org.junit.Test;

import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the StockAndShares class.
 */
public class StockAndSharesTest {
  private Stocks appleStock;
  private StockAndShares appleSAS;

  /**
   * Setup for tests.
   */
  @Before
  public void init() {
    appleStock = new StocksImpl("AAPL");
    appleSAS = new StockAndShares(appleStock, 5);
  }

  /**
   * Tests that the constructor correctly assigns values.
   */
  @Test
  public void testConstructor() {
    assertEquals(appleStock, appleSAS.getStock());
    assertEquals(5, appleSAS.getShares(), 0.0);
  }

  /**
   * Tests that adding shares works.
   */
  @Test
  public void testAddShares() {
    appleSAS.addShares(5);

    assertEquals(10, appleSAS.getShares(), 0.0);
  }

  /**
   * Tests that removing shares works.
   */
  @Test
  public void testRemoveShares() {
    appleSAS.removeShares(3);

    assertEquals(2, appleSAS.getShares(), 0.0);
  }

  /**
   * tests that removing more shares than you have throws an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSharesFail() {
    appleSAS.removeShares(6);
  }
}