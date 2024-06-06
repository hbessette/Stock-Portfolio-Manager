package stocks.controller.commands;

/**
 * To abstract commands that need a portfolio name.
 */
abstract class APortfolioCommand implements StockControllerCommand {
  protected final String portfolioName;

  protected APortfolioCommand(String portfolioName) {
    this.portfolioName = portfolioName;
  }
}
