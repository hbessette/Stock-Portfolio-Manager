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

  public void welcomeMessage() {
    out.println("Welcome to Stock Manager");
    out.println("Here are your list of commands to manage your portfolio:");
    out.println("");
  }
}
