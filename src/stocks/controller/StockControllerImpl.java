package stocks.controller;

import stocks.controller.commands.*;
import stocks.model.StockModel;
import stocks.view.StockView;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class StockControllerImpl implements StockController {
  private Scanner in;
  private StockView view;
  private StockModel model;

  public StockControllerImpl(InputStream in, StockView view, StockModel model) {
    this.in = new Scanner(in);
    this.view = view;
    this.model = model;
  }

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
            (Scanner s) -> { return new AddStock(s.next(), s.next(), s.nextInt()); });
    commands.put("remove-stock",
            (Scanner s) -> { return new RemoveStock(s.next(), s.next()); });
    commands.put("get-x-day-average",
            (Scanner s) -> { return new XDayMovingAverage(s.next(), s.nextInt() - 1 , s.nextInt(),
                    s.nextInt(), s.nextInt()); });
    commands.put("get-x-day-crossovers",
            (Scanner s) -> { return new XDayCrossovers(s.next(), s.nextInt() - 1, s.nextInt(),
                    s.nextInt(), s.nextInt()); });
    commands.put("show-portfolio",
            (Scanner s) -> { return new ShowPortfolio(s.next()); });
    commands.put("evaluate-portfolio",
            (Scanner s) -> { return new EvaluatePortfolio(s.next(), s.nextInt() - 1, s.nextInt(),
                    s.nextInt()); });
    commands.put("add-portfolio",
            (Scanner s) -> { return new AddPortfolio(s.next()); });
    commands.put("remove-portfolio",
            (Scanner s) -> { return new RemovePortfolio(s.next()); });
    commands.put("show-all-portfolios",
            (Scanner s) -> { return new ShowAllPortfolios(); });
    commands.put("get-price-change",
            (Scanner s) -> { return new GetPriceChange(s.next(), s.nextInt() - 1, s.nextInt(),
                    s.nextInt(), s.nextInt() - 1, s.nextInt(), s.nextInt()); });
    commands.put("show-portfolio-stocks",
            (Scanner s) -> { return new ShowPortfolioStocks(s.next()); });
    return commands;
  }
}
