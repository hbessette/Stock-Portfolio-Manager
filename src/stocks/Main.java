package stocks;

import stocks.controller.StockController;
import stocks.controller.StockControllerImpl;
import stocks.model.StockModel;
import stocks.model.StockModelImpl;
import stocks.view.StockView;
import stocks.view.StockViewGUI;
import stocks.view.StockViewImpl;

import javax.swing.*;
import java.io.InputStreamReader;

/**
 * The class from which the program is run.
 */
public class Main {
  /**
   * Starts the program.
   *
   * @param args ignored
   */
  public static void main(String[] args) {
//    StockView stockView = new StockViewImpl(System.out);
//    StockModel stockModel = new StockModelImpl();
//    StockController stockController = new StockControllerImpl(new InputStreamReader(System.in),
//            stockView
//            , stockModel);
//    stockController.start();



    // This is just for GUI testing, we can move it somewhere else later.
    StockViewGUI.setDefaultLookAndFeelDecorated(false);
    StockViewGUI frame = new StockViewGUI();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

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
}
