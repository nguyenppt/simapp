//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//














































package sim;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import btm.swing.*;
import btm.attr.*;
import btm.swing.*;
import common.*;
import btm.business.*;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */
//not use now
public class PanelPrdHierSetupModify extends JPanel {
  DataAccessLayer DAL;

  Utils ut = new Utils();
  ObjHierType prdHier=new ObjHierType();
  HierBusObj hierBusObj = new HierBusObj();
  Vector vSql = new Vector();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  String typeId="";
  String parentId="";
  //define for panel
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;
  JPanel pnlMain = new JPanel();

  BJButton btnPrdHierTypeModify = new BJButton();
  BJButton btnPrdHierTypeCancel = new BJButton();
  BJButton btnPrdHierTypeDone = new BJButton();

  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  //create panel
  BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel pnlCenter = new JPanel();
  JPanel pnlStatus = new JPanel();
  JLabel lblParent = new JLabel();
  JLabel lblName = new JLabel();
  JLabel lblDesc = new JLabel();
  JTextField txtName = new JTextField();
  JTextField txtDesc = new JTextField();

  //grid layout
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout2 = new GridLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel pnlText = new JPanel();
  JPanel pnlTable = new JPanel();
  BJPanel pnlButtonContent = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel3 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel jPanel4 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JLabel lblInputItem = new JLabel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JComboBox jCboParent = new JComboBox();
  FlowLayout flowLayout2 = new FlowLayout();
  JScrollPane jScrollDataGrid = new JScrollPane();
  JScrollPane jScrollPane1 = new JScrollPane();
  JOptionPane jOpt = new JOptionPane();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JScrollPane jScrollData = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  JTable table = new JTable(dm);

  /** Create Panel for Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		FrameMainSim frmMain, CardLayout crdLayout, JPanel parent
   * @return		no
   */
  public PanelPrdHierSetupModify(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      this.cardLayout = crdLayout;
      this.parent = parent;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }


  /** Create jbInit method - init value for frame
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    this.setSize(800,600);

    //event for table
//    table = new JTable(dm){
//      public boolean isCellEditable(int row, int col){
//        return false;
//      }
//    };

    pnlMain.setBackground(new Color(55, 194, 255));
    pnlMain.setLayout(borderLayout2);
    pnlCenter.setMinimumSize(new Dimension(10, 10));
    pnlCenter.setPreferredSize(new Dimension(660, 60));
    pnlCenter.setLayout(borderLayout4);

    pnlText.setMinimumSize(new Dimension(10,10));
    pnlText.setPreferredSize(new Dimension(660, 100));
    pnlText.setRequestFocusEnabled(true);
    pnlText.setLayout(new BorderLayout());

    pnlTable.setMinimumSize(new Dimension(10,10));
    pnlTable.setPreferredSize(new Dimension(660, 357));
    pnlTable.setLayout(borderLayout5);

    lblParent.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblParent.setPreferredSize(new Dimension(100, 21));
    lblParent.setText("Parent:");
    lblParent.setBounds(new Rectangle(10, 11, 100, 21));
    lblName.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblName.setPreferredSize(new Dimension(100, 21));
    lblName.setText("Name:");
    lblName.setBounds(new Rectangle(10, 37, 100, 21));
    lblDesc.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblDesc.setPreferredSize(new Dimension(100, 21));
    lblDesc.setText("Description:");
    lblDesc.setBounds(new Rectangle(10, 63, 100, 21));
    jCboParent.setPreferredSize(new Dimension(240, 21));
    jCboParent.setBounds(new Rectangle(5, 15, 240, 21));
    jCboParent.setFont(new java.awt.Font("Dialog", 1, 11));
    txtName.setFont(new java.awt.Font("Dialog", 1, 11));
    txtName.setPreferredSize(new Dimension(240, 21));
    txtName.setBounds(new Rectangle(5, 41, 240, 21));
    txtName.addKeyListener(new PanelPrdHierSetupModify_txtName_keyAdapter(this));
    txtDesc.setFont(new java.awt.Font("Dialog", 1, 11));
    txtDesc.setPreferredSize(new Dimension(40, 21));
    txtDesc.setBounds(new Rectangle(5, 66, 240, 21));
        txtDesc.addKeyListener(new PanelPrdHierSetupModify_txtDesc_keyAdapter(this));
    jPanel6.setMinimumSize(new Dimension(38, 31));
    jPanel6.setPreferredSize(new Dimension(60, 31));
    jPanel6.setLayout(null);
    jPanel2.setLayout(null);
    flowLayout2.setAlignment(FlowLayout.LEFT);
    btnPrdHierTypeDone.addActionListener(new PanelPrdHierSetupModify_btnPrdHierTypeDone_actionAdapter(this));
    btnPrdHierTypeModify.setToolTipText("Modify (F2)");
    btnPrdHierTypeModify.setActionCommand("Modify");
    btnPrdHierTypeDone.setToolTipText("Done (F1)");
    btnPrdHierTypeCancel.setToolTipText("Cancel (F3)");
    lblInputItem.setFont(new java.awt.Font("SansSerif", 0, 12));
    table.addKeyListener(new PanelPrdHierSetupModify_table_keyAdapter(this));
    jPanel2.add(lblDesc, null);
    jPanel2.add(lblParent, null);
    jPanel2.add(lblName, null);

    jPanel6.add(jCboParent, null);
    jPanel6.add(txtName, null);
    jPanel6.add(txtDesc, null);

    //for status
    pnlStatus.setLayout(gridLayout1);
    pnlHeader.setLayout(borderLayout3);

    pnlButtonContent.setLayout(new BoxLayout(pnlButtonContent, BoxLayout.PAGE_AXIS));

    jPanel1.setPreferredSize(new Dimension(20, 10));
    jPanel3.setPreferredSize(new Dimension(50, 10));
    pnlHeader.setPreferredSize(new Dimension(102, 50));

    btnPrdHierTypeDone.setText("Done (F1)");
    btnPrdHierTypeModify.setText("Modify (F2)");
    btnPrdHierTypeCancel.setText("Cancel (F3)");

    btnPrdHierTypeModify.addActionListener(new PanelPrdHierSetupModify_btnPrdHierTypeModify_actionAdapter(this));
    btnPrdHierTypeCancel.addActionListener(new PanelPrdHierSetupModify_btnPrdHierTypeCancel_actionAdapter(this));
    table.addMouseListener(new PanelPrdHierSetupModify_table_mouseAdapter(this));

    jPanel4.setLayout(flowLayout2);
    gridLayout1.setColumns(0);
    gridLayout1.setRows(1);

    jPanel2.setPreferredSize(new Dimension(120, 10));
    jPanel5.setPreferredSize(new Dimension(290, 10));
    this.add(pnlMain, BorderLayout.CENTER);
    pnlMain.add(pnlCenter, BorderLayout.CENTER);

    pnlCenter.add(pnlText, BorderLayout.NORTH);
    pnlText.add(jPanel2, BorderLayout.WEST);
    pnlText.add(jPanel5, BorderLayout.EAST);
    pnlText.add(jPanel6, BorderLayout.CENTER);

    //add all panel
    pnlMain.add(pnlStatus, BorderLayout.SOUTH);
    pnlMain.add(pnlHeader, BorderLayout.NORTH);
    pnlHeader.add(jPanel1, BorderLayout.WEST);
    pnlHeader.add(pnlButtonContent,  BorderLayout.CENTER);
    pnlButtonContent.add(jPanel4, null);
    pnlHeader.add(jPanel3,  BorderLayout.EAST);
    //add button into panl header
    jPanel4.add(btnPrdHierTypeDone, null);
    jPanel4.add(btnPrdHierTypeModify, null);
    jPanel4.add(btnPrdHierTypeCancel, null);


    pnlCenter.add(pnlTable, BorderLayout.CENTER);
    pnlTable.add(jScrollData,  BorderLayout.CENTER);
    jScrollData.getViewport().add(table, null);

//define for table
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font("Dialog",1,13));
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    String[] columnNames = new String[]{"TypeId", "ParentId", "Name","Description"};
    dm.setDataVector(new Object[][]{},columnNames);
    table.getColumn("TypeId").setPreferredWidth(120);
    table.getColumn("ParentId").setPreferredWidth(120);
    table.getColumn("Name").setPreferredWidth(200);
    table.getColumn("Description").setPreferredWidth(300);
    table.setRowHeight(30);
    //call method
    createComboParent();
  }

  /** Cancel Button of Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnPrdHierTypeCancel_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_PRD_HIER_TYPE_SEARCH);
  }

  /** Add Button of Product Hier Type Setup
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  void btnPrdHierTypeModify_actionPerformed(ActionEvent e) {
    String parentName = this.jCboParent.getSelectedItem().toString();
    String name = this.txtName.getText();
    String desc = this.txtDesc.getText();
    if (name.equals("")){
      ut.showMessage(frmMain,"Warning!" ,"The org hier name is not null !");
      this.txtName.requestFocus(true);
      return;
    }
    vSql.removeAllElements();
    while (dm.getRowCount()>0){
      dm.removeRow(0);
    }
    vSql.addElement("update BTM_IM_PROD_HIR_TYPE set NAME='" + name + "', DESCRIPTION = '" + desc + "' where TYPE_ID = '" + typeId + "'");
    String[] rowData = new String[] {
                           typeId, parentId, name, desc
    };
    dm.addRow(rowData);
    str.installInTable(table, Color.lightGray, Color.white, null, null);
  }

  /** Assign value for Combo button
   * @author 		Tuan.Truong
   * @param 		no
   * @return		no
   */
  private void createComboParent(){
   ResultSet rs = null;
   //reset cbo and vector
   jCboParent.removeAllItems();
   String sSQL = "select TYPE_ID, HIGHER_TYPE_ID, NAME, DESCRIPTION from BTM_IM_PROD_HIR_TYPE order by TYPE_ID";
//   System.out.println(sSQL);
   rs = DAL.executeQueries(sSQL);
   try {
     while (rs.next()) {
       jCboParent.addItem(rs.getString("NAME"));
    }
    int count = jCboParent.getItemCount();
    if (count == 0) {
      jCboParent.addItem(Constant.root);
      jCboParent.setSelectedIndex(0);
    }
    else {
      jCboParent.setSelectedIndex(count - 1);
    }
    rs.getStatement().close();
  }catch (Exception e) {
    ExceptionHandle eh = new ExceptionHandle();
    eh.ouputError(e);
   }
 }//end createComboParent

 private void addComboParent(Object o) {
   jCboParent.addItem(o);
   int count = jCboParent.getItemCount();
   if (count == 0) {
     jCboParent.addItem(Constant.root);
     jCboParent.setSelectedIndex(0);
   }
   else {
     jCboParent.setSelectedIndex(count - 1);
   }
 }
   /** Show data for DataGrid
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   void showData() {
     ResultSet rs = null;
     try{
       String sql = "select * from BTM_IM_PROD_HIR_TYPE where TYPE_ID ='" + typeId + "'";
//       System.out.println(sql);
       rs = DAL.executeQueries(sql);
       if (rs.next()){
         int index = Integer.valueOf(rs.getString("TYPE_ID")).intValue()-2;
         if (index<=-1) {
//            this.jCboParent.setSelectedIndex();//root
         }else {
           this.jCboParent.setSelectedIndex(index);
         }
         parentId = rs.getString("HIGHER_TYPE_ID");
         this.txtName.setText(rs.getString("NAME"));
         this.txtDesc.setText(rs.getString("DESCRIPTION"));
       }
       rs.getStatement().close();
     }catch(Exception e){
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
   }//end of show Data

  /** checkName method - if value ==true add button will enable = true, else = false
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   private boolean checkName(String name) {
     if (name.length()==0)  {
       return true;
     }else {
       return false;
     }
   }

   /** setEnableModifyButton method - if value ==true add button will enable = true, else = false
    * @author 		Tuan.Truong
    * @param 		no
    * @return		no
    */
   private void setEnableModifyButton(boolean value){
     if (value == true) {
       this.btnPrdHierTypeModify.setEnabled(true);
     }else {
       this.btnPrdHierTypeModify.setEnabled(false);
     }
   }
   void resetData(){
      while(dm.getRowCount()>0){
        dm.removeRow(0);
      }
    }


  void txtName_keyReleased(KeyEvent e) {
    if (checkName(txtName.getText().toString()) ) {
      setEnableModifyButton(false);
    }else {
      setEnableModifyButton(true);
    }
  }

  void table_mouseClicked(MouseEvent e) {
    String id = table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString();
  }

  void btnPrdHierTypeSearch_actionPerformed(ActionEvent e) {
//    frmMain.pnlOrgHierTypeSearch.showData(ut.DEFAULT_ROW_LIMITED);
//    frmMain.showScreen(Constant.SCRS_PRD_HIER_TYPE_SEARCH);
  }

  void btnPrdHierTypeDone_actionPerformed(ActionEvent e) {
    if (vSql.isEmpty() == false){
        DAL.executeBatchQuery(vSql);
      }
      while (dm.getRowCount()>0){
        dm.removeRow(0);
      }
      frmMain.showScreen(Constant.SCRS_MAIN_SIM);
  }

    void txtName_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e,txtName,ObjHierType.LEN_TYPE_NAME);
    }

    void txtDesc_keyTyped(KeyEvent e) {
        ut.limitLenjTextField(e,txtDesc,ObjHierType.LEN_TYPE_DESC);
    }
    private void registerKeyboardActions() {
     /// set up the maps:
     InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
     ActionMap actionMap = getActionMap();

     // F1
     Integer key = new Integer(KeyEvent.VK_F1);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), key);
     actionMap.put(key, new KeyAction(key));

     // F2
     key = new Integer(KeyEvent.VK_F2);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), key);
     actionMap.put(key, new KeyAction(key));

     // F3
     key = new Integer(KeyEvent.VK_F3);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), key);
     actionMap.put(key, new KeyAction(key));

     // ESCAPE
        key = new Integer(KeyEvent.VK_ESCAPE);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
        actionMap.put(key, new KeyAction(key));
   }
   class KeyAction extends AbstractAction {

        private Integer identifier = null;

        public KeyAction(Integer identifier) {
          this.identifier = identifier;
        }

        /**
         * Invoked when an action occurs.
         */
        public void actionPerformed(ActionEvent e) {

          if (identifier.intValue() == KeyEvent.VK_F1) {
            btnPrdHierTypeDone.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnPrdHierTypeModify.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F3 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnPrdHierTypeCancel.doClick();
          }
        }
      }

  void table_mousePressed(MouseEvent e) {
//    btnPrdHierTypeDone.requestFocus();
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnPrdHierTypeModify.doClick();
               }
  }

}//end class



class PanelPrdHierSetupModify_btnPrdHierTypeCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierSetupModify adaptee;

  PanelPrdHierSetupModify_btnPrdHierTypeCancel_actionAdapter(PanelPrdHierSetupModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierTypeCancel_actionPerformed(e);
  }
}

class PanelPrdHierSetupModify_btnPrdHierTypeModify_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierSetupModify adaptee;

  PanelPrdHierSetupModify_btnPrdHierTypeModify_actionAdapter(PanelPrdHierSetupModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierTypeModify_actionPerformed(e);
  }
}

class PanelPrdHierSetupModify_txtName_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPrdHierSetupModify adaptee;

  PanelPrdHierSetupModify_txtName_keyAdapter(PanelPrdHierSetupModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtName_keyReleased(e);
  }
    public void keyTyped(KeyEvent e) {
        adaptee.txtName_keyTyped(e);
    }
}

class PanelPrdHierSetupModify_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelPrdHierSetupModify adaptee;

  PanelPrdHierSetupModify_table_mouseAdapter(PanelPrdHierSetupModify adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
  public void mousePressed(MouseEvent e) {
    adaptee.table_mousePressed(e);
  }
}

class PanelPrdHierSetupModify_btnPrdHierTypeDone_actionAdapter implements java.awt.event.ActionListener {
  PanelPrdHierSetupModify adaptee;

  PanelPrdHierSetupModify_btnPrdHierTypeDone_actionAdapter(PanelPrdHierSetupModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrdHierTypeDone_actionPerformed(e);
  }
}

class PanelPrdHierSetupModify_txtDesc_keyAdapter extends java.awt.event.KeyAdapter {
    PanelPrdHierSetupModify adaptee;

    PanelPrdHierSetupModify_txtDesc_keyAdapter(PanelPrdHierSetupModify adaptee) {
        this.adaptee = adaptee;
    }
    public void keyTyped(KeyEvent e) {
        adaptee.txtDesc_keyTyped(e);
    }
}

class PanelPrdHierSetupModify_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPrdHierSetupModify adaptee;

  PanelPrdHierSetupModify_table_keyAdapter(PanelPrdHierSetupModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
