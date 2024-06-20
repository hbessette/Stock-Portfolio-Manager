package stocks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import stocks.model.StockModel;
import stocks.view.IStockViewGUI;

import java.util.Date;
import java.util.Scanner;

public class StockControllerGUI implements StockController, ActionListener {
    private IStockViewGUI view;
    private StockModel model;

    public StockControllerGUI(IStockViewGUI view, StockModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void start() {
        this.view.startListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        switch (arg0.getActionCommand()) {
            case "create-portfolio":
                createPortfolioHelper();
                break;
            case "save-portfolio":
                saveHelper();
                break;
            case "load-portfolio":
                loadHelper();
                break;
            case "buy-stock":
                buyHelper();
                break;
            case "sell-stock":
                sellHelper();
                break;
            case "update-value-comp":
                valueCompHelper();
                break;
        }
    }

    private void valueCompHelper() {
        String portfolioName = this.view.getComponentText("value-comp-portfolio");
        int year = Integer.parseInt(this.view.getComponentText("value-comp-year"));
        int month = Integer.parseInt(this.view.getComponentText("value-comp-month"));
        int day = Integer.parseInt(this.view.getComponentText("value-comp-day"));
        Date date = new Date(year, month - 1, day);

        if (portfolioName.equals("null")) {
            this.view.buySellStockMessage("Please create or load a portfolio first.");
            return;
        }

        try {
            String[] composition = this.model.getCompositionForPortfolio(portfolioName, date);
            double value = this.model.getPortfolioValue(portfolioName, date);
            StringBuilder message = new StringBuilder("Value of " + portfolioName + " on " + day +
                    "-" + month + "-" + year + ": $" + value);
            if (value == 0.0) {
                this.view.valueCompositionMessage(message.toString());
            }
            else {
                message.append(System.lineSeparator()).append("Composition:").append(System.lineSeparator());
                for (String comp : composition) {
                    message.append(comp).append(System.lineSeparator());
                }
                this.view.valueCompositionMessage(message.toString());
            }
        }
        catch (Exception e) {
            this.view.valueCompositionMessage(e.getMessage());
        }
    }

    private void buyHelper() {
        String portfolioName = this.view.getComponentText("buy-sell-portfolio-name");

        if (portfolioName.equals("null")) {
            this.view.buySellStockMessage("Please create or load a portfolio first.");
            return;
        }

        String symbolName = this.view.getComponentText("buy-sell-symbol-name");
        int quantity = Integer.parseInt(this.view.getComponentText("buy-sell-quantity"));
        int year = Integer.parseInt(this.view.getComponentText("buy-sell-year"));
        int month = Integer.parseInt(this.view.getComponentText("buy-sell-month"));
        int day = Integer.parseInt(this.view.getComponentText("buy-sell-day"));

        if (symbolName.isEmpty()) {
            this.view.buySellStockMessage("Please enter a valid stock symbol.");
            return;
        }

        try {
            this.model.purchaseStockForPortfolio(portfolioName, symbolName,
                    new Date(year, month - 1, day), quantity);
            this.view.buySellStockMessage("Successfully bought " + quantity + " shares of " + symbolName
                    + " on " + day + "-" + month + "-" + year);
        }
        catch (Exception e) {
            this.view.buySellStockMessage(e.getMessage());
        }
    }

    private void sellHelper() {
        String symbolName = this.view.getComponentText("buy-sell-symbol-name");
        String portfolioName = this.view.getComponentText("buy-sell-portfolio-name");
        int quantity = Integer.parseInt(this.view.getComponentText("buy-sell-quantity"));
        int year = Integer.parseInt(this.view.getComponentText("buy-sell-year"));
        int month = Integer.parseInt(this.view.getComponentText("buy-sell-month"));
        int day = Integer.parseInt(this.view.getComponentText("buy-sell-day"));

        if (portfolioName.equals("null")) {
            this.view.buySellStockMessage("Please create or load a portfolio first.");
            return;
        }

        if (symbolName.isEmpty()) {
            this.view.buySellStockMessage("Please enter a valid stock symbol.");
            return;
        }

        try {
            this.model.sellStockForPortfolio(portfolioName, symbolName,
                    new Date(year, month - 1, day), quantity);
            this.view.buySellStockMessage("Successfully sold " + quantity + " shares of " + symbolName
                    + " on " + day + "-" + month + "-" + year);
        }
        catch (Exception e) {
            this.view.buySellStockMessage(e.getMessage());
        }
    }

    private void saveHelper() {
        String slText = this.view.getComponentText("save-load-portfolio-text");
        if (isNotValidPortfolioName(slText)) {
            this.view.saveLoadPortfolioMessage("The portfolio name is invalid. It cannot be empty, " +
                    "contain spaces, or be over 20 characters long.");
        }
        else {
            try {
                this.model.savePortfolio(slText);
                this.view.saveLoadPortfolioMessage("Successfully saved portfolio " + slText);
            } catch (Exception e) {
                this.view.saveLoadPortfolioMessage(e.getMessage());
            }
        }
    }

    private void loadHelper() {
        String slText = this.view.getComponentText("save-load-portfolio-text");
        if (isNotValidPortfolioName(slText)) {
            this.view.saveLoadPortfolioMessage("The portfolio name is invalid. It cannot be empty, " +
                    "contain spaces, or be over 20 characters long.");
        }
        else {
            try {
                this.model.loadPortfolio(slText);
                this.view.saveLoadPortfolioMessage("Successfully loaded portfolio " + slText);
                this.view.addPortfolioGUI(slText);
            } catch (Exception e) {
                this.view.saveLoadPortfolioMessage(e.getMessage());
            }
        }
    }

    private boolean isNotValidPortfolioName(String name) {
        return name.isEmpty() || name.contains(" ") || name.contains(System.lineSeparator()) || name.length() > 20;
    }

    private void createPortfolioHelper() {
        String pcText = this.view.getComponentText("portfolio-creation-text");
        if (isNotValidPortfolioName(pcText)) {
            this.view.createPortfolioMessage("The portfolio name is invalid. It cannot be empty, " +
                    "contain spaces, or be over 20 characters long.");
        }
        else {
            try {
                this.model.addPortfolio(pcText);
                this.view.createPortfolioMessage("Successfully created portfolio " + pcText);
                this.view.addPortfolioGUI(pcText);
            } catch (Exception e) {
                this.view.createPortfolioMessage(e.getMessage());
            }
        }
    }


}
