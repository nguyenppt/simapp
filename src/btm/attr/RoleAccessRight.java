package btm.attr;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Tuan Truong
 * @version 1.0
 */

public class RoleAccessRight {
  public static final int LEN_DETAIL_NAME = 120;

  private String roleId;
  private String accId;
  private String accRight;

  public RoleAccessRight() {
  }

  public RoleAccessRight(String roleId, String accId, String accRight){
    this.roleId = roleId;
    this.accId = accId;
    this.accRight = accRight;
  }

  //set and get
  public String getRoleId(){
      return roleId;
  }
  public void setRoleId(String roleId){
      this.roleId= roleId;
  }

  public String getAccId(){
      return accId;
  }
  public void setAccId(String accId){
      this.accId= accId;
  }

  public String getAccRight(){
      return accRight;
  }
  public void setAccRight(String accRight){
      this.accRight= accRight;
  }
}
