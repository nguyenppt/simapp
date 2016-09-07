package btm.swing;


import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import javax.sql.RowSet;
import java.util.Vector;
import common.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class InitTableModel extends AbstractTableModel {

    public int flag = 1;
    private final int IS_FORM_1 = 1; //Default
    private final int IS_FORM_2 = 2; //ResultSet
    private final int IS_FORM_3 = 3; //DataSourceVector
    private Vector columnNames= new Vector();
    private Vector data = new Vector() ;
    private DataSourceVector dsVector;
    private int[] colNoEditable = new int[0];

    private Connection connection;
    public Statement statement;

    private ResultSet dataSet1 = null;
    private ResultSet dataSet ;
    private ResultSetMetaData metaData = null;
    private int numberOfRows = 0;
    private boolean isOverWriteHeader = false;


   // private Object[][] data;

    public  InitTableModel (){
        // notify JTable that model has changed
        fireTableStructureChanged();
    }

    public InitTableModel (DataSourceVector ds){
        dsVector = ds;
        // notify JTable that model has changed
        fireTableStructureChanged();
    }

    public void setDataSourceVector(DataSourceVector ds){
        dsVector = ds;
        flag = IS_FORM_3;
        // notify JTable that model has changed
        fireTableStructureChanged();
    }

    public int getColumnCount()  throws IllegalStateException {

        if(isOverWriteHeader){
            return columnNames.size();
        }else{
            switch (flag) {
                case IS_FORM_2:
                    try {
                        return metaData.getColumnCount();
                    } catch (SQLException sqlException) { // end try
                        sqlException.printStackTrace();
                    } // end catch
                    return 0; // if problems occur above, return 0 for number of columns
                case IS_FORM_3:
                    return dsVector.getColumnName().size();
                default:
                    return columnNames.size();
            }
        }
    }

    public int getRowCount()  throws IllegalStateException {
        switch(flag)
        {
            case IS_FORM_2:
                return numberOfRows;
            case IS_FORM_3:
                return dsVector.getData().size();
            default:
                return data.size();
        }
    }

    public String getColumnName(int col)  throws IllegalStateException {
        if(isOverWriteHeader || (flag == IS_FORM_1)){
            return columnNames.elementAt(col).toString();
        }else{
            switch (flag) {
            case IS_FORM_2:
                // determine column name
                try {
                    return metaData.getColumnName(col + 1);
                } catch (SQLException sqlException) { // end try
                    sqlException.printStackTrace();
                } // end catch
            case IS_FORM_3:
                return dsVector.getColumnName().elementAt(col).toString();
            }
        }
        // if problems, return empty string for column name
        return "";
    }

    /**
     * Set value at current cell
     * @param row int
     * @param col int
     * @return Object
     * @throws IllegalStateException
     */
    public Object getValueAt(int row, int col) throws IllegalStateException {

        switch(flag)
        {
            case IS_FORM_2:
                try {
                    dataSet.absolute(row + 1);
                    return dataSet.getObject(col + 1);
                } catch (SQLException sqlException) { // end try
                    sqlException.printStackTrace();
                } // end catch
                return ""; // if problems, return empty string object
            case IS_FORM_3:
                Vector rowData3 = (Vector)dsVector.getData().elementAt(row);
                return rowData3.elementAt(col).toString();
            default:
                Vector rowData = (Vector)data.elementAt(row);
                return rowData.elementAt(col).toString();
        }
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c)  throws IllegalStateException {
        if(flag != IS_FORM_2){
            return getValueAt(0, c).getClass();
        }else{
            try
            {
                String className = metaData.getColumnClassName(c + 1);

                // return Class object that represents className
                return Class.forName(className);
            } catch (Exception exception) { // end try
                exception.printStackTrace();
            } // end catch

            return Object.class; // if problems occur above, assume type Object
        }
    }

    /**
     * Init data from resultSet
     * @param rs ResultSet
     * @throws Exception
     */
    public void setDataSource(ResultSet rs) throws Exception {
        flag = IS_FORM_2;
        dataSet = rs;
        // obtain meta data for dataSet
        metaData = dataSet.getMetaData();

        metaData.getColumnClassName(1);
        // determine number of rows in ResultSet
        dataSet.last(); // move to last row
        numberOfRows = dataSet.getRow(); // get row number
        // notify JTable that model has changed
        fireTableStructureChanged();
    } // end method setQuery

    /**
     * Init data from resultSet
     * @param rs ResultSet
     * @throws Exception
     */
    public void setDataSource(Vector colName, Vector data) throws Exception {
       flag = IS_FORM_1;
       if(!isOverWriteHeader){
           columnNames = colName;
       }
       this.data = data;



       // notify JTable that model has changed
       fireTableStructureChanged();
   } // end method setQuery


    /**
     * Modify header on grid
     * @param header String[]
     */
    public void setHeader(String[] header){
        isOverWriteHeader = true;
        columnNames.clear();
        for(int i=0; i< header.length; i++){
            columnNames.add(i, header[i]);
        }
        fireTableStructureChanged();
    }

    /**
     * Set some of columns on grid is not editable
     * @param arrIndex int[]
     */
    public void setNoEditable(int[] arrIndex){
        colNoEditable = arrIndex;
    }

    /*
     * Set cells are edited on grid directly.
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if(colNoEditable.length>0){
            for(int i = 0; i< colNoEditable.length; i++){
                if(col == colNoEditable[i]){
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col)
            throws IllegalStateException {

        switch (flag) {
            case IS_FORM_2:
                try {
                    dataSet.absolute(row + 1);
                    dataSet.updateObject(col + 1, value);
                    dataSet.updateRow();
                } catch (SQLException sqlException) {
                    String temp = sqlException.getMessage();
                    sqlException.printStackTrace();
                } // end catch
                break;
            case IS_FORM_3:
                dsVector.setValueAt(value, row, col);
                break;
            default:
                ((Vector)data.elementAt(row)).setElementAt(value, col);
        }
        //refress grid
        fireTableCellUpdated(row, col);
    }

}
