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
 * <p>Description: Main -> Merch Mgmt -> Attribute Setup</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelAttributeSetup extends JPanel {
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
  BJButton btnView = new BJButton();
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


  public PanelAttributeSetup(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setSize(new Dimension(795, 600));
    this.setLayout(borderLayout1);
    jPanel1.setBackground(new Color(121, 152, 198));
    jPanel1.setPreferredSize(new Dimension(800, 50));
    jPanel2.setPreferredSize(new Dimension(800, 150));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(800, 400));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelAttributeSetup_btnCancel_actionAdapter(this));
    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setText(lang.getString("Delete")+" (F8)");
    btnDelete.addActionListener(new PanelAttributeSetup_btnDelete_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setText(lang.getString("Add")+" (F2)");
    btnAdd.addActionListener(new PanelAttributeSetup_btnAdd_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setText(lang.getString("Done")+" (F1)");
    btnDone.addActionListener(new PanelAttributeSetup_btnDone_actionAdapter(this));
    btnView.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnView.setText(lang.getString("View")+" (F5)");
    btnView.addActionListener(new PanelAttributeSetup_btnView_actionAdapter(this));
    btnView.setToolTipText(lang.getString("View")+" (F5)");
    jPanel4.setPreferredSize(new Dimension(50, 150));
    jPanel5.setPreferredSize(new Dimension(300, 150));
    jPanel5.setLayout(borderLayout3);
    jPanel6.setPreferredSize(new Dimension(420, 150));
    jPanel7.setPreferredSize(new Dimension(100, 150));
    jPanel8.setPreferredSize(new Dimension(200, 150));
    lblColorSize.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblColorSize.setPreferredSize(new Dimension(90, 20));
    lblColorSize.setText(lang.getString("ColorSize")+":");
    lblProdGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblProdGroup.setPreferredSize(new Dimension(90, 20));
    lblProdGroup.setText(lang.getString("ProGroup")+":");
    lblAttribute.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblAttribute.setPreferredSize(new Dimension(90, 20));
    lblAttribute.setText(lang.getString("Attribute")+":");
    txtColorSize.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtColorSize.setPreferredSize(new Dimension(200, 20));
//    txtColorSize.addFocusListener(new PanelAttributeSetup_txtColorSize_focusAdapter(this));
//    txtColorSize.addKeyListener(new PanelAttributeSetup_txtColorSize_keyAdapter(this));
    cboAttr.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboAttr.setPreferredSize(new Dimension(200, 20));
    cboAttr.addActionListener(new PanelAttributeSetup_cboAttr_actionAdapter(this));
    cboProdGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboProdGroup.setPreferredSize(new Dimension(200, 20));
    jScrollPane1.setPreferredSize(new Dimension(790, 390));
    table.addKeyListener(new PanelAttributeSetup_table_keyAdapter(this));
    this.add(jPanel1,  BorderLayout.NORTH);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel4,  BorderLayout.WEST);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel7,  BorderLayout.WEST);
    jPanel7.add(lblAttribute, null);
    jPanel7.add(lblProdGroup, null);
    jPanel7.add(lblColorSize, null);
    jPanel5.add(jPanel8,  BorderLayout.CENTER);
    jPanel2.add(jPanel6,  BorderLayout.EAST);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    jPanel1.add(btnDone, null);
    jPanel1.add(btnAdd, null);
    jPanel1.add(btnView, null);
    jPanel1.add(btnDelete, null);
    jPanel1.add(btnCancel, null);
    jPanel8.add(cboAttr, null);
    jPanel8.add(cboProdGroup, null);
    jPanel8.add(txtColorSize, null);
    dm.addColumn(lang.getString("ColorSize"));
    dm.addColumn(lang.getString("ProGroup"));
    dm.addColumn(lang.getString("Attribute"));
//    getAttrDtlID();
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
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
      cboAttr.setData(rs);
      rs.getStatement().close();
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

    rs = null;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      String sql = "select child_id, child_name from btm_im_prod_hir where  recd_curr_flag = 1 and child_id in (select dept_id from BTM_IM_MAP_DEPARTMENT)";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
      cboProdGroup.setData(rs);
      rs.close();
      stmt.close();
    }catch(Exception e){

    }

  }
  //get attr_dtl_id
  void getAttrDtlID(){

    rs = null;
    try{
      String sql = "";
       stmt = DAL.getConnection().createStatement();
      sql = "select attr_dtl_id from btm_im_attr_dtl order by attr_dtl_id desc";
//      System.out.println(sql);
      rs = DAL.executeQueries(sql,stmt);
      if (rs.next()){
        this.attrDtlID = rs.getString("attr_dtl_id");
        this.attrDtlID = ut.getYeroCode(Integer.parseInt(this.attrDtlID)+1,4);
      }else {
        this.attrDtlID = "0001";
      }
      rs.getStatement().close();
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
      lblColorSize.setText(lang.getString("Color")+":");
    }else{
      lblColorSize.setText(lang.getString("Size")+":");
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
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1003_ColorOrSizeIsNotNull"));
          txtColorSize.requestFocus(true);
          return;
      }

    if(checkExistInDatabase(attrID,prod_group_id,attrDtlDesc)){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1004_ThisAttributeIsAlreadyExisted"));
      txtColorSize.requestFocus(true);
      return;
    }
    if (checkExistOnTable(attrID, prod_group_id, attrDtlDesc)) {
      ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1004_ThisAttributeIsAlreadyExisted"));
      txtColorSize.requestFocus(true);
      return;
    }
    String sql = "insert into btm_im_attr_dtl values('" + this.attrDtlID +
        "','" + attrID + "','" + attrDtlDesc + "',to_date('" + ut.getSystemDate() +
        "','" + ut.DATE_FORMAT + "'),'" + DAL.getemployeeID() + "',to_date('" + ut.getSystemDate() +
        "','" + ut.DATE_FORMAT + "'),'" + prod_group_id + "')";
//    System.out.println(sql);
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
//    stripedTable.installInTable(table, Color.lightGray, Color.white,Color.white, Color.black);
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    stripedTable.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);

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
    lblColorSize.setText(lang.getString("Color")+":");
    while (table.getRowCount()>0){
      dm.removeRow(0);
    }
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showMerchanSystemButton();
    frmMain.setTitle(lang.getString("TT0002"));
    try {
      stmt.close();

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }


  }
  private void refreshData(){
    vSql.clear();
    vId.clear();
    resetData();
    cboAttr.setSelectedIndex(0);
    cboProdGroup.setSelectedIndex(0);
    lblColorSize.setText(lang.getString("Color")+":");
    while(table.getRowCount()>0){
      dm.removeRow(0);
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    refreshData();
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showMerchanSystemButton();
    frmMain.setTitle(lang.getString("TT0001"));

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
      frmMain.showScreen(Constant.SCRS_ATTRIBUTE_VIEW);
      frmMain.pnlAttrView.modifyStatus = false;
      frmMain.pnlAttrView.showAttribute();
      frmMain.pnlAttrView.cboAttr.setSelectedIndex(0);
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

    // F4
    key = new Integer(KeyEvent.VK_F4);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), key);
    actionMap.put(key, new KeyAction(key));

    // F5
    key = new Integer(KeyEvent.VK_F5);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
    actionMap.put(key, new KeyAction(key));

    // F5
    key = new Integer(KeyEvent.VK_F5);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
    actionMap.put(key, new KeyAction(key));
    // F6
    key = new Integer(KeyEvent.VK_F6);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), key);
    actionMap.put(key, new KeyAction(key));
    // F7
    key = new Integer(KeyEvent.VK_F7);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), key);
    actionMap.put(key, new KeyAction(key));
    // F8
    key = new Integer(KeyEvent.VK_F8);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), key);
    actionMap.put(key, new KeyAction(key));
    // F9
    key = new Integer(KeyEvent.VK_F9);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), key);
    actionMap.put(key, new KeyAction(key));
    // F10
    key = new Integer(KeyEvent.VK_F10);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), key);
    actionMap.put(key, new KeyAction(key));
    // F11
    key = new Integer(KeyEvent.VK_F11);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), key);
    actionMap.put(key, new KeyAction(key));
    // F12
    key = new Integer(KeyEvent.VK_F12);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
    actionMap.put(key, new KeyAction(key));

    // ENTER
     key = new Integer(KeyEvent.VK_ENTER);
     inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
     actionMap.put(key, new KeyAction(key));

    // Escape
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
            btnDone.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F2) {
            btnAdd.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F8) {
            btnDelete.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F5) {
               btnView.doClick();
          }
          else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
            btnCancel.doClick();
          }
        }
      }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnAdd.doClick();
               }
  }

}

class PanelAttributeSetup_cboAttr_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeSetup adaptee;

  PanelAttributeSetup_cboAttr_actionAdapter(PanelAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboAttr_actionPerformed(e);
  }
}

class PanelAttributeSetup_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeSetup adaptee;

  PanelAttributeSetup_btnAdd_actionAdapter(PanelAttributeSetup adaptee) {
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

class PanelAttributeSetup_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeSetup adaptee;

  PanelAttributeSetup_btnDone_actionAdapter(PanelAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelAttributeSetup_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeSetup adaptee;

  PanelAttributeSetup_btnCancel_actionAdapter(PanelAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelAttributeSetup_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeSetup adaptee;

  PanelAttributeSetup_btnDelete_actionAdapter(PanelAttributeSetup adaptee) {
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

class PanelAttributeSetup_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeSetup adaptee;

  PanelAttributeSetup_btnView_actionAdapter(PanelAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelAttributeSetup_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAttributeSetup adaptee;

  PanelAttributeSetup_table_keyAdapter(PanelAttributeSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
