package btm.swing;

/**
 * @author nghi.doan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt. *;
import javax.swing. *;
/**
* @version 1.0 02/26/99
*/
public class BlankIcon implements Icon {
  private Color fillColor;
  private int size;
  public BlankIcon() {
    this(null, 11);
  }
  public BlankIcon(Color color, int size) {
    fillColor = color;
    this.size = size;
  }
  public void paintIcon(Component c, Graphics g, int x, int y) {
    if (fillColor != null) {
      g.setColor(fillColor);
      g.drawRect(x, y, size-1, size-1);
    }
  }
  public int getIconWidth() {
    return size;
  }
  public int getIconHeight() {
    return size;
  }
}
