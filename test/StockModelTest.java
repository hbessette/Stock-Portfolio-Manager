import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import stocks.model.StockModel;
import stocks.model.StockModelImpl;
import stocks.model.portfolio.StockPortfolio;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import static org.junit.Assert.*;

/**
 * Tests for the stockModel class.
 */
public class StockModelTest {
  private StockModel model;
  private Stocks googleStock;
  private Stocks microsoftStock;
  private Stocks appleStock;

  /**
   * Setup for testing.
   */
  @Before
  public void init() {
    googleStock = new StocksImpl("GOOG");
    microsoftStock = new StocksImpl("MSFT");
    appleStock = new StocksImpl("AAPL");
    model = new StockModelImpl();
  }

  /**
   * Tests that multiple portfolios can be added to the model with addPortfolio(),
   * and that they work correctly.
   * Also tests the getPortfolioByName method.
   */
  @Test
  public void testAddPortfolio() {
    model.addPortfolio("Tester");
    model.addPortfolio("A");
    model.addPortfolio("B");
    model.getPortfolioByName("Tester").addStock(appleStock, 5);

    String expected = "Successfully added Tester" + System.lineSeparator()
            + "Successfully added A" + System.lineSeparator()
            + "Successfully added B" + System.lineSeparator();

    assertEquals(appleStock, model.getPortfolioByName("Tester").getStockByName("AAPL"));
    assertEquals(expected, model.returnLog());
  }

  /**
   * Tests that portfolios can be removed,
   * and that removing a portfolio which never existed does not do anything.
   */
  @Test
  public void testRemovePortfolio() {
    model.addPortfolio("Tester");
    model.removePortfolio("Tester");
    model.removePortfolio("Something");

    String expected = "Successfully added Tester" + System.lineSeparator()
            + "Successfully removed Tester" + System.lineSeparator();

    assertEquals(expected, model.returnLog());
  }

  /**
   * Tests that the evaluate feature of the model evaluates a portfolio.
   */
  @Test
  public void testEvaluatePortfolio() {
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
    model.evaluatePortfolio("Nonexistant", new Date(2024,11,2));
  }
}