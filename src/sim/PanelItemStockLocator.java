package sim;

import java.awt.*;
import  common.*;
import javax.swing.*;
import  btm.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ResourceBundle;
/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Item Lookup -> Item Details -> Stock Locator</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author TrungNT
 * @version 1.0
 */

public class PanelItemStockLocator extends JPanel {
  FrameMainSim frmMain;
  DataAccessLayer DAL ;
  Utils ut=new Utils();
  String itemId="";

  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel pnlStockLocator = new BJPanel();
  BJPanel pnlButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
  BJPanel pnlSOH = new BJPanel();
  BJPanel pnlItem = new BJPanel();
  BJPanel jPanel5 = new BJPanel();
  BJPanel pnlItemDesc = new BJPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  BJButton btnDone = new BJButton();
  FlowLayout flowLayout1 = new FlowLayout();
  BJPanel jPanel1 = new BJPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BJPanel jPanel2 = new BJPanel();
  BJPanel jPanel3 = new BJPanel();
  GridLayout gridLayout1 = new GridLayout();
  BJPanel jPanel4 = new BJPanel();
  BJPanel jPanel7 = new BJPanel();
  GridLayout gridLayout2 = new GridLayout();
  JLabel lblItemIDVal = new JLabel();
  BJLabel lblItemID = new BJLabel();
  JLabel lblItemDescVal = new JLabel();
  BJLabel lblItemDesc = new BJLabel();
  FlowLayout flowLayout2 = new FlowLayout();
  FlowLayout flowLayout3 = new FlowLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  BorderLayout borderLayout4 = new BorderLayout();

  SortableTableModel dm = new SortableTableModel();
  BJTable tblSOH = new BJTable(dm,false);
  BJPanel jPanel8 = new BJPanel();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelItemStockLocator(FrameMainSim frmMain) {
    try {
      this.frmMain=frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    registerKeyboardActions();
    this.setLayout(borderLayout1);
    pnlStockLocator.setLayout(borderLayout2);
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setSelected(false);
    btnDone.setText(lang.getString("Done")+" (F1)");
    btnDone.addActionListener(new PanelItemStockLocator_btnDone_actionAdapter(this));
    pnlButton.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    pnlItem.setLayout(borderLayout3);
    pnlItemDesc.setLayout(gridLayout1);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(0);
    jPanel3.setLayout(gridLayout2);
    lblItemIDVal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    lblItemIDVal.setText("jLabel1");
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setText(lang.getString("ItemID")+":");
    lblItemDescVal.setText("jLabel3");
    lblItemDesc.setText(lang.getString("Description")+":");
    jPanel7.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.LEFT);
    jPanel4.setLayout(flowLayout3);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    pnlSOH.setLayout(borderLayout4);
    this.setAlignmentX((float) 0.5);

    jPanel3.setBorder(BorderFactory.createEtchedBorder());
    tblSOH.addMouseListener(new PanelItemStockLocator_tblSOH_mouseAdapter(this));
    pnlItem.add(jPanel1, BorderLayout.WEST);
    this.add(pnlStockLocator, BorderLayout.CENTER);
    pnlStockLocator.add(pnlItem, BorderLayout.NORTH);
    pnlItem.add(pnlItemDesc, BorderLayout.CENTER);
    pnlItemDesc.add(jPanel3, null);
    jPanel3.add(jPanel7, null);
    jPanel7.add(lblItemID, null);
    jPanel7.add(lblItemIDVal, null);
    jPanel3.add(jPanel4, null);
    jPanel4.add(lblItemDesc, null);
    jPanel4.add(lblItemDescVal, null);
    pnlItemDesc.add(jPanel2, null);
    pnlItem.add(jPanel5, BorderLayout.EAST);
    pnlItem.add(jPanel8, BorderLayout.NORTH);
    pnlStockLocator.add(pnlSOH, BorderLayout.CENTER);
    pnlSOH.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tblSOH, null);
    this.add(pnlButton,  BorderLayout.NORTH);
    pnlButton.add(btnDone, null);
  }
 //set ID of selected item
  void setItemId(String itemId){
      this.itemId=itemId;
  }
  void showData(){
//      DataAccessLayer DAL =frmMain.getDAL();
      ResultSet rs=null;
      Statement stmt = null;
      String sqlQuery="";
      try {
          //show item desc
          sqlQuery="select i.ITEM_ID,i.ITEM_NAME,i.ITEM_DESC,s.SUPP_ID,s.SUPP_NAME,h.CHILD_ID,h.CHILD_NAME from BTM_IM_ITEM_MASTER i ,BTM_IM_ITEM_LOC_SUPPLIER isu ,BTM_IM_SUPPLIER s,BTM_IM_PROD_HIR h "+
                   "where i.ITEM_ID=isu.ITEM_ID and isu.SUPP_ID=s.SUPP_ID and i.CHILD_ID=h.CHILD_ID and i.ITEM_ID='" +itemId+"'";
//          System.out.println(sqlQuery);
          stmt = DAL.getConnection().createStatement();
          rs=DAL.executeQueries(sqlQuery,stmt);
          if (rs.next()){
            lblItemIDVal.setText(rs.getString("ITEM_ID"));
            lblItemDescVal.setText(rs.getString("ITEM_DESC"));
          }
          rs.close();
          stmt.close();
          //show item SOH
          sqlQuery="select STORE_ID,STOCK_ON_HAND,LAST_RECEIVED_DATE,LAST_RECEIVED_QTY from BTM_IM_ITEM_LOC_SOH where ITEM_ID='" +itemId+"'";
//          System.out.println(sqlQuery);
          stmt = DAL.getConnection().createStatement(ResultSet.
                                                         TYPE_SCROLL_INSENSITIVE,
                                                         ResultSet.CONCUR_READ_ONLY);

          rs=DAL.executeScrollQueries(sqlQuery,stmt);
          tblSOH.setData(rs);
          ut.changeDataTypetoLong(0,dm);
          ut.changeDataTypetoLong(1,dm);
          ut.changeDataTypetoLong(3,dm);
          rs.close();
          stmt.close();
      }
      catch (Exception ex) {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(ex);
      }


  }

  void btnDone_actionPerformed(ActionEvent e) {
     frmMain.showScreen(Constant.SCRS_ITEM_LOOKUP_DETAIL);

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

      //  if(flag == true){//4 nut
      if (identifier.intValue() == KeyEvent.VK_F1) {
        btnDone.doClick();
      }
    }
  }

  void tblSOH_mousePressed(MouseEvent e) {
//    btnDone.requestFocus();
  }



}

class PanelItemStockLocator_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelItemStockLocator adaptee;

  PanelItemStockLocator_btnDone_actionAdapter(PanelItemStockLocator adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelItemStockLocator_tblSOH_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelItemStockLocator adaptee;

  PanelItemStockLocator_tblSOH_mouseAdapter(PanelItemStockLocator adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.tblSOH_mousePressed(e);
  }
}
