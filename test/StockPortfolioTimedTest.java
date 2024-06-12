import org.junit.Test;
import stocks.model.portfolio.StockPortfolioTimed;
import stocks.model.portfolio.StockPortfolioTimedImpl;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class StockPortfolioTimedTest {
  @Test
  public void testGetData() {
    StockPortfolioTimed portfolio = new StockPortfolioTimedImpl();
    portfolio.purchase("MSFT", new Date(2024, 4, 31), 10);
    portfolio.purchase("MSFT", new Date(2024, 4, 31), 10);
    portfolio.purchase("AAPL", new Date(2024, 4, 31), 10);
    portfolio.purchase("MSFT", new Date(2024, 5, 3), 10);
    String[] output = new String[] {"5,31,2024,MSFT,20.0,AAPL,10.0,", "6,3,2024,MSFT,10.0,"};
    assertEquals(output[0], portfolio.getData()[0]);
    assertEquals(output[1], portfolio.getData()[1]);
  }
}
