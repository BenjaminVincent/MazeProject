import javax.swing.*;
import java.awt.*;
public class Grid extends JPanel {

  public void paintComponent(Graphics g) {

    int[][] grid2D = new int[10][10];
    int CellSize = 40;
    super.paintComponent(g);
    setBackground(Color.WHITE);
    g.setColor(Color.BLACK);


    //g.drawLine(CellSize, 0, CellSize, 1000);
    // Draw columns
    for (int i = 0; i < 960; i++) {
      g.drawLine(i * CellSize, 0, i * CellSize, 1000);
    }
    for (int j = 0; j < 960; j++) {
      g.drawLine(0, j * CellSize, 1000, j * CellSize);
    }

  }
}
