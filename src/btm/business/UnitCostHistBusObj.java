package btm.business;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: BTM International Software</p>
 * @author Nguyen Quang Phuoc
 * @version 1.0
 */

import common.*;
import java.sql.*;
import java.util.*;

public class UnitCostHistBusObj {

  private DataAccessLayer DAL ;
  private Utils ut = new Utils();

  public UnitCostHistBusObj() {
  }

  public void setDataAccessLayer(DataAccessLayer DAL ){
    this.DAL=DAL;
  }

  public void insertUnitCostHist(String itemId,long storeId,long transType,java.sql.Date priceEffDate,double newPrice, double unitCost,double sellingUnitRetail, String attr1Name, String attr2Name) {
    Statement stmt = null;
    String sql = "INSERT INTO BTM_IM_UNIT_COST_HIST(ITEM_ID,STORE_ID,TRANS_TYPE,UNIT_COST_EFF_DATE,UNIT_COST_END_DATE,PRICE,NEW_UNIT_COST," +
        "SELLING_UNIT_RETAIL,ATTR1_NAME,ATTR2_NAME,LAST_UPD_DATE,BATCH_SEQ,STATUS) VALUES ('"+  itemId + "'," +storeId + ","  + transType + ",to_Date('" + priceEffDate +
        "','yyyy-MM-dd'),to_Date('7777-7-7','yyyy-MM-dd')," + newPrice + "," + unitCost + "," + sellingUnitRetail + ",'" + attr1Name + "','" + attr2Name + "',to_date('"+ut.getSystemDate(1)+"','dd/mm/yyyy'),0,1) ";
//    System.out.println(sql);
    try {
      stmt = DAL.getConnection().createStatement();
      DAL.executeUpdate(sql,stmt);
      stmt.close();
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }
}
