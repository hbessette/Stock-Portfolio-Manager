package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.model.portfolio.StockPortfolioTimed;
import stocks.view.StockView;

import java.util.Date;

public class BuyStock extends ASymbolControllerCommand {
  private Date date;
  private int amount;
  private String portfolioName;
  public BuyStock(String portfolioName, String symbol, int month, int day, int year, int amount) {
    super(symbol);
    this.date = new Date(year, month, day);
    this.amount = amount;
    this.portfolioName = portfolioName;
  }

  @Override
  public void start(StockView view, StockModel model) {
    StockPortfolioTimed portfolio = model.getPortfolioByName(this.portfolioName);
    portfolio.purchase(this.symbol, this.date, this.amount);
    view.show(String.format("Successfully bought %d shares of %s on %d-%d-%d for %s.",
            this.amount, this.symbol, this.date.getMonth() + 1, this.date.getDate(),
            this.date.getYear(), this.portfolioName));
  }
}