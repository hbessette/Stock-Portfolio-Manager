package stocks.model.macros;

import stocks.model.Stocks;

public interface StockMacro<T> {

  T apply(Stocks<T> stock);
}
