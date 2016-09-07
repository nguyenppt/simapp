package common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author: NghiaLe
 * @version 1.0
 */
public class DataSourceVector {

    //Status of row in data
    private final int IS_NO_CHANGE = 0;
    private final int IS_MODIFIED = 1;
    private final int IS_INSERTED = 2;
    private final int IS_DELETED = 3;

    //private Vector rows;
    private Vector data = new Vector();
    private Vector columnNames = new Vector();//To store columns header of data
    private int[] columnsAreNum;

    private Vector dataDeleted = new Vector();//To store these datas were removed from data

    private boolean connectedToDatabase = false;// keep track of database connection status
    private boolean deletedStatus = false;//is true in case data has a deleted row
    private boolean updatedStatus = false;//is true in case data has a updated row
    private boolean insertStatus = false;//is true in case data has a inserted row
    private boolean flagAddStatusRow = true;

    //private int numberOfRows = 0;
    private int numberOfcolumns = 0;



    /**
     * Contructor
     */
    public DataSourceVector(){
    }

    /**
     * init DataSource vectors
     * @param cnn Connection
     * @param sql String
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DataSourceVector(Connection cnn, String sql) throws SQLException,
            ClassNotFoundException, SQLException{

        connectedToDatabase = true;
        Connection connection = cnn;
        Statement statement = connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );

        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData metadata = resultSet.getMetaData();
        numberOfcolumns = metadata.getColumnCount();

        setColumnName(metadata);
        setData(resultSet);

        statement.close();
    }

    /**
     * Contructor
     * @param header Vector
     * @param data Vector
     * @throws IllegalArgumentException
     */
    public DataSourceVector (Vector header, Vector data)throws
            IllegalArgumentException {
      this.data = data;
      this.columnNames = header;

      numberOfcolumns = header.size();

      if (flagAddStatusRow) {
        for (int row = 0; row < data.size(); row++) {
          ( (Vector) data.elementAt(row)).add(numberOfcolumns,
                                              new Integer(IS_NO_CHANGE));
        }
      } else {
        for (int row = 0; row < data.size(); row++) {
          ((Vector) data.elementAt(row)).setElementAt(
              new Integer(IS_NO_CHANGE), numberOfcolumns);
        }
      }
    }

    /**
     * Set value for column name
     * @throws SQLException
     */
    private void setColumnName(ResultSetMetaData metadata) throws SQLException {
      if (connectedToDatabase) {
        for (int i = 1; i <= numberOfcolumns; i++) {
          columnNames.add(i - 1, metadata.getColumnName(i));
        }
        /**
         * create new column to handle status of row. You can hide or visible it by comment or not the line code follow.
         */
        //columnNames.add("RecordStatus");
      }
    }

    /**
     * Set value for column name
     * @param header Vector
     */
    public void setColumnName(Vector header) {
        numberOfcolumns = header.size();
        columnNames = header;
        /**
         * create new column to handle status of row. You can hide or visible it by comment or not the line code follow.
         */
        //columnNames.add("RecordStatus");
    }


    /**
     * copy data from resultset to vector
     * @throws SQLException
     * @throws IllegalStateException
     */
    private void setData(ResultSet resultSet)
            throws SQLException, IllegalStateException {
        if(connectedToDatabase){
            int numberOfRows = 0;
            // determine number of rows in ResultSet
            resultSet.last(); // move to last row
            data.clear();
            numberOfRows = resultSet.getRow();
            for (int row = 0; row < numberOfRows; row++) {
                resultSet.absolute(row +1);
                Vector rows = new Vector();
                for (int col = 0; col < numberOfcolumns; col++) {
                  if(resultSet.getObject(col + 1) != null){
                    rows.add(col, resultSet.getObject(col + 1));
                  }else{
                    rows.add(col, new String(" "));
                  }
                }
                //Set status to row
                if (flagAddStatusRow) {
                  rows.add(numberOfcolumns, new Integer(IS_NO_CHANGE)); //Set status for row is add new
                }
                else {
                  rows.setElementAt(new Integer(IS_NO_CHANGE), numberOfcolumns);
                }

                //Add row to data
                data.add(row, rows);
            }
        }
    }

    /**
     * copy data from vector to vector
     * @param data Vector
     */
    public void setData(Vector data){
      this.data = data;
      //Set status to row
      if (flagAddStatusRow) {
        for (int row = 0; row < data.size(); row++) {
          ( (Vector) data.elementAt(row)).add(numberOfcolumns,
                                              new Integer(IS_NO_CHANGE));
        }
      } else {
        for (int row = 0; row < data.size(); row++) {
          ((Vector) data.elementAt(row)).setElementAt(
              new Integer(IS_NO_CHANGE), numberOfcolumns);
        }
      }

      deletedStatus = false;
      insertStatus = false;
      updatedStatus = false;
    }

    /**
     * get/set method
     * @return Vector
     */
    public Vector getData(){ return data; }
    public Vector getColumnName() {
      if(columnNames.size() == 0 && data.size() > 0){
        for(int i =0; i< ((Vector)data.elementAt(0)).size(); i++){
          columnNames.add(new  Integer(i));
        }
      }
      return columnNames;
    }
    public boolean hasChange(){ return (insertStatus ||
                updatedStatus || deletedStatus); }
    public boolean hasDelete(){ return deletedStatus; }
    public boolean hasUpdate(){ return updatedStatus; }
    public boolean hasInsert(){ return insertStatus; }
    public void setColumnsAreNum(int[] cols){columnsAreNum = cols;}


    /**
     * Set value at cell
     * @param value Object
     * @param row int
     * @param col int
     * @throws IllegalStateException
     */
    public void setValueAt(Object value, int row, int col)
            throws IllegalStateException {

        if(columnsAreNum != null && columnsAreNum.length>0){
	  for(int i =0; i< columnsAreNum.length; i++){
	    if(col == columnsAreNum[i]){
	      value = new Double(tranferStringToNum(value.toString()));
	      break;
	    }
	  }
	}
        //Set value at cell
        ( (Vector) data.elementAt(row)).setElementAt(value, col);
        //Set status modify to this row.
        if (!(Integer.parseInt( ( (Vector) data.elementAt(row)).elementAt
                                (numberOfcolumns).toString()) == IS_INSERTED)) {
          ((Vector) data.elementAt(row)).setElementAt(new Integer(IS_MODIFIED),
                                                       numberOfcolumns);
          this.updatedStatus = true;
        }
    }


    /**
     * Add new row to data
     * @param row Vector
     * @throws IllegalStateException
     */
    public void addNewRow(Vector row) throws IllegalStateException{
        if(flagAddStatusRow){
          row.add(numberOfcolumns, new Integer(IS_INSERTED)); //Set status for row is add new
        }else{
          row.setElementAt(new Integer(IS_NO_CHANGE), numberOfcolumns);
        }
        data.add(row);
    }

    /**
     * Get value at cell
     * @param row int
     * @param col int
     * @return Object
     */
    public Object getValueAt(int row, int col) throws Exception{
      Object obj = null;
      if(data!= null){
	obj = ((Vector) data.elementAt(row)).elementAt(col);
      }
      return obj;
    }

    /**
     * Get row at
     * @param row int
     * @return Vector
     * @throws Exception
     */
    public Vector getRow(int row) throws Exception{
      Vector vr = null;
      if(data!=null){
        vr = (Vector)data.elementAt(row);
      }
      return vr;
    }

    /**
     * get rows number
     * @return int
     */
    public int getNumberOfRows(){
      if(data != null){
        return data.size();
      }else{
        return 0;
      }
    }

    /**
     * get columns number
     * @return int
     */
    public int getNumberOfCoulumns(){
        if(columnNames!=null){
            return columnNames.size();
        }
        return 0;
    }

    /**
     * Remove row at row index
     * @param row int
     * @throws IllegalStateException
     */
    public void removeRowAt(int row)
            throws IllegalStateException {
          if(!(Integer.parseInt(((Vector)data.elementAt(row)).elementAt
                                (numberOfcolumns).toString()) == IS_INSERTED)){
            ( (Vector) data.elementAt(row)).
                setElementAt(new Integer(IS_DELETED), numberOfcolumns);
            dataDeleted.add((Vector)data.elementAt(row));
          }
        data.removeElementAt(row);
        this.deletedStatus = true;
    }

    /**
     * Get all rows were deleted on Datasource
     * @return Vector
     */
    public DataSourceVector getDeletedRows()
            throws IllegalStateException {
          DataSourceVector dataDeletedV = new DataSourceVector();
          dataDeletedV.setColumnName(columnNames);
          dataDeletedV.setData(dataDeleted);
          return dataDeletedV;
    }

    /**
     * Get all rows were modified on datasource
     * @return Vector
     * @throws IllegalStateException
     */
    public DataSourceVector getModifiedRows()
            throws IllegalStateException {
        DataSourceVector dataModified = new DataSourceVector();
        dataModified.setColumnName(columnNames);
        dataModified.flagAddStatusRow = false;
        /*Read data from data Vector*/
        for(int row = 0; row < data.size(); row ++){
            if(((Vector)data.elementAt(row)).elementAt(numberOfcolumns).equals(new Integer(IS_MODIFIED))){
                dataModified.addNewRow((Vector)data.elementAt(row));
            }
        }
        return dataModified;
    }

    /**
     * Get all rows were modified on datasource
     * @return Vector
     * @throws IllegalStateException
     */
    public DataSourceVector getInsertRows() throws IllegalStateException {
        DataSourceVector dataInserted = new DataSourceVector();
        dataInserted.setColumnName(columnNames);
        dataInserted.flagAddStatusRow = false;
        /*Read data from data Vector*/
        for (int row = 0; row < data.size(); row++) {
            if (((Vector) data.elementAt(row)).elementAt(numberOfcolumns).
                equals(new Integer(IS_INSERTED))) {
                dataInserted.addNewRow((Vector)data.elementAt(row));
            }
        }
        return dataInserted;
    }

    /**
     * Tranfer string to number
     * @param num String
     * @return double
     */
    private double tranferStringToNum(String num){
      double number = 0;
      try{
	number = Double.parseDouble(num);
      }catch(Exception ex){
	number = 0;
      }
      return number;
    }
}
