package stocks.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

public class AlphaVantageAPI {
  private String symbol;
  private final String apiKey;


  public AlphaVantageAPI(String symbol) {
    this.symbol = symbol;
    this.apiKey = "TJ5Y749LPC0JJLYJ";
  }

  private URL makeURL() {
    URL url = null;

    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=" + "full"
              + "&symbol"
              + "=" + this.symbol + "&apikey=" + this.apiKey + "&datatype=csv");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
    return url;
  }

  public String[] getData() {
    try {
      return getFileData();
    }
    catch (FileNotFoundException exception) {
      return getURLData();
    }
  }

  private String[] getFileData() throws FileNotFoundException {
    StringBuilder output = new StringBuilder();
    System.out.println("here");
    try {
      BufferedReader reader = new BufferedReader(new FileReader(
              "StockData/" + symbol +
                      "-" + LocalDate.now() + ".csv"
      ));
      String line = reader.readLine();

      while (line != null) {
        output.append(line + System.lineSeparator());
        line = reader.readLine();
      }
    }
    catch (IOException e) {
      System.out.println("exception");
      throw new FileNotFoundException("File does not exist");
    }

    System.out.println("worked");
    return output.toString().split(System.lineSeparator());
  }

  private String[] getURLData() {

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = makeURL().openStream();
      int b;

      while ((b=in.read())!=-1) {
        output.append((char)b);
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + this.symbol);
    }
    String[] returnValue = output.toString().split(System.lineSeparator());

    writeFile(returnValue);
    return returnValue;
  }

  private void writeFile(String[] output) {
    File file = new File("StockData/" + this.symbol + "-" + LocalDate.now() + ".csv");
    try {
      file.createNewFile();
      FileWriter fileWriter = new FileWriter("StockData/" + this.symbol + "-" + LocalDate.now() + ".csv");
      for (String line : output) {
        fileWriter.write(line + System.lineSeparator());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
