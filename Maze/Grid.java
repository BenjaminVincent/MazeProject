import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;

public class Grid extends JPanel implements ActionListener {

  int c; // Columns
  int r; // Rows

  int px; // Starting
  int py; // Starting

  int cx; // Current
  int cy; // Current
  int dir;
  int[][] grid2D;

  int[] visited;
  int count = 0;

  String[] direction = {
    "UP", // 0
    "RIGHT", // 1
    "DOWN", // 2
    "LEFT" // 3
  };

  Timer t = new Timer(20, this);
  int SquareSize = 20;
  int CellSize = 3;



  public Grid(int cols, int rows){
    this.r = rows * CellSize + 1;
    this.c = cols * CellSize + 1;
    grid2D = new int[c][r];
    visited = new int[rows * cols];

    // Set start position
    px = getRandomInt(0, cols);
    py = getRandomInt(0, rows);

    Stack<Integer> stack = new Stack<Integer>();
    stack.push(px);
    count = markVisited(px, count, visited);
    System.out.println("Count: " + count);
    stack.push(py);    //grid2D[px][py] = 2;
    count = markVisited(py, count, visited);
    System.out.println("Count: " + count);
    //stack.push(new Integer[][] {{px}, {py}});
    printStack(stack);

    printArray(visited);
    // Start from initial
    cx = px;
    cy = py;
  }




  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);
    g.setColor(Color.BLACK);
    t.start();
    // 0 = WHITE
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
        } else if (grid2D[i][j] == 3) {
          g.setColor(Color.BLUE);
          g.fillRect(i * SquareSize + 1, j * SquareSize + 1, SquareSize - 1, SquareSize - 1);
          g.setColor(Color.BLACK);
        }
      }
    }
    greenCell(px, py, CellSize, SquareSize, g);
  }

  public void greenCell(int x, int y, int CellSize, int SquareSize, Graphics g) {
    g.setColor(Color.GREEN);
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

  // Used to find a random (px, py) starting position
  public static int getRandomInt(int min, int max) {
    Random r = new Random();
    return r.nextInt(((max - 1) - min) + 1) + min;
  }

  public static void printStack(Stack<Integer> s) {
    if (s.isEmpty()) {
      System.out.println("Empty");
    } else {
      System.out.printf("%s", s);
    }
  }

  public static int markVisited(int a, int count, int[] visited) {
    visited[count] = a;
    System.out.println("Visited: " + visited[count]);
    count++;
    return count;
  }

  // Checks bounds of grid2D for legal movement
  public static Boolean isInside(int cx, int cy, int[][] grid2D) {
    if (cx < grid2D.length-1 && cy < grid2D[0].length-1 && cx > 0 && cy > 0) {
      System.out.println("TRUE");
      return true;
    } else {
      //System.out.println("FALSE");
      return false;
    }
  }

  // Update loop
  public void actionPerformed(ActionEvent e) {

    dir = getRandomInt(0, 4);

  //  System.out.println(direction[dir]);
    switch (direction[dir]) {
      case "UP":
        if (isInside(cx, cy, grid2D)) cy--;
        grid2D[cx][cy] = 2;
        //greenCell(cx, cy, CellSize, SquareSize, g);
        break;
      case "RIGHT":
        if (isInside(cx, cy, grid2D)) cx++;
        grid2D[cx][cy] = 2;
        //greenCell(cx, cy, CellSize, SquareSize, g);
        break;
      case "DOWN":
        if (isInside(cx, cy, grid2D)) py++;
        grid2D[cx][cy] = 2;
        //greenCell(cx, cy, CellSize, SquareSize, g);
        break;
      case "LEFT":
        if (isInside(cx, cy, grid2D)) cx--;
        grid2D[cx][cy] = 2;
        //greenCell(cx, cy, CellSize, SquareSize, g);
        break;
    }
    repaint();
  }
}
