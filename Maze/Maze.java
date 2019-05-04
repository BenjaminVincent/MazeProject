public class Maze {
  public static void main(String[] args) {
    drawWindow();
  }

  public static void drawWindow() {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Maze Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // prevents auto sizing
        frame.setVisible(true);

        frame.setSize(WIDTH, HEIGHT);
      }
    });
  }
}
