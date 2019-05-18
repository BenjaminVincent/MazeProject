import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;


public class Grid extends JPanel implements ActionListener {

  int c;
  int r;
  int px; // Starting
  int py; // Starting
  int cx; // Current
  int cy; // Current
  int[][] grid2D;

  int UP = 0;
  int RIGHT = 1;
  int DOWN = 2;
  int LEFT = 3;

  int[] direction = {
    UP, // 0
    RIGHT, // 1
    DOWN, // 2
    RIGHT // 3
  };


  public Grid(int cols, int rows){
    this.r = rows;
    this.c = cols;
    grid2D = new int[c][r];
    px = getRandomInt(0, c);
    py = getRandomInt(0, r);
    grid2D[px][py] = 2;

  }


  Timer t = new Timer(10, this);
  int CellSize = 40;

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);
    g.setColor(Color.BLACK);
    t.start();

    //px = getRandomInt(0, c);
    //py = getRandomInt(0, r);
    // 0 is WHITE
    // 1 = BLACK
    // 2 = GREEN

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


  // Used to find a random (px, py) starting position
  public static int getRandomInt(int min, int max) {
    Random r = new Random();
    return r.nextInt(((max - 1) - min) + 1) + min;
  }


  // Update loop
  public void actionPerformed(ActionEvent e) {
    /*
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
    */

    repaint();
  }
}
