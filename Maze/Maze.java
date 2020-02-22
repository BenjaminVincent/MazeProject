import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Maze {

  public static final int WIDTH = 960;
  public static final int HEIGHT = 960;

  public static void main(String[] args) {
    drawWindow(WIDTH, HEIGHT);
  }

  public static void drawWindow(int w, int h) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        //initialize JFrame
        JFrame frame = new JFrame("Maze Generator");
        frame.setSize(w, h);
        Input input = new Input(WIDTH, HEIGHT);
        frame.add(input);

        input.b.addActionListener(new ActionListener() {
        //runs when the button is pressed
        public void actionPerformed(ActionEvent arg0) {

            Grid grid = new Grid(input.c, input.r);
            frame.remove(input);
            frame.add(grid);
            frame.invalidate();
            frame.validate();
            }
        });

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
