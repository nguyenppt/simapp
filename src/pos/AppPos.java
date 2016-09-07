package pos;

import javax.swing.UIManager;
import java.awt.*;
import btm.attr.*;
import common.*;
import java.sql.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @version 1.0
 */

public class AppPos {
  boolean packFrame = false;

  //Construct the application
  public AppPos() {
    DataAccessLayer DAL = new DataAccessLayer();
    DAL.getConnfigParameter();
    Utils ut = new Utils();
    SystemClass.objDAL = DAL;
    SystemClass.storeID = DAL.getStoreID(); //Set store ID
    SystemClass.lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);


        FrameLogIn frame = new FrameLogIn();
        //DemoForm frame = new DemoForm();

        //Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
          frame.pack();
        }
        else {
          frame.validate();
        }
        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
          frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
          frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);


    //End Nghi

  }
  //Main method
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    Utils.isPos=true;
    new AppPos();
  }
}
