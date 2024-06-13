
import org.junit.Test;
import stocks.controller.StockController;
import stocks.controller.StockControllerImpl;
import stocks.model.StockModel;
import stocks.model.StockModelImpl;
import stocks.model.StockModelMock;
import stocks.view.StockView;
import stocks.view.StockViewImpl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;


import static org.junit.Assert.assertEquals;

/**
 * To test the controller for sending inputs and outputs to StockModel and StockView.
 */
public class StockMockTest {

  @Test
  public void testModelMock() {
    String inputs = "add-portfolio portfolio" + System.lineSeparator() +
            "buy-stock portfolio MSFT 10 2024 5 31" + System.lineSeparator() +
            "evaluate-portfolio portfolio 2024 5 31" + System.lineSeparator() +
            "show-all-portfolios" + System.lineSeparator() +
            "remove-portfolio portfolio" + System.lineSeparator() +
            "get-price-change MSFT 2024 5 31 2024 6 5" + System.lineSeparator() +
            "save-portfolio portfolio" + System.lineSeparator() +
            "get-x-day-average MSFT 2024 5 31 3" + System.lineSeparator() +
            "get-x-day-crossovers MSFT 2024 5 31 3" + System.lineSeparator() +
            "get-price-change MSFT 2024 5 21 2024 5 31" + System.lineSeparator() +
            "load-portfolio portfolio" + System.lineSeparator() +
            "rebalance-portfolio portfolio 2024 5 31" + System.lineSeparator() +
            "sell-stock portfolio MSFT 2024 5 31 10.0" + System.lineSeparator() +
            "sell-all-stock portfolio MSFT 2024 5 31" + System.lineSeparator() +
            "get-distribution portfolio 2024 5 31" + System.lineSeparator() +
            "get-composition portfolio 2024 5 31" + System.lineSeparator() +
            "performance-over-time portfolio 2024 5 31 2024 6 5" + System.lineSeparator() +
            "q";
    Reader in = new StringReader(inputs);
    OutputStream out = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(out);
    StockView view = new StockViewImpl(outStream);
    StockModel modelMock = new StockModelMock();
    StockController controller = new StockControllerImpl(in, view, modelMock);
    controller.start();

    String[] modelLog = modelMock.returnLog().split(System.lineSeparator());
    assertEquals("name: portfolio" , modelLog[0]);
    assertEquals("name: portfolio, symbol: MSFT, year: 2024, month: 5, day: 31, amount: 10.000000" , modelLog[1]);
    assertEquals("name: portfolio, year: 2024, month: 5, day: 31" , modelLog[2]);
    assertEquals("getAllPortfolios() successfully called." , modelLog[3]);
    assertEquals("name: portfolio" , modelLog[4]);
    assertEquals("symbol: MSFT, startYear: 2024, startMonth: 5, startDay: 31, endYear: 2024, endMonth: 6, endDay: 5" , modelLog[5]);
    assertEquals("name: portfolio" , modelLog[6]);
    assertEquals("symbol: MSFT, startYear: 2024, startMonth: 5, startDay: 31, xDays: 3" , modelLog[7]);
    assertEquals("symbol: MSFT, startYear: 2024, startMonth: 5, startDay: 31, xDays: 3" , modelLog[8]);
    assertEquals("symbol: MSFT, startYear: 2024, startMonth: 5, startDay: 21, endYear: 2024, endMonth: 5, endDay: 31" , modelLog[9]);
    assertEquals("name: portfolio" , modelLog[10]);
    assertEquals("name: portfolio, year: 2024, month: 5, day: 1" , modelLog[11]);
    assertEquals("name: portfolio, year: 2024, month: 5, day: 1," , modelLog[12]);
    assertEquals("name: portfolio, symbol: MSFT, year: 2024, month: 5, day: 31, amount: 10.000000" , modelLog[13]);
    assertEquals("name: portfolio, symbol: MSFT, year: 2024, month: 5, day: 31" , modelLog[14]);
    assertEquals("name: portfolio, year: 2024, month: 5, day: 31" , modelLog[15]);
    assertEquals("name: portfolio, year: 2024, month: 5, day: 31" , modelLog[16]);

    String[] viewLog = out.toString().split(System.lineSeparator());
    assertEquals("Welcome to Stock Manager", viewLog[0]);
    assertEquals("Menu:", viewLog[1]);
    assertEquals("h or help shows commands", viewLog[2]);
    assertEquals("q or quit to exit program", viewLog[3]);
    assertEquals("Enter portfolio name: ", viewLog[4]);
    assertEquals("Successfully added portfolio.", viewLog[5]);
    assertEquals("Menu:", viewLog[6]);
    assertEquals("h or help shows commands", viewLog[7]);
    assertEquals("q or quit to exit program", viewLog[8]);
    assertEquals("Enter the portfolio name to buy stock for: ", viewLog[9]);
    assertEquals("Enter the symbol to buy stock for: ", viewLog[10]);
    assertEquals("Enter the amount to buy stock for: ", viewLog[11]);
    assertEquals("Enter the year to buy stock for: ", viewLog[12]);
    assertEquals("Enter the month to buy stock for: ", viewLog[13]);
    assertEquals("Enter the day to buy stock for: ", viewLog[14]);
    assertEquals("Successfully bought 10 shares of MSFT on 5-31-2024 for portfolio.", viewLog[15]);
    assertEquals("Menu:", viewLog[16]);
    assertEquals("h or help shows commands", viewLog[17]);
    assertEquals("q or quit to exit program", viewLog[18]);
    assertEquals("Enter the portfolio name to evaluate: ", viewLog[19]);
    assertEquals("Enter the year to evaluate: ", viewLog[20]);
    assertEquals("Enter the month to evaluate: ", viewLog[21]);
    assertEquals("Enter the day to evaluate: ", viewLog[22]);
  }
}
