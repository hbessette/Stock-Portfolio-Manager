package stocks.model.macros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import stocks.model.Stocks;

public abstract class AbstractStockMacroXDay {
  protected Double getXDayAverage(Stocks stock, Date startDate, int xDays) {
    List<Date> computedDates = getDatesInRange(stock, startDate, xDays);

    double xDayAverage = 0;
    for (Date xDate : computedDates) {
      xDayAverage += stock.getStockDayStatus(xDate).getClosingTime();
    }
    return xDayAverage / xDays;
  }

  protected List<Date> getDatesInRange(Stocks stock, Date startDate, int xDays) {
    Set<Date> validDatesSet = stock.getValidDates();

    if (!validDatesSet.contains(startDate)) {
      throw new IllegalArgumentException("Start date is not valid.");
    }

    ArrayList<Date> sortedValidDates = new ArrayList<Date>(validDatesSet);
    Collections.sort(sortedValidDates, Collections.reverseOrder());
    Iterator<Date> iterateDates = sortedValidDates.iterator();

    return sortedValidDates.subList(
            sortedValidDates.indexOf(startDate),
            sortedValidDates.indexOf(startDate)+xDays
    );
  }

}
