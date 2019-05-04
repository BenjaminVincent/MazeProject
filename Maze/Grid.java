import javax.swing.*;
import java.awt.*;
public class Grid extends JPanel {

  public void paintComponent(Graphics g) {
    int CellSize = 10;
    super.paintComponent(g);
    setBackground(Color.WHITE);
    g.setColor(Color.BLACK);
    //g.drawLine(CellSize, 0, CellSize, 1000);
    // Draw columns
    for (int i = 0; i < 1000; i++) {
      g.drawLine(i * CellSize, 0, i * CellSize, 1000);
      for (int j = 0; j < 1000; j++) {
        g.drawLine(0, j * CellSize, 1000, j * CellSize);
      }
    }

  }
}
