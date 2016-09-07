package pos;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
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
import btm.attr.*;

public class PanelPrintPage implements Printable{
  ////////////////////////////////////////////
  Vector vc = new Vector();
  Vector vData = new Vector();
//  JTable jTable1 ;
//  DefaultTableModel dm1 = new DefaultTableModel();

  FrameMain frmMain;
  Transaction trans;
  Item item;
  DataAccessLayer DAL;
  Utils ut = new Utils();
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
//  DefaultTableModel dm = new DefaultTableModel(){
    public Class getColumnClass(int col){
      switch (col){
        case 0: return Long.class;
        case 4: return Double.class;
        case 6: return Double.class;
        case 7: return Double.class;
        case 8: return Double.class;
        default: return Object.class;
      }
    }
  };
  BJTable table ;
//ResultSet Data =  frmMain.pnlNetSalesByItem.getData();
  ////////////////////////////////////////
  JFrame frame;
  Font  f = new Font("AduHuas", 1,16);
  Font  f1 = new Font("AduHuas", 0,14);
  Font  f2 = new Font("AduHuas", 0,12);
  public PanelPrintPage(FrameMain frmMain) {
    frame = new JFrame("Sales Report");
    try {

         this.frmMain = frmMain;
         DAL = frmMain.getDAL();
//      DAL = new DataAccessLayer();
//      DAL.getOracleConnect();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    //////////////////// aaaa
//    Object[] columnNames = new Object[]{
//        "Art #","Art Name","Colour","Qty","Unit","Price","Discount","Amount"
//    };
    dm.addColumn("No");
    dm.addColumn("Art #");
    dm.addColumn("Art Name");
    dm.addColumn("Colour");
    dm.addColumn("Qty");
    dm.addColumn("Unit");
    dm.addColumn("Price");
    dm.addColumn("Dis %");
    dm.addColumn("Amount");

//    dm.addColumn(columnNames);
//
    vc = frmMain.pnlPayment.getSalesItem();
    vData.clear();
//System.out.println("VC " + vc.size());
    Item item;
    dm.resetIndexes();
//     long qty =0;
    double quan;
    double price=0.0;
    double mark=0.0;
    double percentdis=0.0;
    for (int i = 0; i < vc.size(); i++) {
          item = (Item) vc.elementAt(i);

        price = item.getunitPrice();
//        System.out.println("price for :"+price);
        quan  = item.getQuantity();
//        System.out.println("quantiy for"+quan);
        mark = item.getMarkdownAmount();
//        System.out.println("mark for:"+mark);
        if (mark>0){
           percentdis = (mark * 100) / (price*quan);
//           System.out.println("percents new:"+percentdis+"%");
        }else{
           percentdis = 0;
        }

        Object[] rowData;
        if (frmMain.pnlNewSale.flagReturn == Constant.RETURN_ITEM) {
          rowData = new Object[] {
              new Long(i + 1), item.getId(), item.getDescription(),
              item.getattribute1(),
              new Double(item.getQuantity()), "",ut.formatNumbervn(""+ new Double(item.getunitPrice())),
              ut.formatNumbervn(""+new Double(item.getMarkdownAmount())),
              ut.formatNumbervn(""+new Double(item.getAmountDue()))
          };
// ut.formatNumber("" + item.getunitPrice())
        }
        else {
              rowData = new Object[] {
              new Long(i + 1), item.getId(), item.getDescription(),
              item.getattribute1(),
              new Double(item.getQuantity()), "",ut.formatNumbervn(""+ new Double(item.getunitPrice())),
              ut.formatNumbervn(""+percentdis),
              ut.formatNumbervn(""+new Double(item.getAmountDue()))
          };

        }

        dm.addRow(rowData);
    }
    dm.addRow(new Object[]{"","","","Quantity:","","","","Total:",""});
//    JTable jTable1 ;
//    System.out.println("qty:"+qty);

//jTable1 = new JTable(dm1);

    ///////////////////////////aaaaaaaaaaa
    table = new BJTable( dm ){
      public void tableChanged(TableModelEvent e){
        super.tableChanged(e);
        repaint();
      }
    };
    table.setColumnWidth(0,25);
    table.setColumnWidth(1,95);
    table.setColumnWidth(2,160);
    table.setColumnWidth(3,80);
    table.setColumnWidth(4,30);
    table.setColumnWidth(5,40);
//    table.setColor(null,null);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    table.setBorder(BorderFactory.createLineBorder(Color.black));
    table.setRowHeight(28);
//    table.setHeader(f1);
//    table.setBackground(Color.white);
    JScrollPane scrollpane = new JScrollPane(table);
//    table.setColumnWidth(0,100);
//    table.setColumnWidth(1,400);
    scrollpane.setPreferredSize(new Dimension(700, 80));
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(BorderLayout.CENTER,scrollpane);
    frame.pack();
    JButton printButton= new JButton();

//    printButton.setText("PRINT");

//    frame.getContentPane().add(
//        BorderLayout.SOUTH,printButton);

    // for faster printing turn double buffering off

    RepaintManager.currentManager(
        frame).setDoubleBufferingEnabled(false);
    reCalculate(table);
  }
  private void reCalculate(BJTable ml) {
                  double total = 0.0;
                  double sub_total = 0.0;
                  int quantity =0;
//                  System.out.println(ml.getRowCount());
                  int i=0;
                  for ( i=0;i<ml.getRowCount()-1;i++) {
//                    System.out.println(i);
//                    System.out.println("Tuan Anh1: " + ml.getValueAt(i,8));
//                    System.out.println("quantiy:" + ml.getValueAt(i,4));
                    quantity += ut.convertToDouble(ml.getValueAt(i,4).toString());
//                    total += ut.convertToDouble(ml.getValueAt(i,8).toString());
//                    System.out.println("quantitye:"+quantity);
                    total += ut.convertToDouble1(ml.getValueAt(i,8).toString());

            }
//            System.out.println("Tuan Anh: " + total);
//            ml.setValueAt(ut.formatNumber(""+total),i,8);
            ml.setValueAt(ut.formatNumbervn(""+quantity),i,4);
            ml.setValueAt(ut.formatNumbervn(""+total),i,8);
        //                  ml.setValueAt(new Double(sub_total),total_row + 1,total_column2);

    //                  ml.setValueAt(new Double(total),total_row + 2, total_column2);
  }
//    printButton.addActionListener( new ActionListener(){
//      public void actionPerformed(ActionEvent evt) {
//        PrinterJob pj=PrinterJob.getPrinterJob();
//        PageFormat pf = new PageFormat();
//        Paper paper = new Paper();
//        // Set the margins.
//          paper.setImageableArea(10, 0, 524, 841);
//          pf.setPaper(paper);
//        pj.setPrintable(PanelPrintPage.this);
//        pj.printDialog();
//        try{
//          pj.print();
//        }catch (Exception PrintException) {}
//      }
//    });

//    frame.setVisible(true);


//get data
//  ResultSet getData(){
//    ResultSet rs = null;
//    try{
//      rs = DAL.executeScrollQueries("select d.item_id , item_desc,unit_qty,amount " +
//                                    " from BTM_POS_DETAIL_TRANS d, BTM_POS_ITEM_PRICE i " +
//                                    "where d.ITEM_ID = i.ITEM_ID and " +
//                                    " trunc(trans_date) = to_date('27/08/2005','DD/MM/YYYY') " +
//                                    " and trans_flag = 0 ");
//                                    "group by d.item_id, item_desc ");
//    }catch(Exception e){
//      ExceptionHandle eh = new ExceptionHandle();
//      eh.ouputError(e);
//    }
//    return rs;
//  }


  public int print(Graphics g, PageFormat pageFormat,
                   int pageIndex) throws PrinterException {
    Graphics2D  g2 = (Graphics2D) g;
    g2.setColor(Color.black);
    int fontHeight=g2.getFontMetrics().getHeight();
    int fontDesent=g2.getFontMetrics().getDescent();
    PageFormat pf = new PageFormat();
    Paper paper = new Paper();
    // Set the margins.
    paper.setImageableArea(10, 5, 500, 688);
    pf.setPaper(paper);
//     Utils ut = new Utils();
//     DAL= frmMain.getDAL();
 String storeID = DAL.getStoreID();
 String store_name = DAL.getStoreName();
 long cust_id = frmMain.pnlNewSale.custID;
// System.out.println("cust ID: "+ frmMain.pnlNewSale.custID);
 String [] i =  frmMain.lblName.getText().split(":");
 String cust_name = i[1];
// System.out.println("cust_name: "+ cust_name);

    ////
//         printJob.validatePage(pf);
//                 printJob.setPrintable(PanPrint,pf);

    //leave room for page number
    double pageHeight = pf.getImageableHeight()-fontHeight;
//    System.out.println("pageheight: "+pageHeight);
    double pageWidth = pf.getImageableWidth();
    double tableWidth = (double)table.getColumnModel().getTotalColumnWidth();
    double scale = 1;
    if (tableWidth >= pageWidth) {
      scale =  pageWidth / tableWidth;
    }

    double headerHeightOnPage=table.getTableHeader().getHeight()*scale;
//    System.out.println("headerHeight on Phage:"+headerHeightOnPage);
    double tableWidthOnPage=tableWidth*scale+50;
    double oneRowHeight=(table.getRowHeight()+table.getRowMargin())*scale;
    int numRowsOnAPage=(int)((pageHeight-headerHeightOnPage)/oneRowHeight)-8;
//    System.out.println("numRowsOnAPag"+numRowsOnAPage);
    double pageHeightForTable=oneRowHeight*numRowsOnAPage+4;
    int totalNumPages=(int)Math.ceil(((double)table.getRowCount())/numRowsOnAPage);
    if(pageIndex>=totalNumPages) {
      return NO_SUCH_PAGE;
    }
    g2.translate(pf.getImageableX(),pf.getImageableY());
    g2.drawString("STOREMEN 1:",65,620);
    g2.drawString("STOREMEN 2:",200,620);
    g2.drawString("CUSTOMER:",360,620);
    g2.drawLine(0,700,430,700);
//    g2.drawString("Page: "+(pageIndex+1)+"of"+totalNumPages,(int)pageWidth/2-35, (int)(pageHeight+fontHeight-fontDesent)+30);
//    System.out.println("toa do x :"+((int)pageWidth-35)+"toa DO Y: "+(int)(pageHeight+fontHeight-fontDesent));
    g2.translate(0f,headerHeightOnPage);
    g2.translate(0f,-pageIndex*pageHeightForTable);
    ///////////////////////////
    g2.translate(0f,90f);//

    //If this piece of the table is smaller
    //than the size available,
    //clip to the appropriate bounds.
    if (pageIndex + 1 == totalNumPages) {
      int lastRowPrinted = numRowsOnAPage * pageIndex;
      int numRowsLeft = table.getRowCount() - lastRowPrinted;
      g2.setClip(0,
                 (int) (pageHeightForTable * pageIndex),
                 (int) Math.ceil(tableWidthOnPage),
                 (int) Math.ceil(oneRowHeight *
                                 numRowsLeft));

      // doi grid len sau header
//      System.out.println("*************** if work*****************"+(int) Math.ceil(pageHeightForTable));
    }
    //else clip to the entire area available.
    else{
      g2.setClip(0, (int) (pageHeightForTable * pageIndex),
                 (int) Math.ceil(tableWidthOnPage),
                 (int) Math.ceil(pageHeightForTable));
//      System.out.println("*************** else work********************"+(int) Math.ceil(pageHeightForTable));
    }
//             g2.translate(0,100);
    g2.scale(scale,scale);
//    System.out.println("bat dau in Table: "+scale);

    table.paint(g2);
    g2.scale(1/scale,1/scale);
    g2.translate(0f,pageIndex*pageHeightForTable); //translate to next page/
    g2.translate(0f, -headerHeightOnPage);
    g2.setClip(0, 0,(int) Math.ceil(tableWidthOnPage),(int)Math.ceil(headerHeightOnPage));

    g2.scale(scale,scale);
//    System.out.println("header at : "+scale);
//                table.getTableHeader().setBackground(Color.white);
    table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black));
    table.getTableHeader().paint(g2);
    g2.translate(0, -130);
//    System.out.println("abc:" + headerHeightOnPage);
    g2.setClip(0, 0, 640, 100);
    g2.setFont(f2);
    g2.drawString("STOREROOM#"+storeID,0,10);
    g2.drawString("Page: "+(pageIndex+1)+"\t of \t"+totalNumPages,560, 10);
    g2.drawString("ADDRESS#"+store_name,0,30);
    g2.drawString("DATE:"+ut.getSystemDate().substring(0,10),0,80);
    g2.drawString("CUST NAME:"+cust_name,230,80);
    g2.drawString("CUST ID:"+cust_id,450,80);
    g2.drawString("TIME:"+ut.getSystemDateTime().substring(11,19),0,100);
    g2.drawString("CONTACT:",230,100);
    g2.drawString("DO:",450,100);
    g2.setFont(f);
    g2.drawString("DELIVERY ORDER",300,50);
    //paint header at top
    return Printable.PAGE_EXISTS;
  }

//  public static void main(String[] args) {
//    new PanelPrintPage();
//  }
}
