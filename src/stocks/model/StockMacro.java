package stocks.model;

public interface StockMacro<T> {

  T apply(Stocks stock);
}
