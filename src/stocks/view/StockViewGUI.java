package stocks.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class StockViewGUI extends JFrame implements StockView {

    private JPanel mainPanel;
    private JScrollPane mainScrollPane;

    private String[] loadedPortfolios;

    private JLabel createPortfolioLabel;
    private JLabel saveLoadLabel;
    private JLabel buySellLabel;
    private JLabel valueCompositionLabel;

    private JButton portfolioCreationButton;
    private JButton savePortfolioButton;
    private JButton loadPortfolioButton;
    private JComboBox<String> buySellPortfolioComboBox;
    private JComboBox<String> valueCompPortfolioComboBox;
    private JButton buyButton;
    private JButton sellButton;

    private JTextArea portfolioCreationTextArea;

    private void createPortfolioPanel() {
        JPanel portfolioCreationPanel = new JPanel();
        portfolioCreationPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
        portfolioCreationPanel.setLayout(new BoxLayout(portfolioCreationPanel, BoxLayout.PAGE_AXIS));

        this.portfolioCreationTextArea = new JTextArea(1, 20);
        this.portfolioCreationTextArea.setBorder(BorderFactory.createTitledBorder("Enter Portfolio name"));
        JScrollPane jScrollPane = new JScrollPane(this.portfolioCreationTextArea);
        portfolioCreationPanel.add(jScrollPane);

        this.portfolioCreationButton = new JButton("Create");
        portfolioCreationPanel.add(this.portfolioCreationButton);

        // This should change to "successfully created [name] or "portfolio name may not contain newlines or spaces"
        this.createPortfolioLabel = new JLabel("Once you've attempted to create a portfolio, " +
                "confirmation will display here.");
        portfolioCreationPanel.add(this.createPortfolioLabel);

        this.mainPanel.add(portfolioCreationPanel);
    }

    private void saveLoadPortfolioPanel() {
        JPanel saveLoadPanel = new JPanel();
        saveLoadPanel.setBorder(BorderFactory.createTitledBorder("Save or Load Portfolio"));
        saveLoadPanel.setLayout(new BoxLayout(saveLoadPanel, BoxLayout.PAGE_AXIS));

        JLabel saveLoadExplanation = new JLabel("To save a portfolio, enter its name and click save. " +
                "To load a portfolio, enter the file name (without the .txt) and click load.");
        saveLoadPanel.add(saveLoadExplanation);

        JTextArea saveLoadTextArea = new JTextArea(1, 20);
        saveLoadTextArea.setBorder(BorderFactory.createTitledBorder("Enter name of Portfolio to save/load"));
        JScrollPane jScrollPane = new JScrollPane(saveLoadTextArea);
        saveLoadPanel.add(jScrollPane);

        this.savePortfolioButton = new JButton("Save");
        saveLoadPanel.add(this.savePortfolioButton);

        this.loadPortfolioButton = new JButton("Load");
        saveLoadPanel.add(this.loadPortfolioButton);

        // Should change to success or failed depending on what happened.
        this.saveLoadLabel = new JLabel("Once you've attempted to save or load a portfolio, " +
                "confirmation will display here.");
        saveLoadPanel.add(this.saveLoadLabel);

        this.mainPanel.add(saveLoadPanel);
    }

    private void buySellStockPanel() {
        JPanel buySellPanel = new JPanel();
        buySellPanel.setBorder(BorderFactory.createTitledBorder("Buy or Sell Stocks"));
        buySellPanel.setLayout(new BoxLayout(buySellPanel, BoxLayout.PAGE_AXIS));

        this.buySellPortfolioComboBox = new JComboBox<String>();
        for (String loadedPortfolio : this.loadedPortfolios) {
            this.buySellPortfolioComboBox.addItem(loadedPortfolio);
        }
        buySellPanel.add(this.buySellPortfolioComboBox);
        this.buySellPortfolioComboBox.setBorder(BorderFactory.createTitledBorder("Choose the portfolio to buy/sell for"));

        JTextArea buySellSymbolTextArea = new JTextArea(1, 20);
        buySellSymbolTextArea.setBorder(BorderFactory.createTitledBorder("Enter stock symbol to buy/sell"));
        JScrollPane jScrollPane = new JScrollPane(buySellSymbolTextArea);
        buySellPanel.add(jScrollPane);

        SpinnerNumberModel buySellQuantitySpinnerModel = new SpinnerNumberModel(
                0, 0, Integer.MAX_VALUE, 1
        );
        JSpinner buySellQuantitySpinner = new JSpinner(buySellQuantitySpinnerModel);
        buySellPanel.add(buySellQuantitySpinner);
        buySellQuantitySpinner.setBorder(BorderFactory.createTitledBorder("Enter the quantity of shares to buy/sell"));

        SpinnerNumberModel yearPickerModel = new SpinnerNumberModel(
                2024, 2000, 2024, 1
        );
        JSpinner yearPicker = new JSpinner(yearPickerModel);
        buySellPanel.add(yearPicker);
        yearPicker.setBorder(BorderFactory.createTitledBorder("Enter the *year* to buy at"));

        SpinnerNumberModel monthPickerModel = new SpinnerNumberModel(
                1, 1, 12, 1
        );
        JSpinner monthPicker = new JSpinner(monthPickerModel);
        buySellPanel.add(monthPicker);
        monthPicker.setBorder(BorderFactory.createTitledBorder("Enter the *month* to buy at"));

        SpinnerNumberModel dayPickerModel = new SpinnerNumberModel(
                1, 1, 31, 1
        );
        JSpinner dayPicker = new JSpinner(dayPickerModel);
        buySellPanel.add(dayPicker);
        dayPicker.setBorder(BorderFactory.createTitledBorder("Enter the *day* to buy at"));

        this.buyButton = new JButton("Buy");
        JScrollPane buyButtonPane = new JScrollPane(this.buyButton);
        buySellPanel.add(buyButtonPane);

        this.sellButton = new JButton("Sell");
        JScrollPane sellButtonPane = new JScrollPane(this.sellButton);
        buySellPanel.add(sellButtonPane);

        // Should change to success or failed depending on what happened.
        this.buySellLabel = new JLabel("Once you attempt to buy or sell a stock, " +
                "confirmation will display here.");
        buySellPanel.add(this.buySellLabel);

        this.mainPanel.add(buySellPanel);
    }

    private void queryPortfolioValueCompositionPanel() {
        JPanel queryPanel = new JPanel();
        queryPanel.setBorder(BorderFactory.createTitledBorder("Value and composition of portfolios"));
        queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.PAGE_AXIS));

        this.valueCompPortfolioComboBox = new JComboBox<String>();
        for (String loadedPortfolio : this.loadedPortfolios) {
            this.valueCompPortfolioComboBox.addItem(loadedPortfolio);
        }
        queryPanel.add(this.valueCompPortfolioComboBox);
        this.valueCompPortfolioComboBox.setBorder(BorderFactory.createTitledBorder("Choose the portfolio"));

        SpinnerNumberModel yearPickerModel = new SpinnerNumberModel(
                2024, 2000, 2024, 1
        );
        JSpinner yearPicker = new JSpinner(yearPickerModel);
        queryPanel.add(yearPicker);
        yearPicker.setBorder(BorderFactory.createTitledBorder("Enter the *year* to query"));

        SpinnerNumberModel monthPickerModel = new SpinnerNumberModel(
                1, 1, 12, 1
        );
        JSpinner monthPicker = new JSpinner(monthPickerModel);
        queryPanel.add(monthPicker);
        monthPicker.setBorder(BorderFactory.createTitledBorder("Enter the *month* to query"));

        SpinnerNumberModel dayPickerModel = new SpinnerNumberModel(
                1, 1, 31, 1
        );
        JSpinner dayPicker = new JSpinner(dayPickerModel);
        queryPanel.add(dayPicker);
        dayPicker.setBorder(BorderFactory.createTitledBorder("Enter the *day* to query"));

        // needs to update
        this.valueCompositionLabel = new JLabel("Once you select a portfolio, its value " +
                "and composition will be displayed here.");
        queryPanel.add(this.valueCompositionLabel);

        this.mainPanel.add(queryPanel);
    }

    public StockViewGUI() {
        super();

        // Default value. Needs to be updated to a new String[] of different size with different elements.
        // When this happens, each stock picker must be updated to the new values.
        this.loadedPortfolios = new String[0];

        this.setTitle("Stock Viewer");
        this.setSize(1000, 500);

        // Setup panel
        this.mainPanel = new JPanel();
        this.mainScrollPane = new JScrollPane(this.mainPanel);
        add(this.mainScrollPane);

        /////////////////// Create Portfolio
        createPortfolioPanel();

        /////////////////// Save / Load Portfolio
        saveLoadPortfolioPanel();

        /////////////////// Buy / sell
        buySellStockPanel();

        /////////////////// Query value/composition
        queryPortfolioValueCompositionPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    }


    @Override
    public void createPortfolioMessage(String message) {
        this.createPortfolioLabel.setText(message);
    }

    @Override
    public void saveLoadPortfolioMessage(String message) {
        this.saveLoadLabel.setText(message);
    }

    @Override
    public void buySellStockMessage(String message) {
        this.buySellLabel.setText(message);
    }

    @Override
    public void ValueCompositionMessage(String message) {
        this.valueCompositionLabel.setText(message);
    }

    @Override
    public void startListeners(ActionListener listener) {
        this.portfolioCreationButton.setActionCommand("create-portfolio");
        this.portfolioCreationButton.addActionListener(listener);
        this.savePortfolioButton.setActionCommand("save-portfolio");
        this.savePortfolioButton.addActionListener(listener);
        this.loadPortfolioButton.setActionCommand("load-portfolio");
        this.loadPortfolioButton.addActionListener(listener);

        this.buySellPortfolioComboBox.setActionCommand("portfolio-selection-buy-sell");
        this.buySellPortfolioComboBox.addActionListener(listener);
        this.valueCompPortfolioComboBox.setActionCommand("portfolio-selection-value-comp");
        this.valueCompPortfolioComboBox.addActionListener(listener);

        this.buyButton.setActionCommand("buy-stock");
        this.buyButton.addActionListener(listener);
        this.sellButton.setActionCommand("sell-stock");
        this.sellButton.addActionListener(listener);
    }

    @Override
    public String getComponentText(String componentName) throws IllegalArgumentException {
        switch (componentName) {
            case "portfolio-creation-text" :
                return this.portfolioCreationTextArea.getText();
            default :
                throw new IllegalArgumentException("the component requested from the GUI does not exist.");
        }
    }

    @Override
    public void addPortfolioGUI(String loadedPortfolio) {
        this.buySellPortfolioComboBox.addItem(loadedPortfolio);
        this.valueCompPortfolioComboBox.addItem(loadedPortfolio);
    }

    @Override
    public void show(String input) {
        // NOT USED, Irrelevant for this implementation
    }

    @Override
    public void welcomeMessage() {
        // NOT USED, Irrelevant for this implementation
    }

    @Override
    public void printHelp() {
        // NOT USED, Irrelevant for this implementation
    }

    @Override
    public void goodbyeMessage() {
        // NOT USED, Irrelevant for this implementation
    }

    @Override
    public void printOptions() {
        // NOT USED, Irrelevant for this implementation
    }
}
