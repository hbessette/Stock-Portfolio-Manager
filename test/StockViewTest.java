import org.junit.Test;
import stocks.view.StockView;
import stocks.view.StockViewImpl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class StockViewTest {

  @Test
  public void testStockView() {
    OutputStream out = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(out);
    StockView view = new StockViewImpl(outStream);

    view.show("Hello");
    view.welcomeMessage();
    view.printHelp();
    view.goodbyeMessage();

    String[] viewLog = out.toString().split(System.lineSeparator());
    assertEquals("Hello", viewLog[0]);
    assertEquals("Welcome to Stock Manager", viewLog[1]);
    assertEquals("Enter help or h to see list of commands.", viewLog[2]);
    assertEquals("List of Commands: ", viewLog[3]);
    assertEquals("add-portfolio (portfolioName)", viewLog[4]);
    assertEquals("remove-portfolio (portfolioName)", viewLog[5]);
    assertEquals("show-portfolio (portfolioName)", viewLog[6]);
    assertEquals("show-all-portfolios", viewLog[7]);
    assertEquals("evaluate-portfolio (portfolioName) (month) (day) (year)", viewLog[8]);
    assertEquals("show-portfolio-stocks (portfolioName)", viewLog[9]);
    assertEquals("add-stock (portfolioName) (stockName) (shares)", viewLog[10]);
    assertEquals("remove-stock (portfolioName) (stockName)", viewLog[11]);
    assertEquals("remove-stock-shares (portfolioName) (stockName) (shares)", viewLog[12]);
    assertEquals("get-price-change (stockName) (startMonth) (startDay) (startYear) (endMonth) " +
            "(endDay) (endYear)", viewLog[13]);
    assertEquals("get-x-day-crossovers (stockName) (startMonth) (startDay) (startYear) (xDays)",
            viewLog[14]);
    assertEquals("get-x-day-average (stockName) (startMonth) (startDay) (startYear) (xDays)",
            viewLog[15]);
    assertEquals("Thank You For Using Stock Manager", viewLog[16]);
  }
}
