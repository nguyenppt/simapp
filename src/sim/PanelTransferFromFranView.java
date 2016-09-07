//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//


























































package sim;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import java.sql.*;
import btm.business.*;
import javax.swing.border.*;
import java.util.ResourceBundle;
//not use
public class PanelTransferFromFranView extends JPanel{
  FrameMainSim frmMain;
  DataAccessLayer DAL;
  FranBusObj franBusObj = new FranBusObj();
  Utils ut = new Utils();
  Statement stmt;
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel btnPanel = new BJPanel(Constant.PANEL_TYPE_HEADER); //the panel contains buttons
  BJButton btnView = new BJButton(); //declare button View//declare button View transfers from franchise
  BJButton btnBack = new BJButton(); //declare button Back
  JPanel dataPanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel infoPanel = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel();
  BJTable tbl = new BJTable(dm,true);
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel datePanel = new JPanel();
  JLabel lblTransferFromDate = new JLabel();
  JTextField txtFromDate = new JTextField();
  JButton btnSearchFromDate = new JButton();
  JPanel franPanel = new JPanel();
  JLabel lblFromFran = new JLabel();
  BJComboBox cboFromFran = new BJComboBox();
  JLabel lblToFran = new JLabel();
  BJComboBox cboToFran = new BJComboBox();
  JLabel lblTransferToDate = new JLabel();
  JTextField txtToDate = new JTextField();
  JButton btnSearchToDate = new JButton();
  TitledBorder titledBorder1;
  Border border1;
  TitledBorder titledBorder2;
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);
//init table to contains view data
  public PanelTransferFromFranView(FrameMainSim frmMain) {
    try {
      this.frmMain=frmMain;
      this.DAL=frmMain.getDAL(); //set connection
      jbInit();//init screen and activate components
      initScreen();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  void initScreen(){
    franBusObj.setCboFran(cboFromFran,DAL);
    franBusObj.setCboFran(cboToFran,DAL);
  }

  void showTransferGrid(){
  if (!txtFromDate.getText().equalsIgnoreCase("") && !ut.checkDate(txtFromDate.getText(),"/")){
    ut.showMessage(frmMain,"Warning!","Wrong format.From_Date must be dd/mm/yyyy.");
    txtFromDate.setText("");
    btnSearchFromDate.requestFocus(true);
    return;
  }
  if (!txtToDate.getText().equalsIgnoreCase("") && !ut.checkDate(txtToDate.getText(),"/")){
    ut.showMessage(frmMain,"Warning!","Wrong format.To_Date must be dd/mm/yyyy.");
    txtToDate.setText("");
    btnSearchToDate.requestFocus(true);
    return;
  }
  dm.resetIndexes();
  try{
     stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     tbl.setData(franBusObj.getTransferFranFromFran(DAL,stmt,txtFromDate.getText(),txtToDate.getText(),cboFromFran.getSelectedData(),cboToFran.getSelectedData()));
     stmt.close();
  }
  catch(Exception e){
    ExceptionHandle eh = new ExceptionHandle();
    eh.ouputError(e);
  }

  tbl.setAutoResizeMode(tbl.AUTO_RESIZE_ALL_COLUMNS);
  tbl.getTableHeader().setReorderingAllowed(false);
  tbl.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 13));
  tbl.setHeaderName("Transfer ID", 0);
  tbl.setHeaderName("From Franchise", 1);
  tbl.setHeaderName("To Franchise", 2);
  tbl.setHeaderName("Transfer Date", 3);
  tbl.setHeader(new java.awt.Font("Dialog", 1, 15));
}

  private void jbInit() throws Exception {
//    System.out.println("N21");
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Search by Date");
    border1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Search by Transfer Date");
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Search by Franchise Name");
    cboFromFran.setPreferredSize(new Dimension(200, 25));
    cboFromFran.setFont(new java.awt.Font("Dialog", 1, 11));
    registerKeyboardActions();
    this.setPreferredSize(new Dimension(800, 600));
    this.setLayout(borderLayout1);
    /*set methods of View button*/
    btnView.setToolTipText("View (F1)");
    btnView.setVerifyInputWhenFocusTarget(true);
    btnBack.setActionCommand("<html><center><b>Back</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnView.setText("<html><center><b>View</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnView.addActionListener(new PanelTransferFromFranView_btnView_actionAdapter(this));
    /*set methods of View button*/
    btnBack.setAlignmentX((float) 0.0);
    btnBack.setToolTipText("Back (F2)");
    btnBack.setActionCommand("<html><center><b>Back</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnBack.setText("<html><center><b>Back</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
    "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F2</html>");
    btnBack.addActionListener(new PanelTransferFromFranView_btnBack_actionAdapter(this));
    //add buttons into panel
    btnPanel.setPreferredSize(new Dimension(800, 50));
    dataPanel.setPreferredSize(new Dimension(800, 550));
    dataPanel.setLayout(borderLayout2);
    infoPanel.setOpaque(true);
    infoPanel.setPreferredSize(new Dimension(800, 110));
    infoPanel.setLayout(flowLayout1);
//set properties for btn Search Date
    tbl.addKeyListener(new PanelTransferFromFranView_tbl_keyAdapter(this));
    tbl.addMouseListener(new PanelTransferFromFranView_tbl_mouseAdapter(this));
    jScrollPane1.setPreferredSize(new Dimension(454, 470));
    datePanel.setBorder(border1);
    datePanel.setDebugGraphicsOptions(0);
    datePanel.setOpaque(true);
    datePanel.setPreferredSize(new Dimension(380, 100));
    lblTransferFromDate.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblTransferFromDate.setPreferredSize(new Dimension(120, 25));
    lblTransferFromDate.setText("From Date:");
    txtFromDate.setFont(new java.awt.Font("Dialog", 1, 11));
    txtFromDate.setPreferredSize(new Dimension(160, 25));
    txtFromDate.setText("");
    txtToDate.setFont(new java.awt.Font("Dialog", 1, 11));
    txtToDate.setPreferredSize(new Dimension(160, 25));
    txtToDate.setText("");
    btnSearchFromDate.setFont(new java.awt.Font("Dialog", 1, 12));
    btnSearchFromDate.setPreferredSize(new Dimension(35, 25));
    btnSearchFromDate.setText("...");
    btnSearchFromDate.addActionListener(new PanelTransferFromFranView_btnSearchFromDate_actionAdapter(this));
    franPanel.setBorder(titledBorder2);
    franPanel.setDebugGraphicsOptions(0);
    franPanel.setPreferredSize(new Dimension(380, 100));
    lblFromFran.setFont(new java.awt.Font("Dialog", 1, 12));
    lblFromFran.setPreferredSize(new Dimension(120, 25));
    lblFromFran.setText("From Franchise:");
    lblToFran.setFont(new java.awt.Font("Dialog", 1, 12));
    lblToFran.setPreferredSize(new Dimension(120, 25));
    lblToFran.setText("To Franchise:");
    cboToFran.setFont(new java.awt.Font("Dialog", 1, 11));
    cboToFran.setPreferredSize(new Dimension(200, 25));
    lblTransferToDate.setFont(new java.awt.Font("Dialog", 1, 12));
    lblTransferToDate.setPreferredSize(new Dimension(120, 25));
    lblTransferToDate.setText("To Date:");
    btnSearchToDate.setFont(new java.awt.Font("Dialog", 1, 12));
    btnSearchToDate.setPreferredSize(new Dimension(35, 25));
    btnSearchToDate.setText("...");
    btnSearchToDate.addActionListener(new PanelTransferFromFranView_btnSearchToDate_actionAdapter(this));
    this.add(btnPanel, BorderLayout.NORTH);
    btnPanel.add(btnView, null);
    btnPanel.add(btnBack, null);

    this.add(dataPanel, BorderLayout.CENTER);
    dataPanel.add(infoPanel,  BorderLayout.NORTH);

//add components of search view into panel Info
    infoPanel.add(datePanel, null);
    infoPanel.add(franPanel, null);

//add elements into panel Date
    datePanel.add(lblTransferFromDate, null);
    datePanel.add(txtFromDate, null);
    datePanel.add(btnSearchFromDate, null);

//add elements into panel Fran
    franPanel.add(lblFromFran, null);
    franPanel.add(cboFromFran, null);
    franPanel.add(lblToFran, null);

    franPanel.add(cboToFran, null);
    dataPanel.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tbl, null);
    datePanel.add(lblTransferToDate, null);
    datePanel.add(txtToDate, null);
    datePanel.add(btnSearchToDate, null);
  }

  void btnBack_actionPerformed(ActionEvent e) {
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
    frmMain.showScreen(Constant.SCRS_TRANSFER_FRAN_VIEW);
  }
  public void registerKeyboardActions(){
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
  }
  class KeyAction extends AbstractAction {
    private Integer identifier =null;

    public KeyAction (Integer identifier){
      this.identifier = identifier;
  }
    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnView.doClick();
      }
      else if (identifier.intValue() == KeyEvent.VK_F2) {
       btnBack.doClick();
      }
    }
  }

  void btnView_actionPerformed(ActionEvent e) {
    showTransferGrid();
  }

  void tbl_keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_F2){
      btnBack.doClick();
    }
  }

  void tbl_mouseClicked(MouseEvent e) {
    if(e.getClickCount()==2){
      frmMain.showScreen(Constant.SCRS_VIEW_TRANSFER_FRAN_DTL);
      frmMain.pnlTransferFranViewDtl.screen_flag=Constant.SCRS_TRANSFER_FROM_FRAN_VIEW;
      frmMain.pnlTransferFranViewDtl.setDataComboReason(frmMain.pnlTransferFranViewDtl.cboReason);
      showTransferViewDetail();
      frmMain.pnlTransferFranViewDtl.txtDiscount.setEditable(true);
      if(tbl.getValueAt(tbl.getSelectedRow(),1).toString().equalsIgnoreCase(tbl.getValueAt(tbl.getSelectedRow(),2).toString())){
          frmMain.pnlTransferFranViewDtl.type_flag=3; //transfer from franchise to itself
      }else{
        frmMain.pnlTransferFranViewDtl.type_flag = 4;//transfer from franchise to another
      }
      showTransferFromFranViewDetail();
      frmMain.pnlTransferFranViewDtl.cboReason.setEnabled(false);
      frmMain.pnlTransferFranViewDtl.rowCount=frmMain.pnlTransferFranViewDtl.tbl.getRowCount();
    }
  }
//get general infor of selected transfer
  void showTransferViewDetail(){
  ResultSet rs=null;
  String sql = " select FRANCHISE_DO_ID,RECEIVER_NAME,to_char(FRANCHISE_DO_DATE,'dd/mm/yyyy') sdate, "
      + "concat(b.FIRST_NAME,concat(' ',b.LAST_NAME)) as NAME  "
      + "from BTM_IM_FRANCHISE_DO a, BTM_IM_EMPLOYEE b  "
      + "where a.FRANCHISE_DO_ID = '" +
      tbl.getValueAt(tbl.getSelectedRow(), 0).toString()
      + "' and b.EMP_ID = a.USER_ID ";
  try{
    stmt = DAL.getConnection().createStatement(ResultSet.
                                              TYPE_SCROLL_INSENSITIVE,
                                              ResultSet.CONCUR_READ_ONLY);
    rs=DAL.executeQueries(sql,stmt);
    rs.next();
    if (!rs.isBeforeFirst() && !rs.isAfterLast()) {
      frmMain.pnlTransferFranViewDtl.txtTransferID.setText(rs.getString("FRANCHISE_DO_ID"));
      frmMain.pnlTransferFranViewDtl.txtFrom.setText(tbl.getValueAt(tbl.getSelectedRow(),1).toString());
      frmMain.pnlTransferFranViewDtl.txtTo.setText(tbl.getValueAt(tbl.getSelectedRow(),2).toString());
      frmMain.pnlTransferFranViewDtl.txtTransferDate.setText(rs.getString("sdate"));
      frmMain.pnlTransferFranViewDtl.txtReceiverName.setText(rs.getString("RECEIVER_NAME"));
      frmMain.pnlTransferFranViewDtl.txtCreaterName.setText(rs.getString("NAME"));
    }
    stmt.close();
    rs.close();
  }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
 }
//get data of selected transfer whose type is '3' or '4'
void showTransferFromFranViewDetail(){
  ResultSet rs=null;
  String SQL = "select fr.ITEM_ID,ma.ART_NO,ma.ITEM_NAME,at1.ATTR_DTL_DESC ssize,at2.ATTR_DTL_DESC color, fr.DO_QTY, DISCOUNT_PER  "
            + "from BTM_IM_FRANCHISE_DO_ITEM fr,BTM_IM_ITEM_MASTER ma,BTM_IM_ATTR_DTL at1,BTM_IM_ATTR_DTL at2  "
            + "where fr.FRANCHISE_DO_ID = '" + tbl.getValueAt(tbl.getSelectedRow(),0).toString()
            +"' and ma.ATTR2_DTL_ID=at1.ATTR_DTL_ID and ma.ATTR1_DTL_ID=at2.ATTR_DTL_ID and fr.ITEM_ID=ma.ITEM_ID ";
    try{
        rs=DAL.executeScrollQueries(SQL);
        frmMain.pnlTransferFranViewDtl.dm.resetIndexes();
        frmMain.pnlTransferFranViewDtl.tbl.removeAll();
        frmMain.pnlTransferFranViewDtl.tbl.setData(rs);
        frmMain.pnlTransferFranViewDtl.tbl.setAutoResizeMode(tbl.AUTO_RESIZE_ALL_COLUMNS);
        frmMain.pnlTransferFranViewDtl.tbl.getTableHeader().setReorderingAllowed(false);
        frmMain.pnlTransferFranViewDtl.tbl.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 13));
        frmMain.pnlTransferFranViewDtl.tbl.setHeaderName("Item ID", 0);
        frmMain.pnlTransferFranViewDtl.tbl.setHeaderName("UPC", 1);
        frmMain.pnlTransferFranViewDtl.tbl.setHeaderName("Item Name", 2);
        frmMain.pnlTransferFranViewDtl.tbl.setHeaderName("Size", 3);
        frmMain.pnlTransferFranViewDtl.tbl.setHeaderName("Color", 4);
        frmMain.pnlTransferFranViewDtl.tbl.setHeaderName("Quantity", 5);
        frmMain.pnlTransferFranViewDtl.tbl.setHeaderName("% Discount", 6);
        frmMain.pnlTransferFranViewDtl.tbl.setHeader(new java.awt.Font("Dialog", 1, 15));
       // ut.changeDataTypetoLong(5,frmMain.pnlTransferFranViewDtl.dm);//Yen.Dinh 19-06-2006
        stmt.close();
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
}

  void btnSearchFromDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnSearchFromDate.getX() + posXFrame+90;
    posY = this.btnSearchFromDate.getY() + posYFrame+90;
    TimeDlg Date = new TimeDlg(null);
    Date.setTitle(lang.getString("ChooseDate"));
    Date.setResizable(false);
    Date.setLocation(posX, posY);
    Date.setVisible(true);

      if (Date.isOKPressed()) {
        java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String date = fd.format(Date.getDate());
        this.txtFromDate.setText(date);
        this.txtFromDate.requestFocus(true);
      }
  }

  void btnSearchToDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;
    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnSearchToDate.getX() + posXFrame+90;
    posY = this.btnSearchToDate.getY() + posYFrame+90;
      TimeDlg Date = new TimeDlg(null);
      Date.setTitle(lang.getString("ChooseDate"));
      Date.setResizable(false);
      Date.setLocation(posX, posY);
      Date.setVisible(true);

      if (Date.isOKPressed()) {
        java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String date = fd.format(Date.getDate());
        this.txtToDate.setText(date);
        this.txtToDate.requestFocus(true);
      }
  }
}
class PanelTransferFromFranView_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferFromFranView adaptee;

  PanelTransferFromFranView_btnBack_actionAdapter(PanelTransferFromFranView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelTransferFromFranView_btnView_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferFromFranView adaptee;

  PanelTransferFromFranView_btnView_actionAdapter(PanelTransferFromFranView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnView_actionPerformed(e);
  }
}

class PanelTransferFromFranView_tbl_keyAdapter extends java.awt.event.KeyAdapter {
  PanelTransferFromFranView adaptee;

  PanelTransferFromFranView_tbl_keyAdapter(PanelTransferFromFranView adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tbl_keyPressed(e);
  }
}

class PanelTransferFromFranView_tbl_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelTransferFromFranView adaptee;

  PanelTransferFromFranView_tbl_mouseAdapter(PanelTransferFromFranView adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.tbl_mouseClicked(e);
  }
}

class PanelTransferFromFranView_btnSearchFromDate_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferFromFranView adaptee;

  PanelTransferFromFranView_btnSearchFromDate_actionAdapter(PanelTransferFromFranView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchFromDate_actionPerformed(e);
  }
}

class PanelTransferFromFranView_btnSearchToDate_actionAdapter implements java.awt.event.ActionListener {
  PanelTransferFromFranView adaptee;

  PanelTransferFromFranView_btnSearchToDate_actionAdapter(PanelTransferFromFranView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearchToDate_actionPerformed(e);
  }
}


