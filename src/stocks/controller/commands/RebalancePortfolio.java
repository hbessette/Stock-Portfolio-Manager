package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RebalancePortfolio implements StockControllerCommand {
  private final Map<String, Double> percentages;
  private final String portfolioName;
  private final Date date;

  public RebalancePortfolio(Scanner s, StockView view, StockModel model) {
    view.show("Enter portfolio name: ");
    this.portfolioName = s.next();
    view.show("Enter year: ");
    int year = s.nextInt();
    view.show("Enter month: ");
    int month = s.nextInt();
    view.show("Enter day: ");
    int day = s.nextInt();
    this.date = new Date(year, month - 1, day);
    this.percentages = new HashMap<>();
    for (String stock : model.getStockNamesForPortfolio(this.portfolioName, this.date)) {
      view.show("Enter percentage for " + stock);
      double percentage = s.nextDouble();
      this.percentages.put(stock, percentage);
    }
  }

  @Override
  public void start(StockView view, StockModel model) {
    model.rebalancePortfolio(this.portfolioName, this.date, this.percentages);
    view.show(String.format("Successfully rebalanced %s for %d-%d-%d.", this.portfolioName,
            this.date.getMonth() + 1,
            this.date.getDate(), this.date.getYear()));
  }
}
