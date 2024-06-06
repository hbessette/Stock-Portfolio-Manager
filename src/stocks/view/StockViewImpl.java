package stocks.view;

import java.io.PrintStream;

/**
 * A simple view which prints text to a PrintStream.
 */
public class StockViewImpl implements StockView {
  private PrintStream out;

  /**
   * Initializes this view.
   *
   * @param out a PrintStream to print to
   */
  public StockViewImpl(PrintStream out) {
    this.out = out;
  }

  /**
   * Prints the given text to the PrintStream.
   * @param input the text to display
   */
  @Override
  public void show(String input) {
    out.println(input);
  }

  /**
   * Prints a welcome message.
   */
  public void welcomeMessage() {
    out.println("Welcome to Stock Manager");
    out.println("Here are your list of commands to manage your portfolio:");
    out.println("");
  }
}
