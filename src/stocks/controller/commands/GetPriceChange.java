package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroPriceChange;
import stocks.view.StockView;

import java.util.Date;

public class GetPriceChange extends ASymbolControllerCommand {
  private final Date startDate;
  private final Date endDate;

  public GetPriceChange(String symbol, int startMonth, int startDay, int startYear, int endMonth,
                        int endDay, int endYear) {
    super(symbol);
    this.startDate = new Date(startYear, startMonth, startDay);
    this.endDate = new Date(endYear, endMonth, endDay);
  }

  @Override
  public void start(StockView view, StockModel model) {
      double priceChange =
              new StockMacroPriceChange(this.startDate, this.endDate).apply(
                      model.getStockByName(this.symbol));
      priceChange = Math.round(priceChange * 100.00) / 100.00;
      view.show("Price change for " + this.symbol + " between " + (this.startDate.getMonth() + 1) +
              "-" + this.startDate.getDate() + "-" + this.startDate.getYear() +
              " and " + (this.endDate.getMonth() + 1) +
              "-" + this.endDate.getDate() + "-" + this.endDate.getYear() + " is " + priceChange +
              ".");
  }
}
