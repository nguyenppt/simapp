//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//



































package sim;

import java.awt.*;
import javax.swing.*;
import btm.swing.*;
import common.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Vector;
import btm.attr.*;
import java.sql.Statement;
import java.util.ResourceBundle;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */










































//not use now
public class DialogAttributeSetup extends JDialog {
  DataAccessLayer DAL;
  FrameMainSim frmMain;
  Utils ut = new Utils();
  Statement stmt = null;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDone = new BJButton();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JLabel lblColorSize = new JLabel();
  JLabel lblProdGroup = new JLabel();
  JLabel lblAttribute = new JLabel();
  JTextField txtColorSize = new JTextField();
  BJComboBox cboProdGroup = new BJComboBox();
  BJComboBox cboAttr = new BJComboBox();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);
  BJTable table = new BJTable(dm){
    public boolean isCellEditable(int row,int col){
      return false;
    }
  };
  StripedTableCellRenderer stripedTable = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  ResultSet rs = null;
  String attrDtlID = "";
  Vector vSql = new Vector();
  Vector vId = new Vector();

  public DialogAttributeSetup(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  public DialogAttributeSetup(Frame frame, String title, boolean modal,FrameMainSim frmMain) {
   super(frame, title, modal);
   try {
     this.frmMain = frmMain;
     DAL = frmMain.getDAL();
     jbInit();
   }
   catch(Exception ex) {
     ex.printStackTrace();
   }
  }
  public DialogAttributeSetup() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
//    System.out.println("N1");
    registerKeyboardActions();
  this.setSize(new Dimension(800, 600));
    this.addKeyListener(new DialogAttributeSetup_this_keyAdapter(this));
//  this.setLayout(borderLayout1);
  this.getContentPane().setLayout(borderLayout1);
  jPanel1.setBackground(new Color(121, 152, 198));
  jPanel1.setPreferredSize(new Dimension(800, 50));
  jPanel2.setPreferredSize(new Dimension(800, 150));
  jPanel2.setLayout(borderLayout2);
  jPanel3.setPreferredSize(new Dimension(800, 400));
  btnCancel.setToolTipText("Cancel (F4 or ESC)");
    btnCancel.setText("<html><center><b>Cancel </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4<" +
    "/html>");
  btnCancel.addActionListener(new DialogAttributeSetup_btnCancel_actionAdapter(this));
  btnDelete.setText("Delete (F3)");
  btnDelete.addActionListener(new DialogAttributeSetup_btnDelete_actionAdapter(this));
  btnAdd.setText("Add (F2)");
  btnAdd.addActionListener(new DialogAttributeSetup_btnAdd_actionAdapter(this));
  btnDone.setText("Done (F1)");
  btnDone.addActionListener(new DialogAttributeSetup_btnDone_actionAdapter(this));
  jPanel4.setPreferredSize(new Dimension(50, 150));
  jPanel5.setPreferredSize(new Dimension(300, 150));
  jPanel5.setLayout(borderLayout3);
  jPanel6.setPreferredSize(new Dimension(420, 150));
  jPanel7.setPreferredSize(new Dimension(100, 150));
  jPanel8.setPreferredSize(new Dimension(200, 150));
  lblColorSize.setFont(new java.awt.Font("Dialog", 1, 12));
  lblColorSize.setPreferredSize(new Dimension(90, 20));
  lblColorSize.setText("Color:");
  lblProdGroup.setFont(new java.awt.Font("Dialog", 1, 12));
  lblProdGroup.setPreferredSize(new Dimension(90, 20));
  lblProdGroup.setText("Product Group:");
  lblAttribute.setFont(new java.awt.Font("Dialog", 1, 12));
  lblAttribute.setPreferredSize(new Dimension(90, 20));
  lblAttribute.setText("Attribute:");
  txtColorSize.setFont(new java.awt.Font("Dialog", 1, 12));
  txtColorSize.setPreferredSize(new Dimension(200, 20));
    txtColorSize.addKeyListener(new DialogAttributeSetup_txtColorSize_keyAdapter(this));
//    txtColorSize.addFocusListener(new PanelAttributeSetup_txtColorSize_focusAdapter(this));
//    txtColorSize.addKeyListener(new PanelAttributeSetup_txtColorSize_keyAdapter(this));
  cboAttr.setFont(new java.awt.Font("Dialog", 1, 12));
  cboAttr.setPreferredSize(new Dimension(200, 20));
    cboAttr.addKeyListener(new DialogAttributeSetup_cboAttr_keyAdapter(this));
  cboAttr.addActionListener(new DialogAttributeSetup_cboAttr_actionAdapter(this));
  cboProdGroup.setFont(new java.awt.Font("Dialog", 1, 12));
  cboProdGroup.setPreferredSize(new Dimension(200, 20));
    cboProdGroup.addKeyListener(new DialogAttributeSetup_cboProdGroup_keyAdapter(this));
  jScrollPane1.setPreferredSize(new Dimension(790, 390));
  table.addKeyListener(new DialogAttributeSetup_table_keyAdapter(this));
    this.getContentPane().add(jPanel1,  BorderLayout.NORTH);
  this.getContentPane().add(jPanel2,  BorderLayout.CENTER);
  //    getContentPane().add(panel1);
  jPanel2.add(jPanel4,  BorderLayout.WEST);
  jPanel2.add(jPanel5,  BorderLayout.CENTER);
  jPanel5.add(jPanel7,  BorderLayout.WEST);
  jPanel7.add(lblAttribute, null);
  jPanel7.add(lblProdGroup, null);
  jPanel7.add(lblColorSize, null);
  jPanel5.add(jPanel8,  BorderLayout.CENTER);
  jPanel2.add(jPanel6,  BorderLayout.EAST);
  this.getContentPane().add(jPanel3, BorderLayout.SOUTH);
  jPanel3.add(jScrollPane1, null);
  jScrollPane1.getViewport().add(table, null);
  jPanel1.add(btnDone, null);
  jPanel1.add(btnAdd, null);
  jPanel1.add(btnDelete, null);
  jPanel1.add(btnCancel, null);
  jPanel8.add(cboAttr, null);
  jPanel8.add(cboProdGroup, null);
  jPanel8.add(txtColorSize, null);
  dm.addColumn("Attribute");
  dm.addColumn("Product Group");
  dm.addColumn("Color/Size");

}

//apply employee's permission for this screen
public void applyPermission() {
  EmpRight er = new EmpRight();
  er.initData(DAL, DAL.getEmpID());
  er.setData(DAL.getEmpID(), Constant.SCRS_ATTRIBUTE_SETUP);
  btnAdd.setEnabled(er.getAdd());
  btnDelete.setEnabled(er.getDelete());
}


//Show data
void showdata(){
//    setOpenConnection();
  getAttrDtlID();
  showCboAttr();
  showCboProdGroup();
}
//show combo attribute data
void showCboAttr(){
  rs = null;
  try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  try{
    String sql = "select attr_id, attr_desc from btm_im_attr where attr_id <> -1";
//    System.out.println(sql);
    rs = DAL.executeScrollQueries(sql,stmt);
    cboAttr.setData(rs);
    rs.close();
    stmt.close();
  }catch(Exception e){

  }
  try {
    stmt.close();
  }
  catch (Exception ex) {
    ex.printStackTrace();
  }

}
//show combo product group
void showCboProdGroup(){
  ResultSet rs = null;
  try{
    String sql = "select child_id, child_name from btm_im_prod_hir where  recd_curr_flag = 1 and child_id in (select dept_id from BTM_IM_MAP_DEPARTMENT)";
//    System.out.println(sql);
    rs = DAL.executeScrollQueries(sql);
    cboProdGroup.setData(rs);
    rs.getStatement().close();

  }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);

  }

}
//get attr_dtl_id
void getAttrDtlID(){
  rs = null;
  try{
    String sql = "";
     stmt = DAL.getConnection().createStatement();
    sql = "select attr_dtl_id from btm_im_attr_dtl order by attr_dtl_id desc";
//    System.out.println(sql);
    rs = DAL.executeQueries(sql,stmt);
    if (rs.next()){
      this.attrDtlID = rs.getString("attr_dtl_id");
      this.attrDtlID = ut.getYeroCode(Integer.parseInt(this.attrDtlID)+1,4);
    }else {
      this.attrDtlID = "0001";
    }
    rs.close();
    stmt.close();
  }catch(Exception e){
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
  }

}
//Reset data
void resetData(){
//    cboAttr.setSelectedIndex(0);
//    cboProdGroup.setSelectedIndex(0);
//    lblColorSize.setText("Color:");
  txtColorSize.setText("");
}
void cboAttr_actionPerformed(ActionEvent e) {
  if (cboAttr.getSelectedIndex() == 0){
    lblColorSize.setText("Color:");
  }else{
    lblColorSize.setText("Size:");
  }
}
private boolean checkExistInDatabase(String attrID, String prod_group_id, String attrDtlDesc){
  boolean result = false;
  String sqlStr = "Select Attr_Dtl_Id From BTM_IM_ATTR_DTL Where Attr_id='" + attrID
      + "' and lower(Attr_Dtl_Desc)=lower('" + attrDtlDesc+ "') and Prod_Group_Id='" + prod_group_id + "'";
  try{
    stmt = DAL.getConnection().createStatement(ResultSet.
                                             TYPE_SCROLL_INSENSITIVE,
                                             ResultSet.CONCUR_READ_ONLY);
    rs =  DAL.executeScrollQueries(sqlStr,stmt);
    if(rs.next()){
      result = true;
    }
    rs.close();
    stmt.close();
  }catch(Exception ex){};

  return result;
}
private boolean checkExistOnTable(String attrID, String prod_group_id, String attrDtlDesc){
  for(int i=0;i<table.getRowCount() ; i++){
    String arrId[] = (String [])vId.get(i);
    if( attrID.equals(arrId[0]) && prod_group_id.equals(arrId[1])
       && attrDtlDesc.equals(table.getValueAt(i,2))){
       return true;
    }
  }
  return false;
}

void btnAdd_actionPerformed(ActionEvent e) {
  String attrID = cboAttr.getSelectedData();
  String prod_group_id = cboProdGroup.getSelectedData();
  String attrDtlDesc = txtColorSize.getText().trim();
  if (attrDtlDesc.equals("")) {
        ut.showMessage(frmMain, "Warning", "Color/Size is not null");
        txtColorSize.requestFocus(true);
        return;
    }

  if(checkExistInDatabase(attrID,prod_group_id,attrDtlDesc)){
    ut.showMessage(frmMain,"Warning!","This attribute is already existed.");
    txtColorSize.requestFocus(true);
    return;
  }
  if (checkExistOnTable(attrID, prod_group_id, attrDtlDesc)) {
    ut.showMessage(frmMain, "Warning!", "This attribute is already existed.");
    txtColorSize.requestFocus(true);
    return;
  }
  String sql = "insert into btm_im_attr_dtl values('" + this.attrDtlID +
      "','" + attrID + "','" + attrDtlDesc + "',to_date('" + ut.getSystemDate() +
      "','" + ut.DATE_FORMAT + "'),'" + DAL.getemployeeID() + "',to_date('" + ut.getSystemDate() +
      "','" + ut.DATE_FORMAT + "'),'" + prod_group_id + "')";
//  System.out.println(sql);
  vSql.addElement(sql);
  String arrID[] = {attrID,prod_group_id};
  vId.add(arrID);
  this.attrDtlID = ut.getYeroCode(Integer.parseInt(this.attrDtlID) + 1, 4);
  String attrData = cboAttr.getSelectedItem().toString();
  String prodGroupData = cboProdGroup.getSelectedItem().toString();
  String colorOrSize = txtColorSize.getText();
  Object[] rowData = new Object[]{
      attrData, prodGroupData, colorOrSize
  };
  dm.addRow(rowData);
//  stripedTable.installInTable(table, Color.lightGray, Color.white,Color.white, Color.black);
  float[] f1 = new float[3];
  Color.RGBtoHSB(255,255,230,f1);
  stripedTable.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

  //set focus for last row
   if(table.getRowCount()>0){
     table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
   }

   //show current row
   Rectangle bounds = table.getCellRect(table.getRowCount()-1,0,true);
   jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

  resetData();
  txtColorSize.requestFocus(true);
}

//  void txtColorSize_keyTyped(KeyEvent e) {
//    ut.limitLenjTextField(e,txtColorSize, 120);
//    if (lblColorSize.getText().equalsIgnoreCase("Size:")){
//      ut.checkNumber(e);
//      char key = e.getKeyChar();
//      if (ut.isDoubleString("" + key) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE || key == '.'){
//        return;
//      }else{
//        e.consume();
//      }
//    }
//  }

void btnDone_actionPerformed(ActionEvent e) {
  try {
    stmt = DAL.getConnection().createStatement();
  }
  catch (Exception ex) {
    ex.printStackTrace();
  }

  if (!vSql.isEmpty()){
    DAL.setBeginTrans(DAL.getConnection());
    DAL.executeBatchQuery(vSql,stmt);
    DAL.setEndTrans(DAL.getConnection());
    vSql.clear();
    vId.clear();
  }
  resetData();
  cboAttr.setSelectedIndex(0);
  cboProdGroup.setSelectedIndex(0);
  lblColorSize.setText("Color:");
  while (table.getRowCount()>0){
    dm.removeRow(0);
  }
  try {
    stmt.close();

  }
  catch (Exception ex) {
    ex.printStackTrace();
  }
  refreshData();
//  frmMain.pnlItemSetup.set

  frmMain.pnlItemSetup.updateNewData();
  this.dispose();
}
private void refreshData(){
  vSql.clear();
  vId.clear();
  resetData();
  cboAttr.setSelectedIndex(0);
  cboProdGroup.setSelectedIndex(0);
  lblColorSize.setText("Color:");
  while(table.getRowCount()>0){
    dm.removeRow(0);
  }
}

void btnCancel_actionPerformed(ActionEvent e) {
  refreshData();
//  isOK=false;
  this.dispose();


}

void btnDelete_actionPerformed(ActionEvent e) {
  int row = table.getSelectedRow();
  if(row >=0){
    vSql.removeElementAt(row);
    vId.removeElementAt(row);
    dm.removeRow(row);
  }
}

void btnView_actionPerformed(ActionEvent e) {
  refreshData();
  DialogAttributeView dlgAttributeView = new DialogAttributeView(
      frmMain,
      lang.getString("TT0079"), true, frmMain);
  dlgAttributeView.showAttribute();
  dlgAttributeView.cboAttr.setSelectedIndex(0);
  dlgAttributeView.setLocation(112, 85);
  dlgAttributeView.setVisible(true);
  this.dispose();
}

//  void txtColorSize_focusLost(FocusEvent e) {
//    String size = txtColorSize.getText().trim();
//    if (lblColorSize.getText().equalsIgnoreCase("Size:")){
//      String result = "";
//      int tempDot = 0;
//      int i = 0;
//      for (i = 0; i<size.length(); i++){
//        if (size.substring(i,i+1).equalsIgnoreCase(".")){
//            tempDot ++;
//        }
//        if (tempDot >= 2){
//          break;
//        }
//      }
//      result = size.substring(0,i);
//      txtColorSize.setText("" + Double.parseDouble(result));
//    }
//  }
private void registerKeyboardActions() {
   /// set up the maps:
   InputMap inputMap = new InputMap();
   inputMap.setParent(jPanel1.getInputMap(JComponent.
                                          WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
   ActionMap actionMap = jPanel1.getActionMap();


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

   // F4
   key = new Integer(KeyEvent.VK_F4);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), key);
   actionMap.put(key, new KeyAction(key));

   // ESCAPE
   key = new Integer(KeyEvent.VK_ESCAPE);
   inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
   actionMap.put(key, new KeyAction(key));
   jPanel1.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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
          btnDone.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F2) {
          btnAdd.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F3) {
          btnDelete.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F4 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
      }
    }

    private void catchHotKey(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }
      else if (e.getKeyCode() == KeyEvent.VK_F2 ) {
        btnAdd.doClick();
      }
      else if (e.getKeyCode() == KeyEvent.VK_F3) {
        btnDelete.doClick();
      }
      else if (e.getKeyCode() == KeyEvent.VK_F4 ||
               e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        btnCancel.doClick();
      }

}

  void cboAttr_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void cboProdGroup_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtColorSize_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }


}

class DialogAttributeSetup_cboAttr_actionAdapter implements java.awt.event.ActionListener {
DialogAttributeSetup adaptee;

DialogAttributeSetup_cboAttr_actionAdapter(DialogAttributeSetup adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.cboAttr_actionPerformed(e);
}
}

class DialogAttributeSetup_btnAdd_actionAdapter implements java.awt.event.ActionListener {
DialogAttributeSetup adaptee;

DialogAttributeSetup_btnAdd_actionAdapter(DialogAttributeSetup adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.btnAdd_actionPerformed(e);
}
}

//class PanelAttributeSetup_txtColorSize_keyAdapter extends java.awt.event.KeyAdapter {
//  PanelAttributeSetup adaptee;
//
//  PanelAttributeSetup_txtColorSize_keyAdapter(PanelAttributeSetup adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void keyTyped(KeyEvent e) {
//    adaptee.txtColorSize_keyTyped(e);
//  }
//}

class DialogAttributeSetup_btnDone_actionAdapter implements java.awt.event.ActionListener {
DialogAttributeSetup adaptee;

DialogAttributeSetup_btnDone_actionAdapter(DialogAttributeSetup adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.btnDone_actionPerformed(e);
}
}

class DialogAttributeSetup_btnCancel_actionAdapter implements java.awt.event.ActionListener {
DialogAttributeSetup adaptee;

DialogAttributeSetup_btnCancel_actionAdapter(DialogAttributeSetup adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.btnCancel_actionPerformed(e);
}
}

class DialogAttributeSetup_btnDelete_actionAdapter implements java.awt.event.ActionListener {
DialogAttributeSetup adaptee;

DialogAttributeSetup_btnDelete_actionAdapter(DialogAttributeSetup adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.btnDelete_actionPerformed(e);
}
}

//class PanelAttributeSetup_txtColorSize_focusAdapter extends java.awt.event.FocusAdapter {
//  PanelAttributeSetup adaptee;
//
//  PanelAttributeSetup_txtColorSize_focusAdapter(PanelAttributeSetup adaptee) {
//    this.adaptee = adaptee;
//  }
//  public void focusLost(FocusEvent e) {
//    adaptee.txtColorSize_focusLost(e);
//  }
//}

class DialogAttributeSetup_btnView_actionAdapter implements java.awt.event.ActionListener {
DialogAttributeSetup adaptee;

DialogAttributeSetup_btnView_actionAdapter(DialogAttributeSetup adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.btnView_actionPerformed(e);
}
}

class DialogAttributeSetup_cboAttr_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeSetup adaptee;

  DialogAttributeSetup_cboAttr_keyAdapter(DialogAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboAttr_keyPressed(e);
  }
}

class DialogAttributeSetup_cboProdGroup_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeSetup adaptee;

  DialogAttributeSetup_cboProdGroup_keyAdapter(DialogAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboProdGroup_keyPressed(e);
  }
}

class DialogAttributeSetup_txtColorSize_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeSetup adaptee;

  DialogAttributeSetup_txtColorSize_keyAdapter(DialogAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtColorSize_keyPressed(e);
  }
}

class DialogAttributeSetup_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeSetup adaptee;

  DialogAttributeSetup_table_keyAdapter(DialogAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogAttributeSetup_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeSetup adaptee;

  DialogAttributeSetup_this_keyAdapter(DialogAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}
