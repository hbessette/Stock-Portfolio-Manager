import org.junit.Test;

import java.util.Date;
import java.util.NoSuchElementException;

import stocks.model.StockModel;
import stocks.model.StockModelImpl;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the stockModel class.
 */
public class StockModelTest {
  /**
   * Tests that multiple portfolios can be added to the model with addPortfolio(),
   * and that they work correctly.
   * Also tests the getPortfolioByName method.
   */
  @Test
  public void testAddPortfolio() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");
    StockModel model = new StockModelImpl();

    model.addPortfolio("Tester");
    model.addPortfolio("A");
    model.addPortfolio("B");
    model.getPortfolioByName("Tester").addStock(appleStock, 5);

    String expected = "Portfolio Tester added." + System.lineSeparator()
            + "Portfolio A added." + System.lineSeparator()
            + "Portfolio B added." + System.lineSeparator()
            + "Portfolio Tester received." + System.lineSeparator()
            + "Portfolio Tester received." + System.lineSeparator();

    assertEquals(appleStock, model.getPortfolioByName("Tester").getStockByName("AAPL"));
    assertEquals(expected, model.returnLog());
  }

  /**
   * Tests that portfolios can be removed,
   * and that removing a portfolio which never existed does not do anything.
   */
  @Test
  public void testRemovePortfolio() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");
    StockModel model = new StockModelImpl();

    model.addPortfolio("Tester");
    model.removePortfolio("Tester");
    model.removePortfolio("Something");

    String expected = "Portfolio Tester added." + System.lineSeparator()
            + "Portfolio Tester removed." + System.lineSeparator();

    assertEquals(expected, model.returnLog());
  }

  /**
   * Tests that the evaluate feature of the model evaluates a portfolio.
   */
  @Test
  public void testEvaluatePortfolio() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");
    StockModel model = new StockModelImpl();

    model.addPortfolio("Tester");
    model.getPortfolioByName("Tester").addStock(appleStock, 1);
    double expected = 189.9900;
    double result = model.getPortfolioByName("Tester").evaluate(
            new Date(2024, 4, 28)
    );
    assertEquals(String.valueOf(expected), String.valueOf(result));
  }

  @Test(expected = NoSuchElementException.class)
  public void testEvaluatePortfolioFail() {
    StockModel model = new StockModelImpl();

    model.evaluatePortfolio("Nonexistant", new Date(2024,11,2));
  }
}