import javax.swing.*;
import java.awt.*;

public class Maze {

public static final int WIDTH = 960;
public static final int HEIGHT = 960;

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
