import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Grid extends JPanel implements ActionListener {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  int c; // Columns
  int r; // Rows
  int cx; // Current x
  int cy; // Current y
  int fx; // Finish x
  int fy; // Finish y

  int[][] grid2D; // 2D array for maze display
  Cell[][] mazeGrid; // 2D array for maze generation

  int SquareSize = 20;
  int CellSize = 3;
  Timer t = new Timer(200, this);

  Color mazeColor;
  MazeGenerator generator;

  Solver solve;

  public Grid(int cols, int rows) {
    this.r = rows * CellSize + 1;
    this.c = cols * CellSize + 1;
    grid2D = new int[r][c];
    generator = new MazeGenerator(cols, rows);
    fx = generator.getFinalX();
    fy = generator.getFinalY();
    mazeGrid = generator.getMazeGrid();
    solve = new Solver(mazeGrid, generator.getCurrentX(), generator.getCurrentY());
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);
    g.setColor(Color.BLACK);
    t.start();
    // 0 = WHITE
    // 1 = BLACK
    // 2 = GREEN

    // ***** Paint initial grid ******
    for (int i = 0; i < grid2D.length; i++) {
      for (int j = 0; j < grid2D[i].length; j++) {
        // Draw rectangle at every grid2D position
        g.drawRect(j * SquareSize, i * SquareSize, SquareSize, SquareSize);
        // Draw empty maze
        if (i % CellSize == 0) grid2D[i][j] = 1;
        else if (j % CellSize == 0) grid2D[i][j] = 1;

        if (grid2D[i][j] == 0) {
          g.setColor(Color.WHITE);
          g.fillRect(j * SquareSize + 1, i * SquareSize + 1, SquareSize-1, SquareSize-1);
          g.setColor(Color.BLACK);
        } else if (grid2D[i][j] == 1) {
          g.setColor(Color.BLACK);
          g.fillRect(j * SquareSize + 1, i * SquareSize + 1, SquareSize - 1, SquareSize - 1);
          g.setColor(Color.BLACK);
        }
      }
    }

    for (int i = 0; i < mazeGrid.length; i++) {
      for (int j = 0; j < mazeGrid[i].length; j++) {
        if (mazeGrid[i][j].isVisited()) {
          colourSquare(j, i, mazeColor, g);
        }
        // for each direction d that has been traveled to
        // remove the wall connecting the cells
        for (String d : mazeGrid[i][j].getUsedDirections()) {
          removeBorder(j, i, d, g);
        }
        if (i == cy && j == cx) {
          colourSquare(cx, cy, Color.GREEN, g);
        } if (generator.isFinished() && !generator.isFirst()) {
            colourSquare(fx, fy, Color.RED, g);
          }
        }
      }
    }

  // Set a particular square to the given color
  public void colourSquare(int x, int y, Color color, Graphics g) {
    g.setColor(color);
    for (int i = x * CellSize + 1; i < x * CellSize + CellSize; i++) {
      for (int j = y * CellSize + 1; j < y * CellSize + CellSize; j++) {
        g.fillRect(i * SquareSize + 1, j * SquareSize + 1, SquareSize - 1, SquareSize - 1);
      }
    }
  }

  public static void printArray(int[] a) {
    System.out.println();
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i]);
    }
  }

  // Removes connecting border between 2 cells
  public void removeBorder(int x, int y, String direction, Graphics g) {
    g.setColor(mazeColor);
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

  // Update loop
  public void actionPerformed(ActionEvent e) {
    if (!generator.isFinished()) {
    generator.moveCell();
    cx = generator.getCurrentX();
    cy = generator.getCurrentY();
    mazeColor = generator.getMazeColor();
  } else { // Maze has finished generating (start solving)
    // call solving algorithm
    // //System.out.println(cy);
    // solve.move(solve.DFS());
    // cx = solve.getCurrentX();
    // cy = solve.getCurrentY();
    // //System.out.println(cy);
    // System.out.println(solve.DFS());
  }
    repaint();
  }
}
