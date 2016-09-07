package sim;

import java.awt.*;
import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: Main -> Inv Mgmt -> Item Lookup -> Search -> Item click -> Store Locator</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import javax.swing.tree.*;
import common.*;
import java.awt.*;
import btm.swing.*;
import btm.swing.BJListBox;
import java.sql.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.ResourceBundle;

public class DialogStoreLocation extends JDialog {
  JPanel panelLocation = new JPanel();
  JScrollPane jScrollPaneTree = new JScrollPane();

  Vector vList1 = new Vector();
  Vector vList2 = new Vector();

  private String childIdLocation;
  private String childNameLocation;
  DataAccessLayer DAL ;

  String root = "Root";
  String rootID = "-1";
//  DefaultMutableTreeNode top =  new DefaultMutableTreeNode(root);

//  BJTree jTreeLocation = new BJTree("BTM_IM_ORG_HIR",getData(),top);
  BNode rootTree ;
  BTreeModel pTreeModel;
  BJTree treOrgHier ;

  BJButton btnOK = new BJButton();
  BJButton btnCancel = new BJButton();

  boolean isOK=false;
  BorderLayout borderLayout1 = new BorderLayout();
  int flagDialog = Constant.ITEM_SETUP;
  boolean flagIsLeaf = false;
  Utils ut = new Utils();
  FrameMainSim frmMain;
  JPanel pnlTree = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlOKCancel = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel pnlOK = new JPanel();
  JPanel pnlCancel = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
//  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
//  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  BJListBox jList1 = new BJListBox();
  JButton btnAllToLeft = new JButton();
  JButton btnAllToRight = new JButton();
  JButton btnRightToLeft = new JButton();
  JButton btnLeftToRight = new JButton();
  JScrollPane jScrollPane2 = new JScrollPane();
  BJListBox jList2 = new BJListBox();
  JPanel jPanel9 = new JPanel();
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international
  public DialogStoreLocation(Frame frame, String title, boolean modal,FrameMainSim frmMain) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      childIdLocation=getLocationRoot();
      jbInit();
//      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  public String getChildIdLocation(){
    return childIdLocation;
  }

  public String getChildNameLocation(){
    return childNameLocation;
  }


  public String getLocationRoot() {
       DataAccessLayer DAL = frmMain.getDAL();
//       String sSQL = "select TYPE_ID, HIGHER_TYPE_ID, NAME, DESCRIPTION from BTM_IM_ORG_HIR_TYPE where PARENT_ID = -1";
       String sSQL = "select child_id from BTM_IM_ORG_HIR where PARENT_ID = -1";
       ResultSet rs = null;
       try {
         DAL.setBeginTrans(DAL.getConnection() ) ;
//         System.out.println(sSQL);
         rs = DAL.executeQueries(sSQL);
         while(rs.next() ){
           childIdLocation=rs.getString("CHILD_ID") ;
           //get first record
           break;
         }
         rs.getStatement().close();
         DAL.setEndTrans(DAL.getConnection() ) ;
       }
       catch (Exception e) {
           DAL.setRollback(DAL.getConnection() ) ;
           ExceptionHandle eh = new ExceptionHandle();
           eh.ouputError(e);
       }

       return childIdLocation;
   }//end method

   public DialogStoreLocation(FrameMainSim frmMain) {
     this(null, "", false,frmMain);
     childIdLocation=getLocationRoot();
   }
  private void jbInit() throws Exception {
    registerKeyboardActions();
    //DAL.getConnfigParameter();

    rootTree =new BNode( new BNodeDetail(Constant.rootId,Constant.rootId,Constant.rootId,Constant.root, Constant.root));
    pTreeModel = new BTreeModel(rootTree);
    treOrgHier=new BJTree(pTreeModel);

    panelLocation.setLayout(borderLayout2);
    this.getContentPane().setLayout(borderLayout1);
    panelLocation.setBackground(new Color(121, 152, 198));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnOK.setBounds(new Rectangle(52, 11, 108, 29));
    btnOK.setPreferredSize(new Dimension(133, 57));
    btnOK.setText(lang.getString("OK"));
    btnOK.addActionListener(new DialogStoreLocation_btnOK_actionAdapter(this));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setBounds(new Rectangle(3, 11, 106, 29));
    btnCancel.setPreferredSize(new Dimension(133, 57));
    btnCancel.setSelectedIcon(null);
    btnCancel.setText(lang.getString("Cancel"));
    btnCancel.addActionListener(new DialogStoreLocation_btnCancel_actionAdapter(this));
    pnlOKCancel.setLayout(borderLayout3);
    pnlOK.setLayout(null);
    pnlCancel.setLayout(null);
    pnlTree.setLayout(borderLayout4);
    treOrgHier.setPreferredSize(new Dimension(100, 500));
    treOrgHier.setModel(pTreeModel);
    treOrgHier.setRootVisible(true);
    treOrgHier.setScrollsOnExpand(true);
    treOrgHier.addKeyListener(new DialogStoreLocation_treOrgHier_keyAdapter(this));
    treOrgHier.addMouseListener(new DialogStoreLocation_treOrgHier_mouseAdapter(this));
    treOrgHier.addTreeSelectionListener(new DialogStoreLocation_treOrgHier_treeSelectionAdapter(this));
    pnlTree.setPreferredSize(new Dimension(800, 550));
    pnlOK.setBackground(new Color(121, 152, 198));
    pnlOK.setPreferredSize(new Dimension(160, 1));
    jScrollPaneTree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPaneTree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPaneTree.setMaximumSize(new Dimension(567, 567));
    jScrollPaneTree.setMinimumSize(new Dimension(25, 25));
    jScrollPaneTree.setPreferredSize(new Dimension(240, 530));
    jScrollPaneTree.setVerifyInputWhenFocusTarget(true);
    pnlOKCancel.setBackground(new Color(121, 152, 198));
    pnlOKCancel.setPreferredSize(new Dimension(151, 50));
    this.getContentPane().setBackground(new Color(121, 152, 198));
    this.setResizable(true);
    this.addKeyListener(new DialogStoreLocation_this_keyAdapter(this));
    pnlCancel.setBackground(new Color(121, 152, 198));
    jPanel3.setBackground(new Color(121, 152, 198));
    jPanel2.setBackground(new Color(121, 152, 198));
    jPanel2.setMinimumSize(new Dimension(10, 10));
    jPanel2.setPreferredSize(new Dimension(550, 540));
    jPanel2.setLayout(borderLayout5);
    jPanel1.setBackground(new Color(121, 152, 198));
//    jPanel4.setBackground(new Color(121, 152, 198));
//    jPanel4.setPreferredSize(new Dimension(240, 540));
    jPanel6.setPreferredSize(new Dimension(250, 540));
    jPanel6.setLayout(borderLayout6);
    jPanel5.setBackground(new Color(121, 152, 198));
    jPanel5.setPreferredSize(new Dimension(60, 540));
//    jPanel7.setBackground(new Color(121, 152, 198));
//    jPanel7.setPreferredSize(new Dimension(240, 540));
    jPanel8.setBackground(new Color(121, 152, 198));
    jScrollPane1.setPreferredSize(new Dimension(240, 530));
    btnAllToLeft.setText("<<");
    btnAllToLeft.addKeyListener(new DialogStoreLocation_btnAllToLeft_keyAdapter(this));
    btnAllToLeft.addActionListener(new DialogStoreLocation_btnAllToLeft_actionAdapter(this));
    btnAllToRight.setText(">>");
    btnAllToRight.addKeyListener(new DialogStoreLocation_btnAllToRight_keyAdapter(this));
    btnAllToRight.addActionListener(new DialogStoreLocation_btnAllToRight_actionAdapter(this));
    btnRightToLeft.setPreferredSize(new Dimension(47, 25));
    btnRightToLeft.setText("<");
    btnRightToLeft.addKeyListener(new DialogStoreLocation_btnRightToLeft_keyAdapter(this));
    btnRightToLeft.addActionListener(new DialogStoreLocation_btnRightToLeft_actionAdapter(this));
    btnLeftToRight.setOpaque(true);
    btnLeftToRight.setPreferredSize(new Dimension(47, 25));
    btnLeftToRight.setRequestFocusEnabled(true);
    btnLeftToRight.setText(">");
    btnLeftToRight.addKeyListener(new DialogStoreLocation_btnLeftToRight_keyAdapter(this));
    btnLeftToRight.addActionListener(new DialogStoreLocation_btnLeftToRight_actionAdapter(this));
    jScrollPane2.getViewport().setBackground(new Color(121, 152, 198));
    jScrollPane2.setPreferredSize(new Dimension(240, 530));
    jPanel9.setBackground(new Color(121, 152, 198));
    jPanel9.setPreferredSize(new Dimension(60, 200));
    jList1.addMouseListener(new DialogStoreLocation_jList1_mouseAdapter(this));
    jList2.addMouseListener(new DialogStoreLocation_jList2_mouseAdapter(this));
    jList1.addKeyListener(new DialogStoreLocation_jList1_keyAdapter(this));
    jList2.addKeyListener(new DialogStoreLocation_jList2_keyAdapter(this));
    getContentPane().add(panelLocation, BorderLayout.CENTER);
    panelLocation.add(pnlTree, BorderLayout.CENTER);
    pnlTree.add(jScrollPaneTree,  BorderLayout.CENTER);
    pnlTree.add(jPanel1,  BorderLayout.WEST);
    pnlTree.add(jPanel2, BorderLayout.EAST);
//    jPanel2.add(jPanel4,  BorderLayout.WEST);
//    jPanel4.add(jScrollPane1, null);
    jPanel2.add(jScrollPane1,BorderLayout.WEST);
    jScrollPane1.getViewport().add(jList1, null);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel9, null);
    jPanel5.add(btnLeftToRight, null);
    jPanel5.add(btnRightToLeft, null);
    jPanel5.add(btnAllToRight, null);
    jPanel5.add(btnAllToLeft, null);
    jPanel2.add(jPanel6, BorderLayout.EAST);
//    jPanel6.add(jPanel7, BorderLayout.CENTER);
    jPanel6.add(jScrollPane2,BorderLayout.CENTER);
    jPanel6.add(jPanel8,  BorderLayout.EAST);
    pnlTree.add(jPanel3, BorderLayout.NORTH);
    jScrollPaneTree.getViewport().add(treOrgHier, null);
    panelLocation.add(pnlOKCancel,  BorderLayout.SOUTH);
    pnlOKCancel.add(pnlOK,  BorderLayout.WEST);
    pnlOK.add(btnOK, BorderLayout.WEST);
    pnlOKCancel.add(pnlCancel, BorderLayout.CENTER);
    pnlCancel.add(btnCancel, BorderLayout.SOUTH);
//    jPanel7.add(jScrollPane2, null);
    jScrollPane2.getViewport().add(jList2, null);

    //set size and position is center screen
    this.setSize(new Dimension(800, 600));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    //built tree prod hier
    Vector vOrgHierAll = new Vector();
    vOrgHierAll = treOrgHier.initHierAll(frmMain.getDAL(), "BTM_IM_ORG_HIR");
    Vector vPrdHierActive = new Vector();
    vPrdHierActive = treOrgHier.initHierActive(vOrgHierAll);
    treOrgHier.showHierTree(vPrdHierActive, rootTree);
    treOrgHier.updateUI();
  }

  public boolean isOKButton(){
    return isOK;
  }

  void treOrgHier_valueChanged(TreeSelectionEvent e) {
//    childIdLocation=treOrgHier.getChildId("BTM_IM_ORG_HIR",e);
//    childNameLocation=treOrgHier.getNodeName(e);
    BNode currentNode;
    // Get paths of all selected nodes
    if (treOrgHier.getSelectionPaths() != null){
      TreePath[] paths = treOrgHier.getSelectionPaths();
//    for (int i = 0; i < paths.length; i++) {
//      System.out.println("_______________________");
//      System.out.println(paths[0].getLastPathComponent().toString());
      currentNode = (BNode) paths[0].getLastPathComponent();
//      System.out.println("ID: " + currentNode.getNodeID());
//      System.out.println("Parent ID: " + currentNode.getParentID());
//      System.out.println("Type ID: " + currentNode.getTypeID());
//      System.out.println("Name: " + currentNode.getName());

      childIdLocation = currentNode.getNodeID();
      childNameLocation = currentNode.getName();

      if (currentNode.isLeaf()) {
        flagIsLeaf = true;
      }
      else {
        flagIsLeaf = false;
      }
    }

  }

  void btnOK_actionPerformed(ActionEvent e) {
    isOK = true;
    this.dispose();
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    isOK=false;
    this.dispose();
  }
  //get List2
  Vector getList2(){
    return jList2.getAllID(); //vList2;
  }
  //get List1
  Vector getList1(){
//    return vList1;
    return jList1.getAllID();
  }
  //get Data
  Vector getDataList1(){
    return jList1.getAllData();
  }
  Vector getDataList2(){
    return jList2.getAllData();
  }
//  //get area id
//  String getChildID(){
//    return childIdLocation;
//  }
//  //get area name
//  String getChildName(){
//    return childNameLocation;
//  }
  void treOrgHier_mouseClicked(MouseEvent e) {
    // Set data to jList1
    try{
      ResultSet rs = null;
      String sql = "";
      if (!childIdLocation.equalsIgnoreCase("-1")) {
        sql = " select c.STORE_ID, name " +
            " from BTM_IM_ORG_HIR a, " +
            " BTM_IM_ORG_HIR b, " +
            " BTM_IM_STORE   c " +
            " where a.PARENT_ID = b.CHILD_ID " +
            " and   a.CHILD_ID  = c.CHILD_ID " +
            " and   (b.CHILD_ID = '" + childIdLocation +
            "' or a.CHILD_ID = '" + childIdLocation +
            "' or a.PARENT_ID = '" + childIdLocation +
            "' or b.PARENT_ID = '" + childIdLocation + "')" +
            " and c.closed_date = to_date('7777-7-7','yyyy-MM-dd') ";
      }
      else {
        sql = "select store_id, name from btm_im_store where closed_date = to_date('7777-7-7','yyyy-MM-dd') ";
      }
//      System.out.println(sql);
//      System.exit(0);
      rs = DAL.executeScrollQueries(sql);
//      vList1.clear();
//      vList2.clear();
//      while (rs.next()){
//        vList1.add(new Long(rs.getLong("store_id")));
//      }
//      jList1.setListData(vList1);
//      jList2.setListData(vList2);
      jList1.setData(rs);
    }catch (Exception ex){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }
  }

  void btnLeftToRight_actionPerformed(ActionEvent e) {
    if (jList1.getSelectedIndex()!= -1){
//      jList2.removeIndexAll();
      jList2.addData(jList1.getSelectedValues(),jList1.getSelectedDatas());
      jList1.removeIndex(jList1.getSelectedIndices());
//      int[] selected = jList1.getSelectedIndices();
//      for (int i=0; i<selected.length; i++){
//        vList2.add(vList1.elementAt(selected[i]));
//      }
//      for (int i=0; i<selected.length; i++){
//        vList1.remove(selected[0]);
//      }
//      jList1.setListData(vList1);
//      jList2.setListData(vList2);
    }
  }

  void btnRightToLeft_actionPerformed(ActionEvent e) {
    if (jList2.getSelectedIndex()!= -1){
      jList1.addData(jList2.getSelectedValues(),jList2.getSelectedDatas());
      jList2.removeIndex(jList2.getSelectedIndices());

//      int[] selected = jList2.getSelectedIndices();
//      for (int i=0; i<selected.length; i++){
//        vList1.add(vList2.elementAt(selected[i]));
//      }
//      for (int i=0; i<selected.length; i++){
//        vList2.remove(selected[0]);
//      }
//      jList2.setListData(vList2);
//      jList1.setListData(vList1);
    }

  }

  void btnAllToRight_actionPerformed(ActionEvent e) {
    jList2.addData(jList1.getAllData(),jList1.getAllID());
    jList1.removeIndexAll();
//    while (!vList1.isEmpty()){
//      vList2.add(vList1.elementAt(0));
//      vList1.remove(0);
//    }
//    jList1.setListData(vList1);
//    jList2.setListData(vList2);
  }

  void btnAllToLeft_actionPerformed(ActionEvent e) {
    jList1.addData(jList2.getAllData(),jList2.getAllID());
    jList2.removeIndexAll();
//    while (!vList2.isEmpty()){
//      vList1.add(vList2.elementAt(0));
//      vList2.remove(0);
//    }
//    jList1.setListData(vList1);
//    jList2.setListData(vList2);
//
  }

  void jList1_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2 ){
      jList2.addData(jList1.getSelectedValues(),jList1.getSelectedDatas());
      jList1.removeIndex(jList1.getSelectedIndices());

//      int[] selected = jList1.getSelectedIndices();
//      for (int i=0; i<selected.length; i++){
//        vList2.add(vList1.elementAt(selected[i]));
//      }
//      for (int i=0; i<selected.length; i++){
//        vList1.remove(selected[0]);
//      }
//      jList1.setListData(vList1);
//      jList2.setListData(vList2);
    }
  }

  void jList2_mouseClicked(MouseEvent e) {
    if (e.getClickCount()==2){
      jList1.addData(jList2.getSelectedValues(),jList2.getSelectedDatas());
      jList2.removeIndex(jList2.getSelectedIndices());

//      int[] selected = jList2.getSelectedIndices();
//      for (int i=0; i<selected.length; i++){
//        vList1.add(vList2.elementAt(selected[i]));
//      }
//      for (int i=0; i<selected.length; i++){
//        vList2.remove(selected[0]);
//      }
//      jList2.setListData(vList2);
//      jList1.setListData(vList1);
    }
  }
  private void registerKeyboardActions() {
               /// set up the maps:

               InputMap inputMap = new InputMap();
               inputMap.setParent(pnlOKCancel.getInputMap(JComponent.
                                                      WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
               ActionMap actionMap = pnlOKCancel.getActionMap();


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
                pnlOKCancel.setInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,inputMap);
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
                   btnOK.doClick();
                 }
                 else if (identifier.intValue() == KeyEvent.VK_F2 || identifier.intValue() == KeyEvent.VK_ESCAPE) {
                   btnCancel.doClick();
                 }
               }
       }
       private void catchHotKey(KeyEvent e) {
              if (e.getKeyCode() == KeyEvent.VK_F1) {
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

  void treOrgHier_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void jList1_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void jList2_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void btnLeftToRight_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void btnRightToLeft_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void btnAllToRight_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

  void btnAllToLeft_keyPressed(KeyEvent e) {
    catchHotKey(e);
  }

}

class DialogStoreLocation_treOrgHier_treeSelectionAdapter implements javax.swing.event.TreeSelectionListener {
  DialogStoreLocation adaptee;

  DialogStoreLocation_treOrgHier_treeSelectionAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(TreeSelectionEvent e) {
    adaptee.treOrgHier_valueChanged(e);
  }
}

class DialogStoreLocation_btnOK_actionAdapter implements java.awt.event.ActionListener {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnOK_actionAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class DialogStoreLocation_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnCancel_actionAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogStoreLocation_treOrgHier_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_treOrgHier_mouseAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.treOrgHier_mouseClicked(e);
  }
}

class DialogStoreLocation_btnLeftToRight_actionAdapter implements java.awt.event.ActionListener {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnLeftToRight_actionAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnLeftToRight_actionPerformed(e);
  }
}

class DialogStoreLocation_btnRightToLeft_actionAdapter implements java.awt.event.ActionListener {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnRightToLeft_actionAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnRightToLeft_actionPerformed(e);
  }
}

class DialogStoreLocation_btnAllToRight_actionAdapter implements java.awt.event.ActionListener {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnAllToRight_actionAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAllToRight_actionPerformed(e);
  }
}

class DialogStoreLocation_btnAllToLeft_actionAdapter implements java.awt.event.ActionListener {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnAllToLeft_actionAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAllToLeft_actionPerformed(e);
  }
}

class DialogStoreLocation_jList1_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_jList1_mouseAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jList1_mouseClicked(e);
  }
}

class DialogStoreLocation_jList2_mouseAdapter extends java.awt.event.MouseAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_jList2_mouseAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jList2_mouseClicked(e);
  }
}

class DialogStoreLocation_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_this_keyAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogStoreLocation_treOrgHier_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_treOrgHier_keyAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.treOrgHier_keyPressed(e);
  }
}

class DialogStoreLocation_jList1_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_jList1_keyAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jList1_keyPressed(e);
  }
}

class DialogStoreLocation_jList2_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_jList2_keyAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jList2_keyPressed(e);
  }
}

class DialogStoreLocation_btnLeftToRight_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnLeftToRight_keyAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.btnLeftToRight_keyPressed(e);
  }
}

class DialogStoreLocation_btnRightToLeft_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnRightToLeft_keyAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.btnRightToLeft_keyPressed(e);
  }
}

class DialogStoreLocation_btnAllToRight_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnAllToRight_keyAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.btnAllToRight_keyPressed(e);
  }
}

class DialogStoreLocation_btnAllToLeft_keyAdapter extends java.awt.event.KeyAdapter {
  DialogStoreLocation adaptee;

  DialogStoreLocation_btnAllToLeft_keyAdapter(DialogStoreLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.btnAllToLeft_keyPressed(e);
  }
}
