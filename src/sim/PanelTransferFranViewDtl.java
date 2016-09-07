//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//









































package sim;

/**
 * <p>Title: Transfer Franchise View Detail</p>
 * <p>Description: View the detail of franchise's transfers</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM INTERSOFT VN</p>
 * @author PHUONG NGUYEN
 * @version 1.0
 */
import javax.swing.*;
import common.*;
import java.awt.*;
import btm.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import java.util.ResourceBundle;
import btm.business.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Chunk;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import java.io.FileOutputStream;
import com.lowagie.text.FontFactory;
import org.pdfbox.pdmodel.PDDocument;
import com.lowagie.text.PageSize;
import org.pdfbox.PrintPDF;

//not use
public class PanelTransferFranViewDtl extends JPanel{
    FrameMainSim frmMain;
    DataAccessLayer DAL;
    Utils ut = new Utils();
    Statement stmt=null;
    Vector vModifySql = new Vector(); //declare vector to contain sqls updated modifying
    ItemMasterBusObj itemObj=new ItemMasterBusObj();
    long trackItemQty=0; //declare variable to record the item's quantity before modified
    int rowCount=0; //count rows exist in grid
    double type_flag=0; //keep status of transfer type
    int screen_flag=0;

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel btnPanel = new BJPanel(Constant.PANEL_TYPE_HEADER);
  FlowLayout flowLayout1 = new FlowLayout();
  BJButton btnDone = new BJButton();
  BJButton btnAdd = new BJButton();
  BJButton btnPrint = new BJButton();
  BJButton btnBack = new BJButton();
  JPanel dataPanel = new JPanel();
  JPanel infoPanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout5 = new FlowLayout();
  FlowLayout flowLayout6 = new FlowLayout();
  FlowLayout flowLayout7 = new FlowLayout();
  JPanel left1Panel = new JPanel();
  BJComboBox cboReason = new BJComboBox();
  JTextField txtTransferID = new JTextField();
  JTextField txtTo = new JTextField();
  JLabel lblQty = new JLabel();
  JLabel lblCreaterName = new JLabel();
  JLabel lblTransferID = new JLabel();
  JTextField txtCreaterName = new JTextField();
  JLabel lblReason = new JLabel();
  JTextField txtQty = new JTextField();
  JPanel leftPanel = new JPanel();
  JLabel lblItemID = new JLabel();
  JPanel right2Panel = new JPanel();
  FlowLayout flowLayout3 = new FlowLayout();
  JLabel lblFrom = new JLabel();
  JLabel lblTo = new JLabel();
  JLabel lblReceiverName = new JLabel();
  JTextField txtItemID = new JTextField();
  FlowLayout flowLayout4 = new FlowLayout();
  JPanel right1Panel = new JPanel();
  JPanel left2Panel = new JPanel();
  JTextField txtFrom = new JTextField();
  JTextField txtReceiverName = new JTextField();
  JPanel rightPanel = new JPanel();
  FlowLayout flowLayout10 = new FlowLayout();
  FlowLayout flowLayout11 = new FlowLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable tbl = new BJTable(dm,true);
  JLabel lblTransferDate = new JLabel();
  JTextField txtTransferDate = new JTextField();
  JLabel lblDiscount = new JLabel();
  JTextField txtDiscount = new JTextField();
  JLabel jLabel1 = new JLabel();

  public PanelTransferFranViewDtl(FrameMainSim frmMain) {
    this.frmMain = frmMain;
    this.DAL = frmMain.getDAL();
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
//    System.out.println("N19");
    registerKeyboardActions();//perform funtions of hot keys
    this.setLayout(borderLayout1);
    btnPanel.setLayout(flowLayout1);
    btnPanel.setPreferredSize(new Dimension(800, 47));
//add buttons into the container
//  set properties for button Done
    btnDone.setToolTipText("Done (F1)");
    btnDone.setActionCommand("btnDone");
    btnDone.setText("<html><center><b>Done</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnDone.addActionListener(new PanelTransferFranViewDtl_btnDone_actionAdapter(this));
//  set properties for button Add
    btnAdd.setToolTipText("Add (F2)");
    btnAdd.setActionCommand("btnAdd");
    btnAdd.setText("<html><center><b>Add</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
    "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnAdd.addActionListener(new PanelTransferFranViewDtl_btnAdd_actionAdapter(this));
//  set properties for button Print
    btnPrint.setToolTipText("Print (F3)");
    btnPrint.setActionCommand("btnPrint");
    btnPrint.setText("<html><center><b>Print</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F3</html>");
    btnPrint.addActionListener(new
                               PanelTransferFranViewDtl_btnPrint_actionAdapter(this));
    //  set properties for button Back
    btnBack.setToolTipText("Back (F4)");
    btnBack.setActionCommand("btnCancel");
    btnBack.setText("<html><center><b>Back</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F4</html>");
    btnBack.addActionListener(new PanelTransferFranViewDtl_btnBack_actionAdapter(this));
//  set properties for info panel
    infoPanel.setLayout(borderLayout3);
    infoPanel.setBorder(null);
    infoPanel.setMinimumSize(new Dimension(107, 63));
    infoPanel.setPreferredSize(new Dimension(800, 180));
    this.setPreferredSize(new Dimension(800, 600));
//  set properties for grid panel
    dataPanel.setLayout(borderLayout2);
    dataPanel.setMinimumSize(new Dimension(1, 1));
    dataPanel.setPreferredSize(new Dimension(800, 230));
    flowLayout6.setAlignment(FlowLayout.LEFT);
    flowLayout7.setAlignment(FlowLayout.LEFT);
    left1Panel.setLayout(flowLayout3);
    left1Panel.setPreferredSize(new Dimension(150, 180));
    cboReason.setBackground(new Color(212, 208, 200));
    cboReason.setFont(new java.awt.Font("Dialog", 1, 11));
    cboReason.setPreferredSize(new Dimension(220, 25));
    cboReason.setEditable(true);
    txtTransferID.setFont(new java.awt.Font("Dialog", 1, 11));
    txtTransferID.setPreferredSize(new Dimension(220, 25));
    txtTransferID.setEditable(false);
    txtTransferID.setText("");
    txtTo.setFont(new java.awt.Font("Dialog", 1, 11));
    txtTo.setPreferredSize(new Dimension(220, 25));
    txtTo.setEditable(false);
    txtTo.setText("");
    lblQty.setFont(new java.awt.Font("Dialog", 1, 12));
    lblQty.setAlignmentX((float) 0.5);
    lblQty.setPreferredSize(new Dimension(120, 25));
    lblQty.setText("Quantity:");
    lblCreaterName.setFont(new java.awt.Font("Dialog", 1, 12));
    lblCreaterName.setAlignmentX((float) 0.5);
    lblCreaterName.setPreferredSize(new Dimension(120, 25));
    lblCreaterName.setText("Creater Name:");
    lblTransferID.setFont(new java.awt.Font("Dialog", 1, 12));
    lblTransferID.setPreferredSize(new Dimension(120, 25));
    lblTransferID.setText("Transfer ID:");
    txtCreaterName.setFont(new java.awt.Font("Dialog", 1, 11));
    txtCreaterName.setPreferredSize(new Dimension(220, 25));
    txtCreaterName.setEditable(false);
    txtCreaterName.setText("");
    lblReason.setFont(new java.awt.Font("Dialog", 1, 12));
    lblReason.setAlignmentX((float) 0.5);
    lblReason.setPreferredSize(new Dimension(120, 25));
    lblReason.setText("Reason:");
    txtQty.setFont(new java.awt.Font("Dialog", 1, 11));
    txtQty.setDebugGraphicsOptions(0);
    txtQty.setPreferredSize(new Dimension(220, 25));
    txtQty.setText("");
    txtQty.addKeyListener(new PanelTransferFranViewDtl_txtQty_keyAdapter(this));
    leftPanel.setLayout(borderLayout4);
    lblItemID.setFont(new java.awt.Font("Dialog", 1, 12));
    lblItemID.setPreferredSize(new Dimension(120, 25));
    lblItemID.setText("Item ID:");
    right2Panel.setPreferredSize(new Dimension(250, 180));
    right2Panel.setRequestFocusEnabled(true);
    right2Panel.setLayout(flowLayout10);
    lblFrom.setFont(new java.awt.Font("Dialog", 1, 12));
    lblFrom.setPreferredSize(new Dimension(120, 25));
    lblFrom.setText("Transfered From:");
    lblTo.setFont(new java.awt.Font("Dialog", 1, 12));
    lblTo.setPreferredSize(new Dimension(120, 25));
    lblTo.setText("Transfer To:");
    lblReceiverName.setEnabled(true);
    lblReceiverName.setFont(new java.awt.Font("Dialog", 1, 12));
    lblReceiverName.setAlignmentX((float) 0.5);
    lblReceiverName.setDoubleBuffered(false);
    lblReceiverName.setPreferredSize(new Dimension(120, 25));
    lblReceiverName.setHorizontalAlignment(SwingConstants.LEADING);
    lblReceiverName.setText("Receiver Name:");
    txtItemID.setFont(new java.awt.Font("Dialog", 1, 11));
    txtItemID.setPreferredSize(new Dimension(220, 25));
    txtItemID.setEditable(false);
    txtItemID.setText("");
    right1Panel.setPreferredSize(new Dimension(150, 180));
    right1Panel.setLayout(flowLayout11);
    left2Panel.setPreferredSize(new Dimension(250, 180));
    left2Panel.setLayout(flowLayout4);
    txtFrom.setFont(new java.awt.Font("Dialog", 1, 11));
    txtFrom.setPreferredSize(new Dimension(220, 25));
    txtFrom.setEditable(false);
    txtFrom.setText("");
    txtReceiverName.setEnabled(true);
    txtReceiverName.setFont(new java.awt.Font("Dialog", 1, 11));
    txtReceiverName.setPreferredSize(new Dimension(220, 25));
    txtReceiverName.setEditable(false);
    rightPanel.setLayout(borderLayout5);
    rightPanel.setPreferredSize(new Dimension(400, 180));
    borderLayout4.setHgap(0);
    tbl.addMouseListener(new PanelTransferFranViewDtl_tbl_mouseAdapter(this));
    lblTransferDate.setFont(new java.awt.Font("Dialog", 1, 12));
    lblTransferDate.setPreferredSize(new Dimension(120, 25));
    lblTransferDate.setText("Transfer Date:");
    txtTransferDate.setEnabled(true);
    txtTransferDate.setFont(new java.awt.Font("Dialog", 1, 11));
    txtTransferDate.setPreferredSize(new Dimension(220, 25));
    txtTransferDate.setEditable(false);
    txtTransferDate.setText("");
    lblDiscount.setFont(new java.awt.Font("Dialog", 1, 12));
    lblDiscount.setAlignmentX((float) 0.5);
    lblDiscount.setPreferredSize(new Dimension(120, 25));
    lblDiscount.setToolTipText("");
    lblDiscount.setText("Discount:");
    txtDiscount.setFont(new java.awt.Font("Dialog", 1, 11));
    txtDiscount.setPreferredSize(new Dimension(198, 25));
    txtDiscount.setEditable(true);
    txtDiscount.setText("");
    jLabel1.setPreferredSize(new Dimension(18, 25));
    jLabel1.setText("%");
    this.add(btnPanel, BorderLayout.NORTH);
    btnPanel.add(btnDone, null);
    btnPanel.add(btnAdd, null);
    btnPanel.add(btnPrint, null);
    btnPanel.add(btnBack,null);
    this.add(dataPanel, BorderLayout.CENTER);
    dataPanel.add(infoPanel,  BorderLayout.NORTH);
//add sub panels into panel Info
    infoPanel.add(leftPanel,  BorderLayout.WEST);
    infoPanel.add(rightPanel,  BorderLayout.CENTER);
    leftPanel.add(left1Panel, BorderLayout.WEST);
    leftPanel.add(left2Panel, BorderLayout.CENTER);
    rightPanel.add(right1Panel,  BorderLayout.WEST);
    rightPanel.add(right2Panel,  BorderLayout.CENTER);
//add label components into left panel
    left1Panel.add(lblItemID, null);
    left1Panel.add(lblTransferID, null);
    left1Panel.add(lblFrom, null);
    left1Panel.add(lblTo, null);
    left1Panel.add(lblTransferDate, null);
//add text components into left panel
    left2Panel.add(txtItemID, null);
    left2Panel.add(txtTransferID, null);
    left2Panel.add(txtFrom, null);
    left2Panel.add(txtTo, null);
    left2Panel.add(txtTransferDate, null);
//add label components into right panel
    right1Panel.add(lblQty, null);
    right1Panel.add(lblReason, null);
    right1Panel.add(lblDiscount, null);
    right1Panel.add(lblCreaterName, null);
    right1Panel.add(lblReceiverName, null);
//add text components into right panel
    right2Panel.add(txtQty, null);
    right2Panel.add(cboReason, null);
    right2Panel.add(txtDiscount, null);
    right2Panel.add(jLabel1, null);
    right2Panel.add(txtCreaterName, null);
    right2Panel.add(txtReceiverName, null);
    dataPanel.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tbl, null);
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
         btnDone.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F2) {
         btnAdd.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F3) {
         btnPrint.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F4) {
         btnBack.doClick();
       }
     }
 }
//set data into combo Reason
 void setDataComboReason(BJComboBox cbo){
   ResultSet rs = null;
   Statement stmt = null;
   String sql = "select reason_id, reason_desc from btm_im_reason where reason_id <> 1";
   try{
     stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     rs = DAL.executeQueries(sql,stmt);
     cbo.setData1(rs);

     stmt.close();
     rs.close();
   }catch(Exception e){
     e.printStackTrace();
   }
 }

  void btnBack_actionPerformed(ActionEvent e) {
    while(tbl.getRowCount()>0){
      dm.removeRow(0);
    }
    txtItemID.setText("");
    txtQty.setText("");
    txtDiscount.setText("");
    if(screen_flag==Constant.SCRS_TRANSFER_FRAN_VIEW){
    frmMain.showScreen(Constant.SCRS_TRANSFER_FRAN_VIEW);
    }else if(screen_flag==Constant.SCRS_TRANSFER_FROM_FRAN_VIEW){
    frmMain.showScreen(Constant.SCRS_TRANSFER_FROM_FRAN_VIEW);
    }
  }

  void btnSearchItem_actionPerformed(ActionEvent e) {
    btm.business.ItemBusObject itemBusObject = new btm.business.ItemBusObject();
    DialogItemLookup dialogItemLookup = new DialogItemLookup(frmMain,
        lang.getString("TT0016"), true, frmMain, itemBusObject);
    dialogItemLookup.setVisible(true);
    if (dialogItemLookup.done) {
      txtItemID.setText(dialogItemLookup.getItemID());
      txtItemID.requestFocus(true);
    }
    else {
      txtItemID.requestFocus(true);
    }

  }

  void tbl_mouseClicked(MouseEvent e) {
    if(e.getClickCount()==2){
      if(type_flag==2){
        txtItemID.setText(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
        txtQty.requestFocus(true);
      }else if(type_flag==3 || type_flag==4){
        txtItemID.setText(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
        txtQty.setText(tbl.getValueAt(tbl.getSelectedRow(),5).toString());
        txtQty.requestFocus(true);
        txtQty.selectAll();
        txtDiscount.setText(tbl.getValueAt(tbl.getSelectedRow(),6).toString());
        trackItemQty=Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(),5).toString());
      }
    }
  }

//add data into grid in the case of transfer is from franchise to franchise
  void modifyDataInGrid(){
    long newprice=itemObj.getUnitPrice(txtItemID.getText(),DAL.getStoreID(),DAL);
    long qty=Long.parseLong(txtQty.getText());
    long tolerance=0;
    double discount=0;
   try{
     if(txtDiscount.getText().equalsIgnoreCase("")){
       ut.showMessage(frmMain,"Warning","Please input discount");
       return;
     }
     if(!ut.isDoubleString(txtDiscount.getText())){
       ut.showMessage(frmMain,"Warning","Discount must be number");
       txtQty.setText("");
       txtQty.requestFocus(true);
       return;
     }
     discount = Double.parseDouble(txtDiscount.getText());
     String SQL = "update BTM_IM_FRANCHISE_DO_ITEM  "
               + "set DO_QTY=" + qty
               + ", DO_AMT=" + qty*newprice
               + ", DISCOUNT_PER=" + discount
               + ", DISCOUNT=" + (qty*newprice*discount)/100
               + " where ITEM_ID='" + txtItemID.getText()
               + "' and FRANCHISE_DO_ID='" + txtTransferID.getText() + "'";
     vModifySql.addElement(SQL);
     if(qty<trackItemQty){//descrease the quantity
       tolerance = trackItemQty-qty;
       if(type_flag==3){//transfer from franchise to itself
          SQL="update BTM_IM_ITEM_FRAN_SOH "
            + "set BACK_ROOM_QTY = BACK_ROOM_QTY + "
            + tolerance + ", FRONT_ROOM_QTY = FRONT_ROOM_QTY - "
            + tolerance + " where ITEM_ID = '" + txtItemID.getText()
            + "' and FRANCHISE_ID = '" + getFranID(txtFrom.getText()) + "'";
        vModifySql.addElement(SQL);
        }else if(type_flag==4){//transfer from franchise to another
          SQL="update BTM_IM_ITEM_FRAN_SOH "
            + "set STOCK_ON_HAND = STOCK_ON_HAND + "
            + tolerance + ", BACK_ROOM_QTY = BACK_ROOM_QTY + "
            + tolerance + " where ITEM_ID = '" + txtItemID.getText()
            + "' and FRANCHISE_ID = '" + getFranID(txtFrom.getText()) + "'";
          vModifySql.addElement(SQL);
          SQL="update BTM_IM_ITEM_FRAN_SOH "
            + "set STOCK_ON_HAND = STOCK_ON_HAND - "
            + tolerance + ", BACK_ROOM_QTY = BACK_ROOM_QTY - "
            + tolerance + " where ITEM_ID = '" + txtItemID.getText()
            + "' and FRANCHISE_ID = '" + getFranID(txtTo.getText()) + "'";
        vModifySql.addElement(SQL);
        }
     }
     if(qty>trackItemQty){//increase the quantity
       tolerance = qty-trackItemQty;
       if(!checkFranSOH(txtItemID.getText(),getFranID(txtFrom.getText()),tolerance)){
         return;
       }
       if(type_flag==3){//transfer from franchise to itself
         SQL="update BTM_IM_ITEM_FRAN_SOH "
              + "set BACK_ROOM_QTY = BACK_ROOM_QTY - "
              + tolerance + ", FRONT_ROOM_QTY = FRONT_ROOM_QTY + "
              + tolerance + " where ITEM_ID = '" + txtItemID.getText()
              + "' and FRANCHISE_ID = '" + getFranID(txtFrom.getText()) + "'";
          vModifySql.addElement(SQL);
       }
       else if(type_flag==4) {//transfer from franchise to another
         SQL="update BTM_IM_ITEM_FRAN_SOH "
           + "set STOCK_ON_HAND = STOCK_ON_HAND - "
           + tolerance + ", BACK_ROOM_QTY = BACK_ROOM_QTY - "
           + tolerance + " where ITEM_ID = '" + txtItemID.getText()
           + "' and FRANCHISE_ID = '" + getFranID(txtFrom.getText()) + "'";
         vModifySql.addElement(SQL);
         SQL="update BTM_IM_ITEM_FRAN_SOH "
           + "set STOCK_ON_HAND = STOCK_ON_HAND + "
           + tolerance + ", BACK_ROOM_QTY = BACK_ROOM_QTY + "
           + tolerance + " where ITEM_ID = '" + txtItemID.getText()
           + "' and FRANCHISE_ID = '" + getFranID(txtTo.getText()) + "'";
       vModifySql.addElement(SQL);
       }
     }
     tbl.setValueAt(txtQty.getText(),tbl.getSelectedRow(),5);
     tbl.setValueAt(txtDiscount.getText(),tbl.getSelectedRow(),6);
   }catch(Exception e){
     ExceptionHandle eh=new ExceptionHandle();
     eh.ouputError(e);
   }
  }
// check if the selected item is available in the franchise
  boolean checkFranSOH(String ItemId,String FranId, long qty){
    int sohQty=0;
    String SQL="select BACK_ROOM_QTY from BTM_IM_ITEM_FRAN_SOH "
             + "where ITEM_ID='" + ItemId + "' and FRANCHISE_ID='" + FranId + "'";
         try{
           stmt=DAL.getConnection().createStatement();
           ResultSet rs=DAL.executeQueries(SQL,stmt);
           if(rs.next()){
             sohQty=rs.getInt("BACK_ROOM_QTY");
           }
           if(qty>sohQty){
             ut.showMessage(frmMain,"Warning","There is no enough quantity in the franchise's stock");
             return false;
           }
           stmt.close();
         }catch(Exception e){
           ExceptionHandle eh=new ExceptionHandle();
           eh.ouputError(e);
         }
    return true;
  }
//get Franchise ID
  String getFranID(String FranName){
    String FranID=null;
    String SQL="select cust_id from BTM_IM_CUSTOMER "
            + "where FIRST_NAME='" + FranName + "'";
        try{
          stmt=DAL.getConnection().createStatement();
          ResultSet rs=DAL.executeQueries(SQL,stmt);
          if(rs.next()){
            FranID = rs.getString("cust_id");
          }

          rs.close();
          stmt.close();
        }catch(Exception e){
          ExceptionHandle eh=new ExceptionHandle();
          eh.ouputError(e);
        }
    return FranID;
  }
//add data into grid in the case of transfer is from store to franchise
  void addDataInGrid(){
    ResultSet rs=null;
    String SQL=null;
    String art_no="";
    String item_name="";
    String size="";
    String color="";
    long qty=0;
    if(cboReason.getSelectedIndex()==0){ //show message when the reason is invalid
      ut.showMessage(frmMain,"Warning","Please choose a modified reason");
      return;
    }
    else if(cboReason.getSelectedIndex()==1){//status is adjusment-add
        SQL = "select a.item_id, a.art_no, a.item_name, b1.attr_dtl_desc ssize, b2.attr_dtl_desc color, d.BACK_ROOM_QTY do_qty   "
        + "from BTM_IM_ITEM_MASTER a , BTM_IM_ATTR_DTL b1, BTM_IM_ATTR_DTL b2, BTM_IM_FRANCHISE_DO c, BTM_IM_ITEM_LOC_SOH d "
        + "where a.ITEM_ID = '" + txtItemID.getText() + "' and b1.ATTR_DTL_ID=a.ATTR2_DTL_ID and b2.ATTR_DTL_ID=a.ATTR1_DTL_ID  "
        + "and FRANCHISE_DO_ID='" + txtTransferID.getText() + "' and a.item_id=d.item_id and store_id = (select store_id from BTM_IM_STORE where NAME='" + txtFrom.getText() + "') ";
    }
    else if(cboReason.getSelectedIndex()==2){//status is adjusment-sub
      SQL = "select a.item_id,a.art_no,a.item_name,b1.attr_dtl_desc ssize, b2.attr_dtl_desc color,c.do_qty  "
          + "from BTM_IM_ITEM_MASTER a, BTM_IM_ATTR_DTL b1, BTM_IM_ATTR_DTL b2, BTM_IM_FRANCHISE_DO_ITEM c "
          + "where c.franchise_do_id = '" + txtTransferID.getText()
          + "' and c.item_id='" + txtItemID.getText()
          + "' and a.item_id=c.item_id and b1.ATTR_DTL_ID=a.ATTR2_DTL_ID and b2.ATTR_DTL_ID=a.ATTR1_DTL_ID";
    }
    try{
    stmt=DAL.getConnection().createStatement();
    rs=DAL.executeQueries(SQL,stmt);
    if(rs.next()){
      art_no = rs.getString("art_no");
      item_name = rs.getString("item_name");
      size = rs.getString("ssize");
      color = rs.getString("color");
      qty = rs.getLong("do_qty");
    }
    if((rs.getLong("do_qty"))==0){ //the selected item is not exist in store
      ut.showMessage(frmMain,"Warning","Selected item is not available in source store");
      txtItemID.setText("");
      txtQty.setText("");
      cboReason.setSelectedIndex(0);

      stmt.close();
      rs.close();
      return;
    }

    stmt.close();
    rs.close();
   }catch(Exception ex){

    }
    if(Long.parseLong(txtQty.getText())>qty){
      if (cboReason.getSelectedIndex() == 2) {//modifying reason is sub
        ut.showMessage(frmMain, "Warning",
                       "The input quantity is over stock quantity");
      }
      else { //modifying reason is add
        ut.showMessage(frmMain, "Warning",
            "The input quantity must smaller than the real stock quantity");
      }
      txtQty.setText("");
      return;
    }
    if (!isExistItem(txtItemID.getText().trim(),cboReason.getSelectedItem().toString(),Long.parseLong(txtQty.getText()))){
      dm.addRow(new Object[] {txtItemID.getText().trim(),
                art_no, item_name, size, color, txtQty.getText(),
                cboReason.getSelectedItem()});
    }
    //set focus for last row
    if (tbl.getRowCount() > 0) {
      tbl.setRowSelectionInterval(tbl.getRowCount() - 1, tbl.getRowCount() - 1);
    }
    //show current row
    Rectangle bounds = tbl.getCellRect(tbl.getRowCount() - 1, 0, true);
    jScrollPane1.getViewport().setViewPosition(bounds.getLocation());

    txtItemID.setText("");
    txtQty.setText("");
    cboReason.setSelectedIndex(0);
  }
//check if item is available in the showed grid
  boolean isExistItem(String itemID, String status, long qty){
    for (int i = this.rowCount; i<tbl.getRowCount(); i++){
      String itemID1 = tbl.getValueAt(i, 0).toString();
      long qty1 = Long.parseLong(tbl.getValueAt(i, 5).toString());
      String status1 = tbl.getValueAt(i, 6).toString();
      if (status.equalsIgnoreCase(status1)) {
        if (itemID.equalsIgnoreCase(itemID1)) {
          qty = qty + qty1;
          tbl.setValueAt(new Long(qty),i,5);
          return true;
        }
      }
    }
    return false;
  }
  private String getStoreId(String storeName){
    String storeId=null;
    String SQL="select STORE_ID from BTM_IM_STORE where NAME = '" + txtFrom.getText().toString()
        + "'";
    try{
      stmt=DAL.getConnection().createStatement();
      ResultSet rs=DAL.executeQueries(SQL,stmt);
      if(rs.next()){
        storeId = rs.getString("STORE_ID");
      }
      rs.close();
      stmt.close();
    }catch(Exception e){
      ExceptionHandle eh=new ExceptionHandle();
      eh.ouputError(e);
    }
    return storeId;
  }

  void btnDone_actionPerformed(ActionEvent e) {
    if (type_flag == 2) {
      addModifiedDataIntoDB();
    }
//    else if (type_flag==3){
    else {
      if (!vModifySql.isEmpty()) {
        try {
          DAL.executeBatchQuery(vModifySql);
          vModifySql.clear();
        }
        catch (Exception ex) {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(ex);
        }
      }
    }
    resetData(); //clear data in grid
    frmMain.showScreen(Constant.SCRS_TRANSFER_FRAN_VIEW);

  }

//add modified data into database
  void addModifiedDataIntoDB() {
    Vector vSql = new Vector();
    int seq = getSeqID(txtTransferID.getText());
    seq++;
    for (int i = this.rowCount; i < tbl.getRowCount(); i++) {
      long qty = Long.parseLong(tbl.getValueAt(i, 5).toString());
      String itemID = tbl.getValueAt(i, 0).toString();
      String itemName = tbl.getValueAt(i, 2).toString();
      if (tbl.getValueAt(i, 6).toString().equalsIgnoreCase("Adjustment-Add")) {
        String SQL = "insert into BTM_IM_FRANCHISE_DO_ITEM "
            + "values ('" + txtTransferID.getText()
            + "','" + itemID + "','" + itemName + "'," + qty
            + ",0,0,0,2," + seq + ") ";
        vSql.addElement(SQL);
//        System.out.println(SQL);
        SQL = "update BTM_IM_ITEM_LOC_SOH "
            + "set STOCK_ON_HAND = STOCK_ON_HAND - " + qty
            + ", BACK_ROOM_QTY = BACK_ROOM_QTY - " + qty
            + " where ITEM_ID = '" + itemID + "' and STORE_ID ='" +
            getStoreId(txtFrom.getText().toString()) + "'";
        vSql.addElement(SQL);
//        System.out.println(SQL);
        SQL = "update BTM_IM_ITEM_FRAN_SOH "
            + "set STOCK_ON_HAND = STOCK_ON_HAND + "
            + qty + ", BACK_ROOM_QTY = BACK_ROOM_QTY + "
            + qty + " where ITEM_ID = '" + itemID
            + "' and FRANCHISE_ID = '" + getFranID(txtTo.getText().toString()) +
            "'";
        vSql.addElement(SQL);
//        System.out.println(SQL);
      }
      else if (tbl.getValueAt(i,
               6).toString().equalsIgnoreCase("Adjustment-Sub")) {
        String SQL = "insert into BTM_IM_FRANCHISE_DO_ITEM "
            + "values ('" + txtTransferID.getText()
            + "','" + itemID + "','" + itemName + "'," + qty
            + ",0,0,0,3," + seq + ") ";
        vSql.addElement(SQL);
//        System.out.println(SQL);
        SQL = "update BTM_IM_ITEM_LOC_SOH "
            + "set STOCK_ON_HAND = STOCK_ON_HAND + " + qty
            + ", BACK_ROOM_QTY = BACK_ROOM_QTY + " + qty
            + " where ITEM_ID = '" + itemID + "' and STORE_ID ='" +
            getStoreId(txtFrom.getText().toString()) + "'";
        vSql.addElement(SQL);
//        System.out.println(SQL);
        SQL = "update BTM_IM_ITEM_FRAN_SOH "
            + "set STOCK_ON_HAND = STOCK_ON_HAND - "
            + qty + ", BACK_ROOM_QTY = BACK_ROOM_QTY - "
            + qty + " where ITEM_ID = '" + itemID
            + "' and FRANCHISE_ID = '" + getFranID(txtTo.getText().toString()) +
            "'";
        vSql.addElement(SQL);
//        System.out.println(SQL);
      }
    }
    if (!vSql.isEmpty()) {
      DAL.executeBatchQuery(vSql);
      vSql.clear();
    }

  }

//clear data in grid after performed button Done
  void resetData() {
    txtItemID.setText("");
    txtQty.setText("");
    cboReason.setSelectedIndex(0);
    txtDiscount.setText("");
    while (tbl.getRowCount() > 0) {
      dm.removeRow(0);
    }
  }

//get sequence ID of the transfer
  int getSeqID(String transferID) {
    int seq = 0;
    Statement stmt = null;
    ResultSet rs = null;
    String sql =
        "select SEQ from BTM_IM_FRANCHISE_DO_ITEM where FRANCHISE_DO_ID = '"
        + transferID + "' order by SEQ desc";
//    System.out.println(sql);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sql, stmt);
      if (rs.next()) {
        seq = rs.getInt("seq");
      }

      stmt.close();
      rs.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return seq;
  }

  void txtQty_keyTyped(KeyEvent e) {
    if ( (e.getKeyChar() < '0' || e.getKeyChar() > '9') &&
        (e.getKeyChar() != e.VK_BACK_SPACE) && (e.getKeyChar() != e.VK_ENTER))
      e.consume();
  }

  public void btnPrint_actionPerformed(ActionEvent e) {
    if (type_flag == 2) { //transfer from store to franchise
      printReceiptStore(txtTransferID.getText(), txtFrom.getText(),
                        txtTo.getText(), txtReceiverName.getText(),
                        txtTransferDate.getText(), tbl, DAL);
    }
    else {
      printReceipt(txtTransferID.getText(), txtFrom.getText(), txtTo.getText(),
                   txtReceiverName.getText(), txtTransferDate.getText(), tbl,
                   DAL);
    }
    //print PDF file
    /*try {
      String[] arg = {"./file/" + txtTransferID.getText() + ".pdf"};
      PrintPDF.main(arg);
         }
         catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
         }*/
  }

  void printReceipt(String strReceiptID, String strStoreRoom,
                    String strToStoreRoom, String strSupplier, String strDate,
                    BJTable tbl,
                    DataAccessLayer DAL) {
    String strHeader[][] = {
        {
        strStoreRoom + " to " + strToStoreRoom
    }
    };
    String strTitle[] = {
        "DELIVERY ORDER ON DATE " + strDate};
    String strInfo[][] = {
        {
        "DATE : " + strDate,
        "FRANCHISE: " + strToStoreRoom,
        "RECEIPT ID: " + strReceiptID}
    };
    String strFormat[] = {
        "String", "String", "String", "String", "String", "String", "double",
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
   /* int iTableSkew[] = {
        4, 7, 30, 10, 6, 7, 17, 10, 18};*/
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
      strTableItem[i][6] = getItemPrice(tbl.getValueAt(i, 0).toString(), DAL);

      //col 7
      if (tbl.getValueAt(i, 6) != null) {
        st = tbl.getValueAt(i, 6).toString()+"%";
      }
      else {
        st = "0%";
      }

      strTableItem[i][7] = st;

      //col 8
      double dTotalPrice = Double.parseDouble(tbl.getValueAt(i, 5).
                                              toString()) *
          Double.parseDouble(getItemPrice(tbl.getValueAt(i, 0).toString(), DAL)) -
          Double.parseDouble(tbl.getValueAt(i, 5).toString()) *
          Double.parseDouble(getItemPrice(tbl.getValueAt(i, 0).toString(), DAL)) *
          Double.parseDouble(getDiscountPer(strReceiptID,
                                            tbl.getValueAt(i, 0).toString(),
                                            DAL)) /
          100;
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
    /*pp.setFontInTableTitle(FontFactory.getFont(FontFactory.
                                               HELVETICA, 8, Font.BOLD,
                                               new Color(0, 0, 0)));*/
    pp.setFontInTable(FontFactory.getFont(FontFactory.HELVETICA, 9));
    /*pp.setFontInTableTotal(FontFactory.getFont(FontFactory.
                                               HELVETICA, 8, Font.BOLD,
                                               new Color(0, 0, 0)));*/
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
                                               HELVETICA, 8, Font.BOLD,
                                               new Color(0, 0, 0)));*/
    pp.setTotalGroupName("Total by Art#");

    pp.print();
  }

  void printReceiptStore(String strReceiptID, String strStoreRoom,
                         String strToStoreRoom, String strSupplier,
                         String strDate, BJTable tbl,
                         DataAccessLayer DAL) {
    String strHeader[][] = {
        {
        strStoreRoom + " to " + strToStoreRoom
    }
    };
    String strTitle[] = {
        "DELIVERY ORDER ON DATE "+strDate};
    String strInfo[][] = {
        {
        "DATE : " + strDate,
        "FRANCHISE: " + strToStoreRoom,
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
                     String strToStoreRoom, String strSupplier,String strDate,DataAccessLayer DAL) {
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
            "DELIVERY ORDER ON DATE "+strDate+"\n",
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
          cell = new PdfPCell(new Paragraph(new Chunk(ut.
              formatNumber(getItemPrice(tbl.getValueAt(l, 0).toString(),DAL)),
              FontFactory.getFont(FontFactory.
                                  HELVETICA, 8))));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.getDefaultCell().setVerticalAlignment(Element.
              ALIGN_MIDDLE);
          cell.setBorderColor(new Color(188, 188, 188));
          table.addCell(cell);

          //col 8
          st = getDiscountPer(strReceiptID,tbl.getValueAt(l, 0).toString(),DAL) + "%";

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
              Double.parseDouble(getItemPrice(tbl.getValueAt(l, 0).toString(),DAL)) -
              Double.parseDouble(tbl.getValueAt(l, 5).toString()) *
              Double.parseDouble(getItemPrice(tbl.getValueAt(l, 0).toString(),DAL)) *
              Double.parseDouble(getDiscountPer(strReceiptID,tbl.getValueAt(l, 0).toString(),DAL)) /
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
                Double.parseDouble(getItemPrice(tbl.getValueAt(i, 0).toString(),DAL)) -
                Double.parseDouble(tbl.getValueAt(i, 5).toString()) *
                Double.parseDouble(getItemPrice(tbl.getValueAt(i, 0).toString(),DAL)) *
                Double.parseDouble(getDiscountPer(strReceiptID,tbl.getValueAt(i, 0).toString(),DAL)) /
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

  }

  //Export to PDF file
  void createPdfFileStoreToFran(String strReceiptID, String strStoreRoom,
                     String strToStoreRoom, String strSupplier,String strDate,DataAccessLayer DAL) {
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
            "DELIVERY ORDER ON DATE " + strDate +"\n",
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
        rs = null;
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
        rs = null;
      }catch(Exception e){
      }
      return strInfo;
  }
  //get Discpunt Per
  public String getDiscountPer(String strFranID,String strItemID,DataAccessLayer DAL){
        String strInfo="";
        ResultSet rs = null;
        try{
          String sql = "Select DISCOUNT_PER from BTM_IM_FRANCHISE_DO_ITEM "+
                       "where FRANCHISE_DO_ID ='"+strFranID+"' and ITEM_ID ='"+strItemID+"' ";
          rs = DAL.executeQueries(sql);
          if (rs.next()) {
            if (rs.getString("DISCOUNT_PER")!=null){
              strInfo = rs.getString("DISCOUNT_PER");
            }else{
              strInfo =  "0";
            }
          }
          rs.getStatement().close();
        }catch(Exception e){
        }
        return strInfo;
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    if(txtItemID.getText().equalsIgnoreCase("")){ //check empty value of item ID
      ut.showMessage(frmMain,"Warning","Double click into modified item to get item ID");
      txtItemID.setText("");
      return;
    }
    if(txtQty.getText().equalsIgnoreCase("")){ //check empty value of quantity
      ut.showMessage(frmMain,"Warning","Please input the quantity");
      txtQty.setText("");
      return;
    }
    if(!ut.isDoubleString(txtQty.getText())){
       ut.showMessage(frmMain,"Warning","The quantity must be number");
       txtQty.setText("");
       txtQty.requestFocus(true);
       return;
    }
    if(Long.parseLong(txtQty.getText())==0){ //check zero value of quantity
      ut.showMessage(frmMain,"Warning","The quantity must greater than 0");
      txtQty.setText("");
      return;
    }
    if(type_flag==2){
      addDataInGrid();
    }
    else if(type_flag==3 || type_flag==4){
      modifyDataInGrid();
    }
  }


}

class PanelTransferFranViewDtl_btnPrint_actionAdapter
    implements java.awt.event.ActionListener {
  private PanelTransferFranViewDtl adaptee;
  PanelTransferFranViewDtl_btnPrint_actionAdapter(PanelTransferFranViewDtl
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnPrint_actionPerformed(e);
  }
}

class PanelTransferFranViewDtl_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferFranViewDtl adaptee;

  PanelTransferFranViewDtl_btnBack_actionAdapter(PanelTransferFranViewDtl adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelTransferFranViewDtl_tbl_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelTransferFranViewDtl adaptee;

  PanelTransferFranViewDtl_tbl_mouseAdapter(PanelTransferFranViewDtl adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.tbl_mouseClicked(e);
  }
}

class PanelTransferFranViewDtl_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferFranViewDtl adaptee;

  PanelTransferFranViewDtl_btnDone_actionAdapter(PanelTransferFranViewDtl adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelTransferFranViewDtl_txtQty_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferFranViewDtl adaptee;

  PanelTransferFranViewDtl_txtQty_keyAdapter(PanelTransferFranViewDtl adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtQty_keyTyped(e);
  }
}

class PanelTransferFranViewDtl_btnAdd_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferFranViewDtl adaptee;

  PanelTransferFranViewDtl_btnAdd_actionAdapter(PanelTransferFranViewDtl adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAdd_actionPerformed(e);
  }
}
