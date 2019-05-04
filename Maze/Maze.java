import javax.swing.*;
import java.awt.*;

public class Maze {

public static final int WIDTH = 1000;
public static final int HEIGHT = 1000;

  public static void main(String[] args) {
    drawWindow(WIDTH, HEIGHT);
  }

  public static void drawWindow(int w, int h) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Maze Generator");

        frame.setSize(w, h);
        Grid test = new Grid();
        frame.add(test);


        //frame.setLayout(null); // prevents auto sizing
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      }
    });
  }
}
