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
  @Test
  public void testPriceChangeMacro() {
    Stocks<Double> stock = new StocksImpl<>("GOOG");
    double result = stock.execute(new StockMacroPriceChange(
            new Date(2024,5,17),
            new Date(2024, 5, 28)
    ));
    double expected = 178.0200 - 177.2900;
    assertEquals(String.valueOf(expected), String.valueOf(result));
  }

  @Test // using the program like this might allow us to remove <T> from Stocks.
  public void testPriceChangeMacro2() {
    Stocks<Double> stock = new StocksImpl<>("GOOG");
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
    Stocks<Double> stock = new StocksImpl<>("GOOG");
    double result = stock.execute(new StockMacroXDayMovingAverage(
            new Date(2024,5,15),
            3
    ));
    double expected = (173.8800 + 171.9300 + 170.9000) / 3;
  }

  @Test
  public void testXDayCrossoverMacro() {
    Stocks<Date[]> stock = new StocksImpl<>("GOOG");
    Date[] result = stock.execute(new StockMacroXDayCrossovers(
            new Date(2024,5,15),
            3
    ));
    Date[] expected = new Date[] {
      new Date(2024,05,15)
    };

    assertEquals(expected[0], result[0]);
  }
}
