package stocks.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import stocks.model.StockModel;
import stocks.view.StockView;

import java.util.Scanner;

public class StockControllerGUI implements StockController, ActionListener {
    private StockView view;
    private StockModel model;

    public StockControllerGUI(StockView view, StockModel model) {
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
            case "create-portfolio" :
                createPortfolioHelper();
                break;
            case "save-portfolio" :
                break;
            case "load-portfolio" :
                break;
            case "buy-stock" :
                break;
            case "sell-stock" :
                break;
        }
    }

    private void createPortfolioHelper() {
        String pcText = this.view.getComponentText("portfolio-creation-text");
        if (pcText.isEmpty() || pcText.contains(" ") || pcText.contains(System.lineSeparator())) {
            this.view.createPortfolioMessage("The portfolio name is invalid. It cannot be empty, " +
                    "contain spaces, or be over 20 characters long.");
        }
        else if (pcText.length() > 20) {
            this.view.createPortfolioMessage("Portfolio names cannot be over 20 characters long.");
        }
        else {
            try {
                this.model.addPortfolio(pcText);
                this.view.createPortfolioMessage("Successfully created portfolio " + pcText);
                this.view.addPortfolioGUI(pcText);
            }
            catch (Exception e) {
                this.view.createPortfolioMessage(e.getMessage());
            }
        }
    }


}
