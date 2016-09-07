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

public class PhasesBusObject {
  private DataAccessLayer DAL;

  public PhasesBusObject() {
  }

  public DataAccessLayer getDataAccessLayer() {
    return DAL;
  }

  public void setDataAccessLayer(DataAccessLayer DAL) {
    this.DAL = DAL;
  }

  /** getDataIntoCombo method - get Phase_ID and Phase_Desc
   * @author 		Hieu.Pham
   * @param 		no
   * @return		ResultSet
   */
  public ResultSet getDataIntoCombo(String Season_ID,Statement stmt) {
//    DAL.getOracleConnect();//temp
    ResultSet rs = null;
    try {
//      System.out.println("select Phase_ID, Phase_Desc from BTM_IM_PHASES where Season_ID=" + Season_ID);
      rs = DAL.executeScrollQueries("select Phase_ID, Phase_Desc from BTM_IM_PHASES where Season_ID=" + Season_ID,stmt);
    }catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }//end method

}
