//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//













































package sim;

import common.*;
import btm.attr.StockOnHand;
import btm.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.Vector;
import javax.swing.border.*;
import btm.business.*;
import btm.attr.*;
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
public class PanelViewDamageGoods extends JPanel {
  //Declare Data Access Layer
  DataAccessLayer DAL;
  //Declare Utils
  Utils ut = new Utils();
  //Declare Frame Main
  FrameMainSim frmMain;
  //Declare a batch update
  Statement stmt = null;
  ReceiptBusObj recBusObj = new ReceiptBusObj();
  String supplierID = "";
  Vector vArrayInfo = new Vector();//Vector save array info of Receipt Item
  ItemMasterBusObj  bItemMasterBusObj = new ItemMasterBusObj();
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch(col){
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm,true){
    public void tableChanged(TableModelEvent e) {
      super.tableChanged(e);
      repaint();
    }
  };

  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JLabel lblReceiptDate = new JLabel();
  BJButton btnBack = new BJButton();
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray,Color.white,null,null);
  GridLayout gridLayout1 = new GridLayout();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  JTextField txtReceiptDate = new JTextField();
  TitledBorder titledBorder1;
  boolean flagSetHotKeyForAdd = true;
  JButton btnDate = new JButton();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);
  public PanelViewDamageGoods(FrameMainSim frmMain) {
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
//        System.out.println("N23");
    registerKeyboardActions();
    titledBorder1 = new TitledBorder("");
    this.setLayout(borderLayout1);
    jPanel1.setPreferredSize(new Dimension(800, 50));
    this.setPreferredSize(new Dimension(800, 600));
    jPanel2.setPreferredSize(new Dimension(800, 150));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setPreferredSize(new Dimension(800, 440));
    jPanel3.setLayout(gridLayout1);
    jScrollPane1.setPreferredSize(new Dimension(760, 395));
    jPanel5.setMinimumSize(new Dimension(300, 150));
    jPanel5.setLayout(borderLayout4);
    jPanel8.setPreferredSize(new Dimension(300, 150));
    jPanel8.setLayout(flowLayout2);
    jPanel9.setPreferredSize(new Dimension(180, 150));
    jPanel9.setLayout(flowLayout3);
    lblReceiptDate.setFont(new java.awt.Font("SansSerif", 1, 12));
    lblReceiptDate.setPreferredSize(new Dimension(90, 20));
    lblReceiptDate.setText("Receipt Date : ");
    btnBack.setPreferredSize(new Dimension(120, 37));
    btnBack.setToolTipText("Back (F1)");
    btnBack.setText("<html><center><b>Back </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnBack.addActionListener(new PanelViewDamageGoods_btnBack_actionAdapter(this));
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    txtReceiptDate.setFont(new java.awt.Font("SansSerif", 1, 11));
    txtReceiptDate.setMinimumSize(new Dimension(6, 21));
    txtReceiptDate.setPreferredSize(new Dimension(170, 20));
    txtReceiptDate.setToolTipText("");
    txtReceiptDate.setSelectionStart(10);
    txtReceiptDate.setText(ut.getSystemDate(1));
    txtReceiptDate.addKeyListener(new PanelViewDamageGoods_txtReceiptDate_keyAdapter(this));
    btnDate.setText("...");
    btnDate.addActionListener(new PanelViewDamageGoods_btnDate_actionAdapter(this));
    table.addMouseListener(new PanelViewDamageGoods_table_mouseAdapter(this));
    this.add(jPanel1,  BorderLayout.NORTH);
    this.add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel8,  BorderLayout.WEST);
    jPanel8.add(lblReceiptDate, null);
    jPanel5.add(jPanel9,  BorderLayout.CENTER);
    jPanel9.add(txtReceiptDate, null);
    jPanel9.add(btnDate, null);
    this.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(table, null);
    jPanel1.add(btnBack, null);
    dm.addColumn("Damage Goods ID");
    dm.addColumn("From Store");
    dm.addColumn("To Store");
    dm.addColumn("Status");
    table.setRowHeight(30);
    table.setColor(Color.lightGray, Color.white);
  }

  void btnBack_actionPerformed(ActionEvent e) {
    while(dm.getRowCount()>0){
      dm.removeRow(0);
    }
    frmMain.showScreen(Constant.SCRS_RECEIPT_DAMAGE_GOODS);
//    frmMain.pnlDamageGoods.getData();
//    frmMain.pnlDamageGoods.cboFromStore.requestFocus(true);
  }

  void btnDate_actionPerformed(ActionEvent e) {
    int posX, posY, posXFrame, posYFrame;

    posXFrame = this.getX();
    posYFrame = this.getY();
    posX = this.btnDate.getX() + posXFrame + 150;
    posY = this.btnDate.getY() + posYFrame + 150;
    TimeDlg endDate = new TimeDlg(null);
    endDate.setTitle(lang.getString("ChooseEndDate"));
    endDate.setResizable(false);
    endDate.setLocation(posX, posY);
    endDate.setVisible(true);

    if (endDate.isOKPressed()) {
      java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
          "dd/MM/yyyy");
      String date = fd.format(endDate.getDate());
      this.txtReceiptDate.setText(date);
      this.txtReceiptDate.requestFocus(true);
      outInterface();
    }
  }

  void outInterface() {
    if (txtReceiptDate.getText().equals("")){
      return;
    }
    if (ut.checkDate(txtReceiptDate.getText(), "/") == false) {
      ut.showMessage(frmMain, "Warning!",
                     "Wrong date.Format of date is dd/mm/yyyy.");
      btnDate.requestFocus(true);
      return;
    }
    dm.resetIndexes();

    try{
      stmt = DAL.getConnection().createStatement(ResultSet.
                                              TYPE_SCROLL_INSENSITIVE,
                                              ResultSet.CONCUR_READ_ONLY);

      table.setData(getData(txtReceiptDate.getText(), stmt));
      stmt.close();
    }
    catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 13));
    table.setHeaderName("Damage Goods ID", 0);
    table.setHeaderName("From Store", 1);
    table.setHeaderName("To Store", 2);
    table.setHeaderName("Status",3);
    table.setHeader(new java.awt.Font("Dialog", 1, 15));

//      ut.changeDataTypetoLong(1, dm);
  }



  ResultSet getData(String date, Statement stmt){
    ResultSet rs = null;
    String sqlStr = "select RECEIPT_DMG_GOODS_ID, s1.name from_store, s2.name to_store, (case when status = 'A' then 'Adjustment' else 'Damage Goods' end) status from btm_im_receive_dmg_goods r, btm_im_store s1, btm_im_store s2 where r.FROM_STORE_ID = s1.STORE_ID and r.TO_STORE_ID = s2.STORE_ID  and Receipt_Date = to_date('" +
          date + "','dd-MM-yyyy')";
//    System.out.println(sqlStr);
    try {

      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  void txtReceiptDate_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
        if (ut.checkDate(txtReceiptDate.getText(), "/") || ut.checkDate(txtReceiptDate.getText(), "-")) {
           outInterface();
        }
        else {
          ut.showMessage(frmMain, "Warning", "Input must be a date type");
          while(dm.getRowCount()>0){
            dm.removeRow(0);
          }
        }
      }
  }

  void table_mouseClicked(MouseEvent e) {
    if(e.getClickCount()==2){
      frmMain.showScreen(Constant.SCRS_DETAIL_DAMAGE_GOODS);
      frmMain.pnlDetailDamageGoods.showData(table.getValueAt(table.getSelectedRow(),0).toString());
    }
  }
  private void registerKeyboardActions() {
/// set up the maps:
  InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
  ActionMap actionMap = getActionMap();

// F1
  Integer key = new Integer(KeyEvent.VK_F1);
  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), key);
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
      if (identifier.intValue() == KeyEvent.VK_F1 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
        btnBack.doClick();
      }
    }
  }
}

class PanelViewDamageGoods_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelViewDamageGoods adaptee;

  PanelViewDamageGoods_btnBack_actionAdapter(PanelViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelViewDamageGoods_btnDate_actionAdapter implements java.awt.event.ActionListener {
  PanelViewDamageGoods adaptee;

  PanelViewDamageGoods_btnDate_actionAdapter(PanelViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDate_actionPerformed(e);
  }
}

class PanelViewDamageGoods_txtReceiptDate_keyAdapter extends java.awt.event.KeyAdapter {
  PanelViewDamageGoods adaptee;

  PanelViewDamageGoods_txtReceiptDate_keyAdapter(PanelViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtReceiptDate_keyTyped(e);
  }
}

class PanelViewDamageGoods_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelViewDamageGoods adaptee;

  PanelViewDamageGoods_table_mouseAdapter(PanelViewDamageGoods adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}
