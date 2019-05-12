import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.text.*;

public class Input extends JPanel implements PropertyChangeListener{
  // set defaults
  int c = 9;
  int r = 9;


  private JLabel colLabel;
  private JLabel rowLabel;

  private static String colString = "Number of Columns: ";
  private static String rowString = "Number of Rows: ";

  private JFormattedTextField colField;
  private JFormattedTextField rowField;

  JButton b;

  public Input() {

    super(new BorderLayout());

    colLabel = new JLabel(colString);
    rowLabel = new JLabel(rowString);

    colField = new JFormattedTextField();
    colField.setValue(c);
    colField.setColumns(5);
    colField.addPropertyChangeListener("value", this);


    rowField = new JFormattedTextField();
    rowField.setValue(r);
    rowField.setColumns(5);
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

    //setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    add(labelPane, BorderLayout.CENTER);
    add(fieldPane, BorderLayout.LINE_END);
    add(b, BorderLayout.NORTH);
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
