import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Grid extends JPanel implements ActionListener {
  // Size of Grid
  int c;
  int r;

  public Grid(int cols, int rows) {
    this.c = cols;
    this.r = rows;
  }

  int[][] grid2D = new int[c][r];
  Timer t = new Timer(10, this); // this refers to ActionListener
  int CellSize = 40;
  int px = 0;
  int py = 0;


  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    setBackground(Color.WHITE);
    g.setColor(Color.BLACK);
    t.start();
    // 0 is WHITE
    // 1 = BLACK
    // 2 = GREEN
    //grid2D[1][1] = 2;

    // Loop through the grid
    for (int i = 0; i < grid2D.length; i++) {
      for (int j = 0; j < grid2D[i].length; j++) {
        g.drawRect(i * CellSize , j * CellSize, CellSize, CellSize);
        if (grid2D[i][j] == 1) {
          g.fillRect(i * CellSize, j * CellSize, CellSize, CellSize);
        } else if (grid2D[i][j] == 2) { // This is a temporary visual demo
          g.setColor(Color.GREEN);
          g.fillRect(i* CellSize + 1, j * CellSize + 1, CellSize-1, CellSize-1);
          g.setColor(Color.BLACK);
        }
      }
    }

  }

  // Update loop
  public void actionPerformed(ActionEvent e) {
    // Just an interesting patteren for fun
    if (px % 2 == 0) {
      grid2D[px][py] = 2;
    } else if (py % 2 == 0) {
    grid2D[px][py] = 2;
  } else {
    grid2D[px][py] = 1;
  }
      if (px < grid2D.length - 1) {
        px++;
        // check if we are at the end of a row
        // if we are - begin filling next row
      } else if (px == grid2D.length-1 && py < grid2D[0].length-1) {
        px = 0; // reset px
        py++;
      }

    repaint();
  }
}
