package stocks.controller.commands;

import stocks.model.StockModel;
import stocks.view.StockView;

public class ShowAllPortfolios extends APortfolioCommand {

  public ShowAllPortfolios(String portfolioName) {
    super(portfolioName);
  }

  @Override
  public void start(StockView view, StockModel model) {
    try {

    }
  }
}
