import java.awt.*;
import javax.swing.*;

public class hello {

  public static int WIDTH = 500;
  public static int HEIGHT = 400;

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Maze Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // prevents auto sizing
        frame.setVisible(true);

        frame.setSize(WIDTH, HEIGHT);
        bye printObject = new bye();
        printObject.printBye();
      }
    });
  }
}
