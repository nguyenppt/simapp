package pos;

/*
 * Created on Jul 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author nghi.doan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import common.DataAccessLayer;
import java.sql.Statement;
/**
* @version 1.0 12/03/98
*/

public class TotalRowExample extends JFrame {
        FrameMain frmMain;
        DataAccessLayer DAL = new DataAccessLayer();
        Object[][] data;
        Statement stmt = null;
//	final private int TOTAL_ROW = 3;
        int total_row=3;
        final private int TOTAL_COLUMN = 1;
        int total_column=1;
        int total_column2=2;
        int total_column3=3;
        public TotalRowExample() {
                super( "Total Row Example" );
                getData();
                final DecimalFormat formatter = new DecimalFormat("###,##0.00");
                DefaultTableModel dm = new DefaultTableModel() {
                        public void setValueAt(Object value, int row, int col) {
                                Vector rowVector = (Vector)dataVector.elementAt(row);
                                if (col == TOTAL_COLUMN) {
                                        Double d = null;
                                        if (value instanceof Double) {
                                                d = (Double)value;
                                        } else {
                                                try {
                                                        d = new Double(
                                                                        ((Number)formatter.parse(value.toString())).doubleValue());
                                                } catch (ParseException ex) {
                                                        d = new Double(0.0);
                                                }
                                        }
                                        rowVector.setElementAt(d, col);
                                } else {
                                        rowVector.setElementAt(value, col);
                                }
                        }
                        public boolean isCellEditable(int row, int col) {
                                if (row == total_row) return false;
                                if (row == total_row + 1) return false;
                                if (row == total_row + 2) return false;
                                return true;
//				return false;
                        }
                        public Class getColumnClass(int col) {
                                if (col == TOTAL_COLUMN) return Number.class;
                                return String.class;
                        }
                };

//		dm.setDataVector(
//				new Object[][]{
//						{"coffee",new Double(30.0)},
//						{"tea" ,new Double(12.0)},
//						{"cocoa" ,new Double(15.0)},
//						{"total" ,new Double(0.0)}},
//						new Object[]{"Item","Price"});
                dm.setDataVector( data, new Object[]{"Item", "Max stock", "Display stock"});
                JTable table = new JTable( dm );//{
//			public void editingStopped(ChangeEvent e) {
//				super.editingStopped(e);
//				reCalcurate(table);
//				repaint();
//			}
//		};
                reCalcurate(table);
                repaint();
                table.getTableHeader().setFont(new java.awt.Font("Dialog",1,12));
                table.getColumn("Display stock").setCellRenderer(
                                new DecimalRenderer(formatter));
                JScrollPane scroll = new JScrollPane(table);
                Container content = getContentPane();
                content.add(scroll);
                setSize( 300, 120 );
                setVisible(true);
        }
        public void getData(){
                ResultSet rs = null;
                DAL.getOracleConnect();
                int i=0;
                int j=0;
                try{
//                  System.out.println("select count(*) num from qr_product_location where location_id = 586");
                        stmt = DAL.getConnection().createStatement();
                        rs = DAL.executeQueries("select count(*) num from qr_product_location where location_id = 586",stmt);
                        if (rs.next()){
                                i = rs.getInt("num");
//                                System.out.println("Row count: " + i);
                                rs=null;
                        }
                        stmt.close();
                        total_row = i;
//			rs = DAL.executeQueries("Select location_id from qr_promo group by location_id");
//			while (rs.next()){
//				j+=1;
//			}
//			rs = null;

                        Object[][] result = new Object[i+3][3];
//			String[] v_validStates = new String[j];
//			rs = DAL.executeQueries("Select location_id from from qr_product_location where location_id = 586");
//			i = 0;
////			v_validStates[0] = "-----oOo-----";
//			while (rs.next()){
////				i+=1;
//				v_validStates[i] = rs.getString("location_id");
////				vColumn2.addElement(rs.getString("location_id"));
////				result[i][1] = v_validStates[0];
//				i+=1;
//			}
//			rs.close();
//			Object[] vv_validStates = new Object[i];
//			vv_validStates = v_validStates;
                        double max_stock;
                        i = 0;
//                        System.out.println("Select product_id, max_stock, display_stock from qr_product_location where location_id = 586");
                        stmt = DAL.getConnection().createStatement();
                        rs = DAL.executeQueries("Select product_id, max_stock, display_stock from qr_product_location where location_id = 586",stmt);
                        while (rs.next()){
//				for(j=0;j<=1;j++){
                                result[i][0] = rs.getString("product_id");
//				vColumn1.addElement(rs.getString("product_id"));
//				v_validStates[i] = rs.getString("location_id");
//				result[i][1] = v_validStates[0];
                                max_stock = rs.getDouble("max_stock");
                                result[i][1] = new Double(max_stock);
                                result[i][2] = new Double(rs.getDouble("display_stock"));
                                i+=1;
//				}
                        }
                        result[i][1] = "Max_Total";
                        result[i][2] = new Double(0.0);
                        result[i+1][1] = "Display_total";
                        result[i+1][2] = new Double(0.0);
                        result[i+2][1] = "Total";
                        result[i+2][2] = new Double(0.0);
//			Object[][] v_result = new Object[i][3];
//			v_result = result;
                        data = result;
                        stmt.close();
                }
                catch(Exception e){

                }

        }


        private void reCalcurate(JTable ml) {
                if (ml == null) return;
                double total = 0.0;
                double max_total = 0.0;
                double sub_total = 0.0;
                for (int i=0;i<total_row;i++) {
//			total += ((Double)ml.getValueAt(i,TOTAL_COLUMN)).doubleValue();
                        max_total += ((Double)ml.getValueAt(i,TOTAL_COLUMN)).doubleValue();
                        sub_total += ((Double)ml.getValueAt(i,total_column2)).doubleValue();
                }
                total = max_total - sub_total;
                ml.setValueAt(new Double(max_total),total_row,total_column2);
                ml.setValueAt(new Double(sub_total),total_row + 1,total_column2);
                ml.setValueAt(new Double(total),total_row + 2, total_column2);
        }
        public static void main(String[] args) {
                TotalRowExample frame = new TotalRowExample();
                frame.addWindowListener( new WindowAdapter() {
                        public void windowClosing( WindowEvent e ) {
                                System.exit(0);
                        }
                });
        }
}
