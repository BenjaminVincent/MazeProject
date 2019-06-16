import java.util.ArrayList;
import java.util.Collections;

public class Cell {
  private boolean visited = false; // has the cell been visited?
  private int x; // x position of cell on maze grid
  private int y; // y position of cell on maze grid
  private ArrayList<String> directions = new ArrayList<String>();
  private ArrayList<String> usedDirections = new ArrayList<String>();

  public Cell(int x, int y, int cols, int rows) {
    this.x = x;
    this.y = y;

    // calculate possible directions of travel and add to list of directions
    if (y != 0) {
      directions.add("UP");
    }
    if (x != (cols - 1)) {
      directions.add("RIGHT");
    }
    if (y != (rows - 1)) {
      directions.add("DOWN");
    }
    if (x != 0) {
      directions.add("LEFT");
    }

    Collections.shuffle(directions); // randomly shuffle directions
  }

  public int c() {
    return x;
  }

  public int r() {
    return y;
  }

  public void visited() {
    visited = true;
  }

  public boolean isVisited() {
    return visited;
  }

  public String getDirection() {
    String direction = directions.get(0);
    directions.remove(0);
    return direction;
  }

  public void usedDirection(String dir) {
    usedDirections.add(dir);
  }

  public ArrayList<String> getUsedDirections() {
    return usedDirections;
  }
}
