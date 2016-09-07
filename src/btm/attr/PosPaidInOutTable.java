package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author TrungNT
 * @version 1.0
 */



import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Types;


public class PosPaidInOutTable {

  private static final String selectSql = "select TRANS_ID, CURR_ID, PAID_IN,PAID_OUT,trunc(TRANS_DATE) TRANS_DATE from BTM_POS_PAID_IN_OUT ";
  private static final String insertSql = "insert into BTM_POS_PAID_IN_OUT (TRANS_ID, CURR_ID, PAID_IN,PAID_OUT,TRANS_DATE) values (?, ?, ?,?,?)";
  private static final String updateSql = "update BTM_POS_PAID_IN_OUT set TRANS_ID = ?, CURR_ID = ?, PAID_IN = ?,PAID_OUT = ?, TRANS_DATE = ? ";
  private static final String deleteSql = "delete from BTM_POS_PAID_IN_OUT ";

  private static final String TABLE_NAME = "BTM_POS_PAID_IN_OUT";
  private static final String COL_TRANS_ID = "BTM_POS_PAID_IN_OUT.TRANS_ID";
  private static final String COL_CURR_ID = "BTM_POS_PAID_IN_OUT.CURR_ID";
  private static final String COL_PAID_IN = "BTM_POS_PAID_IN_OUT.PAID_IN";
  private static final String COL_PAID_OUT = "BTM_POS_PAID_IN_OUT.PAID_OUT";
  private static final String COL_TRANS_DATE = "BTM_POS_PAID_IN_OUT.TRANS_DATE";

  private static final int TYP_TRANS_ID = Types.VARCHAR;
  private static final int TYP_CURR_ID = Types.VARCHAR;
  private static final int TYP_PAID_IN = Types.NUMERIC;
  private static final int TYP_PAID_OUT = Types.NUMERIC;
  private static final int TYP_TRANS_DATE = Types.DATE;

  private String transID;
  private String currID;


  public PosPaidInOutTable(){
  }
  //get method
  public String getSelectSql() {
    return selectSql;
  }
  public String getInsertSql() {
    return insertSql;
  }
  public String getUpdateSql() {
    return updateSql;
  }
  public String getDeleteSql() {
    return deleteSql;
  }

  public String getTransID() {
    return this.transID;
  }
  public void setTransID(String transID) {
    this.transID = transID;
  }
  //set method



}
