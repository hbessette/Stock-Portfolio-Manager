
import org.junit.Test;
import stocks.controller.StockController;
import stocks.controller.StockControllerImpl;
import stocks.model.StockModel;
import stocks.model.StockModelImpl;
import stocks.view.StockView;
import stocks.view.StockViewImpl;

import javax.print.DocFlavor;
import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * To test the controller for sending inputs and outputs to StockModel and StockView.
 */
public class StockMockTest {

  @Test
  public void testModelMock() {
    String inputs = "add-portfolio portfolio" + System.lineSeparator() +
            "add-stock portfolio MSFT 10" + System.lineSeparator() +
            "evaluate-portfolio portfolio 5 31 2024" + System.lineSeparator() +
            "show-all-portfolios" + System.lineSeparator() +
            "show-portfolio-stocks portfolio" + System.lineSeparator() +
            "remove-stock portfolio MSFT" + System.lineSeparator() +
            "remove-portfolio portfolio" + System.lineSeparator() +
            "get-x-day-average MSFT 5 31 2024 3" + System.lineSeparator() +
            "get-x-day-crossovers MSFT 5 31 2024 3" + System.lineSeparator() +
            "get-price-change MSFT 5 20 2024 5 31 2024" + System.lineSeparator() +
            "q";
    Reader in = new StringReader(inputs);
    OutputStream out = new ByteArrayOutputStream();
    PrintStream outStream = new PrintStream(out);
    StockView view = new StockViewImpl(outStream);
    StockModel modelMock = new StockModelImpl();
    StockController controller = new StockControllerImpl(in, view, modelMock);
    controller.start();

    String[] modelLog = modelMock.returnLog().split(System.lineSeparator());
    assertEquals("Portfolio portfolio added." , modelLog[0]);
    assertEquals("Portfolio portfolio received." , modelLog[1]);
    assertEquals("Portfolio portfolio received. Date: 2024-5-31" , modelLog[2]);
    assertEquals("getAllPortfolios() successfully called." , modelLog[3]);
    assertEquals("Portfolio portfolio received." , modelLog[4]);
    assertEquals("Portfolio portfolio removed." , modelLog[6]);

    String[] viewLog = out.toString().split(System.lineSeparator());
    assertEquals("Welcome to Stock Manager", viewLog[0]);
    assertEquals("Enter help or h to see list of commands.", viewLog[1]);
    assertEquals("Successfully added portfolio.", viewLog[2]);
    assertEquals("Successfully added MSFT with 10 shares to portfolio", viewLog[3]);
    assertEquals("Evaluation of portfolio on 5-31-2024 is 4151.3.", viewLog[4]);
    assertEquals("[portfolio]", viewLog[5]);
    assertEquals("MSFT: 10", viewLog[6]);
    assertEquals("Successfully removed MSFT from portfolio", viewLog[7]);
    assertEquals("Successfully removed portfolio portfolio", viewLog[8]);
    assertEquals("Moving average for MSFT from 5-31-2024 is 419.66.", viewLog[9]);
    assertEquals("5-29-2024", viewLog[10]);
    assertEquals("Price change for MSFT between 5-20-2024 and 5-31-2024 is -10.21.", viewLog[11]);
    assertEquals("Thank You For Using Stock Manager", viewLog[12]);
  }
}
