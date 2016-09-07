package sim;

import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import javax.swing.tree.*;
import common.*;
import java.awt.*;
import btm.swing.*;
import java.sql.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class DialogLocation extends JDialog {
  JPanel panelLocation = new JPanel();
  JScrollPane jScrollPaneTree = new JScrollPane();

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
  ResourceBundle lang=DataAccessLayer.getFrameLabel(Utils.sLanguague ,Utils.sCountry);    //international
  public DialogLocation(Frame frame, String title, boolean modal,FrameMainSim frmMain) {
    super(frame, title, modal);
    try {
      this.frmMain = frmMain;
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
      ResultSet rs = null;
      String sSQL = "select PARENT_ID, CHILD_ID, TYPE_ID, RECD_LOAD_DATE,RECD_LAST_UPD_DATE, "+
           "RECD_CURR_FLAG, RECD_CLOSED_DATE, CHILD_NAME,PARENT_NAME, DESCRIPTION  "+
           "from BTM_IM_ORG_HIR where PARENT_ID = -1";
      try {
//        System.out.println(sSQL);
        Statement stmt = DAL.getConnection().createStatement();
        rs = DAL.executeQueries(sSQL,stmt);
        while(rs.next() ){
          childIdLocation=rs.getString("CHILD_ID") ;
          //get first record
          break;
        }
        rs.close();
        stmt.close();
      }
      catch (Exception e) {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }

      return childIdLocation;
  }//end method


public DataAccessLayer getDataAccessLayer(){
  return DAL;
}

public void setDataAccessLayer(DataAccessLayer DAL ){
  this.DAL=DAL;
}



  public DialogLocation(FrameMainSim frmMain) {
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
    treOrgHier.addTreeSelectionListener(new DialogLocation_jTreeLocation_treeSelectionAdapter(this));
    btnOK.setBounds(new Rectangle(52, 11, 108, 29));
    btnOK.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnOK.setPreferredSize(new Dimension(133, 57));
    btnOK.setText(lang.getString("OK"));
    btnOK.addActionListener(new DialogLocation_btnOK_actionAdapter(this));
    btnCancel.setBounds(new Rectangle(3, 11, 106, 29));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    btnCancel.setPreferredSize(new Dimension(133, 57));
    btnCancel.setSelectedIcon(null);
    btnCancel.setText(lang.getString("Cancel"));
    btnCancel.addActionListener(new DialogLocation_btnCancel_actionAdapter(this));
    pnlOKCancel.setLayout(borderLayout3);
    pnlOK.setLayout(null);
    pnlCancel.setLayout(null);
    pnlTree.setLayout(borderLayout4);
    treOrgHier.setPreferredSize(new Dimension(100, 500));
    treOrgHier.setModel(pTreeModel);
    treOrgHier.setRootVisible(true);
    treOrgHier.setScrollsOnExpand(true);
    treOrgHier.addKeyListener(new DialogLocation_treOrgHier_keyAdapter(this));
    pnlTree.setPreferredSize(new Dimension(104, 220));
    pnlOK.setBackground(new Color(121, 152, 198));
    pnlOK.setPreferredSize(new Dimension(160, 1));
    jScrollPaneTree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPaneTree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPaneTree.setMaximumSize(new Dimension(567, 567));
    jScrollPaneTree.setMinimumSize(new Dimension(25, 25));
    jScrollPaneTree.setPreferredSize(new Dimension(104, 250));
    jScrollPaneTree.setVerifyInputWhenFocusTarget(true);
    pnlOKCancel.setBackground(new Color(121, 152, 198));
    pnlOKCancel.setPreferredSize(new Dimension(151, 50));
    this.getContentPane().setBackground(new Color(121, 152, 198));
    this.setResizable(true);
    this.addKeyListener(new DialogLocation_this_keyAdapter(this));
    pnlCancel.setBackground(new Color(121, 152, 198));
    jPanel3.setBackground(new Color(121, 152, 198));
    jPanel2.setBackground(new Color(121, 152, 198));
    jPanel1.setBackground(new Color(121, 152, 198));
    getContentPane().add(panelLocation, BorderLayout.CENTER);
    panelLocation.add(pnlTree, BorderLayout.CENTER);
    pnlTree.add(jScrollPaneTree,  BorderLayout.CENTER);
    pnlTree.add(jPanel1,  BorderLayout.WEST);
    pnlTree.add(jPanel2, BorderLayout.EAST);
    pnlTree.add(jPanel3, BorderLayout.NORTH);
    jScrollPaneTree.getViewport().add(treOrgHier, null);
    panelLocation.add(pnlOKCancel,  BorderLayout.SOUTH);
    pnlOKCancel.add(pnlOK,  BorderLayout.WEST);
    pnlOK.add(btnOK, BorderLayout.WEST);
    pnlOKCancel.add(pnlCancel, BorderLayout.CENTER);
    pnlCancel.add(btnCancel, BorderLayout.SOUTH);
    btnOK.addActionListener(new DialogLocation_btnOK_actionAdapter(this));
    btnCancel.addActionListener(new DialogLocation_btnCancel_actionAdapter(this));

    //set size and position is center screen
    this.setSize(new Dimension(341, 304));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
//    System.out.println("ss111");
    //built tree prod hier
    Vector vOrgHierAll = new Vector();
    vOrgHierAll = treOrgHier.initHierAll(frmMain.getDAL(), "BTM_IM_ORG_HIR");
    Vector vOrgHierActive = new Vector();
    vOrgHierActive = treOrgHier.initHierActive(vOrgHierAll);
    treOrgHier.showHierTree(vOrgHierActive, rootTree);

//System.out.println("ss111");
    treOrgHier.updateUI();
  }

  public boolean isOKButton(){
    return isOK;
  }

  void jTreeLocation_valueChanged(TreeSelectionEvent e) {
//    childIdLocation=treOrgHier.getChildId("BTM_IM_ORG_HIR",e);
//    childNameLocation=treOrgHier.getNodeName(e);
    BNode currentNode;
    // Get paths of all selected nodes
    if (treOrgHier.getSelectionPaths()!= null){
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
//    }

  }

  void btnOK_actionPerformed(ActionEvent e) {
    if (flagDialog == Constant.STORE_SETUP){
      if (flagIsLeaf == false){
        ut.showMessage(frmMain,lang.getString("TT001"),lang.getString("MS1011_ChooseTheExactlyLocation"));
        flagDialog = 0;
        return;
      }else{
        isOK = true;
        this.dispose();
        flagDialog = Constant.ITEM_SETUP;
      }
    }else if (flagDialog == Constant.ITEM_SETUP){
      isOK = true;
      this.dispose();
    }else if (flagDialog == 0){
      flagDialog = Constant.STORE_SETUP;
    }

  }

  void btnCancel_actionPerformed(ActionEvent e) {
    isOK=false;
    this.dispose();

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

}

class DialogLocation_jTreeLocation_treeSelectionAdapter implements javax.swing.event.TreeSelectionListener {
  DialogLocation adaptee;

  DialogLocation_jTreeLocation_treeSelectionAdapter(DialogLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void valueChanged(TreeSelectionEvent e) {
    adaptee.jTreeLocation_valueChanged(e);
  }
}

class DialogLocation_btnOK_actionAdapter implements java.awt.event.ActionListener {
  DialogLocation adaptee;

  DialogLocation_btnOK_actionAdapter(DialogLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}

class DialogLocation_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  DialogLocation adaptee;

  DialogLocation_btnCancel_actionAdapter(DialogLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class DialogLocation_this_keyAdapter extends java.awt.event.KeyAdapter {
  DialogLocation adaptee;

  DialogLocation_this_keyAdapter(DialogLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class DialogLocation_treOrgHier_keyAdapter extends java.awt.event.KeyAdapter {
  DialogLocation adaptee;

  DialogLocation_treOrgHier_keyAdapter(DialogLocation adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.treOrgHier_keyPressed(e);
  }
}
