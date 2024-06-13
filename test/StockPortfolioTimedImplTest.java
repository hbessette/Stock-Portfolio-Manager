import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import stocks.model.portfolio.StockPortfolioTimed;
import stocks.model.portfolio.StockPortfolioTimedImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

  @Test
  public void testSellAll() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    portfolio.purchase("GOOG", new Date(2024, 5, 21), 7);
    portfolio.sellAll("GOOG", new Date(2024, 5, 22));

    assertArrayEquals(new String[] {"GOOG: 0.0 shares"}, portfolio.getComposition(new Date(
            2024, 5, 22
    )));
  }

  @Test
  public void testGetDistribution() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,4,24), 5);
    portfolio.purchase("AAPL", new Date(2024,4,24), 7);
    portfolio.purchase("MSFT", new Date(2024,4,24), 1);
    String[] expected = new String[] {
            "GOOG: $881.6500000000001", "AAPL: $1329.86", "MSFT: $430.16"};
    String[] actual = portfolio.getDistribution(new Date(2024, 4, 24));

    assertArrayEquals(expected, actual);
  }

  @Test
  public void testEvaluate() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,4,24), 5);
    portfolio.purchase("AAPL", new Date(2024,4,24), 7);
    portfolio.purchase("MSFT", new Date(2024,4,24), 1);
    double expected = 881.6500000000001 + 1329.86 + 430.16;
    double actual = portfolio.evaluate(new Date(2024, 4, 24));

    assertEquals(expected, actual, 0.0);
  }

  @Test
  public void testRebalance() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,4,24), 1);
    portfolio.purchase("AAPL", new Date(2024,4,24), 1);

    Map<String, Double> percentages = new HashMap<String, Double>();
    percentages.put("GOOG", 50.0);
    percentages.put("AAPL", 50.0);
    portfolio.rebalance(new Date(2024, 4, 29), percentages);

    String[] expected = new String[] {"GOOG: 1.0363303269447575 shares",
            "AAPL: 0.9661306427032424 shares"};
    assertArrayEquals(expected, portfolio.getComposition(new Date(2024, 4, 30)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRebalanceBadPercentages() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    portfolio.purchase("GOOG", new Date(2024,4,24), 1);
    portfolio.purchase("AAPL", new Date(2024,4,24), 1);

    Map<String, Double> percentages = new HashMap<String, Double>();
    percentages.put("GOOG", 49.0);
    percentages.put("AAPL", 50.0);
    portfolio.rebalance(new Date(2024, 4, 29), percentages);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRebalanceNoShares() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    Map<String, Double> percentages = new HashMap<String, Double>();
    percentages.put("GOOG", 100.0);
    portfolio.rebalance(new Date(2024, 4, 29), percentages);
  }

  @Test
  public void testRebalanceOneStock() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,4,24), 1);

    Map<String, Double> percentages = new HashMap<String, Double>();
    percentages.put("GOOG", 100.0);
    portfolio.rebalance(new Date(2024, 4, 29), percentages);

    String[] expected = new String[] {"GOOG: 1.0 share"};
    assertArrayEquals(expected, portfolio.getComposition(new Date(2024, 4, 30)));
  }

  @Test
  public void testPerformanceOverTime() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("MSFT", new Date(2024,3,24), 6);
    portfolio.purchase("AAPL", new Date(2024,1,12), 1);

    String performanceTime = portfolio.performanceOverTime(
            new Date(2024, 1, 12),
            new Date(2024, 4, 29)
    );

    assertEquals("Feb 2024: \n" +
            "Mar 2024: **\n" +
            "Apr 2024: **\n" +
            "May 2024: ***************************\n" +
            "\n" +
            "Scale: * = $100", performanceTime);
  }

  @Test
  public void testPerformanceOverTimeYears() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("MSFT", new Date(2020,3,24), 6);
    portfolio.purchase("AAPL", new Date(2024,1,12), 1);

    String performanceTime = portfolio.performanceOverTime(
            new Date(2016, 1, 12),
            new Date(2024, 4, 29)
    );

    assertEquals("2016: \n" +
            "2017: \n" +
            "2018: \n" +
            "2019: \n" +
            "2020: \n" +
            "2021: *****************\n" +
            "2022: *********************\n" +
            "2023: ******************\n" +
            "\n" +
            "Scale: * = $90", performanceTime);
  }

  @Test
  public void testPerformanceOverTimeDays() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("MSFT", new Date(2024,1,12), 6);
    portfolio.purchase("AAPL", new Date(2024,1,12), 1);

    String performanceTime = portfolio.performanceOverTime(
            new Date(2024, 1, 12),
            new Date(2024, 1, 16)
    );

    assertEquals("11 Feb 2024: \n" +
            "12 Feb 2024: ***************************\n" +
            "13 Feb 2024: ***************************\n" +
            "14 Feb 2024: ***************************\n" +
            "\n" +
            "Scale: * = $100", performanceTime);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPerformanceOverTimeReversed() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("MSFT", new Date(2024,3,24), 6);
    portfolio.purchase("AAPL", new Date(2024,1,12), 1);

    portfolio.performanceOverTime(
            new Date(2024, 4, 29),
            new Date(2024, 1, 12)
    );
  }

  @Test
  public void testEvaluateBuyAfter() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,4,24), 5);
    portfolio.purchase("AAPL", new Date(2024,4,24), 6);
    portfolio.purchase("MSFT", new Date(2024,1,12), 1);

    double expected = 2451.69;
    double actual = portfolio.evaluate(new Date(2024, 4, 24));
    assertEquals(expected, actual, 0.0);

    double expectedEarlier = 409.06;
    double actualEarlier = portfolio.evaluate(new Date(2024, 3, 24));
    assertEquals(expectedEarlier, actualEarlier, 0.0);
  }

  @Test
  public void testCompositionBuyAfter() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();

    portfolio.purchase("GOOG", new Date(2024,4,24), 5);
    portfolio.purchase("AAPL", new Date(2024,4,24), 6);
    portfolio.purchase("MSFT", new Date(2024,1,12), 1);

    String[] expected = new String[]{"GOOG: 5.0 shares", "AAPL: 6.0 shares", "MSFT: 1.0 share"};
    String[] actual = portfolio.getComposition(new Date(2024, 4, 24));
    assertArrayEquals(expected, actual);

    String[] expectedEarlier = new String[]{"MSFT: 1.0 share"};
    String[] actualEarlier = portfolio.getComposition(new Date(2024, 3, 24));
    assertArrayEquals(expectedEarlier, actualEarlier);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSellAllNoShares() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    portfolio.sellAll("dkjsalfkf", new Date(2024, 3, 24));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSellAllNoSharesExists() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    portfolio.purchase("GOOG", new Date(2024, 3, 24), 34.0);
    portfolio.sellAll("dkjsalfkf", new Date(2024, 3, 24));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSellNoSharesExists() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    portfolio.sell("GOOG", new Date(2024, 3, 24), 0.0);
  }

  @Test
  public void testCompositionNoShares() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    String[] actual = portfolio.getComposition(new Date(2024, 3, 24));
    assertArrayEquals(new String[] {"No stocks are owned at this time."}, actual);
  }

  @Test
  public void testDistributionNoShares() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    String[] actual = portfolio.getDistribution(new Date(2024, 3, 24));
    assertArrayEquals(new String[] {"No stocks are owned at this time."}, actual);
  }

  @Test
  public void testGetStockNames() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    portfolio.purchase("GOOG", new Date(2024, 3, 24), 1.0);
    portfolio.purchase("MSFT", new Date(2024, 3, 24), 1.0);
    String[] names = portfolio.getStockNames(new Date(2024, 3, 24));
    String[] expected = new String[] {"GOOG", "MSFT"};

    assertArrayEquals(expected, names);
  }
}
