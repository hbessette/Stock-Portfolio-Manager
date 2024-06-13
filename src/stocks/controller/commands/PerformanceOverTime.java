package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Date;
import java.util.Scanner;

public class PerformanceOverTime implements StockControllerCommand {
  private final String portfolioName;
  private final Date startDate;
  private final Date endDate;

  public PerformanceOverTime(Scanner s, StockView view) {
    view.show("Enter the portfolio name: ");
    this.portfolioName = s.next();
    view.show("Enter the year for the start date: ");
    int startYear = s.nextInt();
    view.show("Enter the month for the start date: ");
    int startMonth = s.nextInt() - 1;
    view.show("Enter the day for the start date: ");
    int startDay = s.nextInt();
    view.show("Enter the year for the end date: ");
    int endYear = s.nextInt();
    view.show("Enter the month for the end date: ");
    int endMonth = s.nextInt() - 1;
    view.show("Enter the day for the end date: ");
    int endDay = s.nextInt();
    this.startDate = new Date(startYear, startMonth, startDay);
    this.endDate = new Date(endYear, endMonth, endDay);
  }
  @Override
  public void start(StockView view, StockModel model) {
    String s = model.performanceOverTimeForPortfolio(this.portfolioName, this.startDate,
            this.endDate);
    view.show(String.format("Performance of Portfolio %s from %d-%d-%d to %d-%d-%d.",
            this.portfolioName, this.startDate.getMonth() + 1, this.startDate.getDate(),
            this.startDate.getYear(), this.endDate.getMonth() + 1, this.endDate.getDate(),
            this.endDate.getYear()));
    view.show(s);
  }
}
