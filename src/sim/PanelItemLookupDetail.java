package sim;

import java.awt.*;
import common.*;
import javax.swing.*;
import javax.swing.border.*;
import btm.swing.*;
import javax.swing.tree.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author TrungNT
 * @version 1.0
 */

public class PanelItemLookupDetail extends JPanel {
  FrameMainSim frmMain;
  DataAccessLayer DAL ;
  Utils ut=new Utils();
  String itemId="";//id of item
  String prodIDNode=""; //set id of prod hier node

  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel pnlButton = new BJPanel(Constant.PANEL_TYPE_HEADER);
  JPanel pnlItemDetail = new JPanel();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JButton btnStockLocator = new BJButton();
  JButton btnDone = new BJButton();
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout4 = new GridLayout();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BJPanel pnlCenterLeft = new BJPanel(true);
  BorderLayout borderLayout3 = new BorderLayout();
  BJPanel jPanel8 = new BJPanel();
  BJPanel jPanel9 = new BJPanel();
  BJPanel jPanel10 = new BJPanel();
  BJPanel jPanel11 = new BJPanel();
  BJPanel jPanel12 = new BJPanel();
  BJPanel jPanel13 = new BJPanel();
  BJPanel pnlCenterRight = new BJPanel(true);
  BJPanel jPanel15 = new BJPanel();
  BJPanel jPanel16 = new BJPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  Border border1;
  Border border2;
  BJPanel jPanel7 = new BJPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  BJPanel jPanel14 = new BJPanel();
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout2 = new GridLayout();
  BJPanel jPanel3 = new BJPanel(true);
  GridLayout gridLayout3 = new GridLayout();
  BJPanel jPanel4 = new BJPanel();
  BJPanel jPanel17 = new BJPanel();
  JLabel lblSOH = new BJLabel();
  JLabel lblLastReceipDate = new BJLabel();
  JLabel lblLastReceiptQty = new BJLabel();
  JLabel lblTotalVal = new JLabel();
  JLabel lblReceiptDateVal = new JLabel();
  JLabel lblReceiptQtyVal = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  BorderLayout borderLayout6 = new BorderLayout();
//  DefaultMutableTreeNode top =  new DefaultMutableTreeNode("Root");
//  BJTree treProdHier = new BJTree("BTM_IM_PROD_HIR",getData(),top);
  BNode rootTree ;
  BTreeModel pTreeModel;
  BJTree treProdHier ;

  BJPanel jPanel18 = new BJPanel();
  BJPanel jPanel19 = new BJPanel();
  FlowLayout flowLayout2 = new FlowLayout();
  JLabel lblItemVal = new JLabel();
  FlowLayout flowLayout3 = new FlowLayout();
  BJPanel jPanel20 = new BJPanel();
  BJPanel jPanel21 = new BJPanel();
  JLabel lblSupplierIDVal = new JLabel();
  JLabel lblSupplierNameVal = new JLabel();
  JLabel lblSUpplierName = new BJLabel();
  JLabel lblSupplierID = new BJLabel();
  JLabel lblItemID = new BJLabel();
  JLabel lblItemDescVal = new JLabel();
  JLabel lblItemDesc = new BJLabel();
  FlowLayout flowLayout4 = new FlowLayout();
  FlowLayout flowLayout5 = new FlowLayout();
  Border border3;
  BJPanel jPanel22 = new BJPanel();
  BJPanel jPanel23 = new BJPanel();
  BJPanel jPanel24 = new BJPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  BJPanel jPanel25 = new BJPanel();

  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

  public PanelItemLookupDetail(FrameMainSim frmMain) {
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
    rootTree =new BNode( new BNodeDetail("1","1","1",Constant.root, Constant.root));
    pTreeModel = new BTreeModel(rootTree);
    treProdHier=new BJTree(pTreeModel);

    border1 = BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151));
    border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    border3 = BorderFactory.createLineBorder(SystemColor.controlText,1);
    this.setLayout(borderLayout1);
    pnlItemDetail.setLayout(borderLayout2);
    jPanel2.setLayout(borderLayout5);
    btnStockLocator.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnStockLocator.setToolTipText(lang.getString("StockLocator")+" (F2)");
    btnStockLocator.setText(lang.getString("StockLocator")+" (F2)");
    btnStockLocator.addActionListener(new PanelItemLookupDetail_btnStockLocator_actionAdapter(this));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnDone.setToolTipText(lang.getString("Done")+" (F1)");
    btnDone.setText(lang.getString("Done")+" (F1)");
    btnDone.addActionListener(new PanelItemLookupDetail_btnDone_actionAdapter(this));
    pnlButton.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    jPanel1.setLayout(gridLayout4);
    jPanel6.setLayout(borderLayout3);
    jPanel5.setLayout(borderLayout4);
    pnlCenterLeft.setLayout(null);
    pnlCenterRight.setLayout(borderLayout6);
    jPanel7.setLayout(gridLayout1);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(0);
    gridLayout2.setRows(1);
    jPanel3.setBorder(BorderFactory.createEtchedBorder());
    jPanel3.setLayout(gridLayout2);
    gridLayout3.setHgap(0);
    jPanel4.setLayout(gridLayout3);
    lblSOH.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSOH.setText(lang.getString("Total")+":");
    lblSOH.setBounds(new Rectangle(9, 34, 94, 15));
    lblLastReceipDate.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblLastReceipDate.setText(lang.getString("LastReceiptDate")+":");
    lblLastReceipDate.setBounds(new Rectangle(9, 69, 125, 15));
    lblLastReceiptQty.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblLastReceiptQty.setText(lang.getString("LastReceiptQuantity")+":");
    lblLastReceiptQty.setBounds(new Rectangle(9, 104, 127, 15));
    lblTotalVal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblTotalVal.setText("jLabel5");
    lblTotalVal.setBounds(new Rectangle(141, 34, 76, 15));
    lblReceiptDateVal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReceiptDateVal.setText("jLabel6");
    lblReceiptDateVal.setBounds(new Rectangle(141, 69, 75, 15));
    lblReceiptQtyVal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblReceiptQtyVal.setText("jLabel7");
    lblReceiptQtyVal.setBounds(new Rectangle(141, 104, 73, 15));
    jPanel18.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.LEFT);
    lblItemVal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemVal.setText("jLabel2");
    jPanel19.setLayout(flowLayout3);
    flowLayout3.setAlignment(FlowLayout.LEFT);
    lblSupplierIDVal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSupplierIDVal.setText("jLabel3");
    lblSupplierNameVal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSupplierNameVal.setText("jLabel4");
    lblSUpplierName.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSUpplierName.setText(lang.getString("SupplierName")+":");
    lblSupplierID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblSupplierID.setText(lang.getString("SupplierID")+":");
    lblItemID.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemID.setText(lang.getString("ItemName")+": ");
    lblItemDescVal.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemDescVal.setText("jLabel1");
    lblItemDesc.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    lblItemDesc.setText(lang.getString("Description")+":");
    jPanel21.setLayout(flowLayout4);
    jPanel20.setLayout(flowLayout5);
    flowLayout5.setAlignment(FlowLayout.LEFT);
    flowLayout4.setAlignment(FlowLayout.LEFT);
    jPanel1.setBorder(null);
    jPanel22.setLayout(borderLayout7);
    jPanel25.setBorder(BorderFactory.createEtchedBorder());
    jPanel24.setPreferredSize(new Dimension(10, 1));
    jPanel23.setPreferredSize(new Dimension(10, 1));
    jPanel22.setPreferredSize(new Dimension(34, 1));
    this.setEnabled(false);
    this.addKeyListener(new PanelItemLookupDetail_this_keyAdapter(this));
    treProdHier.addKeyListener(new PanelItemLookupDetail_treProdHier_keyAdapter(this));
    pnlCenterLeft.addKeyListener(new PanelItemLookupDetail_pnlCenterLeft_keyAdapter(this));
    this.add(pnlButton,  BorderLayout.NORTH);
    this.add(pnlItemDetail, BorderLayout.CENTER);
    pnlItemDetail.add(jPanel2, BorderLayout.NORTH);
    pnlItemDetail.add(jPanel1, BorderLayout.CENTER);
    pnlButton.add(btnDone, null);
    pnlButton.add(btnStockLocator, null);
    jPanel2.add(jPanel7, BorderLayout.CENTER);
    jPanel7.add(jPanel3, null);
    jPanel3.add(jPanel19, null);
    jPanel19.add(lblItemID, null);
    jPanel19.add(lblItemVal, null);
    jPanel3.add(jPanel18, null);
    jPanel18.add(lblItemDesc, null);
    jPanel18.add(lblItemDescVal, null);
    jPanel7.add(jPanel4, null);
    jPanel4.add(jPanel21, null);
    jPanel21.add(lblSupplierID, null);
    jPanel21.add(lblSupplierIDVal, null);
    jPanel4.add(jPanel20, null);
    jPanel20.add(lblSUpplierName, null);
    jPanel20.add(lblSupplierNameVal, null);
    jPanel2.add(jPanel14,  BorderLayout.WEST);
    jPanel2.add(jPanel17,  BorderLayout.EAST);
    jPanel2.add(jPanel22,  BorderLayout.SOUTH);
    jPanel22.add(jPanel24, BorderLayout.WEST);
    jPanel22.add(jPanel23,  BorderLayout.EAST);
    jPanel22.add(jPanel25,  BorderLayout.CENTER);
    jPanel1.add(jPanel6, null);
    jPanel1.add(jPanel5, null);
    jPanel6.add(pnlCenterLeft, BorderLayout.CENTER);
    pnlCenterLeft.add(lblLastReceipDate, null);
    pnlCenterLeft.add(lblLastReceiptQty, null);
    pnlCenterLeft.add(lblSOH, null);
    pnlCenterLeft.add(lblReceiptDateVal, null);
    pnlCenterLeft.add(lblReceiptQtyVal, null);
    pnlCenterLeft.add(lblTotalVal, null);
    jPanel6.add(jPanel8, BorderLayout.SOUTH);
    jPanel6.add(jPanel9, BorderLayout.WEST);
    jPanel6.add(jPanel10, BorderLayout.EAST);
    jPanel6.add(jPanel11, BorderLayout.NORTH);
    jPanel5.add(jPanel16, BorderLayout.NORTH);
    jPanel5.add(jPanel15, BorderLayout.WEST);
    jPanel5.add(pnlCenterRight, BorderLayout.CENTER);
    pnlCenterRight.add(jScrollPane1,  BorderLayout.CENTER);
    jPanel5.add(jPanel13, BorderLayout.EAST);
    jPanel5.add(jPanel12, BorderLayout.SOUTH);
    jScrollPane1.getViewport().add(treProdHier, null);

  }
 //set ID of selected item
 void setItemId(String itemId){
     this.itemId=itemId;
 }
 //set id of prod hier node
 void setProdIDNode(String prodIDNode){
     this.prodIDNode=prodIDNode;
 }


//show detail data for selected item
 void showData(){
//     DataAccessLayer DAL =frmMain.getDAL();

   ResultSet rs=null;
   Statement stmt = null;
   String sqlQuery="";

   //built tree prod hier
   Vector vPrdHierAll = new Vector();
   vPrdHierAll = treProdHier.initHierAll(frmMain.getDAL(), "BTM_IM_PROD_HIR");
   Vector vPrdHierActive = new Vector();
   vPrdHierActive = treProdHier.initHierActive(vPrdHierAll);
   treProdHier.showHierTree(vPrdHierActive, rootTree);
   treProdHier.updateUI();

   try {
     //show item desc
     sqlQuery="select i.ITEM_ID ,i.ITEM_NAME,i.ITEM_DESC,s.SUPP_ID,s.SUPP_NAME,h.CHILD_ID,h.CHILD_NAME from BTM_IM_ITEM_MASTER i ,BTM_IM_ITEM_LOC_SUPPLIER isu ,BTM_IM_SUPPLIER s,BTM_IM_PROD_HIR h "+
         "where i.ITEM_ID=isu.ITEM_ID and isu.SUPP_ID=s.SUPP_ID and i.CHILD_ID=h.CHILD_ID and i.ITEM_ID='" +itemId+"'";
//     System.out.println(sqlQuery);
     stmt = DAL.getConnection().createStatement();
     rs=DAL.executeQueries(sqlQuery,stmt);
     if(rs.next()){
       lblItemVal.setText(rs.getString("ITEM_ID"));
       lblItemDescVal.setText(rs.getString("ITEM_DESC"));
       lblSupplierIDVal.setText(rs.getString("SUPP_ID"));
       lblSupplierNameVal.setText(rs.getString("SUPP_NAME"));
     }
//     rs.close();
//    stmt.close();
     //show item SOH
     sqlQuery="select sum(BACK_ROOM_QTY ) as SOH from BTM_IM_ITEM_LOC_SOH where ITEM_ID='" +itemId+"'";
//     System.out.println(sqlQuery);
     rs=DAL.executeQueries(sqlQuery,stmt);
     rs.next();
     lblTotalVal.setText(String.valueOf(rs.getFloat("SOH")));
     //show LAST_RECEIVED_DATE,LAST_RECEIVED_QTY
     sqlQuery="select LAST_RECEIVED_DATE,LAST_RECEIVED_QTY from BTM_IM_ITEM_LOC_SOH where ITEM_ID='" +itemId+"' order by LAST_RECEIVED_DATE DESC";
//     System.out.println(sqlQuery);
     rs=DAL.executeQueries(sqlQuery);
     if(rs.next()){
       if (rs.getDate("LAST_RECEIVED_DATE") != null) {
         lblReceiptDateVal.setText(rs.getDate("LAST_RECEIVED_DATE").
                                   toString());
         lblReceiptQtyVal.setText(String.valueOf(rs.getFloat(
              "LAST_RECEIVED_QTY")));
       } else {
         lblReceiptDateVal.setText("");
         lblReceiptQtyVal.setText("");
       }
     } else {
       lblReceiptDateVal.setText("");
       lblReceiptQtyVal.setText("");
     }


     rs.close();
     stmt.close();
   }
   catch (Exception ex) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(ex);
   }
   //
   treProdHier.setSelectionPathByNameNode(rootTree,prodIDNode);

 }

 void btnStockLocator_actionPerformed(ActionEvent e) {
   frmMain.pnlItemStockLocator.setItemId(itemId);
   frmMain.pnlItemStockLocator.showData();

   frmMain.showScreen(Constant.SCRS_ITEM_STOCK_LOCATOR);

 }

  void btnDone_actionPerformed(ActionEvent e) {
     frmMain.showScreen(Constant.SCRS_ITEM_LOOKUP);

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

               // ENTER
                key = new Integer(KeyEvent.VK_ENTER);
                inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), key);
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

                  if (identifier.intValue() == KeyEvent.VK_F1 || identifier.intValue() == KeyEvent.VK_ENTER) {
                    btnDone.doClick();
                  }
                  else if (identifier.intValue() == KeyEvent.VK_F2 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                    btnStockLocator.doClick();
                  }
                }
        }

  private void catchHotKey(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
      btnStockLocator.doClick();
    }
    else if (e.getKeyCode() == KeyEvent.VK_F1 ||
             e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      btnDone.doClick();
    }

  }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void treProdHier_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void pnlCenterLeft_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
}

class PanelItemLookupDetail_btnStockLocator_actionAdapter implements java.awt.event.ActionListener {
  PanelItemLookupDetail adaptee;

  PanelItemLookupDetail_btnStockLocator_actionAdapter(PanelItemLookupDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnStockLocator_actionPerformed(e);
  }
}

class PanelItemLookupDetail_btnDone_actionAdapter implements java.awt.event.ActionListener {
  PanelItemLookupDetail adaptee;

  PanelItemLookupDetail_btnDone_actionAdapter(PanelItemLookupDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnDone_actionPerformed(e);
  }
}

class PanelItemLookupDetail_this_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemLookupDetail adaptee;

  PanelItemLookupDetail_this_keyAdapter(PanelItemLookupDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class PanelItemLookupDetail_treProdHier_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemLookupDetail adaptee;

  PanelItemLookupDetail_treProdHier_keyAdapter(PanelItemLookupDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.treProdHier_keyPressed(e);
  }
}

class PanelItemLookupDetail_pnlCenterLeft_keyAdapter extends java.awt.event.KeyAdapter {
  PanelItemLookupDetail adaptee;

  PanelItemLookupDetail_pnlCenterLeft_keyAdapter(PanelItemLookupDetail adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.pnlCenterLeft_keyPressed(e);
  }
}
