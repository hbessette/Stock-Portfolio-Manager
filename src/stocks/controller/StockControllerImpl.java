package stocks.controller;

import stocks.model.StockModel;
import stocks.view.StockView;
import java.io.InputStream;
import java.util.Scanner;

public class StockControllerImpl implements StockController {
  private Scanner in;
  private StockView view;
  private StockModel model;

  public StockControllerImpl(InputStream in, StockView view, StockModel model) {
    this.in = new Scanner(in);
    this.view = view;
    this.model = model;
  }

  @Override
  public void start() {
    boolean quit = false;

    while (!quit) {
      switch (in.next()) {
        case "q":
        case "quit":
          quit = true;
          break;
      }
    }
  }
}
