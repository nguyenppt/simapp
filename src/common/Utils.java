package common;


/**
 * @author Team Work
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * Ver 1.1 - 11-08-2004
 * Ver 1.2 - 11-08-2004 added getRound
 */
import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.Date;//import pos.*;
import java.text.*;
import btm.swing.*;


//import pos.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;//import pos.*;

import oracle.jdbc.driver.*;
public class Utils {
        //********************************************************************************//
        //******************************* CONSTANT ***************************************//
        //********************************************************************************//
        //config file
        public final String CONFIG_FILE = "Config.txt";
        public final String DRIVER_CLASS = "driver_class";
        public final String THIN_CONN = "thinConn";
        public final String HIER_TYPE_SETUP = "hierTypeSetup";
        public final String STORE_ID = "storeid";
        public final String STORE_NAME = "storeName";
        public final String STORE_ADDRESS = "storeAddress";
        public final String REGISTER_ID = "registerID";
        public final String HOST_ID = "hostID";
        public final String EMPLOYEE_ID = "employeeID";
        public final String CURRENCY_ID = "currencyID";
        public final String USER_NAME = "username";
        public final String PASSWORD = "password";
        public final String FETCH_SIZE = "fetchSize";
        public final String PATH_FILE = "pathFile";
        public final String RESOURCE_FILE = "resourceFile";
        public final String FTP_SERVER = "ftp_server";
        public final String FTP_USER = "ftp_user";
        public final String FTP_PASSWORD = "ftp_password";
        public final String APP_HOME = "appHome";
        public final String TURN_OFF_ACROBAT = "turnOffAcrobat";
        public final String ACROBAT_FILE = "acrobatFile";
        public final String DELAY_BEFORE_PRINT="delayBeforePrint";
        public final String DELAY_AFTER_PRINT="delayAfterPrint";
        //public final String INPUT_CALC_ENG_FILE = "inputCalcEngFile";
        public final String ITEM_LIST_FILE = "ItemList.txt";
        public final String FIRST_AUTO_NUM = "firstAutoNum";
        public final String SEC_AUTO_NUM = "secAutoNum";
        public final String OUTPUT_CALC_ENG_FILE = "outputCalcEngfile";
        public final String ERROR_FILE = "errorFile";
        public final String TRANSACTION_DATE = "transactionDate";
        public final String FRANCHISE_CUST = "franchiseCustomer";
        public final String HIDDEN_NEURON = "hiddenNeuron";
        public final String OUTPUT_NEURON = "outputNeuron";
        public final String INPUT_COLUMN = "inputColumn";
        public final String NUM_OF_STORE = "numOfStore";

        public final String COMPANY_NAME = "companyName";
        public final String COMPANY_ADR = "companyAdr";
        public final String COMPANY_TEL = "companyTel";
        public final String COMPANY_FAX = "companyFax";
        public final String COMPANY_VAT = "companyVAT";
        public final String ADDRESS_DELIVERY = "addrDelivery";
        public final String ADDRESS_BILL = "addrBill";

        public final String OBSERVER_ACCOUNT = "observerAccount";
        public final String VIEWER_ACCOUNT = "viewerAccount";
        public final String SEARCH_LIMIT = "searchLimit";

        //public final String IN_TEMP_CALC_ENG_PREFIX = "InputTempCalcEngFile";
        public final String IN_TEMP_CALC_ENG_PREFIX = "InputTempCalcEngFile_Shadow";
        //Type of App: PreActual, PreShadow,Post
        public final String TYPE_APP_PRE_ACTUAL = "PreActual";
        public final String TYPE_APP_PRE_SHADOW = "shadow";
        public final String TYPE_APP_SEASONAL_INDEX = "Index";
        public final String TYPE_APP_POST = "Post";
        //Status and type of thread in QR_BACKUP_RECOVERY
        public final String STATUS_RUN = "Running";
        public final String STATUS_FIN_CHUNK = "Finish_Chunk";
        public final String STATUS_FIN_PROCESS = "Finish_Process";
        //name of process (app_name)
        public final String SIM_IMP_INV_ITEM_LOC_DCOM = "SimImpInvItemLocDCom";
//        public final String PRE_UPT_PRODUCT = "PreActual_Upt_Product";
//        public final String SHA_CALC = "shadow_calc";
//        public final String SHA_UPT_SHADOW = "shadow_Upt_Shadow";
//        public final String INDEX_CALC = "Index_Calc";
//        public final String INDEX_UPT = "Index_Upt";
//        public final String POST_GET_DATA = "Post_GetData";
//        public final String POST_EXT_REQUEST = "Post_Exp_Request";
//        public final String POST_UPT_PRODUCT_ACTUAL = "Post_Upt_Product_Actual";
//        public final String POST_UPT_PRODUCT_SHADOW = "Post_Upt_Product_Shadow";
//        public final int BUFF_SIZE = 120000;//use to read file
        //milisecond in a date: 24*3600*1000
        public final int MILLISEC_PER_DATE = 86400000;
        public final String DATE_FORMAT = "yyyy-MM-dd";//"yyyy-MM-dd";
        public final String DATE_FORMAT1 = "dd/MM/yyyy";
        public final String DATE_FORMAT2 = "dd-MM-yyyy";
        public final String DATE_FORMAT3 = "dd/MM/yy";
        public final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
        public final String DATETIME_FORMAT0 = "dd/MM/yyyy HH:mm:ss";
        public final String DATETIME_FORMAT1 = "dd-MM-yyyy hh:mm:ss";
        public final String DATETIME_FORMAT2 = "dd-MM-yyyy hh24:mi:ss";
        public final String DATETIME_FORMAT3 = "dd-MM-yyyy HH:mm:ss";
        public final String DATETIME_FORMAT4 = "dd-MM-yyyy kk:mm:ss";
        public final String DATETIME_FORMAT_24 = "yyyy-MM-dd HH:mm:ss";//0..23
        public final String DATETIME_FORMAT_SQL = "yyyy-MM-dd HH24:mi:ss";//use in query, 0..23 hour

        //date for create transfer ID, goddreceipt id, DamageGood id
        public final String DATETIME_FORMAT5 = "dd-MM-yy HH:mm:ss";
        public final String DATETIME_FORMAT6 = "dd-MM-yyyy HH:mi:ss";
        //date use for create ID "ddMMyyhhmmss"
        public final String DATETIME_FORMAT7 = "ddMMyyhhmmss";
        public int mainPosFlag = 0;
        public int flagEvenExchange = 1;
        public String item_id = "";
        public String trans_id = "";

        //Status of BTM_POS_BATCH_MAINTENANCE table
        public final String STATUS_NOT_COMPLETE = "Not Complete";
        public final String STATUS_COMPLETE = "Complete";

        //for SIM
        public final int DEFAULT_ROW_LIMITED = 5;
        public final int PREFIX_CODE_LENGTH=3;
        public static boolean  isPos=true;
        public static boolean  online=true;


        /** current language for Multilanguage
         */
//        public static String sLanguague="en";
        public static String sLanguague="vi";
        /** current country for Multilanguage
         */
//        public static String  sCountry="En";
        public static String  sCountry="VN";


        public boolean getIsPos(){
          return isPos;
        }
       //===============================SET
        public void setIsPos(boolean isPos){
        this.isPos=isPos;
        }


        //********************************************************************************//
        //******************************  NUMBER FUNCTION ********************************//
        //********************************************************************************//


        /** Round a double
     * @author 	Tuan.Truong
     * @param 	Double value, Decimal places
     * @return	Double
   */

        public double getRound(double r, int decimalPlace){
            BigDecimal bd = new BigDecimal(r);
            bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
            r = bd.doubleValue();
            return r;
        }
        public float getRound(float r, int decimalPlace){
            BigDecimal bd = new BigDecimal(r);
            bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
            r = bd.floatValue();
            return r;
        }

   /** doubleStringToInt method - double  string to int
     * @author 	Trung.Nguyen
     * @param 	s double string
     * @return	integer
   */
        public int doubleStringToInt(String s){
                int result=0;
                if(s.indexOf(".")>=0){
                        result=Integer.parseInt(s.substring(0,s.indexOf("."))) ;
                }else{
                        result=Integer.parseInt(s) ;
                }
            return result;
        }
        /** formatNumber(String stringFormat)
         * @author Nghi Doan
         * @return a formated String
         * @param stringFormat
         */
        public String formatNumberNoZero(String stringFormat){
                 NumberFormat formatter = new DecimalFormat("###0");
                 //NumberFormat formatter = new DecimalFormat("#,##0");
                 String result = "";
                 if (stringFormat.equalsIgnoreCase("")){
                   result = formatter.format(Double.parseDouble("0"));
                 }else {
                   if (isDoubleString(stringFormat)) {
                     result = formatter.format(Double.parseDouble(stringFormat));
                   }
                   else {
                     result = formatter.format(Double.parseDouble("0"));
                   }
                 }
                 return result;
               }

        public String formatNumber(String stringFormat){
          NumberFormat formatter = new DecimalFormat("#,##0.00");
          //NumberFormat formatter = new DecimalFormat("#,##0");
          String result = "";
          if (stringFormat.equalsIgnoreCase("")){
            result = formatter.format(Double.parseDouble("0"));
          }else {
            if (isDoubleString(stringFormat)) {
              result = formatter.format(Double.parseDouble(stringFormat));
            }
            else {
              result = formatter.format(Double.parseDouble("0"));
            }
          }
          return result;
        }

        /** formatNumber(String stringFormat)
         * @author Nghi Doan
         * @return a formated String
         * @param stringFormat
         */

        public String formatNumber1(String stringFormat){
          NumberFormat formatter = new DecimalFormat("#,##0.000000");
          String result = "";
          if (stringFormat.equalsIgnoreCase("")){
            result = formatter.format(Double.parseDouble("0"));
          }else {
            if (isDoubleString(stringFormat)) {
              result = formatter.format(Double.parseDouble(stringFormat));
            }
            else {
              result = formatter.format(Double.parseDouble("0"));
            }
          }
          return result;
        }

         /**checkNumber(KeyEvent e)
         * @author Nghi Doan
         * @param KeyEvent
         */
        //check code
        public void checkCode(KeyEvent e){
          char key = e.getKeyChar();
          if (isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE ||
              key == KeyEvent.VK_DELETE || (key <= 'z' && key >= 'a') || (key <='Z' && key >='A')){
            return;
          }else{
            e.consume();
          }
        }
        //check number
        public void checkNumber(KeyEvent e){
          char key = e.getKeyChar();
          if (isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE){
            return;
          }else{
            e.consume();
          }
        }

        //check number

        public void checkNumberPositive(KeyEvent e,String sNode) {
          char key = e.getKeyChar();
          if (isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE ||
              key == KeyEvent.VK_DELETE || (key == '.' && countDot(sNode)==0 && !sNode.equals(""))) {
            return;
          }
          else {
            e.consume();
          }
        }

        //check number

        public void checkNumberUpdateNewSale(KeyEvent e,String sNode) {
          char key = e.getKeyChar();
          if (isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE ||
              key == KeyEvent.VK_DELETE || (key == '.' && countDot(sNode)==0 && !sNode.equals(""))) {
            return;
          }
          else {
            e.consume();
          }
        }
        //check number

        public void checkNumberUpdate(KeyEvent e,String sNode) {
          char key = e.getKeyChar();
          if (isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE ||
              key == KeyEvent.VK_DELETE || (key == '-' && sNode.equals("")) || (key == '.' && countDot(sNode)==0 && !sNode.equals(""))) {
            return;
          }
          else {
            e.consume();
          }
        }

        public void checkCharUpdate(KeyEvent e) {
          char key = e.getKeyChar();
          if (key != '\'' && key != '/' && key != '"' && key != '\\'){
            return;
          }
          else {
            e.consume();
          }
        }

        public int countDot(String sNode){
          char[] aNode = sNode.toCharArray();
          int i,iNum=0;
          for(i=0;i<aNode.length;i++){
            if (aNode[i]=='.'){
              iNum++;
            }
          }
          return iNum;
        }
        /**checkPhone(KeyEvent e)
         * @author Nghi Doan
         * @param KeyEvent
         */
        //check phone
        public void checkPhone(KeyEvent e){
          char key = e.getKeyChar();
          if (isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE ||
              key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UNDERSCORE){
            return;
          }else{
            e.consume();
          }
        }

        //ta
        public String formatNumbervn(String stringFormat){
         NumberFormat formatter = new DecimalFormat("#,##0");
         String result = "";
         if (stringFormat.equalsIgnoreCase("")){
           result = formatter.format(Double.parseDouble("0"));
         }else {
           if (isDoubleString(stringFormat)) {
             result = formatter.format(Double.parseDouble(stringFormat));
           }
           else {
             result = formatter.format(Double.parseDouble("0"));
           }
         }
         return result;
       }

        /** changeDataTypetoLong(int columnPosition, BJTable table)
         *
         * @param columnPosition, BJTable table
         *
         */
        //change Datatye
        public void changeDataTypetoLong(int columnPosition, SortableTableModel dm){
          if (dm.getRowCount()>0){
//            System.out.println(dm.getRowCount());
            for (int i = 0; i<dm.getRowCount(); i++){
//              System.out.println("Nghi : " + dm.getValueAt(i,columnPosition));
              long custID = (long)(convertToDouble("" + dm.getValueAt(i,columnPosition)));
              dm.setValueAt(new Long(custID),i,columnPosition);
            }
          }
        }

        public String formatNumberTo2DigitsDecimal(double d){
          NumberFormat formatter = new DecimalFormat("0.00");
          String s = formatter.format(d);
          return s;
        }

        /** convertToDouble(String sDouble){
         * return a double
         * @param sDouble
         */
        public double convertToDouble(String sDouble){
          String result = "";
          int startPoint = 0;
          int endPoint = 0;
          boolean flag = false;
          if (sDouble.length()>6){
            for (int i = 0; i < sDouble.length(); i++) {
              if (i == 0) {
                startPoint = i;
              }
              else if (sDouble.substring(i - 1, i).equalsIgnoreCase(",")) {
                startPoint = i;
              }
              if (i < sDouble.length() - 1) {
                if (sDouble.substring(i, i + 1).equalsIgnoreCase(",")) {
                  endPoint = i;
                  flag = true;
                }
              }
              else {
                endPoint = 777;
                flag = true;
              }
              if (flag) {
                if (endPoint < 777) {
                  result = result + sDouble.substring(startPoint, endPoint);
                }
                else {
                  result = result + sDouble.substring(startPoint);
                }
                flag = false;
              }
              if (sDouble.substring(i, i + 1).equals(".")) {
                result = result + sDouble.substring(sDouble.lastIndexOf(",") + 1);
                return Double.parseDouble(result);
              }
            }
          }else{
            result = sDouble;
          }

          return Double.parseDouble(result);
        }
        /** convertToDouble(String sDouble){
         * return a double
         * @param sDouble
         */
        public double convertToDouble1(String sDouble){
          String result = "";
          int startPoint = 0;
          int endPoint = 0;
          boolean flag = false;
          if (sDouble.length()>4){
            for (int i = 0; i < sDouble.length(); i++) {
              if (i == 0) {
                startPoint = i;
              }
              else if (sDouble.substring(i - 1, i).equalsIgnoreCase(",")) {
                startPoint = i;
              }
              if (i < sDouble.length() - 1) {
                if (sDouble.substring(i, i + 1).equalsIgnoreCase(",")) {
                  endPoint = i;
                  flag = true;
                }
              }
              else {
                endPoint = 777;
                flag = true;
              }
              if (flag) {
                if (endPoint < 777) {
                  result = result + sDouble.substring(startPoint, endPoint);
                }
                else {
                  result = result + sDouble.substring(startPoint);
                }
                flag = false;
              }
              if (sDouble.substring(i, i + 1).equals(".")) {
                result = result + sDouble.substring(sDouble.lastIndexOf(",") + 1);
                return Double.parseDouble(result);
              }
            }
          }else{
            result = sDouble;
          }

          return Double.parseDouble(result);
        }

        /** doubleToString method - double to string
          * @author 	Trung.Nguyen
          * @param 	s double string
          * @return	integer
        */
             public String doubleToString(double s){
                     String result=String.valueOf(s);
                     if(result.indexOf(".")>=0){
                             result=result.substring(0,result.indexOf(".")) ;
                     }
                 return result;
             }

             /** getCheckDigitEAN13 get last digit of EAN 13 string
              * @param s 12 digit string
              * @return last digit
              *
              */
             public int getCheckDigitEAN13(String s) {
               int check = 0; //check digit
               int sum = 0;
               int num = 0;
               for (int i = 0; i < s.length(); i++) {
                 num = Integer.parseInt(String.valueOf(s.charAt(i)));
                 if (i % 2 == 1) {
                   sum += num * 3;
                 }
                 else {
                   sum += num;
                 }
               }
               check = 10 - sum % 10;
               if (check == 10) {
                 check = 0;
               }
               return check;
             }

        //********************************************************************************//
        //******************************* STRING FUNCTION ********************************//
        //********************************************************************************//

      //check element isExist in string or not
       boolean isElementInString(String sSource ,String ele,String delimiter){
           boolean found=false;
           String s;
           StringTokenizer st = new StringTokenizer(sSource,delimiter);
           while (st.hasMoreTokens()){
              s=st.nextToken();
              if(s.equals(ele)){
                 found =true;
                 break;
              }
           }
           return found;
       }
       /**Check object is empty(=null,="") or not
       * @return True if obj null or equals "", otherwise, it returns false.
       */
      public boolean isEmptyObj(Object obj){
           boolean isEmpty=false;
           if(obj!=null ){
               if (obj.toString().equals("") ){
                   isEmpty=true;
               }
           }else{
               isEmpty=true;
           }
           return isEmpty;
       }
       /**Check string is Integer number or not
       * @param sSource to be checked
       * @return True if sSource is Integer string, otherwise, it returns false.
       */
      public boolean isIntString(String sSource){
         boolean isValid=true;
         if(sSource==null) isValid= false;
         if(sSource.equals("")) isValid= false;
         if(isValid==true){
           try {
               Integer.parseInt(sSource);
           }
           catch (Exception e) {
//             ExceptionHandle eh = new ExceptionHandle();
//             eh.ouputError(e);
             isValid=false;
           }
         }
         return isValid;
       }
       /**Check string is Long Integer number or not
       * @param sSource to be checked
       * @return True if sSource is Integer string, otherwise, it returns false.
       */
      public boolean isLongIntString(String sSource){
         boolean isValid=true;
         if(sSource==null) isValid= false;
         if(sSource.equals("")) isValid= false;
         if(isValid==true){
           try {
               Long.parseLong(sSource);
           }
           catch (Exception e) {
//             ExceptionHandle eh = new ExceptionHandle();
//             eh.ouputError(e);
             isValid=false;
           }
         }
         return isValid;
       }
       /**Check string is Long Integer number or not
       * @param sSource to be checked
       * @return True if sSource is Integer string, otherwise, it returns false.
       */
      public boolean isDoubleString(String sSource){
         boolean isValid=true;
         if(sSource==null) isValid= false;
         if(sSource.equals("")) isValid= false;
         if(isValid==true){
           try {
               Double.parseDouble(sSource);
           }
           catch (Exception e) {
//             ExceptionHandle eh = new ExceptionHandle();
//             eh.ouputError(e);
             isValid=false;
           }
         }
         return isValid;
       }

       /**Check string is Float number or not
       * @param sSource to be checked
       * @return True if sSource is Float string, otherwise, it returns false.
       */
      public boolean isFloatString(String sSource){
         boolean isValid=true;
         if(sSource==null) isValid= false;
         if(sSource.equals("")) isValid= false;
         try {
             Float.parseFloat(sSource) ;
         }
         catch (Exception e) {
//           ExceptionHandle eh = new ExceptionHandle();
//           eh.ouputError(e);
           isValid=false;
         }
         return isValid;
       }
       /**Trim zeros leftmos of number string
       * @param sSource to be trimed
       * @return result string
       */
       public String trimLeftmostZeros(String sSource){
         String tmp=sSource;
         if(sSource.length() >1){
             for(int i=0;i<sSource.length()-1;i++){
               if(sSource.charAt(i)=='0') {
                 tmp=tmp.substring(1);
               }else{
                 break;
               }
             }
         }
         return tmp;
       }
       /**Attache escape character before quot ( ' ) character, use for WHERE clause of query.
       * <p>
       * Example:
       * <blockquote><pre>
       * putEscapeToString("AA'A) return ("AA''A")
       * </pre></blockquote>
       * @param sSource to be attached
       * @return result string
       */
//      public static String putCommaToString(String sSource){
//         String sTemp="";
//
//         if(sSource!=null && sSource.indexOf("'") !=-1){
//           for (int i = 0; i < sSource.length(); i++) {
//               if (sSource.charAt(i)=='\'') {
//                 sTemp=sTemp+sSource.charAt(i)+'\'';
//               }else{
//                 sTemp=sTemp+sSource.charAt(i);
//               }
//           }
//         }else{
//           sTemp=sSource;
//         }
//         return sTemp;
//       }


       /*
       * putToString("Man's Shoe") return ("Man''s Shoe")
       * @param sSource to be attached
       * @return result string
       */
      public String putCommaToString(String stSource){
         String sTemp="";
         String stComma = null;
         if(stSource!=null && stSource.indexOf("'") !=-1){
           for (int i = 0; i < stSource.length(); i++) {
             stComma = stSource.substring(i, i + 1).toString();
             if (stComma.equals("'")) {
                sTemp = sTemp + stComma + "'";
             }else{
                 sTemp=sTemp+stComma;
             }
           }//for
         }else{
           sTemp=stSource;
         }
         return sTemp;
       }
//return true if sTest has speccial chars : ";"
       public boolean foundSpecialChar(String sTest){
         boolean isValid=false;

         if(sTest.indexOf(";")>=0){
           isValid=true;
         }

         return isValid;
       }

     //check whether String is positive integer
       boolean isNumberPositive(String sNum){
         boolean isValid=true;
         if(sNum==null) isValid= false;
         if(sNum.equals("")) isValid= false;
         try {
             Integer.parseInt(sNum);
             if(Integer.parseInt(sNum)<0){
                 isValid=false;
             }
         }
         catch (Exception e) {
//           ExceptionHandle eh = new ExceptionHandle();
//           eh.ouputError(e);
             isValid=false;
         }
         return isValid;
       }

       //check whether String is integer
       boolean isInt(String sNum){
         boolean isValid=true;
         if(sNum==null) isValid= false;
         if(sNum.equals("")) isValid= false;
         try {
             Integer.parseInt(sNum);
         }
         catch (Exception e) {
//           ExceptionHandle eh = new ExceptionHandle();
//           eh.ouputError(e);
             isValid=false;
         }
         return isValid;
       }

       /** checkPhoneNumber(String phoneNumber) - Check the phone number format
        * @author Nghi Doan
        * @param phoneNumber
        */
       //Check phone number
       public boolean checkPhoneNumber(String phoneNumber){
         String result = phoneNumber.trim();
         for (int i=0;i<result.length();i++){
           String temp = result.substring(i,i+1);
           if (!isIntString(temp)){
             if (!temp.equals(" ") && !temp.equals("-") && !temp.equals(".")){
               return false;
             }
           }
         }
         return true;
       }



        //********************************************************************************//
        //******************** FILE FUNCTION *********************************************//
        //********************************************************************************//
        /** Check whether file exist or not
     * @author 	Trung.Nguyen
     * @param 	filePath the path of file
     * @return	true if file is exist, else return false
   */
        public boolean isExistFile(String filePath) {
            boolean isExist=false;
                try{
                File f= new File(filePath);
                isExist=f.exists();
            }catch (Exception e) {
                        ExceptionHandle eh = new ExceptionHandle();
                        eh.ouputError(e);
                }
            return isExist;
        }

        /** Check whether any file exist or not in the path
         * @author 	Trung.Nguyen
         * @param 	baseName a part of file name
         * @param 	path the path of file
         * @return	true if at least one file is exist, else return false
         */
        public boolean  isExistFile(String baseName, String path){
          boolean found=false;
          String filename;
          File dir = new File(path);

          String[] children = dir.list();
          if (children == null) {
            // Either dir does not exist or is not a directory
          }else {
            for (int i = 0; i < children.length; i++) {
              // Get filename of file or directory
              filename = children[i];
              if(filename.indexOf(baseName)!=-1 ){
                found=true;
              }
//              System.out.println(filename);
            }
          }
          return found;

        }

        /** filter file in specified dir
     * @author 	Trung.Nguyen
     * @param 	dir the directory
     * @param 	prefix the prefix string
     * @param 	suffix the suffix string
     * @return	array files that match filter string
   */
        public String[] filterFile(String dir,String prefix,String suffix) {
            // Create a File instance for the current directory
            File currDir = new File( dir );

            // Get a filtered list of the .java files in the current directory
            String[] javaFiles = currDir.list( new JavaFilter(prefix.toLowerCase(),suffix.toLowerCase()) );

            // Print out the contents of the javaFiles array
                return javaFiles;
        }

        //********************************************************************************//
        //******************************* DATE FUNCTION **********************************//
        //********************************************************************************//
        /** Check the Date
        * @author 	Trung.Nguyen
        * @param 	sDate Date in Date type
        * @return	true if sDate is date (d/m/yyyy)
        */

       public boolean checkDate(String sDate,String delimiter){
          boolean isValidDate=true;

          String ddDate="",mmDate="",yyyyDate="";
          int t[] = {0,31,29,31,30,31,30,31,31,30,31,30,31};

          if(sDate==null){
             isValidDate=false;
          }else{
             if(sDate.equals("")) isValidDate=false;
             if(sDate.indexOf(delimiter)==-1) isValidDate=false;
          }

          if(sDate!=null){
              if(!sDate.equals("")){
                    if(isValidDate==true ){
                        // Test format date is one of: DD/MM/YYYY - D/MM/YYYY - DD/M/YYYY - D/M/YYYY
                        if(sDate.indexOf(delimiter)==2 & sDate.lastIndexOf(delimiter)==5 & sDate.length()==10){

                        }else if(sDate.indexOf(delimiter)==1 & sDate.lastIndexOf(delimiter)==4 & sDate.length()==9){

                        }else if(sDate.indexOf(delimiter)==2 & sDate.lastIndexOf(delimiter)==4 & sDate.length()==9){

                        }else if(sDate.indexOf(delimiter)==1 & sDate.lastIndexOf(delimiter)==3 & sDate.length()==8){

                        // Test format date is one of: DD/MM/YY - D/MM/YY - DD/M/YY - D/M/YY
                        }else if(sDate.indexOf(delimiter)==2 & sDate.lastIndexOf(delimiter)==5 & sDate.length()==8){

                        }else if(sDate.indexOf(delimiter)==1 & sDate.lastIndexOf(delimiter)==4 & sDate.length()==7){

                        }else if(sDate.indexOf(delimiter)==2 & sDate.lastIndexOf(delimiter)==4 & sDate.length()==7){

                        }else if(sDate.indexOf(delimiter)==1 & sDate.lastIndexOf(delimiter)==3 & sDate.length()==6){

                        }else{ // error
                            isValidDate=false;

                        }
                    }
                    if(isValidDate==true ){
                              ddDate=sDate.substring(0,sDate.indexOf(delimiter) ) ;
                              mmDate=sDate.substring(sDate.indexOf(delimiter)+1,sDate.lastIndexOf(delimiter)  ) ;
                              yyyyDate=sDate.substring(sDate.lastIndexOf(delimiter)+1) ;
                              //test dd,mm,yyyy is Number
                              if (!isIntString(ddDate)|| !isIntString(mmDate) || !isIntString(yyyyDate) ){
                                 isValidDate=false;
                              //test >0
                              }else if(Integer.parseInt(ddDate)==0||Integer.parseInt(mmDate)==0||Integer.parseInt(yyyyDate)==0){
                                isValidDate=false;
                              }
                              // continue test
                              if(isValidDate==true){
                                    // Test month
                                    if(Integer.parseInt(mmDate)>12){
                                       isValidDate=false;
                                    }
                                    if(isValidDate==true){
                                          // Test date in [1..31]
                                          if(t[Integer.parseInt(mmDate)] <Integer.parseInt(ddDate)){
                                              isValidDate=false;
                                          }
                                          // Test date of February
                                          if(Integer.parseInt(mmDate)==2){
                                             if(Integer.parseInt(yyyyDate)%4==0){
                                                if(Integer.parseInt(ddDate)>29){
                                                    isValidDate=false;
                                                }
                                             }else{
                                                if(Integer.parseInt(ddDate)>28){
                                                    isValidDate=false;
                                                }
                                             }
                                          }
                                    }
                              }
                    }
              }
          }
          return isValidDate;
       }
       /** check month year
        * @author Nghi Doan
        *
        */
       public boolean checkMonthYear(String mainInput, String delimiter){
         String month = "";
         String year = "";
         month = mainInput.substring(0,mainInput.indexOf(delimiter));
         year = mainInput.substring(mainInput.lastIndexOf(delimiter) + 1);
         if (month.length() > 2) return false;
         if (year.length() != 2) return false;
         if (!isIntString(month) || !isIntString(year)) return false;
         if (Integer.parseInt(month)>12 || Integer.parseInt(month)<1) return false;
         return true;
       }

       public String getServerDateTime( DataAccessLayer DAL){
         ResultSet rs;
         String serverDate="";

         String sqlStr ="select sysdate from dual";
         rs = DAL.executeQueries(sqlStr);
         try {
           if (rs.next()) {
             serverDate = rs.getString("sysdate");
           }
           rs.getStatement().close();
         }catch (Exception e){
           ExceptionHandle eh = new ExceptionHandle();
           eh.ouputError(e);
         }
         return serverDate;

       }

       //get  ID base on ddMMyyhhmmss
       public String getddMMyyhhmmssID(){
         String receiptID= getSystemDateTime5();
         receiptID = receiptID.replaceAll(":"," ").replaceAll("-"," ").replaceAll(" ","");
         return receiptID;
       }

   /** Get the system ddmmyyyy
     * @author 	Tuan.Truong
     * @return	String date
   */
        public String getSystemDateTime(){
            java.util.Date day = new java.util.Date();
            java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATETIME_FORMAT1);
            String date = fd.format(day);
            return date;
        }

        /** Get the system ddmmyyyy
           * @author 	Nguyen Pham
           * @return	String date
         */
              public String getSystemDateTime1(){
                  java.util.Date day = new java.util.Date();
                  java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATETIME_FORMAT3);
                  String date = fd.format(day);
                  return date;
              }
              /** Get the system dd/mm/yyyy hh24:mm:ss
              * @author 	Vo Ha Thanh Huy
              * @return	String date
              */

              public String getSystemDateTime2() {
                             java.util.Date day = new java.util.Date();
                             java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
                                 DATETIME_FORMAT4);
                             String date = fd.format(day);
                             return date;
             }
             /** Get the system dd/mm/yyyy hh24:mm:ss
             * @author 	Trung
             * @return	String date
             */

             public String getSystemDateTime5() {
                            java.util.Date day = new java.util.Date();
                            java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
                                DATETIME_FORMAT5);
                            String date = fd.format(day);
                            return date;
            }

              /** Get the system dd/mm/yyyy hh:mm:ss
               * @author 	Loan Vo
               * @return	String date
               */


              public String getSystemDateTime0() {
                java.util.Date day = new java.util.Date();
                java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
                    DATETIME_FORMAT0);
                String date = fd.format(day);
                return date;
              }
              /** Get the system yyyy-MM-dd HH:mm:ss : 0 to 23h
               * @author 	Loan Vo
               * @return	String date
               */

              public String getSystemDateTime24() {
                java.util.Date day = new java.util.Date();
                java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(
                    DATETIME_FORMAT_24);
                String date = fd.format(day);
                return date;
              }

              //check sysdate with server
              public boolean checkSystemDate(DataAccessLayer DAL){
                String sysDateTime="";
                String serverDateTime="";
                String sysDate="";
                String serverDate="";
                String sysTime="";
                String severTime="";
                boolean isOK=false;

                sysDateTime= getSystemDateTime24()  ;
                sysDate=sysDateTime.substring(0,10);
                sysTime=sysDateTime.substring(11,13);
                serverDateTime=getServerDateTime(DAL);
                serverDate=serverDateTime.substring(0,10);
                severTime=serverDateTime.substring(11,13);
                if(sysDate.equals(serverDate)){
                  if ((Integer.parseInt(sysTime)== Integer.parseInt(severTime)) || (Integer.parseInt(sysTime)== Integer.parseInt(severTime)+1) || (Integer.parseInt(sysTime)== Integer.parseInt(severTime)-1)){
                    isOK=true;
                  }
                }

                return isOK;
              }

     /** Get the system yyyy-MM-dd
     * @author 	Tuan.Truong
     * @return	String date
   */
        public String getSystemDate(){
            java.util.Date day = new java.util.Date();
            java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT);
            String date = fd.format(day);
            return date;
        }
        public String changeFormatDate(String sDate){
          java.util.Date day = convertStringToDate(sDate);
          java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT);
          String date = fd.format(day);
          return date;
        }
        public String getSystemDate2() {
          java.util.Date day = new java.util.Date();
          java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT2);
          String date = fd.format(day);
          return date;
        }


        /** Get the system yyyy-MM-dd
        * @author 	Tuan.Truong
        * @return	String date
      */
           public Date getSystemDate_ToDate(){
               java.util.Date day = new java.util.Date();
               java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT);
  //             String date = fd.format(day);
               return day;
           }
           /** Get the system ddmmyyhhmmss for create ID
             * @author 	Vinh.Le
             * @return	String datetime
           */
           public String getDateTimeID(){
               java.util.Date day = new java.util.Date();
               java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATETIME_FORMAT7);
               String date = fd.format(day);
               return date;
           }

           /** compare 2 date , "dd/MM/yyyy"
            * @author Nghi Doan
            * @return true if date11 > date22
            */
           public boolean compareDate(String date11, String date22){
             java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT1);
//             String date11 = fd.format(date1);
//             String date22 = fd.format(date2);
             int day1 = Integer.parseInt(date11.substring(0,date11.indexOf("/")));
             int day2 = Integer.parseInt(date22.substring(0,date22.indexOf("/")));
             int month1 = Integer.parseInt(date11.substring(date11.indexOf("/")+1,date11.lastIndexOf("/")));
             int month2 = Integer.parseInt(date22.substring(date22.indexOf("/")+1,date22.lastIndexOf("/")));
             String year11="";
             if (date11.substring(date11.lastIndexOf("/")+1,date11.length()).length() < 4){
               year11 = "20" + date11.substring(date11.lastIndexOf("/")+1,date11.length());
             }else{
               year11 = date11.substring(date11.lastIndexOf("/")+1,date11.length());
             }
             int year1 = Integer.parseInt(year11);
             int year2 = Integer.parseInt(date22.substring(date22.lastIndexOf("/")+1,date22.length()));
//             System.out.println(day1+"/"+month1+"/"+year1+ "----------" +day2+"/"+month2+"/"+year2);
             java.util.Date d1 = new java.util.Date(year1-1900,month1,day1);
//             System.out.println("Day: " + d1);
             if (year1 > year2){
               return true;
             }else if (year1 < year2){
               return false;
             }else{
               if (month1>month2){
                 return true;
               }else if (month1 < month2){
                 return false;
               }else{
                 if (day1>day2){
                   return true;
                 }else{
                   return false;
                 }
               }
             }
           }
           /** compare 2 date
            * @author Nghi Doan
            * @return true/false
            */
           public boolean compareDate1(String date11, String date22){
             java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT1);
//             String date11 = fd.format(date1);
//             String date22 = fd.format(date2);
             int day1 = Integer.parseInt(date11.substring(0,date11.indexOf("/")));
             int day2 = Integer.parseInt(date22.substring(0,date22.indexOf("/")));
             int month1 = Integer.parseInt(date11.substring(date11.indexOf("/")+1,date11.lastIndexOf("/")));
             int month2 = Integer.parseInt(date22.substring(date22.indexOf("/")+1,date22.lastIndexOf("/")));
             String year11="";
             if (date11.substring(date11.lastIndexOf("/")+1,date11.length()).length() < 4){
               year11 = "20" + date11.substring(date11.lastIndexOf("/")+1,date11.length());
             }else{
               year11 = date11.substring(date11.lastIndexOf("/")+1,date11.length());
             }
             int year1 = Integer.parseInt(year11);
             int year2 = Integer.parseInt(date22.substring(date22.lastIndexOf("/")+1,date22.length()));
//             System.out.println(day1+"/"+month1+"/"+year1+ "----------" +day2+"/"+month2+"/"+year2);
             java.util.Date d1 = new java.util.Date(year1-1900,month1,day1);
//             System.out.println("Day: " + d1);
             if (year1 > year2){
               return true;
             }else if (year1 < year2){
               return false;
             }else{
               if (month1>month2){
                 return true;
               }else if (month1 < month2){
                 return false;
               }else{
                 if (day1>=day2){
                   return true;
                 }else{
                   return false;
                 }
               }
             }
           }

           /** convert String to date
            * @author Nghi Doan
            * @return true/false
            */
           public Date convertStringToDate(String date){
             int day1 = Integer.parseInt(date.substring(0,date.indexOf("/")));
             int month1 = Integer.parseInt(date.substring(date.indexOf("/")+1,date.lastIndexOf("/")));
             int year1 = Integer.parseInt(date.substring(date.lastIndexOf("/")+1,date.length()));
             java.util.Date d1 = new java.util.Date(year1-1900,month1-1,day1);
             return d1;
           }


      /** Add day
       * @author 	Tuan.Truong
       * @return	String date
       */
      public String addDate(Date d1, long num){
        long dtemp = getDate(d1);
        java.util.Date day = getDate(dtemp+MILLISEC_PER_DATE*num);
        java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT);
        String date = fd.format(day);
        return date;
      }

        /** Get the system yyyy-MM-dd
     * @author 	Nghi Doan
     * @return	String date
   */
        public String getSystemDate(int i){
            java.util.Date day = new java.util.Date();
            java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT1);
            String date = fd.format(day);
            return date;
        }
        public String getSystemDateStandard(){
            java.util.Date day = new java.util.Date();
            java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT1);
            String date = fd.format(day);
            return date;
        }


        public String getSystemShortDate() {
          java.util.Date day = new java.util.Date();
          java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat(DATE_FORMAT3);
          String date = fd.format(day);
          return date;
        }


   /** Gets the Date in long type
   * @author 	Trung.Nguyen
   * @param 	date Date in Date type
   * @return	the date
   */
        public long getDate(Date date) {
            return date.getTime();
        }
        /** Gets the Date in Date type
     * @author 	Trung.Nguyen
     * @param 	date Date in long type
     * @return	the date
   */
        public Date getDate(long date) {
            return new Date(date);
        }

        /** Print current date time
     * @author 	Trung.Nguyen
     * @param 	date Date in long type
     * @return	print the date time to console
   */
        public void printDate(long date) {
//            System.out.println(new Date(date));
        }

        /** Gets the number Date between date1 and date2
     * @author 	Trung.Nguyen
     * @param 	date1 First Date in Date type
     * @param 	date2 Second Date in Date type
     * @return	the number date
   */
        public int subDate(Date date1, Date date2){
                long millisec=getDate(date1)-getDate(date2);
                return Math.round(millisec/MILLISEC_PER_DATE);
        }
        /** Gets the number Date between date1 and date2
     * @author 	Trung.Nguyen
     * @param 	date1 First Date in long type
     * @param 	date2 Second Date in long type
     * @return	the number date
   */
        public int subDate(long date1, long date2){
                long millisec=date1-date2;
                return Math.round(millisec/MILLISEC_PER_DATE);
        }
        /**Change date format YYYY-MM-DD to DD/MM/YYYY
         * @param tmpDate date have format YYYY-MM-DD
         * @return date have format DD/MM/YYYY
         */
        public String  getDD_MM_YYYY(String tmpDate){
             String sDate      ;
             sDate=tmpDate.substring(tmpDate.lastIndexOf("-")+1)+"/"+tmpDate.substring(tmpDate.indexOf("-")+1,tmpDate.lastIndexOf("-"))+"/"+tmpDate.substring(0,tmpDate.indexOf("-"));
             return sDate;
         }
         /**Change date format DD/MM/YYYY to YYYY-MM-DD
          * @param tmpDate date have format DD/MM/YYYY
          * @return date have format YYYY-MM-DD
          */
         public String  getYYYY_MM_DD(String tmpDate){
              String sDate      ;
              sDate=tmpDate.substring(tmpDate.lastIndexOf("/")+1)+"-"+tmpDate.substring(tmpDate.indexOf("/")+1,tmpDate.lastIndexOf("/"))+"-"+tmpDate.substring(0,tmpDate.indexOf("/"));
              return sDate;
          }

          /**Change date format MM/DD/YYYY to DD/MMYYYY
           * @param tmpDate date have format DD/MM/YYYY
           * @return date have format DD/MMYYYY
           */
          public String  getDD_MM_YYYY2(String tmpDate){
               String sDate      ;
               sDate=tmpDate.substring(tmpDate.indexOf("/")+1,tmpDate.lastIndexOf("/"))+"/"+tmpDate.substring(0,tmpDate.indexOf("/"))+"/"+tmpDate.substring(tmpDate.lastIndexOf("/")+1);
               return sDate;
           }

        //********************************************************************************//
        //************************ BACKUP RECOVERY FUNCTION ******************************//
        //********************************************************************************//

         /**  commit chunk of records that insert or update to TMP_CALC_ENGINE
     * @author			Trung.Nguyen
     * @param 		    appName name of process that running
     * @param 		    threadNum thread is running
     * @param 		    chunk_num chunk is processed
     * @param 		    recCount number of record that is processed
     * @param 		    vSqlStr set of query that insert or updateto DB
     * @param 		    DAL DataAccessLayer
     */
        public boolean commitChunk(String appName,int threadNum,int chunk_num,int recCount, int fetchSize,PreparedStatement psIns,String typeApp,DataAccessLayer DAL) {
                boolean isCommitChunk=false;

                try {
                        if(recCount==fetchSize){
                                isCommitChunk=true;
                                DAL.setBeginTrans(DAL.getConnection());

                                        psIns.executeUpdate();//queues this for later execution
                                //update status=finish
                                updateBackupRecovery(threadNum,chunk_num,appName,STATUS_FIN_CHUNK,typeApp,DAL);
                                DAL.setEndTrans(DAL.getConnection());
        //			printDate(System.currentTimeMillis());
        //			System.out.println("     "+appName+" at chunk "+chunk_num+" at thread "+threadNum);
                        }else{
                                psIns.executeUpdate();
                        }
                } catch (Exception e) {
                        ExceptionHandle eh = new ExceptionHandle();
                        eh.ouputError(e);
                }

                return isCommitChunk;
        }
         /**  commit chunk of records that insert or update to EXT_REQUEST ,PL,SHADOW
     * @author			Trung.Nguyen
     * @param 		    appName name of process that running
     * @param 		    threadNum thread is running
     * @param 		    chunk_num chunk is processed
     * @param 		    recCount number of record that is processed
     * @param 		    vSqlStr set of query that insert or updateto DB
     * @param 		    DAL DataAccessLayer
     */

        public boolean commitChunk(String appName,int threadNum,int chunk_num,int recCount, int fetchSize,PreparedStatement psInsExtReq,PreparedStatement psDelPL,PreparedStatement psInsPL,PreparedStatement psDelSha, PreparedStatement psInsSha,String typeApp,DataAccessLayer DAL) {
                boolean isCommitChunk=false;
                if(recCount==fetchSize){
                        isCommitChunk=true;
                        DAL.setBeginTrans(DAL.getConnection());
                        try {
                          ((OraclePreparedStatement)psInsExtReq).sendBatch ();
                          ((OraclePreparedStatement)psDelPL).sendBatch ();
                          ((OraclePreparedStatement)psInsPL).sendBatch ();
                          ((OraclePreparedStatement)psDelSha).sendBatch ();
                          ((OraclePreparedStatement)psInsSha).sendBatch ();
                      //JDBC sends the queued request

                        } catch (Exception e) {
                                ExceptionHandle eh = new ExceptionHandle();
                                eh.ouputError(e);
                        }
                        //update status=finish
                        updateBackupRecovery(threadNum,chunk_num,appName,STATUS_FIN_CHUNK,typeApp,DAL);
                        DAL.setEndTrans(DAL.getConnection());
//			printDate(System.currentTimeMillis());
//			System.out.println("     "+appName+" at chunk "+chunk_num+" at thread "+threadNum);

                }
                return isCommitChunk;
        }

        /** commit last chunk of records that insert or update to DB if need
     * @author 			Trung.Nguyen
     * @param 		    appName name of process that running
     * @param 		    threadNum thread is running
     * @param 		    chunk_num chunk is processed
     * @param 		    recCount number of record that is processed
     * @param 		    vSqlStr set of query that insert or updateto DB
     * @param 		    DAL DataAccessLayer
     */
        //TMP_CAL_ENG
        public void commitLastChunk(String appName,int threadNum,int chunk_num, int recCount, PreparedStatement psIns,String typeApp, DataAccessLayer DAL) {
                if(recCount>0){
                        DAL.setBeginTrans(DAL.getConnection());
                        try {
                          ((OraclePreparedStatement)psIns).sendBatch ();
                      //JDBC sends the queued request

                        } catch (Exception e) {
                                ExceptionHandle eh = new ExceptionHandle();
                                eh.ouputError(e);

                        }

                        //update status=finish process
                        updateBackupRecovery(threadNum,chunk_num,appName,STATUS_FIN_PROCESS,typeApp,DAL);
                        DAL.setEndTrans(DAL.getConnection());
                }else{
                        //update status=finish process
                        updateBackupRecovery(threadNum,chunk_num,appName,STATUS_FIN_PROCESS,typeApp,DAL);
                }
        }
        //EXT_REQUEST , PL
        public void commitLastChunk(String appName,int threadNum,int chunk_num, int recCount, PreparedStatement psInsExtReq,PreparedStatement psDelPL,PreparedStatement psInsPL,PreparedStatement psDelSha, PreparedStatement psInsSha,String typeApp, DataAccessLayer DAL) {
                if(recCount>0){
                        DAL.setBeginTrans(DAL.getConnection());
                        try {
                          ((OraclePreparedStatement)psInsExtReq).sendBatch ();
                          ((OraclePreparedStatement)psDelPL).sendBatch ();
                          ((OraclePreparedStatement)psInsPL).sendBatch ();
                          ((OraclePreparedStatement)psDelSha).sendBatch ();
                          ((OraclePreparedStatement)psInsSha).sendBatch ();
                      //JDBC sends the queued request

                        } catch (Exception e) {
                                ExceptionHandle eh = new ExceptionHandle();
                                eh.ouputError(e);
                        }

                        //update status=finish process
                        updateBackupRecovery(threadNum,chunk_num,appName,STATUS_FIN_PROCESS,typeApp,DAL);
                        DAL.setEndTrans(DAL.getConnection());
                }else{
                        //update status=finish process
                        updateBackupRecovery(threadNum,chunk_num,appName,STATUS_FIN_PROCESS,typeApp,DAL);
                }
        }

        /** Insert infomation of backup recovery into QR_BACKUP_RECOVERY
     * @author	Tuan.Truong
     * @param	appName position of Backup Restart Recovery
     * @param	thread_num  thread number
     * @param	chunk_num  chunk number
     * @param	status  status of thread
     * @param	DAL    Data Access Layer
     */
        public void insertBackupRecovery(int thread_num,int chunk_num, String appName,  String status,DataAccessLayer DAL ) {
                String sqlStr= "";

                sqlStr="insert into QR_BACKUP_RECOVERY values (" + thread_num + "," + chunk_num + ",'" + appName+"','"+status+"')";
//                System.out.println(sqlStr);
                DAL.executeUpdate(sqlStr);
        }

        public void writeToTextFile(String fileName, String stBufferSource) {
      //    String stBufferConvertString;
          //create a text file to passed on data
          try {
            OutputStream tempCalcEngFile = new FileOutputStream(fileName, true); //to orverwrite
            //write infomation into text file by chunk
      //      stBufferConvertString = stBufferSource.toString();
            tempCalcEngFile.write(stBufferSource.getBytes());
            tempCalcEngFile.close();
          }
          catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
          }
        }


        /** Update infomation of backup recovery to QR_BACKUP_RECOVERY
     * @author 			Tuan.Truong
     * @param 		    appName name of process that running
     * @param 		    threadNum thread is running
     * @param 		    chunk_num chunk is processed
     * @param 		    status process is running or finish
     * @param 		    DAL DataAccessLayer
     */
        public void updateBackupRecovery(int thread_num,int chunk_num, String appName,  String status, String typeApp, DataAccessLayer DAL) {
                String sqlStr;
                sqlStr = "update QR_BACKUP_RECOVERY set APP_NAME = '"+appName+"',THREAD_NUM = " + thread_num + ", CHUNK_NUM = " + chunk_num + ", STATUS = '" + status + "'" +
                                 " where Thread_Num="+thread_num +" and LOWER(APP_NAME) LIKE LOWER('%"+typeApp+"%')";
//                             System.out.println(sqlStr);
                             DAL.executeUpdate(sqlStr);//update
        }

        /** Method  delete Backup Recovery -
     * @author 			Tuan.Truong
     * @param 		    threadNum thread is running
     * @param 		    appName name of process that running
     * @param 		    DAL DataAccessLayer
     */
        public void deleteBackupRecovery(int threadNum,String typeApp,DataAccessLayer DAL) {
                String sqlStr = "delete QR_BACKUP_RECOVERY " +
                                                "where Thread_Num="+threadNum+" and LOWER(APP_NAME) LIKE LOWER('%"+typeApp+"%')";
//                                            System.out.println(sqlStr);
                DAL.executeUpdate(sqlStr); //delete
        }
        /** Check whether this Process run backup or not
     * @param 		    appName name of process that running
     * @param 		    appNameBackup name of process that should be backup
     * @return		    true if the process should be backup
     */
        public boolean isBackupProcess(String appName,String appNameBackup) {
                boolean isBackup=false;
                if(appName.equals(appNameBackup)){
                        isBackup=true;
                }
                return isBackup;
        }
        /** Method  checkBackupRecovery - check to see if there is any record in the Bookmark table
         * It's return True and get a chunk_num, thread_num, app_name if see any record or else is False
     * @author 			Nghi.Doan
     * @param			app_name
     */
        public boolean checkBackupRecovery(String app_name, DataAccessLayer DAL) {
                boolean isCheckBackup = false;
                int i;
                int totalRecordBackup = 0;
                ResultSet rsCheck;

                String sqlStr =	"select * from QR_BACKUP_RECOVERY where APP_NAME = '" + app_name + "'";
//                System.out.println(sqlStr);
                rsCheck = DAL.executeQueries(sqlStr);
                try {
                        if (rsCheck.next()){
                                isCheckBackup = true;
                        }
                        rsCheck.getStatement().close();
                }catch (Exception e){
                        ExceptionHandle eh = new ExceptionHandle();
                        eh.ouputError(e);
                }
                return isCheckBackup;
        }
        /** check batch job if complete
         * @author Nghi Doan
         * @param batchName
         * @param batchStatus
         * @param  paraDate date
         * @param DAL
         * @return true if complete
         */
//        Trungx
//        public boolean batchjobComplete(String batchName, String batchStatus, String paraDate, DataAccessLayer DAL){
//          ResultSet rs;
//          boolean complete=false;
//
//          String sqlStr ="select * from BTM_IM_BATCH_MAINTENANCE where batch_name = '" + batchName + "' and batch_status='" + batchStatus +"' and batch_date = " +
//                              " to_date('" + paraDate + "','" + DATE_FORMAT + "')";
//          rs = DAL.executeQueries(sqlStr);
//          try {
//            if (rs.next()) {
//              complete = true;
//            }
//          }catch (Exception e){
//            ExceptionHandle eh = new ExceptionHandle();
//            eh.ouputError(e);
//          }
//          return complete;
//
//        }

        /** check Sim batch job if complete
         * @author Trung Nguyen
         * @param batchName
         * @param batchStatus
         * @param  paraDate date
         * @param DAL
         * @return true if complete
         */
//        Trungx
        public boolean batchjobSimComplete(String batchName, String batchStatus, String paraDate, DataAccessLayer DAL){
          ResultSet rs;
          boolean complete=false;

          String sqlStr ="select * from BTM_IM_BATCH_MAINTENANCE where batch_name = '" + batchName + "' and batch_status='" + batchStatus +"' and batch_date = " +
                              " to_date('" + paraDate + "','" + DATE_FORMAT + "')";
          rs = DAL.executeQueries(sqlStr);
          try {
            if (rs.next()) {
              complete = true;
            }
            rs.getStatement().close();
          }catch (Exception e){
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
          }
          return complete;

        }
        /** check Pos batch job if complete
         * @author Trung Nguyen
         * @param batchName
         * @param batchStatus
         * @param  paraDate date
         * @param DAL
         * @return true if complete
         */
//        Trungx
        public boolean batchjobPosComplete(String batchName, String batchStatus, String paraDate, DataAccessLayer DAL){
          ResultSet rs;
          boolean complete=false;

          String sqlStr ="select * from BTM_POS_BATCH_MAINTENANCE where batch_name = '" + batchName + "' and batch_status='" + batchStatus +"' and batch_date = " +
                              " to_date('" + paraDate + "','" + DATE_FORMAT + "')";
          rs = DAL.executeQueries(sqlStr);
          try {
            if (rs.next()) {
              complete = true;
            }
            rs.getStatement().close();
          }catch (Exception e){
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
          }
          return complete;

        }

        //********************************************************************************//
        //************************ RESULTSET FUNCTION ******************************//
        //********************************************************************************//

        /** Go to row at specific position
     * @author 	Trung.Nguyen
     * @param 	rs ResultSet
     * @param 	position where the cursor go to

   */
        public void gotoRow(ResultSet rs,int position){
                for (int i = 0; i < position; i++) {
                        try {
                                rs.next();
                        } catch (Exception e) {
                                ExceptionHandle eh = new ExceptionHandle();
                                eh.ouputError(e);
                        }

                }
        }
        //********************************************************************************//
        //************************ ADIDAS FUNCTION ******************************//
        //********************************************************************************//
        /**Limit length of input text in JTextField
        * @param inputJTextField to be checked
        * @param LENGHT_INPUT number character to be limited
        * @return JTextField have input text with length less or equal  LENGHT_INPUT
        */
        public void limitLenjTextField(KeyEvent e,JTextField txtField,int LENGHT_INPUT) {
          if (( txtField.getText().length() == LENGHT_INPUT) && (e.getKeyChar() != e.VK_BACK_SPACE) && (txtField.getSelectionStart() == txtField.getSelectionEnd()) ) {
            e.consume();
          } else if (txtField.getText().length() > LENGHT_INPUT) {
            String strTemp = txtField.getText() ;
            txtField.setText(strTemp.substring(0,LENGHT_INPUT));
          }
        }

        public void limitLenjComboBox(KeyEvent e,JComboBox cbo,int LENGHT_INPUT) {
         if (( cbo.getSelectedItem().toString().length() == LENGHT_INPUT) && (e.getKeyChar() != e.VK_BACK_SPACE) ) {
           e.consume();
         } else if (cbo.getSelectedItem().toString().length() > LENGHT_INPUT) {
           String strTemp = cbo.getSelectedItem().toString() ;
           cbo.setSelectedItem(strTemp.substring(0,LENGHT_INPUT));
         }
       }


        /**Limit length of input text in JTextArea
        * @param inputJTextArea to be checked
        * @param LENGHT_INPUT number character to be limited
        * @return JTextArea have input text with length less or equal LENGHT_INPUT
        */
        public void limitLenjTextArea(KeyEvent e,JTextArea txtArea,int LENGHT_INPUT) {
          if (( txtArea.getText().length() == LENGHT_INPUT) && (e.getKeyChar() != e.VK_BACK_SPACE) && (txtArea.getSelectionStart() == txtArea.getSelectionEnd()) ) {
            e.consume();
          } else if (txtArea.getText().length() > LENGHT_INPUT) {
            String strTemp = txtArea.getText() ;
            txtArea.setText(strTemp.substring(0,LENGHT_INPUT));
          }
        }
        /**Limit length of input text in JEditorPane
        * @param inputJEditorPane to be checked
        * @param LENGHT_INPUT number character to be limited
        * @return JEditorPane have input text with length less or equal  LENGHT_INPUT
        */
        public void limitLenjEditorPane(KeyEvent e,JEditorPane txtEditor,int LENGHT_INPUT) {
          if (( txtEditor.getText().length() == LENGHT_INPUT) && (e.getKeyChar() != e.VK_BACK_SPACE) && (txtEditor.getSelectionStart() == txtEditor.getSelectionEnd()) ) {
            e.consume();
          } else if (txtEditor.getText().length() > LENGHT_INPUT) {
            String strTemp = txtEditor.getText() ;
            txtEditor.setText(strTemp.substring(0,LENGHT_INPUT));
          }
        }
        /**Limit length of input text in JTextPane
        * @param inputjTextPane to be checked
        * @param LENGHT_INPUT number character to be limited
        * @return JTextPane have input text with length less or equal  LENGHT_INPUT
        */
        public void limitLenTypedjTextPane(KeyEvent e,JTextPane txtPane,int LENGHT_INPUT) {
          if (( txtPane.getText().length() == LENGHT_INPUT) && (e.getKeyChar() != e.VK_BACK_SPACE) && (txtPane.getSelectionStart() == txtPane.getSelectionEnd()) ) {
            e.consume();
          } else if (txtPane.getText().length() > LENGHT_INPUT) {
            String strTemp = txtPane.getText() ;
            txtPane.setText(strTemp.substring(0,LENGHT_INPUT));
          }
        }

        /**Check item code is Integer number or not
        * @param sSource to be checked
        * @return True if sSource is Integer string, otherwise, it returns false.
        */
       public boolean isIntInput(Frame frm,String mainInput,String mess){
          boolean isValid=true;
          if (!isLongIntString(mainInput)) {
            showMessage(frm,"Message",mess);
            isValid=false;
          }
          return isValid;
        }

        /**Check item code is Integer number or not
        * @param sSource to be checked
        * @return True if sSource is Integer string, otherwise, it returns false.
        */
       public boolean isLongIntInput(Frame frm,String mainInput,String mess){
          boolean isValid=true;
          if (!isLongIntString(mainInput)) {
            showMessage(frm,"Message",mess);
            isValid=false;
          }
          return isValid;
        }

       public boolean isLongIntInput(Frame frmMain,String mainInput){
          boolean isValid=true;
          if (!isLongIntString(mainInput)) {
            JOptionPane.showMessageDialog(frmMain, "Input must be integer","Message", 1);
            isValid=false;
          }
          return isValid;
        }

        public boolean isIntInput(Frame frmMain,String mainInput){
           boolean isValid=true;
           if (!isIntString(mainInput)) {
             JOptionPane.showMessageDialog(frmMain, "Input must be integer","Message", 1);
             isValid=false;
           }
           return isValid;
         }

        /**Check Amount is float number or not
        * @param sSource to be checked
        * @return True if sSource is float string, otherwise, it returns false.
        */
       public boolean isFloatInput(Frame frmMain,String mainInput){
          boolean isValid=true;
          if (!isFloatString(mainInput)) {
            JOptionPane.showMessageDialog(frmMain, "Input must uuube number","Message", 1);
            isValid=false;
          }
          return isValid;
        }
        /**Check Date
        * @param sSource to be checked
        * @return True if sSource is float string, otherwise, it returns false.
        */
       public boolean isDateInput(Frame frmMain,String mainInput,String delimeter){
          boolean isValid=true;
          if (!checkDate(mainInput,delimeter)){
            JOptionPane.showMessageDialog(frmMain, "Enter the date (dd/MM/yyyy).","Message", 1);
            isValid=false;
          }
          return isValid;
        }
//        //check date
//        public boolean checkDate(String date){
//         boolean isValid=true;
//         if (!checkDate(mainInput,delimeter)){
//           JOptionPane.showMessageDialog(frmMain, "Enter the date (dd/MM/yyyy).","Message", 1);
//           isValid=false;
//         }
//         return isValid;
//       }

        //get exchange rate from BTM_POS_EXCHANGE_RATE
        public float getExchangeRate(DataAccessLayer DAL,String currencyID){
          float exchangeRate=0;
          String sSQL=new String();
          ResultSet rs=null;

          sSQL = "select * from BTM_POS_EXCHANGE_RATE where CURR_ID='" + currencyID +
              "' order by WORKDAY DESC";
//          System.out.println(sSQL);
          rs=DAL.executeQueries(sSQL);
          try{
            if(rs.next()){
              exchangeRate=rs.getFloat("EXCHANGE_RATE");
            }
            rs.getStatement().close();
          }
          catch (Exception ex) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(ex);
          }
          return exchangeRate;
        }

        public String addYeroCode(int num) {
          String st=null;
          for (int i=0;i<=num;++i) {
            st=st+"0";
          }
          return st;
        }

        public String getYeroCode(int num, int lenStr) {
          String st =null;
          int pos1=0;
          int pos2=0;
          st=addYeroCode(lenStr) + num;
          pos1=st.length()- lenStr;
          pos2=st.length();
          if (pos1 < 0) {
            st = String.valueOf(num).toString();
            return st;
          }
          st=st.substring(pos1, pos2);
          return st;
        }
        /** Show message dialog only
        * @author 	Trung.Nguyen
        * @param 	frame parent frame
        * @param 	title title of dialog
        * @param 	message message of dialog
        * @return	no
        */

        public void showMessage(Frame frame, String title,String message) {
            DialogMessage dlg = new DialogMessage(frame, title, true, message,
                                                  DialogMessage.WARNING_MESSAGE
                                                  , DialogMessage.OK_OPTION);
            dlg.setVisible(true);
        }

        /** Show confirm dialog
        * @author 	Trung.Nguyen
        * @param 	frame parent frame
        * @param 	title title of dialog
        * @param 	message message of dialog
        * @param 	messageType info, question, warning or error messaage
        * @param 	optionType ok, yes-no, yes-no-cancel dialog
        * @return	selected option (yes, no or cancel)
        */

        public int showMessage(Frame frame, String title,String message, int messageType,int optionType) {
            DialogMessage dlg = new DialogMessage(frame, title, true, message,messageType,optionType);
            dlg.setVisible(true);
            return dlg.getselectValue();
        }


        /** Combine temp file to text file
         * @author	Nghi.Doan
         * @param   fileNameFrom source file
         * @param 	fileNameTo target file
         */
        public void combineFile(String fileNameFrom, String fileNameTo) throws IOException {
          OutputStream out = null;
          try {
            out = new FileOutputStream(fileNameTo,true);
            RandomAccessFile in = new RandomAccessFile(fileNameFrom,"r");
            while (in.getFilePointer()<in.length()){
              String strLine = in.readLine();
              strLine = strLine + "\r\n";
              out.write(strLine.getBytes());
            }
            out.close();
            in.close();
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /** format Exchange Rate
     * @author	Phuoc.Nguyen
     * @param   number is a Number in String
     * @param 	n is a Integer stand after character '.'
     * number must is a float number
     * return a String with format 0.123...n
    **/
    public String formatExchangeRate(String number, int n) {
        String result = "";
        if (number.indexOf('.') == -1) {
            number += ".0";
        }
        if (number.indexOf('.') == 0) {
            number = "0" + number;
        }

        int dotPos = number.indexOf('.');
        int len = number.length();
        int lenAfterDot = len - dotPos - 1;
        while (lenAfterDot < n) {
            number += "0";
            lenAfterDot++;
        }

        if (lenAfterDot > n) {
            char c = number.charAt(dotPos + n + 1);
            if (c > '4') {
                char c1 = number.charAt(dotPos + n);
                c1++;
                result = number.substring(0, dotPos + n);
                result += String.valueOf(c1);
            }
        }
        else {
            result = number.toString();
        }
        return result;
    }

    /**checkFloatNumber(KeyEvent e)
     * @author Phuoc Nguyen
     * @param KeyEvent
     */
    //check Float number with n number after character '.'
    public void checkFloatNumber(KeyEvent e, String strNumber, int n) {
      char key = e.getKeyChar();
      int len = strNumber.length();
      int n1 = strNumber.indexOf(".");
      int keyPos = e.getKeyLocation();
      if(keyPos < n1){
//        System.out.println("----------<");
      }
      else{
//        System.out.println("---------->");
      }
//      if (n1 == -1) { //46 = '.'
//        if (isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE ||
//            key == KeyEvent.VK_DELETE || key == 46)
//          return;
//        else
//          e.consume();
//      }
//      else {
//        if (keyPos > n1) {
//          e.consume();
//          return;
//        }
//        int len1 = len - n1 - 1;
//        if (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE ||
//            (len1 < n && (isIntString("" + key))))
//          return;
//        else {
//          if (keyPos <= n1 &&
//              (isIntString("" + key) || key == KeyEvent.VK_BACK_SPACE ||
//               key == KeyEvent.VK_DELETE)) {
//            return;
//          }
//          e.consume();
//        }
//      }

    }

    /**isExRate(String number)
     * @author Phuoc Nguyen
     * @param String
     */
    //check Float number
    public boolean isExRate(String number){
        int len = number.length();
        int dotFirst = number.indexOf(".");
        int dotLast = number.lastIndexOf(".");
        if(dotFirst!=dotLast)
            return false;

        for(int i=0; i<len; i++){
            if((number.charAt(i)<48 || number.charAt(i)>57)&&number.charAt(i)!=46)
                return false;
        }
        return true;
    }


    public static String ToUpperString(String sInput)
    {
      String str="";
      try{

        str = sInput.toUpperCase();
      }catch(Exception ex)
      {
        ExceptionHandle eh = new ExceptionHandle();
        eh.ouputError(ex);
      }
      return str;
    }

}//end class
