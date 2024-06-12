package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.portfolio.StockPortfolioTimed;
import stocks.view.StockView;

import java.util.Date;

public class SellStock extends ASymbolControllerCommand {
  private final String portfolioName;
  private final Date date;
  private final double amount;
  public SellStock(String portfolioName, String symbol, int month, int day, int year,
                   double amount) {
    super(symbol);
    this.date = new Date(year, month, day);
    this.portfolioName = portfolioName;
    this.amount = amount;
  }

  @Override
  public void start(StockView view, StockModel model) {
    StockPortfolioTimed portfolio = model.getPortfolioByName(this.portfolioName);
    portfolio.sell(this.symbol, this.date, this.amount);
    view.show(String.format("Successfully sold %f shares of %s on %d-%d-%d for %s.",
            this.amount, this.symbol, this.date.getMonth() + 1, this.date.getDate(),
            this.date.getYear(), this.portfolioName));
  }
}
