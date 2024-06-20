package stocks.controller;

import stocks.model.StockModel;
import stocks.view.IStockViewGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockControllerGUI implements StockController, ActionListener {
  private IStockViewGUI view;
  private StockModel model;

  public StockControllerGUI(IStockViewGUI view, StockModel model) {
    this.view = view;
    this.model = model;
    this.view.setListener(this);

  }

  @Override
  public void start() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case ""
    }
  }
}
