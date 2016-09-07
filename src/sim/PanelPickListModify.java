package sim;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import java.util.*;
import btm.attr.*;
import btm.business.*;
import javax.swing.border.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.table.TableColumn;
/**
 * <p>Title      : Pick List modify </p>
 * <p>Description: Main -> Inv Mgmt -> Pick list -> Pick List Modify </p>
 * <p>Copyright  : Copyright (c) 2006  </p>
 * <p>Company    : BTM </p>
 * @author       : Phuong Nguyen
 * @version 1.0
 */
public class PanelPickListModify extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  FrameMainSim frmMain;
  CardLayout cardLayout;
  JPanel parent;

  Statement stmt = null;
  DataAccessLayer DAL = new DataAccessLayer();
  Utils ut = new Utils();
  Vector vSql = new Vector();
  Vector vModify = new Vector();

  String  flagStatus = null;

  double qty=-1;
  //=============Define Constant for Table Column
  public static final int ITEM_ID   = 0;
  public static final int UPC       = 1;
  public static final int ITEM_NAME = 2;
  public static final int STANDARD_UOM = 3;
  public static final int REQUESTED_QTY = 4;
  public static final int ACTUAL_PICK_QTY = 5;
  public static final int SUBCATEGORY = 6;
  public static final int CURBACK = 7;
  public static final int CURFRONT = 8;

  public static final String STATUS_WORKSHEET = "WorkSheet";
  public static final String STATUS_APPROVED = "Approved";
  public static final String STATUS_COMPLETED = "Completed";
  public static final String STATUS_CANCELLED = "Cancelled";

  public static final String PL_TO_BACKROOM = "BackRoom";
  public static final String PL_TO_FRONTROOM = "FrontRoom";

  boolean flagCheckSOH = false; //true if RECEIPT > CURBACK
  boolean flagToFrontRoom = false; //true if RECEIPT > CURBACK

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  JPanel pnlHeader = new JPanel();
  BJButton btnReceipt = new BJButton();
  BJButton btnCancel = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnPrint = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnModify = new BJButton();
  BJButton btnDone = new BJButton();
  JPanel pnlCenter = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlInfo = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel pnlLeft = new JPanel();
  JPanel pnlRight = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel pnlLeftLabel = new JPanel();
  JPanel pnlLeftInfo = new JPanel();
  JLabel lblExpectedDate = new JLabel();
  JLabel lblCreateDate = new JLabel();
  JLabel lblPLID = new JLabel();
  JTextField txtExpectedDate = new JTextField();
  JTextField txtCreateDate = new JTextField();
  JButton btnExpectedDate = new JButton();
  JTextField txtPLID = new JTextField();
  JButton btnCreateDate = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel pnlRightLabel = new JPanel();
  JLabel lblStatus = new JLabel();
  JLabel lblType = new JLabel();
  JLabel lblCreator = new JLabel();
  JPanel pnlRightInfo = new JPanel();
  JPanel pnlDesc = new JPanel();
  JScrollPane scrPanelTable = new JScrollPane();
  SortableTableModel dmPickList = new SortableTableModel();
  BJTable tblPickList = new BJTable(dmPickList,true);
  FlowLayout flowLayout3 = new FlowLayout();
  String picklistID ="";
  JComboBox cboStatus = new JComboBox();
  BJComboBox cboPickListTo = new BJComboBox();
  JTextField txtCreator = new JTextField();
  JPanel jPanel2 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JLabel lblUPC = new JLabel();
  JTextField txtUPC = new JTextField();
  JButton btnUPC = new JButton(); //flag to set modify
  boolean FLAG_F4 = false;
  JLabel jLabel9 = new JLabel();
  JLabel jLabel12 = new JLabel();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel pnlComment = new JPanel();
  JLabel lblDesc = new JLabel();
  JTextArea txtDesc = new JTextArea();
  JButton btnAddGroup = new JButton();
  JPanel jPanel3 = new JPanel();
//  JLabel lblQty = new JLabel();
//  JTextField txtQty = new JTextField();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextField txtProGroupID = new JTextField();
  JLabel lblProdGroupName = new JLabel();
  JTextField txtProGroupName = new JTextField();
  boolean IS_ENTER = true;//first time press Enter
  public PanelPickListModify(FrameMainSim frmMain) {
        try {
          this.frmMain = frmMain;
          this.DAL = frmMain.getDAL();
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public PanelPickListModify(FrameMainSim frmMain, CardLayout crdLayout, JPanel parent) {
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
    public void EnabledDefault(boolean flag){
      txtPLID.setEditable(flag);
      txtCreator.setEditable(flag);
      txtCreateDate.setEditable(flag);
      btnCreateDate.setEnabled(flag);
    }

    public void EnabledModify(boolean flag){
      cboPickListTo.setEnabled(flag);
      cboStatus.setEnabled(flag);
      txtDesc.setEnabled(flag);
      txtExpectedDate.setEnabled(flag);
      btnExpectedDate.setEnabled(flag);
      txtUPC.setEnabled(flag);
      btnUPC.setEnabled(flag);
      btnAddGroup.setEnabled(flag);
   }

   public void EnabledReceipt(boolean flag){
//     txtQty.setEnabled(flag);
   }
    private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setLayout(borderLayout1);
    pnlHeader.setBackground(new Color(121, 152, 198));
    pnlHeader.setPreferredSize(new Dimension(800, 45));
    btnReceipt.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnReceipt.setPreferredSize(new Dimension(120, 37));
    btnReceipt.setToolTipText(lang.getString("Receipt")+"(F6)");
    btnReceipt.setText("<html><center><b>"+lang.getString("Receipt")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F6</html>");
    btnReceipt.addActionListener(new PanelPickListModify_btnReceipt_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(120, 37));
    btnCancel.setToolTipText(lang.getString("Cancel")+"(F12)");
    btnCancel.setText("<html><center><b>"+lang.getString("Cancel")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</html>");
    btnCancel.addActionListener(new PanelPickListModify_btnCancel_actionAdapter(this));
    //btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.setPreferredSize(new Dimension(120, 37));
    btnDelete.setToolTipText(lang.getString("Delete")+"(F8)");
    btnDelete.setText("<html><center><b>"+lang.getString("Delete")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F8</html>");
    btnPrint.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnPrint.setPreferredSize(new Dimension(120, 37));
    btnPrint.setToolTipText(lang.getString("Print")+"(F5)");
    btnPrint.setText("<html><center><b>"+lang.getString("Print")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
                      "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html>");
    btnPrint.addActionListener(new PanelPickListModify_btnPrint_actionAdapter(this));

    btnDelete.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDelete.addActionListener(new PanelPickListModify_btnDelete_actionAdapter(this));
    btnAdd.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnAdd.setToolTipText(lang.getString("Add")+"(F2)");
    btnAdd.setText("<html><center><b>"+lang.getString("Add")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                   "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelPickListModify_btnAdd_actionAdapter(this));
    btnAdd.setPreferredSize(new Dimension(120, 37));
    btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnModify.setToolTipText(lang.getString("Modify")+"(F4)");
    btnModify.setText("<html><center><b>"+lang.getString("Modify")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                   "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnModify.addActionListener(new PanelPickListModify_btnModify_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setPreferredSize(new Dimension(120, 37));
    btnDone.setToolTipText(lang.getString("Done")+"(F1)");
    btnDone.setText("<html><center><b>"+lang.getString("Done")+"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                      "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelPickListModify_btnDone_actionAdapter(this));
    pnlCenter.setLayout(borderLayout2);
    pnlInfo.setPreferredSize(new Dimension(800, 195));
    pnlInfo.setLayout(borderLayout3);
    pnlLeft.setPreferredSize(new Dimension(400, 90));
    pnlLeft.setLayout(borderLayout5);
    pnlRight.setPreferredSize(new Dimension(400, 90));
    pnlRight.setLayout(borderLayout6);
    pnlLeftLabel.setPreferredSize(new Dimension(150, 90));
    pnlLeftLabel.setLayout(null);
    lblExpectedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblExpectedDate.setPreferredSize(new Dimension(90, 22));
    lblExpectedDate.setText(lang.getString("ExpectedPickDate")+":");
    lblExpectedDate.setBounds(new Rectangle(27, 60, 115, 22));
    lblCreateDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblCreateDate.setPreferredSize(new Dimension(90, 22));
    lblCreateDate.setText(lang.getString("CreatedDate")+":");
    lblCreateDate.setBounds(new Rectangle(27, 32, 90, 22));
    lblPLID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblPLID.setMaximumSize(new Dimension(66, 23));
    lblPLID.setPreferredSize(new Dimension(90, 22));
    lblPLID.setText(lang.getString("PickListID")+":");
    lblPLID.setBounds(new Rectangle(42, 5, 90, 22));
    pnlLeftInfo.setPreferredSize(new Dimension(250, 90));
    pnlLeftInfo.setLayout(flowLayout1);
    txtExpectedDate.setBackground(Color.white);
    txtExpectedDate.setEnabled(true);
    txtExpectedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtExpectedDate.setPreferredSize(new Dimension(140, 22));
    txtExpectedDate.setBounds(new Rectangle(5, 60, 140, 22));
    txtCreateDate.setBackground(Color.white);
//  txtCreateDate.setEnabled(true);
    txtCreateDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreateDate.setPreferredSize(new Dimension(140, 22));
    txtCreateDate.setEditable(false);
//  txtCreateDate.setEditable(false);
    txtCreateDate.setBounds(new Rectangle(5, 32, 140, 22));
    btnExpectedDate.setBounds(new Rectangle(150, 60, 30, 23));
    btnExpectedDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnExpectedDate.setMinimumSize(new Dimension(30, 23));
    btnExpectedDate.setPreferredSize(new Dimension(30, 23));
    btnExpectedDate.setText("...");
    btnExpectedDate.addActionListener(new
                                 PanelPickListModify_btnExpectedDate_actionAdapter(this));
    btnCreateDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    btnCreateDate.setPreferredSize(new Dimension(30, 23));
    btnCreateDate.setText("...");
    btnCreateDate.setBounds(new Rectangle(150, 32, 30, 23));
    btnCreateDate.setEnabled(false);
    btnCreateDate.addActionListener(new
        PanelPickListModify_btnCreateDate_actionAdapter(this));
    txtPLID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtPLID.setPreferredSize(new Dimension(176, 22));
    txtPLID.setDisabledTextColor(SystemColor.info);
    txtPLID.setEditable(false);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    pnlRightLabel.setPreferredSize(new Dimension(150, 90));
    pnlRightLabel.setLayout(null);
    lblStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblStatus.setOpaque(false);
    lblStatus.setPreferredSize(new Dimension(80, 22));
    lblStatus.setRequestFocusEnabled(true);
    lblStatus.setText(lang.getString("Status")+":");
    lblStatus.setBounds(new Rectangle(42, 59, 80, 22));
    lblType.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblType.setPreferredSize(new Dimension(80, 22));
    lblType.setText(lang.getString("PickListTo")+":");
    lblType.setBounds(new Rectangle(42, 32, 80, 22));
    lblCreator.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblCreator.setPreferredSize(new Dimension(80, 22));
    lblCreator.setText(lang.getString("Creator")+":");
    lblCreator.setBounds(new Rectangle(27, 5, 80, 22));
    pnlRightInfo.setPreferredSize(new Dimension(250, 90));
    pnlRightInfo.setLayout(null);
    pnlDesc.setLayout(flowLayout3);
    tblPickList.addMouseListener(new
                                  PanelPickListModify_tblPickList_mouseAdapter(this));
    cboStatus.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboStatus.setPreferredSize(new Dimension(180, 22));
    cboStatus.setPopupVisible(false);
    cboPickListTo.setEnabled(true);
    cboPickListTo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    cboPickListTo.setPreferredSize(new Dimension(180, 22));
    txtCreator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtCreator.setPreferredSize(new Dimension(175, 22));
    txtCreator.setEditable(false);
    txtCreator.setBounds(new Rectangle(5, 5, 175, 22));
    pnlDesc.setPreferredSize(new Dimension(800, 105));
    jPanel2.setBorder(titledBorder2);
    jPanel2.setMinimumSize(new Dimension(22, 22));
    jPanel2.setPreferredSize(new Dimension(780, 55));
    jPanel2.setLayout(null);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    lblUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblUPC.setPreferredSize(new Dimension(124, 22));
    lblUPC.setToolTipText("");
    lblUPC.setHorizontalAlignment(SwingConstants.LEFT);
    lblUPC.setText(lang.getString("UPC")+":");
    lblUPC.setVerticalAlignment(SwingConstants.CENTER);
    lblUPC.setBounds(new Rectangle(37, 3, 80, 23));
    txtUPC.setBackground(Color.white);
    txtUPC.setEnabled(true);
    txtUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    txtUPC.setPreferredSize(new Dimension(140, 22));
    txtUPC.setRequestFocusEnabled(true);
    txtUPC.setDisabledTextColor(new Color(236, 233, 216));
    txtUPC.setText("");
    txtUPC.setBounds(new Rectangle(150, 3, 146, 23));
    txtUPC.addKeyListener(new PanelPickListModify_txtUPC_keyAdapter(this));
    btnUPC.setBounds(new Rectangle(300, 4, 30, 22));
    btnUPC.setEnabled(true);
    btnUPC.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnUPC.setPreferredSize(new Dimension(30, 22));
    btnUPC.setText("...");
    btnUPC.addActionListener(new PanelPickListModify_btnUPC_actionAdapter(this));
    jLabel9.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel9.setForeground(Color.red);
    jLabel9.setText("(*)");
    jLabel9.setBounds(new Rectangle(185, 35, 13, 16));
    jLabel12.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    jLabel12.setForeground(Color.red);
    jLabel12.setText("(*)");
    jLabel12.setBounds(new Rectangle(185, 63, 13, 16));
    this.setPreferredSize(new Dimension(800, 600));
    pnlCenter.setPreferredSize(new Dimension(800, 555));
    pnlComment.setPreferredSize(new Dimension(800, 40));
    pnlComment.setLayout(null);
    lblDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblDesc.setText(lang.getString("Description")+":");
    lblDesc.setBounds(new Rectangle(37, 0, 80, 22));
    txtDesc.setText("");
    txtDesc.addKeyListener(new PanelPickListModify_txtDesc_keyAdapter(this));
    btnAddGroup.setBounds(new Rectangle(419, 28, 93, 23));
    btnAddGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 11));
    btnAddGroup.setText(lang.getString("AddGroup"));
    btnAddGroup.addActionListener(new
                                  PanelPickListModify_btnAddGroup_actionAdapter(this));
    jPanel3.setBorder(titledBorder2);
    jPanel3.setPreferredSize(new Dimension(260, 55));
    jPanel3.setLayout(null);
//    lblQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    lblQty.setText("Quantity:");
//    lblQty.setBounds(new Rectangle(12, 21, 73, 22));
//    txtQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
//    txtQty.setToolTipText("");
//    txtQty.setText("");
//    txtQty.setBounds(new Rectangle(87, 21, 113, 22));
//    txtQty.addKeyListener(new PanelPickListModify_txtQty_keyAdapter(this));
    jScrollPane2.setPreferredSize(new Dimension(160, 45));
    jScrollPane2.setBounds(new Rectangle(150, 0, 575, 38));
    scrPanelTable.setPreferredSize(new Dimension(800, 350));
    txtProGroupID.setEnabled(false);
    txtProGroupID.setEditable(false);
    txtProGroupID.setText("");
    txtProGroupID.setBounds(new Rectangle(150, 28, 115, 23));
    lblProdGroupName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    lblProdGroupName.setText(lang.getString("ProGroup")+":");
    lblProdGroupName.setBounds(new Rectangle(37, 28, 108, 23));
    txtProGroupName.setEnabled(false);
    txtProGroupID.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtProGroupName.setFont(new java.awt.Font(lang.getString("FontName_Label"), Font.BOLD, 12));
    txtProGroupName.setEditable(false);
    txtProGroupName.setBounds(new Rectangle(268, 28, 148, 23));
    this.add(pnlCenter, java.awt.BorderLayout.CENTER);
    pnlLeftLabel.add(lblPLID);
    pnlLeftLabel.add(lblType);
    pnlLeftLabel.add(lblStatus);
    // left info panel
    pnlLeftInfo.add(txtPLID);
    pnlLeftInfo.add(cboPickListTo, null);
    pnlLeftInfo.add(cboStatus, null);

// right label info
    pnlRightLabel.add(lblCreator);
    pnlRightLabel.add(lblCreateDate);
    pnlRightLabel.add(lblExpectedDate);
    // right info panel
    pnlRightInfo.add(txtCreator, null);
    pnlRightInfo.add(txtCreateDate);
    pnlRightInfo.add(btnCreateDate);
    pnlRightInfo.add(jLabel9, null);
    pnlRightInfo.add(txtExpectedDate);
    pnlRightInfo.add(btnExpectedDate);
    pnlRightInfo.add(jLabel12, null);
    pnlInfo.add(pnlLeft, java.awt.BorderLayout.WEST);
    pnlInfo.add(pnlRight, java.awt.BorderLayout.CENTER);
    pnlInfo.add(pnlDesc, java.awt.BorderLayout.SOUTH);
    pnlDesc.add(pnlComment);
    pnlComment.add(lblDesc);
    pnlComment.add(jScrollPane2);
    jScrollPane2.getViewport().add(txtDesc);
    pnlDesc.add(jPanel2, null);
//    pnlDesc.add(jPanel3);
//    jPanel3.add(lblQty);
//    jPanel3.add(txtQty);
    pnlCenter.add(scrPanelTable, java.awt.BorderLayout.CENTER);
    scrPanelTable.getViewport().add(tblPickList);
    pnlCenter.add(pnlInfo, java.awt.BorderLayout.NORTH);
    pnlHeader.add(btnDone);
    pnlHeader.add(btnAdd);
    pnlHeader.add(btnDelete);
    pnlHeader.add(btnPrint);
    pnlHeader.add(btnReceipt);
    pnlHeader.add(btnCancel);
    jPanel2.add(lblUPC, null);
    jPanel2.add(txtUPC, null);
    jPanel2.add(txtProGroupID);
    jPanel2.add(btnAddGroup);
    jPanel2.add(btnUPC, null);
    jPanel2.add(lblProdGroupName);
    jPanel2.add(txtProGroupName);
    pnlLeft.add(pnlLeftLabel, java.awt.BorderLayout.WEST);
    pnlLeft.add(pnlLeftInfo, java.awt.BorderLayout.EAST);
    pnlRight.add(pnlRightLabel, java.awt.BorderLayout.WEST);
    pnlRight.add(pnlRightInfo, java.awt.BorderLayout.CENTER);
    this.add(pnlHeader, java.awt.BorderLayout.NORTH);
    registerKeyboardActions();
    initTable();
    showButton(true);
    initScreen();
    tblPickList.addKeyListener(new PanelPickListModify_tblPickList_keyAdapter(this));
  }
  public void setCboStatus(JComboBox cbo){
    cbo.removeAllItems();
    cbo.addItem("All");
    cbo.addItem(STATUS_WORKSHEET);
    cbo.addItem(STATUS_APPROVED);
    cbo.addItem(STATUS_COMPLETED);
    cbo.addItem(STATUS_CANCELLED);
    cbo.setSelectedIndex(0); //default status: WorkSheet
  }
  private void setCboPickListTo(JComboBox cbo){
    cbo.removeAllItems();
//    cbo.addItem("All");
    cbo.addItem(PL_TO_BACKROOM);
    cbo.addItem(PL_TO_FRONTROOM); //default
//    cbo.addItem("Within Day");
//    cbo.addItem("End Of Day");
    cbo.setSelectedIndex(0);
  }

  public void showPickListOnForm(String PLID){
    ResultSet rs=null;
    String SQL = " SELECT PICK_LIST_ID, PL.TYPE, "
//    + "	   CASE PL.TYPE WHEN 'F' THEN 'Freeform' "
//    + "	   			 WHEN 'W' THEN 'Within Day' "
//    + "				 WHEN 'E' THEN 'End Of Day' END AS TYPE, "
    + "	   CASE PL.STATUS WHEN 'W' THEN 'WorkSheet' "
    + "		   	   	   WHEN 'A' THEN 'Approved'  "
    + "				   WHEN 'C' THEN 'Completed'  "
    + "				   WHEN 'D' THEN 'Cancelled' END AS STATUS,  "
    + "	   EMP_CDE CREATOR, to_char(PL.CREATE_DATE,'dd/MM/yyyy') CREATE_DATE, "
    + "	   to_char(PL.EXPECTED_DATE,'dd/MM/yyyy') EXPECTED_DATE, PL.DECSRIPTION, REPLEN_GROUP_ID, "
    + " PGR.DESCRIPTION PRO_DESC"
    + " FROM BTM_IM_PICK_LIST PL LEFT OUTER JOIN BTM_IM_PROD_GROUP PGR ON (PGR.PROD_GROUP_ID=REPLEN_GROUP_ID),  "
    + "	 BTM_IM_EMPLOYEE "
    + "WHERE PICK_LIST_ID = '"+PLID+"' "
    + "AND EMP_ID = USER_ID";

    try{
//      System.out.println(SQL);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(SQL, stmt);
      if (rs.next()) {
        txtPLID.setText(rs.getString("PICK_LIST_ID"));
//        cboPickListTo.setSelectedItem(rs.getString("TYPE"));
        cboStatus.setSelectedItem(rs.getString("STATUS"));
        flagStatus = rs.getString("STATUS").toString();
        txtCreator.setText(rs.getString("CREATOR"));
        txtCreateDate.setText(rs.getString("CREATE_DATE"));
        txtExpectedDate.setText(rs.getString("EXPECTED_DATE"));
        txtDesc.setText(rs.getString("DECSRIPTION"));
        txtProGroupID.setText(rs.getString("REPLEN_GROUP_ID"));
        txtProGroupName.setText(rs.getString("PRO_DESC"));

        if(rs.getString("TYPE").equals("F")){
          flagToFrontRoom=true;
          cboPickListTo.setSelectedItem(PL_TO_FRONTROOM);
        }else{
          flagToFrontRoom=false;
          cboPickListTo.setSelectedItem(PL_TO_BACKROOM);
        }
//        System.out.println(">>>>>>>>>>>>> flagToFrontRoom "+flagToFrontRoom);
      }
      stmt.close();
      rs.close();
    }catch(Exception ex){
        ExceptionHandle eh=new ExceptionHandle();
        eh.ouputError(ex);
    }
    SQL = " SELECT PLITEM.ITEM_ID,MAS.ART_NO UPC, MAS.ITEM_NAME, UOM,  "
        + "	   QTY_EXPECTED, NVL(PLITEM.QTY_RECEIVED,0) QTY_RECEIVED, "
        + "	   CHILD_NAME,BACK_ROOM_QTY,FRONT_ROOM_QTY "
        + "FROM BTM_IM_PICK_LIST_ITEM PLITEM LEFT OUTER JOIN BTM_IM_ITEM_LOC_SOH SOH "
        + "ON (PLITEM.ITEM_ID = SOH.ITEM_ID AND STORE_ID='"+DAL.getStoreID()+"'), "
        + "	 BTM_IM_ITEM_MASTER MAS, "
        + "	 BTM_IM_PROD_HIR HIR "
        + "WHERE PICK_LIST_ID = '"+PLID+"' "
        + "AND MAS.CHILD_ID = HIR.CHILD_ID "
        + "AND PLITEM.ITEM_ID = MAS.ITEM_ID ORDER BY LOWER(ITEM_NAME) ";
    try{
//      System.out.println(SQL);
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(SQL, stmt);
      while (dmPickList.getRowCount()>0){
        dmPickList.removeRow(0);
      }
      while (rs.next()) {
        dmPickList.addRow(new Object[] {rs.getString("ITEM_ID"),
                          rs.getString("UPC"),rs.getString("ITEM_NAME"),
                          rs.getString("UOM"),rs.getString("QTY_EXPECTED"),
                          rs.getString("QTY_RECEIVED"),rs.getString("CHILD_NAME"),
                          rs.getString("BACK_ROOM_QTY"),rs.getString("FRONT_ROOM_QTY")});
      }
      stmt.close();
      rs.close();
    }catch(Exception ex){
        ExceptionHandle eh=new ExceptionHandle();
        eh.ouputError(ex);
    }
    initFormValue(cboStatus.getSelectedItem().toString());
  }

  private void initTable() {
      //define for table
      String[] columnNames = new String[] {
         "ITEM ID", "UPC", "Item Name", "UOM", "Request Qty", "Pick Qty", "SubCategory","BackR","FrontR"};
      dmPickList.setDataVector(new Object[][] {}, columnNames);
      tblPickList.setColumnWidth(ITEM_ID, 0);
      tblPickList.setColumnWidth(UPC, 100);
      tblPickList.setColumnWidth(ITEM_NAME, 180);
      tblPickList.setColumnWidth(STANDARD_UOM, 20);
      tblPickList.setColumnWidth(REQUESTED_QTY, 80);
      tblPickList.setColumnWidth(ACTUAL_PICK_QTY, 60);
      tblPickList.setColumnWidth(SUBCATEGORY, 80);
      tblPickList.setColumnWidth(CURBACK, 50);
      tblPickList.setColumnWidth(CURFRONT, 50);
      tblPickList.setColor(Color.lightGray, Color.white);
      tblPickList.setCellEditable(REQUESTED_QTY);

      TableColumn tblColumn = new TableColumn();
      tblColumn = tblPickList.getColumn("ITEM ID");
      tblColumn.setMaxWidth(0);
      tblColumn.setMinWidth(0);
      tblColumn.setPreferredWidth(0);
 }
 public void applyPermission() {
    EmpRight er = new EmpRight();
    er.initData(DAL, DAL.getEmpID());
    er.setData(DAL.getEmpID(), Constant.SCRS_PICK_LIST_MODIFY);
    btnAdd.setEnabled(er.getAdd());
    btnDelete.setEnabled(er.getDelete());
    btnReceipt.setEnabled(er.getModify());
    //  btnAdminRoleReport.setEnabled(er.getReport());
  }

  void showButton(boolean flagSetButton) {
      this.pnlHeader.removeAll();
      this.pnlHeader.updateUI();
      this.pnlHeader.add(btnDone,null);
//      if(flagSetButton==true){
        this.pnlHeader.add(btnAdd, null);
//      }else{
//        this.pnlHeader.add(btnModify, null);
//      }
      this.pnlHeader.add(btnDelete,null);
      this.pnlHeader.add(btnPrint,null);
      this.pnlHeader.add(btnReceipt,null);
      this.pnlHeader.add(btnCancel,null);
  }

  public void backPreviousScreen(){
    //clear data on grid
    while (dmPickList.getRowCount() > 0) {
      dmPickList.removeRow(0);
    }
  //show Dialog Pick List Search
    DialogPickListSearch dlgPickListSearch = new DialogPickListSearch(frmMain,
        lang.getString("TT0109"), true, frmMain, frmMain.plBusObj);
    dlgPickListSearch.setLocation(112, 85);
    dlgPickListSearch.setVisible(true);
    if (dlgPickListSearch.done) {
      frmMain.showScreen(Constant.SCRS_PICK_LIST_MODIFY);
    }
    else {
      frmMain.showScreen(Constant.SCRS_PICK_LIST);
    }
    FLAG_F4=false;
  }
  public void btnCancel_actionPerformed(ActionEvent e) {
    backPreviousScreen();
  }

   public void btnCreateDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnCreateDate.getY() + posYFrame + 90;
    posY = this.btnExpectedDate.getY() + posYFrame + 90;
    TimeDlg startdate = new TimeDlg(null);
    startdate.setTitle(lang.getString("ChooseDate"));
    startdate.setResizable(false);
    startdate.setLocation(posX,posY);
    startdate.setVisible(true);
    if (startdate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
      String date = fd.format(startdate.getDate());
      this.txtCreateDate.setText(date);
    }

  }
  public void btnExpectedDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.frmMain.getX();
    posYFrame = this.frmMain.getY();
    posX = this.btnCreateDate.getY() + posYFrame + 90;
    posY = this.btnExpectedDate.getY() + posYFrame + 90;
    TimeDlg startdate = new TimeDlg(null);
    startdate.setTitle(lang.getString("ChooseDate"));
    startdate.setResizable(false);
    startdate.setLocation(posX,posY);
    startdate.setVisible(true);
    if (startdate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
      String date = fd.format(startdate.getDate());
      this.txtExpectedDate.setText(date);
    }
  }

  public void btnAdd_actionPerformed(ActionEvent e) {
    ResultSet rs = null;
//checking valid quantity
    if(!checkExistOnGridModify(txtUPC.getText().trim())){
//      if (txtQty.getText().trim().equalsIgnoreCase("")) {
        insertItemToGrid(txtUPC.getText().trim(), 1);
//      }
//      else {
//        if (!checkValidQty()) {
//          return;
//        }
//        insertItemToGrid(txtUPC.getText().trim(), qty);
//      }
    }
    txtUPC.setText("");
//    txtQty.setText("");
  }

  public void btnModify_actionPerformed(ActionEvent e) {
//    int row=tblPickList.getSelectedRow();
//    if(!checkValidQty()){
//      return;
//    }
//    if(txtQty.getText().trim().equalsIgnoreCase("")){
//        ut.showMessage(frmMain, "Warning",
//                 "Input the new requested quantity that you want to modify.");
//        txtQty.requestFocus();
//        return;
//      }
//    if(flagStatus.equals(STATUS_WORKSHEET)){
//      tblPickList.setValueAt(String.valueOf(Double.parseDouble(txtQty.getText())),row,REQUESTED_QTY);
//      txtUPC.setText("");
//      txtUPC.setEnabled(true);
//      btnUPC.setEnabled(true);
//      btnAddGroup.setEnabled(true);
//      showButton(true);//show Add, hide Modify
//    }else
//     if (flagStatus.equals(STATUS_APPROVED)){
//       tblPickList.setValueAt(String.valueOf(Double.parseDouble(txtQty.getText())),row,ACTUAL_PICK_QTY);
//       showButton(false);
//     }
//    FLAG_F4 = false;
//    txtQty.setText("");
//    tblPickList.requestFocus(true);
  }

  private boolean checkExistOnGrid(String upc){
    for(int i=0;i < dmPickList.getRowCount();i++){
      if(dmPickList.getValueAt(i,1).toString().equalsIgnoreCase(upc.trim()))
        return true;
    }
    return false;
  }
  public void initScreen(){
    setCboStatus(cboStatus);
    setCboPickListTo(cboPickListTo);
    txtProGroupID.setText("");
    txtProGroupName.setText("");
//    txtQty.setText("");
    txtUPC.setText("");
    FLAG_F4=false;
  }
  public void initFormValue(String status){
//    txtQty.setText("");
    txtUPC.setText("");
    tblPickList.setEnabled(true);
    if (status.equalsIgnoreCase(STATUS_WORKSHEET)){
      showButton(true);
      btnDone.setEnabled(true);
      btnAdd.setEnabled(true);
      btnDelete.setEnabled(true);
      btnPrint.setEnabled(false);
      btnReceipt.setEnabled(false);
      btnCancel.setEnabled(true);
      EnabledDefault(false);
      EnabledModify(true);
      EnabledReceipt(true);
      tblPickList.setCellEditable(REQUESTED_QTY);
      frmMain.setTitle(lang.getString("TT0110"));
    }
    if (status.equalsIgnoreCase(STATUS_APPROVED)){
      showButton(false);
      btnDone.setEnabled(false);
      btnAdd.setEnabled(false);
      btnDelete.setEnabled(false);
      btnPrint.setEnabled(true);
      btnReceipt.setEnabled(true);
      btnCancel.setEnabled(true);
      EnabledDefault(false);
      EnabledModify(false);
      EnabledReceipt(true);
      tblPickList.setCellEditable(ACTUAL_PICK_QTY);
      frmMain.setTitle(lang.getString("TT0111"));
    }
    if (status.equalsIgnoreCase(STATUS_COMPLETED) || status.equalsIgnoreCase(STATUS_CANCELLED)){
      showButton(true);
      btnDone.setEnabled(false);
      btnAdd.setEnabled(false);
      btnDelete.setEnabled(false);
      btnPrint.setEnabled(true);
      btnReceipt.setEnabled(false);
      btnCancel.setEnabled(true);
      EnabledDefault(false);
      EnabledModify(false);
      EnabledReceipt(false);
      tblPickList.setEnabled(false);
    }
  }

  public void btnDone_actionPerformed(ActionEvent e) {
    if(flagStatus.equals(STATUS_WORKSHEET)){
      String createDate = txtCreateDate.getText().trim();
      String expectedDate = txtExpectedDate.getText().trim();

      if (!ut.checkDate(expectedDate, "/") || expectedDate.equals("")) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1135_InputExpectedPickDateDateType"));
        txtExpectedDate.setText("");
        btnExpectedDate.requestFocus(true);
        return;
      }
      if (ut.compareDate(createDate, expectedDate)) {
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1136_ExpectedPickDateGreaterOrEqualCreateDate"));
        txtExpectedDate.setText("");
        btnExpectedDate.requestFocus(true);
        return;
      }
      if (cboStatus.getSelectedItem().equals(STATUS_COMPLETED)){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1141_PickListNotCompletedStatus"));
        cboStatus.setSelectedItem(STATUS_WORKSHEET);
        return;
      }
       setSQLsForVector();
       if (!vModify.isEmpty()) {
          try{
            stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);
            DAL.executeBatchQuery(vModify, stmt);
            stmt.close();
          }
          catch(Exception ex){};
       }
       vModify.clear();
    }
    backPreviousScreen();
  }

  private void setSQLsForVector(){
   String SQL = " UPDATE BTM_IM_PICK_LIST"
              + " SET PICK_LIST_ID ='"+txtPLID.getText()+"',"
              + " STORE_ID = '" + DAL.getStoreID()+"',"
              + " STATUS = '" + cboStatus.getSelectedItem().toString().substring(0,1)+"',"
              + " TYPE = '"+cboPickListTo.getSelectedItem().toString().substring(0,1)+ "',"
              + " USER_ID = '"+DAL.getEmpID()+"', CREATE_DATE = to_date('"+txtCreateDate.getText()+"','dd/mm/yyyy'),"
              + " EXPECTED_DATE = to_date('"+txtExpectedDate.getText()+"','dd/mm/yyyy'),"
              + " RECEIPT_DATE = '', DECSRIPTION = '"+ txtDesc.getText()+"',"
              + " REPLEN_GROUP_ID = '"+txtProGroupID.getText()+"'"
              + " WHERE PICK_LIST_ID = '"+txtPLID.getText()+"'";
//   System.out.println(SQL);
   vModify.addElement(SQL);
   SQL="delete BTM_IM_PICK_LIST_ITEM where PICK_LIST_ID = '"+txtPLID.getText()+"'";
//   System.out.println(SQL);
   vModify.addElement(SQL);
   for (int i=0;i<dmPickList.getRowCount();i++){
     SQL = " insert into BTM_IM_PICK_LIST_ITEM"
         + " values ('"+txtPLID.getText()+"','"+dmPickList.getValueAt(i,ITEM_ID)+"'"
         + ",'"+dmPickList.getValueAt(i,REQUESTED_QTY)+"','',"
         + "'"+dmPickList.getValueAt(i,STANDARD_UOM)+"','')";
//     System.out.println(SQL);
     vModify.addElement(SQL);
   }

  }
//event for F4 press
  void updateValueFromSelectedItemInTable() {
      int row = tblPickList.getSelectedRow();
      if (row >= 0) {
        txtUPC.setText(tblPickList.getValueAt(row, UPC).toString());
          showButton(false); //show Modify; hide Add
          FLAG_F4 = true; // allow hit F4 to modify
          txtUPC.setEnabled(false);
          btnUPC.setEnabled(false);
          btnAddGroup.setEnabled(false);
//          txtQty.requestFocus(true);
      }
  }
  public void btnDelete_actionPerformed(ActionEvent e) {
    btnPrint.setEnabled(false);
    int row= tblPickList.getSelectedRow();
    if (tblPickList.getSelectedRow() == -1) {
      return;
    }
    if(row>=0){
      int choose = ut.showMessage(frmMain, lang.getString("TT001"),
                                  lang.getString("MS1076_DeleteUPC")+" '" + tblPickList.getValueAt(row,UPC).toString() + "' ?", 3, DialogMessage.YES_NO_OPTION);
      if (choose == DialogMessage.YES_VALUE) {
        dmPickList.removeRow(row);
      }
    }
    else{
      if(tblPickList.getRowCount()>0){
        ut.showMessage(frmMain, lang.getString("TT001"),
                       lang.getString("MS1077_ChooseUPCWantToDelete"));
        return;
      }
    }
   }

  public void tblPickList_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
//      if(flagStatus.equals(STATUS_WORKSHEET)|| flagStatus.equals(STATUS_APPROVED)){
//        int row = tblPickList.getSelectedRow();
//        if (row >= 0) {
//          txtUPC.setText(tblPickList.getValueAt(row, UPC).toString());
//          showButton(false); //show Modify; hide Add
//          FLAG_F4 = true; // allow hit F4 to modify
//          txtUPC.setEnabled(false);
//          btnUPC.setEnabled(false);
//          btnAddGroup.setEnabled(false);
//          txtQty.requestFocus(true);
//        }
//      }
    }
  }
  public boolean checkReceiptValue(){
    int countZero = 0;
    int rows = dmPickList.getRowCount();
    for (int i=0; i< rows;i++){
      if (dmPickList.getValueAt(i,ACTUAL_PICK_QTY)==null){
        countZero ++;
      }
    }
    if (countZero == rows){
      return false;
    }else {
      return true;
    }
  }
  public void btnReceipt_actionPerformed(ActionEvent e) {
    ut.writeToTextFile("file\\error.txt","Hist PL  "+ ut.getSystemDateTime()+ "\r\n");

    String strTitleHeader="";
    vSql.clear();
    if(!checkReceiptValue()){
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1140_InputActualPickQty"));
      return;
    }
    double receiptQty = 0;
    for (int i=0;i<dmPickList.getRowCount();i++){
      if(dmPickList.getValueAt(i,ACTUAL_PICK_QTY)!=null){
        receiptQty = Double.parseDouble(dmPickList.getValueAt(i,ACTUAL_PICK_QTY).toString());
      }else{
        receiptQty=0;
      }
      String SQL = " update BTM_IM_PICK_LIST_ITEM "
         + "set QTY_RECEIVED = '"+receiptQty+"' "
         + "where PICK_LIST_ID='"+txtPLID.getText()+"' "
         + "and ITEM_ID='"+dmPickList.getValueAt(i,ITEM_ID)+"' ";
      vSql.addElement(SQL);
      String subSQL = "SELECT * FROM BTM_IM_ITEM_LOC_SOH "
          + "WHERE ITEM_ID = '"+dmPickList.getValueAt(i,ITEM_ID)+"' "
          + "AND STORE_ID = '" + DAL.getStoreID()+"' ";
//      System.out.println(subSQL);
      ResultSet rs=null;
      try{
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
        rs = DAL.executeQueries(subSQL, stmt);
        if (rs.next()){
          if(flagToFrontRoom){
            SQL = " UPDATE BTM_IM_ITEM_LOC_SOH set "
                + "BACK_ROOM_QTY = BACK_ROOM_QTY - '" + receiptQty + "', "
                + "FRONT_ROOM_QTY = FRONT_ROOM_QTY + '" + receiptQty + "', "
                + "SOH_UPDATE_DATETIME = TO_DATE('" + ut.getSystemDate(1) +
                "','dd/mm/yyyy') "
                + "WHERE ITEM_ID = '" + dmPickList.getValueAt(i, ITEM_ID) +
                "' "
                + "AND STORE_ID = '" + DAL.getStoreID() + "' ";

          }else{
            SQL = " UPDATE BTM_IM_ITEM_LOC_SOH set "
                + "BACK_ROOM_QTY = BACK_ROOM_QTY + '" + receiptQty + "', "
                + "FRONT_ROOM_QTY = FRONT_ROOM_QTY - '" + receiptQty + "', "
                + "SOH_UPDATE_DATETIME = TO_DATE('" + ut.getSystemDate(1) +
                "','dd/mm/yyyy') "
                + "WHERE ITEM_ID = '" + dmPickList.getValueAt(i, ITEM_ID) +
                "' "
                + "AND STORE_ID = '" + DAL.getStoreID() + "' ";

          }
//          System.out.println(">>>> "+SQL);
          vSql.addElement(SQL);
        }else{
          if(flagToFrontRoom){
            SQL = " INSERT INTO BTM_IM_ITEM_LOC_SOH "
                + " VALUES ('" + dmPickList.getValueAt(i, ITEM_ID) + "', "
                + " '" + DAL.getStoreID() + "', 0, '-" + receiptQty + "', "
                + " '" + receiptQty +
                "', '','','','','','','','','','','','','', "
                + " TO_DATE('" + ut.getSystemDate(1) + "','dd/mm/yyyy'),'','')";
          }else{
            SQL = " INSERT INTO BTM_IM_ITEM_LOC_SOH "
                + " VALUES ('" + dmPickList.getValueAt(i, ITEM_ID) + "', "
                + " '" + DAL.getStoreID() + "', 0, '" + receiptQty + "', "
                + " '-" + receiptQty +
                "', '','','','','','','','','','','','','', "
                + " TO_DATE('" + ut.getSystemDate(1) + "','dd/mm/yyyy'),'','')";

          }
//          System.out.println(">>>> "+SQL);
          vSql.addElement(SQL);
        }
       stmt.close();
       rs.close();
      }catch(Exception ex){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      }
     }
    String SQL = " update BTM_IM_PICK_LIST "
        + "set STATUS = 'C' , RECEIPT_DATE=to_date('"+ut.getSystemDate(1)+"','dd/mm/yyyy') "
        + "where PICK_LIST_ID='"+txtPLID.getText()+"' ";
   vSql.addElement(SQL);
   if (!vSql.isEmpty()) {
     try {
       stmt = DAL.getConnection().createStatement(ResultSet.
           TYPE_SCROLL_INSENSITIVE,
           ResultSet.CONCUR_READ_ONLY);
       DAL.executeBatchQuery(vSql, stmt);
       stmt.close();
     }
     catch (Exception ex) {}
     ;
   }
   if(flagToFrontRoom){
     strTitleHeader = "PICK LIST RECEIPT";
   }else{
     strTitleHeader = "PICK LIST RECEIPT (TO BACKROOM)";
   }

   PrintReceipt(txtPLID.getText(),strTitleHeader,DAL.getStoreName(),txtProGroupName.getText(),txtCreator.getText(),ut.getSystemDateTime().substring(11,19),txtCreateDate.getText(),txtExpectedDate.getText(),tblPickList,DAL,STATUS_COMPLETED);
   backPreviousScreen();
  }

  void PrintReceipt(String strPickListID,String strTitleHeader, String strFromStoreRoom,
                     String strProdGrp,String strCreator,String strTime,String strDate, String strExpDate,
                     BJTable tbl, DataAccessLayer DAL,String sStatus) {

      String strHeader[][] = {
          {
          "PICK LIST # " + strPickListID
      }
      };
      String strTitle[] = {
          strTitleHeader};
      String strInfo[][];
      String strFormat[];
      String strTableTitle[];
      String strTableAlign[];
      String strTableItem[][];
      String st = "";

//        int iTableSkew[] = {
//            5,8,34,13,10,10,20};

      int iTableSkew[];
      int[] iTotal = {
          4};

   if (sStatus.equals(STATUS_APPROVED)){
     strTableItem = new String[tbl.getRowCount()][6];
     strFormat  = new String[]{
              "String", "String", "String", "String", "money","String"
      };
     strTableTitle = new String[]{
          "No", "UPC", "Item Name", "UOM", "RequestQty","DeliveryQty"
      };
      strTableAlign = new String[]{
          "CENTER", "CENTER", "LEFT", "CENTER", "RIGHT","RIGHT"};
     iTableSkew = new int[]{5,15,40,10,15,15};
     strInfo = new String[][]{
         {
         "Create Date: " + strDate, //+ " " + strTime,
         "Store: " + strFromStoreRoom}
         ,{
         "Expect Date: " + strExpDate,
         "P/L Creator: " + strCreator}
         , {
         "Delivery Date: ",
         "Prod. Group: " + strProdGrp
         }
     };

      //Add Item to receipt
      for (int i = 0; i < tbl.getRowCount(); i++) {
        //col 0
        int iNo = i + 1;
        strTableItem[i][0] = "" + iNo;

        //col 1  -UPC
        if (tbl.getValueAt(i, UPC) != null) {
          st = tbl.getValueAt(i, UPC).toString();
        }
        else {
          st = " ";
        }
        strTableItem[i][1] = st;
        //col 2  - Item Name
        if (tbl.getValueAt(i, ITEM_NAME) != null) {
          st = tbl.getValueAt(i, ITEM_NAME).toString();
        }
        else {
          st = " ";
        }
        strTableItem[i][2] = st;
        //col 3  - UOM
        if (tbl.getValueAt(i, STANDARD_UOM) != null) {
          st = tbl.getValueAt(i, STANDARD_UOM).toString();
        }
        else {
          st = " ";
        }
        strTableItem[i][3] = st;
        //col 4  - QTY

          if (tbl.getValueAt(i, REQUESTED_QTY) != null) {
            st = tbl.getValueAt(i, REQUESTED_QTY).toString();
          }
          else {
            st = "0";
          }
        strTableItem[i][4] = st;
        //col 5
        strTableItem[i][5] = "";
      }
      PrintReceipt pp = new PrintReceipt();
      pp.setFileName(DAL.getAppHome() + "\\file\\" +
                     strPickListID +
                     ".pdf");

      //pp.setFileName("./file/"+strReceiptID+".pdf");
      pp.setHeader(strHeader);
      pp.setTitle(strTitle);
      pp.setInfo(strInfo);

      //Add info
      pp.setIsMagin(false);
      pp.setTotalName("Total Qty");
      pp.setLeftSign("DELIVER");
      pp.setRightSign("RECEIVER");
      pp.setColorBackgroundTotal(new Color(255, 255, 255));

      pp.setIsViewTotal(true);
      pp.setRowsTotal(iTotal);
      pp.setFormatColumn(strFormat);
      pp.setTableSkew(iTableSkew);
      pp.setTableAlign(strTableAlign);
      pp.setTableTitle(strTableTitle);
      pp.setTableItem(strTableItem);
      pp.setDeleteFileAfterPrint(true);

      pp.setIsGroupByArtNumber(false);
      pp.setTotalGroupName("");

      pp.print();
    }
      else if (sStatus.equals(STATUS_COMPLETED)){
        strTableItem = new String[tbl.getRowCount()][5];
        strFormat  = new String[]{
                 "String", "String", "String", "String", "money"
         };
        strTableTitle = new String[]{
             "No", "UPC", "Item Name", "UOM", "Receipt Qty"
         };
         strTableAlign = new String[]{
             "CENTER", "CENTER", "LEFT", "CENTER", "RIGHT"};
        iTableSkew = new int[]{5,15,55,10,15};
        strInfo = new String[][]{
            {
            "Create Date: " + strDate, //+ " " + strTime,
            "Store: " + strFromStoreRoom}
            ,{
            "Expect Date: " + strExpDate,
            "P/L Creator: " + strCreator}
            , {
            "Receipt Date: " + ut.getSystemDate(1),
            "Prod. Group: " + strProdGrp
            }
        };

        //Add Item to receipt
        for (int i = 0; i < tbl.getRowCount(); i++) {
          //col 0
          int iNo = i + 1;
          strTableItem[i][0] = "" + iNo;

          //col 1  -UPC
          if (tbl.getValueAt(i, UPC) != null) {
            st = tbl.getValueAt(i, UPC).toString();
          }
          else {
            st = " ";
          }
          strTableItem[i][1] = st;
          //col 2  - Item Name
          if (tbl.getValueAt(i, ITEM_NAME) != null) {
            st = tbl.getValueAt(i, ITEM_NAME).toString();
          }
          else {
            st = " ";
          }
          strTableItem[i][2] = st;
          //col 3  - UOM
          if (tbl.getValueAt(i, STANDARD_UOM) != null) {
            st = tbl.getValueAt(i, STANDARD_UOM).toString();
          }
          else {
            st = " ";
          }
          strTableItem[i][3] = st;
          //col 4  - QTY

            if (tbl.getValueAt(i, ACTUAL_PICK_QTY) != null) {
              st = tbl.getValueAt(i, ACTUAL_PICK_QTY).toString();
            }
            else {
              st = "0";
            }
          strTableItem[i][4] = st;
        }
        PrintReceipt pp = new PrintReceipt();
        pp.setFileName(DAL.getAppHome() + "\\file\\" +
                       strPickListID +
                       ".pdf");

        //pp.setFileName("./file/"+strReceiptID+".pdf");
        pp.setHeader(strHeader);
        pp.setTitle(strTitle);
        pp.setInfo(strInfo);

        //Add info
        pp.setIsMagin(false);
        pp.setTotalName("Total Qty");
        pp.setLeftSign("CREATOR");
        pp.setRightSign("APPROVER");
        pp.setColorBackgroundTotal(new Color(255, 255, 255));

        pp.setIsViewTotal(true);
        pp.setRowsTotal(iTotal);
        pp.setFormatColumn(strFormat);
        pp.setTableSkew(iTableSkew);
        pp.setTableAlign(strTableAlign);
        pp.setTableTitle(strTableTitle);
        pp.setTableItem(strTableItem);
        pp.setDeleteFileAfterPrint(true);

        pp.setIsGroupByArtNumber(false);
        pp.setTotalGroupName("");

        pp.print();
    }
   }

  boolean checkValidQty(){
//    if(!txtQty.getText().trim().equalsIgnoreCase("")){
//      qty = Double.parseDouble(txtQty.getText());
//      if (qty <= 0) {
//        ut.showMessage(frmMain, lang.getString("TT001"),
//                       "The Quantity can not less than or equal 0.");
//        txtQty.requestFocus();
//        txtQty.setSelectionStart(0);
//        txtQty.setSelectionEnd(txtQty.getText().length());
//        return false;
//      }
//    }
    return true;
  }

  boolean checkExistOnGridModify(String promoName){
    for (int i = 0; i < dmPickList.getRowCount(); i++) {
      if (i != tblPickList.getSelectedRow() &&
          dmPickList.getValueAt(i, UPC).toString().equals(promoName.trim()))
        return true;
    }
    return false;
  }

  public void btnPrint_actionPerformed(ActionEvent e) {
    String strTitleHeader = "";
    String Status = cboStatus.getSelectedItem().toString();
    if(Status.equals(STATUS_APPROVED)){
      if(flagToFrontRoom){
        strTitleHeader = "PICK LIST DELIVERY NOTE";
      }else{
        strTitleHeader = "PICK LIST DELIVERY NOTE (TO BACKROOM)";
      }
    }
    else if (Status.equals(STATUS_COMPLETED)){
      if(flagToFrontRoom){
        strTitleHeader = "PICK LIST RECEIPT";
      }else{
        strTitleHeader = "PICK LIST RECEIPT (TO BACKROOM)";
      }
   }
    PrintReceipt(txtPLID.getText(),strTitleHeader,DAL.getStoreName(),txtProGroupName.getText(),txtCreator.getText(),ut.getSystemDateTime().substring(11,19),txtCreateDate.getText(),txtExpectedDate.getText(),tblPickList,DAL,Status);
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
     else if (identifier.intValue() == KeyEvent.VK_F3) {
     }
//     else if (identifier.intValue() == KeyEvent.VK_F4) {
//       if(FLAG_F4 == true){
//         btnModify.doClick();
//       }else{
//         updateValueFromSelectedItemInTable();
//       }
//     }
     else if (identifier.intValue() == KeyEvent.VK_5) {
       btnPrint.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F8) {
       btnDelete.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_F6) {
       btnReceipt.doClick();
     }
     else if (identifier.intValue() == KeyEvent.VK_ESCAPE) {
       btnCancel.doClick();
     }
    }
 }

  void btnUPC_actionPerformed(ActionEvent e) {
       btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
       DialogItemLookup dlgItemLookup = new DialogItemLookup(frmMain,lang.getString("TT0095"),true,frmMain,itemBusObject);
       dlgItemLookup.cboStatus.setSelectedItem("Approve");
       dlgItemLookup.cboStatus.setEditable(false);
       dlgItemLookup.setVisible(true);
       if (dlgItemLookup.done){
          if (dlgItemLookup.getUPCList().isEmpty()){
            ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1079_ItemNotSelectedOrNotApproved") );
            return;
          }
          setData(dlgItemLookup.getUPCList());
          txtUPC.setText("");
    }
  }
  void insertItemToGrid(String upc,double qty){
    ResultSet rs = null;
    String SQL = " SELECT MAS.ITEM_ID,ART_NO,"
    + "ITEM_NAME,STANDARD_UOM,CHILD_NAME,"
    + String.valueOf(qty)+ " REQUESTED_QTY,'' PICK_QTY,"
    + "BACK_ROOM_QTY,FRONT_ROOM_QTY "
    + "FROM BTM_IM_ITEM_MASTER MAS LEFT OUTER JOIN "
    + "	 BTM_IM_ITEM_LOC_SOH SOH ON (SOH.ITEM_ID=MAS.ITEM_ID AND SOH.STORE_ID='"+DAL.getStoreID()+"'), "
    + "	 BTM_IM_PROD_HIR HIR "
    + "WHERE MAS.CHILD_ID=HIR.CHILD_ID "
    + "AND  MAS.STATUS = 'A' "
    + "AND  MAS.ART_NO='"+upc+"' ";
    try{
//        System.out.println(SQL);
        stmt = DAL.getConnection().createStatement(ResultSet.
            TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = DAL.executeScrollQueries(SQL,stmt);
        while(rs.next()){
          dmPickList.addRow(new Object[] {rs.getString("ITEM_ID"),rs.getString("ART_NO"),
             rs.getString("ITEM_NAME"),rs.getString("STANDARD_UOM"),
             rs.getString("REQUESTED_QTY"),rs.getString("PICK_QTY"),
             rs.getString("CHILD_NAME"),rs.getString("BACK_ROOM_QTY"),
             rs.getString("FRONT_ROOM_QTY")});
        }
        rs.close();
        stmt.close();
        }
        catch (Exception ex) {}

  }
  void setData(Vector vUPC){
    for(int i=0; i< vUPC.size();i++){
      if(!checkExistOnGrid(vUPC.get(i).toString().trim())){
        insertItemToGrid(vUPC.get(i).toString().trim(),1);
      }
    }
 }

  void txtQty_keyTyped(KeyEvent e) {
//    char key = e.getKeyChar();
//    ut.limitLenjTextField(e,txtQty,12);
//    ut.checkNumberUpdate(e,txtQty.getText());
  }

  void txtUPC_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtUPC,Constant.LENGHT_UPC_INPUT);
    ut.checkNumber(e);
  }

  public void btnAddGroup_actionPerformed(ActionEvent e) {
    btm.business.ProdGroupBusObj prdGrpBusObject = new btm.business.ProdGroupBusObj();
    DialogProductGroupSearch dlgProdGroupSearch = new DialogProductGroupSearch(frmMain,lang.getString("TT0101"),true,frmMain,prdGrpBusObject);
    dlgProdGroupSearch.cboType.setSelectedItem("P");
    dlgProdGroupSearch.cboType.setEnabled(false);
    frmMain.pnlProdGroupDetail.SCREEN_FLAG = frmMain.pnlProdGroupDetail.PL_MODIFY;
    dlgProdGroupSearch.setVisible(true);
    if (dlgProdGroupSearch.done){
       if (dlgProdGroupSearch.getPGID().equalsIgnoreCase("")){
         ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1079_ItemNotSelectedOrNotApproved"));
         return;
       }
       frmMain.pnlProdGroupDetail.setDataDetail(dlgProdGroupSearch.getPGID());
       frmMain.showScreen(Constant.SCRS_PROD_GROUP_DETAIL);
    }else{
      frmMain.showScreen(Constant.SCRS_PICK_LIST_MODIFY);
    }
  }

  public void txtDesc_keyTyped(KeyEvent e) {

  }

  void tblPickList_keyPressed(KeyEvent e) {
    char key = e.getKeyChar();
    int COL_MODIFY=REQUESTED_QTY;
    if (cboStatus.getSelectedItem().toString().equalsIgnoreCase(
        STATUS_APPROVED)) {
      COL_MODIFY=ACTUAL_PICK_QTY;
    }
    String sNode ="";
    if(!tblPickList.getValueAt(tblPickList.getSelectedRow(), COL_MODIFY).equals(null)){
      sNode = tblPickList.getValueAt(tblPickList.getSelectedRow(), COL_MODIFY).toString();
    }
    if ( ( (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
          (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) ||
          (e.getKeyCode() == KeyEvent.VK_DELETE) ||
          (key == '-' && sNode.equals("")) ||
          (key == '.' && ut.countDot(sNode) == 0 && !sNode.equals("")))
        && (tblPickList.getSelectedColumn() == COL_MODIFY)) {
      if (IS_ENTER == true) {
        IS_ENTER = false; // modifying
        tblPickList.setValueAt("", tblPickList.getSelectedRow(),COL_MODIFY);
      }
    }
    else {
      if (e.getKeyCode() != KeyEvent.VK_DOWN &&
          e.getKeyCode() != KeyEvent.VK_UP &&
          e.getKeyCode() != KeyEvent.VK_LEFT &&
          e.getKeyCode() != KeyEvent.VK_RIGHT &&
          e.getKeyCode() != KeyEvent.VK_ENTER)
        e.consume();
    }
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      IS_ENTER = true;
    }

  }
//  void updateRequestQty(int row){
//    int COL_MODIFY=REQUESTED_QTY;
//    if (cboStatus.getSelectedItem().toString().equalsIgnoreCase(
//        STATUS_APPROVED)) {
//      COL_MODIFY=ACTUAL_PICK_QTY;
//    }
//    if (tblPickList.getSelectedRow() > 0 && (tblPickList.getValueAt(row, COL_MODIFY).equals("")
//       || !ut.isDoubleString(tblPickList.getValueAt(row, COL_MODIFY).toString()))) {
//      if(COL_MODIFY==ACTUAL_PICK_QTY){
//        tblPickList.setValueAt("0",row, COL_MODIFY);
//      }else{
//        tblPickList.setValueAt("1",row, COL_MODIFY);
//      }
//    }
//    if (tblPickList.getRowCount() > 0 && (tblPickList.getValueAt(tblPickList.getRowCount()-1, COL_MODIFY).equals("")
//        || !ut.isDoubleString(tblPickList.getValueAt(tblPickList.getRowCount()-1, COL_MODIFY).toString()))) {
//      if(COL_MODIFY==ACTUAL_PICK_QTY){
//        tblPickList.setValueAt("0",row, COL_MODIFY);
//      }else{
//        tblPickList.setValueAt("1",row, COL_MODIFY);
//      }
//    }
//  }

  void updateRequestQty(int row){
    int COL_MODIFY=REQUESTED_QTY;
    if (cboStatus.getSelectedItem().toString().equalsIgnoreCase(
        STATUS_APPROVED)) {
      COL_MODIFY=ACTUAL_PICK_QTY;
    }
    if(row >=0 && row < tblPickList.getRowCount()){
      if (tblPickList.getValueAt(row, COL_MODIFY).equals("")
          || tblPickList.getValueAt(row, COL_MODIFY).toString().equals("0")
          ||
          !ut.isDoubleString(tblPickList.getValueAt(row, COL_MODIFY).toString())) {
        if (COL_MODIFY == ACTUAL_PICK_QTY) {
          tblPickList.setValueAt("0", row, COL_MODIFY);
        }
        else {
          tblPickList.setValueAt("1", row, COL_MODIFY);
        }
      }
      if(COL_MODIFY ==ACTUAL_PICK_QTY){
        if(flagToFrontRoom){
          checkBR(row, COL_MODIFY);
        }else{
          checkFR(row, COL_MODIFY);
        }
      }
    }
  }
  void checkBR(int row, int column){
    double curBack = 0;
    if(tblPickList.getValueAt(row,CURBACK).toString().equals("null")){
      curBack=0;
    }else{
      curBack = Double.parseDouble(tblPickList.getValueAt(row,CURBACK).toString());
    }
    if(Double.parseDouble(tblPickList.getValueAt(row,column).toString())> curBack){
      flagCheckSOH=true;
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1138_BackRoomNotEnoughGoodsTransfer"));
      requestFocus();
    }
  }
  void checkFR(int row, int column){
    double curFront = 0;
    if(tblPickList.getValueAt(row,CURFRONT).toString().equals("null")){
      curFront=0;
    }else{
      curFront = Double.parseDouble(tblPickList.getValueAt(row,CURFRONT).toString());
    }
    if(Double.parseDouble(tblPickList.getValueAt(row,column).toString())> curFront){
      flagCheckSOH=true;
      ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1139_FrontRoomNotEnoughGoodsTransfer"));
      requestFocus();
    }
  }

  void tblPickList_keyReleased(KeyEvent e) {
    if (e.getKeyCode()==KeyEvent.VK_ENTER) {
      if(tblPickList.getSelectedRow()<tblPickList.getRowCount()){
        updateRequestQty(tblPickList.getSelectedRow() - 1);
      }
      if(tblPickList.getSelectedRow()==0){
        updateRequestQty(tblPickList.getRowCount()-1);
      }
    }
    if (e.getKeyCode()==KeyEvent.VK_DOWN) {
      if(tblPickList.getSelectedRow()<tblPickList.getRowCount()){
        updateRequestQty(tblPickList.getSelectedRow() - 1);
      }else{
        updateRequestQty(tblPickList.getSelectedRow());
      }
    }
    if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT) {
      updateRequestQty(tblPickList.getSelectedRow());
    }
    if (e.getKeyCode()==KeyEvent.VK_UP) {
      if(tblPickList.getSelectedRow()<0){
        updateRequestQty(tblPickList.getSelectedRow());
      }else{
        updateRequestQty(tblPickList.getSelectedRow()+1);
      }
    }
  }
}

class PanelPickListModify_txtDesc_keyAdapter
    extends KeyAdapter {
  private PanelPickListModify adaptee;
  PanelPickListModify_txtDesc_keyAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtDesc_keyTyped(e);
  }
}

class PanelPickListModify_txtQty_keyAdapter
    extends KeyAdapter {
  private PanelPickListModify adaptee;
  PanelPickListModify_txtQty_keyAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtQty_keyTyped(e);
  }
}

class PanelPickListModify_btnAddGroup_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnAddGroup_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAddGroup_actionPerformed(e);
  }
}

class PanelPickListModify_btnPrint_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnPrint_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrint_actionPerformed(e);
  }
}

class PanelPickListModify_tblPickList_mouseAdapter
    extends MouseAdapter {
  private PanelPickListModify adaptee;
  PanelPickListModify_tblPickList_mouseAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblPickList_mouseClicked(e);
  }
}

class PanelPickListModify_btnReceipt_actionAdapter
    implements ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnReceipt_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnReceipt_actionPerformed(e);
  }
}

class PanelPickListModify_btnDelete_actionAdapter
    implements ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnDelete_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelPickListModify_btnDone_actionAdapter
    implements ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnDone_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelPickListModify_btnAdd_actionAdapter
    implements ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnAdd_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelPickListModify_btnModify_actionAdapter
    implements ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnModify_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelPickListModify_btnExpectedDate_actionAdapter
    implements ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnExpectedDate_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnExpectedDate_actionPerformed(e);
  }
}

class PanelPickListModify_btnCreateDate_actionAdapter
    implements ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnCreateDate_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCreateDate_actionPerformed(e);
  }
}


class PanelPickListModify_btnCancel_actionAdapter
    implements ActionListener {
  private PanelPickListModify adaptee;
  PanelPickListModify_btnCancel_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class PanelPickListModify_btnUPC_actionAdapter implements java.awt.event.ActionListener {
  PanelPickListModify adaptee;

  PanelPickListModify_btnUPC_actionAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnUPC_actionPerformed(e);
  }
}

class PanelPickListModify_txtUPC_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPickListModify adaptee;

  PanelPickListModify_txtUPC_keyAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtUPC_keyTyped(e);
  }
}

class PanelPickListModify_tblPickList_keyAdapter extends java.awt.event.KeyAdapter {
  PanelPickListModify adaptee;

  PanelPickListModify_tblPickList_keyAdapter(PanelPickListModify adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tblPickList_keyPressed(e);
  }
  public void keyReleased(KeyEvent e) {
    adaptee.tblPickList_keyReleased(e);
  }
}





