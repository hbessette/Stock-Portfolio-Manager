package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

/**
 * A command to show display all portfolios to the view.
 */
public class ShowAllPortfolios implements StockControllerCommand {

  /**
   * Displays all portfolios to the view.
   *
   * @param view a view to display to
   * @param model a model to get portfolios from
   */
  @Override
  public void start(StockView view, StockModel model) {
      view.show(model.getAllPortfolios().toString());
  }
}
