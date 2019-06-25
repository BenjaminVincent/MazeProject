import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;
import java.lang.*;

public class Grid extends JPanel implements ActionListener {

  int c; // Columns
  int r; // Rows

  int px; // Starting
  int py; // Starting

  int cx; // Current
  int cy; // Current
  String dir;
  int[][] grid2D; // 2D array for maze display
  Cell[][] mazeGrid; // 2D array for maze generation
  Cell current;
  int SquareSize = 20;
  int CellSize = 3;
  Timer t = new Timer(20, this);
  Stack<Cell> stack;
  boolean first;
  Color mazeColor;


  public Grid(int cols, int rows){
    this.r = rows * CellSize + 1;
    this.c = cols * CellSize + 1;
    grid2D = new int[r][c];

    // Create 2D Cell Array and fill each position with a Cell Object
    mazeGrid = new Cell[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        mazeGrid[i][j] = new Cell(j, i, cols, rows);
      }
    }
    // Set start position
    px = getRandomInt(0, cols);
    py = getRandomInt(0, rows);

    stack = new Stack<Cell>();
    current = mazeGrid[py][px];
    first = true;
    System.out.println(px);
    System.out.println(py);
    cx = px;
    cy = py;
    mazeColor = Color.BLUE;
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
        } else {
          if (stack.isEmpty()) {
          findFinish(px, py, mazeGrid.length, mazeGrid[0].length, g);
          }
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

  // Used to find a random (px, py) starting position
  public static int getRandomInt(int min, int max) {
    Random r = new Random();
    return r.nextInt(((max - 1) - min) + 1) + min;
  }

  public void moveCell(String dir) {
    switch (dir) {
      case "UP":
        cy--;
        break;
      case "RIGHT":
        cx++;
        break;
      case "DOWN":
        cy++;
        break;
      case "LEFT":
        cx--;
        break;
    }
  }

  public boolean cellVisited(String dir, int x, int y) {
    switch (dir) {
      case "UP":
        return mazeGrid[y - 1][x].isVisited();
      case "RIGHT":
        return mazeGrid[y][x + 1].isVisited();
      case "DOWN":
        return mazeGrid[y + 1][x].isVisited();
      case "LEFT":
        return mazeGrid[y][x - 1].isVisited();
    }
    return false;
  }

  public void findFinish(int px, int py, int rows, int cols, Graphics g) {
    // Idea: place in corner furthest from the start (GREEN cell)

    int fX = 0;
    int fY = 0;
    // Top Left
    int x1 = Math.abs(px - 0);
    int y1 = Math.abs(py - 0);

    int tL = x1 + y1;

    // Top Right
    int x2 = Math.abs(px - (cols - 1));
    int y2 = Math.abs(py - 0);

    int tR = x2 + y2;

    // Bottom Left
    int x3 = Math.abs(px - (rows - 1));
    int y3 = Math.abs(py - 0);

    int bL = x3 + y3;

    // Bottom Right
    int x4 = Math.abs(px - (cols - 1));
    int y4 = Math.abs(py - (rows - 1));

    int bR = x4 + y4;

    //System.out.println(tL);
    //System.out.println(tR);
    //System.out.println(bL);
    //System.out.println(bR);

    // Calculate max
    int max = tL;

    if (tR > max) max = tR;
    if (bL > max) max = bL;
    if (bR > max) max = bR;

    if (max == tL) {
      fX = 0;
      fY = 0;
    } else if (max == tR) {
      fX = cols - 1;
      fY = 0;
    } else if (max == bL) {
      fX = 0;
      fY = rows - 1;
    } else if (max == bR) {
      fX = cols - 1;
      fY = rows - 1;
    }

    //System.out.println(max);

    colourSquare(fX, fY, Color.RED, g);

  }


  // Update loop
  public void actionPerformed(ActionEvent e) {
    if (!stack.empty() || first) {
      first = false;
      dir = current.getDirection();
      cx = current.getX();
      cy = current.getY();

      if (!dir.equals("EMPTY")) {
        while (cellVisited(dir, cx, cy)) {
          if (!dir.equals("EMPTY")) {
            dir = current.getDirection();
          } else {
            dir = "EMPTY";
          }
        }
      }
      if (!dir.equals("EMPTY")) {
        current.usedDirection(dir);
        current.visited();
        stack.push(current);
        moveCell(dir);
        current = mazeGrid[cy][cx];
      } else {
        current.usedDirection(dir);
        current.visited();
        current = stack.pop();
      }
    } else {
      mazeColor = Color.WHITE;
    }
    repaint();
  }
}
