package common;

/*
 * Created on Oct 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * @author Tuan.Truong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * Ver 1.1 - 11-08-2004
 */
import java.io.RandomAccessFile;
import java.sql.*;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.util.Vector;

import oracle.jdbc.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class DataAccessLayer {
  //Constant
  public final String DELIM_CONFIG = "=";
  public final String DELIM_CALC = ";";
  //Variable
  private int fetchSize = 1000; //chunk data size
  private String pathFile = ""; // not use
  private static String resourceFile = "";
  private String firstAutoNum = ""; //input file for Calc Eng
  private String secAutoNum = ""; //input file for Calc Eng
  private String prefixIputCalcEngFile = ""; //prefix of input Calc Eng file
  private String outputCalcEngFile = ""; //output file for Calc Eng
  private String prefixOutputCalcEngFile = ""; //prefix of output Calc Eng file
  private String suffixOutInCalcEngFile = ""; //output and input file of Calc Eng have the same type
  private String errorFile = ""; //error log file
  private String transactionDate = "";

  private String companyName = "";
  private String companyAdr = "";
  private String companyTel = "";
  private String companyFax = "";
  private String companyVAT = "";
  private String addrDelivery = "";
  private String addrBill = "";

  private String observerAccount = "";//use for delete or return
  private String viewerAccount = ""; //use for view trans

  private String franchiseCust = "";
  private String hiddenNeuron = "";
  private String outputNeuron = "";
  private String inputColumn = "";
  private String outputAddnewItemFile = ""; // not use
  private String storeID = ""; // get Store ID from Database
  private String storeName = ""; // get Store Name from Database
  private String storeAddress = ""; // get Store Address from Database
  private String registerID = ""; // get Register ID from Database
  private String hostID = ""; //get host ID from Database
  private String employeeID = "";
  private String currencyID = "";
  private int numOfStore = 0;
  private String searchLimit = "";
  private String turnOffAcrobat = "";
  private String acrobatFile = "";
  private int delayBeforePrint = 0;
  private int delayAfterPrint = 0;
  Connection conn = null;
  Statement stmtSelect = null;
  Statement stmtUpdate = null;
  public static String simEmpID = "";
  public static String simEmpCode = "";
  public static String posEmpID = "";
  public static String posEmpID1 = "";

  String driver_class = ""; //"oracle.jdbc.driver.OracleDriver";
  String thinConn = ""; //"jdbc:oracle:thin:@192.168.1.251:1521:BTM1";

  String username = ""; //"quantum";
  String password = ""; //""quantum";

  String ftpServer = ""; //"quantum";
  String ftpUser = ""; //""quantum";
  String ftpPassword = ""; //"quantum";

  String appHome = "";

  public String hierTypeSetup = "";
  Utils ut = new Utils();

  /** Get parameter from Config.txt file, store in public variable
   * @author 		Tuan.Truong
   */
  public void getConnfigParameter() {
    DefineEncrypt enc=new DefineEncrypt();

    String configFile = ut.CONFIG_FILE;
    try {
      RandomAccessFile fInit = new RandomAccessFile(configFile, "r");
      String strLine = "";
      String firstToken = ""; //token in right side
      while (fInit.getFilePointer() < fInit.length()) {
        //get next record
        strLine = fInit.readLine();
        if (strLine.indexOf(DELIM_CONFIG) > 0) {
          StringTokenizer st = new StringTokenizer(strLine, DELIM_CONFIG);
          firstToken = st.nextToken().trim();
          //get value of Connection
          if (firstToken.equalsIgnoreCase(ut.DRIVER_CLASS)) {
            driver_class = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.THIN_CONN)) {
            thinConn = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.HIER_TYPE_SETUP)) {
            hierTypeSetup = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.STORE_ID)) {
            storeID = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.STORE_NAME)) {
            storeName = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.STORE_ADDRESS)) {
            storeAddress = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.HOST_ID)) {
            hostID = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.REGISTER_ID)) {
            registerID = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.USER_NAME)) {
            username = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.PASSWORD)) {
            password = st.nextToken().trim();
            password = enc.decryptData(password);
          }
          else if (firstToken.equalsIgnoreCase(ut.FTP_SERVER)) {
            ftpServer = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.FTP_USER)) {
            ftpUser = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.FTP_PASSWORD)) {
            ftpPassword = st.nextToken().trim();
            //get value of Chunk data
          }
          else if (firstToken.equalsIgnoreCase(ut.APP_HOME)) {
            appHome = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.TURN_OFF_ACROBAT)) {
            turnOffAcrobat = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.ACROBAT_FILE)) {
            acrobatFile = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.DELAY_BEFORE_PRINT)) {
            delayBeforePrint = Integer.parseInt(st.nextToken().trim());
          }
          else if (firstToken.equalsIgnoreCase(ut.DELAY_AFTER_PRINT)) {
            delayAfterPrint = Integer.parseInt(st.nextToken().trim());
          }
          else if (firstToken.equalsIgnoreCase(ut.FETCH_SIZE)) {
            fetchSize = Integer.parseInt(st.nextToken().trim());
            //path of file
          }
          else if (firstToken.equalsIgnoreCase(ut.PATH_FILE)) {
            pathFile = st.nextToken().trim();
            //path of printer
          }
          else if (firstToken.equalsIgnoreCase(ut.RESOURCE_FILE)) {
            resourceFile = st.nextToken().trim();
            //get file path
          }
          else if (firstToken.equalsIgnoreCase(ut.FIRST_AUTO_NUM)) {
            firstAutoNum = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.SEC_AUTO_NUM)) {
            secAutoNum = st.nextToken().trim();
          }

          else if (firstToken.equalsIgnoreCase(ut.OUTPUT_CALC_ENG_FILE)) {
            outputCalcEngFile = st.nextToken().trim();
            prefixOutputCalcEngFile = outputCalcEngFile.substring(0,
                outputCalcEngFile.indexOf("."));
            suffixOutInCalcEngFile = outputCalcEngFile.substring(
                outputCalcEngFile.indexOf("."));
//                                }else if (firstToken.equalsIgnoreCase(ut.OUTPUT_ADDNEWITEM_FILE)) {
//                                        outputAddnewItemFile = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.ERROR_FILE)) {
            errorFile = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.TRANSACTION_DATE)) {
            transactionDate = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.FRANCHISE_CUST)) {
            franchiseCust = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.HIDDEN_NEURON)) {
            hiddenNeuron = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.OUTPUT_NEURON)) {
            outputNeuron = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.INPUT_COLUMN)) {
            inputColumn = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.NUM_OF_STORE)) {
            numOfStore = Integer.parseInt(st.nextToken().trim());
          }
          else if (firstToken.equalsIgnoreCase(ut.CURRENCY_ID)) {
            currencyID = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.SEARCH_LIMIT)) {
            searchLimit = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.COMPANY_NAME)) {
            companyName = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.COMPANY_ADR)) {
            companyAdr = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.COMPANY_TEL)) {
            companyTel = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.COMPANY_FAX)) {
            companyFax = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.COMPANY_VAT)) {
            companyVAT = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.ADDRESS_BILL)) {
            addrBill = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.ADDRESS_DELIVERY)) {
            addrDelivery = st.nextToken().trim();
          }
          else if (firstToken.equalsIgnoreCase(ut.VIEWER_ACCOUNT)) {
            viewerAccount = st.nextToken().trim();
          }

          else if (firstToken.equalsIgnoreCase(ut.OBSERVER_ACCOUNT)) {
            observerAccount = st.nextToken().trim();
          }
        }
      }
      fInit.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
//                e.printStackTrace();
    }
  } //end method

  /** Connect to Oracle Database
   * @author 		Tuan.Truong
   * @param 		loginID
   * @param       password
   * @return		Connection
   */
  public Connection getOracleConnect() {
    getConnfigParameter();
    try {
      Class.forName(driver_class).newInstance();
      conn = DriverManager.getConnection(thinConn, username, password);
      ( (OracleConnection) conn).setDefaultRowPrefetch(fetchSize);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return conn;
  }

  /** Re-Connect to Oracle Database when connection is failed
   * @author 		Tuan.Truong
   * @param 		loginID
   * @param       password
   * @return		Connection
   */
  public Connection reConnect() {
    System.out.println("------ RE CONNECT --------");
    try {
      Class.forName(driver_class).newInstance();
      conn = DriverManager.getConnection(thinConn, username, password);
      ( (OracleConnection) conn).setDefaultRowPrefetch(fetchSize);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return conn;
  }

  //Test connect to DB
  public boolean testOracleConnect() {

    getConnfigParameter();
    try {
      Class.forName(driver_class).newInstance();
      conn = DriverManager.getConnection(thinConn, username, password);
      ( (OracleConnection) conn).setDefaultRowPrefetch(fetchSize);
    }
    catch (Exception e) {
//                  ExceptionHandle eh = new ExceptionHandle();
//                  eh.ouputError(e);
      return false;
    }
    return true;
  }

  //check whether Connection is failed
  public boolean isConnect() {
    boolean isConnect = true;
    Statement stmt = null;

    try {
      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                  ResultSet.CONCUR_READ_ONLY);
      stmt.close();
    }
    catch (Exception e) {
//            ExceptionHandle eh = new ExceptionHandle();
//            eh.ouputError(e);
      isConnect = false;
    }
    return isConnect;
  }

  /**Set AutoCommit = false
   * @author 		Tuan.Truong
   * @param 		Connection conn
   * @return		No
   */
  public void setBeginTrans(Connection conn) {
    try {
      //check connection ,  if faild :re connect
      if (!isConnect()) {
        reConnect();
      }
      conn.setAutoCommit(false);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /** Commit to DB and set AutoCommit = true
   * @author 		Tuan.Truong
   * @param 		conn Connection
   * @return		No
   */
  public void setEndTrans(Connection conn) {
    try {
      conn.commit();
      conn.setAutoCommit(true);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /** Rollback DB
   * @author 		Tuan.Truong
   * @param 		conn Connection
   * @return		No
   */
  public void setRollback(Connection conn) {
    try {
      conn.rollback();
      conn.setAutoCommit(true);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /** get Connection
   * @author 		Tuan.Truong
   * @param 		No
   * @return		Connection
   */
  public Connection getConnection() {
    //check connection ,  if faild :re connect
    if (!isConnect()) {
      reConnect();
    }

    return conn;
  }

  /** Close connection
   * @author 		Tuan.Truong
   * @param 		conn Conection
   * @return		No
   */
  public void closeOracleConnection(Connection conn) {
    try {
      conn.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /** Execute SQL statement for Update, Delete and Insert data
   * @author		Trung.Nguyen
   * @param 		sqlStr query statement
   * @return		No
   */
  public void executeUpdate(String sqlStr) {
    Statement stmt = null;
    ResultSet rs = null;
    try {
      //check connection ,  if faild :re connect
      if (!isConnect()) {
        reConnect();
      }

      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                  ResultSet.CONCUR_UPDATABLE);
      stmt.executeUpdate(sqlStr);
      //stmt.execute(sqlStr);
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /** Execute SQL statement for Select data,handle stmt avoid "java.sql.SQLException: ORA-01000: maximum open cursors exceeded"
   * @author		Loan.vo
   * @param 		String sqlStr
   * @return		no
   */
  public void executeUpdate(String sqlStr, Statement stmt) {
    ResultSet rs = null;
    try {
      stmt.executeUpdate(sqlStr);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  /** Execute SQL statement for Update, Delete and Insert data
   * @author		Nghi Doan
   * @param 		sqlStr query statement
   * @return		No
   */
  public void executeUpdate(String sqlStr, int i) throws Exception {
    Statement stmt = null;
    ResultSet rs = null;
    try {
      //check connection ,  if faild :re connect
      if (!isConnect()) {
        reConnect();
      }

      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                  ResultSet.CONCUR_UPDATABLE);
      stmt.executeUpdate(sqlStr);
      stmt.close();
    }
    catch (Exception e) {
      throw new Exception("Exist constraint");
    }
  }

  /** Execute SQL statement for Select data
   * @author		Tuan.Truong
   * @param 		sqlStr query statement
   * @return		ResultSet
   */
  public ResultSet executeQueries(String sqlStr) {
    Statement stmt = null;
    ResultSet rs = null;

    try {
      //check connection ,  if faild :re connect
      if (!isConnect()) {
        reConnect();
      }

      stmt = conn.createStatement();
      rs = stmt.executeQuery(sqlStr);

    }
    catch (Exception e) {
      e.printStackTrace();
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

//===============================================================================
  /** Execute SQL statement for Select data
   * @author		Nghi.Doan
   * @param 		sqlStr query statement
   * @return		ResultSet
   */
  public ResultSet executeQueries(String sqlStr, int temp) {
    Statement stmt = null;
    ResultSet rs = null;

    try {
      //check connection ,  if faild :re connect
      if (!isConnect()) {
        reConnect();
      }

      stmt = conn.createStatement();
      rs = stmt.executeQuery(sqlStr);
    }
    catch (Exception e) {
//                        e.printStackTrace();
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  public ResultSet executeScrollQueries(String sqlStr) {
    Statement stmt = null;
    ResultSet rs = null;

    try {
      //check connection ,  if faild :re connect
      if (!isConnect()) {
        reConnect();
      }

      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                  ResultSet.CONCUR_READ_ONLY);
      rs = stmt.executeQuery(sqlStr);
    }
    catch (Exception e) {
      e.printStackTrace();
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;

  }

  /** Execute SQL statement for Select data,handle stmt avoid "java.sql.SQLException: ORA-01000: maximum open cursors exceeded"
   * @author		Loan.vo
   * @param 		String sqlStr
   * @return		ResultSet
   */
  public ResultSet executeScrollQueries(String sqlStr, Statement stmt) {
    ResultSet rs = null;

    try {
      rs = stmt.executeQuery(sqlStr);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;

  }

  /** Execute SQL statement for Select data,handle stmt avoid "java.sql.SQLException: ORA-01000: maximum open cursors exceeded"
   * @author		Tuan.Truong
   * @param 		String sqlStr
   * @return		ResultSet
   */
  public ResultSet executeQueries(String sqlStr, Statement stmt) {
    ResultSet rs = null;
    try {
      rs = stmt.executeQuery(sqlStr);
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }

  /** Execute SQL statement for Update, Delete and Insert data
   * @author		Trung.Nguyen
   * @param 		sqlStr query statement
   * @return		No
   */

  public int countRecord(String sqlStr, String fieldName, DataAccessLayer DAL) {
    Statement stmt = null;
    ResultSet rs = null;
    int numRecord = 0;
    try {
      //check connection ,  if faild :re connect
      if (!isConnect()) {
        reConnect();
      }

      stmt = conn.createStatement();
      rs = stmt.executeQuery(sqlStr);
      rs.next();
      numRecord = Integer.parseInt(rs.getString(fieldName));
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
      numRecord = 0;
    }
    return numRecord;
  }

  /** Execute SQL statement by batch, more performance
   * @author		Trung.Nguyen
   * @param 		String[] array query
   * @return		ResultSet
   */
  public int[] executeBatchQuery(Vector sqlStrs) {
    Statement stmt = null;
    int[] result = null;

    try {
      //check connection ,  if faild :re connect
      if (!isConnect()) {
        reConnect();
      }

      stmt = conn.createStatement();
      for (int i = 0; i < sqlStrs.size(); i++) {
        stmt.addBatch(sqlStrs.elementAt(i).toString());
        //				result=stmt.executeBatch();
        //				stmt.clearBatch();
      }
      result = stmt.executeBatch();
      stmt.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return result;
  }

  /** Execute SQL statement for Select data,handle stmt avoid "java.sql.SQLException: ORA-01000: maximum open cursors exceeded"
   * @author		Loan.vo
   * @param 		String sqlStr
   * @return		Array
   */
  public int[] executeBatchQuery(Vector sqlStrs, Statement stmt) {
    int[] result = null;
    try {
      for (int i = 0; i < sqlStrs.size(); i++) {
        stmt.addBatch(sqlStrs.elementAt(i).toString());
      }
      result = stmt.executeBatch();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return result;

  }

  /** Execute Store Procedure
   * @author		Trung.Nguyen
   * @param 		conn Connection
   * @param		storeProc store procedure name
   * @return		No
   */
  public synchronized void executeStoreProce(Connection conn, String storeProc) {
    try {

      String exeproc = "{call " + storeProc + "}";
      CallableStatement cs = conn.prepareCall(exeproc);
      cs.executeUpdate();
      cs.close();
    }
    catch (Exception e) {
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
  }

  //get Hier Type Setup
  public boolean getHierTypeSetup() {
    hierTypeSetup = hierTypeSetup.trim();
    if (hierTypeSetup.equals("0"))
      return false;
    else
      return true;
  }

  /** Get number of stores
   ** @return		a numOfStore
   */
  public int getNumOfStore() {
    return numOfStore;
  }

  /** get currency ID
   * @return a currencyID
   */
  public String getCurrencyID() {
    return currencyID;
  }

  /** getEmpID : emp_code
   * @return simEmpID;
   */
  public static String getEmpID() {
    return simEmpID;
  }

  /** getEmpPosID1 : emp_ID
   * @return posEmpID1
   */
  public static String getEmpPosID1() {
    return posEmpID1;
  }

  public static void setEmpPosID1(String empID) {
    posEmpID1 = empID;
  }

  /** getEmpCode
   * @return simEmpCode;
   */
  public static String getEmpCode() {
    return simEmpCode;
  }

  public static String getEmpPOSID() {
    return posEmpID;
  }

  /** setEmpID(String empID)
   * @param empID
   */
  public static void setEmpID(String empID) {
    simEmpID = empID;
  }

  /** setEmpCode(String empCode)
   * @param empCode
   */
  public static void setEmpCode(String empCode) {
    simEmpCode = empCode;
  }

  public static void setEmpPOSID(String empID) {
    posEmpID = empID;
  }

  /** Get chunk data size
   ** @return		a fetchSize
   */
  public int getFetchSize() {
    return fetchSize;
  }

  /** Get path of file
   ** @return		a pathFile
   */
  public String getPathFile() {
    return pathFile;
  }

  /** Get path of Acrobat printer
   ** @return		a pathFile
   */
  public static String getResourceFile() {
    return resourceFile;
  }

  /** Get input file of Calc Eng
   ** @return		an inputCalcEngFile
   */
  public String getFirstAutoNum() {
    return firstAutoNum;
  }
  public String getSectAutoNum() {
    return secAutoNum;
  }

  /** Get prefix of input file of Calc Eng
   ** @return		a prefixIputCalcEngFile
   */
  public String getPrefixIputCalcEngFile() {
    return prefixIputCalcEngFile;
  }

  /** Get suffix of input, output file of Calc Eng
   ** @return		a suffixOutInCalcEngFile
   */
  public String getSuffixOutInCalcEngFile() {
    return suffixOutInCalcEngFile;
  }

  /** Get output file of Calc Eng
   ** @return		a outputCalcEngFile
   */
  public String getOutputAddNewItemfile() {
    return outputAddnewItemFile;
  }

  /** Get output file of Calc Eng
   ** @return		a outputCalcEngFile
   */
  public String getOutputCalcEngine() {
    return outputCalcEngFile;
  }

  /** Get prefig of output file of Calc Eng
   ** @return		a prefixOutputCalcEngFile
   */
  public String getPrefixOutputCalcEngFile() {
    return prefixOutputCalcEngFile;
  }

  /** Get error log file
   ** @return		an errorFile
   */
  public String getErrorFile() {
    return errorFile;
  }

  /** Get file contains processing date
   ** @return		a transactionDate
   */
  public String getTransactionDate() {
    return transactionDate;
  }

  public String getFranchiseCust() {
    return franchiseCust;
  }

  public int getHiddenNeuron() {
    return Integer.parseInt(hiddenNeuron);
  }

  public int getOutputNeuron() {
    return Integer.parseInt(outputNeuron);
  }

  public String getInputColumn() {
    return inputColumn;
  }

  public String getStoreID() {
    return storeID;
  }

  public String getStoreName() {
    return storeName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getCompanyAdr() {
    return companyAdr;
  }

  public String getCompanyTel() {
    return companyTel;
  }

  public String getCompanyFax() {
    return companyFax;
  }

  public String getCompanyVAT() {
    return companyVAT;
  }
  public String getAddrDelivery() {
      return addrDelivery;
    }

    public String getAddrBill() {
        return addrBill;
      }

    public String getObserverAccount() {
        return observerAccount;
      }
      public String getViewerAccount() {
          return viewerAccount;
        }

  public String getStoreAddress() {
    return storeAddress;
  }

  public String getregisterID() {
    return registerID;
  }

  public String getHostID() {
    return hostID;
  }

  public String getemployeeID() {
    return employeeID;
  }

  public static void setEmpSIMID(String empID) {
    simEmpID = empID;
  }

  public String getSearchLimit() {
    return searchLimit;
  }

  public String getFtpServer() {
    return ftpServer;
  }

  public String getTurnOffAcrobat() {
    return turnOffAcrobat;
  }

  public String getAcrobatFile() {
    return acrobatFile;
  }

  public int getDelayBeforePrint() {
    return delayBeforePrint;
  }

  public int getDelayAfterPrint() {
    return delayAfterPrint;
  }

  public String getFtpUser() {
    return ftpUser;
  }

  public String getFtpPassword() {
    return ftpPassword;
  }

  public String getAppHome() {
    return appHome;
  }

  /**getFrameLabel(String language,String country)
   * @author Trung Nguyen
   * @param String
   */
  //Load language resource
  public static ResourceBundle getFrameLabel(String language,String country){
      Locale currentLocale;
      ResourceBundle rsLabel;

      currentLocale = new Locale(language, country);
      rsLabel =ResourceBundle.getBundle(DataAccessLayer.getResourceFile(),currentLocale);
      return rsLabel;
  }

} //end class
