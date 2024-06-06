package stocks;

import stocks.controller.StockController;
import stocks.controller.StockControllerImpl;
import stocks.model.StockModel;
import stocks.model.StockModelImpl;
import stocks.view.StockView;
import stocks.view.StockViewImpl;

public class Main {
  public static void main(String[] args) {
    StockView stockView = new StockViewImpl(System.out);
    StockModel stockModel = new StockModelImpl();
    StockController stockController = new StockControllerImpl(System.in, stockView, stockModel);
    stockController.start();
  }
}
