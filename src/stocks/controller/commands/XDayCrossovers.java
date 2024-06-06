package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroXDayCrossovers;
import stocks.view.StockView;

import java.util.Arrays;
import java.util.Date;

public class XDayCrossovers extends AXDayCommand {

  public XDayCrossovers(String symbol, int month, int day, int year, int xDays) {
    super(symbol, month, day, year, xDays);
  }

  @Override
  public void start(StockView view, StockModel model) {
      Date[] dates =
              new StockMacroXDayCrossovers(this.startDate, this.xDays).apply(model.getStockByName(this.symbol));
      for (Date date : dates) {
        view.show((date.getMonth() + 1) + "-" + date.getDate() + "-" + date.getYear());
      }
  }
}
