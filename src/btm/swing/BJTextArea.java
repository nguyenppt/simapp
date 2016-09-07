package btm.swing;

import javax.swing.*;
import javax.swing.text.*;
import common.Utils;
import java.util.ResourceBundle;
import common.DataAccessLayer;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class BJTextArea extends JTextArea {

  public BJTextArea() {
    super();
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public BJTextArea(String text) {
    super(text);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public BJTextArea(int rows, int columns) {
    super(rows, columns);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public BJTextArea(String text, int rows, int columns) {
    super(text, rows, columns);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public BJTextArea(Document doc) {
    super(doc);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public BJTextArea(Document doc, String text, int rows, int columns) {
    super(doc, text, rows, columns);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Init JTextField
   * @throws Exception
   */
  private void jbInit() throws Exception {
    this.setFont(new java.awt.Font("Arial", 1, 12));
    setFontText();
  }

  private void setFontText() {
    Utils ut = new Utils();
    ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague,
        ut.sCountry);
    this.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
  }

}
