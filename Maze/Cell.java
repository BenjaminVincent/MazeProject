import java.util.ArrayList;
import java.util.Collections;

public class Cell {
  private boolean visited = false; // has the cell been visited?
  private int c;
  private int r;
  private ArrayList<Integer> directions = new ArrayList<Integer>();

  public Cell(int c, int r, int cols, int rows) {
    this.c = c;
    this.r = r;

    // calculate possible directions of travel and add to list of directions
    if (r != 0) {
      directions.add(0); // 0 is north
    }
    if (r != (rows - 1)) {
      directions.add(1); // 1 is south
    }
    if (c != 0) {
      directions.add(2); // 2 is east
    }
    if (c != (cols - 1)) {
      directions.add(3); // 3 is west
    }

    Collections.shuffle(directions); // randomly shuffle directions
  }

  public int c() {
    return c;
  }

  public int r() {
    return r;
  }

  public void visited() {
    visited = true;
  }

  public boolean isVisited() {
    return visited;
  }

  public Integer direction() {
    return directions.get(0);
  }

  public void removeDirection() {
    directions.remove(0);
  }
}
