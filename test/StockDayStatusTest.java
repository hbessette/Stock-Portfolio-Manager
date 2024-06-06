import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import stocks.model.daystatus.StockDayStatus;
import stocks.model.daystatus.StockDayStatusImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the StockDayStatus class.
 */
public class StockDayStatusTest {

  private StockDayStatus sdsApple202464;

  /**
   * Setup for tests.
   */
  @Before
  public void init() {
    sdsApple202464 = new StockDayStatusImpl(
            new Date(2024, 6, 4),
            194.0300,
            50080539
    );
  }

  /**
   * Tests date getter.
   */
  @Test
  public void testGetDate() {
    assertEquals(new Date(2024, 6, 4), sdsApple202464.getDate());
  }

  /**
   * Tests closing time getter.
   */
  @Test
  public void testGetClosingTime() {
    assertEquals(String.valueOf(194.0300), String.valueOf(sdsApple202464.getClosingTime()));
  }

  /**
   * Tests volume getter.
   */
  @Test
  public void testGetVolume() {
    assertEquals(50080539, sdsApple202464.getVolume());
  }
}