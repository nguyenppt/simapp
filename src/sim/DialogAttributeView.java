package sim;

import java.util.*;
import java.util.ResourceBundle;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import btm.business.*;
import btm.swing.*;
import common.*;
import common.Utils.*;
import java.awt.event.*;
import btm.attr.*;
import java.sql.Statement;


/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Attribute Setup -> View</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */

public class DialogAttributeView extends JDialog {
  DataAccessLayer DAL;
   FrameMainSim frmMain;
   ProductAttributeBusObj productAttBusObj = new ProductAttributeBusObj();
   Utils ut = new Utils();
   BorderLayout borderLayout1 = new BorderLayout();
   boolean status = false;
   Statement stmt = null;
   Statement stmt1 = null;
   Statement stmt2 = null;
   ResourceBundle lang=DataAccessLayer .getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international
   //Panels
   BJPanel pnlHeader = new BJPanel(Constant.PANEL_TYPE_HEADER);
   BJPanel pnlCenter = new BJPanel();
   BJPanel pnlBottom = new BJPanel();
   BJPanel pnlLef = new BJPanel();
   BJPanel pnlRight = new BJPanel();
   JScrollPane jScrollPane1 = new JScrollPane();
   //buttons
   BJButton btnModify = new BJButton();
   BJButton btnBack = new BJButton();

   //Label
   JLabel lblAttribute = new JLabel(); //("Attribute");
   JLabel lblProGroup = new JLabel();//("Product Group:");
   JLabel lblColorSize = new JLabel();//("Color:");

   //combobox
   BJComboBox cboAttr = new BJComboBox();

   //textfield
   JTextField txtColorSize = new JTextField();
   JTextField txtProdGroup = new JTextField();

   //Table
   SortableTableModel dm = new SortableTableModel();
   BJTable table = new BJTable(dm, true) {
      public Class getColumnClass(int col) {
          return Object.class;
      }

      public boolean isCellEditable(int row, int col) {
          return false;
      }
  };
  StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray, Color.white, null, null);


  public DialogAttributeView(FrameMainSim frmMain) {
      try {
          this.frmMain = frmMain;
          DAL = frmMain.getDAL();
          jbInit();
      }
      catch (Exception ex) {
          ex.printStackTrace();
      }
  }

  public DialogAttributeView(Frame frame, String title, boolean modal,
                              FrameMainSim frmMain) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      DAL = frmMain.getDAL();
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogAttributeView(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogAttributeView() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    this.setBackground(UIManager.getColor("Label.background"));
    this.setSize(new Dimension(800, 600));
    this.addKeyListener(new DialogAttributeView_this_keyAdapter(this));
//     this.setPreferredSize(new Dimension(800, 600));
     this.getContentPane().setLayout(borderLayout1);
     jScrollPane1.setPreferredSize(new Dimension(790, 405));
     btnBack.addActionListener(new DialogAttributeView_btnBack_actionAdapter(this));
     table.addMouseListener(new DialogAttributeView_table_mouseAdapter(this));
     btnModify.addActionListener(new DialogAttributeView_btnModify_actionAdapter(this));
     table.addKeyListener(new DialogAttributeView_table_keyAdapter(this));
    cboAttr.addKeyListener(new DialogAttributeView_cboAttr_keyAdapter(this));
    txtProdGroup.addKeyListener(new DialogAttributeView_txtProdGroup_keyAdapter(this));
    txtColorSize.addKeyListener(new DialogAttributeView_txtColorSize_keyAdapter(this));
    this.getContentPane().add(pnlHeader, BorderLayout.NORTH);
     this.getContentPane().add(pnlCenter, BorderLayout.CENTER);
     this.getContentPane().add(pnlBottom, BorderLayout.SOUTH);
     this.getContentPane().add(pnlLef,BorderLayout.WEST);
     this.getContentPane().add(pnlRight,BorderLayout.EAST);

     pnlCenter.setPreferredSize(new Dimension(110, 26));
     pnlBottom.setPreferredSize(new Dimension(800, 410));
     pnlRight.setLayout(null);
     pnlRight.setPreferredSize(new Dimension(600, 100));
     pnlLef.setPreferredSize(new Dimension(50, 100));
     pnlHeader.setPreferredSize(new Dimension(850, 50));

     pnlHeader.add(btnModify, null);
     pnlHeader.add(btnBack, null);

     pnlCenter.add(lblAttribute,null);
     pnlCenter.add(lblProGroup, null);
     pnlCenter.add(lblColorSize,null);

     pnlRight.add(cboAttr, null);
     pnlRight.add(txtProdGroup, null);
     pnlRight.add(txtColorSize, null);

     pnlBottom.add(jScrollPane1, null);
     jScrollPane1.getViewport().add(table,null);
     lblAttribute.setText(lang.getString("Attribute")+":");
     lblProGroup.setText(lang.getString("ProGroup")+":");
     lblColorSize.setText(lang.getString("ColorSize")+":");

     //button done
     btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
     btnModify.setText(lang.getString("Modify")+"(F1)");
     btnModify.setToolTipText(lang.getString("Modify")+" (F1)");
     //btn Add
     btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
     btnBack.setText(lang.getString("Back")+"(F2)");
     btnBack.setToolTipText(lang.getString("Back")+" (F2)");

     //label
     lblAttribute.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
     lblAttribute.setPreferredSize(new Dimension(110, 23));

     lblColorSize.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
     lblColorSize.setPreferredSize(new Dimension(110, 23));

     lblProGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
     lblProGroup.setPreferredSize(new Dimension(110, 23));

     //TextField
     txtColorSize.setPreferredSize(new Dimension(220, 21));
     txtColorSize.setBounds(new Rectangle(1, 58, 220, 21));
     txtColorSize.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

     txtProdGroup.setPreferredSize(new Dimension(220, 21));
     txtProdGroup.setBounds(new Rectangle(1, 32, 220, 21));
     txtProdGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
     txtProdGroup.setEditable(false);

     //Combo box
     cboAttr.setPreferredSize(new Dimension(220, 21));
     cboAttr.setBounds(new Rectangle(1, 6, 220, 21));
     cboAttr.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

     //Table
     String[] columnNames = new String[] {lang.getString("AttributeDetailId"),lang.getString("Attribute"), lang.getString("ProductGroup"), lang.getString("ColorSize")};
     dm.setDataVector(new Object[][] {}, columnNames);
     cboAttr.addActionListener(new DialogAttributeView_cboAttr_actionAdapter(this));

//    panel1.setLayout(borderLayout1);
//    getContentPane().add(panel1);
  }
  private boolean checkModify(String attrID, String prod_group_id,
                                       String attrDtlDesc,String attr_Dtl_Id) {
    boolean result = false;
    ResultSet rs = null;
    String sqlStr = "Select Attr_Dtl_Id From BTM_IM_ATTR_DTL Where Attr_id='" +
        attrID
        + "' and lower(Attr_Dtl_Desc)=lower('" + attrDtlDesc +
        "') and Prod_Group_Id='" + prod_group_id + "' and Attr_Dtl_Id <>'" + attr_Dtl_Id + "'";
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.
                                                 TYPE_SCROLL_INSENSITIVE,
                                                 ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries(sqlStr, stmt);
      if (rs.next()) {
        result = true;
      }
      rs.close();
      stmt.close();
    }
    catch (Exception ex) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);}
    ;

    return result;
  }




  void showAttribute(){


    try {
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    cboAttr.setData(productAttBusObj.getAttribute(DAL,stmt));
    try {
      stmt.close();

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  void showDataTable(){
      ResultSet rsAttrDtl = null;
      String sizeColor="";
      String proGroup="";
      String attr="";
      String id="";
      String attrId = cboAttr.getSelectedData();
      String temp="";
      boolean b= true;
      try {
        stmt1 = DAL.getConnection().createStatement(ResultSet.
                                                TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

    stmt2 = DAL.getConnection().createStatement(ResultSet.
                                            TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);

          rsAttrDtl = productAttBusObj.getAttrDetail(DAL,attrId,stmt1);
          while (rsAttrDtl.next()) {
              id = rsAttrDtl.getString("ATTR_DTL_ID");
              sizeColor = rsAttrDtl.getString("ATTR_DTL_DESC");
              attr = cboAttr.getSelectedItem().toString();
              proGroup=productAttBusObj.getProdHirChildName(DAL,rsAttrDtl.getString("PROD_GROUP_ID"),stmt2);
              Object[] rowData = new Object[] {id,attr, proGroup, sizeColor};
              dm.addRow(rowData);
//              str.installInTable(table, Color.white, Color.black,
//                                 Color.lightGray, Color.white);
              float[] f1 = new float[3];
              Color.RGBtoHSB(255,255,230,f1);
              str.installInTable(table, Color.getHSBColor(f1[0],f1[1],f1[2]), Color.black, null, null);
          }
      }catch(Exception ex){
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(ex);
      }
      try {
        stmt1.close();
        stmt2.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }

  }
  void btnBack_actionPerformed(ActionEvent e) {
    DialogAttributeSetup dlgAttributeSetup = new   DialogAttributeSetup(
        frmMain,
        lang.getString("TT0079"), true, frmMain);
    dlgAttributeSetup.showdata();
    dlgAttributeSetup.txtColorSize.requestFocus(true);
    dlgAttributeSetup.setLocation(112, 85);
    dlgAttributeSetup.setVisible(true);
      this.dispose();
  }

 void table_mouseClicked(MouseEvent e) {
     if (e.getClickCount() == 2) {
         status = true;
         int row = table.getSelectedRow();
         txtColorSize.setText(table.getValueAt(row, 3).toString());
         if(cboAttr.getSelectedIndex()==0){
             lblColorSize.setText(lang.getString("Color")+":");
         }else {
             lblColorSize.setText(lang.getString("Size")+":");
         }
         txtProdGroup.setText(table.getValueAt(row, 2).toString());
         cboAttr.setEnabled(false);
     }
 }
 private String getProdGroupId(String prodGroupName){
   String id = "";
   ResultSet rs = null;

   String sqlStr = "Select distinct Parent_Id From BTM_IM_PROD_HIR Where lower(Parent_Name)=lower('" + prodGroupName + "')";
   try{
//     System.out.println(sqlStr);
     stmt = DAL.getConnection().createStatement(ResultSet.
                                        TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);
     rs = DAL.executeScrollQueries(sqlStr,stmt);
     if(rs.next()){
       id = rs.getString("Parent_id");
     }
     rs.close();
     stmt.close();
   }
   catch(Exception ex){};

   return id;
 }
 void btnModify_actionPerformed(ActionEvent e) {
     if (status) {
         String sizeColor = txtColorSize.getText().trim();
         String attr = cboAttr.getSelectedItem().toString();

         if (sizeColor.length() > 120) {
             ut.showMessage(frmMain, lang.getString("TT001"), "\"" + lblColorSize.getText() + "\""+ lang.getString("MS1005_IsTooLarge"));
             txtColorSize.requestFocus(true);
             return;
         }

         if (sizeColor.equals("")) {
             ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1003_ColorOrSizeIsNotNull"));
             txtColorSize.requestFocus(true);
             return;
         }
         String attr_Dtl_id = table.getValueAt(table.getSelectedRow(),0).toString();
         String prod_group_id = getProdGroupId(txtProdGroup.getText().trim());
         if (checkModify(cboAttr.getSelectedData(),prod_group_id,sizeColor,attr_Dtl_id)) {
           ut.showMessage(frmMain, lang.getString("TT001"),
                          lang.getString("MS1004_ThisAttributeIsAlreadyExisted"));
           txtColorSize.requestFocus(true);
           return;
         }

         try {
               stmt = DAL.getConnection().createStatement();
             }
             catch (Exception ex) {
               ex.printStackTrace();
             }

//         productAttBusObj.updateAttrDetail(DAL,sizeColor,attr_Dtl_id,stmt);

         int row = table.getSelectedRow();
         table.setValueAt(sizeColor, row, 3);

         txtColorSize.setText("");
         txtProdGroup.setText("");
         status = false;
         cboAttr.setEnabled(true);
     }
     try {
       stmt.close();

     }
     catch (Exception ex) {
       ex.printStackTrace();
  }


 }

 void cboAttr_actionPerformed(ActionEvent e) {
     while (dm.getRowCount() > 0) {
         dm.removeRow(0);
     }
     showDataTable();
     if (cboAttr.getSelectedIndex() == 0) {
         lblColorSize.setText(lang.getString("Color")+":");
     }
     else {
         lblColorSize.setText(lang.getString("Size")+":");
     }
 }
 private void registerKeyboardActions() {
   /// set up the maps:
   InputMap inputMap = new InputMap();
  inputMap.setParent(pnlHeader.getInputMap(JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
  ActionMap actionMap = pnlHeader.getActionMap();


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
   pnlHeader.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                         inputMap);
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

        if (identifier.intValue() == KeyEvent.VK_F1 ||identifier.intValue() == KeyEvent.VK_ENTER) {
          btnModify.doClick();
        }

        else if (identifier.intValue() == KeyEvent.VK_F2 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
          btnBack.doClick();
        }
      }
    }
    private void catchHotKey(KeyEvent e) {
       if (e.getKeyCode() == KeyEvent.VK_F1) {
         btnModify.doClick();
       }
       else if (e.getKeyCode() == KeyEvent.VK_F2 ||
                e.getKeyCode() == KeyEvent.VK_ESCAPE) {
         btnBack.doClick();
       }

     }

  void this_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void table_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void cboAttr_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtProdGroup_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void txtColorSize_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

}

class DialogAttributeView_btnBack_actionAdapter implements java.awt.event.ActionListener {
    DialogAttributeView adaptee;

DialogAttributeView_btnBack_actionAdapter(DialogAttributeView adaptee) {
   this.adaptee = adaptee;
 }
 public void actionPerformed(ActionEvent e) {
   adaptee.btnBack_actionPerformed(e);
 }
}

class DialogAttributeView_table_mouseAdapter extends java.awt.event.MouseAdapter {
DialogAttributeView adaptee;

 DialogAttributeView_table_mouseAdapter(DialogAttributeView adaptee) {
   this.adaptee = adaptee;
 }
 public void mouseClicked(MouseEvent e) {
   adaptee.table_mouseClicked(e);
 }
}

class DialogAttributeView_btnModify_actionAdapter implements java.awt.event.ActionListener {
DialogAttributeView adaptee;

 DialogAttributeView_btnModify_actionAdapter(DialogAttributeView adaptee) {
   this.adaptee = adaptee;
 }
 public void actionPerformed(ActionEvent e) {
   adaptee.btnModify_actionPerformed(e);
 }
}

class DialogAttributeView_cboAttr_actionAdapter implements java.awt.event.ActionListener {
 DialogAttributeView adaptee;

 DialogAttributeView_cboAttr_actionAdapter(DialogAttributeView adaptee) {
   this.adaptee = adaptee;
 }
 public void actionPerformed(ActionEvent e) {
   adaptee.cboAttr_actionPerformed(e);
 }
}

class DialogAttributeView_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeView adaptee;

  DialogAttributeView_this_keyAdapter(DialogAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogAttributeView_table_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeView adaptee;

  DialogAttributeView_table_keyAdapter(DialogAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}

class DialogAttributeView_cboAttr_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeView adaptee;

  DialogAttributeView_cboAttr_keyAdapter(DialogAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.cboAttr_keyPressed(e);
  }
}

class DialogAttributeView_txtProdGroup_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeView adaptee;

  DialogAttributeView_txtProdGroup_keyAdapter(DialogAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtProdGroup_keyPressed(e);
  }
}

class DialogAttributeView_txtColorSize_keyAdapter extends java.awt.event.KeyAdapter {
  DialogAttributeView adaptee;

  DialogAttributeView_txtColorSize_keyAdapter(DialogAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.txtColorSize_keyPressed(e);
  }
}
