package stocks.view;

import java.io.PrintStream;

public class StockViewImpl implements StockView {
  private PrintStream out;

  public StockViewImpl(PrintStream out) {
    this.out = out;
  }

  @Override
  public void show(String input) {
    out.println(input);
  }
}
