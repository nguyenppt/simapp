package btm.business;

import java.sql.*;
import common.*;
import java.util.*;
/**
 * <p>Title:  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class PromotionBusObj {
   Utils ut = new Utils();
   Statement stmt = null;

   public static final int LEN_PROMO_ID = 12;
   public String promo_ID ;
   public String promo_Name;
   public String start_Date;
   public String end_Date;
   public int rowlimit;
   public Vector vData ;
   public int rowSelected;

  public PromotionBusObj() {
  }
  public ResultSet searchPromo(String PromoID, String PromoName, String StartDate, String EndDate,int rowsNum,DataAccessLayer DAL,Statement stmt){
    ResultSet rs = null;
    String SQL="";
    SQL = " SELECT PROMO_ID,PROMO_NAME,DESCRIPTION,START_DATE,END_DATE,START_TIME,END_TIME "
        + "FROM BTM_IM_PROMO_HEAD "
        + "WHERE STATUS <> 'D' AND ROWNUM <= " + rowsNum ;
     if(!PromoName.trim().equalsIgnoreCase("")){
       SQL = SQL + " AND LOWER(PROMO_NAME) LIKE (%'" + PromoName + "'%)";
     }
     if(!StartDate.trim().equalsIgnoreCase("")){
       SQL = SQL + " AND START_DATE>=TO_DATE('"+ StartDate +"','DD-MM-YYYY')";
     }
     if(!EndDate.trim().equalsIgnoreCase("")){
       SQL = SQL + "AND START_DATE <= TO_DATE('"+ EndDate +"','DD-MM-YYYY')";
     }
    try{
//      System.out.println(SQL);
      DAL.executeScrollQueries(SQL,stmt);
      stmt.close();
    }
    catch(Exception ex){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(ex);
    }
    return rs;
  }
  public void setData(String promoID, String promoName, String startDate, String endDate,int rows, Vector vData,int rowSelected) {
    this.promo_ID = promoID;
    this.promo_Name = promoName;
    this.start_Date = startDate;
    this.end_Date = endDate;
    this.rowlimit = rows;
    this.vData = vData;
    this.rowSelected = rowSelected;
 }
 public ResultSet getPromoName(DataAccessLayer DAL){
   ResultSet rs=null;
   String SQL="";
   SQL="select promo_id, promo_name from BTM_IM_PROMO_HEAD where status<>'D' order by upper(promo_name)";
   try{
     rs=DAL.executeScrollQueries(SQL);
   }
   catch(Exception e){
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return rs;
 }

}
