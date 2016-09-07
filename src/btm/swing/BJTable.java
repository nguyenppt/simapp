package btm.swing;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import common.ExceptionHandle;
import common.*;
import java.util.Vector;
import btm.swing.*;
import java.text.*;
import java.util.*;

public class BJTable extends JTable{

  private static final int COL_HEIGHT =30 ;
  Utils ut = new Utils();
  SortableTableModel dm;
  Vector dataVector = new Vector();
  public boolean flagSortable = false;
  double[] columnclass = new double[0];
  int temp = 0;
  int unSortColumn = -1;
  int cellEditable = -1;
  Font font;
  Object[] columnNames;
  StripedTableCellRenderer str = new StripedTableCellRenderer(null, Color.lightGray, Color.white, null,null);

  public BJTable() {
    this.dm = dm;
    this.setRowHeight(COL_HEIGHT);
    this.setAutoResizeMode(this.AUTO_RESIZE_ALL_COLUMNS);
    this.getTableHeader().setReorderingAllowed(false);
    this.getTableHeader().setFont(new java.awt.Font(setFontName(),1,13));
    this.setFont(new java.awt.Font(setFontName(),0,12));
  }

  public BJTable(TableModel tbModal) {
  super(tbModal);
  this.setRowHeight(COL_HEIGHT);
  this.setAutoResizeMode(this.AUTO_RESIZE_ALL_COLUMNS);
  this.getTableHeader().setReorderingAllowed(false);
  this.getTableHeader().setFont(new java.awt.Font(setFontName(),1,13));
  this.setFont(new java.awt.Font(setFontName(),0,12));

}


  public BJTable(ResultSet rs,SortableTableModel dm) {
    super(dm);
    this.dm = dm;
    this.setRowHeight(COL_HEIGHT);
    this.setAutoResizeMode(this.AUTO_RESIZE_ALL_COLUMNS);
    this.getTableHeader().setReorderingAllowed(false);
    this.getTableHeader().setFont(new java.awt.Font(setFontName(),1,13));
    this.setFont(new java.awt.Font(setFontName(),0,12));
//    this.setColor(Color.lightGray, Color.white);
    setData(rs);
  }
  public BJTable(ResultSet rs, SortableTableModel dm, boolean isSortable){
    super(dm);
    this.dm = dm;
    this.setAutoResizeMode(this.AUTO_RESIZE_ALL_COLUMNS);
    this.getTableHeader().setReorderingAllowed(false);
    this.setRowHeight(COL_HEIGHT);
    this.setFont(new java.awt.Font(setFontName(),0,12));
    this.getTableHeader().setFont(new java.awt.Font(setFontName(),1,13));
//    this.setColor(Color.lightGray, Color.white);
    setData(rs);
    flagSortable = isSortable;
    this.getTableHeader().setReorderingAllowed(false);

  }
  public BJTable(SortableTableModel dm){
    super(dm);
    this.setAutoResizeMode(this.AUTO_RESIZE_ALL_COLUMNS);
    this.getTableHeader().setReorderingAllowed(false);
    this.setFont(new java.awt.Font(setFontName(),0,12));
    this.dm = dm;
    this.setRowHeight(COL_HEIGHT);
    this.getTableHeader().setFont(new java.awt.Font(setFontName(),1,13));
//    this.setColor(Color.lightGray, Color.white);
  }
  public BJTable(SortableTableModel dm, boolean isSortable){
    super(dm);
    this.dm = dm;
    this.setAutoResizeMode(this.AUTO_RESIZE_ALL_COLUMNS);
    this.getTableHeader().setReorderingAllowed(false);
    this.setFont(new java.awt.Font(setFontName(),0,12));
    flagSortable = isSortable;
    this.setRowHeight(COL_HEIGHT);
    this.getTableHeader().setFont(new java.awt.Font(setFontName(),1,13));
//    this.setColor(Color.green, Color.yellow);
  }
  //pull data from dataset and put it into table;
  public void setData(ResultSet rs){
    int columnCount = 0;
    Object[] tempdata;
    boolean flag = false;
    try{
      while (dm.getRowCount()>0){
        dm.removeRow(0);
      }
      columnCount = rs.getMetaData().getColumnCount();
      columnNames = new Object[columnCount];
      for (int i = 1; i<= columnCount; i ++){
        columnNames[i-1] = rs.getMetaData().getColumnName(i);
      }
      int j = 0;
      rs.beforeFirst();
      while (rs.next()){
        j +=1;
      }
      rs.beforeFirst();
      Object[] data = new Object[columnCount];
      Object[][] data1 = new Object[j][columnCount];
      int t = 0;
      while (rs.next()){
        for (int i = 1; i<= columnCount; i ++){
          int columntype = getColumnType(rs,i);
          if (columntype == 1){
            data1[t][i-1] = rs.getString(i);
          }else if (columntype == 2 ){
            data1[t][i-1] = ut.formatNumber("" + rs.getDouble(i));
            int add_data = i-1;
            dataVector.add("" + add_data);
            temp += 1;
          }else if (columntype == 3){
            data1[t][i-1] = rs.getDate(i);
          }
          if (i==1 && t == 0){
            flag = true;
          }
        }
        if (flag == true){
          columnclass = new double[temp];
          tempdata = new Object[temp];
          dataVector.copyInto(tempdata);
          for (int i=0; i<tempdata.length; i++){
            if (tempdata[i].toString() != null){
              columnclass[i] = Double.parseDouble(  tempdata[i].toString());
            }
          }
          flag = false;
        }
        t += 1;
      }
      dm.setDataVector(data1,columnNames);
      super.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      super.getTableHeader().setReorderingAllowed(false);
      super.getTableHeader().setFont(new java.awt.Font(setFontName(),1,13));
      super.setFont(new java.awt.Font(setFontName(),0,12));
      this.setColor(Color.lightGray,Color.white);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
      return;
    }
    if (flagSortable){
      sortButton();
    }
  }

  //Create sort button on header
  public void sortButton(){
    JTableHeader header = super.getTableHeader();

    SortButtonRenderer renderer = new SortButtonRenderer();
    TableColumnModel model = super.getColumnModel();
    if(columnNames == null){
      columnNames = new Object[this.getColumnCount()];
      for(int i=0;i<this.getColumnCount();i++){
        columnNames[i] = this.getColumnName(i);
      }
    }
    int n = columnNames.length;
    for (int i=0;i<n;i++) {
      model.getColumn(i).setHeaderRenderer(renderer);
//            model.getColumn(i).setPreferredWidth(columnWidth[i]);
    }
    header.addMouseListener(new HeaderListener(header,renderer,unSortColumn));
    renderer.setButtonFont(new java.awt.Font(setFontName(),1,13));

  }

  //set color
  public void setColor(Color backColor, Color foreColor){
    float[] f1 = new float[3];
    Color.RGBtoHSB(255,255,230,f1);
    Color.getHSBColor(f1[0],f1[1],f1[2]);
    //backColor foreColor
    str.installInTable(this, Color.getHSBColor(f1[0],f1[1],f1[2]) , Color.black ,null,null);
  }
  //set table header name
  public void setHeaderName(String name, int col){
    if (col <= dm.getColumnCount()){
      TableColumn tc = new TableColumn();
      tc = super.getColumn(dm.getColumnName(col));
      tc.setHeaderValue(name);

    }
  }
  //put the component into a Table
  public void setData(ResultSet rs, BJCheckBox check, int indexForValue){
    int columnCount = 0;
    Object[] tempdata;
    boolean flag = false;
    try{
      while (dm.getRowCount()>0){
        dm.removeRow(0);
      }
      columnCount = rs.getMetaData().getColumnCount();
      columnNames = new Object[columnCount + 1];
//      dm.addColumn(" ");
      columnNames[0] = " ";
      for (int i = 1; i<= columnCount; i ++){
//        dm.addColumn(rs.getMetaData().getColumnName(i));
        columnNames[i] = rs.getMetaData().getColumnName(i);
      }
      int j = 0;
      rs.beforeFirst();
      while (rs.next()){
        j +=1;
      }
      rs.beforeFirst();
      Object[][] data = new Object[j][columnCount+1];
      int t = 0;
      while (rs.next()){
        check = new BJCheckBox();
        check.setBackground(Color.white);
        check.setHorizontalAlignment(SwingConstants.CENTER);
        if (indexForValue > 0){
          check.setValue(rs.getString(indexForValue));
        }
        data[t][0] = check;
        for (int i = 1; i<= columnCount; i ++){
//          dataVector.add(rs.getString(i));
          int columntype = getColumnType(rs,i);
          if (columntype == 1){
            data[t][i] = rs.getString(i);
          }else if (columntype == 2 ){
            data[t][i] = ut.formatNumber("" + rs.getDouble(i));
//            int add_data = i;
//            dataVector.add("" + add_data);
//            temp += 1;
          }else if (columntype == 3){
            data[t][i] = rs.getDate(i);
          }
          if (i==1 && t == 0){
            flag = true;
          }
        }
//        dm.addRow(data);
        if (flag == true){
          columnclass = new double[temp];
          tempdata = new Object[temp];
          dataVector.copyInto(tempdata);
          for (int i=0; i<tempdata.length; i++){
            if (tempdata[i].toString() != null){
              columnclass[i] = Double.parseDouble(  tempdata[i].toString());
            }
          }
          flag = false;
        }
        t += 1;
      }
      dm.setDataVector(data,columnNames);
      this.setColumnWidth(0,20);
      super.getColumn(" ").setCellRenderer(new RadioButtonRenderer());
      super.getColumn(" ").setCellEditor(new CheckBoxEditor(new JCheckBox()));
      super.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      super.getTableHeader().setFont(new java.awt.Font(setFontName(),1,12));
      this.setColor(Color.lightGray,Color.white);
      setCellEditable(0);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
      return;
    }
    if (flagSortable){
      unSortColumn = 0;
      sortButton();
    }
  }
  public String formatNumberTo2DigitsDecimal(Double d){
    NumberFormat formatter = new DecimalFormat("0.00");
    String s = formatter.format(d);
    return s;
  }
  public void tableChanged(TableModelEvent e){
    super.tableChanged(e);
    repaint();
  }
  public Class getColumnClass(int col){
    if (col > 0){
      for (int i = 0; i < columnclass.length; i++) {
        if (col == columnclass[i]) {
          return Long.class;
        }
      }
//      for (int i = 0; i <columnclass1.length; i++){
//        if (col == columnclass1[i]){
//          return Date.class;
//        }
//      }
      return Object.class;
    }
    return Object.class;
  }
  public boolean isCellEditable(int row, int col){
    if (col == cellEditable){
      return true;
    }
    return false;
  }
  public void setCellEditable(int col){
    cellEditable = col;
  }
  public void setColumnWidth(int column, int width){
    TableColumn col = new TableColumn();
    col = super.getColumn(super.getColumnName(column));
    col.setPreferredWidth(width);
  }
  public void setHeader(Font font){
    JTableHeader header = new JTableHeader();
    header = super.getTableHeader();
    header.setFont(font);
    super.setTableHeader(header);
  }

  //get index of column from name
  public int getColumnInd(String columnName){
    TableColumn tc = getColumn(columnName);
    return tc.getModelIndex();
  }
//return variable type of value
  private int getColumnType(ResultSet rs,int col){
    String type;
    int result = -1;
    try{
      type = rs.getMetaData().getColumnTypeName(col);
      if (type == "VARCHAR2"){
        result = 1;
      }else if(type == "NUMBER"){
        result = 2;
      }else if(type == "DATE"){
        result = 3;
      }else{
        result = 0;
      }
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return result;
  }

  private String setFontName(){
      String strFontName="Arial";
      /*ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
      try{
          strFontName = lang.getString("FontName_Label");
      }catch(Exception ex){
          strFontName="Arial";
      }*/
      return strFontName;
  }

}
