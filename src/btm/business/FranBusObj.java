package btm.business;

import btm.swing.*;
import java.sql.*;
import common.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FranBusObj {
  public FranBusObj() {
  }
//set franchises' name into cbo box
  public void setCboFran(BJComboBox cbo, DataAccessLayer DAL){
    ResultSet rs= null;
    Statement stmt=null;
    String sql =
        "select cust_id, first_name from btm_im_customer where cust_flag = 1";
    try{
      stmt=DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs=DAL.executeScrollQueries(sql,stmt);
      cbo.setData1(rs);
      rs.close();
      stmt.close();
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
//set stores' name into cbo box
  public void setCboStore(BJComboBox cbo, DataAccessLayer DAL){
    ResultSet rs= null;
    Statement stmt=null;
    String sql =
        "select store_id, name from btm_im_store where store_type = 'D'";
    try{
      stmt=DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs=DAL.executeScrollQueries(sql,stmt);
      cbo.setData1(rs);
      rs.close();
      stmt.close();
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
//get transfers from store with indicated conditions
  public ResultSet getTransferFranFromStore(DataAccessLayer DAL,Statement stmt,String fromDate, String toDate,String fromStore, String toFran){
  ResultSet rs=null;
  String SQL = "";
  try{
    SQL = " select FRANCHISE_DO_ID, NAME TRANSFER_FROM, FIRST_NAME TRANSFER_TO, to_char(FRANCHISE_DO_DATE,'dd/MM/yyyy') "
        + "from BTM_IM_FRANCHISE_DO a, BTM_IM_STORE b , BTM_IM_CUSTOMER c  "
        + "where a.FROM_STORE=b.STORE_ID and a.TO_FRANCHISE=c.CUST_ID";
      if (!fromDate.equalsIgnoreCase("")){
        SQL = SQL + " and FRANCHISE_DO_DATE >= to_date('" + fromDate + "','DD-MM-YYYY')";
      }
      if (!toDate.equalsIgnoreCase("")){
        SQL = SQL + " and FRANCHISE_DO_DATE <= to_date('" + toDate + "','DD-MM-YYYY')";
      }
      if(!fromStore.equalsIgnoreCase("")){
        SQL = SQL + " and a.FROM_STORE = '" + fromStore + "'";
      }
      if(!toFran.equalsIgnoreCase("")){
        SQL = SQL + " and a.TO_FRANCHISE = '" + toFran + "'";
      }
//      System.out.println(SQL);
      rs = DAL.executeScrollQueries(SQL,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
//get transfers from franchise with indicated conditions
  public ResultSet getTransferFranFromFran(DataAccessLayer DAL,Statement stmt,String fromDate, String toDate,String fromFran, String toFran){
  ResultSet rs=null;
  String SQL = "";
  try{
    SQL = " select FRANCHISE_DO_ID, b.FIRST_NAME TRANSFER_FROM, c.FIRST_NAME TRANSFER_TO, to_char(FRANCHISE_DO_DATE,'dd/MM/yyyy') "
        + "from BTM_IM_FRANCHISE_DO a, BTM_IM_CUSTOMER b , BTM_IM_CUSTOMER c  "
        + "where a.FROM_FRANCHISE=b.CUST_ID and a.TO_FRANCHISE=c.CUST_ID";
      if (!fromDate.equalsIgnoreCase("")){
        SQL = SQL + " and FRANCHISE_DO_DATE >= to_date('" + fromDate + "','DD-MM-YYYY')";
      }
      if (!toDate.equalsIgnoreCase("")){
        SQL = SQL + " and FRANCHISE_DO_DATE <= to_date('" + toDate + "','DD-MM-YYYY')";
      }
      if(!fromFran.equalsIgnoreCase("")){
        SQL = SQL + " and a.FROM_FRANCHISE = '" + fromFran + "'";
      }
      if(!toFran.equalsIgnoreCase("")){
        SQL = SQL + " and a.TO_FRANCHISE = '" + toFran + "'";
      }
//      System.out.println(SQL);
      rs = DAL.executeScrollQueries(SQL,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

}
