package btm.business;

/***dftdg
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.sql.*;
import btm.attr.*;
import btm.swing.*;
//import common.DataAccessLayer;
//import common.ExceptionHandle;
import common.*;
import java.util.*;


public class ItemSeasonBusObj {
  private DataAccessLayer DAL ;
  private Utils ut = new Utils();
  public String item_ID;
  public String item_Name;
  public String season_Name;
  public String phase_Name;
  public Vector vData;
  public String []arrSEQ;
  public int rowlimit;
  public int rowSelected;
  Statement stmt = null;
  public ItemSeasonBusObj() {
  }

  public DataAccessLayer getDataAccessLayer() {
    return DAL;
  }

  public void setDataAccessLayer(DataAccessLayer DAL) {
    this.DAL = DAL;
  }

  public void deleteItemSeason(String ItemCode, String seasonId,Statement stmt ){
    try {
      String sql = " delete from BTM_IM_ITEM_SEASON where item_id ='" + ItemCode + "' and season_id='" + seasonId + "'";
      DAL.executeUpdate(sql,stmt);
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  } //end method


  /** insertItemSeason method - insert into database <BTM_IM_ITEM_SEASON>
   * @author 		Hieu.Pham
   * @param 		String ItemCode, long seasonId, long phaseId, Date createDateTime
   * @return		No
   */
  public void insertItemSeason(String ItemCode, String seasonId, String last_upd_id,Statement stmt ) /*throws Exception */ {
    String currDate = ut.getSystemDate();
    try {
      String sql = " INSERT INTO BTM_IM_ITEM_SEASON VALUES ( '" +
          ItemCode + "',ITEMSEASON_SEQ.nextval," + seasonId + ",'',to_date('" + currDate +  "','yyyy-MM-dd'),to_date('" + currDate +  "','yyyy-MM-dd'),'"+last_upd_id + "')";
//      System.out.println(sql);
      DAL.executeUpdate(sql,stmt);
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  } //end method

  public void setData(String itemID, String itemName, String seasonName,int rows, Vector vData, String [] arr_Seq,int rowSelected) {
    this.item_ID = itemID;
    this.item_Name = itemName;
    this.season_Name =seasonName;
//    this.phase_Name = phaseName;
    this.rowlimit = rows;
    this.vData = vData;
    this.arrSEQ = arr_Seq;
    this.rowSelected = rowSelected;
  }

  /** updateItemSeason method - update in database <BTM_IM_ITEM_SEASON>
   * @author 		Hieu.Pham
   * @param 		String ItemCode, long seqNo, long seasonId, long phaseId, Date lastUpdDateTime
   * @return		No
   */
  public void updateItemSeason(String ItemCode, String seqNo, String seasonId,String last_upd_id) {
    Statement stmt = null;
    String currDate = ut.getSystemDate();
    String sql = "UPDATE BTM_IM_ITEM_SEASON SET SEASON_ID=" + seasonId +
        ",LAST_UPD_DATETIME = to_Date('" +  currDate  +
        "','yyyy-MM-dd'), LAST_UPD_ID ='" + last_upd_id + "' WHERE ITEM_ID='" + ItemCode + "' and SEQ_NO= " + seqNo ;
//    System.out.println(sql);
    try {
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      DAL.executeUpdate(sql,stmt);
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  } //end method
  public ResultSet getData(String itemID,String itemName,String season,String phase, int rowsLimit, DataAccessLayer DAL,Statement stmt){
     ResultSet rs = null;
     try{
       String sqlStr = "select its.item_id, m.item_name, season_cde, phase_desc from btm_im_item_season its, btm_im_item_master m, btm_im_season s, btm_im_phases p where its.item_id=m.item_id and its.Season_id=s.season_id and its.phase_id=p.phase_id and rownum <=" + rowsLimit;
       String sql = "";
       if(itemID.trim().length()>0){
         sql += " and lower(its.item_id) like lower('%" + itemID + "%')";
       }
       if(itemName.trim().length()>0){
         sql += " and lower(item_name) like lower('%" + itemName + "%')";
       }
       if(season != ""){
         sql += " and its.season_id =" + season ;
       }
       if(phase != ""){
         sql += " and its.phase_id =" + phase ;
       }
        sqlStr += sql;
//       System.out.println(sqlStr);
       rs = DAL.executeScrollQueries(sqlStr,stmt);
     }catch(Exception e){
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
     return rs;
   }
   public ResultSet getData(String itemID,String itemName,String season, int rowsLimit, String artNo, DataAccessLayer DAL,Statement stmt){
   ResultSet rs = null;
   try{
     String sqlStr = "select  distinct its.item_id, m.item_name, season_cde, m.art_no,ph.attr2_name from btm_im_item_season its, btm_im_item_master m, btm_im_season s, btm_im_price_hist ph where ph.item_id=m.item_id and ph.item_id = its.item_id and its.Season_id=s.season_id and ph.status = 1 ";
     String sql = "";
     if(itemID.trim().length()>0){
       sql += " and lower(its.item_id) like lower('%" + itemID + "%')";
     }
     if(itemName.trim().length()>0){
       sql += " and lower(item_name) like lower('%" + itemName + "%')";
     }
     if(season != ""){
       sql += " and its.season_id =" + season ;
     }
     if(!artNo.equalsIgnoreCase("")){
       if (artNo.length()==6){
         sql += " and m.art_no = '" + artNo + "'";
       }else{
         sql += " and m.art_no like '%" + artNo + "%'";
       }
     }
      sqlStr += sql;
      sqlStr = "select * from (" + sqlStr + ") where rownum<=" + rowsLimit ;
//     System.out.println(sqlStr);
     rs = DAL.executeScrollQueries(sqlStr,stmt);
   }catch(Exception e){
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return rs;
 }

    public ResultSet getDataSEQ(String itemID,String itemName,String season,String phase, int rowsLimit, DataAccessLayer DAL,Statement stmt){
    ResultSet rs = null;
    try{
      String sqlStr = "select Seq_No from btm_im_item_season its, btm_im_item_master m, btm_im_season s, btm_im_phases p where its.item_id=m.item_id and its.Season_id=s.season_id and its.phase_id=p.phase_id and rownum <=" +
          rowsLimit;
      String sql = "";
      if (itemID.length() > 0) {
        sql += " and lower(its.item_id) like lower('%" + itemID + "%')";
      }
      if (itemName.length() > 0) {
        sql += " and lower(item_name) like lower('%" + itemName + "%')";
      }
      if (season != "") {
        sql += " and its.season_id =" + season;
      }
      if (phase != "") {
        sql += " and its.phase_id =" + phase;
      }
      if (sql != "") {
        sqlStr += sql;
      }

//      System.out.println(sqlStr);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  public ResultSet getDataSEQ(String itemID,String itemName,String season, int rowsLimit, DataAccessLayer DAL,Statement stmt){
    ResultSet rs = null;
    try{
      String sqlStr = "select Seq_No from btm_im_item_season its, btm_im_item_master m, btm_im_season s where its.item_id=m.item_id and its.Season_id=s.season_id and rownum <=" +
          rowsLimit;
      String sql = "";
      if (itemID.length() > 0) {
        sql += " and lower(its.item_id) like lower('%" + itemID + "%')";
      }
      if (itemName.length() > 0) {
        sql += " and lower(item_name) like lower('%" + itemName + "%')";
      }
      if (season != "") {
        sql += " and its.season_id =" + season;
      }
      if (sql != "") {
        sqlStr += sql;
      }

//      System.out.println(sqlStr);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

 /* public ResultSet getDataSEQWithNullValue(String itemID,String itemName,String season, int rowsLimit, DataAccessLayer DAL){
   ResultSet rs = null;
   try{
     String sql = "select its.item_id, m1.item_name, season_desc,Seq_No from btm_im_item_season its, btm_im_item_master m1, btm_im_season s where its.item_id = m1.item_id and its.season_id = s.season_id and its.phase_id is null and lower(its.item_id) like lower('%"
          + itemID + "%') and lower((select item_name from  btm_im_item_master m2 where its.Item_id = m2.item_id)) like  lower('%" + itemName +
          "%')and lower(its.season_id) like lower('%" + season + "%') and rownum <=" + rowsLimit;
     System.out.println(sql);
     rs = DAL.executeScrollQueries(sql);
   }catch(Exception e){
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return rs;
 }*/

  public boolean checkDataExist(String itemID,String season,String phase,DataAccessLayer DAL){
   ResultSet rs = null;
   Statement stmt = null;
   boolean exist = false;
  if(phase.equals(""))
    phase = "null";
  try {
    String sql = "Select * from BTM_IM_ITEM_SEASON Where Item_ID='" + itemID +
        "' and Season_ID=" + season +
        " and (((PHASE_ID is null) and (" + phase +
        " is null)) or (phase_ID=" + phase + "))";

//    System.out.println(sql);
    stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    rs = DAL.executeScrollQueries(sql, stmt);
    if (rs.next()) {
      exist = true;
    }
    stmt.close();
  }
  catch (Exception e) {
    ExceptionHandle eh = new ExceptionHandle();
    eh.ouputError(e);
  }
  return exist;
 }
 public boolean checkDataExist(String itemID,String season,DataAccessLayer DAL){
 ResultSet rs = null;
 Statement stmt = null;
 boolean exist = false;
 try {
  String sql = "Select * from BTM_IM_ITEM_SEASON Where Item_ID='" + itemID +
      "' and Season_ID=" + season ;

//  System.out.println(sql);
  stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
  rs = DAL.executeScrollQueries(sql, stmt);
  if (rs.next()) {
    exist = true;
  }
  stmt.close();
}
catch (Exception e) {
  ExceptionHandle eh = new ExceptionHandle();
  eh.ouputError(e);
}
return exist;
}

 public boolean checkDataExistModify(String itemID,String season,String seqNo,DataAccessLayer DAL){
   ResultSet rs = null;
   boolean exist = false;
  try {
    String sql = "Select * from BTM_IM_ITEM_SEASON Where Item_ID='" + itemID +
        "' and Season_ID=" + season + " and Seq_No <>" + seqNo;

//    System.out.println(sql);
    stmt = DAL.getConnection().createStatement(ResultSet.
                                             TYPE_SCROLL_INSENSITIVE,
                                             ResultSet.CONCUR_READ_ONLY);
    rs = DAL.executeScrollQueries(sql, stmt);
    if (rs.next()) {
      exist = true;
    }
    stmt.close();
    rs.close();

  }
  catch (Exception e) {
    ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return exist;
 }

   public ResultSet getData(String itemID){
     ResultSet rs = null;
     try {
       String sql = "select its.Item_ID,Item_Name, Season_Desc, m.ART_NO" +
           " from BTM_IM_ITEM_SEASON its, BTM_IM_ITEM_MASTER m, BTM_IM_SEASON s " +
           "where its.Item_id=m.item_id and its.season_id =s.season_id and its.item_id='" +
           itemID + "'";
//       System.out.println(sql);
       rs = DAL.executeScrollQueries(sql);
     }
     catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
     return rs;
   }

  /** deleteItemSeason method - update in database <BTM_IM_ITEM_SEASON>
   * @author 		Hieu.Pham
   * @param 		String ItemCode, long seqNo
   * @return		No
   */
  public void deleteItemSeason(String ItemCode, long seqNo ) {
    ResultSet rs = null;

    String sql = "DELETE BTM_IM_ITEM_SEASON WHERE ITEM_ID='" + ItemCode + "' and SEQ_NO= " + seqNo ;
//    System.out.println(sql);
    try {
      rs = DAL.executeQueries(sql);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  } //end method
  public void deleteItemSeason(String ItemCode) {
     ResultSet rs = null;

     String sql = "DELETE BTM_IM_ITEM_SEASON WHERE ITEM_ID='" + ItemCode + "'" ;
//     System.out.println(sql);
     try {
       rs = DAL.executeQueries(sql);
     }
     catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
   } //end method


  /** get data from table <BTM_IM_ITEM_SEASON>
   * @author 		Hieu.Pham
   * @param 		no
   * @return		ResultSet
   */
  public ResultSet selectItemSeason() {
    ResultSet rs = null;
    String sqlStr = "select its.ITEM_ID,it.ITEM_NAME, p.PHASE_DESC" +
              " from BTM_IM_ITEM_MASTER it ,BTM_IM_ITEM_SEASON its,BTM_IM_PHASES p,BTM_IM_SEASON s " +
              " where it.ITEM_ID=its.ITEM_ID and its.SEASON_ID=s.SEASON_ID and its.PHASE_ID=p.PHASE_ID  ";

    try {
      rs = DAL.executeScrollQueries(sqlStr);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method

  /** check ItemSeason existing in table <BTM_IM_ITEM_SEASON>
   * @author 		Hieu.Pham
   * @param 		no
   * @return		boolean
   *                         true : ItemSeason exist
   *                         false: ItemSeason does not exist
   */
//  public boolean checkItemSeasonExisting(String itemCode,long itemSeq) {
//    DAL.getOracleConnect();//temp
//    ResultSet rs = null;
//    boolean fExisting = false;
//    try {
//      System.out.println("select count(*) from BTM_IM_ITEM_SEASON where ITEM_ID='" +
//          itemCode + "' and SEQ_NO= " +itemSeq);
//      rs = DAL.executeScrollQueries(
//          "select count(*) from BTM_IM_ITEM_SEASON where ITEM_ID='" +
//          itemCode + "' and SEQ_NO= " +itemSeq);
//      while (rs.next()) {
//        fExisting = rs.getLong(1) > 0 ? true : false;
//      }
//
//    }
//    catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//    return fExisting;
//  } //end method
//  public boolean checkItemSeasonExisting(String itemCode) {
//    DAL.getOracleConnect();//temp
//    ResultSet rs = null;
//    boolean fExisting = false;
//    try {
//      System.out.println("select count(*) from BTM_IM_ITEM_SEASON where ITEM_ID='" + itemCode + "'");
//      rs = DAL.executeScrollQueries(
//          "select count(*) from BTM_IM_ITEM_SEASON where ITEM_ID='" +  itemCode + "'");
//      while (rs.next()) {
//        fExisting = rs.getLong(1) > 0 ? true : false;
//      }
//
//    }
//    catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//    return fExisting;
//  } //end method



  /** get data from table <BTM_IM_ITEM_SEASON>
   * @author 		Hieu.Pham
   * @param 		String itemCode
   */
  public ResultSet selectItemSeason(String itemCode,long itemSeq) {
    ResultSet rs = null;

    try {
//      System.out.println(" SELECT im.ITEM_ID,i.ITEM_NAME , im.SEQ_NO, s.SEASON_DESC, p.PHASE_DESC " +
//                                    " FROM BTM_IM_ITEM_SEASON im, BTM_IM_ITEM_MASTER i,BTM_IM_SEASON s,BTM_IM_PHASES p " +
//                                    " WHERE im.ITEM_ID= '" + itemCode + "' and im.SEQ_NO= " + itemSeq +
//                                   " and im.ITEM_ID=i.ITEM_ID and im.SEASON_ID=s.SEASON_ID and im.PHASE_ID=p.PHASE_ID  ");

      rs = DAL.executeScrollQueries(" SELECT im.ITEM_ID,i.ITEM_NAME , im.SEQ_NO, s.SEASON_DESC, p.PHASE_DESC " +
                                    " FROM BTM_IM_ITEM_SEASON im, BTM_IM_ITEM_MASTER i,BTM_IM_SEASON s,BTM_IM_PHASES p " +
                                    " WHERE im.ITEM_ID= '" + itemCode + "' and im.SEQ_NO= " + itemSeq +
                                   " and im.ITEM_ID=i.ITEM_ID and im.SEASON_ID=s.SEASON_ID and im.PHASE_ID=p.PHASE_ID  ");
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method
  public ResultSet selectItemSeason(String itemCode) {
    ResultSet rs = null;
    String sqlStr = " SELECT im.ITEM_ID,i.ITEM_NAME , im.SEQ_NO, s.SEASON_DESC, p.PHASE_DESC " +
                                    " FROM BTM_IM_ITEM_SEASON im, BTM_IM_ITEM_MASTER i,BTM_IM_SEASON s,BTM_IM_PHASES p " +
                                    " WHERE im.ITEM_ID= '" + itemCode + "' and im.ITEM_ID=i.ITEM_ID and im.SEASON_ID=s.SEASON_ID and im.PHASE_ID=p.PHASE_ID  ";

    try {
//      System.out.println(sqlStr);

      rs = DAL.executeScrollQueries(sqlStr);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method


}


