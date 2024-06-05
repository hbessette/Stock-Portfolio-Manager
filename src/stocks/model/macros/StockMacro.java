package stocks.model.macros;

import stocks.model.Stocks;

/**
 * Represents a stock macro.
 * A stock macro can be used on stock data to produce a result.
 *
 * @param <T> The type this macro returns
 */
public interface StockMacro<T> {

  /**
   * Applies this stock macro on the specified stock. Returns the result of the macro.
   *
   * @param stock the stock to apply the macro to
   * @return the result of the macro
   */
  T apply(Stocks<T> stock);
}
