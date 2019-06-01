import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Stack;

public class Grid extends JPanel implements ActionListener {

  int c; // Columns
  int r; // Rows

  int px; // Starting
  int py; // Starting

  int cx; // Current
  int cy; // Current
  int dir;
  int[][] grid2D;

  String[] direction = {
    "UP", // 0
    "RIGHT", // 1
    "DOWN", // 2
    "LEFT" // 3
  };



  public Grid(int cols, int rows){
    this.r = rows;
    this.c = cols;
    grid2D = new int[c][r];
    // Set start position
    px = getRandomInt(1, c-1);
    py = getRandomInt(1, r-1);
    grid2D[px][py] = 2;

    // Start from initial
    cx = px;
    cy = py;
  }


  Timer t = new Timer(20, this);
  int SquareSize = 20;
  int CellSize = 3;

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);
    g.setColor(Color.BLACK);
    t.start();

    // 0 is WHITE
    // 1 = BLACK
    // 2 = GREEN

    // Loop through the grid
    for (int i = 0; i < grid2D.length; i++) {
      for (int j = 0; j < grid2D[i].length; j++) {
        // Draw rectangle at every grid2D position
        g.drawRect(i * SquareSize, j * SquareSize, SquareSize, SquareSize);

        // Draw empty maze
        if (i % CellSize == 0) grid2D[i][j] = 1;
        else if (j % CellSize == 0) grid2D[i][j] = 1;

        if (grid2D[i][j] == 0) {
          g.setColor(Color.WHITE);
          g.fillRect(i * SquareSize + 1, j * SquareSize + 1, SquareSize-1, SquareSize-1);
          g.setColor(Color.BLACK);
        } else if (grid2D[i][j] == 1) {
          g.setColor(Color.BLACK);
          g.fillRect(i * SquareSize + 1, j * SquareSize + 1, SquareSize - 1, SquareSize - 1);
          g.setColor(Color.BLACK);

        } else if (grid2D[i][j] == 2) {
          g.setColor(Color.GREEN);
          g.fillRect(i * SquareSize + 1, j * SquareSize + 1, SquareSize - 1, SquareSize - 1);
          g.setColor(Color.BLACK);
        }
      }
    }


  }

  public static int[][] createCellBlock(int[][] grid2D, int i, int j, int CellSize) {
    for (int k = i; k < i + CellSize; k++) {
      for (int l = j; l < j + CellSize; l++) {

      }
    }
    return grid2D;
  }

  // Used to find a random (px, py) starting position
  public static int getRandomInt(int min, int max) {
    Random r = new Random();
    return r.nextInt(((max - 1) - min) + 1) + min;
  }

/*
  public static int getStart(int min, int max) [
    Random r = new Random();

  ]
*/

  // Checks bounds of grid2D for legal movement
  public static Boolean isInside(int cx, int cy, int[][] grid2D) {
    if (cx < grid2D.length-1 && cy < grid2D[0].length-1 && cx > 0 && cy > 0) {
      System.out.println("TRUE");
      return true;
    } else {
      System.out.println("FALSE");
      return false;
    }
  }

  // Update loop
  public void actionPerformed(ActionEvent e) {

    dir = getRandomInt(0, 4);

  //  System.out.println(direction[dir]);
/*
    switch (direction[dir]) {
      case "UP":
        if (isInside(cx, cy, grid2D)) cy--;
        grid2D[cx][cy] = 0;
        break;
      case "RIGHT":
        if (isInside(cx, cy, grid2D)) cx++;
        grid2D[cx][cy] = 0;
        break;
      case "DOWN":
        if (isInside(cx, cy, grid2D)) cy++;
        grid2D[cx][cy] = 0;
        break;
      case "LEFT":
        if (isInside(cx, cy, grid2D)) cx--;
        grid2D[cx][cy] = 0;
        break;
    }
    */

    repaint();
  }
}
