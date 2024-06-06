package stocks.model;

import stocks.model.portfolio.StockPortfolio;
import stocks.model.stock.Stocks;

import java.util.Date;
import java.util.List;

public interface StockModel {
  void addPortfolio(String name);

  void removePortfolio(String name);

  StockPortfolio getPortfolioByName(String name);

  double evaluatePortfolio(String name, Date date);

  Stocks getStockByName(String symbol);

  List<StockPortfolio> getAllPortfolios();

  String returnLog();
}
