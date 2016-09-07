package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */

public class ObjEmpAccRight {

  private String empId;
  private String empCode;
  private String roleId;
  private String scrId;
  private String roleAccess;


  public ObjEmpAccRight() {
  }

  public ObjEmpAccRight(String empId, String empCode, String roleId, String scrId, String roleAccess) {
    this.empId = empId;
    this.empCode = empCode;
    this.roleId = roleId;
    this.scrId = scrId;
    this.roleAccess = roleAccess;
  }//end Hierarchy

  //set and get
  public String getEmpId(){
      return empId;
  }
  public void setEmpId(String empId){
      this.empId= empId;
  }

  public String getEmpCode(){
      return empCode;
  }
  public void setEmpCode(String empCode){
      this.empCode= empCode;
  }

  public String getRoleId(){
      return roleId;
  }
  public void setRoleId(String roleId){
      this.roleId= roleId;
  }

  public String getScrId(){
      return scrId;
  }
  public void setScrId(String scrId){
      this.scrId= scrId;
  }

  public String getRoleAccess(){
      return roleAccess;
  }
  public void setRoleAccess(String roleAccess){
      this.roleAccess= roleAccess;
  }
}
