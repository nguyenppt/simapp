/*
 * Created on Jul 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pos;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author nghi.doan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DecimalRenderer extends DefaultTableCellRenderer {
	DecimalFormat formatter;
	DecimalRenderer(String pattern) {
		this(new DecimalFormat(pattern));
	}
	DecimalRenderer(DecimalFormat formatter) {
		this.formatter = formatter;
		setHorizontalAlignment(JLabel.RIGHT);
	}
	public void setValue(Object value) {
		setText((value == null) ? ""
				: formatter.format(((Double)value).doubleValue()));

	}
}