package sim;


import java.util.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import btm.business.*;
import btm.swing.*;
import common.*;
import java.awt.event.*;
import btm.attr.*;
import java.sql.Statement;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Merch Mgmt -> Attribute Setup -> View</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Phuoc Nguyen
 * @version 1.0
 */


public class PanelAttributeView extends JPanel{
    DataAccessLayer DAL;
    FrameMainSim frmMain;
    ProductAttributeBusObj productAttBusObj = new ProductAttributeBusObj();
    Utils ut = new Utils();
    BorderLayout borderLayout1 = new BorderLayout();
    boolean status = false;
    Statement stmt = null;
//    Statement stmt1 = null;
//    Statement stmt2 = null;
    boolean modifyStatus = false;
    String attr_Dtl_id = "";
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
    JLabel lblAttribute = new JLabel();//("Attribute:");
    JLabel lblProGroup = new JLabel();//("Product Group:");
    JLabel lblColorSize = new JLabel();//("Color:");

    //combobox
    BJComboBox cboAttr = new BJComboBox();

    //textfield
    JTextField txtColorSize = new JTextField();

    //Table
    SortableTableModel dm = new SortableTableModel();

    ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);

    BJTable table = new BJTable(dm, true) {
       public Class getColumnClass(int col) {
           return Object.class;
       }

       public boolean isCellEditable(int row, int col) {
           return false;
       }
   };
   StripedTableCellRenderer str = new StripedTableCellRenderer(null,Color.lightGray, Color.white, null, null);
  BJComboBox cboProdGroup = new BJComboBox();

   public PanelAttributeView(FrameMainSim frmMain) {
       try {
           this.frmMain = frmMain;
           DAL = frmMain.getDAL();
           jbInit();
       }
       catch (Exception ex) {
           ex.printStackTrace();
       }
   }

    void jbInit() throws Exception {
       registerKeyboardActions();
       this.setBackground(UIManager.getColor("Label.background"));
       this.setPreferredSize(new Dimension(800, 600));
       this.setLayout(borderLayout1);

       lblAttribute.setText(lang.getString("Attribute")+":");
       lblProGroup.setText(lang.getString("ProGroup")+":");
       lblColorSize.setText(lang.getString("ColorSize")+":");

       jScrollPane1.setPreferredSize(new Dimension(790, 405));
       btnBack.addActionListener(new PanelAttributeView_btnBack_actionAdapter(this));
       table.addMouseListener(new PanelAttributeView_table_mouseAdapter(this));
       btnModify.addActionListener(new PanelAttributeView_btnModify_actionAdapter(this));
       cboProdGroup.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
       cboProdGroup.setBounds(new Rectangle(1, 32, 220, 21));
       cboProdGroup.addActionListener(new PanelAttributeView_cboProdGroup_actionAdapter(this));
       table.addKeyListener(new PanelAttributeView_table_keyAdapter(this));
       this.add(pnlHeader, BorderLayout.NORTH);
       this.add(pnlCenter, BorderLayout.CENTER);
       this.add(pnlBottom, BorderLayout.SOUTH);
       this.add(pnlLef,BorderLayout.WEST);
       this.add(pnlRight,BorderLayout.EAST);

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
       pnlRight.add(txtColorSize, null);
    pnlRight.add(cboProdGroup, null);

       pnlBottom.add(jScrollPane1, null);
       jScrollPane1.getViewport().add(table,null);

       //button done
       btnModify.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
       btnModify.setText(lang.getString("Modify")+" (F4)");
       btnModify.setToolTipText(lang.getString("Modify")+" (F4)");
       //btn Add
       btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
       btnBack.setText("<html><center><b>"+lang.getString("Cancel")+" </b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&n" +
       "bsp;&nbsp;&nbsp;&nbsp;&nbsp;&" + "nbsp;F12</html>");
       btnBack.setToolTipText(lang.getString("Cancel")+" (F12)");

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


       //Combo box
       cboAttr.setPreferredSize(new Dimension(220, 21));
       cboAttr.setBounds(new Rectangle(1, 6, 220, 21));
       cboAttr.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));

       //Table
       String[] columnNames = new String[] {"Attribute Detail Id","Attribute", "Product Group", "Color/Size"};
       dm.setDataVector(new Object[][] {}, columnNames);
       cboAttr.addActionListener(new PanelAttributeView_cboAttr_actionAdapter(this));
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
     cboProdGroup.setData(productAttBusObj.getProdGroup(DAL,stmt));
     try {
       stmt.close();

     }
     catch (Exception ex) {
       ex.printStackTrace();
     }

   }

   void showDataTable(){
     if (cboAttr.getSelectedIndex() != -1 && cboProdGroup.getSelectedIndex() != -1){
       ResultSet rs = null;
       Statement stmt = null;
       String sql = "select attr_dtl_id, attr_dtl_desc, child_name, attr_desc " +
           " from btm_im_attr a, btm_im_attr_dtl ad, btm_im_prod_hir p " +
           " where a.attr_id = ad.attr_id and ad.prod_group_id = p.child_id " +
           " and ad.attr_id = '" + cboAttr.getSelectedData() + "'" +
           " and ad.prod_group_id = '" + cboProdGroup.getSelectedData() + "'";
//       System.out.println(sql);

       try {
         stmt = DAL.getConnection().createStatement(ResultSet.
             TYPE_SCROLL_INSENSITIVE,
             ResultSet.CONCUR_READ_ONLY);
         rs = DAL.executeScrollQueries(sql, stmt);
         dm.resetIndexes();
         table.setData(rs);
         table.setHeaderName(lang.getString("Attribute"),0);
         table.setHeaderName(lang.getString("AttrDesc"),1);
         table.setHeaderName(lang.getString("ProGroup"),2);
         table.setHeaderName(lang.getString("ColorSize"),3);
         rs.close();
         stmt.close();
       }
       catch (Exception e) {
         e.printStackTrace();
       }

     }
   }
   void btnBack_actionPerformed(ActionEvent e) {
     modifyStatus = false;
     frmMain.showScreen(Constant.SCRS_ATTRIBUTE_SETUP);
   }

  void table_mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
        this.attr_Dtl_id = table.getValueAt(table.getSelectedRow(),0).toString();
          status = true;
          int row = table.getSelectedRow();
          txtColorSize.setText(table.getValueAt(row, 1).toString());
          if(cboAttr.getSelectedIndex()==0){
              lblColorSize.setText(lang.getString("Color")+":");
          }else {
              lblColorSize.setText(lang.getString("Size")+":");
          }
//          txtProdGroup.setText(table.getValueAt(row, 2).toString());
          cboProdGroup.setSelectedItem(table.getValueAt(row, 2).toString());
          modifyStatus = true;
//          cboAttr.setEnabled(false);
      }
  }
  private String getProdGroupId(String prodGroupName){
    String id = "";
    ResultSet rs = null;

    String sqlStr = "Select distinct Parent_Id From BTM_IM_PROD_HIR Where lower(Parent_Name)=lower('" + prodGroupName + "')";
    try{
//      System.out.println(sqlStr);
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
              ut.showMessage(frmMain, lang.getString("TT001"), "\"" + lblColorSize.getText() + "\" is too large");
              txtColorSize.requestFocus(true);
              return;
          }

          if (sizeColor.equals("")) {
              ut.showMessage(frmMain, lang.getString("TT001"), lang.getString("MS1003_ColorOrSizeIsNotNull"));
              txtColorSize.requestFocus(true);
              return;
          }
          String prod_group_id = cboProdGroup.getSelectedData();
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

          productAttBusObj.updateAttrDetail(DAL,sizeColor,cboAttr.getSelectedData(), cboProdGroup.getSelectedData(), attr_Dtl_id,stmt);

//          int row = table.getSelectedRow();
//          table.setValueAt(sizeColor, row, 3);

          txtColorSize.setText("");
//          txtProdGroup.setText("");
          cboProdGroup.setSelectedIndex(0);
          cboAttr.setSelectedIndex(0);
          status = false;
          modifyStatus = false;
//          cboAttr.setEnabled(true);
      }
      try {
        stmt.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
      showDataTable();

  }

  void cboAttr_actionPerformed(ActionEvent e) {
    if (cboAttr.getSelectedIndex() != -1){
      if (modifyStatus){
        if (cboAttr.getSelectedIndex() == 1) {
          lblColorSize.setText(lang.getString("Color")+":");
        }
        else if(cboAttr.getSelectedIndex() == 2){
          lblColorSize.setText(lang.getString("Size")+":");
        }
        return;
      }
      while (dm.getRowCount() > 0) {
        dm.removeRow(0);
      }
      showDataTable();
      if (cboAttr.getSelectedIndex() == 1) {
        lblColorSize.setText(lang.getString("Color")+":");
      }
      else if(cboAttr.getSelectedIndex() == 2){
        lblColorSize.setText(lang.getString("Size")+":");
      }
    }
  }
  private void registerKeyboardActions() {
    /// set up the maps:
    InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap = getActionMap();

    // F4
    Integer key = new Integer(KeyEvent.VK_F4);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), key);
    actionMap.put(key, new KeyAction(key));

    // F12
    key = new Integer(KeyEvent.VK_F12);
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), key);
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
         if (identifier.intValue() == KeyEvent.VK_F4) {
           btnModify.doClick();
         }
         else if (identifier.intValue() == KeyEvent.VK_F12 ||identifier.intValue() == KeyEvent.VK_ESCAPE) {
           btnBack.doClick();
         }
       }
     }

  void cboProdGroup_actionPerformed(ActionEvent e) {
    if (cboProdGroup.getSelectedIndex() != -1){
      if (modifyStatus){
        return;
      }
      showDataTable();
    }
  }

  void table_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_F2) {
                 btnBack.doClick();
               }
  }
}

class PanelAttributeView_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeView adaptee;

  PanelAttributeView_btnBack_actionAdapter(PanelAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelAttributeView_table_mouseAdapter extends java.awt.event.MouseAdapter {
  PanelAttributeView adaptee;

  PanelAttributeView_table_mouseAdapter(PanelAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.table_mouseClicked(e);
  }
}

class PanelAttributeView_btnModify_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeView adaptee;

  PanelAttributeView_btnModify_actionAdapter(PanelAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnModify_actionPerformed(e);
  }
}

class PanelAttributeView_cboAttr_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeView adaptee;

  PanelAttributeView_cboAttr_actionAdapter(PanelAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboAttr_actionPerformed(e);
  }
}

class PanelAttributeView_cboProdGroup_actionAdapter implements java.awt.event.ActionListener {
  PanelAttributeView adaptee;

  PanelAttributeView_cboProdGroup_actionAdapter(PanelAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cboProdGroup_actionPerformed(e);
  }
}

class PanelAttributeView_table_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAttributeView adaptee;

  PanelAttributeView_table_keyAdapter(PanelAttributeView adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.table_keyPressed(e);
  }
}
