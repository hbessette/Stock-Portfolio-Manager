package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;

abstract class AXDayCommand extends ASymbolControllerCommand {
  protected final Date startDate;
  protected final int xDays;

  protected AXDayCommand(String symbol, int month, int day, int year, int xDays) {
    super(symbol);
    this.startDate = new Date(month, day, year);
    this.xDays = xDays;
  }
  @Override
  public abstract void start(StockView view, StockModel model);
}
