package stocks.controller;

import stocks.controller.commands.*;

import stocks.model.StockModel;
import stocks.model.portfolio.shares.StockAndShares;
import stocks.view.StockView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * A stock controller. Processes commands obtained from a scanner using a model,
 * and displays the results using a view.
 */
public class StockControllerImpl implements StockController {
  private Scanner in;
  private StockView view;
  private StockModel model;

  /**
   * Creates a controller.
   *
   * @param in    some Readable to take in input
   * @param view  a view to display results
   * @param model a model to process commands using
   */
  public StockControllerImpl(Readable in, StockView view, StockModel model) {
    this.in = new Scanner(in);
    this.view = view;
    this.model = model;
  }

  /**
   * Starts the controller, initiating a loop of asking for commands and using them.
   */
  @Override
  public void start() {
    boolean quit = false;
    Map<String, Function<Scanner, StockControllerCommand>> commands = getCommands(this.view);

    this.view.welcomeMessage();
    while (!quit) {
      String command = this.in.next();
      switch (command) {
        case "q":
        case "quit":
          this.view.goodbyeMessage();
          quit = true;
          break;
        case "h":
        case "help":
          this.view.printHelp();
          break;
        case "rebalance-portfolio":
          String portfolio = this.in.next();
          int month = this.in.nextInt();
          int day = this.in.nextInt();
          int year = this.in.nextInt();
          try {
            Map<String, Double> percentages = new HashMap<>(); // DELET NEW DATE
            for (String stock : this.model.getPortfolioByName(portfolio).getStockNames(new Date())) {
              view.show("Enter percentage for " + stock);
              double percentage = this.in.nextDouble() / 100.0;
              percentages.put(stock, percentage);
            }
            model.getPortfolioByName(portfolio).rebalance(new Date(year, month - 1, day),
                    percentages);
            view.show(String.format("Successfully rebalanced %s for %d-%d-%d.", portfolio, month,
                    day, year));
          } catch (Exception e) {
            view.show(e.getMessage());
          }
        default:
          try {
            StockControllerCommand cmd = commands.get(command).apply(this.in);
            cmd.start(this.view, this.model);
          } catch (NullPointerException e) {
            this.view.show("Invalid command.");
          } catch (Exception e) {
            this.view.show(e.getMessage());
          }
      }
    }
  }

  private static Map<String, Function<Scanner, StockControllerCommand>> getCommands(StockView view) {
    Map<String, Function<Scanner, StockControllerCommand>> commands = new HashMap<>();
    commands.put("get-x-day-average", (Scanner s) -> new XDayMovingAverage(s, view));
    commands.put("get-x-day-crossovers", (Scanner s) -> new XDayCrossovers(s, view));
    commands.put("evaluate-portfolio", (Scanner s) -> new EvaluatePortfolio(s, view));
    commands.put("add-portfolio", (Scanner s) -> new AddPortfolio(s, view));
    commands.put("remove-portfolio", (Scanner s) -> new RemovePortfolio(s, view));
    commands.put("show-all-portfolios", (Scanner s) -> new ShowAllPortfolios());
    commands.put("get-price-change", (Scanner s) -> new GetPriceChange(s, view));
    commands.put("save-portfolio", (Scanner s) -> new SavePortfolio(s, view));
    commands.put("load-portfolio", (Scanner s) -> new LoadPortfolio(s, view));
    commands.put("buy-stock", (Scanner s) -> new BuyStock(s, view));
    commands.put("get-composition", (Scanner s) -> new GetComposition(s, view));
    commands.put("sell-stock", (Scanner s) -> new SellStock(s, view));
    commands.put("sell-all-stock", (Scanner s) -> new SellAllStock(s,view));
    return commands;
  }
}
