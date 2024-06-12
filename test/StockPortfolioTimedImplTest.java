import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import stocks.model.portfolio.StockPortfolioTimed;
import stocks.model.portfolio.StockPortfolioTimedImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StockPortfolioTimedImplTest {
  @Test
  public void testPurchase() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,5,22), 5);

    String[] expectedFirst = new String[] {"GOOG: 5.0 shares"};
    String[] actual = portfolio.getComposition(new Date(2024, 5, 26));

    assertEquals(Arrays.toString(expectedFirst), Arrays.toString(actual));

    portfolio.purchase("GOOG", new Date(2024,5,24), 5);
    portfolio.purchase("AAPL", new Date(2024,5,24), 7);
    portfolio.purchase("MSFT", new Date(2024,5,24), 1);
    String[] expected = new String[] {"GOOG: 10.0 shares", "AAPL: 7.0 shares", "MSFT: 1.0 share"};

    actual = portfolio.getComposition(new Date(2024, 5, 26));
    String[] actualBefore = portfolio.getComposition(new Date(2024, 5, 23));

    assertArrayEquals(expected, actual);
    assertArrayEquals(expectedFirst, actualBefore);
  }

  @Test
  public void testPurchaseAfterBefore() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,5,22), 5);
    portfolio.purchase("MSFT", new Date(2024,5,21), 2);

    String[] expectedFirst = new String[] {"GOOG: 5.0 shares", "MSFT: 2.0 shares"};
    String[] actual = portfolio.getComposition(new Date(2024, 5, 26));

    assertArrayEquals(expectedFirst, actual);
  }

  @Test
  public void testSell() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,5,22), 5);
    portfolio.sell("GOOG", new Date(2024, 5, 23), 2);

    String[] expected = new String[] {"GOOG: 3.0 shares"};
    String[] actual = portfolio.getComposition(new Date(2024, 5, 26));

    assertArrayEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSellFailsSellBefore() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,5,22), 5);
    portfolio.sell("GOOG", new Date(2024, 5, 21), 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSellFailsSellTooMuch() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,5,22), 5);
    portfolio.sell("GOOG", new Date(2024, 5, 23), 7);
  }

  @Test
  public void testSellSameDay() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,5,22), 5);
    portfolio.sell("GOOG", new Date(2024, 5, 22), 2);

    String[] expected = new String[] {"GOOG: 3.0 shares"};
    String[] actual = portfolio.getComposition(new Date(2024, 5, 26));

    assertArrayEquals(expected, actual);
  }

  /**
   * Tests an edge case in which you buy a stock on a day (day A),
   * then sell that stock on a future day (day B),
   * and then attempt to sell that stock on a day in-between the two (day C, A < C < B).
   * Technically you still have the stock at this date, but this would prevent you from selling
   * the stock in the future, which you have already committed to, so it should throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSellEdgeCase() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,5,21), 5);
    portfolio.sell("GOOG", new Date(2024, 5, 24), 3);
    portfolio.sell("GOOG", new Date(2024, 5, 22), 3);
  }


  @Test
  public void testSellInBetweenNotTooMuch() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,5,21), 5);
    portfolio.sell("GOOG", new Date(2024, 5, 24), 2);
    portfolio.sell("GOOG", new Date(2024, 5, 22), 2);

    String[] expected22 = new String[] {"GOOG: 3.0 shares"};
    String[] actual22 = portfolio.getComposition(new Date(2024, 5, 22));

    String[] expected24 = new String[] {"GOOG: 1.0 share"};
    String[] actual24 = portfolio.getComposition(new Date(2024, 5, 24));

    assertArrayEquals(expected22, actual22);
    assertArrayEquals(expected24, actual24);
  }
}