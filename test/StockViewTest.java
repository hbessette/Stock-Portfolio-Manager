import org.junit.Test;
import stocks.view.StockView;
import stocks.view.StockViewImpl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * To test the stock view.
 */
public class StockViewTest {

  /**
   * To test the outputs that stock view sends out from its methods.
   */
  @Test
  public void testStockView() {
    OutputStream out = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(out);
    StockView view = new StockViewImpl(outStream);

    view.show("Hello");
    view.welcomeMessage();
    view.printOptions();
    view.printHelp();
    view.goodbyeMessage();

    String[] viewLog = out.toString().split(System.lineSeparator());
    assertEquals("Hello", viewLog[0]);
    assertEquals("Welcome to Stock Manager", viewLog[1]);
    assertEquals("Menu:", viewLog[2]);
    assertEquals("h or help shows commands", viewLog[3]);
    assertEquals("q or quit to exit program", viewLog[4]);
    assertEquals("List of Commands: ", viewLog[5]);
    assertEquals("show-all-portfolios (to show current portfolios)", viewLog[6]);
    assertEquals("add-portfolio (to add new portfolio)", viewLog[7]);
    assertEquals("remove-portfolio (to remove a portfolio)", viewLog[8]);
    assertEquals("save-portfolio (to save a portfolio for later use)", viewLog[9]);
    assertEquals("load-portfolio (to load a portfolio from storage)", viewLog[10]);
    assertEquals("buy-stock (to buy a stock for specific portfolio)", viewLog[11]);
    assertEquals("sell-stock (to sell a stock for specific portfolio)", viewLog[12]);
    assertEquals("sell-all-stock (to sell all of a stock in specific portfolio)",
            viewLog[13]);
    assertEquals("evaluate-portfolio (to evaluate total value of a specific portfolio)",
            viewLog[14]);
    assertEquals("get-composition (to get the composition of stocks of a specific " +
            "portfolio)", viewLog[15]);
    assertEquals("get-distribution (to get the distribution of stocks of a specific " +
            "portfolio)", viewLog[16]);
    assertEquals("performance-over-time (to get a graph of a portfolio's stock)",
            viewLog[17]);
    assertEquals("get-price-change (to get the price change of a stock from and to " +
            "specific dates)", viewLog[18]);
    assertEquals("get-x-day-crossovers (to get the x day crossovers of a stock)",
            viewLog[19]);
    assertEquals("get-x-day-average (to get the average of a stock over a specific " +
            "period)", viewLog[20]);
    assertEquals("Thank You For Using Stock Manager", viewLog[21]);
  }
}
