package common;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import btm.swing.*;
//import
import java.util.*;

/**
 * Title:
 * Description: Dialog to show message or confirm
 * Copyright:    Copyright (c) 2005
 * Company:
 * @author
 * @version 1.0
 */

public class DialogMessage extends JDialog {
  //message type
   public static final int ERROR_MESSAGE =0 ;
   public static final int INFORMATION_MESSAGE  =1 ;
   public static final int WARNING_MESSAGE  =2 ;
   public static final int QUESTION_MESSAGE  =3 ;
   public static final int PLAIN_MESSAGE  =4 ;
   //option type
   public static final int YES_NO_OPTION =0 ;
   public static final int YES_NO_CANCEL_OPTION  =1 ;
   public static final int OK_CANCEL_OPTION  =2 ;
   public static final int OK_OPTION  =3 ;
   public static final int YES_YES_ALL_OPTION  =4 ;//yes, yes to all
   public static final int YES_ALL_NO_ALL_OPTION  =5 ;//yes, yes to all,no , no to all

   //selected VALUE
   public static final int YES_VALUE =0 ;
   public static final int NO_VALUE  =1 ;
   public static final int CANCEL_VALUE  =2 ;
   public static final int YES_ALL_VALUE  =3 ;// yes to all
   public static final int NO_ALL_VALUE  =4 ;// no to all

   Utils ut = new Utils();
   //ResourceBundle lang;
   DataAccessLayer DAL;

   ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);

  BJPanel panel1 = new BJPanel(Constant.PANEL_TYPE_DEFAULT);
  BorderLayout borderLayout1 = new BorderLayout();
  BJPanel jPanel1 = new BJPanel(Constant.PANEL_TYPE_DEFAULT);
  BJPanel jPanel2 = new BJPanel(Constant.PANEL_TYPE_DEFAULT);
  BJButton btnNo = new BJButton();
  BJButton btnYes = new BJButton();
  BJButton btnCancel = new BJButton();

  ImageIcon errorIco;
  ImageIcon infoIco;
  ImageIcon warningIco;
  ImageIcon questIco;

  String title="";
  String message="";
  int optionType=OK_OPTION;
  int messageType=INFORMATION_MESSAGE;


  int selectValue=1;
  BorderLayout borderLayout2 = new BorderLayout();
  BJPanel jPanel3 = new BJPanel(Constant.PANEL_TYPE_DEFAULT);
  JLabel iconjLabel = new JLabel();
  BJPanel jPanel4 = new BJPanel(Constant.PANEL_TYPE_DEFAULT);
  JTextPane jTextPane1 = new JTextPane();
  BJButton btnYesAll = new BJButton();
  BJButton btnNoAll = new BJButton();// 0:Ok 1:Non  2:Annuler

  public DialogMessage(Frame frame, String title, boolean modal,String message, int  messageType,int optionType) {
    super(frame, title, modal);
    try {
      this.title=title;
      this.message= message;
      this.messageType= messageType;
      this.optionType=optionType;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace() ;
      //JOptionPane.showMessageDialog(null, "in DialogMessage it happens a mistake: 1-"+ ex.toString());
      JOptionPane.showMessageDialog(null, lang.getString("MS039_ErrorSys") );
    }
  }


  void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    //DAL.getConnfigParameter();
    lang = DAL.getFrameLabel(ut.sLanguague, ut.sCountry);
    btnNo.setPreferredSize(new Dimension(100, 37));
    btnNo.setSelected(false);
    btnNo.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnNo.setText(lang.getString("No"));
    btnNo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnNo_actionPerformed(e);
      }
    });
    btnYes.setPreferredSize(new Dimension(100, 37));
    btnYes.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnYes.setText(lang.getString("Yes"));
    btnYes.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnYes_actionPerformed(e);
      }
    });
    jPanel1.setLayout(borderLayout2);
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowOpened(WindowEvent e) {
        this_windowOpened(e);
      }
    });
    btnCancel.setPreferredSize(new Dimension(100, 37));
    btnCancel.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnCancel.setText(lang.getString("Cancel"));
    btnCancel.addActionListener(new ActionListener(this));
    btnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnCancel_actionPerformed(e);
      }
    });
    this.setResizable(false);
    jPanel1.setBackground(new Color(121, 152, 198));
    jTextPane1.setText(message);
    iconjLabel.setIcon(warningIco);
    iconjLabel.setBounds(new Rectangle(32, 2, 38, 29));
    jPanel4.setBackground(new Color(208, 209, 204));
    jPanel4.setPreferredSize(new Dimension(10, 30));
    jTextPane1.setBackground(new Color(208, 209, 204));
    jTextPane1.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 13));
    jTextPane1.setEditable(false);
    jTextPane1.setText("ff");
    jTextPane1.setBounds(new Rectangle(78, 0, 256, 77));
    jPanel3.setLayout(null);
    btnYesAll.setPreferredSize(new Dimension(100, 37));
    btnYesAll.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnYesAll.setText(lang.getString("YesToAll"));
    btnYesAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnYesAll_actionPerformed(e);
      }
    });
    btnNoAll.setPreferredSize(new Dimension(100, 37));
    btnNoAll.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    btnNoAll.setText(lang.getString("NoToAll"));
    btnNoAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnNoAll_actionPerformed(e);
      }
    });
    jPanel3.setBackground(new Color(208, 209, 204));
    jPanel2.setBackground(new Color(208, 209, 204));
    getContentPane().add(panel1);
    panel1.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(jPanel3,  BorderLayout.CENTER);
    jPanel3.add(jTextPane1, null);
    jPanel3.add(iconjLabel, null);
    jPanel1.add(jPanel4, BorderLayout.NORTH);
    panel1.add(jPanel2,  BorderLayout.SOUTH);
    jPanel2.add(btnYes, null);
    jPanel2.add(btnYesAll, null);
    jPanel2.add(btnNo, null);
    jPanel2.add(btnNoAll, null);
    jPanel2.add(btnCancel, null);
    questIco=new ImageIcon("Images//quest.jpg");
    errorIco=new ImageIcon("Images//error.jpg");
    infoIco=new ImageIcon("Images//infor.jpg");
    warningIco=new ImageIcon("Images//warning.jpg");

    this.setTitle(title) ;
    this.setSize(new Dimension(363, 164)) ;
    btnNo.requestFocus() ;
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    if (frameSize.height > screenSize.height) {
       frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
       frameSize.width = screenSize.width;
    }
    this.setLocation((screenSize.width - frameSize.width) / 2,
      (screenSize.height - frameSize.height) / 2);
  }

  //show message and buttons
  void showData(){
      jTextPane1.setText(message);
      this.setTitle(title);
      //show message icon
      if (messageType == ERROR_MESSAGE) {
          iconjLabel.setIcon(errorIco);
      } else if (messageType == INFORMATION_MESSAGE) {
          iconjLabel.setIcon(infoIco);
      } else if (messageType == WARNING_MESSAGE) {
          iconjLabel.setIcon(warningIco);
      } else if (messageType == QUESTION_MESSAGE) {
          iconjLabel.setIcon(questIco);
      }
      //show button
      if (optionType == YES_NO_OPTION) {
          btnCancel.setVisible(false);
          btnYesAll.setVisible(false);
          btnNoAll.setVisible(false);
      } else if (optionType == YES_NO_CANCEL_OPTION) {
          btnYesAll.setVisible(false);
          btnNoAll.setVisible(false);
      } else if (optionType == OK_CANCEL_OPTION) {
          btnYes.setText(lang.getString("OK"));
          btnNo.setVisible(false);
          btnYesAll.setVisible(false);
          btnNoAll.setVisible(false);
      } else if (optionType == YES_YES_ALL_OPTION) {
          btnNo.setVisible(false);
          btnNoAll.setVisible(false);
      } else if (optionType == YES_ALL_NO_ALL_OPTION) {

      } else if (optionType == OK_OPTION) {
          btnYes.setText(lang.getString("OK"));
          btnNo.setVisible(false);
          btnCancel.setVisible(false);
          btnYesAll.setVisible(false);
          btnNoAll.setVisible(false);
      }
}

  //show data
  void this_windowOpened(WindowEvent e) {
     btnYes.requestFocus() ;
     showData();
  }
  //get select value
  public int getselectValue(){
     return selectValue;
  }

  void btnYes_actionPerformed(ActionEvent e) {
    selectValue=YES_VALUE;
    this.dispose() ;
  }

  void btnNo_actionPerformed(ActionEvent e) {
    selectValue=NO_VALUE;
    this.dispose() ;
  }
  void btnCancel_actionPerformed(ActionEvent e) {
    selectValue=CANCEL_VALUE;
    this.dispose() ;
  }


  void btnYesAll_actionPerformed(ActionEvent e) {
      selectValue=YES_ALL_VALUE;
      this.dispose() ;
  }

  void btnNoAll_actionPerformed(ActionEvent e) {
      selectValue=NO_ALL_VALUE;
      this.dispose() ;
  }
}

class ActionListener implements java.awt.event.ActionListener {
  DialogMessage adaptee;

  ActionListener(DialogMessage adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}
