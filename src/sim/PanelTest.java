package sim;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import common.*;
import java.awt.event.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelTest extends JPanel {
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  DataAccessLayer DAL;
  FrameMainSim frmMain;
  FlowLayout flowLayout1 = new FlowLayout();

  public PanelTest(FrameMainSim frmMain) {
    try {
      this.frmMain = frmMain;
      this.DAL = frmMain.getDAL();
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    jButton1.setText("jButton1");
    jButton1.addActionListener(new PanelTest_jButton1_actionAdapter(this));
    this.setLayout(flowLayout1);
    jButton2.setText("jButton2");
    jButton2.addActionListener(new PanelTest_jButton2_actionAdapter(this));
    this.add(jButton1, null);
    this.add(jButton2, null);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    ResultSet rs = null;
    String sql = "select * from btm_im_employee";
    try{
      rs = DAL.executeQueries(sql);
      while (rs.next()){
//        System.out.println("Test connection " + rs.getString(1));
      }
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }

  void jButton2_actionPerformed(ActionEvent e) {
    DAL.getOracleConnect();
//    System.out.println("Error and reconnect");
  }
}

class PanelTest_jButton1_actionAdapter implements java.awt.event.ActionListener {
  PanelTest adaptee;

  PanelTest_jButton1_actionAdapter(PanelTest adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class PanelTest_jButton2_actionAdapter implements java.awt.event.ActionListener {
  PanelTest adaptee;

  PanelTest_jButton2_actionAdapter(PanelTest adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}
