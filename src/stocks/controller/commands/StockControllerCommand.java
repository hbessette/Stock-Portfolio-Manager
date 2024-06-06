package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

public interface StockControllerCommand {
  void start(StockView view, StockModel model);
}
