
package btm.business;

import common.*;
import java.sql.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

public class ItemLocSupplierBusObj {
//  DataAccessLayer DAL = new DataAccessLayer();

  private DataAccessLayer DAL ;
  public ItemLocSupplierBusObj() {
//        DAL.getOracleConnect();
  }

  public DataAccessLayer getDataAccessLayer(){
   return DAL;
 }

 public void setDataAccessLayer(DataAccessLayer DAL ){
   this.DAL=DAL;
 }


  /** insertItemMaster method - insert into database <BTM_IM_ITEM_LOC_SUPPLIER>
  * @author 		Hieu.Pham
  * @param 		String itemId,String suppId,long storeId,double unitCost
  * @return		No
  */
  public void insertItemLocSupplier(String itemId,String suppId,long storeId,double unitCost,double VAT) {
//    DAL.getOracleConnect();//temp
//   ResultSet rs = null;
    Statement stmt = null;
   String sql = "INSERT INTO BTM_IM_ITEM_LOC_SUPPLIER (ITEM_ID, SUPP_ID, STORE_ID, UNIT_COST, AVG_COST,VAT) VALUES ( '"+
       itemId + "','" +suppId + "',"  + storeId + "," + unitCost + "," + unitCost + "," + VAT +") ";
//   System.out.println(sql);
   try {
//     rs = DAL.executeQueries(sql);
     stmt = DAL.getConnection().createStatement();
     DAL.executeUpdate(sql,stmt);
     stmt.close();
   }catch (Exception e) {
     ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
   }
 }//end method

 /** updateItemLocSupplier method - update in database <BTM_IM_ITEM_LOC_SUPPLIER>
   * @author 		Hieu.Pham
   * @param 		String itemId,String suppId,long storeId,double unitCost
   * @return		No
   */
  public void updateItemLocSupplier(String itemId,String suppId,long storeId,double unitCost) {
//    DAL.getOracleConnect();//temp
    ResultSet rs = null;

    String sql = "UPDATE BTM_IM_ITEM_LOC_SUPPLIER SET UNIT_COST="+unitCost+
        " WHERE ITEM_ID='"+itemId+"' AND SUPP_ID='"  +suppId+"' AND STORE_ID=" +storeId ;
//    System.out.println(sql);
    try {
      rs = DAL.executeQueries(sql);
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }//end method

  /** deleteItemLocSupplier method - delete in database <BTM_IM_ITEM_LOC_SUPPLIERR>
   * @author 		Hieu.Pham
   * @param 		String itemId,String suppId,long storeId
   * @return		No
   */
  public void deleteItemLocSupplier(String itemId,String suppId,long storeId) {
//    DAL.getOracleConnect();//temp
    ResultSet rs = null;

    String sql = "DELETE BTM_IM_ITEM_LOC_SUPPLIER " +
              " WHERE ITEM_ID='"+itemId+"' AND SUPP_ID='"  +suppId+"' AND STORE_ID=" +storeId ;
//          System.out.println(sql);
    try {
      rs = DAL.executeQueries(sql);
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }//end method




}
