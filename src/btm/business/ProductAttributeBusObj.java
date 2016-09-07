package btm.business;

import java.sql.*;
import common.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Phuoc Nguyen
 * @version 1.0
 */


public class ProductAttributeBusObj {
    public ProductAttributeBusObj() {
    }

    //get Attribute
    public ResultSet getAttribute(DataAccessLayer DAL,Statement stmt){
        ResultSet rs = null;
        rs = null;
        try {
            String sql = "select ATTR_ID, ATTR_DESC from BTM_IM_ATTR";
            rs = DAL.executeScrollQueries(sql,stmt);
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return rs;
    }

    // get Attribute Detail
    public ResultSet getAttrDetail(DataAccessLayer DAL, String attrId,Statement stmt){
        ResultSet rs = null;
        rs = null;
        try {
            String sql = "select ATTR_DTL_ID, ATTR_ID, ATTR_DTL_DESC,PROD_GROUP_ID from BTM_IM_ATTR_DTL" +
                    " where ATTR_ID ='" + attrId +"' order by PROD_GROUP_ID  desc";
            rs = DAL.executeScrollQueries(sql,stmt);
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return rs;
    }

    //get Attribute ATTR_DESC
    public String getAtrrDesc(DataAccessLayer DAL, String attrId,Statement stmt){
        ResultSet rs = null;
        rs = null;
        String desc ="";
        try {
            String sql = "select ATTR_DESC from BTM_IM_ATTR where ATTR_ID='" + attrId + "'";
            rs = DAL.executeScrollQueries(sql,stmt);
            if(rs.next()){
                desc = rs.getString("ATTR_DESC");
            }
            stmt.close();
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return desc;
    }
    //get product group
    public ResultSet getProdGroup(DataAccessLayer DAL, Statement stmt){
      ResultSet rs = null;
      String sql = "select child_id, child_name from btm_im_prod_hir where  recd_curr_flag = 1 and child_id in (select dept_id from BTM_IM_MAP_DEPARTMENT)";
      try{
        rs = DAL.executeScrollQueries(sql,stmt);
      }catch (Exception e){
        e.printStackTrace();
      }
      return rs;
    }
    //get product hierarchy child name
    public String getProdHirChildName(DataAccessLayer DAL, String ProdHirChildId,Statement stmt){
        ResultSet rs = null;
        rs = null;
        String childName ="";
        try {
            String sql = "select CHILD_NAME from BTM_IM_PROD_HIR where CHILD_ID='" + ProdHirChildId + "'";
            rs = DAL.executeScrollQueries(sql,stmt);
            if(rs.next()){
                childName = rs.getString("CHILD_NAME");
            }
            stmt.close();
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return childName;
    }

    //update attribute detail
    public void updateAttrDetail(DataAccessLayer DAL, String sizeColor, String attrID, String prodGroupID, String attrDtlId,Statement stmt){
        ResultSet rs = null;
        rs = null;
//        String attrId = getAtrrId(DAL, attr);
        try {
            String sql = "update BTM_IM_ATTR_DTL set ATTR_DTL_DESC ='" + sizeColor +
                "', attr_id = '" + attrID + "', prod_group_id = '" + prodGroupID +
                "' where ATTR_DTL_ID ='" + attrDtlId +"'";

            rs = DAL.executeQueries(sql,stmt);
//            System.out.println("_________:"+ sql);
            stmt.close();
       }
       catch (Exception e) {
           ExceptionHandle eh = new ExceptionHandle();
           eh.ouputError(e);
       }

    }
    //get Attribute ATTR_ID
       public String getAtrrId(DataAccessLayer DAL, String attrDesc,Statement stmt){
           ResultSet rs = null;
           rs = null;
           String id ="";
           try {
               String sql = "select ATTR_ID from BTM_IM_ATTR where ATTR_DESC='" + attrDesc + "'";
               rs = DAL.executeScrollQueries(sql,stmt);
               if(rs.next()){
                   id = rs.getString("ATTR_ID");
               }
               stmt.close();
           }
           catch (Exception e) {
               ExceptionHandle eh = new ExceptionHandle();
               eh.ouputError(e);
           }
           return id;
       }

    //get product hierarchy childid
    public String getProdHirChildId(DataAccessLayer DAL, String ProdHirChildName,Statement stmt){
        ResultSet rs = null;
        rs = null;
        String childId ="";
        try {
            String sql = "select CHILD_ID from BTM_IM_PROD_HIR where CHILD_NAME='" + ProdHirChildName + "'";
            rs = DAL.executeScrollQueries(sql,stmt);
            if(rs.next()){
                childId = rs.getString("CHILD_ID");
            }
            stmt.close();
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return childId;
    }

    //get
}
