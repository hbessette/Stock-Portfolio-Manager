The jar file must be in the same directory as a folder named "StockData", and another folder
named "StockPortfolios".

===================

You can type "h" to see the list of commands.


Instructions for the interactions in the assignment description:
- First, type "add-portfolio"
- When prompted for a name, enter anything.
- To buy a stock, type "buy-stock"
- When prompted for a portfolio name, enter the name you used for your portfolio.
- When prompted for a symbol, enter the symbol of the stock you'd like to buy ("GOOG" for example).
- When prompted for amount, enter any whole number. This is the number of shares you are purchasing.
- Next, you will be prompted for a year, then a month, and then a day. Enter these as numbers. For example: 2024, then 5, then 6.
- After this, you may repeat the process for two more stocks.
- Then, evaluate this portfolio by typing "evaluate-portfolio". Enter your portfolio name when prompted.
- When prompted for a date for an evaluation, enter a date *after* the dates you bought the stocks on.
  You can enter a date before you bought any stocks, but the result will just be 0.

===================

The program should support all stocks with Symbols that Alpha Vantage has access to,
but it was tested on GOOG, AAPL, and MSFT.

The program should support all dates on which stock data is available (not weekends).