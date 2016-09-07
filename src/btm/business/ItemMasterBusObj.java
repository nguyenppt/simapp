
package btm.business;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

import java.sql.*;
import btm.attr.*;
import common.DataAccessLayer;
import common.ExceptionHandle;
import common.*;
import sim.FrameMainSim;


public class ItemMasterBusObj extends Exception {

//  String sSQL=new String();
//  btm.attr.ItemMaster attrItemMaster;
//  DataAccessLayer DAL = new DataAccessLayer();
//  FrameMainSim frmMain;
  private DataAccessLayer DAL;
  public ItemMasterBusObj() {
//    this.frmMain = frmMain;
//        DAL.getOracleConnect();
  }

  public DataAccessLayer getDataAccessLayer() {
    return DAL;
  }

  public void setDataAccessLayer(DataAccessLayer DAL) {
    this.DAL = DAL;
  }

  /** insertItemMasterBusObj method - insert into database <BTM_IM_ITEM_MASTER>
   * @author 		Hieu.Pham
   * @param 		String ItemCode,String ItemName,String ItemDesc,String ColorID,String SizeID, String Child_ID,
       long transLevel,long itemLevel,String standardUom
   * @return		No
   */
  public void insertItemMaster(String ItemCode, String ItemName,
                               String ItemDesc, String ColorID, String SizeID,
                               String Child_ID,
                               long transLevel, long itemLevel,
                               String standardUom, String packSize, String ArtNo, String material, String status, String priceRange, FrameMainSim frmMain) /*throws Exception */ {
    Utils ut = new Utils();

    try {
      String sql = "INSERT INTO BTM_IM_ITEM_MASTER (ITEM_ID, ITEM_NAME, ITEM_DESC, ATTR1_ID, ATTR2_ID, ATTR1_DTL_ID, ATTR2_DTL_ID, CHILD_ID, STATUS,TRANS_LEVEL, ITEM_LEVEL, STANDARD_UOM,ART_NO,MATERIAL,CREATED_DATE, PRICE_RANGE, PACK_SIZE, LAST_UPD_DATE ) "
          + " VALUES ( '" + ItemCode + "','" + ut.putCommaToString(ItemName) + "','" + ut.putCommaToString(ItemDesc) +
          "','-1','-1','" + ColorID + "','" + SizeID + "','" + Child_ID + "','" + status + "'," +
          transLevel + "," + itemLevel + ",'" + standardUom + "','" + ArtNo +  "','" + ut.putCommaToString(material) + "',to_date('" + ut.getSystemDate() + "','" + ut.DATE_FORMAT + "'),'" + priceRange + "','" + ut.putCommaToString(packSize) +
          "', to_date('" + ut.getSystemDate() + "','" + ut.DATE_FORMAT + "')) ";
//      System.out.println(sql);
      DAL.executeUpdate(sql);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
//    if (frmMain.frmMainCount == 300){
//      try{
//        DAL.closeOracleConnection(DAL.getConnection());
//        frmMain.frmMainCount = 0;
//      }catch(Exception ex){
//      }
//    }
  } //end method

  /** updateItemMaster method - update in database <BTM_IM_ITEM_MASTER>
   * @author 		Hieu.Pham
   * @param 		String ItemCode,String ItemName,String ItemDesc,String ColorID,String SizeID, String Child_ID,
       long transLevel,long itemLevel,String standardUom
   * @return		No
   */
//  public void updateItemMaster(String ItemCode, String ItemName,
//                               String ItemDesc, String ColorID, String SizeID,
//                               String Child_ID,
//                               long transLevel, long itemLevel,
//                               String standardUom,String ArtNo) {
//    DAL.getOracleConnect();//temp
//    ResultSet rs = null;
//
//    String sql = "UPDATE BTM_IM_ITEM_MASTER SET ITEM_NAME='" + ItemName +
//        "',ITEM_DESC='" + ItemDesc + "',ATTR1_ID='" + ColorID +
//        "',ATTR2_ID='" + SizeID + "',CHILD_ID='" + Child_ID + "',TRANS_LEVEL=" +
//        transLevel + ",ITEM_LEVEL=" + itemLevel + ",STANDARD_UOM='" +
//        standardUom +  "',ART_NO='" + ArtNo +
//        "' WHERE ITEM_ID='" + ItemCode + "'";
//    System.out.println(sql);
//    try {
//      rs = DAL.executeQueries(sql);
//    }
//    catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//  } //end method

  /** deleteItemMaster method - delete in database <BTM_IM_ITEM_MASTER>
   * @author 		Hieu.Pham
   * @param 		String ItemCode
   * @return		No
   */
//  public void deleteItemMaster(String ItemCode) {
////    DAL.getOracleConnect();//temp
//    ResultSet rs = null;
//
////    String sql = "DELETE BTM_IM_ITEM_MASTER " +
////              "' WHERE ITEM_ID='"+ItemCode+"'" ;
//    String sql = "UPDATE BTM_IM_ITEM_MASTER SET Status='D' WHERE ITEM_ID='" +
//        ItemCode + "'";
//    System.out.println(sql);
//    try {
//      rs = DAL.executeQueries(sql);
//    }
//    catch (Exception e) {
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//  } //end method

  /** get data from table <BTM_IM_ITEM_MASTER>
   * @author 		Hieu.Pham
   * @param 		no
   * @return		ResultSet
   */
  public ResultSet selectItemMaster() {
//    DAL.getOracleConnect();//temp
    ResultSet rs = null;
    String sSQL = null;
    try {
      sSQL = "select ITEM_ID, ITEM_NAME, ITEM_DESC,  ATTR1_ID, ATTR2_ID, CHILD_ID, TRANS_LEVEL, ITEM_LEVEL, STANDARD_UOM from BTM_IM_ITEM_MASTER";
//      System.out.println(sSQL);
      rs = DAL.executeScrollQueries(sSQL);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method

  /** get data from table <BTM_IM_ITEM_MASTER>
   * @author 		Hieu.Pham
   * @param 		String itemCode
   * @return		Vector
   *                          element(0) : recordCount
   *                          element(1) : resultSet
   */

//    public java.util.Vector  selectItemMaster(String itemCode) {
//      java.util.Vector vData= new java.util.Vector();
//      long recordCount;
//      ResultSet rs = null;
//
//      try {
//        rs = DAL.executeScrollQueries("select count(*) from BTM_IM_ITEM_MASTER where ITEM_ID='" + itemCode+ "'");
//        rs.next() ;
//        recordCount=rs.getLong(0) ;
//        rs = DAL.executeScrollQueries("select ITEM_ID, ITEM_NAME, ITEM_DESC,  ATTR1_ID, ATTR2_ID, CHILD_ID, TRANS_LEVEL, ITEM_LEVEL, STANDARD_UOM from BTM_IM_ITEM_MASTER where ITEM_ID='" + itemCode+ "'");
//        vData.addElement(String.valueOf(recordCount) );
//        vData.addElement(rs);
//      }catch (Exception e) {
//        ExceptionHandle eh = new ExceptionHandle();
//        eh.ouputError(e);
//      }
//      return vData;
//    }//end method
  public ResultSet selectItemMaster(String itemCode,Statement stmt) {
    ResultSet rs = null;
    String sqlStr = "select distinct i.ITEM_ID,i.ART_NO,i.ITEM_NAME,i.ITEM_DESC,i.ATTR1_ID, i.ATTR2_ID, i.CHILD_ID, i.TRANS_LEVEL, i.ITEM_LEVEL, i.STANDARD_UOM,isu.UNIT_COST,isu.SUPP_ID,iph.NEW_PRICE " +
        "from BTM_IM_ITEM_MASTER i ,BTM_IM_ITEM_LOC_SUPPLIER isu ,BTM_IM_SUPPLIER s,BTM_IM_PROD_HIR h,BTM_IM_PRICE_HIST iph " +
        "where i.ITEM_ID=isu.ITEM_ID and isu.SUPP_ID=s.SUPP_ID and i.CHILD_ID=h.CHILD_ID and " +
        "i.ITEM_ID=iph.ITEM_ID and isu.STORE_ID=iph.STORE_ID and iph.status='1' and i.Item_id='" +
        itemCode + "'";

    try {
//      System.out.println(sqlStr);
      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  } //end method

//  TrungNT 07/07/06
//  public ResultSet selectItemNameMaster(String itemCode, String suppID) {
//   ResultSet rs = null;
//   String sqlStr = "Select Item_Name From BTM_IM_ITEM_MASTER im, btm_im_item_loc_supplier ils " +
//       " Where im.item_id = ils.item_id and ils.supp_Id ='" + suppID +
//       "' and im.ART_NO='" + itemCode + "'";
//
//   try {
//     System.out.println(sqlStr);
//     rs = DAL.executeScrollQueries(sqlStr);
//   }
//   catch (Exception e) {
//     ExceptionHandle eh = new ExceptionHandle();
//     eh.ouputError(e);
//   }
//   return rs;
// } //end method
//TrungNT 07/07/06
 public ResultSet selectInfoItemMaster(String itemCode) {
   ResultSet rs = null;
   String sqlStr = "Select Item_Name,Art_No,Attr2_Dtl_Id,Status From BTM_IM_ITEM_MASTER " +
       " Where  ART_NO='" + itemCode + "' and status <> 'D'";

   try {
//     System.out.println(sqlStr);
     rs = DAL.executeScrollQueries(sqlStr);
   }
   catch (Exception e) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return rs;
 } //end method

 public ResultSet selectInfoItem(String itemCode, String supplierID){
   ResultSet rs = null;
   String sqlStr = "Select Item_Name,Art_No,Attr2_Dtl_Id,Status From BTM_IM_ITEM_MASTER im, " +
       " btm_im_item_loc_supplier ils " +
       " Where ils.item_id = im.item_id and im.ART_NO='" + itemCode + "' and supp_id ='" +
       supplierID+"' and status <> 'D'";

   try {
//     System.out.println(sqlStr);
     rs = DAL.executeScrollQueries(sqlStr);
   }
   catch (Exception e) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return rs;
 }
 public ResultSet selectInfoItem1(String itemCode){
   ResultSet rs = null;
   String sqlStr = "Select Item_Name,Art_No,Attr2_Dtl_Id,Status, supp_id From BTM_IM_ITEM_MASTER im, " +
       " btm_im_item_loc_supplier ils " +
       " Where ils.item_id = im.item_id and im.ART_NO='" + itemCode +
       "' and status <> 'D'";

   try {
//     System.out.println(sqlStr);
     rs = DAL.executeScrollQueries(sqlStr);
   }
   catch (Exception e) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return rs;
 }
 public String getIDfromUPC(String UPC){
   ResultSet rs = null;
   String itemID="";
   String sqlStr = "select * from BTM_IM_ITEM_MASTER where ART_NO='"+ UPC +"'";

   try {
//     System.out.println(sqlStr);
     rs = DAL.executeQueries(sqlStr);
     if(rs.next()){
       itemID=rs.getString("item_id");
//       System.out.println("Error! Can not find this item");
     }
     rs.getStatement().close();
   }
   catch (Exception e) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return itemID;
 }

 public ResultSet selectItemNameMaster(String itemCode) {
  ResultSet rs = null;
  String sqlStr = "Select Item_Name From BTM_IM_ITEM_MASTER Where ART_NO='" + itemCode + "'";

  try {
//    System.out.println(sqlStr);
    rs = DAL.executeScrollQueries(sqlStr);
  }
  catch (Exception e) {
    ExceptionHandle eh = new ExceptionHandle();
    eh.ouputError(e);
  }
  return rs;
} //end method

  /** get data from table <BTM_IM_ITEM_MASTER>
   * @author 		Hieu.Pham
   * @param 		no
   * @return		maxID of ItemMaster
   */
  public String getMaxItemMasterID() {
//    DAL.getOracleConnect();//temp
    ResultSet rs = null;
    long maxID = 0;
    String sSQL = null;
    try {
      sSQL = "select max(ITEM_ID)from BTM_IM_ITEM_MASTER";
//      System.out.println(sSQL);
      rs = DAL.executeScrollQueries(sSQL);
      while (rs.next()) {
        maxID = rs.getLong(1) + 1;
      }

    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return String.valueOf(maxID);
  } //end method

  //check if item eixist, base on ArtNo
    public boolean checkItem(String itemId, String itemUpc) {
      String colorID = "";
      String sizeID = "";
      String sql = "";
      if (!itemUpc.equalsIgnoreCase("")) {

          sql =
              "select * from btm_im_item_master where art_no ='" +
              itemUpc +
              "' ";
//          System.out.println(sql);
          try {
            ResultSet rs = null;
            rs = DAL.executeQueries(sql);
            if (rs.next()) {
              // UPC is exist if belongs to other item
              if(!rs.getString("item_id").equals(itemId)){
                return false;
              }
            }
            rs.getStatement().close();
          }
          catch (Exception e) {
            e.printStackTrace();
          }

      }

      return true;
    }

  /** check Itemcode existing in table <BTM_IM_ITEM_MASTER>
   * @author 		Hieu.Pham
   * @param 		no
   * @return		boolean
   *                         true : Item exist
   *                         false: Item does not exist
   */
  public boolean checkItemcodeExisting(String itemCode,String itemName) {
//    DAL.getOracleConnect();
    Statement stmt = null;
    ResultSet rs = null;
    boolean fExisting = false;
    String sSQL = null;
    try {
      sSQL = "select count(*) from BTM_IM_ITEM_MASTER where status <> 'D' and (ART_NO ='" + itemCode +
          "' or ITEM_NAME='" +itemName+"')";
//      System.out.println(sSQL);
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeQueries(sSQL,stmt);
      while (rs.next()) {
        fExisting = rs.getLong(1) > 0 ? true : false;
      }
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return fExisting;
  } //end method

//get new price of item
  public  long getUnitPrice(String itemID, String storeID, DataAccessLayer DAL) {
    long new_price=0;
    String sql = "select new_price from btm_im_price_hist " +
        "where ART_NO = '" + itemID + "' and store_id = " + storeID +
        " and status = '1'";
    ResultSet rs = null;
    try {
//      System.out.println(sql);
      rs = DAL.executeQueries(sql);
      if (rs.next()) {
        new_price = Long.parseLong(rs.getString("new_price").toString());
      }
      rs.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return new_price;
  }

  //get Item_ID
  public String getItemID(String upc, DataAccessLayer DAL){
    ResultSet rs = null;
    Statement stmt = null;
    if(upc.equalsIgnoreCase("")){
      return "";
    }
    String itemID="-1";
    try {
          String sql ="Select ITEM_ID From BTM_POS_ITEM_PRICE Where ART_NO='" + upc + "'";
//          System.out.println(sql);
          stmt = DAL.getConnection().createStatement();
          rs = DAL.executeQueries(sql, stmt);
          if (rs.next()) {
            itemID = rs.getString("ITEM_ID");
          }
    }catch (Exception e) {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(e);
    }
    try {
          stmt.close();
        }
    catch (Exception e) {
         e.printStackTrace();
    }
    return itemID;
  }


}

