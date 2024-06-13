package stocks.controller;

import stocks.controller.commands.*;
import stocks.model.StockModel;
import stocks.view.StockView;

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
    Map<String, Function<Scanner, StockControllerCommand>> commands = this.getCommands();

    this.view.welcomeMessage();
    while (!quit) {
      this.view.printOptions();
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

  private Map<String, Function<Scanner, StockControllerCommand>> getCommands() {
    Map<String, Function<Scanner, StockControllerCommand>> commands = new HashMap<>();
    commands.put("get-x-day-average", (Scanner s) -> new XDayMovingAverage(s, this.view));
    commands.put("get-x-day-crossovers", (Scanner s) -> new XDayCrossovers(s, this.view));
    commands.put("evaluate-portfolio", (Scanner s) -> new EvaluatePortfolio(s, this.view));
    commands.put("add-portfolio", (Scanner s) -> new AddPortfolio(s, this.view));
    commands.put("remove-portfolio", (Scanner s) -> new RemovePortfolio(s, this.view));
    commands.put("show-all-portfolios", (Scanner s) -> new ShowAllPortfolios());
    commands.put("get-price-change", (Scanner s) -> new GetPriceChange(s, this.view));
    commands.put("save-portfolio", (Scanner s) -> new SavePortfolio(s, this.view));
    commands.put("load-portfolio", (Scanner s) -> new LoadPortfolio(s, this.view));
    commands.put("buy-stock", (Scanner s) -> new BuyStock(s, this.view));
    commands.put("get-composition", (Scanner s) -> new GetComposition(s, this.view));
    commands.put("sell-stock", (Scanner s) -> new SellStock(s, this.view));
    commands.put("sell-all-stock", (Scanner s) -> new SellAllStock(s, this.view));
    commands.put("rebalance-portfolio", (Scanner s) -> new RebalancePortfolio(s, this.view,
            this.model));
    commands.put("get-distribution", (Scanner s) -> new GetDistribution(s, this.view));
    commands.put("performance-over-time", (Scanner s) -> new PerformanceOverTime(s, this.view));
    return commands;
  }
}