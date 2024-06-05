import org.junit.Test;

import java.util.Date;

import stocks.model.Stocks;
import stocks.model.StocksImpl;
import stocks.model.macros.StockMacro;
import stocks.model.macros.StockMacroPriceChange;
import stocks.model.macros.StockMacroXDayCrossovers;
import stocks.model.macros.StockMacroXDayMovingAverage;

import static org.junit.Assert.assertEquals;

public class StockMacroTest {
  @Test // using the program like this might allow us to remove <T> from Stocks.
  public void testPriceChangeMacro() {
    Stocks stock = new StocksImpl("GOOG");
    StockMacro<Double> macro = new StockMacroPriceChange(
            new Date(2024,5,17),
            new Date(2024, 5, 28)
    );

    double result = macro.apply(stock);
    double expected = 178.0200 - 177.2900;
    assertEquals(String.valueOf(expected), String.valueOf(result));
  }

  @Test
  public void testXDayMovingAverageMacro() {
    Stocks stock = new StocksImpl("GOOG");
    StockMacro<Double> macro = new StockMacroXDayMovingAverage(
            new Date(2024,5,15),
            3
    );
    double result = macro.apply(stock);
    double expected = (173.8800 + 171.9300 + 170.9000) / 3;
    assertEquals(String.valueOf(result), String.valueOf(expected));
  }

  @Test
  public void testXDayCrossoverMacro() {
    Stocks stock = new StocksImpl("GOOG");
    StockMacro<Date[]> macro = new StockMacroXDayCrossovers(
            new Date(2024,5,15),
            3
    );
    Date[] result = macro.apply(stock);
    Date[] expected = new Date[] {
            new Date(2024,05,15)
    };

    assertEquals(expected[0], result[0]);
  }
}
