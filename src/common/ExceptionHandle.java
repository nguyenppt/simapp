package common;

/**
 * @author Trung.Nguyen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 *  Ver 1.1 - 11-08-2004
 */
import java.io.*;
import java.lang.Exception;
import javax.swing.JOptionPane;
public class ExceptionHandle {
        public String fileError="";
        DataAccessLayer DAL = new DataAccessLayer();

        public ExceptionHandle(){
                //set connection
                DAL.getConnfigParameter();
                //set path for error file
                fileError=DAL.getErrorFile();
        }

        /**  Display friendly info of Exception for User
     * @author 			Trung.Nguyen
     * @param 		    Exception ex,
     * @param			String mess
     */
        private void displayException(Exception ex,String mess){
                JOptionPane.showMessageDialog(null, mess);
        }

        /** Store info of Exception to table on DB
         * write to table : ID of Exc, Date of Exc, Description of Exc, class of Exc)
         * => (NOW for test only: write to QR_EXCEPTIONTBL table which have 5 fields: ID,  ENTRY_DATE, ERROR_MESSAGE,ERROR_CLASS_OBJECT)
     * @author 			Trung.Nguyen
     * @param 		    Exception ex,
     * 					String date  - date when Exception occurs
     */
        private void storeExcToTable(Exception ex, String date){
            String query="INSERT INTO QR_EXCEPTION " +
                                         "VALUES (EXC_ID_SEQ.nextval,'" +date+"','"+ex.toString()+"','"+ex.getClass().toString()+"')";

            try {
                    DAL.executeUpdate(query);
                    DAL.getConnection().commit();
                } catch (Exception e) {
                        //e.getSystemDate();
                        ExceptionHandle eh = new ExceptionHandle();
                        eh.ouputError(e);
                        Utils ut = new Utils();
                        storeExcToFile(e,ut.getSystemDate());
                }
        }

        /**Save exception info to file
         * write to file : Date of Exc, class of Exc, Description of Exc
     * @author 			Trung.Nguyen
     * @param 		    content string will save to file
         * @param 			fileName path of file
         * @param 			isAppend determine append to file or create new file
     */
        private void storeExcToFile(Exception ex,String date){
                String sMess="";
                sMess = "\r\n";
                sMess += "============================================================"+"\r\n";
                sMess +="Date: "+date + "\r\n";
                sMess +="Class: " + ex.getClass().toString()+"\r\n";
            sMess += "============================================================" + "\r\n";

                try{
                  //write date, class name
              OutputStream fwrite = new FileOutputStream( fileError, true);
              fwrite.write( sMess.getBytes());
              fwrite.write("\r\n".getBytes());
              fwrite.close();
              //write error desc
          ex.printStackTrace(new PrintStream(new FileOutputStream(fileError,true)));
            }catch (Exception e ){
              e.printStackTrace();
            }
        }

        /** Output text file, then exit program
     * @author 			Trung.Nguyen
     * @param 		    e Exception
     */
        public void ouputError(Exception e) {
                Utils ut = new Utils();
                e.printStackTrace();
                storeExcToFile(e,ut.getSystemDateTime());
//                storeExcToTable(e,ut.getSystemDateTime());
                displayException(e,e.getMessage());
                System.exit(0);
        }
}//end class
