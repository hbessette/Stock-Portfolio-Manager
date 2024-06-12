package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;

public class SellAllStock extends ASymbolControllerCommand {
  private final String portfolioName;
  private final Date date;

  public SellAllStock(String symbol, String portfolioName, int month, int day, int year) {
    super(symbol);
    this.portfolioName = portfolioName;
    this.date = new Date(year, month, day);
  }

  @Override
  public void start(StockView view, StockModel model) {

  }
}
