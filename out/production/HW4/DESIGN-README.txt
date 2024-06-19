Design changes:
- Stocks and portfolios now work with a double amount of shares rather than an integer.
  This change allows fractional shares.

- The portfolio class used in this program was a new implementation, because our previous
  implementation could not support adding and removing stocks at specific dates.

- All operations called by the controller are now public methods in the model, rather than the
  model passing a portfolio or stock which the controller then operates on. This change prevents
  an outsider from mutating the model without directly calling a method in the model interface.

- The controller and view were changed so that the commands used sequential inputs, rather than
  having the user input long commands with arguments. This change was made to prevent user error,
  since it prompts the user for every field.

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
Similarly to the model having StockMacros, the controller has a collection of
"StockControllerCommands", which it calls based on input. Each of these commands uses some function
of the model and displays the result using the view. This too was used to allow more commands to be
easily implemented in the future. Commands ask for sequential inputs for each field they require,
to prevent user error.



The view package is the simplest, containing a view class which displays text to some PrintStream.
The controller uses this to print to the console. The view contains methods for a welcome message,
goodbye message, and commands list message.