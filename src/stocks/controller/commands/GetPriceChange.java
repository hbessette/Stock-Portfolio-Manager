package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroPriceChange;
import stocks.view.StockView;

import java.util.Date;

public class GetPriceChange extends ASymbolControllerCommand {
  private final Date startDate;
  private final Date endDate;

  public GetPriceChange(String symbol, Date startDate, Date endDate) {
    super(symbol);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public void start(StockView view, StockModel model) {
    try {
      double priceChange =
              new StockMacroPriceChange(this.startDate, this.endDate).apply(
                      model.getStockByName(this.symbol));
      view.show("Price change for " + this.symbol + " between" + this.startDate.toString() + " " +
              "and " + this.endDate.toString() + " is" + priceChange + ".");
    } catch (IllegalStateException e) {
      view.show(e.getMessage());
    }
  }
}
