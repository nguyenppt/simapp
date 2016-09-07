package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author
 * @version 1.0
 */
import java.util.*;
import common.DataAccessLayer;
import java.sql.*;

public class EmpRight {
  private boolean add;
  private boolean delete;
  private boolean modify;
  private boolean search;
  private boolean report;
  Vector vEmpAccRightAll = new Vector();
  Statement stmt = null;
  public static final String NO_ACC_RIGHT = "00000";
  public static final String FULL_ACC_RIGHT = "11111";

  public EmpRight() {
  }

  public void initData(DataAccessLayer DAL, String empId) {
      ObjEmpAccRight oEmpAccRight;
      if (empId ==null) {
        vEmpAccRightAll=null;
        return ;
      }
      String sSQL = "select s2.emp_id, s2.emp_cde, s1.role_id, s1.scr_id, s1.access_right from btm_im_role_access_right s1, " +
                          "(select a1.emp_id, a1.emp_cde, a2.role_id from btm_im_employee a1, btm_im_employee_right a2 "+
                          "where a1.emp_id = a2.emp_id and a1.emp_id = '" + empId + "') s2 "+
                    "where s1.role_id = s2.role_id";
      ResultSet rs = null;
      try {
        stmt = DAL.getConnection().createStatement();
        rs = DAL.executeQueries(sSQL,stmt);
        if (!rs.isBeforeFirst() && !rs.isAfterLast()) {
          vEmpAccRightAll=null;
          stmt.close();
          return ;
        }else {
          while (rs.next()) {
            oEmpAccRight = new ObjEmpAccRight(rs.getString("EMP_ID"),
                                              rs.getString("EMP_CDE"),
                                              rs.getString("ROLE_ID"),
                                              rs.getString("SCR_ID"),
                                              rs.getString("ACCESS_RIGHT"));
            vEmpAccRightAll.add(oEmpAccRight);
          }//while
          stmt.close();
        }
      }catch (Exception e) {
        e.printStackTrace();
      }
  }

  public void setData(String empId, String roleId, String scrId) {
    if (vEmpAccRightAll==null || vEmpAccRightAll.size()==0) {
      setEmpAccRight(NO_ACC_RIGHT);
      return;
    }else {
      setEmpAccRight(getEmpAccRight(empId, roleId, scrId));
    }
  }

  public void setData(String empId, int scrId) {
//    admin has full righs
    if (empId.equals("0000000001007") || empId.equals("0000000002004")) {
      setAdd(1);
      setDelete(1);
      setModify(1);
      setSearch(1);
      setReport(1);
      return;
    }
    if (vEmpAccRightAll==null || vEmpAccRightAll.size()==0) {
      setEmpAccRight(NO_ACC_RIGHT);
      return;
    }else {
      String roleIdTmp;
      String scrIdTmp;
      String accRight;
      String stScrId = String.valueOf(scrId).toString().trim();
      int add = 0;
      int delete = 0;
      int modify = 0;
      int search = 0;
      int report = 0;
      int i = 0;
//      boolean chkAdmin=false;
      String roleId;
      while (i < vEmpAccRightAll.size()) {
        ObjEmpAccRight oEmpAccRight;
        oEmpAccRight = (ObjEmpAccRight) vEmpAccRightAll.get(i);
        roleId = oEmpAccRight.getRoleId();
        if ( (empId.equals(oEmpAccRight.getEmpId())) &&
            stScrId.equals(oEmpAccRight.getScrId())) {
          accRight = oEmpAccRight.getRoleAccess().toString().trim();
          if (i == 0) setEmpAccRight(accRight); //the first
          add = Integer.parseInt(accRight.substring(0, 1));
          delete = Integer.parseInt(accRight.substring(1, 2));
          modify = Integer.parseInt(accRight.substring(2, 3));
          search = Integer.parseInt(accRight.substring(3, 4));
          report = Integer.parseInt(accRight.substring(4, 5));
          if (!getAdd()) {
            if (add == 1)
              setAdd(1);
            else
              setAdd(0);
          }

          if (!getDelete()) {
            if (delete == 1)
              setDelete(1);
            else
              setDelete(0);
          }

          if (!getModify()) {
            if (modify == 1)
              setModify(1);
            else
              setModify(0);
          }

          if (!getSearch()) {
            if (search == 1)
              setSearch(1);
            else
              setSearch(0);
          }

          if (!getReport()) {
            if (report == 1)
              setReport(1);
            else
              setReport(0);
          }
        } //end if
        i = i + 1;
      } //end while
  }//end else
}//end method

  //get vector when user login
  //tuan.truong
  public String getEmpAccRight(String empId, String roleId, String scrId) {
    ObjEmpAccRight oEmpAccRight;
    String empIdTmp =null;
    String roleIdTmp=null;
    String accIdTmp=null;
    int i=0;
    while (i < vEmpAccRightAll.size()) {
       oEmpAccRight = (ObjEmpAccRight) vEmpAccRightAll.get(i);
       empIdTmp=oEmpAccRight.getEmpId().toString().trim();
       roleIdTmp=oEmpAccRight.getRoleId().toString().trim();
       accIdTmp=oEmpAccRight.getScrId().toString().trim();
       if (empIdTmp.equals(empId) && roleIdTmp.equals(roleId) && accIdTmp.equals(scrId)) {
         return oEmpAccRight.getRoleAccess();
       }
       i += 1;
     }
     if (roleId.equals("001") || roleId.equals("002")) return FULL_ACC_RIGHT;//admin BTM and admin Adidas
     return NO_ACC_RIGHT;
  }

  public void setEmpAccRight(String accessRight){
    //add, delete, modify, search, report
    //ex : 10101 -> add = true, delete = false, modify = true, search = false, report = true;
    int add = Integer.parseInt(accessRight.substring(0,1));
    int delete = Integer.parseInt(accessRight.substring(1,2));
    int modify = Integer.parseInt(accessRight.substring(2,3));
    int search = Integer.parseInt(accessRight.substring(3,4));
    int report = Integer.parseInt(accessRight.substring(4,5));

    if (add == 1) this.add = true;
    else this.add = false;

    if (delete == 1) this.delete = true;
    else this.delete = false;

    if (modify == 1) this.modify = true;
    else this.modify = false;

    if (search == 1) this.search = true;
    else this.search = false;

    if (report == 1) this.report = true;
    else this.report = false;
  }//end Hierarchy

  //set and get
  public boolean getAdd() {
    return add;
  }
  public void setAdd(int add) {
    if (add==1) this.add = true;
    else this.add = false;
  }


  public boolean getDelete() {
    return this.delete;
  }
  public void setDelete(int delete) {
    if (delete==1) this.delete = true;
    else this.delete = false;
  }

  public boolean getModify() {
    return this.modify;
  }
  public void setModify(int modify) {
    if (modify==1) this.modify = true;
    else this.modify = false;
  }

  public boolean getSearch() {
    return this.search;
  }
  public void setSearch(int search) {
    if (search==1) this.search = true;
    else this.search = false;
  }

  public boolean getReport() {
    return this.report;
  }
  public void setReport(int report) {
    if (report==1) this.report = true;
    else this.report = false;
  }
}
