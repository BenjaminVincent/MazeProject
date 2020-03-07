import java.util.*;

public class Solver {
  private int cx;
  private int cy;
  private Cell[][] mazeGrid;
  private String dir;
  private Cell current;
  private Stack<Cell> stack;
  private ArrayList<String> directions = new ArrayList<String>();

  public Solver(Cell[][] mazeGrid, int cx, int cy) {
    this.cx = cx;
    this.cy = cy;
    this.mazeGrid = mazeGrid;
    stack = new Stack<Cell>();
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

  public boolean checkMove(String dir, ArrayList<String> a) {
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i).equals(dir)) {
        return true;
      } 
    }
    return false;
  }

  public String DFS() {
    dir = "UP";
    current = mazeGrid[cy][cx];
    directions = current.getUsedDirections();
    stack.push(current);
    if (!checkMove(dir, directions)) {
      dir = "RIGHT";
      checkMove(dir, directions);
    } else if (!checkMove(dir, directions)) {
        dir = "DOWN";
        checkMove(dir, directions);
    } else if (!checkMove(dir, directions)) {
        dir = "LEFT";
        checkMove(dir, directions);
    } else {
      stack.pop();
      dir = "EMPTY";
    }

    return dir;
  }

  public int getCurrentX() {
    return cx;
  }
  public int getCurrentY(){
    return cy;
  }
}
