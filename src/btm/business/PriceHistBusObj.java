package btm.business;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author not attributable
 * @version 1.0
 */

import common.*;
import java.sql.*;
import java.util.*;

public class PriceHistBusObj {

//  private DataAccessLayer DAL = new DataAccessLayer();
  private DataAccessLayer DAL ;
  private Utils ut = new Utils();
  public PriceHistBusObj() {
//    DAL.getOracleConnect();
  }

  public DataAccessLayer getDataAccessLayer(){
    return DAL;
  }

  public void setDataAccessLayer(DataAccessLayer DAL ){
    this.DAL=DAL;
  }



  /** insertPriceHist method - insert into database <BTM_IM_PRICE_HIST>
  * @author 		Hieu.Pham
  * @param 		String itemId,long storeId,long transType,Date priceEffDate,double newPrice, double unitCost,double sellingUnitRetail
  * @return		No
  */
  public void insertPriceHist(String itemId,long storeId,long transType,java.sql.Date priceEffDate,double newPrice, double unitCost,double sellingUnitRetail, String attr1Name, String attr2Name) {
//    DAL.getOracleConnect();//temp
//   ResultSet rs = null;
    Statement stmt = null;
   String sql = "INSERT INTO BTM_IM_PRICE_HIST (ITEM_ID, STORE_ID, TRANS_TYPE, PRICE_EFF_DATE, PRICE_END_DATE ,NEW_PRICE, UNIT_COST," +
       " SELLING_UNIT_RETAIL, ATTR1_NAME, ATTR2_NAME, LAST_UPD_DATE, BATCH_SEQ, STATUS) VALUES (  '"+  itemId + "'," +storeId + ","  + transType + ",to_Date('" + priceEffDate +
       "','yyyy-MM-dd'),to_Date('7777-7-7','yyyy-MM-dd')," + newPrice + "," + unitCost + "," + sellingUnitRetail + ",'" + attr1Name + "','" + attr2Name + "',to_date('"+ut.getSystemDate(1)+"','dd/mm/yyyy'),0,1) ";
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

 /** updatePriceHist method - update into database <BTM_IM_PRICE_HIST>
* @author 		Hieu.Pham
* @param 		String itemId,long storeId,long transType,Date priceEffDate,double newPrice, double unitCost,double sellingUnitRetail
* @return		No
*/
public void updatePriceHist(String itemId,long storeId,long transType,java.sql.Date priceEffDate,double newPrice, double unitCost,double sellingUnitRetail,String attr1Name,String attr2Name) {
//    DAL.getOracleConnect();//temp
 ResultSet rs = null;
 String sql = "UPDATE BTM_IM_PRICE_HIST SET TRANS_TYPE="+transType + ",PRICE_EFF_DATE= " + "to_Date('" + priceEffDate + "','yyyy-MM-dd')," +
              " NEW_PRICE= " +newPrice + ", UNIT_COST= " +unitCost + ", SELLING_UNIT_RETAIL= "+ sellingUnitRetail +
              " ATTR1_NAME='" + attr1Name + "', ATTR2_NAME='" + attr2Name + "'" +
        " WHERE ITEM_ID='"+itemId+ "' AND STORE_ID=" +storeId ;
//    System.out.println(sql);
 try {
   rs = DAL.executeQueries(sql);
 }catch (Exception e) {
   ExceptionHandle eh = new ExceptionHandle();
   eh.ouputError(e);
 }
}

  /** updatePriceHist method - update into database <BTM_IM_PRICE_HIST>
 * @author 		Tuan.Truong
 * @param 		String itemId,long storeId,long transType,Date priceEffDate,double newPrice, double unitCost,double sellingUnitRetail
 * @return		No
 */
  public void changePriceHist(DataAccessLayer dal, String itemId, int storeId, double newPrice,
                              double oldPrice, double sellingUnitRetail,
                              String status, String desc,
                              String priceEffDate, String priceEndDate
                              ) {
    ResultSet rs = null;
    String sql = "update BTM_IM_PRICE_HIST set NEW_PRICE=" + newPrice + ", OLD_PRICE=" + oldPrice +
                 ", SELLING_UNIT_RETAIL=" + sellingUnitRetail +
                 ", STATUS ='" + status + "', DESCRIPTION = '" + desc + "'" +
                 ", PRICE_EFF_DATE=to_date('" + priceEffDate + "','yyyy-MM-dd'), PRICE_END_DATE=to_date('" + priceEndDate + "','yyyy-MM-dd')" +
                 "  where ITEM_ID='"+itemId+ "' and STORE_ID=" +storeId ;
//             System.out.println(sql);
    try {
           dal.executeUpdate(sql);
         }catch (Exception e) {
           ExceptionHandle eh = new ExceptionHandle();
           eh.ouputError(e);
         }
   }//end method


 /** getPrice method - update into database <BTM_IM_PRICE_HIST>
  * @author 		Tuan.Truong
  * @param 		no
  * @return		No
  */
 public Vector getPrice(DataAccessLayer DAL, String itemId, int storeId) {
   Vector vItem = new Vector();
//   Object oItem;
   ResultSet rs = null;

   int numRecord = DAL.countRecord("select count(*) as RECORD_COUNT " +
                          "from BTM_IM_PRICE_HIST where ITEM_ID = '" + itemId + "' and STORE_ID =" + storeId, "RECORD_COUNT", DAL);
    if (numRecord==0) {
      return vItem=null;
    }
   String sql = "select ITEM_ID, STORE_ID, PRICE_EFF_DATE, PRICE_END_DATE, NEW_PRICE, OLD_PRICE, " +
       "STATUS, DESCRIPTION, UNIT_COST, SELLING_UNIT_RETAIL, UNIT_RETAIL, SELLING_UOM " +
       "from BTM_IM_PRICE_HIST where ITEM_ID = '" + itemId + "' and STORE_ID =" + storeId;
//   System.out.println(sql);
   try {
     rs = DAL.executeQueries(sql);
     rs.next();
     String[] oItem = new String[]{
         rs.getString("ITEM_ID"),
         rs.getString("STORE_ID"),
         rs.getString("PRICE_EFF_DATE"),
         rs.getString("PRICE_END_DATE"),
         rs.getString("NEW_PRICE"),
         rs.getString("OLD_PRICE"),
         rs.getString("STATUS"),
         rs.getString("DESCRIPTION"),
         rs.getString("UNIT_COST"),
         rs.getString("SELLING_UNIT_RETAIL"),
         rs.getString("UNIT_RETAIL"),
         rs.getString("SELLING_UOM")
     };
     for (int i = 0; i<oItem.length;i++){
       vItem.addElement(oItem[i]);
     }
     rs.getStatement().close();
   }catch (Exception e) {
     ExceptionHandle eh = new ExceptionHandle();
     eh.ouputError(e);
   }
   return vItem;
 }//end method
}
