package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;

public class GetComposition extends APortfolioCommand {
  private final Date date;

  public GetComposition(String portfolioName, int month, int day, int year) {
    super(portfolioName);
    this.date = new Date(year, month, day);
  }

  @Override
  public void start(StockView view, StockModel model) {
    String[] output = model.getPortfolioByName(this.portfolioName).getComposition(this.date);
    for (String o : output) {
      view.show(o);
    }
  }
}
