package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

public class ShowAllPortfolios implements StockControllerCommand {

  @Override
  public void start(StockView view, StockModel model) {
      view.show(model.getAllPortfolios().toString());
  }
}
