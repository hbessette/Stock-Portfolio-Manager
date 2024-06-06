package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

/**
 * A command that can be does some action using the model,
 * and provides some description of what has happened to the view.
 * Intended to be called from the StockController.
 */
public interface StockControllerCommand {
  /**
   * Executes this command, displaying whatever happened to the view.
   *
   * @param view  the view to display to
   * @param model the model to use to process the command
   */
  void start(StockView view, StockModel model);
}
