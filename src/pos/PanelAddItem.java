//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//






















































                                                                                                                                                                                                                         package pos;

import btm.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import common.*;
import java.sql.*;
import oracle.jdbc.*;
import javax.swing.table.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import btm.attr.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author
 * @version 1.0
 */

public class PanelAddItem extends JPanel {
  FrameMain frmMain;
  int parentScreen=0;
  JTextField txtItemId = new JTextField();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField txtItemDesc = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField txtItemPrice = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField txtcomment = new JTextField();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  BJButton btnOK = new BJButton();
  BJButton btnBack = new BJButton();
  Utils ut = new Utils();
  DataAccessLayer DAL;
  Vector vItemID=new Vector();
  JTextField txtSize = new JTextField();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JTextField txtArtNo = new JTextField();//all id of all item
  public PanelAddItem(FrameMain frmMain) {
    try {
      this.frmMain=frmMain;
      this.DAL= frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
//        System.out.println("N3");
    registerKeyboardActions();
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    txtcomment.setBounds(new Rectangle(83, 312, 230, 21));
    jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel4.setText("Enter Comment:");
    jLabel4.setBounds(new Rectangle(85, 287, 122, 23));
    txtItemPrice.setBounds(new Rectangle(83, 261, 230, 21));
    txtItemPrice.addKeyListener(new PanelAddItem_txtItemPrice_keyAdapter(this));
    txtItemPrice.addActionListener(new PanelAddItem_txtItemPrice_actionAdapter(this));
    jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel3.setText("Enter Item Price:");
    jLabel3.setBounds(new Rectangle(85, 234, 122, 23));
    txtItemDesc.setBounds(new Rectangle(83, 209, 230, 21));
    txtItemDesc.addActionListener(new PanelAddItem_txtItemDesc_actionAdapter(this));
    jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel2.setText("Enter Item Description:");
    jLabel2.setBounds(new Rectangle(85, 182, 145, 23));
    this.setLayout(null);
    txtItemId.setBounds(new Rectangle(83, 55, 230, 21));
    txtItemId.addKeyListener(new PanelAddItem_txtItemId_keyAdapter(this));
    this.setBackground(SystemColor.control);
    this.setBorder(BorderFactory.createEtchedBorder());
    jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel1.setText("Item ID test:");
    jLabel1.setBounds(new Rectangle(85, 26, 122, 23));
    jLabel5.setBounds(new Rectangle(316, 54, 19, 23));
    jLabel5.setText("(*)");
    jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel5.setForeground(Color.red);
    jLabel6.setForeground(Color.red);
    jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel6.setText("(*)");
    jLabel6.setBounds(new Rectangle(316, 208, 19, 23));
    jLabel7.setForeground(Color.red);
    jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel7.setText("(*)");
    jLabel7.setBounds(new Rectangle(316, 260, 19, 23));
    jLabel8.setForeground(Color.red);
    jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel8.setText("(*)");
    jLabel8.setBounds(new Rectangle(26, 339, 19, 23));
    jLabel9.setBounds(new Rectangle(48, 340, 249, 23));
    jLabel9.setText("Field is required");
    jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12));
    btnOK.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnOK.setText("<html><center><b>OK</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nb" +
    "sp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F1</html>");
    btnOK.addActionListener(new PanelAddItem_btnOK_actionAdapter(this));
    btnBack.setFont(new java.awt.Font("SansSerif", 0, 12));
    btnBack.setText("<html><center><br><b>Cancel</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&" +
                       "nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;F12</ht" +
                       "ml>");
    btnBack.addActionListener(new PanelAddItem_btnBack_actionAdapter(this));
    //txtSize.addActionListener(new PanelAddItem_txtSize_actionAdapter(this));
    txtSize.setBounds(new Rectangle(82, 156, 230, 21));
    txtSize.addKeyListener(new PanelAddItem_txtSize_keyAdapter(this));
    jLabel11.setBounds(new Rectangle(83, 130, 145, 23));
    jLabel11.setText("Size:");
    jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel11.setToolTipText("");
    jLabel12.setToolTipText("");
    jLabel12.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel12.setText("Art No:");
    jLabel12.setBounds(new Rectangle(82, 79, 145, 23));
    jLabel13.setForeground(Color.red);
    jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabel13.setText("(*)");
    jLabel13.setBounds(new Rectangle(313, 104, 19, 23));
    txtArtNo.setBounds(new Rectangle(81, 105, 230, 21));
    txtArtNo.addKeyListener(new PanelAddItem_txtArtNo_keyAdapter(this));
    //txtArtNo.addActionListener(new PanelAddItem_txtArtNo_actionAdapter(this));
    this.add(jLabel9, null);
    this.add(jLabel8, null);
    this.add(jLabel4, null);
    this.add(txtcomment, null);
    this.add(jLabel3, null);
    this.add(jLabel7, null);
    this.add(txtItemPrice, null);
    this.add(jLabel2, null);
    this.add(jLabel6, null);
    this.add(txtItemDesc, null);
    this.add(txtSize, null);
    this.add(jLabel11, null);
    this.add(jLabel12, null);
    this.add(txtArtNo, null);
    this.add(jLabel13, null);
    this.add(jLabel1, null);
    this.add(jLabel5, null);
    this.add(txtItemId, null);
  }

  void txtItemdesc_actionPerformed(ActionEvent e) {

  }
  void resetInput(){
    txtItemId.setText("");
    txtItemDesc.setText("");
    txtItemPrice.setText("");
    txtcomment.setText("");
    txtItemId.requestFocus();
    txtArtNo.setText("");
    txtSize.setText("");
  }


  void txtItemPrice_actionPerformed(ActionEvent e) {

  }

  //show button in Frame Main
  void showAddItemButton(){
    frmMain.showButton(btnOK);
    frmMain.showButton(btnBack);
  }
  void setParentScreen(int parentScreen){
    this.parentScreen=parentScreen;
  }
  //Process mainInput in FrameMain
  void txtMainInput_keyPressed(KeyEvent e) {

  }

  void btnOK_actionPerformed(ActionEvent e) {
    String s="";
//    DataAccessLayer DAL= frmMain.getDAL();
    String sSQL=new String();
    ResultSet rs=null;
    String itemID=txtItemId.getText().trim();
    String desc=txtItemDesc.getText().trim();
    String unitPrice=txtItemPrice.getText();
    String artNo = txtArtNo.getText().trim();
    String Size=txtSize.getText().trim();
// check for input data
    if (txtItemId.getText().equals("")||txtArtNo.getText().equals("")||txtItemPrice.getText().equals("")) {
      ut.showMessage(frmMain, "Warning", "You must enter all textbox (*)");
      txtItemId.requestFocus();
      return;
    }

   if (txtItemId.getText().length()>24 ){
     ut.showMessage(frmMain,"Warning", "Item code must be integer");
     txtItemId.requestFocus();
     return;
   }

   if (txtArtNo.getText().length() > 6) {
     ut.showMessage(frmMain, "Warning", "Art no must be integer");
     txtArtNo.requestFocus();
     return;
   }

   if (txtSize.getText().length() > 120) {
     ut.showMessage(frmMain, "Warning", "Size is very long");
     txtSize.requestFocus();
     return;
   }

   if (unitPrice.length()>9){
     ut.showMessage(frmMain, "Warning", "Price is very large");
     txtItemPrice.requestFocus();
     return;
   }

   if ( !ut.isFloatString(unitPrice)){
     ut.showMessage(frmMain,"Warning", "Price must be number");
     txtItemPrice.requestFocus();
     return;
   }
   Statement stmt = null;
    sSQL = "Select * from BTM_POS_ITEM_PRICE where ITEM_ID='" +
        itemID + "'";
//    System.out.println(sSQL);
//    rs = DAL.executeQueries(sSQL);
    try {
      stmt = DAL.getConnection().createStatement();
      rs = DAL.executeQueries(sSQL,stmt);
        if ( !rs.next()) {
           //insert item to BTM_POS_ITEM_PRICE, write to file

            Transaction trans = frmMain.getTransaction();
            s = "Insert into BTM_POS_ITEM_PRICE(item_id, store_id, item_desc, unit_price, flag,ITEM_PRICE_DATE,art_no,ATTR2_NAME) " +
               " values ('" + itemID + "'," +
                trans.getStore_ID() + ",'" + desc + "'," + unitPrice +
                ",1,to_date('" + ut.getSystemDate() +"','" + ut.DATE_FORMAT + "'),'"+artNo+"','"+Size+"')";
            DAL.executeUpdate(s,stmt);
//            storeExcToFile(itemID + ";" + trans.getStore_ID() + ";" +
//                           desc + ";" + unitPrice + "\n");
            resetInput();
        }
        else {
          ut.showMessage(frmMain,"Warning", "This Item ID is already exist");
        }
        rs.close();
    }catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }
    try{
      stmt.close();
    }catch(Exception ex){
      ex.printStackTrace();
    }

  }


//write new item to file
  private void storeExcToFile(String date){

     try{
      //write date, class name
       OutputStream fwrite = new FileOutputStream( "file\\test.txt", true);
       fwrite.write( date.getBytes());
       fwrite.write("\r\n".getBytes());
       fwrite.close();
       }catch (Exception e ){
        e.printStackTrace();
        }
       }

  void btnBack_actionPerformed(ActionEvent e) {
    //reset data
    txtcomment.setText("");
    txtItemDesc.setText("");
    txtItemId.setText("");
    txtItemPrice.setText("");
    txtArtNo.setText("");
    txtSize.setText("");

    if (parentScreen == Constant.SCR_NEW_SALE){
      frmMain.setTitle("JPOS - NEW SALES");
    }else if (parentScreen == Constant.SCR_RECEIPT_ITEM){
      frmMain.setTitle("JPOS - RECEIPT ITEM");
    }
    frmMain.showScreen(parentScreen);
  }

  void txtItemId_keyReleased(KeyEvent e) {
    /*char c = e.getKeyChar();
    if ( c == e.VK_ENTER || c == e.VK_BACK_SPACE ) return;
    if( c < '0' || c > '9') {
      txtItemId.setText(txtItemId.getText().substring(0,txtItemId.getText().length()-1));
      ut.showMessage(frmMain,"Warning", "The Item ID must be numeric");
      return;
    }
   if(txtItemId.getText().length()>24)
   {
       txtItemId.setText(txtItemId.getText().substring(0,txtItemId.getText().length()-1));
       ut.showMessage(frmMain,"Warning", "The Item ID must be lower than 25 characters");
       return;
   }*/
  }

  void txtItemPrice_keyReleased(KeyEvent e) {
    if(txtItemPrice.getText().equals("") || e.getKeyChar()== e.VK_ENTER)
      return;
    Utils ut =new Utils();
    if(!ut.isDoubleString(txtItemPrice.getText()))
    {
      txtItemPrice.setSelectionStart(0);
      txtItemPrice.setSelectionEnd(txtItemPrice.getText().length());
      ut.showMessage(frmMain,"Warning", "The Item Price must be interger");
      return;
    }
    double i = Double.parseDouble(txtItemPrice.getText());
    if(i <= 0 )
    {
      txtItemPrice.setText("");
      ut.showMessage(frmMain,"Warning", "The Item Price must be greater than 0");

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

 // F2
 key = new Integer(KeyEvent.VK_F2);
 inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), key);
 actionMap.put(key, new KeyAction(key));
 // F2
 key = new Integer(KeyEvent.VK_F12);
 inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
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
     if (identifier.intValue()==KeyEvent.VK_F1){
         btnOK.doClick();
     }else if (identifier.intValue()==KeyEvent.VK_F12 || identifier.intValue()==KeyEvent.VK_ESCAPE){
         btnBack.doClick();
     }
 }
}

  void txtArtNo_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtArtNo,6);
    ut.checkNumber(e);
  }

  void txtSize_keyTyped(KeyEvent e) {
//    ut.limitLenjTextField(e,txtSize,120);
//    ut.checkNumber(e);
  }

  void txtItemId_keyTyped(KeyEvent e) {
    ut.limitLenjTextField(e,txtArtNo,24);
    ut.checkNumber(e);
  }

}

class PanelAddItem_txtItemDesc_actionAdapter implements java.awt.event.ActionListener {
  PanelAddItem adaptee;

  PanelAddItem_txtItemDesc_actionAdapter(PanelAddItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtItemdesc_actionPerformed(e);
  }
}

class PanelAddItem_txtItemPrice_actionAdapter implements java.awt.event.ActionListener {
  PanelAddItem adaptee;

  PanelAddItem_txtItemPrice_actionAdapter(PanelAddItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtItemPrice_actionPerformed(e);
  }
}

class PanelAddItem_btnOK_actionAdapter implements java.awt.event.ActionListener {
  PanelAddItem adaptee;

  PanelAddItem_btnOK_actionAdapter(PanelAddItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class PanelAddItem_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelAddItem adaptee;

  PanelAddItem_btnBack_actionAdapter(PanelAddItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelAddItem_txtItemId_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAddItem adaptee;

  PanelAddItem_txtItemId_keyAdapter(PanelAddItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtItemId_keyReleased(e);
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtItemId_keyTyped(e);
  }
}

class PanelAddItem_txtItemPrice_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAddItem adaptee;

  PanelAddItem_txtItemPrice_keyAdapter(PanelAddItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.txtItemPrice_keyReleased(e);
  }
}

class PanelAddItem_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAddItem adaptee;

  PanelAddItem_txtArtNo_keyAdapter(PanelAddItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtArtNo_keyTyped(e);
  }
}

class PanelAddItem_txtSize_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAddItem adaptee;

  PanelAddItem_txtSize_keyAdapter(PanelAddItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.txtSize_keyTyped(e);
  }
}
