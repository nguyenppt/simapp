package pos;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import btm.swing.*;
import btm.attr.*;
import java.awt.event.*;
import common.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: aaa</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @version 1.0
 */

public class DialogSearchResultItem extends JDialog {
  Utils ut = new Utils();
  DataAccessLayer DAL = new DataAccessLayer();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  Object[][] rowdata = new Object[0][0];
  String[] columnNames = new String[]{lang.getString("Code"),lang.getString("Description"),lang.getString("RetailPrice")};
  SortableTableModel model = new SortableTableModel(){
    public boolean isCellEditable(int rowIndex, int mColIndex) {
      return false;
    }
  };

  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel pnlCenter = new JPanel();
  BJPanel pnlSouth = new BJPanel();
  BJButton btnOK = new BJButton();
  BJButton btnCancel = new BJButton();
  JScrollPane jScrollPane1 = new JScrollPane();
  BJTable jTable1 = new BJTable(model,true);
  BorderLayout borderLayout2 = new BorderLayout();
  boolean isOK=false;

  public DialogSearchResultItem(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogSearchResultItem() {
    this(null, "", true);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    panel1.setLayout(borderLayout1);
//    btnOK.setPreferredSize(new Dimension(133, 57));
    btnOK.setText(lang.getString("OK"));
    btnOK.addActionListener(new DialogSearchResultItem_btnOK_actionAdapter(this));
//    btnCancel.setPreferredSize(new Dimension(133, 57));
    btnCancel.setSelectedIcon(null);
    btnCancel.setText(lang.getString("Cancel"));
    btnCancel.addActionListener(new DialogSearchResultItem_btnCancel_actionAdapter(this));
    pnlSouth.setBackground(new Color(121, 152, 198));
    pnlSouth.setPreferredSize(new Dimension(281, 47));
    pnlCenter.setLayout(borderLayout2);
    jTable1.addMouseListener(new DialogSearchResultItem_jTable1_mouseAdapter(this));
    jTable1.setRowHeight(30);
    jTable1.addKeyListener(new DialogSearchResultItem_jTable1_keyAdapter(this));
    this.addKeyListener(new DialogSearchResultItem_this_keyAdapter(this));
    getContentPane().add(panel1);
    panel1.add(pnlCenter, BorderLayout.CENTER);
    pnlCenter.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTable1, null);
    panel1.add(pnlSouth,  BorderLayout.SOUTH);
    pnlSouth.add(btnOK, null);
    pnlSouth.add(btnCancel, null);
    this.setTitle(lang.getString("TT005"));
    this.setSize(500,300);
    //set center screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
    (screenSize.height - frameSize.height) / 2);
    //set table model
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable1.getTableHeader().setFont(new java.awt.Font(lang.getString("FontName_Label"),1,12));
    model.setDataVector(rowdata, columnNames);

  }  //add item row
  void addItemRow(Object[] item){
    model.addRow(item);
    if(jTable1.getRowCount()==1){
      jTable1.setRowSelectionInterval(0, 0);
    }

  }

  //return selected item
  public Item getItem(){

    Item item=new Item("",jTable1.getValueAt(jTable1.getSelectedRow(),1).toString(),Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString()),1,0,"",0,Double.parseDouble(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString()),0,jTable1.getValueAt(jTable1.getSelectedRow(),0).toString(),"","");
    return item;
  }

  void btnOK_actionPerformed(ActionEvent e) {
    isOK=true;
    this.dispose();
  }
  public boolean isOKButton(){
    return isOK;
  }
  //return item number
  public boolean isExistItem(){
    if(jTable1.getRowCount()>0){
      return true;
    }else{
      return false;
    }

  }

  void btnCancel_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void jTable1_mouseClicked(MouseEvent e) {
    if(e.getClickCount()==1){
      isOK=true;
      this.dispose();
    }
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
             if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               if (jTable1.getSelectedRow() == 0){
                 jTable1.setRowSelectionInterval(jTable1.getRowCount()-1,jTable1.getRowCount()-1);
               }else{
                 int iSelectedRow = jTable1.getSelectedRow()-1;
                 jTable1.setRowSelectionInterval(iSelectedRow,iSelectedRow);
               }
               btnOK.doClick();
             }
              if (e.getKeyCode() == KeyEvent.VK_F1) {
                btnOK.doClick();
              }
              else if (e.getKeyCode() == KeyEvent.VK_F2 || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                btnCancel.doClick();
              }

            }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void jTable1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }
}

class DialogSearchResultItem_btnOK_actionAdapter implements java.awt.event.ActionListener {
  DialogSearchResultItem adaptee;

  DialogSearchResultItem_btnOK_actionAdapter(DialogSearchResultItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class DialogSearchResultItem_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogSearchResultItem adaptee;

  DialogSearchResultItem_btnCancel_actionAdapter(DialogSearchResultItem adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogSearchResultItem_jTable1_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogSearchResultItem adaptee;

  DialogSearchResultItem_jTable1_mouseAdapter(DialogSearchResultItem adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTable1_mouseClicked(e);
  }
}

class DialogSearchResultItem_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSearchResultItem adaptee;

  DialogSearchResultItem_this_keyAdapter(DialogSearchResultItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogSearchResultItem_jTable1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogSearchResultItem adaptee;

  DialogSearchResultItem_jTable1_keyAdapter(DialogSearchResultItem adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTable1_keyPressed(e);
  }
}
