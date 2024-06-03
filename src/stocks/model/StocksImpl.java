package stocks.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import stocks.AlphaVantageAPI;

public class StocksImpl implements Stocks {
  private final Map<Date, StockDayStatus> data;
  private final String symbol;

  public StocksImpl(String symbol) {
    this.symbol = symbol;
    data = loadData(this.symbol);
  }

  @Override
  public void execute(StockMacro stockMacro) {
    stockMacro.apply(this);
  }

  @Override
  public StockDayStatus getStockDayStatus(Date date) {
    return data.get(date);
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
              Integer.parseInt(dateNumbers[1]),
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
