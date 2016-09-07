package btm.swing;

/**
 * @author nghi.doan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class HeaderListener extends MouseAdapter {
        JTableHeader header;
        SortButtonRenderer renderer;
        int unSortColumn = -1;
        HeaderListener(JTableHeader header,SortButtonRenderer renderer, int unSortColumn) {
                this.header = header;
                this.renderer = renderer;
                this.unSortColumn = unSortColumn;
        }
        public void mousePressed(MouseEvent e) {
          int col = header.columnAtPoint(e.getPoint());
          if (col == unSortColumn){
            return;
          }
          int sortCol = header.getTable().convertColumnIndexToModel(col);
          renderer.setPressedColumn(col);
          renderer.setSelectedColumn(col);
          header.repaint();
          if (header.getTable().isEditing()) {
            header.getTable().getCellEditor().stopCellEditing();
          }
          boolean isAscent;
          if (SortButtonRenderer.DOWN == renderer.getState(col)) {
            isAscent = true;
          }
          else {
            isAscent = false;
          }
          //sort by column
          ( (SortableTableModel) header.getTable().getModel())
              .sortByColumn(sortCol, isAscent);
        }
        public void mouseReleased(MouseEvent e) {
          int col = header.columnAtPoint(e.getPoint());
          if (col == unSortColumn){
            return;
          }
          renderer.setPressedColumn(-1); // clear
          header.repaint();
        }
}
