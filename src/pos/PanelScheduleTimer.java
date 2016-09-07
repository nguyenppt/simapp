package pos;

import java.awt.*;
import common.*;
import javax.swing.*;
import java.awt.event.*;
import btm.swing.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelScheduleTimer extends JPanel {

  FrameMain frmMain;
  Utils ut=new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  BJButton btnOk = new BJButton();
  BJButton btnBack = new BJButton();

  public PanelScheduleTimer(FrameMain frmMain) {
    try {
      this.frmMain=frmMain;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    this.setBackground(SystemColor.control);
    registerKeyboardActions();
    btnBack.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnBack.setVerifyInputWhenFocusTarget(true);
    btnBack.setText(lang.getString("Back") +  " (F2)");
    btnBack.addActionListener(new PanelScheduleTimer_btnBack_actionAdapter(this));
    btnOk.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnOk.setRequestFocusEnabled(true);
    btnOk.setToolTipText(lang.getString("OK") + " (F1)");
    btnOk.setText(lang.getString("OK") + " (F1)");
    btnOk.addActionListener(new PanelScheduleTimer_btnOk_actionAdapter(this));
    this.setLayout(borderLayout1);
  }

  //show button in Frame Main
  void showButton(){
    frmMain.showButton(btnOk);
     frmMain.showButton(btnBack);
  }

  void btnBack_actionPerformed(ActionEvent e) {
    frmMain.setTitle(lang.getString("TT016"));
    frmMain.showScreen(Constant.SCR_MAIN);
    frmMain.removeButton();
    frmMain.pnlMain.showAdminButton();
    frmMain.pnlMain.requestFocusInWindow();
  }

  void btnOk_actionPerformed(ActionEvent e) {
    String time = frmMain.txtMainInput.getText();
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
       if (identifier.intValue() == KeyEvent.VK_F1 ) {
         btnOk.doClick();
       }
       else if (identifier.intValue() == KeyEvent.VK_F2 || identifier.intValue()==KeyEvent.VK_ESCAPE) {
         btnBack.doClick();
       }
      }
   }

}

class PanelScheduleTimer_btnBack_actionAdapter implements java.awt.event.ActionListener {
  PanelScheduleTimer adaptee;

  PanelScheduleTimer_btnBack_actionAdapter(PanelScheduleTimer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnBack_actionPerformed(e);
  }
}

class PanelScheduleTimer_btnOk_actionAdapter implements java.awt.event.ActionListener {
  PanelScheduleTimer adaptee;

  PanelScheduleTimer_btnOk_actionAdapter(PanelScheduleTimer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOk_actionPerformed(e);
  }
}
