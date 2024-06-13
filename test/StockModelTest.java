import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import stocks.model.StockModel;
import stocks.model.StockModelImpl;
import stocks.model.stock.Stocks;
import stocks.model.stock.StocksImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    Set<String> port = model.getAllPortfolios();

    assertTrue(port.contains("Tester"));
    assertTrue(port.contains("A"));
    assertTrue(port.contains("B"));
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

    assertTrue(model.getAllPortfolios().contains("Tester"));
    model.removePortfolio("Tester");
    assertFalse(model.getAllPortfolios().contains("Tester"));
    model.removePortfolio("Something");
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
    model.purchaseStockForPortfolio("Tester", "AAPL",
            new Date(2024, 4, 28), 1);
    double expected = 189.9900;
    double result = model.evaluatePortfolio("Tester",
            new Date(2024, 4, 28)
    );
    assertEquals(String.valueOf(expected), String.valueOf(result));
  }

  @Test(expected = NoSuchElementException.class)
  public void testEvaluatePortfolioFail() {
    StockModel model = new StockModelImpl();

    model.evaluatePortfolio("Nonexistant", new Date(2024,11,2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoSuchStock() {
    StockModel model = new StockModelImpl();
    model.addPortfolio("test");
    model.purchaseStockForPortfolio("test", "djfsidjlfkjsdlf",
            new Date(2024, 1, 6), 1.0);
  }

  @Test(expected = NoSuchElementException.class)
  public void testGetPortfolioNotAdded() {
    StockModel model = new StockModelImpl();
    model.evaluatePortfolio("DNE", new Date(2024, 5, 2));
  }

  @Test
  public void testGetComposition() {
    StockModel model = new StockModelImpl();
    model.addPortfolio("e");
    model.purchaseStockForPortfolio("e", "GOOG", new Date(2024, 5, 11), 1.0);
    model.sellStockForPortfolio("e", "GOOG", new Date(2024, 5, 11), 0.5);
    String[] comp = model.getCompositionForPortfolio("e", new Date(2024, 5, 11));
    assertArrayEquals(new String[] {"GOOG: 0.5 shares"}, comp);
  }

  @Test
  public void testGetDistribution() {
    StockModel model = new StockModelImpl();
    model.addPortfolio("e");
    model.purchaseStockForPortfolio("e", "GOOG", new Date(2024, 5, 11), 1.0);
    String[] dist = model.getDistributionForPortfolio("e", new Date(2024, 5, 11));
    assertArrayEquals(new String[] {"GOOG: $178.19"}, dist);
  }

  @Test
  public void testGetPriceChangeForStock() {
    StockModel model = new StockModelImpl();
    double change = model.getPriceChangeForStock("MSFT",
            new Date(2024, 5, 10),
            new Date(2024, 5, 11)
    );
    assertEquals(4.81, change, 0.0);
  }

  @Test
  public void testXDayCrossovers() {
    StockModel model = new StockModelImpl();
    String[] xcross = model.getXDayCrossovers("MSFT",
            new Date(2024, 5, 10),
            3
    );
    assertArrayEquals(new String[] {"6-10-2024"}, xcross);
  }

  @Test
  public void testRebalance() {
    StockModel model = new StockModelImpl();
    model.addPortfolio("e");

    model.purchaseStockForPortfolio("e", "GOOG", new Date(2024,4,24), 1);
    model.purchaseStockForPortfolio("e", "AAPL", new Date(2024,4,24), 1);

    Map<String, Double> percentages = new HashMap<String, Double>();
    percentages.put("GOOG", 50.0);
    percentages.put("AAPL", 50.0);
    model.rebalancePortfolio("e", new Date(2024, 4, 29), percentages);

    String[] expected = new String[] {"GOOG: 1.0363303269447575 shares",
            "AAPL: 0.9661306427032424 shares"};
    assertArrayEquals(expected, model.getCompositionForPortfolio("e", new Date(2024, 4, 30)));
  }

  @Test
  public void testGetStockNames() {
    StockModel model = new StockModelImpl();
    model.addPortfolio("e");
    model.purchaseStockForPortfolio("e", "GOOG", new Date(2024,4,24), 1);
    model.purchaseStockForPortfolio("e", "AAPL", new Date(2024,4,24), 1);

    String[] names = model.getStockNamesForPortfolio("e", new Date(2024,4,24));
    assertArrayEquals(new String[] {"GOOG", "AAPL"}, names);
  }

  @Test
  public void testSellAll() {
    StockModel model = new StockModelImpl();
    model.addPortfolio("e");
    model.purchaseStockForPortfolio("e", "GOOG", new Date(2024,4,24), 1);
    model.sellAllStockForPortfolio("e", "GOOG", new Date(2024,4,24));

    String[] dist = model.getDistributionForPortfolio("e", new Date(2024, 5, 11));
    assertArrayEquals(new String[] {"GOOG: $0.0"}, dist);
  }
}