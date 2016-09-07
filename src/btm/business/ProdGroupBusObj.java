package btm.business;

import java.sql.*;
import common.*;
import java.util.*;
/**
 * <p>Title: Purchase Order Business  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author : Vinh Le
 * @version 1.0
 */
public class ProdGroupBusObj {
   Utils ut = new Utils();
//   Statement stmt = null;
   public static final int LEN_PO_ID = 12;
   public static final int LEN_UPC_ID = 13;
   public String pg_ID ;
   public String store_Name ;
   public String supp_Name ;
   public String created_Date;
   public String type;
   public String status;
   public String desc;
   public int rowlimit;
   public Vector vData ;
   public Vector vStatus ;
   public int rowSelected;

  public ProdGroupBusObj() {
  }
  public void setPOID(String poID){
    this.pg_ID = poID;
  }
  public String getPOID(){
    return this.pg_ID;
  }
  public void setData(String poID, String storeName, String suppName, String created_Date, String type,String status,String desc,int rows, Vector vData, Vector vStatus, int rowSelected) {
    this.pg_ID = poID;
    this.store_Name = storeName;
    this.supp_Name = suppName;
    this.created_Date = created_Date;
    this.type = type;
    this.status = status;
    this.desc = desc;
    this.rowlimit = rows;
    this.vData = vData;
    this.vStatus = vStatus;
    this.rowSelected = rowSelected;
 }
 public String getProdGroupIdCanInsertPOS(DataAccessLayer DAL){
    String id = new String();
     try {
       ResultSet rs = DAL.executeScrollQueries("Select PROD_GROUP_ID from BTM_IM_PROD_GROUP where PROD_GROUP_ID like '" + DAL.getStoreID()+DAL.getHostID() + "%' Order by PROD_GROUP_ID DESC");
       rs.beforeFirst();
       if (!rs.next()){
         rs.getStatement().close();
         return DAL.getStoreID()+DAL.getHostID()+"0000001";
       }
       String strTemp1 = rs.getString("PROD_GROUP_ID");
       strTemp1 = strTemp1.substring(1,strTemp1.length());
       long iTemp = Long.parseLong(strTemp1)+1;
       //Add yero
       String st =null;
       int pos1=0;
       int pos2=0;
       st=ut.addYeroCode(7) + iTemp;
       pos1=st.length()- 7;
       pos2=st.length();
       if (pos1 < 0) {
         st = String.valueOf(iTemp).toString();
         return st;
       }
       st=st.substring(pos1, pos2);
       id=DAL.getStoreID()+DAL.getHostID()+st;
       rs.getStatement().close();
     }
     catch(Exception ex) {
       ex.printStackTrace();
     }

    return id;
   }

}

