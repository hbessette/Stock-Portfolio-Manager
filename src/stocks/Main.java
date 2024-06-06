package stocks;

import stocks.controller.StockController;
import stocks.controller.StockControllerImpl;
import stocks.model.StockModel;
import stocks.model.StockModelImpl;
import stocks.view.StockView;
import stocks.view.StockViewImpl;

import java.io.InputStreamReader;

/**
 * The class from which the program is run.
 */
public class Main {
  /**
   * Starts the program.
   *
   * @param args ignored
   */
  public static void main(String[] args) {
    StockView stockView = new StockViewImpl(System.out);
    StockModel stockModel = new StockModelImpl();
    StockController stockController = new StockControllerImpl(new InputStreamReader(System.in),
            stockView
            , stockModel);
    stockController.start();
  }
}
