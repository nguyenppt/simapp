//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//













































package pos;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import btm.swing.*;
import btm.attr.*;
import java.awt.event.*;
import common.*;
import java.sql.*;
import btm.attr.*;
import javax.swing.BorderFactory;

/**
 * <p>Title: </p>
 * <p>Description: Search item by Art No and Size </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @version 1.0
 */

public class DialogSearchExpressItem extends JDialog {
  FrameMain frmMain;
  DataAccessLayer DAL;
  Item item;
  boolean found=false;

  BJPanel panel1 = new BJPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel pnlCenter = new BJPanel();
  BJPanel pnlSouth = new BJPanel();
  BJButton btnOK = new BJButton();
  BJButton btnCancel = new BJButton();
  boolean isOK=false;
JLabel jLabel1 = new JLabel();
JLabel jLabel2 = new JLabel();
JTextField txtArtNo = new JTextField();
JTextField txtSize = new JTextField();

  public DialogSearchExpressItem(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      this.frmMain=(FrameMain)frame;
      this.DAL = frmMain.getDAL();
      jbInit();
//      pack();

    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogSearchExpressItem( ) {
    this(null, "", true);
  }
  private void jbInit() throws Exception {
//        System.out.println("N2");
    registerKeyboardActions();
    panel1.setLayout(borderLayout1);
//    btnOK.setPreferredSize(new Dimension(133, 57));
    btnOK.setText("OK");
    btnOK.addActionListener(new DialogSearchExpressItem_btnOK_actionAdapter(this));
//    btnCancel.setPreferredSize(new Dimension(133, 57));
    btnCancel.setSelectedIcon(null);
    btnCancel.setText("Cancel");
    btnCancel.addActionListener(new DialogSearchExpressItem_btnCancel_actionAdapter(this));
    pnlSouth.setBackground(new Color(121, 152, 198));
    pnlSouth.setPreferredSize(new Dimension(281, 47));
    pnlCenter.setLayout(null);
    this.addKeyListener(new DialogSearchExpressItem_this_keyAdapter(this));
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 11));
  jLabel1.setText("Art No:");
  jLabel1.setBounds(new Rectangle(53, 42, 37, 15));
  jLabel2.setFont(new java.awt.Font("Dialog", 1, 11));
  jLabel2.setText("Size:");
  jLabel2.setBounds(new Rectangle(52, 108, 26, 15));
  txtArtNo.setText("");
  txtArtNo.setBounds(new Rectangle(152, 37, 164, 30));
  txtArtNo.addKeyListener(new DialogSearchExpressItem_txtArtNo_keyAdapter(this));
  txtSize.addKeyListener(new DialogSearchExpressItem_txtSize_keyAdapter(this));
  txtSize.setBorder(BorderFactory.createLoweredBevelBorder());
  txtSize.setBounds(new Rectangle(151, 98, 165, 30));
  getContentPane().add(panel1);
    panel1.add(pnlCenter, BorderLayout.CENTER);
  pnlCenter.add(txtArtNo, null);
  pnlCenter.add(txtSize, null);
  pnlCenter.add(jLabel1, null);
  pnlCenter.add(jLabel2, null);
    panel1.add(pnlSouth,  BorderLayout.SOUTH);
    pnlSouth.add(btnOK, null);
    pnlSouth.add(btnCancel, null);
    this.setTitle("Item");
    this.setSize(400,250);
    //set center screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
    (screenSize.height - frameSize.height) / 2);

  }

  //return result item
  public Item getItem(){
    return item;
  }

  void btnOK_actionPerformed(ActionEvent e) {
    Utils ut=new Utils();
    String sqlQuery="";
//      Item item=new Item();
      Statement stmt = null;
      ResultSet rs=null;

      String artNo=txtArtNo.getText();
      String size= txtSize.getText();

      sqlQuery="Select * from BTM_POS_ITEM_PRICE where ART_NO='" + artNo + "' and UPPER(ATTR2_NAME) LIKE UPPER('" +size+"')";

      if(size.equals("")){
        sqlQuery = "Select * from BTM_POS_ITEM_PRICE where ART_NO='" + artNo +"' and (Attr2_name is null or Attr2_name like 'null')";

      }else{
        sqlQuery = "Select * from BTM_POS_ITEM_PRICE where ART_NO='" + artNo + "' and UPPER(ATTR2_NAME) LIKE UPPER('" +size+"')";
      }



//    System.out.println(sqlQuery);
    try {
      if(!artNo.equals("") ){
        stmt = DAL.getConnection().createStatement();
        rs = DAL.executeQueries(sqlQuery, stmt);

        if (rs.next()) {
          found = true;
          if (DAL.getFranchiseCust().length() == 13) {
            item = new Item(rs.getString("ITEM_ID"), rs.getString("ITEM_DESC"),
                            rs.getDouble("UNIT_COST")*(1+rs.getDouble("VAT")/100), 1, 0, "", 0,
                            rs.getDouble("UNIT_COST")*(1+rs.getDouble("VAT")/100), 0, artNo, "", size);

          }else{
            item = new Item(rs.getString("ITEM_ID"), rs.getString("ITEM_DESC"),
                          rs.getDouble("UNIT_PRICE"), 1, 0, "", 0,
                          rs.getDouble("UNIT_PRICE"), 0, artNo, "", size);
          }
          rs.close();
          stmt.close();
          isOK = true;
          this.dispose();

        }
        else {
          ut.showMessage(frmMain, "Warning",
                         "No items found matching your Art No and Size");

          rs.close();
          stmt.close();
        }
      }else{
        ut.showMessage(frmMain, "Warning",
                       "Art No can not null");

      }

    }catch(Exception ex){
      ex.printStackTrace();
    }

  }
  public boolean isOKButton(){
    return isOK;
  }
  //return item number
  public boolean isExistItem(){
      return found;
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  private void registerKeyboardActions() {
             /// set up the maps:

             InputMap inputMap = new InputMap();
             inputMap.setParent(pnlSouth.getInputMap(JComponent.
                                                    WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
             ActionMap actionMap = pnlSouth.getActionMap();


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
              pnlSouth.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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

               if (identifier.intValue() == KeyEvent.VK_F1 ) {
                 btnOK.doClick();
               }
               else if (identifier.intValue() == KeyEvent.VK_F2 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                 btnCancel.doClick();
               }
             }
               }

           private void catchHotKey(KeyEvent e) {
             if (e.getKeyCode() == KeyEvent.VK_F1 || e.getKeyCode() == KeyEvent.VK_ENTER) {
               btnOK.doClick();
             }
             else if (e.getKeyCode() == KeyEvent.VK_F2 ||
                      e.getKeyCode() == KeyEvent.VK_ESCAPE) {
               btnCancel.doClick();
             }

           }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
//Enter=OK if fill up all info
void txtSize_keyPressed(KeyEvent e) {
  if (e.getKeyCode() == KeyEvent.VK_F1 || e.getKeyCode() == KeyEvent.VK_ENTER) {
    if(txtArtNo.getText().trim().equals("")){
      txtArtNo.requestFocus();
    }else{
      btnOK.doClick();
    }
  }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
      btnCancel.doClick();
  }


}

void txtArtNo_keyPressed(KeyEvent e) {
  if (e.getKeyCode() == KeyEvent.VK_F1 || e.getKeyCode() == KeyEvent.VK_ENTER) {
    if(txtSize.getText().trim().equals("")){
      txtSize.requestFocus();
    }else{
      btnOK.doClick();
    }
  }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
      btnCancel.doClick();
  }

}

}

class DialogSearchExpressItem_btnOK_actionAdapter implements java.awt.event.ActionListener {
  DialogSearchExpressItem adaptee;

  DialogSearchExpressItem_btnOK_actionAdapter(DialogSearchExpressItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class DialogSearchExpressItem_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogSearchExpressItem adaptee;

  DialogSearchExpressItem_btnCancel_actionAdapter(DialogSearchExpressItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}


class DialogSearchExpressItem_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSearchExpressItem adaptee;

  DialogSearchExpressItem_this_keyAdapter(DialogSearchExpressItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogSearchExpressItem_txtSize_keyAdapter extends java.awt.event.KeyAdapter {
DialogSearchExpressItem adaptee;

DialogSearchExpressItem_txtSize_keyAdapter(DialogSearchExpressItem adaptee) {
  this.adaptee = adaptee;
}
public void keyPressed(KeyEvent e) {
  adaptee.txtSize_keyPressed(e);
}
}

class DialogSearchExpressItem_txtArtNo_keyAdapter extends java.awt.event.KeyAdapter {
DialogSearchExpressItem adaptee;

DialogSearchExpressItem_txtArtNo_keyAdapter(DialogSearchExpressItem adaptee) {
  this.adaptee = adaptee;
}
public void keyPressed(KeyEvent e) {
  adaptee.txtArtNo_keyPressed(e);
}
}

