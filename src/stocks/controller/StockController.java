package stocks.controller;

/**
 * To represent a StockController that handles the inputs and outputs between a StockModel and
 * StockView. This controller will send commands to StockModel from user inputs and then send
 * results back to the StockView depending on the command.
 */
public interface StockController {
  /**
   * To start the StockController on a continuous loop until the user inputs the quit command.
   * Otherwise, keeps accepting inputs and commands.
   */
  void start();
}
