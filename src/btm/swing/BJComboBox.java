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
import java.sql.*;
import common.*;
import java.util.ResourceBundle;

public class BJComboBox extends JComboBox {

  String[] id_data;
  public BJComboBox() {
      try {
        jbInit();
      }
      catch (Exception e) {
        e.printStackTrace();
    }
  }

  /**
   * Set data
   * @param rs ResultSet
   */
  public void setData(ResultSet rs){
    int i=0;
    try{
      int columncount = rs.getMetaData().getColumnCount();
      if (super.getItemCount() > 0){
        super.removeAllItems();
      }
      if (columncount == 2){
        while (rs.next()) {
          i += 1;
        }
        id_data = new String[i];
        i = 0;
        rs.beforeFirst();
        while (rs.next()) {
          id_data[i] = rs.getString(1);
          super.addItem(rs.getString(2));
          i += 1;
        }
      }
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /**
   * for new sale
   * @param rs ResultSet
   */
  public void setData1(ResultSet rs){
    int i=0;
    try{
      int columncount = rs.getMetaData().getColumnCount();
      if (super.getItemCount() > 0){
        super.removeAllItems();
      }
      if (columncount == 2){
        while (rs.next()) {
          i += 1;
        }
        id_data = new String[i+1];
        super.addItem("None");
        id_data[0] = "";
        i=1;
        rs.beforeFirst();
        while (rs.next()) {
          id_data[i] = rs.getString(1);
          super.addItem(rs.getString(2));
          i += 1;
        }
      }
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /**
   * for new sale
   * @param rs ResultSet
   */
  public void setData1(DataSourceVector rs){
    int i=0;
    try{
      int columncount = rs.getNumberOfCoulumns();
      int rowCount = rs.getNumberOfRows();
      if (super.getItemCount() > 0){
        super.removeAllItems();
      }
      if (columncount == 2){
          id_data = new String[rowCount + 1];
          super.addItem("None");
          id_data[0] = "";
          for (i = 0; i < rowCount; i++) {
              id_data[i + 1] = rs.getValueAt(i, 0).toString();
              super.addItem(rs.getValueAt(i, 1).toString());
          }
      }
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }


  /**
   *
   * @param rs ResultSet
   */
  public void setData2(ResultSet rs){
    int i=0;
    try{
      int columncount = rs.getMetaData().getColumnCount();
      if (super.getItemCount() > 0){
        super.removeAllItems();
      }
      if (columncount == 2){
        while (rs.next()) {
          i += 1;
        }
        id_data = new String[i+1];
        super.addItem("All");
        id_data[0] = "";
        i=1;
        rs.beforeFirst();
        while (rs.next()) {
          id_data[i] = rs.getString(1);
          super.addItem(rs.getString(2));
          i += 1;
        }
      }
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /**
   * get selected data
   * @return String
   */
  public String getSelectedData(){
    String result = null;
    result = id_data[super.getSelectedIndex()];
    return result;
  }

  /**
   * set selected data base on id
   * data : id of element
   * @param data String
   */
  public void setSelectedData(String data){
    for (int i = 0; i<id_data.length; i++){
      if (id_data[i].equalsIgnoreCase(data)){
        super.setSelectedIndex(i);
        break;
      }
    }
  }

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
