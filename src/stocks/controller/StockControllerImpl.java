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
   * @param in some Readable to take in input
   * @param view a view to display results
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
    Map<String, Function<Scanner, StockControllerCommand>> commands = getCommands();

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
        default:
          try {
            StockControllerCommand cmd = commands.get(command).apply(this.in);
            cmd.start(this.view, this.model);
          }
          catch (NullPointerException e) {
            this.view.show("Invalid command.");
          } catch (Exception e) {
            this.view.show(e.getMessage());
          }
      }
    }
  }

  private static Map<String, Function<Scanner, StockControllerCommand>> getCommands() {
    Map<String, Function<Scanner, StockControllerCommand>> commands = new HashMap<>();
    commands.put("add-stock",
            (Scanner s) -> new AddStock(s.next(), s.next(), s.nextInt()));
    commands.put("remove-stock",
            (Scanner s) -> new RemoveStock(s.next(), s.next()));
    commands.put("remove-stock-shares",
            (Scanner s) -> new RemoveStockShares(s.next(), s.next(), s.nextInt()));
    commands.put("get-x-day-average",
            (Scanner s) -> new XDayMovingAverage(s.next(), s.nextInt() - 1 , s.nextInt(),
                    s.nextInt(), s.nextInt()));
    commands.put("get-x-day-crossovers",
            (Scanner s) -> new XDayCrossovers(s.next(), s.nextInt() - 1, s.nextInt(),
                    s.nextInt(), s.nextInt()));
    commands.put("show-portfolio",
            (Scanner s) -> new ShowPortfolio(s.next()));
    commands.put("evaluate-portfolio",
            (Scanner s) -> new EvaluatePortfolio(s.next(), s.nextInt() - 1, s.nextInt(),
                    s.nextInt()));
    commands.put("add-portfolio",
            (Scanner s) -> new AddPortfolio(s.next()));
    commands.put("remove-portfolio",
            (Scanner s) -> new RemovePortfolio(s.next()));
    commands.put("show-all-portfolios",
            (Scanner s) -> new ShowAllPortfolios());
    commands.put("get-price-change",
            (Scanner s) -> new GetPriceChange(s.next(), s.nextInt() - 1, s.nextInt(),
                    s.nextInt(), s.nextInt() - 1, s.nextInt(), s.nextInt()));
    commands.put("show-portfolio-stocks",
            (Scanner s) -> new ShowPortfolioStocks(s.next()));
    return commands;
  }
}
