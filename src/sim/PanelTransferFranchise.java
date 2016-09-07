//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//

































package sim;

/**
 * <p>Title: Franchise's Transfering</p>
 * <p>Description: Performs function of transfering from store to franschise</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM Intersoft VN</p>
 * @PHUONG NGUYEN
 * @version 1.0
 */
import common.*;
import btm.attr.ItemMaster;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import btm.swing.*;
import java.sql.*;
import java.util.Vector;

import com.lowagie.text.pdf.*;
import com.lowagie.text.Table;
import com.lowagie.text.Document;
import com.lowagie.text.Chunk;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import java.io.*;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.PrintPDF;
//not  use
public class PanelTransferFranchise
    extends JPanel {

  DataAccessLayer DAL;
  FrameMainSim frmMain;
  Vector vStoreID = new Vector(); //Store's IDs are saved in vStoreID
  Vector vStoreName = new Vector(); //Store's Names are saved in vStoreName
  Vector vFranID = new Vector(); //Franchise's IDs are saved in vStoreID
  Vector vFranName = new Vector(); //Franchise's Names are saved in vStoreName
  Vector vSqlS = new Vector();
  Vector vSqlF = new Vector();
  //Declares flag boolean variable to track value of radio button option
  boolean flagOption = false; //false if from store ; true if from franchise
  boolean itmID_input=false;//true: input ItemID; false: input artNo
  //Declares Util
  Utils ut = new Utils();
  //
  BorderLayout borderLayout1 = new BorderLayout();
  //Button Panel
  BJPanel btnPanel = new BJPanel(Constant.PANEL_TYPE_HEADER);
  FlowLayout flowLayout1 = new FlowLayout();
  BJButton btnDone = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnDelete = new BJButton();
  BJButton btnView = new BJButton();
  BJButton btnStockCount = new BJButton();
  BJButton btnBack = new BJButton();
  //Body Panel
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel dataPanel = new JPanel();
  //Option Panel
  //Infor Panel
  JPanel jPanel1 = new JPanel();
  //Declare radio buttons
  JRadioButton radiobtnFran = new JRadioButton();
  JRadioButton radiobtnStore = new JRadioButton();

  JPanel txtLPanel = new JPanel();
  JLabel lblFranID = new JLabel();
  JTextField txtCreater = new JTextField();
  JLabel lblReceiver = new JLabel();
  JLabel lblDiscount = new JLabel();
  JLabel lblCreater = new JLabel();
  JPanel lblLPanel = new JPanel();
  JPanel optionPanel = new JPanel();
  JTextField txtDiscount = new JTextField();
  JPanel lblRPanel = new JPanel();
  JPanel leftPanel = new JPanel();
  JPanel infoPanel = new JPanel();
  JLabel lblItemID = new JLabel();
  JLabel lblDate = new JLabel();
  JTextField txtFranID = new JTextField();
  JLabel lblQuantity = new JLabel();
  JTextField txtReceiver = new JTextField();
  JPanel txtRPanel = new JPanel();
  JTextField txtDate = new JTextField();
  JComboBox cboToFran = new JComboBox();
  JLabel lblTo = new JLabel();
  JLabel lblFrom = new JLabel();
  JTextField txtItemID = new JTextField();
  JComboBox cboFromStore = new JComboBox();
  JPanel rightPanel = new JPanel();
  JTextField txtQuantity = new JTextField();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable tbl = new BJTable(dm, true);
  JButton btnSearchItem = new JButton();
  JLabel lblPer = new JLabel();
  String itemName = "";
  String artNo = "";
  String size = "";
  String color = "";
  long new_price = 0;
  long sohStore = 0; //quantity of store's soh
  long sohFran = 0; //quantity of franchise's soh
  Statement stmt;
  FlowLayout flowLayout2 = new FlowLayout(FlowLayout.CENTER, 0, 0);
  FlowLayout flowLayout3 = new FlowLayout(FlowLayout.CENTER, 0, 0);
  FlowLayout flowLayout4 = new FlowLayout(FlowLayout.CENTER, 0, 0);
  FlowLayout flowLayout5 = new FlowLayout(FlowLayout.CENTER, 0, 0);
  FlowLayout flowLayout6 = new FlowLayout();
  JLabel lblArtNo = new JLabel();
  JTextField txtArtNo = new JTextField();
  JLabel lblSize = new JLabel();
  JTextField txtSize = new JTextField();
  StripedTableCellRenderer stripedTable = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);

  public PanelTransferFranchise(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  private void jbInit() throws Exception {
//    System.out.println("N20");
    registerKeyboardActions();
    jPanel1.setLayout(flowLayout6);
    txtQuantity.setFont(new java.awt.Font("Dialog", 1, 11));
    txtQuantity.setPreferredSize(new Dimension(240, 25));
    txtQuantity.setText("");
    txtQuantity.setBounds(new Rectangle(3, 61, 223, 21));
    txtQuantity.addFocusListener(new PanelTransferFranchise_txtQuantity_focusAdapter(this));
    txtQuantity.addKeyListener(new
                               PanelTransferFranchise_txtQuantity_keyAdapter(this));
    txtQuantity.setHorizontalAlignment(SwingConstants.LEADING);
    rightPanel.setLayout(flowLayout4);
    rightPanel.setPreferredSize(new Dimension(400, 200));
    rightPanel.setMinimumSize(new Dimension(189, 160));
    rightPanel.setBackground(Color.lightGray);
    rightPanel.setMaximumSize(new Dimension(189, 160));
    cboFromStore.setFont(new java.awt.Font("Dialog", 1, 11));
    cboFromStore.setPreferredSize(new Dimension(240, 25));
    cboFromStore.setActionCommand("comboBoxChanged");
    cboFromStore.setBounds(new Rectangle(4, 33, 216, 21));
    //Set methods for Item ID textfield
    txtItemID.setBounds(new Rectangle(3, 7, 180, 21));
    txtItemID.addKeyListener(new PanelTransferFranchise_txtItemID_keyAdapter(this));
    txtItemID.setHorizontalAlignment(SwingConstants.LEADING);
    txtItemID.setText("");
    txtItemID.setFont(new java.awt.Font("Dialog", 1, 11));
    txtItemID.setPreferredSize(new Dimension(210, 25));
    //Set methods for Franchise ID label
    lblFranID.setVerticalTextPosition(SwingConstants.TOP);
    lblFranID.setBounds(new Rectangle(13, 7, 110, 20));
    lblFranID.setVerticalAlignment(SwingConstants.CENTER);
    lblFranID.setText("Transfer ID:");
    lblFranID.setHorizontalTextPosition(SwingConstants.RIGHT);
    lblFranID.setPreferredSize(new Dimension(150, 25));
    lblFranID.setHorizontalAlignment(SwingConstants.LEADING);
    lblFranID.setMinimumSize(new Dimension(50, 16));
    lblFranID.setMaximumSize(new Dimension(50, 16));
    lblFranID.setAlignmentX( (float) 0.5);
    lblFranID.setFont(new java.awt.Font("SansSerif", 1, 12));
    //Set methods for From Franchise label
    lblFrom.setMinimumSize(new Dimension(50, 16));
    lblFrom.setMaximumSize(new Dimension(50, 16));
    lblFrom.setAlignmentX( (float) 0.0);
    lblFrom.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblFrom.setPreferredSize(new Dimension(150, 25));
    lblFrom.setHorizontalAlignment(SwingConstants.LEADING);
    lblFrom.setText("From Store/Franchise:");
    lblFrom.setVerticalAlignment(SwingConstants.CENTER);
    lblFrom.setVerticalTextPosition(SwingConstants.TOP);
    lblFrom.setBounds(new Rectangle(13, 32, 110, 20));
    lblFrom.setHorizontalAlignment(SwingConstants.LEADING);
    lblFrom.setHorizontalTextPosition(SwingConstants.TRAILING);
    //Set methods for To Franchise label
    lblTo.setBackground(Color.white);
    lblTo.setMinimumSize(new Dimension(50, 16));
    lblTo.setMaximumSize(new Dimension(50, 16));
    lblTo.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblTo.setPreferredSize(new Dimension(150, 25));
    lblTo.setHorizontalAlignment(SwingConstants.LEADING);
    lblTo.setHorizontalTextPosition(SwingConstants.RIGHT);
    lblTo.setText("To Franchise:");
    lblTo.setVerticalAlignment(SwingConstants.CENTER);
    lblTo.setVerticalTextPosition(SwingConstants.TOP);
    lblTo.setBounds(new Rectangle(13, 57, 110, 20));
    //Set methods for To Franchise Combobox
    cboToFran.setFont(new java.awt.Font("Dialog", 1, 11));
    cboToFran.setPreferredSize(new Dimension(240, 25));
    cboToFran.setBounds(new Rectangle(4, 59, 216, 21));
    //Set methods for Date textfield
    txtDate.setBackground(new Color(212, 208, 200));
    txtDate.setFont(new java.awt.Font("Dialog", 1, 11));
    txtDate.setPreferredSize(new Dimension(240, 25));
    txtDate.setEditable(false);
    txtDate.setText(ut.getSystemDate(1));
    txtDate.setHorizontalAlignment(SwingConstants.LEADING);
    txtDate.setBounds(new Rectangle(3, 114, 223, 21));
    txtRPanel.setLayout(null);
    //Set methods for Receiver textfield
    txtReceiver.setBounds(new Rectangle(4, 110, 216, 21));
    txtReceiver.addKeyListener(new PanelTransferFranchise_txtReceiver_keyAdapter(this));
    txtReceiver.setHorizontalAlignment(SwingConstants.LEADING);
    txtReceiver.setText("");
    txtReceiver.setFont(new java.awt.Font("Dialog", 1, 11));
    txtReceiver.setPreferredSize(new Dimension(240, 25));
    //Set methods for Quantity label
    lblQuantity.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblQuantity.setHorizontalAlignment(SwingConstants.LEADING);
    lblQuantity.setBounds(new Rectangle(0, 60, 110, 20));
    lblQuantity.setVerticalTextPosition(SwingConstants.TOP);
    lblQuantity.setVerticalAlignment(SwingConstants.CENTER);
    lblQuantity.setText("Quantity:");
    lblQuantity.setHorizontalAlignment(SwingConstants.LEADING);
    lblQuantity.setPreferredSize(new Dimension(150, 25));
    lblQuantity.setFont(new java.awt.Font("SansSerif", 1, 12));
    //Set methods for Unit Price textfield
    //Set methods for Franchise ID textfield
    txtFranID.setBounds(new Rectangle(4, 6, 216, 21));
    txtFranID.setHorizontalAlignment(SwingConstants.LEADING);
    txtFranID.setText("");
    txtFranID.setColumns(0);
    txtFranID.setFont(new java.awt.Font("Dialog", 1, 11));
    txtFranID.setOpaque(true);
    txtFranID.setPreferredSize(new Dimension(240, 25));
    txtFranID.setDisabledTextColor(Color.gray);
    txtFranID.setEditable(false);
    lblDate.setMinimumSize(new Dimension(50, 16));
    lblDate.setMaximumSize(new Dimension(50, 16));
    lblDate.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblDate.setPreferredSize(new Dimension(150, 25));
    lblDate.setHorizontalAlignment(SwingConstants.LEADING);
    lblDate.setHorizontalTextPosition(SwingConstants.RIGHT);
    lblDate.setText("Created Date:");
    lblDate.setVerticalAlignment(SwingConstants.CENTER);
    lblDate.setBounds(new Rectangle(0, 112, 110, 20));
    lblDate.setVerticalTextPosition(SwingConstants.TOP);
    lblDate.setHorizontalAlignment(SwingConstants.LEADING);
    lblItemID.setBounds(new Rectangle(0, 8, 110, 20));
    lblItemID.setVerticalTextPosition(SwingConstants.TOP);
    lblItemID.setVerticalAlignment(SwingConstants.CENTER);
    lblItemID.setText("Item ID: ");
    lblItemID.setHorizontalAlignment(SwingConstants.LEADING);
    lblItemID.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblItemID.setPreferredSize(new Dimension(150, 25));
    lblItemID.setFont(new java.awt.Font("SansSerif", 1, 12));
    infoPanel.setLayout(flowLayout2);
    infoPanel.setPreferredSize(new Dimension(800, 150));
    infoPanel.setVerifyInputWhenFocusTarget(true);
    infoPanel.setForeground(Color.black);
    leftPanel.setPreferredSize(new Dimension(400, 200));
    leftPanel.setVisible(true);
    leftPanel.setMaximumSize(new Dimension(189, 160));
    leftPanel.setMinimumSize(new Dimension(189, 160));
    leftPanel.setLocale(java.util.Locale.getDefault());
    leftPanel.setBackground(Color.lightGray);
    leftPanel.setLayout(flowLayout3);
    lblRPanel.setLayout(null);
    lblRPanel.setPreferredSize(new Dimension(150, 150));
    lblRPanel.setMinimumSize(new Dimension(221, 26));
    lblRPanel.setMaximumSize(new Dimension(32767, 32767));
    //Set methods for discount textfield
    txtDiscount.setFont(new java.awt.Font("Dialog", 1, 11));
    txtDiscount.setPreferredSize(new Dimension(220, 25));
    txtDiscount.setText("");
    txtDiscount.setBounds(new Rectangle(3, 87, 197, 21));
    txtDiscount.addKeyListener(new
                               PanelTransferFranchise_txtDiscount_keyAdapter(this));
    txtDiscount.setHorizontalAlignment(SwingConstants.LEADING);
    txtDiscount.setDisabledTextColor(Color.gray);
    txtDiscount.setEditable(false);
    txtDiscount.setSelectionStart(0);
    optionPanel.setLayout(flowLayout5);
    optionPanel.setPreferredSize(new Dimension(800, 30));
    optionPanel.setMinimumSize(new Dimension(10, 10));
    optionPanel.setBackground(UIManager.getColor(
        "InternalFrame.inactiveTitleGradient"));
    lblLPanel.setLayout(null);
    lblLPanel.setPreferredSize(new Dimension(150, 150));
    lblLPanel.setMinimumSize(new Dimension(231, 26));
    lblCreater.setHorizontalAlignment(SwingConstants.LEADING);
    lblCreater.setVerticalTextPosition(SwingConstants.TOP);
    lblCreater.setBounds(new Rectangle(13, 82, 110, 20));
    lblCreater.setVerticalAlignment(SwingConstants.CENTER);
    lblCreater.setText("Creater Name:");
    lblCreater.setHorizontalTextPosition(SwingConstants.RIGHT);
    lblCreater.setHorizontalAlignment(SwingConstants.LEADING);
    lblCreater.setPreferredSize(new Dimension(150, 25));
    lblCreater.setMinimumSize(new Dimension(50, 16));
    lblCreater.setMaximumSize(new Dimension(50, 16));
    lblCreater.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblDiscount.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblDiscount.setPreferredSize(new Dimension(150, 25));
    lblDiscount.setHorizontalAlignment(SwingConstants.LEADING);
    lblDiscount.setText("Discount:");
    lblDiscount.setVerticalAlignment(SwingConstants.CENTER);
    lblDiscount.setBounds(new Rectangle(0, 86, 110, 20));
    lblDiscount.setVerticalTextPosition(SwingConstants.TOP);
    lblDiscount.setHorizontalAlignment(SwingConstants.LEADING);
    lblDiscount.setHorizontalTextPosition(SwingConstants.TRAILING);
    lblReceiver.setMinimumSize(new Dimension(50, 16));
    lblReceiver.setMaximumSize(new Dimension(50, 16));
    lblReceiver.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblReceiver.setPreferredSize(new Dimension(150, 25));
    lblReceiver.setHorizontalAlignment(SwingConstants.LEADING);
    lblReceiver.setHorizontalTextPosition(SwingConstants.RIGHT);
    lblReceiver.setText("Receiver Name:");
    lblReceiver.setVerticalAlignment(SwingConstants.CENTER);
    lblReceiver.setBounds(new Rectangle(13, 107, 110, 20));
    lblReceiver.setVerticalTextPosition(SwingConstants.TOP);
    lblReceiver.setHorizontalAlignment(SwingConstants.LEADING);

    //Associate the two radio buttons with a button group
    ButtonGroup btngrp = new ButtonGroup();
    //Set methods for Percent label
    lblPer.setPreferredSize(new Dimension(20, 25));
    lblPer.setText("%");
    lblPer.setBounds(new Rectangle(205, 86, 19, 21));
    btnAdd.addActionListener(new PanelTransferFranchise_btnAdd_actionAdapter(this));
    btnDelete.addActionListener(new
                                PanelTransferFranchise_btnDelete_actionAdapter(this));
    txtCreater.setFont(new java.awt.Font("Dialog", 1, 11));
    txtCreater.setEditable(false);
    btnDone.addActionListener(new PanelTransferFranchise_btnDone_actionAdapter(this));
    txtRPanel.setPreferredSize(new Dimension(250, 150));
    btnSearchItem.setPreferredSize(new Dimension(30, 25));
    btnPanel.setPreferredSize(new Dimension(800, 47));
    dataPanel.setPreferredSize(new Dimension(800, 554));
    btnView.addActionListener(new PanelTransferFranchise_btnView_actionAdapter(this));
    btnStockCount.setPreferredSize(new Dimension(120, 37));
    btnStockCount.setToolTipText("Stock Count (F5)");
    btnStockCount.setActionCommand("btnStockCount");
    btnStockCount.setText("<html><center><b>Stock Count</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F5</html>");
    btnStockCount.addActionListener(new PanelTransferFranchise_btnStockCount_actionAdapter(this));
    btnView.setActionCommand("<html><center><b>View</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnBack.setForeground(Color.white);
    btnBack.setSelected(false);
    lblArtNo.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblArtNo.setPreferredSize(new Dimension(34, 15));
    lblArtNo.setText("UPC:");
    lblArtNo.setBounds(new Rectangle(0, 34, 110, 20));
    txtArtNo.setFont(new java.awt.Font("Dialog", 1, 11));
    txtArtNo.setText("");
    txtArtNo.setBounds(new Rectangle(3, 34, 112, 21));
    txtArtNo.addKeyListener(new PanelTransferFranchise_txtArtNo_keyAdapter(this));
    lblSize.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblSize.setText("Size:");
    lblSize.setBounds(new Rectangle(118, 34, 40, 21));
    txtSize.setFont(new java.awt.Font("Dialog", 1, 11));
    txtSize.setText("");
    txtSize.setBounds(new Rectangle(147, 35, 79, 21));
    txtSize.addKeyListener(new PanelTransferFranchise_txtSize_keyAdapter(this));
    btngrp.add(radiobtnStore);
    btngrp.add(radiobtnFran);
    //Set methods for Store radio button
    radiobtnStore.setVerticalTextPosition(SwingConstants.CENTER);
    radiobtnStore.setVerticalAlignment(SwingConstants.CENTER);
    radiobtnStore.setText("Transfer from Store to Franchise");
    radiobtnStore.setSelected(true);
    radiobtnStore.setMargin(new Insets(2, 2, 6, 6));
    radiobtnStore.setHorizontalAlignment(SwingConstants.CENTER);
    radiobtnStore.setBorderPainted(false);
    radiobtnStore.setActionCommand("radiobtnStore");
    radiobtnStore.setPreferredSize(new Dimension(400, 30));
    radiobtnStore.setDebugGraphicsOptions(0);
    radiobtnStore.setEnabled(true);
    radiobtnStore.setForeground(Color.black);
    //Set methods for Franchise Radio button
    radiobtnFran.setText("Transfer from franchise to other");
    radiobtnFran.setHorizontalAlignment(SwingConstants.CENTER);
    radiobtnFran.setActionCommand("radiobtnFran");
    radiobtnFran.setPreferredSize(new Dimension(400, 30));
    radiobtnFran.setMinimumSize(new Dimension(189, 27));
    radiobtnFran.setMaximumSize(new Dimension(189, 27));
    radiobtnFran.setDoubleBuffered(false);
    radiobtnFran.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
    //Register Listener for Store radio button
    radiobtnStore.addItemListener(new
                                  PanelTransferFranchise_radiobtnStore_itemAdapter(this));
    //Register Listener for Franchise radio button
    radiobtnFran.addItemListener(new
                                 PanelTransferFranchise_radiobtnFran_itemAdapter(this));
    //Set methods for Creater Textfield
    txtCreater.setBounds(new Rectangle(4, 85, 216, 21));
    txtCreater.setHorizontalAlignment(SwingConstants.LEADING);
    txtCreater.setText("");
    txtCreater.setPreferredSize(new Dimension(240, 25));

    txtLPanel.setLayout(null);
    txtLPanel.setPreferredSize(new Dimension(250, 150));

    this.setLayout(borderLayout1);
    btnPanel.setLayout(flowLayout1);
    dataPanel.setLayout(borderLayout5);
    btnBack.setActionCommand("btnBack");
    this.setDoubleBuffered(true);
    this.setPreferredSize(new Dimension(800, 600));
    jPanel1.setPreferredSize(new Dimension(800, 180));
    btnSearchItem.setBounds(new Rectangle(187, 7, 37, 21));
    btnSearchItem.setFont(new java.awt.Font("Dialog", 1, 11));
    btnSearchItem.setForeground(Color.black);
    btnSearchItem.setSelected(false);
    btnSearchItem.setText("...");
    btnSearchItem.addActionListener(new
                                    PanelTransferFranchise_btnSearchItem_actionAdapter(this));
    btnPanel.add(btnDone, null);
    btnPanel.add(btnAdd, null);
    btnPanel.add(btnDelete, null);
    btnPanel.add(btnView, null);
    btnPanel.add(btnStockCount, null);
    btnPanel.add(btnBack, null);
    this.add(dataPanel, BorderLayout.CENTER);
    // Button Done
    btnDone.setText("<html><center><b>Done</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.setToolTipText("Done(F1)");
    btnDone.setPreferredSize(new Dimension(120, 37));
    this.add(btnPanel, BorderLayout.NORTH);
    // Button Add
    btnAdd.setText("<html><center><b>Add</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                   "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.setToolTipText("Add(F2)");
    btnAdd.setPreferredSize(new Dimension(120, 37));
    // Button Delete
    btnDelete.setText("<html><center><b>Delete</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                      "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnDelete.setToolTipText("Delete(F3)");
    btnDelete.setPreferredSize(new Dimension(120, 37));
    // Button View
    btnView.setText("<html><center><b>View Transfer</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbs" +
    "p;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnView.setToolTipText("View(F4)");
    btnView.setPreferredSize(new Dimension(120, 37));
    // Button Back
    btnBack.setPreferredSize(new Dimension(120, 37));
    btnBack.setToolTipText("Back (F6)");
    btnBack.setText("<html><center><b>Back</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F6</html>");
    btnBack.addActionListener(new
                                PanelTransferFranchise_btnBack_actionAdapter(this));
    dataPanel.add(jPanel1, BorderLayout.NORTH);
    optionPanel.add(radiobtnStore, null);
    optionPanel.add(radiobtnFran, null);
    dataPanel.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tbl, null);
    tbl.setAutoResizeMode(tbl.AUTO_RESIZE_ALL_COLUMNS);
    tbl.getTableHeader().setReorderingAllowed(false);
    tbl.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 13));
    dm.addColumn("Item ID");
    dm.addColumn("UPC ");
    dm.addColumn("Item Name");
    dm.addColumn("Size");
    dm.addColumn("Color");
    dm.addColumn("Quantity");
    dm.addColumn("Unit Price");
    dm.addColumn("% Discount");
    dm.addColumn("Discount Amt");
    jPanel1.add(optionPanel, null);
    jPanel1.add(infoPanel, null);
    infoPanel.add(leftPanel, null);
    lblLPanel.add(lblReceiver, null);
    lblLPanel.add(lblFranID, null);
    lblLPanel.add(lblFrom, null);
    lblLPanel.add(lblTo, null);
    lblLPanel.add(lblCreater, null);
    leftPanel.add(lblLPanel, null);
    leftPanel.add(txtLPanel, null);
    txtLPanel.add(txtFranID, null);
    txtLPanel.add(txtCreater, null);
    txtLPanel.add(txtReceiver, null);
    txtLPanel.add(cboFromStore, null);
    txtLPanel.add(cboToFran, null);
    infoPanel.add(rightPanel, null);
    rightPanel.add(lblRPanel, null);
    txtRPanel.add(txtItemID, null);
    txtRPanel.add(btnSearchItem, null);
    txtRPanel.add(txtQuantity, null);
    txtRPanel.add(txtDiscount, null);
    txtRPanel.add(lblPer, null);
    txtRPanel.add(txtDate, null);
    txtRPanel.add(txtArtNo, null);
    lblRPanel.add(lblItemID, null);
    lblRPanel.add(lblDate, null);
    lblRPanel.add(lblQuantity, null);
    lblRPanel.add(lblDiscount, null);
    lblRPanel.add(lblArtNo, null);
    rightPanel.add(txtRPanel, null);
    txtRPanel.add(lblSize, null);
    txtRPanel.add(txtSize, null);
    tbl.setColumnWidth(0,70);
    tbl.setColumnWidth(1,25);
    tbl.setColumnWidth(2,160);
    tbl.setColumnWidth(3,30);
    tbl.setColumnWidth(4,40);
    tbl.setColumnWidth(5,40);
    tbl.setColumnWidth(6,40);
    tbl.setColumnWidth(7,50);
    tbl.setColumnWidth(8,65);
  }
  void initScreen() {
    try {
      //defaut store optioned
      flagOption = false;
      //show generated FranID
      txtFranID.setText(generateFranID());
      //activate objects
      txtItemID.setText("");
      txtQuantity.setText("");
      txtReceiver.setText("");
      txtReceiver.setEditable(true);
      txtDiscount.setText("");
      txtArtNo.setText("");
      txtSize.setText("");
      cboFromStore.setEnabled(true);
      cboToFran.setEnabled(true);
      radiobtnStore.setEnabled(true);
      radiobtnFran.setEnabled(true);
      //set default value of radio button option
      radiobtnStore.setSelected(true);
      //add franchises into combobox From
      vStoreID.clear();
      vStoreName.clear();
      getStore();
      showData(vStoreName, cboFromStore);
      cboFromStore.setSelectedItem(vStoreName.elementAt(vStoreID.indexOf(DAL.getStoreID())));
      //add franchises into combobox To
      vFranID.clear();
      vFranName.clear();
      getFranchise();
      showData(vFranName, cboToFran);
      //set Employee Name as a Creater
      txtCreater.setText(getEmpName(DAL.getEmpID()));
      txtReceiver.requestFocus(true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  class PanelTransferFranchise_btnBack_actionAdapter
      implements java.awt.event.ActionListener {
    PanelTransferFranchise adaptee;
    PanelTransferFranchise_btnBack_actionAdapter(PanelTransferFranchise
        adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.btnBack_actionPerformed(e);
    }
  }

  void btnBack_actionPerformed(ActionEvent e) {
    while (tbl.getRowCount() > 0) {
      dm.removeRow(0);
    }
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showInventManageButton();
//    frmMain.pnlMainSim.btnFranchise.requestFocus(true);
    frmMain.setTitle("JSIM - Inventory Management");
  }

  class PanelTransferFranchise_btnSearchItem_actionAdapter
      implements java.awt.event.ActionListener {
    PanelTransferFranchise adaptee;
    PanelTransferFranchise_btnSearchItem_actionAdapter(PanelTransferFranchise
        adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
      DialogItemLookup dialogItemLookup = new DialogItemLookup(frmMain,
          "JSIM - Item Lookup", true, frmMain, itemBusObject);
      dialogItemLookup.setVisible(true);
      if (dialogItemLookup.done) {
        txtItemID.setText(dialogItemLookup.getItemID());
        txtItemID.requestFocus(true);
      }
      else {
        txtItemID.requestFocus(true);
      }
    }
  }

  //Get Employee name - Creater Name
  private String getEmpName(String empID) {
//        String empID = DAL.getEmpID();
    String empName = new String();
    String sql =
        "select first_name, last_name from btm_im_employee where emp_id ='" +
        empID + "'";
    try {
      ResultSet rs = null;
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        empName = (rs.getString("first_name").trim() + " " +
                   rs.getString("last_name").trim());
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return empName;
  }

  //get Franchaise data
  void getFranchise() {
    String sql =
        "select cust_id, first_name from btm_im_customer where cust_flag = 1";
    ResultSet rs = null;
    try {
      rs = DAL.executeQueries(sql);
      while (rs.next()) {
        vFranID.addElement(rs.getString("cust_id"));
        vFranName.addElement(rs.getString("first_name"));
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //get Store data
  void getStore() {
    String sql =
        "select store_id, name from btm_im_store where store_type = 'D'";
    ResultSet rs = null;
    try {
      rs = DAL.executeQueries(sql);
      while (rs.next()) {
        vStoreID.addElement(rs.getString("store_id"));
        vStoreName.addElement(rs.getString("name"));
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Show data on Combobox
  void showData(Vector v, javax.swing.JComboBox cbo) {
    cbo.removeAllItems();
    for (int i = 0; i < v.size(); i++) {
      cbo.addItem(v.elementAt(i));
    }
  }

  //get unit price of selected item
  void getUnitPrice(String itemID, String storeID) {
    String sql = "select new_price from btm_im_price_hist " +
        "where item_id = '" + itemID + "' and store_id = " + storeID +
        " and status = '1'";
    ResultSet rs = null;
    try {
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        this.new_price = Long.parseLong(rs.getString("new_price").toString());
      }
//      else {
//        ut.showMessage(frmMain, "Warning",
//                       "Selected Item hasn't updated price yet");
//      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  //Generate Franchise ID
  private String generateFranID() {
    String str = DAL.getStoreID() + ut.getSystemDateTime5();
    str = str.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
    return str;
  }

  //check if item is available in selected store
  boolean checkItemInStore(String itemID, String storeID) {
    ResultSet rs = null;
    ResultSet rs1 = null;
    String sql =
        "select i.item_id, i.item_name, i.art_no, BACK_ROOM_QTY " +
        "from btm_im_item_master i, btm_im_item_loc_soh ils " +
        "where i.item_id = ils.item_id and i.item_id = '" + itemID +
        "' and store_id = " + storeID;
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        this.itemName = rs.getString("item_name");
        this.artNo = rs.getString("art_no");
        this.sohStore = rs.getLong("BACK_ROOM_QTY");
        sql = "";
        sql = " select attr_dtl_desc color, (select attr_dtl_desc from btm_im_attr_dtl a, btm_im_item_master i  " +
            "where i.attr2_dtl_id = a.attr_dtl_id and item_id ='" + itemID +
            "') ssize " +
            "from btm_im_attr_dtl a, btm_im_item_master i " +
            "where i.attr1_dtl_id = a.attr_dtl_id and item_id ='" + itemID +
            "'";
        rs1 = DAL.executeQueries(sql);
        if (rs1.next()) {
          this.size = rs1.getString("ssize");
          this.color = rs1.getString("color");
        }
        rs1.getStatement().close();
        rs.close();
        stmt.close();
        return true;
      }
        rs.close();
        stmt.close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
      return false;
  }

  //check if item is available in selected franchise
  boolean checkItemInFran(String itemID, String franID) {
    ResultSet rs = null;
    String sql =
        "select item_id, franchise_id, back_room_qty " +
        "from btm_im_item_fran_soh " +
        "where item_id = " + itemID + " and franchise_id = " + franID;
    try {
      rs = DAL.executeScrollQueries(sql);
      if (rs.next()){
        if (checkItemInStore(itemID, "1200")) { //get info about size, color...
          this.sohFran = rs.getLong("back_room_qty");
          rs.getStatement().close();
          return true;
        }
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return false;
  }

  void radiobtnStore_itemStateChanged(ItemEvent e) {
    flagOption = false;
    showData(vStoreName, cboFromStore);
    txtReceiver.requestFocus(true);
    txtDiscount.setText("");
    txtDiscount.setDisabledTextColor(Color.gray);
    txtDiscount.setEditable(false);
  }

  void radiobtnFran_itemStateChanged(ItemEvent e) {
    flagOption = true;
    showData(vFranName, cboFromStore);
    txtReceiver.requestFocus(true);
    txtDiscount.setDisabledTextColor(Color.white);
    txtDiscount.setEditable(true);
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    String franchiseDoId = "";
    String fromStore = "";
    String fromFranchise = "";
    String toFranchise = "";
    String userID = "";
    String receiverName = "";
//    String DO_Type = "";
    String item_ID = "";
    String franchiseDoDate = "";

    boolean itmID_input=false;//true: input ItemID; false: input artNo

    double discount = 0;
    double discountPer = 0;
    long qty = 0;
    int flag = 0;
//    boolean itmID_input=false;//true: input ItemID; false: input artNo
    if (txtReceiver.getText().equalsIgnoreCase("")){
      ut.showMessage(frmMain,"Warning","Receiver Name is not null");
      txtReceiver.requestFocus(true);
      return;
    }
//    if (txtItemID.getText().equalsIgnoreCase("")) {
//      ut.showMessage(frmMain, "Warning", "Item ID is not null");
//      txtItemID.requestFocus(true);
//      return;
//    }
//    if (!txtItemID.getText().equalsIgnoreCase("") && !ut.isDoubleString(txtItemID.getText())) {
//      ut.showMessage(frmMain, "Warning", "Item ID must be number");
//      txtItemID.setText("");
//      txtItemID.requestFocus(true);
//      return;
//    }
    if (!txtItemID.getText().equalsIgnoreCase("")) {
      item_ID = txtItemID.getText();
      if(!isValidable(item_ID)){
        ut.showMessage(frmMain,"Warning","Item ID is invalid. Please input again.");
        txtItemID.selectAll();
        txtItemID.requestFocus(true);
        return;
      }
      itmID_input=true;
    }
   if(!txtArtNo.getText().equalsIgnoreCase("") && !txtSize.getText().equalsIgnoreCase("")){
      item_ID=getItemID(txtArtNo.getText(),txtSize.getText());
    }
   if (!txtArtNo.getText().equalsIgnoreCase("") && txtSize.getText().equalsIgnoreCase("")){
      item_ID=getItemID(txtArtNo.getText());
    }
   if(item_ID.equalsIgnoreCase("")){
      ut.showMessage(frmMain,"Warning","Product's information (Item_ID/ArtNo-Size) is invalid. Please input again.");
//      if(itmID_input==true){
        txtItemID.setText("");
        txtItemID.requestFocus(true);
//      }else {
        txtArtNo.setText("");
        txtSize.setText("");
//        txtArtNo.requestFocus(true);
//      }
      return;
    }
    if (txtQuantity.getText().equalsIgnoreCase("")) {
      ut.showMessage(frmMain, "Warning", "Quantity is not null");
      txtQuantity.requestFocus(true);
      return;
    }
//    if (!ut.isDoubleString(txtQuantity.getText())) {
//      ut.showMessage(frmMain, "Warning", "Quantity must be a number");
//      txtQuantity.setText("");
//      txtQuantity.requestFocus(true);
//      return;
//    }
    if (txtQuantity.getText().equalsIgnoreCase("0")) {
      ut.showMessage(frmMain, "Warning", "Quantity must be greater than 0");
      txtQuantity.setText("");
      txtQuantity.requestFocus(true);
      return;
    }
    franchiseDoId = txtFranID.getText();
    qty = Long.parseLong(txtQuantity.getText());
//    new_price=Long.parseLong(getItemPrice(item_ID,DAL));
    getUnitPrice(item_ID, DAL.getStoreID());
    if (flagOption == true) { //transfer from franchise to franchise
      if (!checkItemInFran(item_ID,//txtItemID.getText()
                           vFranID.elementAt(cboFromStore.getSelectedIndex()).
                           toString())) {
        ut.showMessage(frmMain, "Warning",
                       "Item id does not available in Franchise's Stock! Please choose again");
        txtQuantity.setText("");
        txtDiscount.setText("");
        if(itmID_input==true){
          txtItemID.setText("");
          txtItemID.requestFocus(true);
        }else{
          txtArtNo.setText("");
          txtSize.setText("");
          txtArtNo.requestFocus(true);
        }
        return;
      }
      if (!txtQuantity.getText().equalsIgnoreCase("")) {
        if (Long.parseLong(txtQuantity.getText()) > this.sohFran) {
          ut.showMessage(frmMain, "Warning",
                         "There is not enough quantity in stock on hand");
          txtQuantity.selectAll();
          txtQuantity.requestFocus(true);
          return;
        }
      }
      if (!txtDiscount.getText().equalsIgnoreCase("")) {
        discountPer = Double.parseDouble(txtDiscount.getText());
      }
      if (cboFromStore.getSelectedItem().toString().equalsIgnoreCase(cboToFran.
          getSelectedItem().toString())) {
        flag = 3;
      }
      else {
        flag = 4;
      }
      radiobtnStore.setEnabled(false);
//      getUnitPrice(item_ID, "1200");
      discount = (qty * new_price * discountPer) / 100;
      fromFranchise = vFranID.elementAt(cboFromStore.getSelectedIndex()).
          toString();

    }
    else if (flagOption == false) { //transfer from store to franchise
      if (!checkItemInStore(item_ID, //txtItemID.getText()
                            vStoreID.elementAt(cboFromStore.getSelectedIndex()).
                            toString())) {
        ut.showMessage(frmMain, "Warning",
                       "Item id does not available in Store's Stock! Please choose again");
//        txtQuantity.setText("");
        if(itmID_input==true){
          txtItemID.setText("");
          txtItemID.requestFocus(true);
        }else{
          txtArtNo.setText("");
          txtSize.setText("");
          txtArtNo.requestFocus(true);
        }
        return;
      }
      if (!txtQuantity.getText().equalsIgnoreCase("")) {
        if (Long.parseLong(txtQuantity.getText()) > this.sohStore) {
          ut.showMessage(frmMain, "Warning",
                         "There is not enough quantity in stock on hand");
          txtQuantity.selectAll();
          txtQuantity.requestFocus(true);
          return;
        }
      }
      flag = 2;
      radiobtnFran.setEnabled(false);
//      new_price = 0;
      discount = 0;
      fromStore = vStoreID.elementAt(cboFromStore.getSelectedIndex()).toString();

    }

    if (tbl.getRowCount() < 1) {
      toFranchise = vFranID.elementAt(cboToFran.getSelectedIndex()).toString();
      userID = DAL.getEmpID();
      receiverName = txtReceiver.getText();
      franchiseDoDate = txtDate.getText();
      if (flagOption == true) { //transfer from franchise to franchise
        vSqlF.addElement("insert into btm_im_franchise_do values('" +
                         franchiseDoId +
                         "','','" + fromFranchise + "','" + toFranchise +
                         "','" + userID +
                         "','" + receiverName + "','" + flag +
                         "', to_date('" + franchiseDoDate + "', 'dd/mm/yyyy'))");
      }
      else if (flagOption == false) { //transfer from store to franchise
        vSqlS.addElement("insert into btm_im_franchise_do values('" +
                         franchiseDoId +
                         "','" + fromStore + "','','" + toFranchise + "','" +
                         userID +
                         "','" + receiverName + "','" + flag +
                         "', to_date('" + franchiseDoDate + "', 'dd/mm/yyyy'))");
      }
      cboFromStore.setEnabled(false);
      cboToFran.setEnabled(false);
      txtReceiver.setEditable(false);
    }
    if (checkItemTable(item_ID, qty, discountPer)) {
      //set focus for last row
       if(tbl.getRowCount()>0){
         tbl.setRowSelectionInterval(tbl.getRowCount()-1, tbl.getRowCount()-1);
       }

       //show current row
       Rectangle bounds = tbl.getCellRect(tbl.getRowCount()-1,0,true);
       jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

      txtQuantity.setText("");
      txtDiscount.setText("");
      if(itmID_input==true){
        txtItemID.setText("");
        txtItemID.requestFocus(true);
      }else {
        txtArtNo.setText("");
        txtSize.setText("");
        txtArtNo.requestFocus(true);
      }
      return;
    }
    Object[] rowData = new Object[] {
        item_ID, artNo, itemName, size, color, new Long(qty),
        new Long(new_price), new Double(discountPer), new Double(discount)};
    dm.addRow(rowData);

    float[] f1 = new float[3];
    Color.RGBtoHSB(255, 255, 230, f1);
    stripedTable.installInTable(tbl, Color.getHSBColor(f1[0], f1[1], f1[2]),
                                Color.black, null, null);

   //set focus for last row
    if(tbl.getRowCount()>0){
      tbl.setRowSelectionInterval(tbl.getRowCount()-1, tbl.getRowCount()-1);
    }

    //show current row
    Rectangle bounds = tbl.getCellRect(tbl.getRowCount()-1,0,true);
    jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

    txtQuantity.setText("");
    txtDiscount.setText("");
    if(itmID_input==true){
      txtItemID.setText("");
      txtItemID.requestFocus(true);
    }else {
//      txtArtNo.setText("");
      txtArtNo.selectAll();
      txtSize.setText("");
      txtArtNo.requestFocus(true);
    }
  }

  //check an item is available or not
  boolean isValidable(String itemId){
    String SQL="select ITEM_ID from BTM_IM_ITEM_MASTER where ITEM_ID='"+ itemId +"'";
    ResultSet rs=null;
    try{
      rs=DAL.executeQueries(SQL);
      if(rs.next()){
        rs.getStatement().close();
        return true;
      }
      rs.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }
  //check item in table
  boolean checkItemTable(String itemID, long qty, double discountPer) {
    for (int i = 0; i < dm.getRowCount(); i++) {
      String item = "" + tbl.getValueAt(i, 0);
      if (itemID.equalsIgnoreCase(item)) {
        long quantity=qty+Long.parseLong(tbl.getValueAt(i,5).toString());
        double discount = (quantity * Double.parseDouble(tbl.getValueAt(i,6).toString())* discountPer)/100;
        if((flagOption==true && quantity <= this.sohFran) || (flagOption==false && quantity <= this.sohStore)){
          tbl.setValueAt(new Long(quantity), i, 5);
          tbl.setValueAt(new Double(discountPer),i,7);
          tbl.setValueAt(new Double(discount), i, 8);
        }else {
        ut.showMessage(frmMain,"Warning","There is not enough quantity in stock on hand.");
        }
       return true;
      }
    }
    return false;
  }
  public String getItemID(String artNo, String size)
  {
    String itemId="";
    String SQL = "select ITEM_ID, ART_NO, ATTR_DTL_DESC "
    + "from BTM_IM_ITEM_MASTER itm_master, BTM_IM_ATTR_DTL itm_size "
    + "where itm_master.ART_NO='"+artNo+"' "
    + "and itm_master.ATTR2_DTL_ID=itm_size.ATTR_DTL_ID  "
    + "and lower(itm_size.ATTR_DTL_DESC)=lower('"+size+"')  "
    + "and itm_master.STATUS<>'D' ";
//    System.out.println(SQL);
    ResultSet rs=null;
    try{
      rs=DAL.executeQueries(SQL);
      if(rs.next()){
        itemId=rs.getString("ITEM_ID");
      }
      rs.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return itemId;
  }
  public String getItemID(String artNo)
  {
    String itemId="";
    String SQL = "select ITEM_ID, ART_NO, ATTR_DTL_DESC "
    + "from BTM_IM_ITEM_MASTER itm_master, BTM_IM_ATTR_DTL itm_size "
    + "where itm_master.ART_NO='"+artNo+"' "
    + "and itm_master.ATTR2_DTL_ID=itm_size.ATTR_DTL_ID  "
    + "and itm_size.ATTR_DTL_DESC is null "
    + "and itm_master.STATUS<>'D' ";
//    System.out.println(SQL);
    ResultSet rs=null;
    try{
      rs=DAL.executeQueries(SQL);
      if(rs.next()){
        itemId=rs.getString("ITEM_ID");
      }
      rs.getStatement().close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return itemId;
  }
  void btnDelete_actionPerformed(ActionEvent e) {
    if (tbl.getSelectedRow() == -1) {
      return;
    }
    if (tbl.getRowCount() == 1) {
      if (!flagOption)
        vSqlS.clear();
      else if (flagOption)
        vSqlF.clear();
      dm.removeRow(0);
      return;
    }
    int[] i = tbl.getSelectedRows();
    for (int j = 0; j < i.length; j++) {
      dm.removeRow(i[0]);
    }
  }

  void btnDone_actionPerformed(ActionEvent e) {
//Transfer from the store to the franchise
    if (!vSqlS.isEmpty()) {
      if (tbl.getRowCount() > 0) {
        for (int i = 0; i < tbl.getRowCount(); i++) {
          //the case of item is available in the franchise
          if (!checkAvailableItemInFran(tbl.getValueAt(i, 0).toString(),
                                        vFranID.elementAt(cboToFran.
              getSelectedIndex()).toString())) {
            //insert new item in stock franchise with transfered quantity
            vSqlS.addElement("insert into btm_im_item_fran_soh values('" +
                             tbl.getValueAt(i, 0).toString() +
                             "','" +
                             vFranID.elementAt(cboToFran.getSelectedIndex()).
                             toString() +
                             "','" +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             "',0,0, to_date('" + ut.getSystemDate() + "','" +
                             ut.DATE_FORMAT +
                             "'),'" + DAL.getEmpID() +
                             "','" +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             "', 0)");
          }

          //if item is unvailable in franchise
          else {
            //update stock quantity
            vSqlS.addElement(
                "Update btm_im_item_fran_soh set stock_on_hand = stock_on_hand + " +
                Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                ", soh_update_datetime = to_date('" + ut.getSystemDate() +
                "','" +
                ut.DATE_FORMAT + "'), last_user_id = '" + DAL.getEmpID() +
                "', back_room_qty = back_room_qty + '" +
                Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                "' where item_id = '" + tbl.getValueAt(i, 0).toString() +
                "' and franchise_id = '" +
                vFranID.elementAt(cboToFran.getSelectedIndex()).toString() +
                "'");
          }

          //update stock quantity in store
          vSqlS.addElement(
              "Update btm_im_item_loc_soh set stock_on_hand = stock_on_hand - " +
              Long.parseLong(tbl.getValueAt(i, 5).toString()) +
              ", soh_update_datetime = to_date('" + ut.getSystemDate() +
              "','" + ut.DATE_FORMAT + "'), back_room_qty = back_room_qty - " +
              Long.parseLong(tbl.getValueAt(i, 5).toString()) +
              " where item_id ='" + tbl.getValueAt(i, 0).toString() +
              "' and store_id =" +
              Long.parseLong(vStoreID.elementAt(cboFromStore.getSelectedIndex()).
                             toString()));

          //insert transfered item detail into table btm_im_fran_do_item
          vSqlS.addElement("insert into btm_im_franchise_do_item values ('" +
                           txtFranID.getText() +
                           "','" + tbl.getValueAt(i, 0).toString() + "','" +
                           tbl.getValueAt(i, 2).toString() +
                           "','" + Long.parseLong(tbl.getValueAt(i, 5).toString()) +
//                           "','" + Long.parseLong(tbl.getValueAt(i, 5).toString()) +
//                           "'*'" + new_price +
//                           "','" +
                           "',0,'" +
                           Double.parseDouble(tbl.getValueAt(i, 7).toString()) +
                           "','" +
                           Double.parseDouble(tbl.getValueAt(i, 8).toString()) +
                           "',1,0)");
        }
      }
    }
    //Transfer from the franchise to other franchise
    if (!vSqlF.isEmpty()) {
      if (tbl.getRowCount() > 0) {
        //check if it is transfered to the same franchise or not
        if (cboFromStore.getSelectedItem().toString().equalsIgnoreCase(
            cboToFran.getSelectedItem().toString())) { //transfered to the same franchise
          for (int i = 0; i < tbl.getRowCount(); i++) {

            vSqlF.addElement("insert into BTM_IM_FRANCHISE_DO_ITEM values('" +
                             txtFranID.getText() +
                             "','" + tbl.getValueAt(i, 0).toString() +
                             "','" + tbl.getValueAt(i, 2).toString() +
                             "','" +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             "','" +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             "'*'" + new_price +
                             "','" +
                             Double.parseDouble(tbl.getValueAt(i, 7).toString()) +
                             "','" +
                             Double.parseDouble(tbl.getValueAt(i, 8).toString()) +
                             "',1,0)");

            vSqlF.addElement("Update BTM_IM_ITEM_FRAN_SOH set " +
                             "soh_update_datetime = to_date('" +
                             ut.getSystemDate() +
                             "','" + ut.DATE_FORMAT +
                             "'), back_room_qty = back_room_qty - " +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             ", front_room_qty = front_room_qty + " +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             " where item_id = '" +
                             tbl.getValueAt(i, 0).toString() +
                             "' and franchise_id = '" +
                             Long.parseLong(vFranID.elementAt(cboToFran.
                getSelectedIndex()).toString()) + "'");
          }
        }
        else { //transfered to the other franchise
          for (int i = 0; i < tbl.getRowCount(); i++) {
            vSqlF.addElement("insert into BTM_IM_FRANCHISE_DO_ITEM values('" +
                             txtFranID.getText() +
                             "','" + tbl.getValueAt(i, 0).toString() +
                             "','" + tbl.getValueAt(i, 2).toString() +
                             "','" +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             "','" +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             "'*'" + new_price +
                             "','" +
                             Double.parseDouble(tbl.getValueAt(i, 7).toString()) +
                             "','" +
                             Double.parseDouble(tbl.getValueAt(i, 8).toString()) +
                             "',1,0)");

            vSqlF.addElement("Update BTM_IM_ITEM_FRAN_SOH set " +
                             "soh_update_datetime = to_date('" +
                             ut.getSystemDate() +
                             "','" + ut.DATE_FORMAT +
                             "'), stock_on_hand = stock_on_hand - " +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             ", back_room_qty = back_room_qty - " +
                             Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                             " where item_id = '" +
                             tbl.getValueAt(i, 0).toString() +
                             "' and franchise_id = '" +
                             Long.parseLong(vFranID.elementAt(cboFromStore.
                getSelectedIndex()).toString()) + "'");

            //check if the item is available in the franchise
            if (checkItemInFran(tbl.getValueAt(i, 0).toString(),
                                vFranID.elementAt(cboToFran.getSelectedIndex()).
                                toString())) { //item is available in the franchise
              vSqlF.addElement("Update BTM_IM_ITEM_FRAN_SOH set " +
                               "soh_update_datetime = to_date('" +
                               ut.getSystemDate() +
                               "','" + ut.DATE_FORMAT +
                               "'), stock_on_hand = stock_on_hand + " +
                               Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                               ", front_room_qty = front_room_qty + " +
                               Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                               " where item_id = '" +
                               tbl.getValueAt(i, 0).toString() +
                               "' and franchise_id = '" +
                               Long.parseLong(vFranID.elementAt(cboToFran.
                  getSelectedIndex()).toString()) + "'");
            }
            else {
              vSqlF.addElement("insert into btm_im_item_fran_soh values('" +
                               tbl.getValueAt(i, 0).toString() +
                               "','" +
                               vFranID.elementAt(cboToFran.getSelectedIndex()).
                               toString() +
                               "','" +
                               Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                               "',0,0, to_date('" +
                               ut.getSystemDate() + "','" + ut.DATE_FORMAT +
                               "'),'" + DAL.getEmpID() +
                               "',0, '" +
                               Long.parseLong(tbl.getValueAt(i, 5).toString()) +
                               "')");
            }
          }
        }
      }
    }
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      DAL.setBeginTrans(DAL.getConnection());
      if (!vSqlS.isEmpty()) {
        DAL.executeBatchQuery(vSqlS, stmt);
      }
      if (!vSqlF.isEmpty()) {
        DAL.executeBatchQuery(vSqlF, stmt);
      }
      DAL.setEndTrans(DAL.getConnection());
      stmt.close();
    }
    catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);}
    ;
    vSqlS.clear();
    vSqlF.clear();

    //Export to PDF file (transfer form)
    if (tbl.getRowCount() > 0) {
      if (radiobtnFran.isSelected()) {
        printReceipt(txtFranID.getText(),
                     cboFromStore.getSelectedItem().toString(),
                     cboToFran.getSelectedItem().toString(),
                     cboToFran.getSelectedItem().toString(), tbl, DAL);
      }
      else {
        if (radiobtnStore.isSelected()) {
          printReceiptStore(txtFranID.getText(),
                            cboFromStore.getSelectedItem().toString(),
                            cboToFran.getSelectedItem().toString(),
                            cboToFran.getSelectedItem().toString(), tbl, DAL);
        }
      }
    }
    //print PDF file
    /*try {
      String[] arg = {"./file/" + txtFranID.getText() + ".pdf"};
      PrintPDF.main(arg);
    }
    catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }*/

    //reset table
    while (tbl.getRowCount() > 0) {
      dm.removeRow(0);
    }
    //back to prevous screen
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);
    frmMain.pnlMainSim.showInventManageButton();
    frmMain.pnlMainSim.btnTranStore.requestFocus(true);
    frmMain.setTitle("JSIM - Inventory Management");
    frmMain.showScreen(Constant.SCRS_MAIN_SIM);

  }

  void printReceipt(String strReceiptID, String strStoreRoom,
                    String strToStoreRoom, String strSupplier, BJTable tbl,
                    DataAccessLayer DAL) {
    String strHeader[][] = {
        {
        strStoreRoom + " to " + strToStoreRoom
    }
    };
    String strTitle[] = {
        "DELIVERY ORDER"};
    String strInfo[][] = {
        {
        "DATE : " + ut.getSystemDate().substring(0, 10),
        "FRANCHISE: " + strSupplier,
        "RECEIPT ID: " + strReceiptID}
    };
    String strFormat[] = {
        "String", "String", "String", "String", "String", "int", "double",
        "string", "double"
    };

    String strTableTitle[] = {
        "No", "Art#", "Item Name", "Color", "Size", "Qty", "Price", "Discount",
        "Item Total"
    };

    String strTableAlign[] = {
        "CENTER", "CENTER", "LEFT", "LEFT", "LEFT", "RIGHT", "RIGHT", "RIGHT",
        "RIGHT"};
    String strTableItem[][] = new String[tbl.getRowCount()][9];
    String st = "";

    int iTableSkew[] = {
        4, 7, 27, 20, 6, 5, 11, 10, 14};
    int[] iTotal = {
        5,8};

    //Add Item to receipt
    for (int i = 0; i < tbl.getRowCount(); i++) {
      //col 0
      int iNo = i + 1;
      strTableItem[i][0] = "" + iNo;

      //col 1
      if (tbl.getValueAt(i, 1) != null) {
        st = tbl.getValueAt(i, 1).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][1] = st;

      //col 2
      if (tbl.getValueAt(i, 2) != null) {
        st = tbl.getValueAt(i, 2).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][2] = st;

      //col 3
      if (tbl.getValueAt(i, 4) != null) {
        st = tbl.getValueAt(i, 4).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][3] = st;

      //col 4
      if (tbl.getValueAt(i, 3) != null) {
        st = tbl.getValueAt(i, 3).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][4] = st;

      //col 5
      if (tbl.getValueAt(i, 5) != null) {
        st = tbl.getValueAt(i, 5).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][5] = st;

      //col 6
      if (tbl.getValueAt(i, 6) != null) {
        st = tbl.getValueAt(i, 6).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][6] = st;

      //col 7
      if (tbl.getValueAt(i, 7) != null) {
        st = tbl.getValueAt(i, 7).toString() + "%";
      }
      else {
        st = "0%";
      }
      strTableItem[i][7] = st;

      //col 8
      double dTotalPrice = Double.parseDouble(tbl.getValueAt(i, 5).
                                              toString()) *
          Double.parseDouble(tbl.getValueAt(i, 6).toString()) -
          Double.parseDouble(tbl.getValueAt(i, 5).toString()) *
          Double.parseDouble(tbl.getValueAt(i, 6).toString()) *
          Double.parseDouble(tbl.getValueAt(i, 7).toString()) /
          100;

//      String strTemp = ut.doubleToString(dTotalPrice);
//      strTableItem[i][8] = strTemp.substring(0, strTemp.indexOf("."));
      long lTotal=(long)dTotalPrice;
      String strTemp = String.valueOf(lTotal);
      strTableItem[i][8] = strTemp;
    }

    PrintReceipt pp = new PrintReceipt();
    pp.setFileName(DAL.getAppHome()+"\\file\\" +
                         strReceiptID +
                         ".pdf");

    //pp.setFileName("./file/" + strReceiptID + ".pdf");
    pp.setHeader(strHeader);
    pp.setTitle(strTitle);
    pp.setInfo(strInfo);

    //Add info
    pp.setTotalName("Grand Total");
    pp.setLeftSign("FRANCHISE");
    pp.setRightSign("CREATER");
    //Set Font for table
    pp.setColorBackgroundTotal(new Color(255, 255, 255));
//    pp.setFontInTableTitle(FontFactory.getFont(FontFactory.
//                                               HELVETICA, 8, Font.BOLD,
//                                               new Color(0, 0, 0)));
    pp.setFontInTable(FontFactory.getFont(FontFactory.HELVETICA, 9));
//    pp.setFontInTableTotal(FontFactory.getFont(FontFactory.
//                                               HELVETICA, 8, Font.BOLD,
//                                               new Color(0, 0, 0)));
    pp.setTotalRowInPage(61);

    pp.setIsViewTotal(true);
    pp.setRowsTotal(iTotal);
    pp.setFormatColumn(strFormat);
    pp.setTableSkew(iTableSkew);
    pp.setTableAlign(strTableAlign);
    pp.setTableTitle(strTableTitle);
    pp.setTableItem(strTableItem);
    pp.setDeleteFileAfterPrint(true);

    pp.setGroupAt(1);
    pp.setIsGroupByArtNumber(false);
    /*pp.setFontInTableTotalGroupArt(FontFactory.getFont(FontFactory.
        HELVETICA, 10, Font.BOLD,
        new Color(0, 0, 0)));*/
    pp.setTotalGroupName("Total by Art#");

    pp.print();
  }

  void printReceiptStore(String strReceiptID, String strStoreRoom,
                         String strToStoreRoom, String strSupplier, BJTable tbl,
                         DataAccessLayer DAL) {
    String strHeader[][] = {
        {
        strStoreRoom + " to " + strToStoreRoom
    }
    };
    String strTitle[] = {
        "DELIVERY ORDER"};
    String strInfo[][] = {
        {
        "DATE : " + ut.getSystemDate().substring(0, 10),
        "FRANCHISE: " + strSupplier,
        "RECEIPT ID: " + strReceiptID}
    };

    String strFormat[] = {
        "String", "String", "String", "String", "String", "int", "double"
    };

    String strTableTitle[] = {
        "No", "Art#", "Item Name", "Color", "Size", "Qty", "Price"
    };

    String strTableAlign[] = {
        "CENTER", "CENTER", "LEFT", "LEFT", "LEFT", "RIGHT", "RIGHT"};
    String strTableItem[][] = new String[tbl.getRowCount()][7];
    String st = "";

    int iTableSkew[] = {
        5, 8, 32, 21, 8, 7, 19};

    int[] iTotal = {
        5};

    //Add Item to receipt
    for (int i = 0; i < tbl.getRowCount(); i++) {
      //col 0
      int iNo = i + 1;
      strTableItem[i][0] = "" + iNo;

      //col 1
      if (tbl.getValueAt(i, 1) != null) {
        st = tbl.getValueAt(i, 1).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][1] = st;

      //col 2
      if (tbl.getValueAt(i, 2) != null) {
        st = tbl.getValueAt(i, 2).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][2] = st;

      //col 3
      if (tbl.getValueAt(i, 4) != null) {
        st = tbl.getValueAt(i, 4).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][3] = st;

      //col 4
      if (tbl.getValueAt(i, 3) != null) {
        st = tbl.getValueAt(i, 3).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][4] = st;

      //col 5
      if (tbl.getValueAt(i, 5) != null) {
        st = tbl.getValueAt(i, 5).toString();
      }
      else {
        st = " ";
      }
      strTableItem[i][5] = st;

      //col 6
      strTableItem[i][6] = getItemPrice(tbl.getValueAt(i, 0).toString(), DAL);
    }

    PrintReceipt pp = new PrintReceipt();
    pp.setFileName(DAL.getAppHome()+"\\file\\" +
                         strReceiptID +
                         ".pdf");
    //pp.setFileName("./file/" + strReceiptID + ".pdf");
    pp.setHeader(strHeader);
    pp.setTitle(strTitle);
    pp.setInfo(strInfo);

    //Add info
    pp.setTotalName("Total Qty");
    pp.setLeftSign("FRANCHISE");
    pp.setRightSign("CREATER");
    pp.setColorBackgroundTotal(new Color(255, 255, 255));

    pp.setIsViewTotal(true);
    pp.setRowsTotal(iTotal);
    pp.setFormatColumn(strFormat);
    pp.setTableSkew(iTableSkew);
    pp.setTableAlign(strTableAlign);
    pp.setTableTitle(strTableTitle);
    pp.setTableItem(strTableItem);
    pp.setDeleteFileAfterPrint(true);

    pp.setGroupAt(1);
    pp.setIsGroupByArtNumber(false);
    /*pp.setFontInTableTotalGroupArt(FontFactory.getFont(FontFactory.
        HELVETICA, 7, Font.BOLD,
        new Color(0, 0, 0)));*/
    pp.setTotalGroupName("Total by Art#");

    pp.print();
  }

  //Export to PDF file
/* void createPdfFile(String strReceiptID, String strStoreRoom,
                     String strToStoreRoom, String strSupplier) {

    Document document = new Document();
    document.setPageSize(PageSize.A4);
    document.setMargins(10,70,10,10);
    try {
      //Total page
      int iPage = tbl.getRowCount() / 30 + 1;
      int i, j;
      Utils ut = new Utils();
      PdfWriter writer = PdfWriter.getInstance(document,
                                               new FileOutputStream("./file/" +
          strReceiptID + ".pdf"));
      document.open();
      for (j = 1; j <= iPage; j++) {
        //-----------------------------------------Part 1-----------------------------------------//
        if (j!=1){
          document.newPage();
        }


        PdfPTable tableGroup = new PdfPTable(2);
        tableGroup.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableGroup.getDefaultCell().setGrayFill(0f);
        tableGroup.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableGroup.addCell(new Paragraph(new Chunk(strStoreRoom +
            " to " + strToStoreRoom,
            FontFactory.getFont(FontFactory.HELVETICA, 10))));
        tableGroup.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_RIGHT);
        tableGroup.getDefaultCell().setGrayFill(0f);
        tableGroup.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableGroup.addCell(new Paragraph(new Chunk("Page: " + j +
            " of " + iPage,
            FontFactory.getFont(FontFactory.HELVETICA, 10))));
        tableGroup.setTotalWidth(520);
        tableGroup.setLockedWidth(true);
        document.add(tableGroup);
        //-----------------------------------------Part 2-----------------------------------------//
        Paragraph p2 = new Paragraph(
            "DELIVERY ORDER\n",
            FontFactory.getFont(FontFactory.
                                HELVETICA, 12, Font.BOLD,
                                new Color(0, 0, 0)));
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);

        p2 = new Paragraph("(PHIEU XUAT)\n\n",
                           FontFactory.getFont(FontFactory.
                                HELVETICA, 10, Font.BOLD,
                                new Color(0, 0, 0)));
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);
        //-----------------------------------------Part 3-----------------------------------------//
        PdfPTable tableDesc = new PdfPTable(3);
        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableDesc.addCell(new Paragraph(new Chunk("DATE : " +
                                                  ut.getSystemDate().substring(
            0, 10),
                                                  FontFactory.getFont(
            FontFactory.HELVETICA, 10))));

        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_CENTER);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableDesc.addCell(new Paragraph(new Chunk("FRANCHISE: " +
                                                  strSupplier,
                                                  FontFactory.getFont(
            FontFactory.HELVETICA, 10))));

        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_RIGHT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableDesc.addCell(new Paragraph(new Chunk("RECEIPT ID: " +
                                                  strReceiptID,
                                                  FontFactory.getFont(
            FontFactory.HELVETICA, 10))));

        tableDesc.getDefaultCell().setColspan(3);
        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        // +ut.getSystemDateTime2()
        tableDesc.addCell(new Paragraph(new Chunk("TIME  : " +
                                                  ut.getSystemDateTime2().
                                                  substring(11, 19),
                                                  FontFactory.getFont(
            FontFactory.HELVETICA, 10))));

        tableDesc.setTotalWidth(520);
        tableDesc.setLockedWidth(true);
        document.add(tableDesc);
        //-----------------------------------------Part 4-----------------------------------------//
        Paragraph p3 = new Paragraph(" ",
                                     FontFactory.getFont(FontFactory.
            HELVETICA, 12, Font.BOLD, new Color(0, 0, 0)));
        p3.setAlignment(Element.ALIGN_CENTER);
        document.add(p3);
        //-----------------------------------------Part 5 1-----------------------------------------//
        float[] widths = {
            0.04f, 0.08f, 0.3f, 0.08f, 0.06f, 0.08f, 0.17f, 0.10f,
            0.18f};
        PdfPTable table = new PdfPTable(widths);
        PdfPCell cell = new PdfPCell(new Paragraph("No",
            FontFactory.getFont(FontFactory.
                                HELVETICA, 8, Font.BOLD,
                                new Color(0, 0, 0))
                                     ));

        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Art #",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 8, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Art Name",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 8, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Color",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 8, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Size",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 8, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Qty",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 8, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Price",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 8, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Discount",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 8, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Item Total",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 8, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        //-----------------------------------------Part 5 2-----------------------------------------//
        int iRemain = 0;
        if ( (tbl.getRowCount() - 30 * (j - 1)) > 30) {
          iRemain = 30;
        }
        else {
          iRemain = tbl.getRowCount() - 30 * (j - 1);
        }
        int endFor = 30 * (j - 1) + iRemain;
        int l = 0;
        String st = "";
        for (l = 30 * (j - 1); l < endFor; l++) {
          //col 1
          int iNo = l + 1;
          cell = new PdfPCell(new Paragraph(new Chunk(String.valueOf(
              iNo).
              toString(),
              FontFactory.getFont(FontFactory.HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 2
          if (tbl.getValueAt(l, 1) != null) {
            st = tbl.getValueAt(l, 1).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 3
          if (tbl.getValueAt(l, 2) != null) {
            st = tbl.getValueAt(l, 2).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 4
          if (tbl.getValueAt(l, 4) != null) {
            st = tbl.getValueAt(l, 4).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 5
          if (tbl.getValueAt(l, 3) != null) {
            st = tbl.getValueAt(l, 3).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 6
          if (tbl.getValueAt(l, 5) != null) {
            st = tbl.getValueAt(l, 5).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 7
          if (tbl.getValueAt(l, 6) != null) {
            st = tbl.getValueAt(l, 6).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(ut.
              formatNumber(st),
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 8
          if (tbl.getValueAt(l, 7) != null) {
            st = tbl.getValueAt(l, 7).toString() + "%";
          }
          else {
            st = "0%";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 9
          double dTotalPrice = Double.parseDouble(tbl.getValueAt(l, 5).
                                                  toString()) *
              Double.parseDouble(tbl.getValueAt(l, 6).toString()) -
              Double.parseDouble(tbl.getValueAt(l, 5).toString()) *
              Double.parseDouble(tbl.getValueAt(l, 6).toString()) *
              Double.parseDouble(tbl.getValueAt(l, 7).toString()) /
              100;
          cell = new PdfPCell(new Paragraph(new Chunk(ut.formatNumber(
              String.valueOf(dTotalPrice).toString()),
              FontFactory.getFont(FontFactory.HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);
        }
        //-----------------------------------------Part 5 3-----------------------------------------//
        if (iPage == j) {
          //  table = new PdfPTable(8);
          double iTotalEnd = 0;
          for (i = 0; i < tbl.getRowCount(); i++) {
            iTotalEnd +=
                Double.parseDouble(tbl.getValueAt(i, 5).toString()) *
                Double.parseDouble(tbl.getValueAt(i, 6).toString()) -
                Double.parseDouble(tbl.getValueAt(i, 5).toString()) *
                Double.parseDouble(tbl.getValueAt(i, 6).toString()) *
                Double.parseDouble(tbl.getValueAt(i, 7).toString()) /
                100;
          }

          cell = new PdfPCell(new Paragraph("Grand Total",
                                            FontFactory.getFont(FontFactory.
              HELVETICA, 8, Font.BOLD,
              new Color(0, 0, 0))
                              ));
          cell.setColspan(8);
          cell.setBorderColor(new Color(188, 188, 188));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          table.addCell(cell);

          cell = new PdfPCell(new Paragraph(new Chunk(ut.formatNumber(
              String.valueOf(iTotalEnd).toString()),
              FontFactory.getFont(FontFactory.HELVETICA, 8))));
          cell.setBorderColor(new Color(188, 188, 188));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          table.addCell(cell);

          table.setTotalWidth(520);
          table.setLockedWidth(true);
          document.add(table);
        }
        else {
          table.setTotalWidth(520);
          table.setLockedWidth(true);
          document.add(table);
        }

        //-----------------------------------------Part 6-----------------------------------------//
        if (iRemain <= 30) {
          document.add(new Paragraph(" "));
          PdfPTable tableSpace = new PdfPTable(1);
          for (i = 0; i < (30 - iRemain) + 1; i++) {
            tableSpace.getDefaultCell().setVerticalAlignment(Element.
                ALIGN_MIDDLE);
            tableSpace.getDefaultCell().setGrayFill(0f);
            tableSpace.getDefaultCell().setBorderColor(new Color(255,
                255,
                255));
            tableSpace.addCell(" ");
          }
          tableSpace.setTotalWidth(520);
          tableSpace.setLockedWidth(true);
          document.add(tableSpace);
        }
        //-----------------------------------------Part 7-----------------------------------------//
        document.add(new Paragraph(" "));
        PdfPTable tableSign = new PdfPTable(2);
        tableSign.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_CENTER);
        tableSign.getDefaultCell().setGrayFill(0f);
        tableSign.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableSign.addCell("FRANCHISE");
        tableSign.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_CENTER);
        tableSign.getDefaultCell().setGrayFill(0f);
        tableSign.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableSign.addCell("CREATER");
        for (i = 0; i < 6; i++) {
          tableSign.addCell(" ");
          tableSign.getDefaultCell().setColspan(2);
          tableSign.setTotalWidth(520);
          tableSign.setLockedWidth(true);
        }
        document.add(tableSign);
      }

      document.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }*/

  //Export to PDF file
  /*void createPdfFileStoreToFran(String strReceiptID, String strStoreRoom,
                     String strToStoreRoom, String strSupplier,DataAccessLayer DAL) {
    Document document = new Document();
    document.setPageSize(PageSize.A4);
    document.setMargins(10,70,10,10);
    try {
      //Total page
      int iPage = tbl.getRowCount() / 30 + 1;
      int i, j;
      Utils ut = new Utils();
      PdfWriter writer = PdfWriter.getInstance(document,
                                               new FileOutputStream("./file/" +
          strReceiptID + ".pdf"));
      document.open();
      for (j = 1; j <= iPage; j++) {
        //-----------------------------------------Part 1-----------------------------------------//
        if (j!=1){
         document.newPage();
        }

        PdfPTable tableGroup = new PdfPTable(2);
        tableGroup.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableGroup.getDefaultCell().setGrayFill(0f);
        tableGroup.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableGroup.addCell(new Paragraph(new Chunk(strStoreRoom +
            " to " + strToStoreRoom,
            FontFactory.getFont(FontFactory.HELVETICA, 10))));
        tableGroup.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_RIGHT);
        tableGroup.getDefaultCell().setGrayFill(0f);
        tableGroup.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableGroup.addCell(new Paragraph(new Chunk("Page: " + j +
            " of " + iPage,
            FontFactory.getFont(FontFactory.HELVETICA, 10))));
        tableGroup.setTotalWidth(520);
        tableGroup.setLockedWidth(true);
        document.add(tableGroup);
        //-----------------------------------------Part 2-----------------------------------------//
        Paragraph p2 = new Paragraph(
            "DELIVERY ORDER\n",
            FontFactory.getFont(FontFactory.
                                HELVETICA, 12, Font.BOLD,
                                new Color(0, 0, 0)));
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);

        p2 = new Paragraph("(PHIEU XUAT)\n\n",
                           FontFactory.getFont(FontFactory.
                                               HELVETICA, 10, Font.BOLD,
                                               new Color(0, 0, 0)));
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);
        //-----------------------------------------Part 3-----------------------------------------//
        PdfPTable tableDesc = new PdfPTable(3);
        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableDesc.addCell(new Paragraph(new Chunk("DATE : " +
                                                  ut.getSystemDate().substring(
            0, 10),
                                                  FontFactory.getFont(
            FontFactory.HELVETICA, 10))));

        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_CENTER);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableDesc.addCell(new Paragraph(new Chunk("FRANCHISE: " +
                                                  strSupplier,
                                                  FontFactory.getFont(
            FontFactory.HELVETICA, 10))));

        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_RIGHT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableDesc.addCell(new Paragraph(new Chunk("RECEIPT ID: " +
                                                  strReceiptID,
                                                  FontFactory.getFont(
            FontFactory.HELVETICA, 10))));

        tableDesc.getDefaultCell().setColspan(3);
        tableDesc.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_LEFT);
        tableDesc.getDefaultCell().setGrayFill(0f);
        tableDesc.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        // +ut.getSystemDateTime2()
        tableDesc.addCell(new Paragraph(new Chunk("TIME  : " +
                                                  ut.getSystemDateTime2().
                                                  substring(11, 19),
                                                  FontFactory.getFont(
            FontFactory.HELVETICA, 10))));

        tableDesc.setTotalWidth(520);
        tableDesc.setLockedWidth(true);
        document.add(tableDesc);
        //-----------------------------------------Part 4-----------------------------------------//
        Paragraph p3 = new Paragraph(" ",
                                     FontFactory.getFont(FontFactory.
            HELVETICA, 12, Font.BOLD, new Color(0, 0, 0)));
        p3.setAlignment(Element.ALIGN_CENTER);
        document.add(p3);
        //-----------------------------------------Part 5 1-----------------------------------------//
        float[] widths = {
            0.05f, 0.08f, 0.45f, 0.09f, 0.07f, 0.15f, 0.2f};
        PdfPTable table = new PdfPTable(widths);
        PdfPCell cell = new PdfPCell(new Paragraph("No",
            FontFactory.getFont(FontFactory.
                                HELVETICA, 10, Font.BOLD,
                                new Color(0, 0, 0))
                                     ));

        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Art #",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Art Name",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Color",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Size",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Qty",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Price",
                                          FontFactory.getFont(FontFactory.
            HELVETICA, 10, Font.BOLD,
            new Color(0, 0, 0))
                            ));
        cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        cell.setBorderColor(new Color(188, 188, 188));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.
            ALIGN_MIDDLE);
        table.addCell(cell);

        //-----------------------------------------Part 5 2-----------------------------------------//
        int iRemain = 0;
        if ( (tbl.getRowCount() - 30 * (j - 1)) > 30) {
          iRemain = 30;
        }
        else {
          iRemain = tbl.getRowCount() - 30 * (j - 1);
        }
        int endFor = 30 * (j - 1) + iRemain;
        int l = 0;
        String st = "";
        for (l = 30 * (j - 1); l < endFor; l++) {
          //col 1
          int iNo = l + 1;
          cell = new PdfPCell(new Paragraph(new Chunk(String.valueOf(
              iNo).
              toString(),
              FontFactory.getFont(FontFactory.HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 2
          if (tbl.getValueAt(l, 1) != null) {
            st = tbl.getValueAt(l, 1).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 3
          if (tbl.getValueAt(l, 2) != null) {
            st = tbl.getValueAt(l, 2).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 4
          if (tbl.getValueAt(l, 4) != null) {
            st = tbl.getValueAt(l, 4).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 5
          if (tbl.getValueAt(l, 3) != null) {
            st = tbl.getValueAt(l, 3).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 6
          if (tbl.getValueAt(l, 5) != null) {
            st = tbl.getValueAt(l, 5).toString();
          }
          else {
            st = " ";
          }
          cell = new PdfPCell(new Paragraph(new Chunk(st,
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 7
          cell = new PdfPCell(new Paragraph(new Chunk(ut.
              formatNumber(getItemPrice(tbl.getValueAt(l, 0).toString(),DAL)),
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 10))));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);
        }
        //-----------------------------------------Part 5 3-----------------------------------------//
        if (iPage == j) {
          //  table = new PdfPTable(8);
          int iTotalEnd = 0;
          for (i = 0; i < tbl.getRowCount(); i++) {
            iTotalEnd += Integer.parseInt(tbl.getValueAt(i, 5).toString());
          }

          cell = new PdfPCell(new Paragraph("Qty Total",
                                            FontFactory.getFont(FontFactory.
              HELVETICA, 10, Font.BOLD,
              new Color(0, 0, 0))
                              ));
          cell.setColspan(5);
          cell.setBorderColor(new Color(188, 188, 188));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          table.addCell(cell);

          cell = new PdfPCell(new Paragraph(new Chunk(
              String.valueOf(iTotalEnd).toString(),
              FontFactory.getFont(FontFactory.HELVETICA, 10))));
          cell.setColspan(1);
          cell.setBorderColor(new Color(188, 188, 188));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          table.addCell(cell);

          cell = new PdfPCell(new Paragraph(new Chunk(" ",
              FontFactory.getFont(FontFactory.HELVETICA, 10))));
          cell.setColspan(1);
          cell.setBorderColor(new Color(188, 188, 188));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);

          table.addCell(cell);
          table.setTotalWidth(520);
          table.setLockedWidth(true);
          document.add(table);
        }
        else {
          table.setTotalWidth(520);
          table.setLockedWidth(true);
          document.add(table);
        }

        //-----------------------------------------Part 6-----------------------------------------//
        if (iRemain <= 30) {
          document.add(new Paragraph(" "));
          PdfPTable tableSpace = new PdfPTable(1);
          for (i = 0; i < (30 - iRemain) + 1; i++) {
            tableSpace.getDefaultCell().setVerticalAlignment(Element.
                ALIGN_MIDDLE);
            tableSpace.getDefaultCell().setGrayFill(0f);
            tableSpace.getDefaultCell().setBorderColor(new Color(255,
                255,
                255));
            tableSpace.addCell(" ");
          }
          tableSpace.setTotalWidth(520);
          tableSpace.setLockedWidth(true);
          document.add(tableSpace);
        }
        //-----------------------------------------Part 7-----------------------------------------//
        document.add(new Paragraph(" "));
        PdfPTable tableSign = new PdfPTable(2);
        tableSign.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_CENTER);
        tableSign.getDefaultCell().setGrayFill(0f);
        tableSign.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableSign.addCell("FRANCHISE");
        tableSign.getDefaultCell().setHorizontalAlignment(Element.
            ALIGN_CENTER);
        tableSign.getDefaultCell().setGrayFill(0f);
        tableSign.getDefaultCell().setBorderColor(new Color(255, 255,
            255));
        tableSign.addCell("CREATER");
        for (i = 0; i < 6; i++) {
          tableSign.addCell(" ");
          tableSign.getDefaultCell().setColspan(2);
          tableSign.setTotalWidth(520);
          tableSign.setLockedWidth(true);
        }
        document.add(tableSign);
        Chunk c = new Chunk("                                                                                                                                                            ",
                            FontFactory.getFont(FontFactory.HELVETICA,
                                                12));
        c.setUnderline(new Color(0, 0, 0), 0.1f, 0.0f, 0.0f, -0.5f,
                       PdfContentByte.LINE_CAP_ROUND);

        document.add(c);
      }

      document.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }*/
  //get Item Price
  public String getItemPrice(String strItemID,DataAccessLayer DAL){
      String strInfo="";
      ResultSet rs = null;
      try{
        String sql =
           "select item_id, NEW_PRICE, attr1_name, attr2_name " +
           "from btm_im_price_hist " +
           "where item_id ='" + strItemID + "' and STATUS ='1' ";
        rs = DAL.executeQueries(sql);
        if (rs.next()) {
          if (rs.getString("new_price")!=null){
            strInfo = rs.getString("new_price");
          }else{
            strInfo =  "0";
          }
        }
        rs.getStatement().close();
      }catch(Exception e){
      }
      return strInfo;
  }
//check whether item is avalable in stock franchise or not
  private boolean checkAvailableItemInFran(String itemID, String FranID) {
    String sql = "select item_id, franchise_id, stock_on_hand " +
        "from btm_im_item_fran_soh where " +
        " item_id = " + itemID + " and franchise_id = " + FranID;
    ResultSet rs = null;
    try {
      rs = DAL.executeQueries(sql);
      if (rs.next()){
        rs.getStatement().close();
        return true;
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return false;
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

    // ESCAPE
    key = new Integer(KeyEvent.VK_ESCAPE);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), key);
    actionMap.put(key, new KeyAction(key));
  }

  class KeyAction
      extends AbstractAction {

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
      else if (identifier.intValue() == KeyEvent.VK_F4) {
        btnView.doClick();
      }
      else if (identifier.intValue()== KeyEvent.VK_F5){
        btnStockCount.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F6 ||
               identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnBack.doClick();
      }
    }
  }

  void btnView_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_TRANSFER_FRAN_VIEW);
    frmMain.pnlTransferFranView.txtFromDate.setText(ut.getSystemDate(1));
    frmMain.pnlTransferFranView.txtToDate.setText(ut.getSystemDate(1));

  }

  void txtItemID_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtItemID, ItemMaster.LEN_ITEM_ID);
    if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
        (e.getKeyChar() != e.VK_BACK_SPACE) && (e.getKeyChar() != e.VK_ENTER))
      e.consume();
    else if (e.getKeyChar() == e.VK_ENTER) {
      if (!txtItemID.getText().equalsIgnoreCase("")) {
        txtQuantity.requestFocus(true);
      }else {
        txtArtNo.requestFocus(true);
      }
    }
  }

  void txtDiscount_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e, txtDiscount, 4);
    if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
        (e.getKeyChar() != '.') &&
        (e.getKeyChar() != e.VK_BACK_SPACE) && (e.getKeyChar() != e.VK_ENTER)){
      e.consume();
   }
    if(e.getKeyChar()==e.VK_ENTER){
        btnAdd.doClick();
      }
  }

  void txtQuantity_keyTyped(KeyEvent e) {
     if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
        (e.getKeyChar() != e.VK_BACK_SPACE) && (e.getKeyChar() != e.VK_ENTER))
      e.consume();
    if(e.getKeyChar()==e.VK_ENTER){
     if(!txtQuantity.getText().equalsIgnoreCase("")){
       if(radiobtnStore.isSelected()){
         btnAdd.doClick();
       }else if(radiobtnFran.isSelected()){
         txtDiscount.requestFocus(true);
       }
     }
   }

  }

  void btnStockCount_actionPerformed(ActionEvent e) {
    frmMain.showScreen(Constant.SCRS_FRAN_STOCK_COUNT);
  }

  void txtReceiver_keyTyped(KeyEvent e) {
    if(e.getKeyChar()==e.VK_ENTER){
      if(!txtReceiver.getText().equalsIgnoreCase("")){
        txtItemID.requestFocus(true);
      }
    }
  }

  void txtQuantity_focusGained(FocusEvent e) {
    if (txtItemID.getText().equalsIgnoreCase("")
     && txtArtNo.getText().equalsIgnoreCase("")
//     && txtSize.getText().equalsIgnoreCase("")
     ) {
      ut.showMessage(frmMain, "Warning", "Please input product's information (Item_ID/ArtNo-Size)");
      txtItemID.requestFocus(true);
      return;
    }
  }

  void txtArtNo_keyTyped(KeyEvent e) {
    if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
        (e.getKeyChar() != e.VK_BACK_SPACE) && (e.getKeyChar() != e.VK_ENTER))
      e.consume();
    if(e.getKeyChar()==e.VK_ENTER){
      txtSize.requestFocus(true);
    }
  }

  void txtSize_keyTyped(KeyEvent e) {
    if(e.getKeyChar()==e.VK_ENTER){
      txtQuantity.requestFocus(true);
    }
  }
}

class PanelTransferFranchise_radiobtnStore_itemAdapter
    implements java.awt.event.ItemListener {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_radiobtnStore_itemAdapter(PanelTransferFranchise
      adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.radiobtnStore_itemStateChanged(e);
  }
}

class PanelTransferFranchise_radiobtnFran_itemAdapter
    implements java.awt.event.ItemListener {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_radiobtnFran_itemAdapter(PanelTransferFranchise
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.radiobtnFran_itemStateChanged(e);
  }
}

class PanelTransferFranchise_btnAdd_actionAdapter
    implements java.awt.event.ActionListener {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_btnAdd_actionAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}

class PanelTransferFranchise_txtItemID_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_txtItemID_keyAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtItemID_keyTyped(e);
  }
}

class PanelTransferFranchise_txtDiscount_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_txtDiscount_keyAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtDiscount_keyTyped(e);
  }
}

class PanelTransferFranchise_btnDelete_actionAdapter
    implements java.awt.event.ActionListener {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_btnDelete_actionAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDelete_actionPerformed(e);
  }
}

class PanelTransferFranchise_btnDone_actionAdapter
    implements java.awt.event.ActionListener {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_btnDone_actionAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelTransferFranchise_btnView_actionAdapter
    implements java.awt.event.ActionListener {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_btnView_actionAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelTransferFranchise_txtQuantity_keyAdapter
    extends java.awt.event.KeyAdapter {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_txtQuantity_keyAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }

  public void keyTyped(KeyEvent e) {
    adaptee.txtQuantity_keyTyped(e);
  }
}

class PanelTransferFranchise_btnStockCount_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_btnStockCount_actionAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnStockCount_actionPerformed(e);
  }
}

class PanelTransferFranchise_txtReceiver_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_txtReceiver_keyAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtReceiver_keyTyped(e);
  }
}

class PanelTransferFranchise_txtQuantity_focusAdapter extends java.awt.event.FocusAdapter {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_txtQuantity_focusAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }
  public void focusGained(FocusEvent e) {
    adaptee.txtQuantity_focusGained(e);
  }
}

class PanelTransferFranchise_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_txtArtNo_keyAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelTransferFranchise_txtSize_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferFranchise adaptee;

  PanelTransferFranchise_txtSize_keyAdapter(PanelTransferFranchise adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSize_keyTyped(e);
  }
}

