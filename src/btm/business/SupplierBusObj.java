package btm.business;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author Nghi Doan
 * @version 1.0
 */
import java.sql.*;
import common.*;

public class SupplierBusObj {
    //Set Data
    String suppId = "";
    String suppName = "";
    String abbreName = "";
    String contactName = "";
    String contactPhone = "";

    String suppIdLookup = "";
	public SupplierBusObj() {
	}

	/** checkLength(String string, int length) - this method will check the limit of length
	 * @author Nghi Doan
	 * @param string
	 * @param length
	 */
	public boolean checkLength(String string, int length) {
		if (string.length() > length) {
			return true;
		}
		return false;
	}

	/** getData(String suppName, String contactName, int rowsLimit, DataAccessLayer DAL) - this method return the data from BTM_IM_CUSTOMER table
	 * @author Nghi Doan
	 * @param suppName
	 * @param contactName
	 * @param rowsLimit
	 * @param DAL
	 */
//	public ResultSet getData(String suppName, String contactName, int rowsLimit,
//							 DataAccessLayer DAL) {
//		ResultSet rs = null;
//		try {
//			String sql = "select * " +
//					" from BTM_IM_SUPPLIER where lower(supp_name) like lower('%" +
//					suppName.trim() +
//					"%') and lower(contact_name) like lower('%" + contactName +
//					"%') and rownum <=" + rowsLimit;
//			rs = DAL.executeScrollQueries(sql);
//		}
//		catch (Exception e) {
//			ExceptionHandle eh = new ExceptionHandle();
//			eh.ouputError(e);
//		}
//		return rs;
//	}

//Phuoc.nguyen----------------------------------------------------------------------------------//
//Get Currency Code, return a ResultSet
	public ResultSet getCurrency(DataAccessLayer DAL,Statement stmt) {
		ResultSet rs = null;
		try {
			String sql = "select curr_id, curr_id from btm_im_currency_cde";
			rs = DAL.executeScrollQueries(sql,stmt);
		}
		catch (Exception e) {
			ExceptionHandle eh = new ExceptionHandle();
			eh.ouputError(e);
		}
		return rs;
	}

//Phuoc.nguyen----------------------------------------------------------------------------------//
//Get Currency Code, return a ResultSer
	public ResultSet getCountry(DataAccessLayer DAL,Statement stmt) {
		ResultSet rs = null;
		try {
			String sql = "select cntry_cde, cntry_name from btm_im_country_cde order by cntry_name desc";
			rs = DAL.executeScrollQueries(sql,stmt);
		}
		catch (Exception e) {
			ExceptionHandle eh = new ExceptionHandle();
			eh.ouputError(e);
		}
		return rs;
	}
//Phuoc.nguyen----------------------------------------------------------------------------------//
//Get max value suppiler ID , return a number in long integer
	public int getMaxSuppID(DataAccessLayer DAL, String hostId){
		ResultSet rs = null;
		int value = 0;
        String id=null;
        String host = hostId + "%";
		try {
			String sql = "select SUPP_ID from BTM_IM_SUPPLIER where SUPP_ID like '" + host + "' order by SUPP_ID DESC ";
//                        System.out.println(sql);
			rs = DAL.executeScrollQueries(sql);
			if(!rs .isBeforeFirst() && !rs.isAfterLast()) return 0;
                        if (rs.next()) {
                          id = rs.getString("SUPP_ID").toString().trim();
                          value = Integer.parseInt(id);
                        }
                        rs.getStatement().close();
		}
		catch (Exception e) {
			ExceptionHandle eh = new ExceptionHandle();
			eh.ouputError(e);
		}
		return value;
	}
//Phuoc.nguyen----------------------------------------------------------------------------------//
//Get data  UPP_ID, SUPP_NAME, CONTACT_NAME, CONTACT_PHONE for search Supplier
    public ResultSet getData(String suppId, String suppName,String abbName, String contactName, String contactPhone, int rowsLimit, DataAccessLayer DAL,Statement stmt){
        String sql = "select SUPP_ID, SUPP_NAME, ABBREVIATION_NAME, CONTACT_NAME, CONTACT_PHONE " +
                " from BTM_IM_SUPPLIER where rownum <=" + rowsLimit;
        if (!suppId.trim().equalsIgnoreCase("")) {
            sql = sql + " and lower(SUPP_ID) like lower('%" + suppId.trim() +
                    "%') ";
        }
        if (!suppName.trim().equalsIgnoreCase("")) {
            sql = sql + " and lower(SUPP_NAME) like lower('%" + suppName.trim() +
                    "%') ";
        }
        if (!contactName.trim().equalsIgnoreCase("")) {
            sql = sql + " and lower(CONTACT_NAME) like lower('%" + contactName +
                    "%') ";
        }
        if (!contactPhone.trim().equalsIgnoreCase("")) {
            sql = sql + " and lower(CONTACT_PHONE) like lower('%" +
                    contactPhone + "%') ";
        }
        if (!abbName.trim().equalsIgnoreCase("")) {
          sql = sql + " and lower(ABBREVIATION_NAME) like lower('%" +
              abbName + "%') ";
        }

        ResultSet rs = null;

        try {
            rs = DAL.executeScrollQueries(sql,stmt);
        }
        catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
        }
        return rs;
    }

//Phuoc.nguyen----------------------------------------------------------------------------------//
//set value for modify
    public void setValue(String suppId, String suppName, String abbName, String contactName, String phone){
        this.suppId = suppId;
        this.suppName = suppName;
        this.abbreName = abbName;
        this.contactPhone = phone;
        this.contactName = contactName;
    }

    public String getSuppId() {
        return this.suppId;
    }

    public String getSuppName() {
        return this.suppName;
    }

    public String getAbbreName(){
        return this.abbreName;
    }
    public String getContactName() {
        return this.contactName;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public void setSuppIdLookup(String id){
        suppIdLookup = id;
    }

    public String getSuppIdLookup(){
        return suppIdLookup;
    }
}
