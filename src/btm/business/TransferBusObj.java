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

public class TransferBusObj {
  public TransferBusObj() {
  }
//  ResultSet getDataDetail(String storeID, String suppID,String date) {
//     ResultSet rs = null;
//     String sqlStr = "Select r.Receipt_ID, ri.Item_ID, Supp_Name, Qty,DESCRIPTION From BTM_IM_RECEIPT r ,BTM_IM_RECEIPT_ITEM ri,BTM_IM_SUPPLIER s,BTM_IM_ITEM_MASTER m Where r.RECEIPT_ID = ri.RECEIPT_ID and ri.ITEM_ID = m.ITEM_ID and r.SUPP_ID = s.SUPP_ID and r.Store_ID=" + storeID + " and r.Supp_ID='" + suppID + "' and Receipt_Date=to_date('" + date + "','dd-MM-yyyy')";
//     System.out.println(sqlStr);
//     try {
//       rs = DAL.executeScrollQueries(sqlStr);
//     }
//     catch (Exception e) {
//       ExceptionHandle eh = new ExceptionHandle();
//       eh.ouputError(e);
//     }
//     return rs;
//
//   }
//
// ResultSet getData(String storeID, String suppID,String date) {
//   ResultSet rs = null;
//   String sqlStr = "Select ri.Item_ID , Item_Name, Qty From BTM_IM_RECEIPT r ,BTM_IM_RECEIPT_ITEM ri,BTM_IM_SUPPLIER s,BTM_IM_ITEM_MASTER m Where r.RECEIPT_ID = ri.RECEIPT_ID and ri.ITEM_ID = m.ITEM_ID and r.SUPP_ID = s.SUPP_ID and r.Store_ID=" + storeID + " and r.Supp_ID='" + suppID + "' and Receipt_Date=to_date('" + date + "','dd-MM-yyyy')";
//   System.out.println(sqlStr);
//   try {
//     rs = DAL.executeScrollQueries(sqlStr);
//   }
//   catch (Exception e) {
//     ExceptionHandle eh = new ExceptionHandle();
//     eh.ouputError(e);
//   }
//   return rs;
//
// }

  public ResultSet getDataWithDate(String sDate,DataAccessLayer DAL,Statement stmt,String fromStore, String toStore){
    ResultSet rs=null;
    String sql = "";
    try{
        sql = "select transfer_id, s1.name, s2.name,status"+
            " from btm_im_transfer t, btm_im_store s1, btm_im_store s2 where t.FROM_STORE_ID = s1.STORE_ID and t.TO_STORE_ID = s2.STORE_ID and TRANSFER_DATE = to_date('"+sDate+"','DD-MM-YYYY')";
        if (!fromStore.equalsIgnoreCase("")){
          sql = sql + " and s1.store_id = " + Long.parseLong(fromStore);
        }
        if (!toStore.equalsIgnoreCase("")){
          sql = sql + " and s2.store_id = " + Long.parseLong(toStore);
        }
//        System.out.println(sql);
        rs = DAL.executeScrollQueries(sql,stmt);
      }catch(Exception e){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      return rs;
  }
  public ResultSet getDataWithDateDamageGoods(String sDate,DataAccessLayer DAL,Statement stmt,String fromStore, String toStore){
    ResultSet rs=null;
    String sql = "";
    try{
        sql = "select dmg_goods_id, s1.name, s2.name"+
            " from btm_im_transfer_dmg_goods t, btm_im_store s1, btm_im_store s2 where t.FROM_STORE_ID = s1.STORE_ID and t.TO_STORE_ID = s2.STORE_ID and TRANSFER_DATE = to_date('"+sDate+"','DD-MM-YYYY')";
        if (!fromStore.equalsIgnoreCase("")){
          sql = sql + " and s1.store_id = " + Long.parseLong(fromStore);
        }
        if (!toStore.equalsIgnoreCase("")){
          sql = sql + " and s2.store_id = " + Long.parseLong(toStore);
        }
//        System.out.println(sql);
        rs = DAL.executeScrollQueries(sql,stmt);
      }catch(Exception e){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      return rs;
  }

  public ResultSet getUserNameWithUserID(String sUserID,DataAccessLayer DAL){
    ResultSet rs=null;
    try{
        String sql = "Select concat(FIRST_NAME,concat(' ',LAST_NAME)) as NAME from BTM_IM_EMPLOYEE where EMP_ID = '" + sUserID +"'";
//        System.out.println(sql);
        rs = DAL.executeScrollQueries(sql);
    }catch(Exception e){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
    }
    return rs;
  }

  public ResultSet getDataTransferWithID(String sID,DataAccessLayer DAL,Statement stmt){
    ResultSet rs=null;
    try{
        String sql = "Select TRANSFER_ID, s1.NAME FROM_STORE_ID, s2.NAME TO_STORE_ID, USER_ID, RECEIVER_NAME, to_char(TRANSFER_DATE,'DD/MM/YYYY') TRANSFER_DATE"+
                     " from BTM_IM_TRANSFER t, BTM_IM_STORE s1, BTM_IM_STORE s2 Where t.FROM_STORE_ID = s1.STORE_ID and t.TO_STORE_ID = s2.store_id and TRANSFER_ID = '"  + sID + "'";
//        System.out.println(sql);
        rs = DAL.executeScrollQueries(sql,stmt);
      }catch(Exception e){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      return rs;
  }
  //Damage goods
  public ResultSet getDataTransferWithIDDmgGoods(String sID,DataAccessLayer DAL,Statement stmt){
    ResultSet rs=null;
    try{
        String sql = "Select dmg_goods_ID, s1.NAME FROM_STORE_ID, s2.NAME TO_STORE_ID, USER_ID, RECEIVER_NAME, to_char(TRANSFER_DATE,'DD/MM/YYYY') TRANSFER_DATE"+
                     " from BTM_IM_TRANSFER_DMG_GOODS t, BTM_IM_STORE s1, BTM_IM_STORE s2 Where t.FROM_STORE_ID = s1.STORE_ID and t.TO_STORE_ID = s2.store_id and dmg_goods_ID = '"  + sID + "'";
//        System.out.println(sql);
        rs = DAL.executeScrollQueries(sql,stmt);
      }catch(Exception e){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
      return rs;
  }


  public ResultSet getDataTransferItemWithID(String sID,DataAccessLayer DAL){
  ResultSet rs=null;
  try{
      String sql = "Select im.Item_id, im.art_no, im.item_name, ad.ATTR_DTL_DESC, QTY, reason_desc"+
                   " from BTM_IM_TRANSFER_ITEM t, BTM_IM_ITEM_MASTER im, BTM_IM_ATTR_DTL ad, btm_im_reason re Where t.reason_id = re.reason_id and t.item_id = im.item_id and im.ATTR2_DTL_ID = ad.ATTR_DTL_ID and TRANSFER_ID = '"  + sID + "' order by seq";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }
  //damge good item
  public ResultSet getDataTransferItemWithIDDmgGoods(String sID,DataAccessLayer DAL){
  ResultSet rs=null;
  try{
      String sql = "Select im.Item_id, im.art_no, im.item_name, ad.ATTR_DTL_DESC, QTY, reason_desc"+
                   " from BTM_IM_TRANSFER_DMG_GOODS_ITEM t, BTM_IM_ITEM_MASTER im, BTM_IM_ATTR_DTL ad, btm_im_reason re Where t.reason_id = re.reason_id and t.item_id = im.item_id and im.ATTR2_DTL_ID = ad.ATTR_DTL_ID and DMG_GOODS_ID = '"  + sID + "' order by seq";
//      System.out.println(sql);
      rs = DAL.executeScrollQueries(sql);
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

}
