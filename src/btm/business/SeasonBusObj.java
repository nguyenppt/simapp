package btm.business;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM Intersoft</p>
 * @author Loan vo
 * @version 1.0
 */

import java.sql.*;
import common.*;

public class SeasonBusObj {
  private DataAccessLayer DAL;
    String seaID = "";
    String seaName = "";
    String startdate = "";
    String enddate = "";
    String disc = "";

  public SeasonBusObj() {
  }

  public DataAccessLayer getDataAccessLayer() {
    return DAL;
  }

  public void setDataAccessLayer(DataAccessLayer DAL) {
    this.DAL = DAL;
  }

  /** getDataCboPosition method - get Season_ID and Season_Desc
   * @author 		Loan.vo
   * @param 		no
   * @return		ResultSet
   */
  public ResultSet getDataIntoCombo() {
    ResultSet rs = null;
    String sql = "select Season_ID, Season_Cde from BTM_IM_SEASON Where Season_ID <> -1";
    try {
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql);
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }//end method
  public ResultSet getDataIntoCombo(Statement stmt) {
    ResultSet rs = null;
    String sql = "select Season_ID, Season_Cde from BTM_IM_SEASON Where Season_ID <> -1";
    try {
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql,stmt);
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }//end method

//Get max value suppiler ID , return a number in long integer

        public int getMaxSeaID(DataAccessLayer DAL, String hostId){
                ResultSet rs = null;
                int value = 0;
                String id=null;
                String host = hostId + "%";
                try {
                  String sql = "select SEASON_ID from BTM_IM_SEASON where SEASON_ID like '" + host + "' order by SEASON_ID DESC";
//                  System.out.println(sql);
                  rs = DAL.executeScrollQueries(sql);
                  if(!rs .isBeforeFirst() && !rs.isAfterLast()) return 0;
                  rs.next();
                  id = rs.getString("SEASON_ID").toString().trim();
                  value = Integer.parseInt(id);
                  rs.getStatement().close();
                }
                catch (Exception e) {
                        ExceptionHandle eh = new ExceptionHandle();
                        eh.ouputError(e);
                }
                return value;
        }

        public ResultSet getData(String seaID, String seaName, String startdate,
                                 String enddate, String disc,int rowsLimit, DataAccessLayer DAL,Statement stmt) {
          String sql = "select Season_ID, season_cde, to_char(start_date,'dd/MM/yyyy'), to_char(end_date,'dd/MM/yyyy'),season_desc " +
              " from BTM_IM_SEASON where Season_ID <> '-1' and rownum <=" + rowsLimit;
          if (!seaID.trim().equalsIgnoreCase("")) {
            sql +=  " and lower(Season_ID) like lower('%" + seaID.trim() +"%') ";
          }
          if (!seaName.trim().equalsIgnoreCase("")) {
            sql +=  " and lower(season_cde) like lower('%" + seaName.trim() + "%') ";
          }
          if (!startdate.trim().equalsIgnoreCase("")) {
           sql += " and start_date = to_date('" + startdate + "','DD-MM-YYYY')";
          }
          if (!enddate.trim().equalsIgnoreCase("")) {
            sql +=  " and end_date = to_date('"+ enddate + "','DD-MM-YYYY')";
          }
          if (!disc.trim().equalsIgnoreCase("")) {
            sql += " and lower(season_desc) like lower('%" + disc + "%') ";
          }
//          System.out.println(sql);
          ResultSet rs = null;
          try {

            rs = DAL.executeScrollQueries(sql,stmt);
          }
          catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
          }
          return rs;
        }

        public void setValue(String seaID, String seaName, String startdate,
                             String enddate, String disc) {
          this.seaID = seaID;
          this.seaName = seaName;
          this.startdate = startdate;
          this.enddate = enddate;
          this.disc = disc;
        }

          public void updateSeason(String seaID, String seaName, String startdate, String enddate, String desc,Statement stmt) {
            try {
              stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            }
            catch (Exception ex) {
              ex.printStackTrace();
            }

             if(enddate.length()==0){
               enddate = "7-7-7777";
             }
             if(startdate.length()==0){
               startdate = "7-7-7777";
             }

             String sql = "UPDATE BTM_IM_SEASON SET SEASON_CDE='" + seaName + "' ,START_DATE = to_Date('" +  startdate  +
                 "','DD-MM-YYYY'), END_DATE = to_Date('" +  enddate  +
                 "','DD-MM-YYYY'),SEASON_DESC = '" + desc + "' WHERE SEASON_ID = " + seaID;
//             System.out.println(sql);
             try {
               DAL.executeUpdate(sql,stmt);
             }
             catch (Exception e) {
               ExceptionHandle eh = new ExceptionHandle();
               eh.ouputError(e);
             }
             try {
               stmt.close();
             }
             catch (Exception ex) {
               ex.printStackTrace();
             }

           } //end method
           public String getseaID(){
              return this.seaID;
            }
            public String getseaName(){
              return this.seaName;
            }
            public String getstartDate(){
              return this.startdate;
            }
            public String getendDate(){
              return this.enddate;
            }
            public String getDesc(){
              return this.disc;
            }


}
