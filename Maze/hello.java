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

        JButton b = new JButton("test");
        b.setBounds(130, 100, 100, 40); // x, y, w, h
        frame.add(b);

        frame.setSize(WIDTH, HEIGHT);

      }
    });
  }
}
