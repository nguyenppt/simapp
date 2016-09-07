//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//






















































package pos;

import java.sql.*;
import btm.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import common.*;
import btm.attr.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.print.*;
import java.text.*;
import java.awt.font.*;
import javax.swing.text.*;
public class PanelPrintTransfer extends JPanel implements Printable{
    private static final int MARGIN_LEFT = 20;
    private static final int MARGIN_TOP = 5;
    private static final int LINE_SPACE = 16;
  BJTable jTable1 ;
  SortableTableModel dm = new SortableTableModel();
  Vector vc = new Vector();
  Transaction trans;
  int printType=Constant.PRINT_NEW_SALE;

  FrameMain frmMain;
  Vector vSaleItem;
    DataAccessLayer DAL ;
    String StoreID="";
    Item item;

  public PanelPrintTransfer(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    jTable1 = frmMain.pnlTransferItem.table;
  }

   void setSaleItem(Vector vSaleItem) {
     this.vSaleItem = vSaleItem;

   }

   void setTransaction(Transaction trans) {
      this.trans = trans;
    }

  public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {
    if (pi >=1){
     return Printable.NO_SUCH_PAGE;
     }else {
       Graphics2D g2d = (Graphics2D) g;
       g2d.translate(pf.getImageableX(), pf.getImageableY());
       drawShapes(g2d,pf);
       return Printable.PAGE_EXISTS;
     }
   }
 public void drawShapes(Graphics2D g2, PageFormat pf){
   Utils ut = new Utils();
   JLabel labelbacode = new JLabel();
   Font  f = new Font("AduHuas", Font.PLAIN,10);
   Font  f1 = new Font("Arial", Font.PLAIN,12);
   Font  f2 = new Font("Arial", Font.PLAIN,10);
   double fTotal = 0;
   double disAmount =0.0;
   double fRetail =0;
   DAL= frmMain.getDAL();
   String source_storeID = frmMain.pnlTransferItem.txtSrcStoreID.getText();
   String des_storeID = frmMain.pnlTransferItem.cboDesStoreID.getSelectedItem().toString();
   String transferID = frmMain.pnlTransferItem.txtTransferID.getText();
//   System.out.println("store ID new:"+source_storeID+"To StoreID"+des_storeID);
   String creater= frmMain.pnlTransferItem.txtCreaterName.getText();
   String deleiver = frmMain.pnlTransferItem.txtReceiverName.getText();
   String store_name = DAL.getStoreName();
//   String quantity = frmMain.pnlTransferItem.txtq
//   System.out.println(store_name);
   int row = frmMain.pnlTransferItem.dm.getRowCount();
//   System.out.println("a:"+row);
   Dimension d = getSize();
            int gridWidth = 200 / 6;
            int gridHeight = 300 / 2;
            int rowspacing = 5;
            int columnspacing = 7;
            int rectWidth = gridWidth - columnspacing;
            int rectHeight = gridHeight - rowspacing;
            Color fg3D = Color.BLACK;
            g2.setPaint(fg3D);
            Image img = new ImageIcon("images\\adilog.jpg").getImage();
            //image
            g2.drawImage(img,MARGIN_LEFT+140,MARGIN_TOP,32,25,null);
            g2.setFont(new java.awt.Font("AduHuas", 1, 13));
            //Header
            g2.drawString("TRANSFER",MARGIN_LEFT+40,LINE_SPACE*2);
            g2.setFont(new java.awt.Font("Arial", 0, 11));
            g2.drawString("From:"+source_storeID,MARGIN_LEFT,LINE_SPACE*3);
            g2.drawString("To:"+des_storeID,MARGIN_LEFT,LINE_SPACE*4);
            g2.drawString("Transfer ID: "+transferID,MARGIN_LEFT,LINE_SPACE*5);
            g2.drawString(ut.getSystemDateTime().substring(0,10),MARGIN_LEFT+130,LINE_SPACE*3);
            g2.drawString(ut.getSystemDateTime().substring(11,19),MARGIN_LEFT+130,LINE_SPACE*4);
            g2.drawString("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ",MARGIN_LEFT,LINE_SPACE*6);
            //header
            int k=0;
            for (int i = 0,j=i+1; i < row; i++,++j){
              g2.setFont(f);
              int quantitya=0 ;
//              quantitya += jTable1.getValueAt(i,2);
              g2.drawString("" + j + ".", MARGIN_LEFT, LINE_SPACE * (7 + k));
              g2.drawString(""+ jTable1.getValueAt(i,1), MARGIN_LEFT + 10,LINE_SPACE * (7 + k));
//              System.out.println("item des:"+ jTable1.getValueAt(i,1));
              g2.drawString(""+jTable1.getValueAt(i,0), MARGIN_LEFT, LINE_SPACE * (8 + k));
              g2.drawString(""+jTable1.getValueAt(i,2), MARGIN_LEFT + 70,LINE_SPACE * (8 + k));
//                    fRetail += item.getQuantityR() * item.getunitPrice();
//                    g2.drawString(String.valueOf(item.getQuantity()), MARGIN_LEFT + 75,
//                                LINE_SPACE * (8 + k));
//                  fRetail += item.getQuantity() * item.getunitPrice();
//                g2.drawString(ut.formatNumbervn(String.valueOf(item.getunitPrice())), MARGIN_LEFT + 85,
//                              LINE_SPACE * (8 + k));
//                g2.drawString(ut.formatNumbervn(String.valueOf(item.getAmountDue())),
//                              MARGIN_LEFT + 135, LINE_SPACE * (8 + k));
                if (j == row) {

//                  String inTRanID = String.valueOf(trans.getTrans_ID());
//                  System.out.println(inTRanID);
//                  AttributedString astr = new AttributedString(inTRanID);
//                  astr.addAttribute(TextAttribute.FONT,
//                                    Font.getFont("ID",
//                                                 new java.awt.
//                                                 Font("IDAutomation.com Code39", 0, 10)));
//                  TextLayout tl = new TextLayout(astr.getIterator(),
//                                                 g2.getFontRenderContext());
                    g2.drawString(
                        "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ",
                        MARGIN_LEFT, LINE_SPACE * (9 + k));
                    g2.setFont(new java.awt.Font("Arial", 0, 11));
                    g2.drawString("SUBTOTAL: ", 40, LINE_SPACE * (10 + k));
                    g2.drawString("DISCOUNT: ", 40, LINE_SPACE * (11 + k));
                    g2.drawString("TOTAL: ", 40, LINE_SPACE * (12 + k));
                    g2.drawString("VND", 100, LINE_SPACE * (10 + k));
                    g2.drawString("VND", 100, LINE_SPACE * (11 + k));
                    g2.drawString("VND", 100, LINE_SPACE * (12 + k));
                    g2.setFont(new java.awt.Font("Arial", 1, 12));
//                    g2.drawString(ut.formatNumbervn(""+fRetail), 140, LINE_SPACE * (10 + k));
//                    g2.drawString(ut.formatNumbervn("" + disAmount), 140, LINE_SPACE * (11 + k));
//                    g2.drawString(ut.formatNumbervn("" + fTotal), 140, LINE_SPACE * (12 + k));
//                    tl.draw(g2, MARGIN_LEFT + 10, LINE_SPACE * (14 + k));
                    g2.drawString("Creater:",MARGIN_LEFT+10,LINE_SPACE * (14 + k));
                    g2.drawString("Receiver:",MARGIN_LEFT+120,LINE_SPACE * (14 + k));
                    if (creater.length() <10){
                      g2.drawString("" + creater, MARGIN_LEFT + 10,LINE_SPACE * (19 + k));
                      g2.drawString(""+deleiver,MARGIN_LEFT+120,LINE_SPACE * (19 + k));
                    }else{
                      g2.drawString("" + creater.substring(0, 10), MARGIN_LEFT + 10,LINE_SPACE * (19 + k));
                      g2.drawString(""+deleiver.substring(0,10),MARGIN_LEFT+120,LINE_SPACE * (19 + k));
                    }

                    g2.drawLine(0,LINE_SPACE * (20 + k),240,LINE_SPACE * (20 + k));
                }
                k = k + 2;
            }

        }

        public void setTypePrint(int printType){
          this.printType=printType;
        }
        public void setStoreID(){

        }

}
