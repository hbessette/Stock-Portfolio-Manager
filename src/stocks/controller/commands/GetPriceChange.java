package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.macros.StockMacroPriceChange;
import stocks.view.StockView;

import java.util.Date;

/**
 * To get the price change of a certain stock between two dates. Value can be negative or positive.
 */
public class GetPriceChange extends ASymbolControllerCommand {
  private final Date startDate;
  private final Date endDate;

  /**
   * To create a new GetPriceChange object.
   * @param symbol target stock symbol
   * @param startMonth target start month
   * @param startDay target start day
   * @param startYear target start year
   * @param endMonth target end month
   * @param endDay target end day
   * @param endYear target end year
   */
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
