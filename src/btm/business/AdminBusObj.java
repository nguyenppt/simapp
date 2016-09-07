package btm.business;
import java.sql.*;
import common.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AdminBusObj {
  Utils ut = new Utils();
  Statement stmt = null;
   public static final int LEN_ROLE_ID = 5;
   public static final int LEN_ROW_LIMIT = 5;
   public String role_ID ;
   public String role_Name ;
   public int rowlimit;
   public Vector vData ;
   public int rowSelected;

//  /** getData(DataAccessLayer DAL) - this method return the data from BTM_IM_CUSTOMER table
//     * @author Tuan Truong
//     * @param  String typeId, String name, int rowsLimit, DataAccessLayer DAL
//     * @return ResultSet
//     */
//    public ResultSet getAdminAccessLevel(DataAccessLayer DAL,Statement stmt){
//      ResultSet rs = null;
//      try{
//        String sql = "select * " +
//            " from BTM_IM_ACCESS_RIGHT";
//        System.out.println(sql);
//        rs = DAL.executeScrollQueries(sql,stmt);
//      }catch(Exception e){
//        ExceptionHandle eh = new ExceptionHandle();
//        eh.ouputError(e);
//      }
//      return rs;
//    }//end method

//    /** getData(DataAccessLayer DAL) - this method return the data from BTM_IM_CUSTOMER table
//       * @author Tuan Truong
//       * @param  String typeId, String name, int rowsLimit, DataAccessLayer DAL
//       * @return ResultSet
//       */
//      public ResultSet getAdminRole(DataAccessLayer DAL,Statement stmt){
//        ResultSet rs = null;
//        try{
//          String sql = "select * " +
//              " from BTM_IM_ROLE";
//          System.out.println(sql);
//          rs = DAL.executeScrollQueries(sql,stmt);
//        }catch(Exception e){
//          ExceptionHandle eh = new ExceptionHandle();
//          eh.ouputError(e);
//        }
//        return rs;
//      }//end method

      /** get last record and add +1 for type Id method
        * @author 		Tuan.Truong
        * @param 		String typeId, DataAccessLayer DAL
        * @return		no
        */
       public String getNewRoleId(DataAccessLayer DAL) {
         ResultSet rs = null;
         String st=null;
         String sSQL=null;
         try {
           int totalRecord = DAL.countRecord("select count(*) as RECORD_COUNT from BTM_IM_ROLE","RECORD_COUNT",DAL);
           if (totalRecord==0) {
             st=addNewRoleId("0",DAL);
             return st;
           }
           sSQL = "select ROLE_ID from BTM_IM_ROLE order by ROLE_ID desc";
//           System.out.println(sSQL);
           stmt = DAL.getConnection().createStatement(ResultSet.
                                         TYPE_SCROLL_INSENSITIVE,
                                         ResultSet.CONCUR_READ_ONLY);

           rs = DAL.executeScrollQueries(sSQL,stmt);
           rs.next();
           int k=Integer.parseInt(rs.getString("ROLE_ID").trim())+1;
           st=ut.addYeroCode(ut.PREFIX_CODE_LENGTH) + k;
           st=st.substring(st.length()- ut.PREFIX_CODE_LENGTH, st.length());
           rs.getStatement().close();
        }catch (Exception e) {
          ExceptionHandle eh = new ExceptionHandle();
          eh.ouputError(e);
         }
         return st;
       }//end method

      /** Create new RoleId
        * @author 		Tuan.Truong
        * @param 		no
        * @return		no
        */
       public String addNewRoleId(String roleId, DataAccessLayer DAL) {
         String st=null;
         int k=Integer.parseInt(roleId.trim())+1;
         st=ut.addYeroCode(ut.PREFIX_CODE_LENGTH) + k;
         st=st.substring(st.length()- ut.PREFIX_CODE_LENGTH, st.length());
         return roleId=st;
       }//end method get Parent


       /** getData(String custName, String address1, int rowsLimit, DataAccessLayer DAL) - this method return the data from BTM_IM_CUSTOMER table
          * @author Tuan Truong
          * @param  String typeId, String name, int rowsLimit, DataAccessLayer DAL
          * @return ResultSet
          */
         public ResultSet getAdminRoleData(String roleId, String desc, int rowsLimit, DataAccessLayer DAL,Statement stmt){
           ResultSet rs = null;
           try{
             String sSQL = "select * " +
                 " from BTM_IM_ROLE " +
                 " where (ROLE_ID like '%" + roleId.trim() +
                 "%') and (ROLE_NAME like '%" + desc.trim() + "%') and rownum <=" + rowsLimit +
                 " order by ROLE_ID ASC";
//             System.out.println(sSQL);
                        rs = DAL.executeScrollQueries(sSQL,stmt);
           }catch(Exception e){
             ExceptionHandle eh = new ExceptionHandle();
             eh.ouputError(e);
           }
           return rs;
         }//end method

         /** check last record method
           * @author 		Tuan.Truong
           * @param 		String typeId, DataAccessLayer DAL
           * @return		no
           */
          public boolean checkRole_AllowDel(String roleId, DataAccessLayer DAL) {
            boolean check=true;
            if (roleId.trim().length() ==0) return false;
            ResultSet rs = null;
            String st=null;
            try {
              int totalRecord = DAL.countRecord("select count(*) as RECORD_COUNT from BTM_IM_ROLE_ACCESS_RIGHT","RECORD_COUNT",DAL);
              if (totalRecord==0) {
                return check=false;
              }
              check=true;
           }catch (Exception e) {
             ExceptionHandle eh = new ExceptionHandle();
             eh.ouputError(e);
            }
            return check;
          }//end method

          /** Delete record
            * @author 	Tuan.Truong
            * @param 		String typeId, DataAccessLayer DAL
            * @return		no
            */
          public void deleteRole(String typeId, DataAccessLayer DAL){
            ResultSet rs = null;
            String sqlStr;
            sqlStr = "delete from BTM_IM_ROLE where ROLE_ID = '" + typeId.trim() + "'";
//            System.out.println(sqlStr);
            try{
              stmt = DAL.getConnection().createStatement(ResultSet.
                                                             TYPE_SCROLL_INSENSITIVE,
                                                             ResultSet.CONCUR_READ_ONLY);
              DAL.executeUpdate(sqlStr,stmt);
              stmt.close();
            }catch(Exception e){
              ExceptionHandle eh = new ExceptionHandle();
              eh.ouputError(e);
            }
          }//end method

//-------------------
          /** Create new RoleID
           * @author 		Tuan.Truong
           * @param 		no
           * @return		no
           */
          public int getNewAccessID(DataAccessLayer DAL) {
            ResultSet rs = null;
            int k=1;
            String sSQL = "select ACCESS_ID from BTM_IM_ACCESS_RIGHT order by ACCESS_ID desc";
//            System.out.println(sSQL);
            try {
              stmt = DAL.getConnection().createStatement();
              rs = DAL.executeQueries(sSQL,stmt);
              if (!rs.next()) {
                return 1;
              }else {
                k = rs.getInt("ACCESS_ID") + 1;
              }
              stmt.close();
            }catch (Exception e) {
              ExceptionHandle eh = new ExceptionHandle();
              eh.ouputError(e);
            }
            return k;
          }//end method get Parent

          /** Create new accessID
          * @author 		Tuan.Truong
          * @param 		no
          * @return		no
          */
          public String addNewAccessId(String roleId, DataAccessLayer DAL) {
                   String st=null;
                   int k=Integer.parseInt(roleId.trim())+1;
                   st=ut.addYeroCode(ut.PREFIX_CODE_LENGTH) + k;
                   st=st.substring(st.length()- ut.PREFIX_CODE_LENGTH, st.length());
                   return roleId=st;
           }//end method get Parent

           public void setData(String roleID, String roleName,int rows, Vector vData,int rowSelected) {
             this.role_ID = roleID;
             this.role_Name = roleName;
             this.rowlimit = rows;
             this.vData = vData;
             this.rowSelected = rowSelected;
           }
}
