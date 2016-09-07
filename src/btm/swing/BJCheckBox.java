package btm.swing;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */
import javax.swing.*;


public class BJCheckBox extends JCheckBox {
  private String value = "";
  public BJCheckBox() {
  }
  public String getValue(){
    if (super.isSelected()){
      return value;
    }
    return null;
  }
  public void setValue(String value){
    this.value = value;
  }

}