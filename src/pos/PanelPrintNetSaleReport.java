package pos;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import common.*;
import btm.swing.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;

public class PanelPrintNetSaleReport implements Printable{
 ////////////////////////////////////////////
  FrameMain frmMain;
  DataAccessLayer DAL;
  Statement stmt = null;
  Utils ut = new Utils();
  ResourceBundle lang = DataAccessLayer.getFrameLabel(ut.sLanguague, ut.sCountry);
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch (col){
        case 2: return Double.class;
        case 3: return Double.class;
        default: return Object.class;
      }
    }
  };
  BJTable table ;
//ResultSet Data =  frmMain.pnlNetSalesByItem.getData();
  ////////////////////////////////////////
  JFrame frame;
  public PanelPrintNetSaleReport() {
    frame = new JFrame(lang.getString("SalesReport"));
    try {

//         this.frmMain = frmMain;
//         DAL = frmMain.getDAL();
         DAL = new DataAccessLayer();
         DAL.getOracleConnect();
       }
       catch(Exception ex) {
         ex.printStackTrace();
       }

    frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
      System.exit(0);}});
     table = new BJTable(getData(stmt),dm,true );
     try{
       stmt.close();
     }catch(Exception e){
       ExceptionHandle eh = new ExceptionHandle();
       eh.ouputError(e);
     }
     JScrollPane scrollpane = new JScrollPane(table);
     table.setColumnWidth(0,100);
     table.setColumnWidth(1,400);
     scrollpane.setPreferredSize(new Dimension(500, 80));
     frame.getContentPane().setLayout(new BorderLayout());
     frame.getContentPane().add(BorderLayout.CENTER,scrollpane);
     frame.pack();
     JButton printButton= new JButton();
     printButton.setFont(new java.awt.Font(lang.getString("FontName_Label"), 0, 12));
     printButton.setText(lang.getString("Print"));

     frame.getContentPane().add(
               BorderLayout.SOUTH,printButton);

     // for faster printing turn double buffering off

     RepaintManager.currentManager(
             frame).setDoubleBufferingEnabled(false);

     printButton.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent evt) {
          PrinterJob pj=PrinterJob.getPrinterJob();
          PageFormat pf = new PageFormat();
          Paper paper = new Paper();
          // Set the margins.
//          paper.setImageableArea(10, 0, 524, 841);
//          pf.setPaper(paper);
          pj.setPrintable(PanelPrintNetSaleReport.this);
          pj.printDialog();
          try{
            pj.print();
          }catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
          }
        }
        });

        frame.setVisible(true);
     }


//get data
  ResultSet getData(Statement stmt){
    ResultSet rs = null;
    try{
      stmt = DAL.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs = DAL.executeScrollQueries("select d.item_id , item_desc,unit_qty,amount " +
                                    " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i " +
                                    "where d.ITEM_ID = i.ITEM_ID and " +
                                    " trunc(trans_date) = to_date('12/08/2005','DD/MM/YYYY') " +
                                    " and trans_flag = 0 ",stmt);
//                                    "group by d.item_id, item_desc ");
    }catch(Exception e){
      ExceptionHandle eh = new ExceptionHandle();
      eh.ouputError(e);
    }
    return rs;
  }



     public int print(Graphics g, PageFormat pageFormat,
        int pageIndex) throws PrinterException {
             Graphics2D  g2 = (Graphics2D) g;
             g2.setColor(Color.black);
             int fontHeight=g2.getFontMetrics().getHeight();
             int fontDesent=g2.getFontMetrics().getDescent();
             PageFormat pf = new PageFormat();
             Paper paper = new Paper();
              Font  f = new Font(lang.getString("FontName_Label"), 1,16);
             // Set the margins.
//             paper.setImageableArea(0, 0, 214, 841);
//             pf.setPaper(paper);
         ////
//         printJob.validatePage(pf);
//                 printJob.setPrintable(PanPrint,pf);

             //leave room for page number
             double pageHeight = pageFormat.getImageableHeight()-fontHeight;
//             System.out.println("pageheight: "+pageHeight);
             double pageWidth = pageFormat.getImageableWidth();
             double tableWidth = (double)table.getColumnModel().getTotalColumnWidth();
             double scale = 1;
             if (tableWidth >= pageWidth) {
                scale =  pageWidth / tableWidth;
              }

             double headerHeightOnPage=table.getTableHeader().getHeight()*scale;
//             System.out.println("headerHeight on Phage:"+headerHeightOnPage);
             double tableWidthOnPage=tableWidth*scale;
             double oneRowHeight=(table.getRowHeight()+table.getRowMargin())*scale;
             int numRowsOnAPage=(int)((pageHeight-headerHeightOnPage)/oneRowHeight)-4;
//             System.out.println("numRowsOnAPag"+numRowsOnAPage);
             double pageHeightForTable=oneRowHeight*numRowsOnAPage;
             int totalNumPages=(int)Math.ceil(((double)table.getRowCount())/numRowsOnAPage);
             if(pageIndex>=totalNumPages) {
                      return NO_SUCH_PAGE;
             }
             g2.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
//             System.out.println("outs:"+pageFormat.getImageableX()+"OUT"+pageFormat.getImageableY());
//bottom center
             g2.drawString("Page: "+(pageIndex+1)+"of"+totalNumPages,(int)pageWidth/2-35, (int)(pageHeight+fontHeight-fontDesent));
//             System.out.println("toa do x :"+((int)pageWidth/2-35)+"toa DO Y: "+(int)(pageHeight+fontHeight-fontDesent));
             g2.translate(0f,headerHeightOnPage);
//             g2.translate(0f,-pageIndex*pageHeightForTable);
             ///////////////////////////
             g2.translate(0f,90);
//             g2.translate(0f,headerHeightOnPage);
//     	g2.translate(0f,-pageIndex*pageHeightForTable);
//              System.out.println("of : "+ 0f+"he:"+headerHeightOnPage);
//             System.out.println("page Height For:"+ -pageIndex*pageHeightForTable);
             //If this piece of the table is smaller
             //than the size available,
             //clip to the appropriate bounds.
             if (pageIndex + 1 == totalNumPages) {
               int lastRowPrinted = numRowsOnAPage * pageIndex;
               int numRowsLeft = table.getRowCount() - lastRowPrinted;
//               System.out.println("getRowCount"+table.getRowCount());
//                g2.translate(0f,0f);
               g2.setClip(0,
                          0,

                          (int) Math.ceil(tableWidthOnPage),
                          645);
               // doi grid len sau header

             }
             //else clip to the entire area available.
             else{
               g2.setClip(0, (int) (pageHeightForTable * pageIndex),
                          (int) Math.ceil(tableWidthOnPage),
                          (int) Math.ceil(pageHeightForTable));
//               System.out.println("else work"+(int) Math.ceil(pageHeightForTable));
             }
//             g2.translate(0,100);
             g2.scale(scale,scale);
//             System.out.println("bat dau in Table: "+scale);
             table.paint(g2);
             g2.scale(1/scale,1/scale);
//             System.out.println("scale duoc set lai sau khi print table:"+1/scale);
             g2.translate(0f,0f); //translate to next page
             g2.translate(0f, -headerHeightOnPage);
             g2.setClip(0, 0,(int) Math.ceil(tableWidthOnPage),(int)Math.ceil(headerHeightOnPage));
//             System.out.println("Clip 2"+g2.getClip());
//             System.out.println("clip for table header:"+(int) Math.ceil(tableWidthOnPage)+"and:"+(int)Math.ceil(headerHeightOnPage));
             g2.scale(scale,scale);
//             System.out.println("header at : "+scale);
             table.getTableHeader().paint(g2);
             g2.translate(0, -130);
//             System.out.println("abc:" + headerHeightOnPage);
             g2.setClip(0, 0, 500, 100);
             g2.drawString("STOREROOM#",0,10);
             g2.drawString("ADDRESS#",0,30);
             g2.drawString("DATE:",0,80);
             g2.drawString("CUST NAME:",230,80);
             g2.drawString("CUST ID:",450,80);
             g2.drawString("TIME:",0,100);
             g2.drawString("CONTACT:",230,100);
             g2.drawString("DO:",450,100);
             g2.setFont(f);
             g2.drawString("DELIVERY ORDER",300,50);
             //paint header at top
             return Printable.PAGE_EXISTS;
   }

   public static void main(String[] args) {
        new PanelPrintNetSaleReport();
   }
}
