

//============================================================================//
//============================ NOT USE NOW ===================================//
//============================================================================//





























package sim;

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

public class PanelPrintGRN implements Printable{
  ////////////////////////////////////////////
  Vector vc = new Vector();
  FrameMainSim frmMain;
  DataAccessLayer DAL;
  Utils ut = new Utils();
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  SortableTableModel dm = new SortableTableModel(){
    public Class getColumnClass(int col){
      switch (col){
        case 0: return Long.class;
        case 4: return Long.class;
        case 6: return Double.class;
        case 7: return Double.class;
        case 8: return Double.class;
        default: return Object.class;
      }
    }
  };
  BJTable table = new BJTable(dm){
    public void tableChanged(TableModelEvent e) {
      super.tableChanged(e);
      repaint();
    }
  };
  JFrame frame;
  Font  f = new Font("AduHuas", 1,18);
  Font  f1 = new Font("AduHuas", 0,14);
  Font  f2 = new Font("AduHuas", 0,12);
  public PanelPrintGRN(FrameMainSim frmMain) {
    frame = new JFrame("Sales Report");
    try {

         this.frmMain = frmMain;
         DAL = frmMain.getDAL();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    dm.addColumn("No");
    dm.addColumn("Art #");
    dm.addColumn("Art Name");
    dm.addColumn("Qty");
    dm.addColumn("Price");
    dm.addColumn("Color");
    dm.addColumn("Size");

//System.out.println("VC " + vc.size());

    dm.resetIndexes();
    int quan;
    double Unit_Cost=0.0;
    double mark=0.0;
    double percentdis=0.0;
    String attr1 = "";
    String attr2 = "";
    String ArtNo="";
    String ItemDesc="";
    String ItemId="";
    int quantity=0;
    ResultSet rs = null;
    for (int i = 0; i < frmMain.pnlGoodsReceipt.table.getRowCount(); i++) {
//          item = (Item) vc.elementAt(i);
        ItemId = frmMain.pnlGoodsReceipt.table.getValueAt(i,0).toString();
      ArtNo = frmMain.pnlGoodsReceipt.table.getValueAt(i,2).toString();
      ItemDesc = frmMain.pnlGoodsReceipt.table.getValueAt(i,1).toString();
      quantity = Integer.parseInt(frmMain.pnlGoodsReceipt.table.getValueAt(i,4).toString());
      rs = null;
      String sql = "select item_id, Unit_Cost, attr1_name, attr2_name from btm_im_price_hist " +
          " where item_id ='" + ItemId + "' and price_end_date = to_date('7/7/7777','dd/mm/yyyy')";
//      System.out.println(sql);
      try{
        rs = DAL.executeQueries(sql);
        if (rs.next()){
          Unit_Cost = rs.getDouble("Unit_Cost");
          attr1 = rs.getString("attr1_name");
          attr2 = rs.getString("attr2_name");
        }
        rs.getStatement().close();
      }catch(Exception e){
      }
//    dm.addRow(new Object[]{new Integer(i+1),ItemID,ItemDesc,new Integer(quantity),
//                ut.formatNumbervn(new Double(price).toString()),attr1,attr2});
      dm.addRow(new Object[]{new Integer(i+1),ArtNo,ItemDesc,new Integer(quantity),
                  (new Double(Unit_Cost).toString()),attr1,attr2});

    }
    dm.addRow(new Object[]{"","","Total Qty: ","","","",""});
    table.setColumnWidth(0,25);
    table.setColumnWidth(1,95);
    table.setColumnWidth(2,160);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    table.setBorder(BorderFactory.createLineBorder(Color.black));
    table.setRowHeight(28);
    JScrollPane scrollpane = new JScrollPane(table);
    scrollpane.setPreferredSize(new Dimension(700, 80));
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(BorderLayout.CENTER,scrollpane);
    frame.pack();
    JButton printButton= new JButton();

    RepaintManager.currentManager(
        frame).setDoubleBufferingEnabled(false);
    reCalculate(table);
  }
  private void reCalculate(BJTable ml) {
    int quantity =0;
    int i=0;
    for ( i=0;i<ml.getRowCount()-1;i++) {
      quantity += ut.convertToDouble(ml.getValueAt(i,3).toString());
    }
    ml.setValueAt(ut.formatNumbervn(""+quantity),i,3);
   }

//    frame.setVisible(true);
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

//    String storeID = frmMain.pnlGoodsReceipt.cboStore.getSelectedData();
    String supplier = frmMain.pnlGoodsReceipt.cboSupplier.getSelectedItem().toString();
    String receiptID = frmMain.pnlGoodsReceipt.txtReceiptID.getText();
//    System.out.println("Store ID: "+storeID);
    String store_name = DAL.getStoreName();

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
    g2.drawString("CREATER:",65,620);
//    g2.drawString("STOREMEN 2:",200,620);
    g2.drawString("SUPPLIER:",380,620);
    g2.drawLine(0,700,450,700);
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
    g2.setClip(0, 0, 690, 100);
    g2.setFont(f2);
    g2.drawString("STOREROOM#"+store_name,0,10);
    g2.drawString("Page: "+(pageIndex+1)+"\t of \t"+totalNumPages,590, 10);
//    g2.drawString("ADDRESS#"+store_name,0,30);
    g2.drawString("DATE:"+ut.getSystemDate().substring(0,10),0,80);
    g2.drawString("TIME:"+ut.getSystemDateTime().substring(11,19),0,100);
    g2.drawString("SUPPLIER:"+supplier,230,80);
//    g2.drawString("ADDRESS:",230,100);
    g2.drawString("RECEIPT ID:"+ receiptID,490,80);
//    g2.drawString("To STORE:"+toStoreID ,460,100);
    g2.setFont(f);
    g2.drawString("GOOD RECEIPT NOTE",270,50);
    //paint header at top
    return Printable.PAGE_EXISTS;
  }

//  public static void main(String[] args) {
//    new PanelPrintPage();
//  }
}
