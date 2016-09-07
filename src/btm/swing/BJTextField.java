package btm.swing;

import javax.swing.JTextField;
import javax.swing.text.Document;
import java.awt.Dimension;

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
public class BJTextField extends JTextField {
    public BJTextField() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public BJTextField(String text) {
        super(text);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BJTextField(int columns) {
        super(columns);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BJTextField(String text, int columns) {
        super(text, columns);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BJTextField(Document doc, String text, int columns) {
        super(doc, text, columns);
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
        this.setFont(new java.awt.Font("Arial", 1, 12));
        setFontText();
    }

    private void setFontText() {
        Utils ut = new Utils();
        ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
        this.setFont(new java.awt.Font(lang.getString("FontName_Label"), 1, 12));
    }
}
