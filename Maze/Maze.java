import javax.swing.*;
import java.awt.*;

public class Maze {

public static final int WIDTH = 1440;
public static final int HEIGHT = 1440;

  public static void main(String[] args) {
    drawWindow(WIDTH, HEIGHT);
  }

  public static void drawWindow(int w, int h) {
    int rows = 21;
    int cols = 31;
    Grid test = new Grid(rows, cols);

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {

        //initialize JFrame
        JFrame frame = new JFrame("Maze Generator");
        frame.setSize(w, h);
        frame.add(test);
        // set icon
        ImageIcon icon = new ImageIcon("mazeicon.png");
        frame.setIconImage(icon.getImage());

        //frame.setLayout(null); // prevents auto sizing
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
    });
  }
}
