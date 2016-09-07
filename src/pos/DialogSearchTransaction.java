package pos;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import btm.swing.*;
import common.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Date;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class DialogSearchTransaction extends JDialog {
  Utils ut = new Utils();

  JPanel panel1 = new JPanel();
  DataAccessLayer DAL = new DataAccessLayer();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
SortableTableModel dm = new SortableTableModel(){
  public Class getColumnClass(int col) {
    switch (col) {
      case 1: return Date.class;
      default: return Object.class;
    }
  }
};
BJTable table;
public boolean done;

  public DialogSearchTransaction(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogSearchTransaction() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(new GridLayout());
    table = new BJTable(dm,true);
    table.setHeader(new java.awt.Font(lang.getString("FontName_Label"),1,13));
    table.addMouseListener(new DialogSearchTransaction_table_mouseAdapter(this));
    JScrollPane jsp = new JScrollPane(table);
    jsp.setViewportView(table);
    panel1.add(jsp,null);
    getContentPane().add(panel1);
    this.setTitle(lang.getString("TT006"));
    this.setSize(new Dimension(775, 465));
    //set center screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
                     (screenSize.height - frameSize.height) / 2);
//    this.setVisible(true);
  }
  void setData(String trans_id){
    Connection con = null;
    con = DAL.getOracleConnect();
    ResultSet rs = null;
    try{
      String sql = "select distinct trans_id, trans_desc,trunc(trans_date) trans_date " +
          " from BTM_POS_DETAIL_TRANS d, BTM_POS_TRANS_TYPE t " +
         " where d.trans_type_id = t.trans_type_id and trans_id like '%" + trans_id + "%'";
//     System.out.println(sql);

     rs = DAL.executeScrollQueries(sql);
     table.setData(rs);
     table.setHeaderName(lang.getString("TransactionID"),0);
     table.setHeaderName(lang.getString("TransactionDesc"),1);
     table.setHeaderName(lang.getString("TransactionDate"),2);
//     table.setColumnWidth(2, 200);
//     table.setRowHeight(30);
//     table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//     table.setColor(Color.lightGray, Color.white);
     if (dm.getRowCount()>0){
       table.setRowSelectionInterval(0, 0);
     }
     this.setVisible(true);
   }catch(Exception ex){
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(ex);
   }

 }
 String getData(){
    String result = "";
    result = table.getValueAt(table.getSelectedRow(),0).toString();
    return result;
  }
  void table_mousePressed(MouseEvent e) {
    if (e.getClickCount() == 2){
      done = true;
      this.dispose();
    }
  }
}

class DialogSearchTransaction_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogSearchTransaction adaptee;

  DialogSearchTransaction_table_mouseAdapter(DialogSearchTransaction adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}
