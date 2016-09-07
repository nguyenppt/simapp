package btm.swing;

import javax.swing.*;
import common.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @Update by NghiaLe
 * @version 1.0
 */

public class BJLabel extends JLabel{

    boolean  isHeaderLable = false;

    public BJLabel() {

        try {
            jbInit();
            setStyleLabel();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public BJLabel(boolean isHeaderLable) {

        try {
            jbInit();
            this.isHeaderLable = isHeaderLable;
            setStyleLabel();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Init
     * @throws Exception
     */
    private void jbInit() throws Exception {

        this.setFont(new java.awt.Font("Arial", 1, 12));
    }


    /**
     * Overwrite property of lable
     */
    private void setStyleLabel() {
        Utils ut = new Utils();
        ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague,
                ut.sCountry);
        if (isHeaderLable) {
            if (Constant.POS_MODULE) {
                this.setFont(new java.awt.Font(lang.getString("FontName_Label"),
                                               1, 17));

            } else {

                this.setFont(new java.awt.Font(lang.getString("FontName_Label"),
                                               1, 17));
            }
        } else {

            if (Constant.POS_MODULE) {
                this.setFont(new java.awt.Font(lang.getString("FontName_Label"),
                                               1, Constant.LBL_POS_FONTSIZE));

            } else {

                this.setFont(new java.awt.Font(lang.getString("FontName_Label"),
                                               1, Constant.LBL_SIM_FONTSIZE));

            }
        }
    }
}
