//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//































package pos;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PanelAddStockCount extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanelSub = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanelSubNorth = new JPanel();
  JLabel jLabelDate = new JLabel();
  JTextField txtDate = new JTextField();
  JPanel jPanelSubCenter = new JPanel();
  JPanel jPanelTableNorth = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanelBuf = new JPanel();
  JPanel jPanelMes = new JPanel();
  JLabel jLabelMes = new JLabel();
  JPanel jPanelTableCenter = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTableStockCount = new JTable();
  TitledBorder titledBorder1;

  public PanelAddStockCount() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
//    System.out.println("N4");
    titledBorder1 = new TitledBorder("");
    this.setLayout(borderLayout1);
    jPanelSub.setLayout(borderLayout2);
    jPanelSubNorth.setPreferredSize(new Dimension(100, 100));
    jPanelSubNorth.setLayout(null);
    jLabelDate.setFont(new java.awt.Font("SansSerif", 1, 12));
    jLabelDate.setText("Enter Date (DD/MM/YYYY)");
    jLabelDate.setBounds(new Rectangle(23, 13, 369, 29));
    txtDate.setText("");
    txtDate.setBounds(new Rectangle(21, 48, 430, 38));
    jPanelTableNorth.setPreferredSize(new Dimension(100, 50));
    jPanelTableNorth.setLayout(borderLayout4);
    jPanelSubCenter.setLayout(borderLayout3);
    jPanelBuf.setMinimumSize(new Dimension(10, 10));
    jPanelBuf.setPreferredSize(new Dimension(100, 10));
    jLabelMes.setText("Mesasdf askfj lksadjf");
    jLabelMes.setFont(new java.awt.Font("SansSerif", 1, 14));
    jPanelTableCenter.setLayout(borderLayout5);
    jScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());
    jScrollPane1.addKeyListener(new PanelAddStockCount_jScrollPane1_keyAdapter(this));
    this.add(jPanelSub,  BorderLayout.CENTER);
    jPanelSub.add(jPanelSubNorth, BorderLayout.NORTH);
    jPanelSubNorth.add(jLabelDate, null);
    jPanelSubNorth.add(txtDate, null);
    jPanelSub.add(jPanelSubCenter,  BorderLayout.CENTER);
    jPanelSubCenter.add(jPanelTableNorth, BorderLayout.NORTH);
    jPanelTableNorth.add(jPanelBuf,  BorderLayout.WEST);
    jPanelTableNorth.add(jPanelMes,  BorderLayout.CENTER);
    jPanelMes.add(jLabelMes, null);
    jPanelSubCenter.add(jPanelTableCenter,  BorderLayout.CENTER);
    jPanelTableCenter.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTableStockCount, null);
  }

  void jScrollPane1_keyPressed(KeyEvent e) {

  }
}

class PanelAddStockCount_jScrollPane1_keyAdapter extends java.awt.event.KeyAdapter {
  PanelAddStockCount adaptee;

  PanelAddStockCount_jScrollPane1_keyAdapter(PanelAddStockCount adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jScrollPane1_keyPressed(e);
  }
}
