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
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software </p>
 * @author Loan Vo
 * @version 1.0
 */

public class DialogSearchTransfer_Id extends JDialog {
  Utils ut = new Utils();

  JPanel panel1 = new JPanel();
  FrameMain frmMain;
  BorderLayout borderLayout1 = new BorderLayout();
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


  public DialogSearchTransfer_Id(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
     // pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogSearchTransfer_Id() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);

    getContentPane().add(panel1);
    panel1.setLayout(new GridLayout());
   table = new BJTable(dm,true);
   table.addMouseListener(new DialogSearchTransfer_Id_table_mouseAdapter(this));
   table.setHeader(new java.awt.Font(lang.getString("FontName_Label"),1,13));
   JScrollPane jsp = new JScrollPane(table);
   jsp.setViewportView(table);
   panel1.add(jsp,null);
   getContentPane().add(panel1);
   this.setTitle(lang.getString("TT010"));
   this.setSize(new Dimension(629, 456));
   //set center screen
   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   Dimension frameSize = this.getSize();
   this.setLocation((screenSize.width - frameSize.width) / 2,
                    (screenSize.height - frameSize.height) / 2);
  }
    void setData(String transfer_date){
    Connection con = null;
    con = DAL.getOracleConnect();
    ResultSet rs = null;
    try{
      String sql = "select distinct transfer_id, creater_name, receiver_name " +
          " from BTM_POS_TRANSFER_ITEM " +" where transfer_date = to_date('" + transfer_date + "', 'DD/MM/YYYY')";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql);
        table.setData(rs);
        table.setHeaderName(lang.getString("TransferId"),0);
        table.setHeaderName(lang.getString("CreaterName"),1);
        table.setHeaderName(lang.getString("ReceiveName"),2);
//        table.setColumnWidth(2, 200);
//        table.setRowHeight(30);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
class DialogSearchTransfer_Id_table_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogSearchTransfer_Id adaptee;

  DialogSearchTransfer_Id_table_mouseAdapter(DialogSearchTransfer_Id adaptee) {
    this.adaptee = adaptee;
  }

  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}
