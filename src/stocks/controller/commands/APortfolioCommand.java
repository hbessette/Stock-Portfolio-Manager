package stocks.controller.commands;

abstract class APortfolioCommand implements StockControllerCommand {
  protected final String portfolioName;

  protected APortfolioCommand(String portfolioName) {
    this.portfolioName = portfolioName;
  }
}
