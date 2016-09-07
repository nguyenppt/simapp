package common;

/**
 * @author nghi.doan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
public class StripedTableCellRenderer implements TableCellRenderer {
        protected TableCellRenderer targetRenderer;
        protected Color evenBack;
        protected Color evenFore;
        protected Color oddBack;
        protected Color oddFore;
        public StripedTableCellRenderer(TableCellRenderer targetRenderer,
                        Color evenBack, Color evenFore, Color oddBack, Color oddFore){
                this.targetRenderer = targetRenderer;
                this.evenBack = evenBack;
                this.evenFore = evenFore;
                this.oddBack = oddBack;
                this.oddFore = oddFore;
        }
        public Component getTableCellRendererComponent(
                                                                        JTable table, Object value,
                                                                        boolean isSelected, boolean hasFocus,
                                                                        int row, int column
                                                                ){
                TableCellRenderer renderer = targetRenderer;
                if (renderer == null){
                        //Get default renderer from the table
                        renderer = table.getDefaultRenderer(table.getColumnClass(column));
                }
                //Let the real renderer create the component
                Component comp;
                comp = renderer.getTableCellRendererComponent(table, value,
                      isSelected, hasFocus, row, column);
                //Now apply the stripe effect
                if (isSelected == false && hasFocus == false){
                        if ((row & 1)==0){
                                comp.setBackground(evenBack != null ? evenBack : table.getBackground());
                                comp.setForeground(evenFore != null ? evenFore : table.getForeground());
                        }
                        else{
                                comp.setBackground(oddBack != null ? oddBack : table.getBackground());
                                comp.setForeground(oddFore != null ? oddFore : table.getForeground());
                        }
                }
                return comp;
        }
        //Convenience method to apply this renderer to single column
        public void installInColumn(JTable table, int columnIndex, Color evenBack,
                                                                                Color evenFore, Color oddBack, Color oddFore){
                TableColumn tc = table.getColumnModel().getColumn(columnIndex);
                //Get the cell renderer for this column, if any
                tc.setCellRenderer(new StripedTableCellRenderer(targetRenderer, evenBack, evenFore, oddBack,oddFore));

        }
        //Convenience method to apply this renderer to an entire table
        public void installInTable(JTable table, Color evenBack, Color evenFore, Color oddBack, Color oddFore){
                StripedTableCellRenderer sharedInstance = null;
                int columns = table.getColumnCount();
                for (int i=0; i<columns; i++){
                        TableColumn tc = table.getColumnModel().getColumn(i);
                        TableCellRenderer targetRenderer = tc.getCellRenderer();
                        if (targetRenderer != null){
                                //this column has a specific renderer
                                tc.setCellRenderer(new StripedTableCellRenderer(targetRenderer, evenBack, evenFore, oddBack, oddFore));
                        }
                        else{
                                //This column uses a class renderer - use a
                                //shared renderer
                                if (sharedInstance == null){
                                        sharedInstance = new StripedTableCellRenderer(null, evenBack,evenFore, oddBack, oddFore);
                                }
                                tc.setCellRenderer(sharedInstance);
                        }
                }
        }
}
