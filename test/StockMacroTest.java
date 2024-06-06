import org.junit.Test;

import java.util.Date;

import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;
import stocks.model.macros.StockMacro;
import stocks.model.macros.StockMacroPriceChange;
import stocks.model.macros.StockMacroXDayCrossovers;
import stocks.model.macros.StockMacroXDayMovingAverage;

import static org.junit.Assert.assertEquals;

/**
 * Tests all StockMacro objects and their outputs on Stocks.
 */
public class StockMacroTest {

  /**
   * Tests that the price change macro correctly determines the gain or loss of a stock over time.
   */
  @Test
  public void testPriceChangeMacro() {
    Stocks stock = new StocksImpl("GOOG");
    StockMacro<Double> macro = new StockMacroPriceChange(
            new Date(2024,4,17),
            new Date(2024, 4, 28)
    );

    double result = macro.apply(stock);
    double expected = 178.0200 - 177.2900;
    assertEquals(String.valueOf(expected), String.valueOf(result));
  }

  /**
   * Tests that the X-day moving average macro correctly determines the X-day moving average.
   */
  @Test
  public void testXDayMovingAverageMacro() {
    Stocks stock = new StocksImpl("GOOG");
    StockMacro<Double> macro = new StockMacroXDayMovingAverage(
            new Date(2024,4,15),
            3
    );
    double result = macro.apply(stock);
    double expected = (173.8800 + 171.9300 + 170.9000) / 3;
    assertEquals(String.valueOf(result), String.valueOf(expected));
  }

  /**
   * Tests that the x-day crossover macro correctly gets x-day crossovers.
   */
  @Test
  public void testXDayCrossoverMacro() {
    Stocks stock = new StocksImpl("GOOG");
    StockMacro<Date[]> macro = new StockMacroXDayCrossovers(
            new Date(2024,4,15),
            3
    );
    Date[] result = macro.apply(stock);
    Date[] expected = new Date[] {
            new Date(2024,4,15)
    };

    assertEquals(expected[0], result[0]);
  }
}
