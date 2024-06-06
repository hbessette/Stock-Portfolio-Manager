import org.junit.Test;

import java.util.Arrays;

import stocks.api.AlphaVantageAPI;

import static org.junit.Assert.assertTrue;

/**
 * Tests the AlphaVantageAPI class.
 */
public class AlphaVantageAPITest {

  /**
   * Tests that getData correctly gets the data from either the AlphaVantage API, or local files.
   */
  @Test
  public void testGetData() {
    AlphaVantageAPI avapi = new AlphaVantageAPI("GOOG");
    String[] data = avapi.getData();
    assertTrue(Arrays.asList(data).contains(
            "2024-05-31,173.4000,174.4200,170.9700,173.9600,28085151"));
  }
}
