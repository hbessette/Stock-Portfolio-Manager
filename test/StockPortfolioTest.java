import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import stocks.model.portfolio.StockPortfolio;
import stocks.model.portfolio.StockPortfolioImpl;
import stocks.model.portfolio.shares.StockAndShares;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import static org.junit.Assert.*;

/**
 * Tests for StockPortfolios.
 */
public class StockPortfolioTest {

  /**
   * Tests if adding a stock works correctly.
   * Also tests if getting all stocks works correctly, since the two cannot be tested separately.
   * Tests that getting all stocks with their shares works as well.
   */
  @Test
  public void testAddStockAndGetAllStocks() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");

    StockPortfolio portfolio = new StockPortfolioImpl();

    List<Stocks> expected = new ArrayList<Stocks>();
    assertEquals(expected, portfolio.getAllStocks());

    expected.add(googleStock);
    portfolio.addStock(googleStock, 1);
    assertEquals(expected, portfolio.getAllStocks());

    expected.add(microsoftStock);
    portfolio.addStock(microsoftStock, 2);
    assertTrue(portfolio.getAllStocks().equals(expected));

    List<StockAndShares> expectedSAS = new ArrayList<StockAndShares>();
    expectedSAS.add(new StockAndShares(googleStock, 1));
    expectedSAS.add(new StockAndShares(microsoftStock, 2));
    assertTrue(portfolio.getAllStocksAndShares().equals(expectedSAS));
  }

  /**
   * Tests if the removeStock function properly removes stocks.
   * Also tests that nothing happens when you remove a stock that wasn't there in the first place.
   */
  @Test
  public void testRemoveStock() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");

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
   * Tests that removing a certain amount of shares of a stock works.
   * Tests that nothing happens if you remove shares before you added any.
   */
  @Test
  public void testRemoveStockShares() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");

    StockPortfolio portfolio = new StockPortfolioImpl();

    String expected;

    portfolio.removeStockShares(appleStock, 3);

    portfolio.addStock(googleStock, 1);
    portfolio.addStock(microsoftStock, 3);
    portfolio.removeStockShares(googleStock, 1);
    expected = "Added:GOOG,Shares:1."
            + "Added:MSFT,Shares:3."
            + "Removed:GOOG,Shares:1.";

    assertEquals(expected, portfolio.returnLog());

    portfolio.removeStockShares(microsoftStock, 1);
    expected = "Added:GOOG,Shares:1."
            + "Added:MSFT,Shares:3."
            + "Removed:GOOG,Shares:1."
            + "Removed:MSFT,Shares:1.";

    assertEquals(expected, portfolio.returnLog());
  }

  /**
   * Tests that you cannot remove more shares than you have (it throws an exception).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveStockSharesFail() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");

    StockPortfolio portfolio = new StockPortfolioImpl();

    portfolio.addStock(appleStock, 5);
    portfolio.removeStockShares(appleStock, 6);
  }

  /**
   * Tests if a stock can be obtained by its symbol, using the getStockByName method.
   */
  @Test
  public void testGetStockByName() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");

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
   * Tests if a stockAndShares can be obtained by its symbol, using the getStockByName method.
   */
  @Test
  public void testGetStockAndSharesByName() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");

    StockPortfolio portfolio = new StockPortfolioImpl();

    portfolio.addStock(appleStock, 1);
    portfolio.addStock(microsoftStock,2);
    portfolio.addStock(googleStock, 3);

    StockAndShares shouldBeGoogle = portfolio.getStockAndSharesByName("GOOG");
    StockAndShares shouldBeMicrosoft = portfolio.getStockAndSharesByName("MSFT");
    StockAndShares shouldBeApple = portfolio.getStockAndSharesByName("AAPL");

    StockAndShares expectedApple = new StockAndShares(appleStock, 1);
    StockAndShares expectedMicrosoft = new StockAndShares(microsoftStock, 2);
    StockAndShares expectedGoogle = new StockAndShares(googleStock, 3);

    assertEquals(expectedGoogle, shouldBeGoogle);
    assertEquals(expectedMicrosoft, shouldBeMicrosoft);
    assertEquals(expectedApple, shouldBeApple);
  }

  /**
   * Tests that attempting to get a stock by name that has not been added throws an exception.
   */
  @Test(expected = NoSuchElementException.class)
  public void testGetStockByNameNotInPortfolio() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");

    StockPortfolio portfolio = new StockPortfolioImpl();

    portfolio.addStock(appleStock,1);
    portfolio.addStock(microsoftStock,2);

    portfolio.getStockByName("GOOG");
  }

  /**
   * Tests that the evaluate feature works.
   * It should get the value of the portfolio on a specific date.
   */
  @Test
  public void testEvaluate() {
    Stocks googleStock = new StocksImpl("GOOG");
    Stocks microsoftStock = new StocksImpl("MSFT");
    Stocks appleStock = new StocksImpl("AAPL");

    StockPortfolio portfolio = new StockPortfolioImpl();

    portfolio.addStock(appleStock, 1);

    assertEquals(
            String.valueOf(183.3800),
            String.valueOf(portfolio.evaluate(new Date(2024,4,3))
            ));

    portfolio.addStock(appleStock, 2);

    assertEquals(
            String.valueOf(183.3800 * 3),
            String.valueOf(portfolio.evaluate(new Date(2024,4,3))
            ));

    portfolio.addStock(microsoftStock, 5);

    assertEquals(
            String.valueOf((183.3800 * 3) + (406.6600 * 5)),
            String.valueOf(portfolio.evaluate(new Date(2024,4,3))
            ));
  }
}