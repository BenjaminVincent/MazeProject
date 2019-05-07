import javax.swing.*;
import java.awt.*;
public class Grid extends JPanel {

  public void paintComponent(Graphics g) {

    int[][] grid2D = new int[20][20];
    int CellSize = 40;
    super.paintComponent(g);
    setBackground(Color.WHITE);
    g.setColor(Color.BLACK);

    // Setting these to 1 will fill the cell in
    grid2D[3][2] = 1;
    grid2D[4][3] = 1;
    grid2D[1][1] = 2; // 2 is green

    // Loop through the grid
    for (int i = 0; i < grid2D.length; i++) {
      for (int j = 0; j < grid2D[i].length; j++) {
        g.drawRect(i * CellSize , j * CellSize, CellSize, CellSize);
        if (grid2D[i][j] == 1) {
          g.fillRect(i * CellSize, j * CellSize, CellSize, CellSize);
        } else if (grid2D[i][j] == 2) { // This is a temporary visual demo
          g.setColor(Color.GREEN);
          g.fillRect(i* CellSize + 1, j * CellSize + 1, CellSize-1, CellSize-1);
          g.setColor(Color.BLACK);
        }
      }
    }
  }
}
