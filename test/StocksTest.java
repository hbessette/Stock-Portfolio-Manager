import org.junit.Test;

import java.util.Date;
import java.util.Set;

import stocks.model.daystatus.StockDayStatus;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Stocks class.
 */
public class StocksTest {
  /**
   * Tests that getStockDayStatus returns the correct StockDayStatus on a given day.
   */
  @Test
  public void testGetStockDayStatus() {
    Stocks stock = new StocksImpl("GOOG");
    StockDayStatus sds = stock.getStockDayStatus(new Date(2024,4,28));
    assertEquals(String.valueOf(178.0200), String.valueOf(sds.getClosingTime()));
    assertEquals(15655340, sds.getVolume());
  }

  /**
   * Tests that getStockDayStatus throws an exception if no data exists for the given day.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetStockDayStatusInvalid() {
    Stocks stock = new StocksImpl("GOOG");
    stock.getStockDayStatus(new Date(2024,4,27));
  }

  /**
   * Tests that getting the Symbol of a stock works.
   */
  @Test
  public void testGetSymbol() {
    Stocks stockGoog = new StocksImpl("GOOG");
    Stocks stockMsft = new StocksImpl("MSFT");
    Stocks stockAapl = new StocksImpl("AAPL");

    assertEquals("GOOG", stockGoog.getSymbol());
    assertEquals("MSFT", stockMsft.getSymbol());
    assertEquals("AAPL", stockAapl.getSymbol());
  }

  /**
   * Tests that getValidDates gets the correct valid dates for a stock.
   */
  @Test
  public void testGetValidDates() {
    Stocks stockGoog = new StocksImpl("GOOG");
    Set<Date> dateSet = stockGoog.getValidDates();

    assertTrue(dateSet.contains(new Date(2024, 4, 28)));
    assertFalse(dateSet.contains(new Date(2024, 4, 27)));
    assertTrue(dateSet.contains(new Date(2024, 4, 29)));
  }
}
