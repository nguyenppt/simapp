package pos;

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
public class PanelPrintReciept extends JPanel implements Printable{
    private static final int MARGIN_LEFT = 20;
    private static final int MARGIN_TOP = 5;
    private static final int LINE_SPACE = 16;
//<<<<<<< PanelPrintReciept.java
//  JTable jTable1 ;
//  DefaultTableModel dm = new DefaultTableModel();
//=======
//    public static final int POS_NEW_SA?LE =3 ;
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  BJTable jTable1 ;
  SortableTableModel dm = new SortableTableModel();
//>>>>>>> 1.8
  Vector vc = new Vector();
  Transaction trans;
  int printType=Constant.PRINT_NEW_SALE;

  FrameMain frmMain;
  PanelNewSale pnlNewSale;
//  PanelPayment pnlPayment;

  Vector vSaleItem;
    DataAccessLayer DAL ;
    String StoreID="";
    Item item;

  public PanelPrintReciept(FrameMain frmMain) {
    try {
      this.frmMain = frmMain;
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
  void jbInit() throws Exception {
    pnlNewSale = new PanelNewSale(frmMain);
  vc = pnlNewSale.getSaleItem();
  dm.addRow(vc);
//<<<<<<< PanelPrintReciept.java
//  jTable1 = new JTable(dm);
//=======
  jTable1 = new BJTable(dm,true);

    jLabel6.setBounds(new Rectangle(37, 65, 59, 17));
    jTable1.setBorder(BorderFactory.createLoweredBevelBorder());
    jTable1.getTableHeader().setFont(new java.awt.Font("Dialog",1,12));
    jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable1.setBounds(new Rectangle(6, 89, 279, 218));
    this.add(jLabel2, null);
    this.add(jLabel3, null);
    this.add(jLabel5, null);
    this.add(jLabel6, null);
    this.add(jTable1, null);
    this.add(jLabel4, null);
    this.add(jLabel1, null);
//>>>>>>> 1.8
  }

   void setSaleItem(Vector vSaleItem) {
     this.vSaleItem = vSaleItem;

   }

   void setTransaction(Transaction trans) {
      this.trans = trans;
    }

  public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {
//    public int print(Graphics g, PageFormat pf, int pageIndex) {
            if (pi >=1) {


                return Printable.NO_SUCH_PAGE;
            }
            else {
              // The area of the printable area.
double ix = pf.getImageableX();
double iy = pf.getImageableY();
double iw = pf.getImageableWidth();
double ih = pf.getImageableHeight();

// The area of the actual page.
double x = 10;
double y = 10;
double w = pf.getWidth();
double h = pf.getHeight();
//return Printable.NO_SUCH_PAGE;

              Graphics2D g2d = (Graphics2D) g;
              g2d.translate(pf.getImageableX(), pf.getImageableY());
//              g2d.setClip(0,0,10,10);
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
   String storeID = DAL.getStoreID();
   String store_name = DAL.getStoreName();
//   System.out.println(store_name);
//   Transaction tran = (Transaction) vInfo.elementAt(0);

   String emp = trans.getEmpl_ID();
   Dimension d = getSize();
            int gridWidth = 200 / 6;
            int gridHeight = 300 / 2;
            int rowspacing = 5;
            int columnspacing = 7;
            int rectWidth = gridWidth - columnspacing;
            int rectHeight = gridHeight - rowspacing;
            Color fg3D = Color.BLACK;
            g2.setPaint(fg3D);
//            g2.drawRect(80, 80, 200 - 1, 310);
            Image img = new ImageIcon("images\\adilog.jpg").getImage();
            //image
            g2.drawImage(img,MARGIN_LEFT+140,MARGIN_TOP,32,25,null);
            g2.setFont(new java.awt.Font("AduHuas", 1, 13));
            //store name
            if (store_name.length()<=20){
                g2.drawString(store_name,MARGIN_LEFT+40,LINE_SPACE*2);
              }
              else {
                g2.drawString(store_name.substring(1,20),MARGIN_LEFT+60,LINE_SPACE*2);
              }
            g2.setFont(new java.awt.Font("Arial", 0, 11));
            g2.drawString("St#"+storeID,MARGIN_LEFT,LINE_SPACE*3);
            g2.drawString("Op#"+emp,MARGIN_LEFT,LINE_SPACE*4);
            g2.drawString("CUST:",MARGIN_LEFT,LINE_SPACE*5);
            g2.drawString(ut.getSystemDateTime().substring(0,10),MARGIN_LEFT+130,LINE_SPACE*3);
            g2.drawString(ut.getSystemDateTime().substring(11,19),MARGIN_LEFT+130,LINE_SPACE*4);
            g2.drawString("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ",MARGIN_LEFT,LINE_SPACE*6);
            //header
            int k=0;
            for (int i = 0,j=i+1; i < vSaleItem.size(); i++,++j){
              g2.setFont(f);
              item = (Item) vSaleItem.elementAt(i);
              String itm = item.getDescription();
              fTotal += item.getAmountDue();
              disAmount += item.getMarkdownAmount();
              g2.drawString("" + j + ".", MARGIN_LEFT, LINE_SPACE * (7 + k));
              if (item.getDescription().length() <= 26) {
                g2.drawString(item.getDescription() + " " + item.getattribute1() +
                                " " + item.getattribute2(), MARGIN_LEFT + 10,
                                LINE_SPACE * (7 + k));
                }else {
                  g2.drawString(item.getDescription().substring(0, 26) + " " +
                                item.getattribute1() + " " + item.getattribute2(),
                                MARGIN_LEFT + 10, LINE_SPACE * (7 + k));
                }
                g2.drawString(item.getId(), MARGIN_LEFT, LINE_SPACE * (8 + k));
                if (frmMain.pnlNewSale.flagReturn == Constant.RETURN_ITEM){
                    g2.drawString(ut.formatNumbervn(String.valueOf(item.getQuantity())), MARGIN_LEFT + 70,
                                  LINE_SPACE * (8 + k));
                    fRetail += item.getQuantity() * item.getunitPrice();
                }else{
                  g2.drawString(String.valueOf(item.getQuantity()), MARGIN_LEFT + 75,
                                LINE_SPACE * (8 + k));
                  fRetail += item.getQuantity() * item.getunitPrice();
                }
                g2.drawString(ut.formatNumbervn(String.valueOf(item.getunitPrice())), MARGIN_LEFT + 85,
                              LINE_SPACE * (8 + k));
                g2.drawString(ut.formatNumbervn(String.valueOf(item.getAmountDue())),
                              MARGIN_LEFT + 135, LINE_SPACE * (8 + k));
                if (j == vSaleItem.size()) {

                  String inTRanID = String.valueOf(trans.getTrans_ID());
//                  System.out.println(inTRanID);
                  AttributedString astr = new AttributedString(inTRanID);
                  astr.addAttribute(TextAttribute.FONT,
                                    Font.getFont("ID",
                                                 new java.awt.
                                                 Font("IDAutomation.com Code39", 0, 10)));
                  TextLayout tl = new TextLayout(astr.getIterator(),
                                                 g2.getFontRenderContext());
                  if (printType == Constant.PRINT_NEW_SALE) {
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
                    g2.drawString(ut.formatNumbervn(""+fRetail), 140, LINE_SPACE * (10 + k));
                    g2.drawString(ut.formatNumbervn("" + disAmount), 140, LINE_SPACE * (11 + k));
                    g2.drawString(ut.formatNumbervn("" + fTotal), 140, LINE_SPACE * (12 + k));
                    tl.draw(g2, MARGIN_LEFT + 10, LINE_SPACE * (14 + k));
                    g2.drawString("Thank you  ", 80, LINE_SPACE * (16 + k));
                  }
                  else {
                    g2.drawString(
                        "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ",
                        MARGIN_LEFT, LINE_SPACE * (16 + k));
                    g2.drawString("Deliver:  ", MARGIN_LEFT + 10, LINE_SPACE * (12 + k));
                    g2.drawString("Reciever:  ", MARGIN_LEFT + 150,
                                  LINE_SPACE * (12 + k));
                  }
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
