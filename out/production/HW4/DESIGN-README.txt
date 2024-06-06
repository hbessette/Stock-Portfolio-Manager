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
Portfolios are represented by an object containing a map of stocks and their respective shares.

Any operation on a stock is represented by a "StockMacro". These must have some
"apply(Stock stock)" method, which does something with the data of a given stock and returns the
result. There are macros for calculating the stock price change, x day moving average, and
x day crossovers. This design was used to allow easy future additions.

Finally, a "StockModel" class contains a collection of Portfolios, which can be added or removed
from. It also allows the creation of Stocks. This StockModel is the model used by the controller.



The controller package contains a StockController class, which takes in some scanner for user input,
a view object to push output to, and a StockModel to use to process data and get the results of
operations.
Similarly to the model having StockMacros, the controller has a collection of
"StockControllerCommands", which it calls based on input. Each of these commands uses some function
of the model and displays the result using the view. This too was used to allow more commands to be
easily implemented in the future.



The view package is the simplest, containing a view class which displays text to some PrintStream.
The controller uses this to print to the console. The view contains methods for a welcome message,
goodbye message, and commands list message.