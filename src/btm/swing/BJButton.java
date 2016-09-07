package btm.swing;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import common.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @Last update by NghiaLe
 * @version 1.0
 */

public class BJButton extends JButton{
  public static final String DELIMITER ="(" ;

  public BJButton() {

    try {
      jbInit();
      SetStyleButton();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Init JButton
   * @throws Exception
   */
  private void jbInit() throws Exception {

    this.setFont(new java.awt.Font("Arial",1, 12));
    this.setPreferredSize(new Dimension(130, 37));
    this.setOpaque(false);
    this.setHorizontalTextPosition(0);
  }

  /**
   * Overwrite property for Jbutton
   */
  private void SetStyleButton(){

    Utils ut = new Utils();
    ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
    ImageIcon buttonUp;
    if(ut.getIsPos()){
     this.setFont(new java.awt.Font( lang.getString("FontName_Label"),1, Constant.BTN_POS_FONTSIZE));
     this.setForeground(Color.white);
     this.setPreferredSize(new Dimension(Constant.BTN_POS_WIDTH, Constant.BTN_POS_HEIGHT));
     buttonUp = new ImageIcon("images//pos.gif");
     this.setIcon(buttonUp);

   }else{

     this.setFont(new java.awt.Font(lang.getString("FontName_Label"),1, Constant.BTN_SIM_FONTSIZE));
     this.setForeground(Color.white);
     this.setPreferredSize(new Dimension(Constant.BTN_SIM_WIDTH, Constant.BTN_SIM_HEIGHT));
     buttonUp = new ImageIcon("images//sim.gif");
     this.setIcon(buttonUp);

   }
  }

  /**
   * Set text for button
   * @param s String
   */
  public  void setText(String s){

    String hotKey = " ";
    if (s.indexOf(DELIMITER) != -1) {
        hotKey = getHotKey(s.trim());
        s = s.substring(0, s.indexOf(DELIMITER));
        super.setText("<html><center><b>" + s +"</b></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                      hotKey.trim() + "</html>");
    }
    else{

      super.setText(s);

    }
  }

  /**
   * Get hot key
   * @param s String
   * @return String
   */
  String getHotKey(String s){
     if(s.equals("")) return "";
    int i=0;
    try{
      while (s.charAt(i) != DELIMITER.charAt(0))
        i++;
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);}
   String sHotkey= s.substring(i+1,s.length()-1);
   return sHotkey;
  }


}
