import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import stocks.model.portfolio.StockPortfolio;
import stocks.model.portfolio.StockPortfolioImpl;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import static org.junit.Assert.*;

/**
 * Tests for StockPortfolios.
 */
public class StockPortfolioTest {
  private Stocks googleStock;
  private Stocks microsoftStock;
  private Stocks appleStock;

  /**
   * Initialize stocks for testing
   */
  @Before
  public void init() {
    googleStock = new StocksImpl("GOOG");
    microsoftStock = new StocksImpl("MSFT");
    appleStock = new StocksImpl("AAPL");
  }

  /**
   * Tests if adding a stock works correctly.
   * Also tests if getting all stocks works correctly, since the two cannot be tested separately.
   */
  @Test
  public void testAddStockAndGetAllStocks() {
    StockPortfolio portfolio = new StockPortfolioImpl();

    List<Stocks> expected = new ArrayList<Stocks>();
    assertEquals(expected, portfolio.getAllStocks());

    expected.add(googleStock);
    portfolio.addStock(googleStock, 1);
    assertEquals(expected, portfolio.getAllStocks());

    expected.add(microsoftStock);
    portfolio.addStock(microsoftStock, 2);
    assertEquals(expected, portfolio.getAllStocks());
  }

  /**
   * Tests if the removeStock function properly removes stocks.
   * Also tests that nothing happens when you remove a stock that wasn't there in the first place.
   */
  @Test
  public void testRemoveStock() {
    StockPortfolio portfolio = new StockPortfolioImpl();

    List<Stocks> expected = new ArrayList<Stocks>();

    portfolio.removeStock(appleStock);

    portfolio.addStock(googleStock, 1);
    portfolio.addStock(microsoftStock, 3);
    portfolio.removeStock(googleStock);
    expected.add(microsoftStock);

    assertEquals(expected, portfolio.getAllStocks());

    portfolio.removeStock(microsoftStock);
    expected.remove(microsoftStock);

    assertEquals(expected, portfolio.getAllStocks());

    portfolio.removeStock(microsoftStock);

    assertEquals(expected, portfolio.getAllStocks());
  }

  /**
   * Tests if a stock can be obtained by its symbol, using the getStockByName method.
   */
  @Test
  public void testGetStockByName() {
    StockPortfolio portfolio = new StockPortfolioImpl();

    portfolio.addStock(appleStock, 1);
    portfolio.addStock(microsoftStock,2);
    portfolio.addStock(googleStock, 3);

    Stocks shouldBeGoogle = portfolio.getStockByName("GOOG");
    Stocks shouldBeMicrosoft = portfolio.getStockByName("MSFT");
    Stocks shouldBeApple = portfolio.getStockByName("AAPL");

    assertEquals(googleStock, shouldBeGoogle);
    assertEquals(microsoftStock, shouldBeMicrosoft);
    assertEquals(appleStock, shouldBeApple);
  }

  /**
   * Tests that attempting to get a stock by name that has not been added throws an exception.
   */
  @Test(expected = NoSuchElementException.class)
  public void testGetStockByNameNotInPortfolio() {
    StockPortfolio portfolio = new StockPortfolioImpl();

    portfolio.addStock(appleStock,1);
    portfolio.addStock(microsoftStock,2);

    portfolio.getStockByName("GOOG");
  }

  @Test
  public void testEvaluate() {
    StockPortfolio portfolio = new StockPortfolioImpl();

//    portfolio.addStock();
  }
}