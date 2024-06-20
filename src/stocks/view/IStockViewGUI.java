package stocks.view;

import java.awt.event.ActionListener;

public interface IStockViewGUI extends StockView {
  void setListener(ActionListener listener);

  String getInputString(String command);
}
