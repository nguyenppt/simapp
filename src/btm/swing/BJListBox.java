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
import java.util.Vector;

public class BJListBox extends JList {
  DataAccessLayer DAL = new DataAccessLayer();
  Vector id_temp = new Vector();
  Vector id_data = new Vector();
  Vector name_data = new Vector();
  public BJListBox() {
  }
  public void setData(ResultSet rs){
    int i=0;
    try{
      int columncount = rs.getMetaData().getColumnCount();
      if (columncount == 2){
//        while (rs.next()) {
//          i += 1;
//        }
//        id_data = new String[i];
//        name_data = new String[i];
        i = 0;
        rs.beforeFirst();
        id_data.clear();
        id_temp.clear();
        name_data.clear();
        while (rs.next()) {
          id_data.addElement(rs.getString(1));
          id_temp.addElement(rs.getString(1));
          name_data.addElement(rs.getString(2));
//          super.addItem(rs.getString(2));
          i += 1;
        }
        super.setListData(name_data);
      }
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
  public String getSelectedData(){
    String result = null;
    if (!id_data.isEmpty()){
      result = id_data.elementAt(super.getSelectedIndex()).toString();
    }
    return result;
  }
  public String[] getSelectedDatas(){
    if(!id_data.isEmpty()){
      int[] selected = super.getSelectedIndices();
      String[] result = new String[selected.length];
      for (int i = 0; i < selected.length; i++) {
        result[i] = id_data.elementAt(selected[i]).toString();
      }
      return result;
    }
    return null;
  }
//  public String[] getSelectedDatas(){
//    int[] selected = super.getSelectedIndices();
//    String[] result = new String[selected.length];
//    for(int i = 0; i<selected.length; i++){
//      result[i] = id_data.elementAt(selected[i]).toString();
//    }
//    return result;
//  }

  public void removeIndex(int[] index){
    int[] selected = index;//super.getSelectedIndices();
    for (int i = selected.length - 1 ; i>=0; i--){
//      System.out.println("Nghi: " + selected[i]);
      name_data.removeElementAt(selected[i]);
      id_data.removeElementAt(selected[i]);
    }
    super.setListData(name_data);
  }
  ////=====================================================================
  public Vector getAllData(){
    return name_data;
  }
  public Vector getAllID(){
    return id_data;
  }
  public void addData(String[] data, String[] id){
    for (int i = 0; i < data.length; i++) {
      name_data.addElement(data[i]);
      id_data.addElement(id[i]);
    }
    super.setListData(name_data);
  }
  public void addMoreData(String[] data, String[] id){
    boolean flag = false;
    if (name_data.isEmpty()){
      return;
    }else{
      for (int i = 0; i < id.length; i++){
        for (int j = 0 ; j < id_temp.size(); j++){
          if (id[i].equalsIgnoreCase(id_temp.elementAt(j).toString())){
            if (j > 0){
              for (int k = 0; k<id_data.size(); k++){
                if (id_temp.elementAt(j-1).toString().equalsIgnoreCase(id_data.elementAt(k).toString())){
                  name_data.insertElementAt(data[i], k+1);
                  id_data.insertElementAt(id[i], k+1);
                  flag = true;
                  break;
                }
              }
            }else{
              name_data.insertElementAt(data[i], id_temp.indexOf(id[i]));
              id_data.insertElementAt(id[i], id_temp.indexOf(id[i]));
              flag = true;
              break;
            }
          }
        }
        if (flag == false){
          name_data.addElement(data[i]);
          id_data.addElement(id[i]);
          id_temp.addElement(id[i]);
        }else{
          flag = false;
        }
      }
    }
    super.setListData(name_data);
  }
  //================================================
  public void addData(Vector data, Vector id){
    for (int i = 0; i < data.size(); i++) {
      name_data.addElement(data.elementAt(i));
      id_data.addElement(id.elementAt(i));
    }
    super.setListData(name_data);
  }
  public void addMoreData(Vector data, Vector id){
    boolean flag = false;
    if (name_data.isEmpty()){
      return;
    }else{
      for (int i = 0; i < id.size(); i++){
        for (int j = 0 ; j < id_temp.size(); j++){
          if (id.elementAt(i).toString().equalsIgnoreCase(id_temp.elementAt(j).toString())){
            if (j > 0){
              for (int k = 0; k<id_data.size(); k++){
                if (id_temp.elementAt(j-1).toString().equalsIgnoreCase(id_data.elementAt(k).toString())){
                  name_data.insertElementAt(data.elementAt(i), k+1);
                  id_data.insertElementAt(id.elementAt(i), k+1);
                  flag = true;
                  break;
                }
              }
            }else{
              name_data.insertElementAt(data.elementAt(i),id_temp.indexOf(id.elementAt(i)));
              id_data.insertElementAt(id.elementAt(i), id_temp.indexOf(id.elementAt(i)));
              flag = true;
              break;
            }
          }
        }
        if (flag == false){
          name_data.addElement(data.elementAt(i));
          id_data.addElement(id.elementAt(i));
          id_temp.addElement(id.elementAt(i));
        }else {
          flag = false;
        }
      }
    }
    super.setListData(name_data);
  }
  ///====================================================
  public void addData(Object[] data, String[] id){
    for (int i = 0; i < data.length; i++) {
      name_data.addElement(data[i]);
      id_data.addElement(id[i]);
    }
   super.setListData(name_data);
  }
  public void addMoreData(Object[] data, String[] id){
    boolean flag = false;
    if (name_data.isEmpty()){
      return;
    }else{
      for (int i = 0; i < id.length; i++){
        for (int j = 0 ; j < id_temp.size(); j++){
          if (id[i].equalsIgnoreCase(id_temp.elementAt(j).toString())){
            if (j > 0){
              for (int k = 0; k<id_data.size(); k++){
                if (id_temp.elementAt(j-1).toString().equalsIgnoreCase(id_data.elementAt(k).toString())){
                  name_data.insertElementAt(data[i], k+1);
                  id_data.insertElementAt(id[i], k+1);
                  flag = true;
                  break;
                }
              }
            }else{
              name_data.insertElementAt(data[i], id_temp.indexOf(id[i]));
              id_data.insertElementAt(id[i], id_temp.indexOf(id[i]));
              flag = true;
              break;
            }
          }
        }
        if (flag == false){
          name_data.addElement(data[i]);
          id_data.addElement(id[i]);
          id_temp.addElement(id[i]);
        }else{
          flag = false;
        }
      }
    }
//    for (int i=0; i<data.length; i++){
//      name_data.addElement(data[i]);
//      id_data.addElement(id[i]);
//    }
    super.setListData(name_data);
  }
  //=======================================================================
  public int getRowCount(){
    return name_data.size();
  }
  ////////////=============================================================
  public void removeIndexAll(){
    name_data.clear();
    id_data.clear();
    super.setListData(name_data);
  }


}
