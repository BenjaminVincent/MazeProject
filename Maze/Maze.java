import javax.swing.*;
import java.awt.*;

public class Maze {

  public static void main(String[] args) {
    drawWindow(500, 500);
  }

  public static void drawWindow(int w, int h) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Maze Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // prevents auto sizing
        frame.setVisible(true);
        frame.setSize(w, h);
      }
    });
  }
}
