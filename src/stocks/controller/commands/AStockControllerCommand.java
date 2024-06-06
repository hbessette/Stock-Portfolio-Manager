package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

abstract class AStockControllerCommand implements StockControllerCommand {
  protected final String symbol;

  protected AStockControllerCommand(String symbol) {
    this.symbol = symbol;
  }

  public abstract void start(StockView view, StockModel model);
}
