package btm.business;

import java.sql.*;
import common.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Phuoc Nguyen
 * @version 1.0
 */

public class ExchangeRateBusObj {
    public ExchangeRateBusObj() {
    }

    //Get Currency Code, return a ResultSet
    public ResultSet getCurrency(DataAccessLayer DAL,Statement stmt) {
        ResultSet rs = null;
        String localCurr = DAL.getCurrencyID();
        try {
            String sql = "select CURR_ID, CURR_ID from BTM_IM_CURRENCY_CDE where CURR_ID <> '" + localCurr + "'";
            rs = DAL.executeScrollQueries(sql,stmt);
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return rs;
    }

    public boolean isHasCurrency(DataAccessLayer DAL, String date, String currency,Statement stmt){
        ResultSet rs = null;
        try {
            String sql = "select CURR_ID from BTM_IM_EXCHANGE_RATE where CURR_ID = '" +
                    currency + "' and WORKDAY = to_date('" + date + "','DD/MM/YYYY')";

            rs = DAL.executeScrollQueries(sql,stmt);
            rs.previous();
            if(rs.next()){
                stmt.close();
                return true;
            }
            else{
                stmt.close();
                return false;
            }
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return true;
    }

    public ResultSet getAll(DataAccessLayer DAL,String date,Statement stmt){
        ResultSet rs = null;
        try {
            String sql ="select * from BTM_IM_EXCHANGE_RATE where WORKDAY = to_date('"
                    + date + "','DD/MM/YYYY')";
            rs = DAL.executeQueries(sql,stmt);
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return rs;

    }
    public ResultSet getAll(DataAccessLayer DAL,Statement stmt){
           ResultSet rs = null;
           try {
             String sql ="select to_char(workday,'dd/mm/yyyy') workday,curr_id,exchange_rate from BTM_IM_EXCHANGE_RATE where (CURR_ID = 'USD' and WORKDAY = (select max ( WORKDAY ) from BTM_IM_EXCHANGE_RATE where CURR_ID = 'USD')) or (CURR_ID = 'EUR' and WORKDAY = (select max ( WORKDAY ) from BTM_IM_EXCHANGE_RATE where CURR_ID = 'EUR')) order by WORKDAY ASC";
               rs = DAL.executeQueries(sql,stmt);
           }
           catch (Exception e) {
               ExceptionHandle eh = new ExceptionHandle();
               eh.ouputError(e);
           }
           return rs;

       }

    public void updateExRate(DataAccessLayer DAL,String exRate, String date, String currId,Statement stmt){
//        ResultSet rs = null;
        try {
            String sql ="update BTM_IM_EXCHANGE_RATE set EXCHANGE_RATE =" + exRate +
                    "where WORKDAY = to_date('"+ date + "','DD/MM/YYYY') and CURR_ID ='" + currId+ "'";

            DAL.executeQueries(sql,stmt);
            stmt.close();
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
    }
}
