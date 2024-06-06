package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroXDayMovingAverage;
import stocks.view.StockView;

import java.util.Date;

public class XDayMovingAverage extends AXDayCommand {

  public XDayMovingAverage(String symbol, int month, int day, int year, int xDays) {
    super(symbol, month, day, year, xDays);
  }

  @Override
  public void start(StockView view, StockModel model) {
    try {
      double average =
              new StockMacroXDayMovingAverage(this.startDate, this.xDays).apply(
                      model.getStockByName(this.symbol));
      view.show("Moving average for " + this.symbol + " from " + this.startDate.toString() +
              " is " + average + ".");
    } catch (IllegalStateException e) {
      view.show(e.getMessage());
    }
  }
}
