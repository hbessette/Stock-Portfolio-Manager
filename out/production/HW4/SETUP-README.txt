The jar file must be in the same directory as a folder named "StockData".

===================

Instructions for the interactions in the assignment description:
- To create a portfolio with 3 different stocks:
    - First type "add-portfolio Test".
    - Next type "add-stock Test GOOG 5"
    - Add more stocks the same way:
        - "add-stock Test AAPL 3"
        - "add-stock Test MSFT 1"
- To create another portfolio with 2 different stocks:
    - "add-portfolio Second"
    - "add-stock Second GOOG 1"
    - "add-stock Second AAPL 4"
- To query their values:
    - "evaluate-portfolio Test "
    - "evaluate-portfolio Second"

===================

The program should support all stocks with Symbols that Alpha Vantage has access to,
but it was tested on GOOG, AAPL, and MSFT.

The program should support all dates on which stock data is available (not weekends).