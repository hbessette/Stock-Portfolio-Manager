Design changes:
- One additional method was added to the model to return the monetary value of a portfolio.
  This was added to better support getting the value of a portfolio for the GUI.

- No other changes were made to existing code (except for the main method, which had to be changed).
  Instead, a new controller and view were created for the GUI version.

================================================

The design is split into four main packages: model, view, controller, and api.



The api package contains a class which collects data about a stock.
Data is collected from the local folder "StockData", but if no data for the specified stock is found
there, data is obtained from the AlphaVantage api, and is also downloaded to the "StockData"
folder for future use.

The Model package contains all operations required to process stock data.
Stocks are represented as an object with a symbol and a map containing a "StockDayStatus"
for every date for which there is data.
A StockDayStatus represents the data about a stock on a specific date (the volume of trade,
the closing price, etc.)
Portfolios are represented by an object containing a map of dates and a "StockPortfolioTimeStatus"
class, which contains stocks and their shares owned on that date. The map has an entry for each
date for which there is a change or multiple changes (buying a stock, selling a stock, etc.).
This way, when the user prompts for some operation on a portfolio, the data used in the operation
is easily obtained by the most recent date entry in relation to the date the user wants the
operation done (i.e., user buys google stock on 6/1/2024, sells it on 6/5/2024, but then asks the
value of the portfolio on 6/2/2024. In this case the shares owned are gotten from the
6/1/2024 entry, so the value of the portfolio will be shown correctly for 6/2/2024).

Any operation on a stock is represented by a "StockMacro". These must have some
"apply(Stock stock)" method, which does something with the data of a given stock and returns the
result. There are macros for calculating the stock price change, x day moving average, and
x day crossovers. This design was used to allow easy future additions.

Finally, a "StockModel" class contains a collection of Portfolios, which can be added or removed
from. It allows the creation of Stocks, and facilitates every feature of the program including
buying and selling stocks, checking the composition of a portfolio, and so on.
This StockModel is the model used by the controller.



The controller package contains a StockController class, which takes in some scanner for user input,
a view object to push output to, and a StockModel to use to process data and get the results of
operations.

There are two StockControllers, one for the text version and one for the GUI.

The GUI controller acts as a listener for swing events, and calls methods in the model to get the
output. It calls methods from the view to update its components.

Similarly to the model having StockMacros, the text controller has a collection of
"StockControllerCommands", which it calls based on input. Each of these commands uses some function
of the model and displays the result using the view. This too was used to allow more commands to be
easily implemented in the future. Commands ask for sequential inputs for each field they require,
to prevent user error.


There are also two views, one for the text version and one for the GUI.

The GUI view uses Swing to create a simple GUI with buttons, text fields, and other elements.
All listeners are directed to the controller, and a method exists to get the current values of
elements in the GUI, for use in the controller.

The text view is very simple, merely displaying text to some PrintStream.