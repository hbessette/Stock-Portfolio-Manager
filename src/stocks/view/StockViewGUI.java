package stocks.view;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class StockViewGUI extends JFrame implements IStockViewGUI {

    private JPanel mainPanel;
    private JScrollPane mainScrollPane;

    private String[] loadedPortfolios;

    private Map<String, JLabel> labels;
    private Map<String, JButton> buttons;
    private Map<String, JComboBox<String>> comboBoxes;
    private Map<String, JTextArea> textAreas;
    private Map<String, JSpinner> spinners;

    public StockViewGUI() {
        super();

        this.comboBoxes = new HashMap<>();
        this.spinners = new HashMap<>();
        this.textAreas = new HashMap<>();
        this.buttons = new HashMap<>();
        this.labels = new HashMap<>();

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

    private void createPortfolioPanel() {
        JPanel portfolioCreationPanel = new JPanel();
        portfolioCreationPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
        portfolioCreationPanel.setLayout(new BoxLayout(portfolioCreationPanel, BoxLayout.PAGE_AXIS));

        JTextArea portfolioCreationTextArea = new JTextArea(1, 20);
        portfolioCreationTextArea.setBorder(BorderFactory.createTitledBorder("Enter Portfolio name"));
        this.textAreas.put("portfolio-creation-text", portfolioCreationTextArea);
        JScrollPane jScrollPane = new JScrollPane(portfolioCreationTextArea);
        portfolioCreationPanel.add(jScrollPane);

        JButton portfolioCreationButton = new JButton("Create");
        this.buttons.put("create-portfolio", portfolioCreationButton);
        portfolioCreationPanel.add(portfolioCreationButton);

        // This should change to "successfully created [name] or "portfolio name may not contain newlines or spaces"
        JLabel createPortfolioLabel = new JLabel("Once you've attempted to create a portfolio, " +
                "confirmation will display here.");
        this.labels.put("create-portfolio-label", createPortfolioLabel);
        portfolioCreationPanel.add(createPortfolioLabel);

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
        this.textAreas.put("save-load-portfolio-text", saveLoadTextArea);
        JScrollPane jScrollPane = new JScrollPane(saveLoadTextArea);
        saveLoadPanel.add(jScrollPane);

        JButton savePortfolioButton = new JButton("Save");
        this.buttons.put("save-portfolio", savePortfolioButton);
        saveLoadPanel.add(savePortfolioButton);

        JButton loadPortfolioButton = new JButton("Load");
        this.buttons.put("load-portfolio", loadPortfolioButton);
        saveLoadPanel.add(loadPortfolioButton);

        // Should change to success or failed depending on what happened.
        JLabel saveLoadLabel = new JLabel("Once you've attempted to save or load a portfolio, " +
                "confirmation will display here.");
        this.labels.put("save-load-label", saveLoadLabel);
        saveLoadPanel.add(saveLoadLabel);

        this.mainPanel.add(saveLoadPanel);
    }

    private void buySellStockPanel() {
        JPanel buySellPanel = new JPanel();
        buySellPanel.setBorder(BorderFactory.createTitledBorder("Buy or Sell Stocks"));
        buySellPanel.setLayout(new BoxLayout(buySellPanel, BoxLayout.PAGE_AXIS));

        JComboBox<String> buySellPortfolioComboBox = new JComboBox<>();
        this.comboBoxes.put("buy-sell-portfolio-name", buySellPortfolioComboBox);
        for (String loadedPortfolio : this.loadedPortfolios) {
            buySellPortfolioComboBox.addItem(loadedPortfolio);
        }
        buySellPanel.add(buySellPortfolioComboBox);
        buySellPortfolioComboBox.setBorder(BorderFactory.createTitledBorder("Choose the portfolio to buy/sell for"));

        JTextArea buySellSymbolTextArea = new JTextArea(1, 20);
        buySellSymbolTextArea.setBorder(BorderFactory.createTitledBorder("Enter stock symbol to buy/sell"));
        this.textAreas.put("buy-sell-symbol-name", buySellSymbolTextArea);
        JScrollPane jScrollPane = new JScrollPane(buySellSymbolTextArea);
        buySellPanel.add(jScrollPane);

        SpinnerNumberModel buySellQuantitySpinnerModel = new SpinnerNumberModel(
                0, 0, Integer.MAX_VALUE, 1
        );
        JSpinner buySellQuantitySpinner = new JSpinner(buySellQuantitySpinnerModel);
        this.spinners.put("buy-sell-quantity", buySellQuantitySpinner);
        buySellPanel.add(buySellQuantitySpinner);
        buySellQuantitySpinner.setBorder(BorderFactory.createTitledBorder("Enter the quantity of shares to buy/sell"));

        SpinnerNumberModel yearPickerModel = new SpinnerNumberModel(
                2024, 2000, 2024, 1
        );
        JSpinner buySellYearPicker = new JSpinner(yearPickerModel);
        this.spinners.put("buy-sell-year", buySellYearPicker);
        buySellPanel.add(buySellYearPicker);
        buySellYearPicker.setBorder(BorderFactory.createTitledBorder("Enter the *year* to buy at"));

        SpinnerNumberModel monthPickerModel = new SpinnerNumberModel(
                1, 1, 12, 1
        );
        JSpinner buySellMonthPicker = new JSpinner(monthPickerModel);
        this.spinners.put("buy-sell-month", buySellMonthPicker);
        buySellPanel.add(buySellMonthPicker);
        buySellMonthPicker.setBorder(BorderFactory.createTitledBorder("Enter the *month* to buy at"));

        SpinnerNumberModel dayPickerModel = new SpinnerNumberModel(
                1, 1, 31, 1
        );
        JSpinner buySellDayPicker = new JSpinner(dayPickerModel);
        this.spinners.put("buy-sell-day", buySellDayPicker);
        buySellPanel.add(buySellDayPicker);
        buySellDayPicker.setBorder(BorderFactory.createTitledBorder("Enter the *day* to buy at"));

        JButton buyButton = new JButton("Buy");
        this.buttons.put("buy-stock", buyButton);
        JScrollPane buyButtonPane = new JScrollPane(buyButton);
        buySellPanel.add(buyButtonPane);

        JButton sellButton = new JButton("Sell");
        this.buttons.put("sell-stock", sellButton);
        JScrollPane sellButtonPane = new JScrollPane(sellButton);
        buySellPanel.add(sellButtonPane);

        // Should change to success or failed depending on what happened.
        JLabel buySellLabel = new JLabel("Once you attempt to buy or sell a stock, " +
                "confirmation will display here.");
        this.labels.put("buy-sell-label", buySellLabel);
        buySellPanel.add(buySellLabel);

        this.mainPanel.add(buySellPanel);
    }

    private void queryPortfolioValueCompositionPanel() {
        JPanel queryPanel = new JPanel();
        queryPanel.setBorder(BorderFactory.createTitledBorder("Value and composition of portfolios"));
        queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.PAGE_AXIS));

        JComboBox<String> valueCompPortfolioComboBox = new JComboBox<>();
        this.comboBoxes.put("value-comp-portfolio", valueCompPortfolioComboBox);
        for (String loadedPortfolio : this.loadedPortfolios) {
            valueCompPortfolioComboBox.addItem(loadedPortfolio);
        }
        queryPanel.add(valueCompPortfolioComboBox);
        valueCompPortfolioComboBox.setBorder(BorderFactory.createTitledBorder("Choose the portfolio"));

        SpinnerNumberModel yearPickerModel = new SpinnerNumberModel(
                2024, 2000, 2024, 1
        );
        JSpinner valueCompYearPicker = new JSpinner(yearPickerModel);
        this.spinners.put("value-comp-year", valueCompYearPicker);
        queryPanel.add(valueCompYearPicker);
        valueCompYearPicker.setBorder(BorderFactory.createTitledBorder("Enter the *year* to query"));

        SpinnerNumberModel monthPickerModel = new SpinnerNumberModel(
                1, 1, 12, 1
        );
        JSpinner valueCompMonthPicker = new JSpinner(monthPickerModel);
        this.spinners.put("value-comp-month", valueCompMonthPicker);
        queryPanel.add(valueCompMonthPicker);
        valueCompMonthPicker.setBorder(BorderFactory.createTitledBorder("Enter the *month* to query"));

        SpinnerNumberModel dayPickerModel = new SpinnerNumberModel(
                1, 1, 31, 1
        );
        JSpinner valueCompDayPicker = new JSpinner(dayPickerModel);
        this.spinners.put("value-comp-day", valueCompDayPicker);
        queryPanel.add(valueCompDayPicker);
        valueCompDayPicker.setBorder(BorderFactory.createTitledBorder("Enter the *day* to query"));

        JButton valueCompUpdateButton = new JButton("Query");
        this.buttons.put("update-value-comp", valueCompUpdateButton);
        JScrollPane updateButtonPane = new JScrollPane(valueCompUpdateButton);
        queryPanel.add(updateButtonPane);

        JLabel valueCompositionLabel = new JLabel("Once you query a portfolio, its value " +
                "and composition will be displayed here.");
        this.labels.put("value-comp-label", valueCompositionLabel);
        queryPanel.add(valueCompositionLabel);

        this.mainPanel.add(queryPanel);
    }

    @Override
    public void createPortfolioMessage(String message) {
        this.labels.get("create-portfolio-label").setText(message);
    }

    @Override
    public void saveLoadPortfolioMessage(String message) {
        this.labels.get("save-load-label").setText(message);
    }

    @Override
    public void buySellStockMessage(String message) {
        this.labels.get("buy-sell-label").setText(message);
    }

    @Override
    public void valueCompositionMessage(String message) {
        String msg = message.replaceAll(System.lineSeparator(), "<br/>");
        this.labels.get("value-comp-label").setText("<html>" + msg + "</html>");
    }

    @Override
    public void startListeners(ActionListener listener) {
        for (String b : this.buttons.keySet()) {
            this.buttons.get(b).setActionCommand(b);
            this.buttons.get(b).addActionListener(listener);
        }

        for (String b : this.comboBoxes.keySet()) {
            this.comboBoxes.get(b).setActionCommand(b);
            this.comboBoxes.get(b).addActionListener(listener);
        }
    }

    @Override
    public String getComponentText(String componentName) throws IllegalArgumentException {
        if (this.textAreas.containsKey(componentName)) {
            return this.textAreas.get(componentName).getText();
        } else if (this.comboBoxes.containsKey(componentName)) {
            return String.valueOf(this.comboBoxes.get(componentName).getSelectedItem());
        } else if (this.spinners.containsKey(componentName)) {
            return String.valueOf(this.spinners.get(componentName).getValue());
        } else {
            throw new IllegalArgumentException("the component requested from the GUI does not exist.");
        }
    }

    @Override
    public void addPortfolioGUI(String loadedPortfolio) {
        for (JComboBox<String> j : this.comboBoxes.values()) {
            j.addItem(loadedPortfolio);
        }
    }

    @Override
    public void display() {
        setDefaultLookAndFeelDecorated(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());

            //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            //    {
            //       if ("Nimbus".equals(info.getName())) {
            //          UIManager.setLookAndFeel(info.getClassName());
            //         break;
            //    }
            // }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        } catch (Exception e) {
        }
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
