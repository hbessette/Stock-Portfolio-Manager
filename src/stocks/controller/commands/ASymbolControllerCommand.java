package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

/**
 * To abstract commands that need a symbol.
 */
abstract class ASymbolControllerCommand implements StockControllerCommand {
  protected final String symbol;

  protected ASymbolControllerCommand(String symbol) {
    this.symbol = symbol;
  }

  public abstract void start(StockView view, StockModel model);
}
