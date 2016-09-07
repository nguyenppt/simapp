package btm.swing;

import java.awt.*;
import javax.swing.*;
import common.*;
import javax.swing.border.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @version 1.0
 */

public class BJPanel extends JPanel {
  Border border;
  boolean isBorder=false;
  int pnlType= Constant.PANEL_TYPE_DEFAULT;
  public BJPanel() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  public BJPanel(boolean isBorder) {
      try {
          this.isBorder=isBorder;
          jbInit();
      }
      catch(Exception ex) {
        ex.printStackTrace();
      }
  }


  public BJPanel(int pnlType) {
    try {
        this.pnlType=pnlType;
        jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
}
  public BJPanel(int pnlType,boolean isBorder) {
    try {
        this.pnlType=pnlType;
        this.isBorder=isBorder;
        jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
}

  void jbInit() throws Exception {
  if(pnlType == Constant.PANEL_TYPE_DEFAULT){
    this.setBackground(new Color(Constant.PANEL_RED_DEFAULT,
                                 Constant.PANEL_GREEN_DEFAULT,
                                 Constant.PANEL_BLUE_DEFAULT));
  }
  else if (pnlType == Constant.PANEL_TYPE_HEADER){

    this.setBackground(new Color(Constant.PANEL_RED_HEADER,
                                 Constant.PANEL_GREEN_HEADER,
                                 Constant.PANEL_BLUE_HEADER));
      }
    if(isBorder){
        border = BorderFactory.createEtchedBorder(new Color(Constant.PANEL_HIGHLIGH_RED, Constant.PANEL_HIGHLIGH_GREEN, Constant.PANEL_HIGHLIGH_BLUE),new Color(Constant.PANEL_SHADOW_RED, Constant.PANEL_SHADOW_GREEN, Constant.PANEL_SHADOW_BLUE));
        this.setBorder(border);
    }
  }

}
