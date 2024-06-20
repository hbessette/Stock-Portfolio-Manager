package stocks;

import stocks.controller.StockController;
import stocks.controller.StockControllerGUI;
import stocks.controller.StockControllerImpl;
import stocks.model.StockModel;
import stocks.model.StockModelImpl;
import stocks.view.IStockViewGUI;
import stocks.view.StockView;
import stocks.view.StockViewGUI;
import stocks.view.StockViewImpl;

import javax.swing.*;
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
//    StockView stockView = new StockViewImpl(System.out);
//    StockModel stockModel = new StockModelImpl();
//    StockController stockController = new StockControllerImpl(new InputStreamReader(System.in),
//            stockView
//            , stockModel);
//    stockController.start();

    StockModel stockModel = new StockModelImpl();
    IStockViewGUI view = new StockViewGUI();
    StockController controller = new StockControllerGUI(view, stockModel);
    controller.start();
  }
}
