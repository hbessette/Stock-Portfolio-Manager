import org.junit.Test;

import stocks.api.AlphaVantageAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AlphaVantageAPITest {

  @Test
  public void testGetData() {
    AlphaVantageAPI avapi = new AlphaVantageAPI("GOOG");
    String[] data = avapi.getData();
    fail("we need to redo this test since it won't work if you run it on a different day.");
//    assertEquals("timestamp,open,high,low,close,volume", data[0]);
//    assertEquals("2024-05-31,173.4000,174.4200,170.9700,173.9600,28085151", data[1]);
  }
}
