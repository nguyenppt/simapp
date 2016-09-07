package btm.business;
import java.sql.*;
import common.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class HierBusObj {
  Utils ut = new Utils();

//--------Product Hier Setup
  /** getData(String custName, String address1, int rowsLimit, DataAccessLayer DAL) - this method return the data from BTM_IM_CUSTOMER table
    * @author Tuan Truong
    * @param  String typeId, String name, int rowsLimit, DataAccessLayer DAL
    * @return ResultSet
    */
   public ResultSet getPrdHierTypeData(String typeId, String name, int rowsLimit, DataAccessLayer DAL){
     ResultSet rs = null;
     try{
       String sql = "select * " +
           " from BTM_IM_PROD_HIR_TYPE " +
           " where TYPE_ID like '%" + typeId.trim() +
           "%' and NAME like '%" + name.trim() + "%' and rownum <=" + rowsLimit +
           " order by TYPE_ID ASC";
//       System.out.println(sql);
       rs = DAL.executeScrollQueries(sql);
     }catch(Exception e){
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
     return rs;
   }//end method


   /** get last record and add +1 for type Id method
     * @author 		Tuan.Truong
     * @param 		String typeId, DataAccessLayer DAL
     * @return		no
     */
    public String getNewTypeId(DataAccessLayer DAL) {
      ResultSet rs = null;
      String st=null;
      String sSQL = null;
      try {
        int totalRecord = DAL.countRecord("select count(*) as RECORD_COUNT from BTM_IM_PROD_HIR_TYPE","RECORD_COUNT",DAL);
        if (totalRecord==0) {
          st=addNewTypeId("0",DAL);
          return st;
        }
        sSQL = "select TYPE_ID from BTM_IM_PROD_HIR_TYPE order by TYPE_ID desc";
//        System.out.println(sSQL);
        rs = DAL.executeQueries(sSQL);
        rs.next();
        int k=Integer.parseInt(rs.getString("TYPE_ID").trim())+1;
        st=ut.addYeroCode(ut.PREFIX_CODE_LENGTH) + k;
        st=st.substring(st.length()- ut.PREFIX_CODE_LENGTH, st.length());
        rs.getStatement().close();
     }catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
      }
      return st;
    }//end method

   /** check last record method
     * @author 		Tuan.Truong
     * @param 		String typeId, DataAccessLayer DAL
     * @return		no
     */
    public boolean checkEndTypeId(String typeId, DataAccessLayer DAL) {
      boolean check=false;
      if (typeId.trim().length() ==0) return false;
      ResultSet rs = null;
      String st=null;
      String sSQL=null;
      try {
        int totalRecord = DAL.countRecord("select count(*) as RECORD_COUNT from BTM_IM_PROD_HIR_TYPE","RECORD_COUNT",DAL);
        if (totalRecord==0) {
          return check=false;
        }
        sSQL = "select TYPE_ID from BTM_IM_PROD_HIR_TYPE order by TYPE_ID desc";
//        System.out.println(sSQL);
        rs = DAL.executeQueries(sSQL);
        rs.next();
        if (typeId.trim().equalsIgnoreCase(rs.getString("TYPE_ID").trim())) {
          check=true;
        }
        rs.getStatement().close();
     }catch (Exception e) {
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
      }
      return check;
    }//end method


    /** Create new TypeID
     * @author 		Tuan.Truong
     * @param 		no
     * @return		no
     */
    public String addNewTypeId(String typeId, DataAccessLayer DAL) {
      String st=null;
      int k=Integer.parseInt(typeId.trim())+1;
      st=ut.addYeroCode(ut.PREFIX_CODE_LENGTH) + k;
      st=st.substring(st.length()- ut.PREFIX_CODE_LENGTH, st.length());
      return typeId=st;
//      if (checkEndTypeId(typeId, DAL)) {
    }//end method get Parent

    /** Get TypeID method
      * @author 		Tuan.Truong
      * @param 		no
      * @return		no
      */
     public String getTypeID(String name,DataAccessLayer DAL) {
       ResultSet rs = null;
       String st=null;
       String sSQL = null;
       if (name==null) return null;
       if (name==Constant.root) {
         st=Constant.rootId;
         return st;
       }
       sSQL = "select TYPE_ID, NAME from BTM_IM_PROD_HIR_TYPE where NAME='" + name + "'";
//       System.out.println(sSQL);
       rs = DAL.executeQueries(sSQL);
       try {
         rs.next();
         st=rs.getString("TYPE_ID");
         rs.getStatement().close();
       }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
        }
       return st;
     }//end method get Parent


    /** Delete record
      * @author 	Tuan.Truong
      * @param 		String typeId, DataAccessLayer DAL
      * @return		no
      */
    public void deletePrdHierTypeData(String typeId, DataAccessLayer DAL){
      ResultSet rs = null;
      String sqlStr;
      sqlStr = "delete from BTM_IM_PROD_HIR_TYPE where TYPE_ID = '" + typeId.trim() + "'";
//      System.out.println(sqlStr);
      try{
        DAL.executeUpdate(sqlStr);
      }catch(Exception e){
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(e);
      }
    }//end method

    /** getData(String custName, String address1, int rowsLimit, DataAccessLayer DAL) - this method return the data from BTM_IM_CUSTOMER table
      * @author Tuan Truong
      * @param  String typeId, String name, int rowsLimit, DataAccessLayer DAL
      * @return ResultSet
      */
     public ResultSet getOrgHierTypeData(String typeId, String name, int rowsLimit, DataAccessLayer DAL){
       ResultSet rs = null;
       try{
         String sSQL = "select * " +
             " from BTM_IM_ORG_HIR_TYPE " +
             " where TYPE_ID like '%" + typeId.trim() +
             "%' and NAME like '%" + name.trim() + "%' and rownum <=" + rowsLimit +
             " order by TYPE_ID ASC";
//         System.out.println(sSQL);
         rs = DAL.executeScrollQueries(sSQL);
       }catch(Exception e){
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
       }
       return rs;
     }//end method


     /** get last record and add +1 for type Id method
       * @author 		Tuan.Truong
       * @param 		String typeId, DataAccessLayer DAL
       * @return		no
       */
      public String getNewOrgTypeId(DataAccessLayer DAL) {
        ResultSet rs = null;
        String st=null;
        String sSQL=null;
        try {
          int totalRecord = DAL.countRecord("select count(*) as RECORD_COUNT from BTM_IM_ORG_HIR_TYPE","RECORD_COUNT",DAL);
          if (totalRecord==0) {
            st=addNewOrgTypeId("0",DAL);
            return st;
          }
          sSQL = "select TYPE_ID from BTM_IM_ORG_HIR_TYPE order by TYPE_ID desc";
//          System.out.println(sSQL);
          rs = DAL.executeQueries(sSQL);
          rs.next();
          int k=Integer.parseInt(rs.getString("TYPE_ID").trim())+1;
          st=ut.addYeroCode(ut.PREFIX_CODE_LENGTH) + k;
          st=st.substring(st.length()- ut.PREFIX_CODE_LENGTH, st.length());
          rs.getStatement().close();
       }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
        }
        return st;
      }//end method

     /** check last record method
       * @author 		Tuan.Truong
       * @param 		String typeId, DataAccessLayer DAL
       * @return		no
       */
      public boolean checkEndOrgTypeId(String typeId, DataAccessLayer DAL) {
        boolean check=false;
        String sSQL=null;
        if (typeId.trim().length() ==0) return false;
        ResultSet rs = null;
        String st=null;
        try {
          int totalRecord = DAL.countRecord("select count(*) as RECORD_COUNT from BTM_IM_ORG_HIR_TYPE","RECORD_COUNT",DAL);
          if (totalRecord==0) {
            return check=false;
          }
          sSQL = "select TYPE_ID from BTM_IM_ORG_HIR_TYPE order by TYPE_ID desc";
//          System.out.println(sSQL);
          rs = DAL.executeQueries(sSQL);
          rs.next();
          if (typeId.trim().equalsIgnoreCase(rs.getString("TYPE_ID").trim())) {
            check=true;
          }
          rs.getStatement().close();
       }catch (Exception e) {
         ExceptionHandle eh = new ExceptionHandle();
         eh.ouputError(e);
        }
        return check;
      }//end method


      /** Create new TypeID
       * @author 		Tuan.Truong
       * @param 		no
       * @return		no
       */
      public String addNewOrgTypeId(String typeId, DataAccessLayer DAL) {
        String st=null;
        int k=Integer.parseInt(typeId.trim())+1;
        st=ut.addYeroCode(ut.PREFIX_CODE_LENGTH) + k;
        st=st.substring(st.length()- ut.PREFIX_CODE_LENGTH, st.length());
        return typeId=st;
//      if (checkEndTypeId(typeId, DAL)) {
      }//end method get Parent

      /** Get TypeID method
        * @author 		Tuan.Truong
        * @param 		no
        * @return		no
        */
       public String getOrgTypeID(String name,DataAccessLayer DAL) {
         ResultSet rs = null;
         String st=null;
         String sSQL=null;
         if (name==null) return null;
         if (name==Constant.root) {
           st=Constant.rootId;
           return st;
         }
         sSQL = "select TYPE_ID, NAME from BTM_IM_ORG_HIR_TYPE where NAME='" + name + "'";
//         System.out.println(sSQL);
         rs = DAL.executeQueries(sSQL);
         try {
           rs.next();
           st=rs.getString("TYPE_ID");
           rs.getStatement().close();
         }catch (Exception e) {
           ExceptionHandle eh = new ExceptionHandle();
           eh.ouputError(e);
          }
         return st;
       }//end method get Parent


      /** Delete record
        * @author 	Tuan.Truong
        * @param 		String typeId, DataAccessLayer DAL
        * @return		no
        */
      public void deleteOrgHierTypeData(String typeId, DataAccessLayer DAL){
        ResultSet rs = null;
        String sqlStr;
        sqlStr = "delete from BTM_IM_ORG_HIR_TYPE where TYPE_ID = '" + typeId.trim() + "'";
//        System.out.println(sqlStr);
        try{
          DAL.executeUpdate(sqlStr);
        }catch(Exception e){
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(e);
        }
      }//end method

}
