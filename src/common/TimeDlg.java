package common;


import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import com.klg.jclass.util.calendar.*;
import com.klg.jclass.util.calendar.JCDateChooser.*;
import com.klg.jclass.util.swing.JCBox;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class TimeDlg extends JDialog
{
  private Date date;
  private boolean isOKPressed;
  JCDateChooser jCDateChooser1;

    public TimeDlg(Frame theParent)
    {
        this(theParent, "Select Time", new Date(),true);
    }

    public TimeDlg(Frame theParent, Date date)
    {
        this(theParent, "Select Time", date,true);
    }

    public TimeDlg(Frame theParent, String title, Date date, boolean modal)
    {
        super(theParent, title, true);
        jCDateChooser1 = new JCDateChooser();
        this.date = date;
        try
        {
            jbInit();
            pack();
            setSize(300, 320);// or 290
            if (theParent!=null){
              Dimension d = theParent.getSize();
              Point p = theParent.getLocation();
              setLocation((d.width - getSize().width) / 2 + p.x, (d.height - getSize().height) / 2 + p.y);
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            jCDateChooser1.setValue(cal);
        }
        catch(Exception e)
        {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(e);        }
    }

    public boolean isOKPressed()
    {
        return isOKPressed;
    }

    public Date getDate()
    {
        Calendar date = jCDateChooser1.getValue();
        return date.getTime();
    }

    private void jbInit()
        throws Exception
    {
        JPanel pnlToolbar = new JPanel();
        JPanel pnlLine = new JPanel();
        JPanel pnlSouth = new JPanel();
        JPanel pnlCenter = new JPanel();
        JButton btnCancel = new JButton();
        JButton btnOK = new JButton();
        pnlCenter.setLayout(new BorderLayout());
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlCenter.setPreferredSize(new Dimension(234, 260));
        pnlSouth.setPreferredSize(new Dimension(10, 35));
        pnlSouth.setLayout(new FlowLayout(2));
        JCCalendar special_dates = new JCCalendar();
        special_dates.addSpecialDate(new com.klg.jclass.util.calendar.JCCalendar.DayOfWeek(0));
        special_dates.addSpecialDate(new com.klg.jclass.util.calendar.JCCalendar.DayOfWeek(6));
        special_dates.addSpecialDate(new com.klg.jclass.util.calendar.JCCalendar.DateMonthYear(Calendar.getInstance()));
        jCDateChooser1.setSpecialDates(special_dates);
        jCDateChooser1.setDebugGraphicsOptions(0);
    jCDateChooser1.setPreferredSize(new Dimension(224, 245));
    jCDateChooser1.setChooserType(com.klg.jclass.util.calendar.JCDateChooser.SPIN_POPDOWN);
        jCDateChooser1.addMouseListener(new TimeDlg_jCDateChooser1_mouseAdapter(this));
        pnlCenter.add(jCDateChooser1, "Center");
        btnCancel.setMnemonic('C');
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }

        });
        btnOK.setNextFocusableComponent(btnCancel);
        btnOK.setMnemonic('O');
        btnOK.setText("OK");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                isOKPressed = true;
                setVisible(false);
            }
        });
        pnlLine.setBorder(BorderFactory.createEtchedBorder());
        pnlLine.setPreferredSize(new Dimension(2, 2));
        pnlToolbar.setLayout(new BorderLayout());
        pnlSouth.add(btnOK, null);
        pnlSouth.add(btnCancel, null);
        getContentPane().add(pnlCenter, "Center");
        getContentPane().add(pnlToolbar, "South");
        pnlToolbar.add(pnlLine, "North");
        pnlToolbar.add(pnlSouth, "Center");
        getRootPane().setDefaultButton(btnOK);
    }
    void jCDateChooser1_mouseClicked(MouseEvent e) {
      if(e.getClickCount()==2){
        isOKPressed = true;
        setVisible(false);
      }
    }

}
  class TimeDlg_jCDateChooser1_mouseAdapter extends java.awt.event.MouseAdapter {
   TimeDlg adaptee;

   TimeDlg_jCDateChooser1_mouseAdapter(TimeDlg adaptee) {
     this.adaptee = adaptee;
   }
   public void mouseClicked(MouseEvent e) {
     adaptee.jCDateChooser1_mouseClicked(e);
   }
 }





