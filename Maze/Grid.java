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
  int[][] grid2D; // 2D array for maze display
  Cell[][] mazeGrid; // 2D array for maze generation
  Cell current;
  int SquareSize = 20;
  int CellSize = 6;

  Timer t = new Timer(20, this);

  String[] direction = {
    "UP", // 0
    "RIGHT", // 1
    "DOWN", // 2
    "LEFT" // 3
  };



  public Grid(int cols, int rows){
    this.r = rows * CellSize + 1;
    this.c = cols * CellSize + 1;
    grid2D = new int[c][r];
    mazeGrid = new Cell[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        mazeGrid[i][j] = new Cell(j, i, cols, rows);
      }
    }
    // Set start position
    px = getRandomInt(0, cols);
    py = getRandomInt(0, rows);
    //grid2D[px][py] = 2;

    // Start from initial
    cx = px;
    cy = py;
  }

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
    colourSquare(px, py, Color.GREEN, g);
    removeBorder(px, py, "UP", g);
  }

  public void colourSquare(int x, int y, Color color, Graphics g) {
    g.setColor(color);
    for (int i = x * CellSize + 1; i < x * CellSize + CellSize; i++) {
      for (int j = y * CellSize + 1; j < y * CellSize + CellSize; j++) {
        g.fillRect(i * SquareSize + 1, j * SquareSize + 1, SquareSize - 1, SquareSize - 1);
      }
    }
  }

  public void removeBorder(int x, int y, String direction, Graphics g) {
    g.setColor(Color.WHITE);
    switch (direction) {
      case "UP":
        for (int i = x * CellSize + 1; i < x * CellSize + CellSize; i++) {
          g.fillRect(i * SquareSize + 1, y * CellSize * SquareSize + 1, SquareSize - 1, SquareSize - 1);
        }
        break;
      case "DOWN":
        for (int i = x * CellSize + 1; i < x * CellSize + CellSize; i++) {
          g.fillRect(i * SquareSize + 1, (y + 1) * CellSize * SquareSize + 1, SquareSize - 1, SquareSize - 1);
        }
        break;
      case "LEFT":
        for (int i = y * CellSize + 1; i < y * CellSize + CellSize; i++) {
          g.fillRect(x * CellSize * SquareSize + 1, i * SquareSize + 1, SquareSize - 1, SquareSize - 1);
        }
        break;
      case "RIGHT":
        for (int i = y * CellSize + 1; i < y * CellSize + CellSize; i++) {
          g.fillRect((x + 1) * CellSize * SquareSize + 1, i * SquareSize + 1, SquareSize - 1, SquareSize - 1);
        }
        break;
      }
  }

  // Used to find a random (px, py) starting position
  public static int getRandomInt(int min, int max) {
    Random r = new Random();
    return r.nextInt(((max - 1) - min) + 1) + min;
  }


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

    /* current = mazeGrid[cx][cy];
    current.visited();
    dir = current.direction */
    repaint();
  }
}
