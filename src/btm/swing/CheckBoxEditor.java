package btm.swing;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class CheckBoxEditor extends DefaultCellEditor
        implements ItemListener {
      private JCheckBox button;
        public CheckBoxEditor(JCheckBox checkBox) {
                super(checkBox);
        }
        public Component getTableCellEditorComponent(JTable table, Object value,
                        boolean isSelected, int row, int column) {
                if (value==null) return null;
                button = (JCheckBox)value;
                button.addItemListener(this);
                return (Component)value;
        }
        public Object getCellEditorValue() {
                button.removeItemListener(this);
                return button;
        }
        public void itemStateChanged(ItemEvent e) {
                super.fireEditingStopped();
        }
}
