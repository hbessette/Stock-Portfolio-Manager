package stocks.model.stock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import stocks.api.AlphaVantageAPI;
import stocks.model.daystatus.StockDayStatus;
import stocks.model.daystatus.StockDayStatusImpl;

public class StocksImpl implements Stocks {
  private final Map<Date, StockDayStatus> data;
  private final String symbol;

  public StocksImpl(String symbol) {
    this.symbol = symbol;
    this.data = loadData(this.symbol);
  }

  @Override
  public StockDayStatus getStockDayStatus(Date date) throws IllegalArgumentException {
    if (!this.data.containsKey(date)) {
      throw new IllegalArgumentException("Date does not exist.");
    } else {
      return this.data.get(date);
    }
  }

  @Override
  public String getSymbol() {
    return this.symbol;
  }

  @Override
  public Set<Date> getValidDates() {
    return this.data.keySet();
  }

  private static Map<Date, StockDayStatus> loadData(String symbol) {
    AlphaVantageAPI avapi = new AlphaVantageAPI(symbol);

    String[] data = avapi.getData();

    HashMap<Date, StockDayStatus> returnData = new HashMap<Date, StockDayStatus>();
    for (String line : data) {
      String[] dataPoints = line.split(",");
      String[] dateNumbers = dataPoints[0].split("-");
      Date date = new Date(
              Integer.parseInt(dateNumbers[0]),
              Integer.parseInt(dateNumbers[1]) - 1,
              Integer.parseInt(dateNumbers[2])
      );
      double closingPrice = Double.parseDouble(dataPoints[4]);
      int volume = Integer.parseInt(dataPoints[5]);
      StockDayStatus sds = new StockDayStatusImpl(date, closingPrice, volume);
      returnData.put(date, sds);
    }

    return returnData;
  }
}
