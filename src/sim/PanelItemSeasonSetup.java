package sim;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import common.*;
import common.ExceptionHandle;
import common.Utils;

import btm.attr.*;
import btm.swing.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Season Setup</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Vinh.Le
 * @version 1.0
 */

public class PanelItemSeasonSetup extends JPanel {
  FrameMainSim frmMain;
  Statement stmt = null;
  CardLayout cardLayout;
  JPanel parent;

  DataAccessLayer DAL = new DataAccessLayer();
  btm.business.PhasesBusObject bPhasesBusObject = new btm.business.PhasesBusObject();
  btm.business.SeasonBusObj bSeasonBusObj = new btm.business.SeasonBusObj();
  btm.business.ItemSeasonBusObj bItemSeasonBusObj = new btm.business.ItemSeasonBusObj();
  btm.business.ItemMasterBusObj  bItemMasterBusObj = new btm.business.ItemMasterBusObj();

  Utils ut = new Utils();
  String itemCode, itemName,artNo;
  Vector vItemSize = new Vector();
  Vector vItemName = new Vector();
  long seasonId,phaseId,itemSeq;
  Vector vId = new Vector();//Vector save array seasonID and phaseId of each Item
  java.sql.Date dDate;
  int rowNum = 5;
  boolean  flagReturn = false;//check exist season
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel pnlButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJButton btnDelete = new BJButton();
  BJButton btnCancel = new BJButton();
  BJButton btnSearch = new BJButton();
  BJButton btnAdd = new BJButton();
  FlowLayout flowLayout4 = new FlowLayout();
  BJButton btnModify = new BJButton();
  JPanel pnlSub = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlInfo = new JPanel();
  JPanel pnlTable = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel pnlItemDetail = new JPanel();
  Border border1;
  JLabel lblItemID = new JLabel();
  JTextField txtItemname = new JTextField();
  JTextField txtItemcode = new JTextField();
  JButton btnItemSearch = new JButton();
  JPanel pnlWest = new JPanel();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  SortableTableModel dmItemSeason = new SortableTableModel();

  JPanel pnlEast = new JPanel();
  JLabel lblItemName = new JLabel();
  FlowLayout flowLayout1 = new FlowLayout();
  BJButton btnDone = new BJButton();
  BJButton btnClear = new BJButton();
  BorderLayout borderLayout5 = new BorderLayout();
  JScrollPane scrPanelTable = new JScrollPane();
  BJTable tblItemSeasonSetup = new BJTable(dmItemSeason,true);
  boolean flagSetHotKeyForAdd = true;
  BorderLayout borderLayout4 = new BorderLayout();
//  JScrollPane panelLeft = new JScrollPane();
  JPanel panelLeft = new JPanel();
  JPanel panelButton = new JPanel();
  JPanel panelRight = new JPanel();
  JButton btnRemove = new JButton();
  JButton btnSelect = new JButton();
  BorderLayout borderLayout6 = new BorderLayout();
  BorderLayout borderLayout7 = new BorderLayout();
  JPanel panelBottom = new JPanel();
  JPanel panleTop = new JPanel();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  FlowLayout flowLayout2 = new FlowLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  BJListBox listLeft = new BJListBox();
  JScrollPane jScrollPane2 = new JScrollPane();
  BJListBox listRight = new BJListBox();
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;//default Add

  public PanelItemSeasonSetup(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
    try {
      this.frmMain = frmMain;
      this.cardLayout = crdLayout;
      this.parent = parent;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    registerKeyboardActions();
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    pnlButton.setLayout(flowLayout4);
    pnlButton.setBackground(new Color(121, 152, 198));
    pnlButton.setMinimumSize(new Dimension(750, 35));
    pnlButton.setPreferredSize(new Dimension(10, 50));
    this.setBackground(new Color(121, 152, 198));
    this.setLayout(borderLayout1);
    //btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+" (F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnDelete.addActionListener(new PanelItemSeasonSetup_btnDelete_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+" (F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
    btnCancel.addActionListener(new PanelItemSeasonSetup_btnCancel_actionAdapter(this));
    btnCancel.addMouseListener(new PanelItemSeasonSetup_btnCancel_mouseAdapter(this));
    btnSearch.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnSearch.setPreferredSize(new Dimension(120, 37));
    btnSearch.setToolTipText(lang.getString("Search")+" (F3)");
    btnSearch.setText("<html><center><b>"+lang.getString("Search")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnSearch.addActionListener(new PanelItemSeasonSetup_btnSearch_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+" (F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelItemSeasonSetup_btnAdd_actionAdapter(this));
    btnAdd.addMouseListener(new PanelItemSeasonSetup_btnAdd_mouseAdapter(this));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setPreferredSize(new Dimension(120, 37));
    btnModify.setToolTipText(lang.getString("Modify")+" (F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnModify.addActionListener(new PanelItemSeasonSetup_btnModify_actionAdapter(this));
    pnlSub.setPreferredSize(new Dimension(800, 550));
    pnlSub.setLayout(borderLayout2);
    pnlInfo.setPreferredSize(new Dimension(800, 160));
    pnlInfo.setLayout(borderLayout3);
    pnlTable.setPreferredSize(new Dimension(800, 390));
    pnlTable.setLayout(borderLayout5);
    pnlItemDetail.setLayout(null);
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setText(lang.getString("UPC")+":");
    lblItemID.setBounds(new Rectangle(34, 22, 85, 24));
    txtItemname.setBackground(SystemColor.control);
    txtItemname.setEnabled(false);
    txtItemname.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtItemname.setText("");
    txtItemname.setBounds(new Rectangle(155, 52, 156, 24));
    txtItemcode.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 11));
    txtItemcode.setSelectionStart(3);
    txtItemcode.setBounds(new Rectangle(154, 22, 156, 24));
    txtItemcode.addKeyListener(new PanelItemSeasonSetup_txtItemcode_keyAdapter(this));
    txtItemcode.addFocusListener(new PanelItemSeasonSetup_txtItemcode_focusAdapter(this));
    btnItemSearch.setText("...");
    btnItemSearch.setBounds(new Rectangle(321, 23, 35, 22));
    btnItemSearch.addActionListener(new PanelItemSeasonSetup_btnItemSearch_actionAdapter(this));
    pnlWest.setPreferredSize(new Dimension(380, 80));
    pnlWest.setLayout(borderLayout4);
    pnlEast.setPreferredSize(new Dimension(10, 50));
    pnlEast.setRequestFocusEnabled(true);
    lblItemName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemName.setToolTipText("");
    lblItemName.setBounds(new Rectangle(35, 52, 86, 24));
    lblItemName.setText(lang.getString("ItemName")+":");
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelItemSeasonSetup_btnDone_actionAdapter(this));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnClear.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnClear.setText("<html><center><b>"+lang.getString("Clear")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp" +
    ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F7</html>");
    btnClear.addActionListener(new PanelItemSeasonSetup_btnClear_actionAdapter(this));
    btnClear.setPreferredSize(new Dimension(120, 37));
    btnClear.setToolTipText(lang.getString("Clear")+" (F7)");
    showButton(true);
    panelLeft.setMinimumSize(new Dimension(10, 10));
    panelLeft.setPreferredSize(new Dimension(150, 10));
    panelLeft.setLayout(borderLayout6);
    panelRight.setMinimumSize(new Dimension(10, 10));
    panelRight.setPreferredSize(new Dimension(150, 10));
    panelRight.setLayout(borderLayout7);
    btnRemove.setPreferredSize(new Dimension(47, 25));
    btnRemove.setText("<");
    btnRemove.addActionListener(new PanelItemSeasonSetup_btnRemove_actionAdapter(this));
    btnSelect.setMaximumSize(new Dimension(47, 25));
    btnSelect.setMinimumSize(new Dimension(41, 25));
    btnSelect.setPreferredSize(new Dimension(47, 25));
    btnSelect.setHorizontalAlignment(SwingConstants.CENTER);
    btnSelect.setHorizontalTextPosition(SwingConstants.TRAILING);
    btnSelect.setText(">");
    panelButton.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.CENTER);
    listLeft.setBorder(null);
    listLeft.setOpaque(true);
    listLeft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tblItemSeasonSetup.addKeyListener(new PanelItemSeasonSetup_tblItemSeasonSetup_keyAdapter(this));
    scrPanelTable.setPreferredSize(new Dimension(790, 350));
    this.add(pnlSub, BorderLayout.CENTER);
    this.add(pnlButton,  BorderLayout.NORTH);
    pnlSub.add(pnlInfo,  BorderLayout.NORTH);
    pnlSub.add(pnlTable,  BorderLayout.CENTER);
    pnlTable.add(scrPanelTable,  BorderLayout.CENTER);
    pnlInfo.add(pnlItemDetail,  BorderLayout.CENTER);
    pnlInfo.add(pnlWest, BorderLayout.EAST);
    pnlWest.add(panelLeft,  BorderLayout.WEST);
    pnlWest.add(panelButton,  BorderLayout.CENTER);
    pnlWest.add(panelRight,  BorderLayout.EAST);
    pnlInfo.add(pnlEast, BorderLayout.WEST);
    pnlItemDetail.add(txtItemcode, null);
    pnlItemDetail.add(txtItemname, null);
    pnlItemDetail.add(btnItemSearch, null);
    pnlItemDetail.add(lblItemID, null);
    pnlItemDetail.add(lblItemName, null);
    scrPanelTable.getViewport().add(tblItemSeasonSetup, null);
    panelButton.add(btnSelect, null);
    panelButton.add(btnRemove, null);
    panelLeft.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(listLeft, null);
    pnlWest.add(panelBottom, BorderLayout.SOUTH);
    pnlWest.add(panleTop, BorderLayout.NORTH);
    panelRight.add(jPanel1, BorderLayout.EAST);
    panelRight.add(jScrollPane2,  BorderLayout.CENTER);
    jScrollPane2.getViewport().add(listRight, null);
    createConnection();
    tblItemSeasonSetup.addMouseListener(new
    PanelItemSeasonSetup_tblItemSeasonSetup_mouseAdapter(this));
    initTable();
    btnSelect.addActionListener(new PanelItemSeasonSetup_btnSelect_actionAdapter(this));
   }




  /************ INIT SCREEN************************/
   public void initScreen() {

     try{
       stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       txtItemcode.requestFocus(true);
       listLeft.setData(bSeasonBusObj.getDataIntoCombo(stmt));
       stmt.close();
     }catch(Exception ex){};
    }

   public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_ITEM_SEASON_SETUP);
    btnAdd.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnModify.setEnabled(er.getModify());
    btnSearch.setEnabled(er.getSearch());
  //  btnAdminRoleReport.setEnabled(er.getReport());
  }

  void showButton(boolean flagSetButton) {
    this.pnlButton.removeAll();
    this.pnlButton.updateUI();
    pnlButton.add(btnDone, null);
    if (flagSetButton == true)
      pnlButton.add(btnAdd, null);
    else
    pnlButton.add(btnModify, null);
    pnlButton.add(btnSearch, null);
    pnlButton.add(btnClear, null);
    pnlButton.add(btnDelete, null);
    pnlButton.add(btnCancel, null);
  }

  void initTable() {
    //define for table
    String[] columnNames = new String[] {
        lang.getString("ItemID"), lang.getString("UPC"), lang.getString("Name"), lang.getString("Size"), lang.getString("SeasonName")};
    dmItemSeason.setDataVector(new Object[][] {}, columnNames);
    tblItemSeasonSetup.setColumnWidth(0, 120);
    tblItemSeasonSetup.setColumnWidth(1, 120);
    tblItemSeasonSetup.setColumnWidth(2, 120);
    tblItemSeasonSetup.setColumnWidth(4, 180);
    tblItemSeasonSetup.setColor(Color.lightGray, Color.white);
 }
  private ResultSet getDataSupplierSearch(String sql) {
    ResultSet rs = null;
    try {
//      System.out.println(sql);
        rs = DAL.executeScrollQueries(sql);
      }catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      return rs;
  }/////end method

  /*
   * CREATE CONNECTION  INTO DATABASE
   * @author 			Hieu.Pham
   *@param  :   no
   *@return :   no
   */
  private void createConnection(){
    this.bItemSeasonBusObj.setDataAccessLayer(DAL);
    this.bSeasonBusObj.setDataAccessLayer(DAL);
    this.bPhasesBusObject .setDataAccessLayer(DAL);
    this.bItemMasterBusObj.setDataAccessLayer(DAL) ;
  }

  private void resetDataInScreen(){
    this.txtItemcode .setText("") ;
    this.txtItemname .setText("") ;
    this.txtItemcode.requestFocus(true);
  }

  /*
   * SUB FUNCTION CHECK DATA
   * @author 			Hieu.Pham
   *@param  :   no
   *@return :   boolean
   *true : input is true
   *                    false: input is false
   */
  private boolean checkDataInScreen(){
    try{
      this.itemCode = this.txtItemcode.getText().trim();
      if ( (itemCode.length() <= 0)) {
        ut.showMessage(this.frmMain,lang.getString("TT013"),lang.getString("MS1108_LengthUPC_CanNotLower0Character"));
        this.txtItemcode.requestFocus(true) ;
        return false;
      }
      else if (itemCode.length() > 6) {
        ut.showMessage(this.frmMain,lang.getString("TT013"),lang.getString("MS1109_LengthUPC_CanNotGreater6Character"));
        this.txtItemcode.requestFocus(true) ;
        return false;
      }
      else if(listRight.getRowCount()==0){
        ut.showMessage(this.frmMain,lang.getString("TT013"),lang.getString("MS1110_SelectSeason"));
        return false;
      }

    return true;
    }catch(Exception e){
        JOptionPane.showMessageDialog(this.frmMain, e.toString() , lang.getString("TT013"), JOptionPane.ERROR_MESSAGE);
        return false;
    }
  }
  Vector checkArtNo(){
    Vector vSql = new Vector();
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "select distinct im.item_id, im.item_name, ph.ATTR2_NAME from btm_im_item_master im, btm_im_price_hist ph where ph.item_id = im.item_id and art_no = '" + txtItemcode.getText() + "' and im.status <> 'D' and ph.status = '1'";
//    System.out.println(sql);
    try{
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      vSql.clear();
      vItemSize.clear();
      vItemName.clear();
      while (rs.next()){
        vSql.addElement(rs.getString("item_id"));
        vItemName.addElement(rs.getString("item_name"));
        vItemSize.addElement(rs.getString("attr2_name"));
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return vSql;
  }
  /*
   * SUB FUNCTION ADD DATA
   * @author 			Hieu.Pham
   *@param  :   no
   */
  private void addDataInScreen(){
    if (!checkDataInScreen()){
      return;
    }
    String seasonId="";
    Vector vItemID = new Vector();
    vItemID = checkArtNo();
    if (vItemID.isEmpty()){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS349_UPCNotExist"));
      txtItemcode.requestFocus(true);
      return;
    }
    String seasonExistName="";
    for(int i=0;i<listRight.getRowCount();i++){
      listRight.setSelectedIndex(i);
//      if (bItemSeasonBusObj.checkDataExist(txtItemcode.getText(), listRight.getSelectedData(), DAL) ||
//          checkItemExistOnTableAdd()) {
      if (checkItemExistOnTableAdd()) {
        seasonExistName += listRight.getSelectedValue().toString() + ", ";
        listLeft.addData(listRight.getSelectedValues(),
                         listRight.getSelectedDatas());
        listRight.removeIndex(listRight.getSelectedIndices());
        flagReturn = true;
        i--;
      }
    }
    if(flagReturn == true){
        seasonExistName = seasonExistName.substring(0,seasonExistName.length()-2);
        ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1112_ItemIsExistedForSeason") + seasonExistName);
         flagReturn = false;
        return;
    }
    String sSeasonName="";
    String arrID="";
    try {
      for(int i=0;i<listRight.getRowCount();i++){
        listRight.setSelectedIndex(i);
        sSeasonName += listRight.getSelectedValue() + ";";
        arrID += listRight.getSelectedData() + ";";
      }
      sSeasonName = sSeasonName.substring(0,sSeasonName.length()-1);
//      vId.add(arrID);
      for(int i=0; i<vItemID.size(); i++){
        vId.add(arrID);
        dmItemSeason.addRow(new Object[] {vItemID.elementAt(i), txtItemcode.getText(),
                            vItemName.elementAt(i), vItemSize.elementAt(i), sSeasonName});
      }
      //set focus for last row
       if(tblItemSeasonSetup.getRowCount()>0){
         tblItemSeasonSetup.setRowSelectionInterval(tblItemSeasonSetup.getRowCount()-1, tblItemSeasonSetup.getRowCount()-1);
       }

       //show current row
       Rectangle bounds = tblItemSeasonSetup.getCellRect(tblItemSeasonSetup.getRowCount()-1,0,true);
       jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

      resetDataInScreen();
      RemoveAllToLeft();
    }
    catch (Exception e) {
        DAL.setRollback(DAL.getConnection() ) ;
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }
  }

  private String getSizeName(String sizeId){
    String name = "";
    ResultSet rs = null;
    String sqlStr = "Select Attr_Dtl_Desc From BTM_IM_ATTR_DTL Where Attr_dtl_id = '" + sizeId + "'";
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                               TYPE_SCROLL_INSENSITIVE,
                                               ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
      if(rs.next()){
        name = rs.getString("Attr_Dtl_Desc");
      }
      rs.close();
      stmt.close();
    }catch(Exception ex){};
    return name;
  }
  /*
   * SUB FUNCTION UPDATE INFOMATION IN SCREEN BASE
   * @author 			Hieu.Pham
   *@param  :   String itemCode
   *@return :   no
   */
  void updateValueInScreen(String itemCode){
    ResultSet rs= null;
    try {
          rs = (ResultSet)this.bItemMasterBusObj.selectInfoItemMaster(itemCode);
        if(rs.next()) {
          txtItemname.setText(rs.getString("ITEM_NAME"));
          artNo = rs.getString("Art_No");
          String sizeId = rs.getString("Attr2_Dtl_Id");
          if(sizeId == null || sizeId.trim().equals("")){
//           itemSize = "";
          }
          else{
//            itemSize = getSizeName(sizeId);
          }

        }
        else{
          ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1111_ItemValueDoesNotExistInDatabase"));
          txtItemcode.setText("");
          txtItemname.setText("");
          txtItemcode.requestFocus();
        }
        rs.getStatement().close();
    }catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
  }

  /*
   * SUB FUNCTION UPDATE INFOMATION IN SCREEN
   * @author 			Hieu.Pham
   *@param  :   no
   *@return :   no
   */
  void updateValueFromSelecledItemInTable(){

    int row=tblItemSeasonSetup.getSelectedRow();
    if(row >= 0){
      this.txtItemcode.setText(tblItemSeasonSetup.getValueAt(row, 1).toString());
      this.txtItemname.setText(tblItemSeasonSetup.getValueAt(row, 2).toString());
      String seasonName =  tblItemSeasonSetup.getValueAt(row,4).toString() + ";";
      String arrName[] =seasonName.split(";");
      String seasonId = vId.get(row).toString();
      String arrId[] = seasonId.split(";");
      listRight.addData(arrName,arrId);
      for(int i=0;i<arrName.length;i++){
        listLeft.setSelectedValue(arrName[i],true);
        listLeft.removeIndex(listLeft.getSelectedIndices());
      }
      showButton(false);//show Modify; hide Add
      flagSetHotKeyForAdd = false;
      txtItemcode.requestFocus(true);
    }
  }

  void txtItemcode_focusLost(FocusEvent e) {
//    if (this.txtItemcode.getText().length() > 0) {
//      updateValueInScreen(this.txtItemcode.getText());
//    }
  }



  void btnItemSearch_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0095"),true, frmMain,itemBusObject);
    dlgItemLookup.setVisible(true);
    if (dlgItemLookup.done){
      txtItemcode.setText(dlgItemLookup.getUPC());
      updateValueInScreen(txtItemcode.getText().trim());
    }
  }

    void txtItemcode_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtItemcode,ItemMaster.LEN_ITEM_ARTNO);
      if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
          (e.getKeyChar() != e.VK_BACK_SPACE))
        e.consume();
    }

    void txtItemSeasonSeq_keyTyped(KeyEvent e) {

    }

    void txtItemname_keyTyped(KeyEvent e) {
      ut.limitLenjTextField(e,txtItemname,ItemMaster.LEN_ITEM_NAME);
    }

  void btnCancel_actionPerformed(ActionEvent e) {
    listRight.removeIndexAll();
    vId.removeAllElements();
    showButton(true);
    flagSetHotKeyForAdd = true;
    returnPreviousScreen();
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
          if(flagSetHotKeyForAdd==true){
            btnAdd.doClick();
          }
        }
        else if (identifier.intValue() == KeyEvent.VK_F3) {
             btnSearch.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F4) {
          if(flagSetHotKeyForAdd==false){
            btnModify.doClick();
          }
        }
        else if (identifier.intValue() == KeyEvent.VK_F7) {
          btnClear.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F8) {
             btnDelete.doClick();
        }
        else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnCancel.doClick();
        }
      }
    }

  void btnAdd_actionPerformed(ActionEvent e) {
   this.addDataInScreen();
  }
  boolean checkItemExistOnTableAdd(){
     for(int i=0;i<tblItemSeasonSetup.getRowCount();i++){
      String seasonId =  vId.get(i).toString() + ";";
      String arrId[] = seasonId.split(";");
      for(int j=0;j<arrId.length ;j++){
        for(int k=0;k<listRight.getRowCount();k++){
          listRight.setSelectedIndex(k);
          if (txtItemcode.getText().equals(tblItemSeasonSetup.getValueAt(i, 0).
                                           toString()) &&
              arrId[j].equals(listRight.getSelectedData()))
            return true;
        }
      }
    }
    return false;
  }

  boolean checkItemExistOnTableModify(int rowSelected) {
    for (int i = 0; i < tblItemSeasonSetup.getRowCount(); i++) {
      String seasonId =  vId.get(i).toString() + ";";
    String arrId[] = seasonId.split(";");
    for(int j=0;j<arrId.length ;j++){
      for(int k=0;k<listRight.getRowCount();k++){
        listRight.setSelectedIndex(k);
        if (rowSelected!=i && txtItemcode.getText().equals(tblItemSeasonSetup.getValueAt(i, 0).
                                         toString()) &&
            arrId[j].equals(listRight.getSelectedData()))
          return true;
      }
    }
    }
    return false;
  }

  void btnDone_actionPerformed(ActionEvent e) {
    int rows = dmItemSeason.getRowCount();
    if (rows > 0) {
      String last_upd_id = DAL.getEmpID();
      bItemSeasonBusObj.setDataAccessLayer(DAL);
      try{
         stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      }catch(Exception ex){};
      for (int i = 0; i < rows; i++) {
        itemCode = dmItemSeason.getValueAt(i,0).toString();
        String seasonId = vId.get(i).toString();
        String arrId[] = seasonId.split(";");
        //arrId[0]: seasonId
        //arrId[1]: phaseId
        for(int j=0;j<arrId.length;j++){
          bItemSeasonBusObj.deleteItemSeason(itemCode,arrId[j],stmt);
          bItemSeasonBusObj.insertItemSeason(itemCode, arrId[j], last_upd_id,
                                             stmt);
        }
      }
      try {
        stmt.close();
      }
      catch (Exception ex) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);}

      vId.removeAllElements();
    }
    showButton(true);
    flagSetHotKeyForAdd = true;
    returnPreviousScreen();
  }
  private void returnPreviousScreen(){
   resetDataInScreen();
   resetDataOnTable();
   frmMain.showScreen(Constant.SCRS_MAIN_SIM);
   frmMain.setTitle(lang.getString("TT0001"));
  }
  private void resetDataOnTable(){
    while(dmItemSeason.getRowCount()>0){
      dmItemSeason.removeRow(0);
    }
  }

  void btnClear_actionPerformed(ActionEvent e) {
    resetDataInScreen();
    RemoveAllToLeft();
    showButton(true);
    flagSetHotKeyForAdd = true;
  }

  void btnDelete_actionPerformed(ActionEvent e) {
    int row = tblItemSeasonSetup.getSelectedRow();
     if (row >= 0) {
      dmItemSeason.removeRow(row);
      vId.remove(row);
      resetDataInScreen();
      showButton(true);
      RemoveAllToLeft();
      flagSetHotKeyForAdd = true;
    }
  }


  void tblItemSeasonSetup_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      RemoveAllToLeft();
      updateValueFromSelecledItemInTable();
    }
  }

  void btnSearch_actionPerformed(ActionEvent e) {
    listRight.removeIndexAll();
    DialogItemSeasonSearch dlgItemSeasonSearch = new DialogItemSeasonSearch(frmMain,
        lang.getString("TT0104"), true, frmMain,frmMain.iSeasonBusObj);
    dlgItemSeasonSearch.setLocation(112,85);
    dlgItemSeasonSearch.requestFocus();
    dlgItemSeasonSearch.txtItemID.requestFocus(true);
    dlgItemSeasonSearch.setVisible(true);
  }

  void btnModify_actionPerformed(ActionEvent e) {
    int row = tblItemSeasonSetup.getSelectedRow();
    if (row >= 0) {
      if (!checkDataInScreen()) {
        return;
      }

      if (!bItemMasterBusObj.checkItemcodeExisting(this.itemCode,"")) {
        JOptionPane.showMessageDialog(this.frmMain,
                                      lang.getString("MS1113_ItemCodeNotExistedInDatabaseCanNotAddItemIntoDatabase"),
                                      lang.getString("MS1114_DatabaseConflict"),
                                      JOptionPane.ERROR_MESSAGE);
        return;
      }
      for (int i = 0; i < listRight.getRowCount(); i++) {
        listRight.setSelectedIndex(i);
        if (bItemSeasonBusObj.checkDataExist(txtItemcode.getText(),
                                             listRight.getSelectedData(), DAL) ||
            checkItemExistOnTableModify(row)) {
          ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1112_ItemIsExistedForSeason") + listRight.getSelectedValue());
          return;
        }
      }

      tblItemSeasonSetup.setValueAt(txtItemcode.getText(), row, 1);
      tblItemSeasonSetup.setValueAt(txtItemname.getText(), row, 2);
      String sSeasonName = "";
      String sId="";
      for(int i=0;i<listRight.getRowCount();i++){
        listRight.setSelectedIndex(i);
        sSeasonName += listRight.getSelectedValue() + ";";
        sId += listRight.getSelectedData() + ";";
      }
      /*vId.setElementAt(sId,row);
      sSeasonName = sSeasonName.substring(0,sSeasonName.length()-1);
      tblItemSeasonSetup.setValueAt(sSeasonName, row, 4);*/
      sSeasonName = sSeasonName.substring(0,sSeasonName.length()-1);
      for (int i=0;i<tblItemSeasonSetup.getRowCount();i++){
        vId.setElementAt(sId,i);
        tblItemSeasonSetup.setValueAt(sSeasonName, i, 4);
      }
      resetDataInScreen();
      RemoveAllToLeft();
      showButton(true); //show Add, hide Modify
      flagSetHotKeyForAdd = true;
    }
  }

  void btnSelect_actionPerformed(ActionEvent e) {
    Object[] value = listLeft.getSelectedValues();
    String[] data = listLeft.getSelectedDatas();
    int[] index = listLeft.getSelectedIndices();
    if (!listRight.getAllID().isEmpty()){
      listRight.setSelectedIndex(0);
      listLeft.addData(listRight.getSelectedValues(),listRight.getSelectedDatas());
      listRight.removeIndexAll();
    }
    listRight.addData(value, data);
    listLeft.removeIndex(index);
  }

  void btnRemove_actionPerformed(ActionEvent e) {
    listLeft.addData(listRight.getSelectedValues(),listRight.getSelectedDatas());
    listRight.removeIndex(listRight.getSelectedIndices());
  }

  void btnSelectAll_actionPerformed(ActionEvent e) {
    int arrIndex[] = new int[listLeft.getRowCount()];
    for(int i=0;i<listLeft.getRowCount();i++){
      arrIndex[i] = i;
    }
    listLeft.setSelectedIndices(arrIndex);
    listRight.addData (listLeft.getSelectedValues(),listLeft.getSelectedDatas());
    listLeft.removeIndexAll();
  }

  void btnRemoveAll_actionPerformed(ActionEvent e) {
    RemoveAllToLeft();
  }
  void RemoveAllToLeft(){
    int arrIndex[] = new int[listRight.getRowCount()];
    for (int i = 0; i < listRight.getRowCount(); i++) {
      arrIndex[i] = i;
    }
    listRight.setSelectedIndices(arrIndex);
    listLeft.addData(listRight.getSelectedValues(), listRight.getSelectedDatas());
    listRight.removeIndexAll();
  }

  void tblItemSeasonSetup_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      if (flagSetHotKeyForAdd == true) {
        btnAdd.doClick();
      }
      else {
        btnModify.doClick();
      }

    }

  }
}



class PanelItemSeasonSetup_btnCancel_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnCancel_mouseAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
}


class PanelItemSeasonSetup_btnAdd_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnAdd_mouseAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
}



class PanelItemSeasonSetup_txtItemcode_focusAdapter extends java.awt.event.FocusAdapter {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_txtItemcode_focusAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void focusLost(FocusEvent e) {
    adaptee.txtItemcode_focusLost(e);
  }
}



class PanelItemSeasonSetup_btnItemSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnItemSearch_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnItemSearch_actionPerformed(e);
  }
}


class PanelItemSeasonSetup_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnCancel_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelItemSeasonSetup_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnAdd_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelItemSeasonSetup_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnDone_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelItemSeasonSetup_btnClear_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnClear_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClear_actionPerformed(e);
  }
}

class PanelItemSeasonSetup_btnDelete_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnDelete_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelItemSeasonSetup_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnModify_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelItemSeasonSetup_tblItemSeasonSetup_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_tblItemSeasonSetup_mouseAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.tblItemSeasonSetup_mouseClicked(e);
  }
}

class PanelItemSeasonSetup_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnSearch_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class PanelItemSeasonSetup_txtItemcode_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_txtItemcode_keyAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemcode_keyTyped(e);
  }
}

class PanelItemSeasonSetup_btnSelect_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnSelect_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSelect_actionPerformed(e);
  }
}

class PanelItemSeasonSetup_btnRemove_actionAdapter implements java.awt.event.ActionListener {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_btnRemove_actionAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnRemove_actionPerformed(e);
  }
}

class PanelItemSeasonSetup_tblItemSeasonSetup_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemSeasonSetup adaptee;

  PanelItemSeasonSetup_tblItemSeasonSetup_keyAdapter(PanelItemSeasonSetup adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tblItemSeasonSetup_keyPressed(e);
  }
}
