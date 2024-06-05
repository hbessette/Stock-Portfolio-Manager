package stocks.model.macros;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import stocks.model.StockDayStatus;
import stocks.model.Stocks;


public class StockMacroXDayMovingAverage implements StockMacro<Double> {
  Date startDate;
  int xDays;

  public StockMacroXDayMovingAverage(Date startDate, int xDays) {
    this.startDate = startDate;
    this.xDays = xDays;
  }

  @Override
  public Double apply(Stocks<Double> stock) throws IllegalArgumentException {
    Set<Date> validDatesSet = stock.getValidDates();

    if (!validDatesSet.contains(this.startDate)) {
      throw new IllegalArgumentException("Start date is not valid.");
    }

    ArrayList<Date> sortedValidDates = new ArrayList<Date>(validDatesSet);
    Collections.sort(sortedValidDates, Collections.reverseOrder());
    Iterator<Date> iterateDates = sortedValidDates.iterator();

    while (iterateDates.next() != this.startDate) {
      iterateDates.next();
    }

    ArrayList<Date> xDates = new ArrayList<Date>();
    for (int i = 0; i < this.xDays; i++) {
      try {
        xDates.add(iterateDates.next());
      }
      catch (NoSuchElementException e) {
        e.printStackTrace();
      }
    }

    double xDayAverage = 0;
    for (Date xDate : xDates) {
      xDayAverage += stock.getStockDayStatus(xDate).getClosingTime();
    }
    return xDayAverage / xDates.size();
  }
}
