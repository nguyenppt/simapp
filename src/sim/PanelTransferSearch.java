package sim;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
import common.*;
import btm.swing.*;
import btm.attr.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.ResourceBundle;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Transfer -> View </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelTransferSearch extends JPanel{
  DataAccessLayer DAL;
  ResourceBundle lang;
  FrameMainSim frmMain;
  Vector vSql = new Vector();
  Utils ut = new Utils();
  JPanel jPanel6 = new JPanel();
  BJButton btnCancel = new BJButton();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  SortableTableModel dm = new SortableTableModel();
  BJTable table = new BJTable(dm,true) {
    public boolean isCellEditable(int row, int col) {
      return false;
    }

    public void tableChanged(TableModelEvent e) {
      super.tableChanged(e);
      repaint();
    }

    public Class getColumnClass(int col) {
      if (col == 2) {
        return Long.class;
      }
      return Object.class;
    }
  };

  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();

  public PanelTransferSearch() {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    DAL.getConnfigParameter();
    lang=DAL.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international

    jPanel6.setPreferredSize(new Dimension(800, 50));
    jPanel6.setBackground(new Color(121, 152, 198));
    jPanel6.setMinimumSize(new Dimension(126, 51));
    //Button Cancel
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setToolTipText(lang.getString("Back")+" (F1)");
    btnCancel.setActionCommand("btnCancel");
    btnCancel.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</h" +
    "tml>");
    this.setLayout(borderLayout1);
    jPanel1.setDebugGraphicsOptions(0);
    jPanel1.setPreferredSize(new Dimension(800, 460));
    jPanel1.setLayout(borderLayout2);
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(780, 395));
    jPanel3.setBackground(SystemColor.control);
    jPanel3.setPreferredSize(new Dimension(800, 400));
    jPanel3.setRequestFocusEnabled(true);
    jPanel3.setLayout(borderLayout3);
    jPanel2.setPreferredSize(new Dimension(800, 50));
    jPanel6.add(btnCancel, null);
    this.add(jPanel2,  BorderLayout.CENTER);
    this.add(jPanel1,  BorderLayout.SOUTH);
    jPanel1.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jScrollPane1, BorderLayout.CENTER);
    this.add(jPanel6, BorderLayout.NORTH);
    jScrollPane1.add(table, null);
  }

}
