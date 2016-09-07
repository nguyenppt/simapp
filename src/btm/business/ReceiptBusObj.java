package btm.business;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.sql.*;
import common.*;

public class ReceiptBusObj {
  public ReceiptBusObj() {
  }

  public ResultSet getDataDetail(String receiptID,
                          DataAccessLayer DAL,Statement stmt) {
    ResultSet rs = null;
    String sqlStr = "Select r.Receipt_ID, r.Store_ID, s.SUPP_NAME, r.RECEIPT_DATE, DESCRIPTION From BTM_IM_RECEIPT r ,BTM_IM_RECEIPT_ITEM ri,BTM_IM_SUPPLIER s,BTM_IM_ITEM_MASTER m Where r.RECEIPT_ID = ri.RECEIPT_ID and ri.ITEM_ID = m.ITEM_ID and r.SUPP_ID = s.SUPP_ID and r.RECEIPT_ID = '" + receiptID + "' order by ri.seq";
//    System.out.println(sqlStr);
    try {
      rs = DAL.executeScrollQueries(sqlStr,stmt);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  public ResultSet getData(String receiptID,
                    DataAccessLayer DAL) {
    ResultSet rs = null;
    String sqlStr = "Select ri.Item_ID, m.ART_NO, Item_Name, ad.ATTR_DTL_DESC, Qty, re.reason_desc, season_cde From BTM_IM_REASON re, BTM_IM_RECEIPT r ,BTM_IM_RECEIPT_ITEM ri,BTM_IM_SUPPLIER s,BTM_IM_ITEM_MASTER m, BTM_IM_ATTR_DTL ad , btm_im_season sea Where ri.season_id = sea.season_id and r.RECEIPT_ID = ri.RECEIPT_ID and ri.ITEM_ID = m.ITEM_ID and m.ATTR2_DTL_ID = ad.ATTR_DTL_ID and ri.reason_id = re.reason_id and r.SUPP_ID = s.SUPP_ID and r.RECEIPT_ID = '" + receiptID + "' order by seq";
//    System.out.println(sqlStr);
    try {
      rs = DAL.executeScrollQueries(sqlStr);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  public String getSupplierName(DataAccessLayer DAL, String sSupplierID) {
    String sTemp= "";
    ResultSet rs = null;
    try {
      String sql = "Select SUPP_NAME from BTM_IM_SUPPLIER where SUPP_ID = '" +
          sSupplierID + "'";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql);
      rs.beforeFirst();
      if (rs.next()){
        sTemp=rs.getString(0);
      }
      rs.getStatement().close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return sTemp;
  }

}
