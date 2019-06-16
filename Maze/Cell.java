import java.util.ArrayList;
import java.util.Collections;

public class Cell {
  private boolean visited = false; // has the cell been visited?
  private int x; // x position of cell on maze grid
  private int y; // y position of cell on maze grid
  private ArrayList<String> directions = new ArrayList<String>();

  public Cell(int c, int r, int cols, int rows) {
    this.x = c;
    this.y = r;

    // calculate possible directions of travel and add to list of directions
    if (r != 0) {
      directions.add("UP");
    }
    if (c != (cols - 1)) {
      directions.add("RIGHT");
    }
    if (r != (rows - 1)) {
      directions.add("DOWN");
    }
    if (c != 0) {
      directions.add("LEFT");
    }
    Collections.shuffle(directions); // randomly shuffle directions
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setVisited() {
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
