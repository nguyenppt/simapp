package btm.swing;

import javax.swing.JPasswordField;
import javax.swing.text.Document;

import common.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghia Le
 * @version 1.0
 */
public class BJPasswordField extends JPasswordField {
    public BJPasswordField() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BJPasswordField(String text) {
        super(text);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BJPasswordField(int columns) {
        super(columns);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BJPasswordField(String text, int columns) {
        super(text, columns);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BJPasswordField(Document doc, String txt, int columns) {
        super(doc, txt, columns);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Init JTextField
     * @throws Exception
     */
    private void jbInit() throws Exception {
        this.setFont(new java.awt.Font("Arial", 0, 12));
        setFontText();
    }

    private void setFontText() {
        Utils ut = new Utils();
        ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague,
                ut.sCountry);
        this.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
    }

}
