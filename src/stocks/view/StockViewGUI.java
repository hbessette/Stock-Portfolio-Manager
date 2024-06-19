package stocks.view;
import javax.swing.*;
import java.awt.*;
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

    private void createPortfolioPanel() {
        JPanel portfolioCreationPanel = new JPanel();
        portfolioCreationPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
        portfolioCreationPanel.setLayout(new BoxLayout(portfolioCreationPanel, BoxLayout.PAGE_AXIS));

        JTextArea portfolioCreationTextArea = new JTextArea(1, 20);
        portfolioCreationTextArea.setBorder(BorderFactory.createTitledBorder("Enter Portfolio name"));
        JScrollPane jScrollPane = new JScrollPane(portfolioCreationTextArea);
        portfolioCreationPanel.add(jScrollPane);

        JButton portfolioCreationButton = new JButton("Create");
//        portfolioCreationButton.setActionCommand(""); // should go to the controller probably
//        portfolioCreationButton.addActionListener(this);
        portfolioCreationPanel.add(portfolioCreationButton);

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

        JButton savePortfolioButton = new JButton("Save");
//        saveLoadButton.setActionCommand(""); // should go to the controller probably
//        saveLoadButton.addActionListener(this);
        saveLoadPanel.add(savePortfolioButton);

        JButton loadPortfolioButton = new JButton("Load");
//        saveLoadButton.setActionCommand(""); // should go to the controller probably
//        saveLoadButton.addActionListener(this);
        saveLoadPanel.add(loadPortfolioButton);

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

        JComboBox<String> buySellPortfolioComboBox = new JComboBox<String>();
//        buySellPortfolioComboBox.setActionCommand("Size options"); // should go to the controller probably
//        buySellPortfolioComboBox.addActionListener(this);
        for (String loadedPortfolio : this.loadedPortfolios) {
            buySellPortfolioComboBox.addItem(loadedPortfolio);
        }
        buySellPanel.add(buySellPortfolioComboBox);
        buySellPortfolioComboBox.setBorder(BorderFactory.createTitledBorder("Choose the portfolio to buy/sell for"));

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

        JButton buyButton = new JButton("Buy");
//        saveLoadButton.setActionCommand(""); // should go to the controller probably
//        saveLoadButton.addActionListener(this);
        JScrollPane buyButtonPane = new JScrollPane(buyButton);
        buySellPanel.add(buyButtonPane);

        JButton sellButton = new JButton("Sell");
//        saveLoadButton.setActionCommand(""); // should go to the controller probably
//        saveLoadButton.addActionListener(this);
        JScrollPane sellButtonPane = new JScrollPane(sellButton);
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

        JComboBox<String> portfolioComboBox = new JComboBox<String>();
//        buySellPortfolioComboBox.setActionCommand("Size options"); // should go to the controller probably
//        buySellPortfolioComboBox.addActionListener(this);
        for (String loadedPortfolio : this.loadedPortfolios) {
            portfolioComboBox.addItem(loadedPortfolio);
        }
        queryPanel.add(portfolioComboBox);
        portfolioComboBox.setBorder(BorderFactory.createTitledBorder("Choose the portfolio"));

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
