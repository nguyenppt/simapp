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
 * <p>Description: Transfer > View > Search > double click</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelTransferSearchDetail extends JPanel{
  DataAccessLayer DAL;
  ResourceBundle lang;
  FrameMainSim frmMain;
  Vector vSql = new Vector();
  Utils ut = new Utils();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel6 = new JPanel();
  BJButton btnPrint = new BJButton();
  BJButton btnCancel = new BJButton();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JTextField txtTransferDate = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField txtTransferID = new JTextField();
  JPanel jPanel4 = new JPanel();
  JLabel lblQty = new JLabel();
  JLabel jLabel1 = new JLabel();
  JTextField txtSrcStoreID = new JTextField();
  JLabel jLabel3 = new JLabel();
  JPanel jPanel10 = new JPanel();
  JLabel jLabel2 = new JLabel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JTextField txtCreaterName = new JTextField();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JTextField txtQty = new JTextField();
  JPanel jPanel8 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BJComboBox cboDesStoreID = new BJComboBox();
  JLabel lblItemID = new JLabel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JLabel jLabel6 = new JLabel();
  JTextField txtItemID = new JTextField();
  JTextField txtReceiverName = new JTextField();
  JPanel jPanel9 = new JPanel();
  JLabel jLabel5 = new JLabel();
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

  public PanelTransferSearchDetail() {
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

    jPanel6.setBackground(new Color(121, 152, 198));
    jPanel6.setPreferredSize(new Dimension(800, 50));
    this.setLayout(borderLayout1);
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    //Button Cancel
    btnCancel.setToolTipText(lang.getString("Back")+" (F2)");
    btnCancel.setActionCommand("btnCancel");
    btnCancel.setText("<html><center><b>"+lang.getString("Back")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</h" +
    "tml>");
    //Button Print
    btnPrint.setToolTipText(lang.getString("Print")+" (F1)");
    btnPrint.setActionCommand("btnPrint");
    btnPrint.setText("<html><center><b>"+lang.getString("Print")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</h" +
                      "tml>");

    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel1.setLayout(borderLayout2);
    txtTransferDate.setText("");
    txtTransferDate.setPreferredSize(new Dimension(150, 20));
    txtTransferDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel4.setText(lang.getString("ToStore")+":");
    jLabel4.setPreferredSize(new Dimension(120, 20));
    jLabel4.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtTransferID.setText("");
    txtTransferID.setPreferredSize(new Dimension(150, 20));
    txtTransferID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel4.setPreferredSize(new Dimension(200, 10));
    lblQty.setText(lang.getString("Quantity")+":");
    lblQty.setPreferredSize(new Dimension(120, 20));
    lblQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel1.setText(lang.getString("ReceiverName")+":");
    jLabel1.setPreferredSize(new Dimension(120, 20));
    jLabel1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtSrcStoreID.setText("");
    txtSrcStoreID.setPreferredSize(new Dimension(150, 20));
    txtSrcStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel3.setText(lang.getString("TransferDate")+":");
    jLabel3.setPreferredSize(new Dimension(120, 20));
    jLabel3.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jPanel10.setPreferredSize(new Dimension(130, 100));
    jPanel10.setMinimumSize(new Dimension(38, 31));
    jPanel10.setBackground(SystemColor.control);
    jLabel2.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel2.setPreferredSize(new Dimension(120, 20));
    jLabel2.setText(lang.getString("CreaterName")+":");
    jPanel2.setBackground(SystemColor.control);
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setPreferredSize(new Dimension(800, 100));
    jPanel2.setLayout(borderLayout3);
    txtCreaterName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreaterName.setPreferredSize(new Dimension(150, 20));
    txtCreaterName.setEditable(false);
    txtCreaterName.setText("");
    jPanel5.setPreferredSize(new Dimension(300, 100));
    jPanel5.setLayout(borderLayout5);
    jPanel11.setBackground(SystemColor.control);
    jPanel11.setPreferredSize(new Dimension(170, 100));
    txtQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtQty.setPreferredSize(new Dimension(150, 20));
    jPanel8.setBackground(SystemColor.control);
    jPanel8.setPreferredSize(new Dimension(130, 100));
    cboDesStoreID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboDesStoreID.setNextFocusableComponent(txtReceiverName);
    cboDesStoreID.setPreferredSize(new Dimension(150, 20));
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setPreferredSize(new Dimension(120, 20));
    lblItemID.setText(lang.getString("ItemID")+":");
    jPanel3.setBackground(SystemColor.control);
    jPanel3.setPreferredSize(new Dimension(300, 100));
    jPanel3.setLayout(borderLayout4);
    jLabel6.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel6.setPreferredSize(new Dimension(120, 20));
    jLabel6.setText(lang.getString("TransferId")+":");
    txtItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtItemID.setNextFocusableComponent(txtQty);
    txtItemID.setPreferredSize(new Dimension(150, 20));
    txtItemID.setSelectionStart(11);
    txtReceiverName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtReceiverName.setNextFocusableComponent(txtItemID);
    txtReceiverName.setPreferredSize(new Dimension(150, 20));
    txtReceiverName.setText("");
    jPanel9.setBackground(SystemColor.control);
    jPanel9.setPreferredSize(new Dimension(170, 100));
    jLabel5.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel5.setPreferredSize(new Dimension(120, 20));
    jLabel5.setText(lang.getString("FromStore")+":");
    jScrollPane1.getViewport().setBackground(SystemColor.control);
    jScrollPane1.setAutoscrolls(false);
    jScrollPane1.setPreferredSize(new Dimension(780, 395));
    this.add(jPanel6, BorderLayout.NORTH);
    jPanel6.add(btnPrint, null);
    jPanel6.add(btnCancel, null);
    this.add(jPanel1,  BorderLayout.CENTER);
    jPanel8.add(jLabel6, null);
    jPanel8.add(jLabel5, null);
    jPanel8.add(jLabel4, null);
    jPanel8.add(lblItemID, null);
    jPanel3.add(jPanel9, BorderLayout.CENTER);
    jPanel3.add(jPanel8, BorderLayout.WEST);
    jPanel9.add(txtTransferID, null);
    jPanel9.add(txtSrcStoreID, null);
    jPanel9.add(cboDesStoreID, null);
    jPanel9.add(txtItemID, null);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel2.add(jPanel3, BorderLayout.WEST);
    jPanel10.add(jLabel3, null);
    jPanel10.add(jLabel2, null);
    jPanel10.add(jLabel1, null);
    jPanel10.add(lblQty, null);
    jPanel5.add(jPanel11, BorderLayout.CENTER);
    jPanel5.add(jPanel10, BorderLayout.WEST);
    jPanel11.add(txtTransferDate, null);
    jPanel11.add(txtCreaterName, null);
    jPanel11.add(txtReceiverName, null);
    jPanel11.add(txtQty, null);
    jPanel2.add(jPanel4, BorderLayout.EAST);
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    jPanel1.add(jPanel2,  BorderLayout.NORTH);
    jScrollPane1.add(table, null);
  }
}
