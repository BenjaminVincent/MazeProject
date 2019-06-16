import java.util.ArrayList;
import java.util.Collections;

public class Cell {
  private boolean visited = false; // has the cell been visited?
  private int x;
  private int y;
  private ArrayList<String> directions = new ArrayList<String>();

  public Cell(int c, int r, int cols, int rows) {
    this.x = x;
    this.y = y;

    // calculate possible directions of travel and add to list of directions
    if (y != 0) {
      directions.add("UP"); // 0 is north
    }
    if (x != (cols - 1)) {
      directions.add("RIGHT"); // 1 is south
    }
    if (y != (rows - 1)) {
      directions.add("DOWN"); // 2 is east
    }
    if (x != 0) {
      directions.add("LEFT"); // 3 is west
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
    return directions.get(0);
  }

  public void removeDirection() {
    directions.remove(0);
  }
}
