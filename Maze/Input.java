import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Input extends JPanel implements PropertyChangeListener{

  private static final long serialVersionUID = 1L;

  private JLabel colLabel = new JLabel(colString);
  private JLabel rowLabel  = new JLabel(rowString);

  private static String colString = "Number of Columns: ";
  private static String rowString = "Number of Rows: ";

  private JFormattedTextField colField;
  private JFormattedTextField rowField;


  // set defaults
  int c = 6;
  int r = 8;

  int winWidth;
  int winHeight;

  JButton b;

  public Input(int WIDTH, int HEIGHT) {

    super(new FlowLayout());

    this.winWidth = WIDTH;
    this.winHeight = HEIGHT;

    colField = new JFormattedTextField();
    colField.setValue(c);
    colField.setColumns(8);
    colField.addPropertyChangeListener("value", this);


    rowField = new JFormattedTextField();
    rowField.setValue(r);
    rowField.setColumns(8);
    rowField.addPropertyChangeListener("value", this);


    colLabel.setLabelFor(colField);
    rowLabel.setLabelFor(rowField);

    JPanel labelPane = new JPanel(new GridLayout(0, 1));
    labelPane.add(colLabel);
    labelPane.add(rowLabel);


    JPanel fieldPane = new JPanel(new GridLayout(0, 1));
    fieldPane.add(colField);
    fieldPane.add(rowField);

    b = new JButton("Run");

    setBorder(BorderFactory.createEmptyBorder(winHeight/2 - 60, 0, 0, 0));
    add(labelPane);
    add(fieldPane);
    add(b);
  }

  public void propertyChange(PropertyChangeEvent e) {
    Object source = e.getSource();
    if (source == colField) {
      c = ((Number)colField.getValue()).intValue();
    } else if (source == rowField) {
      r = ((Number)rowField.getValue()).intValue();
    }
  }
}
