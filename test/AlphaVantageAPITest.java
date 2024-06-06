import org.junit.Test;

import java.util.Arrays;

import stocks.api.AlphaVantageAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AlphaVantageAPITest {

  @Test
  public void testGetData() {
    AlphaVantageAPI avapi = new AlphaVantageAPI("GOOG");
    String[] data = avapi.getData();
    assertTrue(Arrays.asList(data).contains(
            "2024-05-31,173.4000,174.4200,170.9700,173.9600,28085151"));
  }
}
