package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroXDayMovingAverage;
import stocks.view.StockView;

public class XDayMovingAverage extends AXDayCommand {

  public XDayMovingAverage(String symbol, int month, int day, int year, int xDays) {
    super(symbol, month, day, year, xDays);
  }

  @Override
  public void start(StockView view, StockModel model) {
      double average =
              new StockMacroXDayMovingAverage(this.startDate, this.xDays).apply(
                      model.getStockByName(this.symbol));
      view.show("Moving average for " + this.symbol + " from " + (this.startDate.getMonth() + 1) +
              "-" + this.startDate.getDate() + "-" + this.startDate.getYear() +
              " is " + (Math.round(average * 100.00) / 100.00) + ".");
  }
}
