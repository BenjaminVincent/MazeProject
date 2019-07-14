import java.awt.Color;
import java.util.Random;
import java.util.Stack;
public class MazeGenerator {

  private int px; // Starting
  private int py; // Starting
  private int cx; // Current
  private int cy; // Current
  private int fx; // Final
  private int fy; // Final
  String dir;
  private Cell[][] mazeGrid; // 2D array for maze generation
  Cell current; // Current cell
  Stack<Cell> stack;
  private boolean first; // Is it on the first cell in the generation process?
  private boolean finished; // Is generation finished?
  private Color mazeColor; // The colour of visited cells

  public MazeGenerator(int cols, int rows) {
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
    // Choose finish position
    findFinish(px, py, rows, cols);

    stack = new Stack<Cell>();
    current = mazeGrid[py][px];
    first = true;
    finished = false;
    cx = px;
    cy = py;
    mazeColor = Color.BLUE;
  }

  // Used to find a random (px, py) starting position
  public static int getRandomInt(int min, int max) {
    Random r = new Random();
    return r.nextInt(((max - 1) - min) + 1) + min;
  }
  // Update cx/cy to coordinates of cell in direction "dir" from current cell
  public void move(String dir) {
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

  public void findFinish(int px, int py, int rows, int cols) {
    // Idea: place in corner furthest from the start (GREEN cell)

    // initial final X,Y
    int fX = 0;
    int fY = 0;

    // Top Left
    int tL = px + py;

    // Top Right
    int tR = Math.abs(px - (cols - 1)) + py;

    // Bottom Left
    int bL = Math.abs(px - (rows - 1)) + py;

    // Bottom Right
    int bR = Math.abs(px - (cols - 1)) + Math.abs(py - (rows - 1));

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
    fx = fX;
    fy = fY;
  }

  public void moveCell() {
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
        move(dir);
        current = mazeGrid[cy][cx];
      } else {
        current.usedDirection(dir);
        current.visited();
        current = stack.pop();
      }
    } else {
      mazeColor = Color.WHITE;
      cx = px;
      cy = py;
      finished = true;
    }
  }

  public int getFinalX() {
    return fx;
  }

  public int getFinalY() {
    return fy;
  }

  public int getCurrentX() {
    return cx;
  }

  public int getCurrentY() {
    return cy;
  }

  public Cell[][] getMazeGrid() {
    return mazeGrid;
  }

  public boolean isFinished() {
    return finished;
  }

  public boolean isFirst() {
    return first;
  }

  public Color getMazeColor() {
    return mazeColor;
  }

}
