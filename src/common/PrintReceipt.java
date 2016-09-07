package common;

//Import component of iText
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
//Import component of PDF box
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.PrintPDF;
//Import component common
import java.io.*;
import java.awt.Color;
import java.lang.Math;
import java.util.Vector;
import java.awt.print.*;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import com.qoppa.pdf.IPasswordHandler;
import com.qoppa.pdf.PDFException;
import com.qoppa.pdf.PrintSettings;
import com.qoppa.pdfPrint.PDFPrint;
import java.awt.Frame;
import sim.FrameMainSim;

public class PrintReceipt {
    FrameMainSim mainFrame = null;
    Document document;
    //Detail of receipt has type is normal
    private Rectangle PAGE_SIZE = PageSize.A4;
    private int MAX_SIZE_TABLE = 520;
    private int MARGIN_LEFT = 10;
    private int MARGIN_RIGHT = 70;
    private int MARGIN_TOP = 10;
    private int MARGIN_BOTTOM = 10;

    private String FILE_NAME = "";

    private String[][] HEADER;
    private String BARCODE = "";
    private String[] TITLE;
    private String[][] INFO;

    private String PAGE_STRING1 = "Page";
    private String PAGE_STRING2 = "of";

    private String LEFT_SIGN = "CREATOR";
    private String RIGHT_SIGN = "RECEIVER";
    //font size 8 =====  TOTAL_ROW_IN_PAGE = 66
    //font size 9 =====  TOTAL_ROW_IN_PAGE = 61
    //font size 10 ===== TOTAL_ROW_IN_PAGE = 58
    private int TOTAL_ROW_IN_PAGE = 58; //Max of row in a page when use font 10 is 58
    private int TOTAL_ROW_PO_IN_PAGE = 52; //Max of row in a page when use font 10 is 58, use for PO
    private int TOTAL_ROW_PRINT_TOTAL_VAT = 6; //row for print Total and VAT after print table, use for PO

    //font size 10 ===== TOTAL_ROW_IN_PAGE = 8
    private int TOTAL_ROW_FOR_SIGNAL = 9;

    private Font FONT_HEADER = new Font(Font.HELVETICA, 10);
    private Font FONT_TITLE = new Font(Font.
                                       HELVETICA, 12, Font.BOLD);
    private Font FONT_INFO = new Font(Font.HELVETICA, 10);

    private Font FONT_IN_TABLE = new Font(Font.HELVETICA, 10);
    private Font FONT_IN_TABLE_TITLE = new Font(Font.HELVETICA, 10, Font.BOLD);
    private Font FONT_IN_TABLE_TOTAL = new Font(Font.HELVETICA, 10, Font.BOLD);
    private Font FONT_IN_TABLE_TOTAL_GROUP_ART = new Font(Font.HELVETICA, 9,
            Font.BOLD);

    private Color COLOR_BORDER_TABLE_HEADER = new Color(255, 255, 255);
    private Color COLOR_TITLE = new Color(0, 0, 0);
    private Color COLOR_BORDER_TABLE_UNVIEW = new Color(255, 255, 255);
    private Color COLOR_BORDER_TABLE_INFO = new Color(255, 255, 255);
    private Color COLOR_TABLE_ITEM = new Color(0, 0, 0);
    private Color COLOR_BORDER_TABLE = new Color(0xC0, 0xC0, 0xC0);

    private Color COLOR_BACKGROUND_TABLE_TITLE = new Color(0xC0, 0xC0, 0xC0);
    private Color COLOR_BACKGROUND_TABLE = new Color(255, 255, 255);
    private Color COLOR_BACKGROUND_TOTAL = new Color(255, 255, 255);

    //Info of table item
    private int[] TABLE_SKEW;
    private String[] TABLE_TITLE;
    private String[][] TABLE_ITEM;
    private String[] TABLE_ALIGHT;
    private String[] FORMAT_COLUMN;
    //Type of Receipt
    // 1 A4 printing, for franchise
    // 2 TXN printing
    private int TYPE_RECEIPT = 1;

    private boolean IS_VIEW_TOTAL = true;
    private boolean IS_VIEW_TOTAL_IN_PAGE = false;
    private boolean IS_DELETE_FILE_AFTER_PRINT = false;
    private boolean IS_GROUP_BY_ART_NUMBER = false;
    private int GROUP_AT = 1;
    private String TOTAL_GROUP_NAME = "Total by UPC";
    private int[] ROW_TOTAL;
    private String TOTAL_NAME = "Grand Total";

    //Detail of receipt has type TXN
    private String[] HEADER_RECEIPT;
    private String RECEIPT_ID;
    private Vector ITEM_LIST;

    private String CUST_INFO;

    private int MAX_SIZE_TABLE_TNX = 200; //180 245
    private Color COLOR_ITEM_TNX = new Color(255, 255, 255);
    private Font FONT_ITEM_TNX = new Font(Font.HELVETICA, 9);
    private Font FONT_ITEM_BOLD_TNX = new Font(Font.HELVETICA, 10, Font.BOLD);
    private String[] ALIGN_ITEM_TNX;
    private Font FONT_HEADER_TNX = new Font(Font.HELVETICA, 10, Font.BOLD);

    private String TYPE_CURRENCY = "VND";

    private String[][] ITEM_INFO_TNX;
    private String[] TOTAL_TNX;
    private String[] TABLE_ALIGHT_TNX;
    private int[] TABLE_TNX_SKEW;
    private String ID_TNX = "";
    private boolean IS_PRINT_BARCODE = false;
    private String TITLE_TNX = "";
    private boolean IS_MAGIN = true;

    private Rectangle PAGE_SIZE_TNX = new Rectangle(245, 850); //PageSize.A4    720;
    private int MARGIN_LEFT_TNX = 5;
    private int MARGIN_RIGHT_TNX = 70;
    private int MARGIN_TOP_TNX = 0;
    private int MARGIN_BOTTOM_TNX = 10;

    private boolean SHOW_DIALOG = true;
    private String TURN_OFF_ACROBAT = "false";
    private String ACROBAT_PATH = "Acrobat.exe";
    private int DELAY_BEFORE_PRINT = 500;
    private int DELAY_AFTER_PRINT = 1000;

    //Order Form
    private String ORDER_FORM_LEFT[];
    private String ORDER_FORM_RIGHT[];
    private String ORDER_FORM_TITLE[];
    private String ORDER_FORM_INFO_TOP[];
    private String ORDER_FORM_INFO_BOTTOM[];
    private Color ORDER_FORM_BORDER_TABLE = new Color(255, 255, 255);
    private Font FONT_ORDER_FORM_LEFT = new Font(Font.HELVETICA, 10, Font.BOLD);
    private Font FONT_ORDER_FORM_TITLE = new Font(Font.
                                                  HELVETICA, 13, Font.BOLD);
    private int[] ORDER_FORM_INFO_SKEW;
    private boolean PRINT_ORDER_FORM = false;
    private boolean SHOW_VAT = false;
    private String VAT_LABEL_FIRST = "";
    private String VAT_LABEL_SECOND = "";
    private String VAT_TEXT_FIRST = "";
    private String VAT_TEXT_SECOND = "";

    private String AMOUNT_TENDERED = "0";
    private String CHANGED_DUE = "0";
    private boolean IS_SHOW_AMOUNT = false;
    public PrintReceipt() {
        document = new Document();
    }

    //Print for PO
    //Auto calculate total row per page, no use TOTAL_ROW_IN_PAGE = 58 above
    public void createOrderForm() {
//    int i, j, l;
        Utils ut = new Utils();
        String TABLE_ITEM_TEMP[][];
        if (FILE_NAME.equals("")) {
            return;
        }
        try {
            document.setPageSize(PAGE_SIZE);
            //Precess file name
            String sFileNameTemp = FILE_NAME.split(".pdf")[0] + "_temp.pdf";
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(
                            sFileNameTemp));
            document.open();

            //printing barcode for PO
            if (barcodePrint()) {
                PdfPTable tmpTable = new PdfPTable(1);
                PdfPCell tmpCell = new PdfPCell();
                PdfContentByte cb = writer.getDirectContent();
                BarcodeEAN codeEAN = new BarcodeEAN();
                codeEAN.setCodeType(codeEAN.EAN13);
                codeEAN.setCode(BARCODE);
                codeEAN.setFont(null);

                Image imageEAN = codeEAN.createImageWithBarcode(cb, null, null);
                tmpCell = new PdfPCell(new Phrase(new Chunk(imageEAN, 0, 0)));
                tmpCell.setBorder(0);
                tmpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tmpTable.addCell(tmpCell);
                document.add(tmpTable);

//        System.out.println("===============================POPO==========================");
            }

            //Insert to Table Temp
            int k, m, n;
            TABLE_ITEM_TEMP = new String[TABLE_ITEM.length][TABLE_ITEM[0].
                              length];
            //Insert Table of Item to Table of Item Temp
            for (k = 0; k < TABLE_ITEM.length; k++) {
                for (m = 0; m < TABLE_ITEM[k].length; m++) {
                    TABLE_ITEM_TEMP[k][m] = TABLE_ITEM[k][m];
                }
            }

            //Total records
            int iNum = TABLE_ITEM_TEMP.length;

            //row could display on first page
            int iTotalRowFirstPage = TOTAL_ROW_PO_IN_PAGE -
                                     TOTAL_ROW_FOR_SIGNAL -
                                     HEADER.length -
                                     Math.round((float) (ORDER_FORM_LEFT.length *
                    1.25)) + 5;

            //number of page for table: page1 + others
            int iPage = 1;
            //use only 1 page if :total recs + row for print Total fit to page
            if ((iNum + TOTAL_ROW_PRINT_TOTAL_VAT) <= iTotalRowFirstPage) {
                iPage = 1;
            } else {
                iPage = 1 + (iNum - iTotalRowFirstPage) / TOTAL_ROW_PO_IN_PAGE +
                        1;
            }
            int iTotalRowUnView = iTotalRowFirstPage * iPage - iNum;

            int currentPage = 0;
            int totalRowLastPage = 0;

            float[] fTableSkew = new float[TABLE_ITEM_TEMP[0].length];
            float[] fOrderFormInfoSkew = new float[ORDER_FORM_INFO_SKEW.length];
            int[] iTableAlight = new int[TABLE_ITEM_TEMP[0].length];

            //Table Skew
            for (int i = 0; i < TABLE_ITEM_TEMP[0].length; i++) {
                fTableSkew[i] = (float) TABLE_SKEW[i] / 100;
            }

            //Order Form Info Skew
            for (int i = 0; i < ORDER_FORM_INFO_SKEW.length; i++) {
                fOrderFormInfoSkew[i] = (float) ORDER_FORM_INFO_SKEW[i] / 100;
            }

            //Table Align
            for (int i = 0; i < TABLE_ITEM_TEMP[0].length; i++) {
                if (TABLE_ALIGHT[i].equalsIgnoreCase("LEFT")) {
                    iTableAlight[i] = Element.ALIGN_LEFT;
                } else if (TABLE_ALIGHT[i].equalsIgnoreCase("RIGHT")) {
                    iTableAlight[i] = Element.ALIGN_RIGHT;
                } else if (TABLE_ALIGHT[i].equalsIgnoreCase("CENTER")) {
                    iTableAlight[i] = Element.ALIGN_CENTER;
                }
            }

            //Total Temp
            long[] lTotalTemp;
            double[] dTotalTemp;
            if (IS_VIEW_TOTAL) {
                lTotalTemp = new long[ROW_TOTAL.length];
                for (int i = 0; i < ROW_TOTAL.length; i++) {
                    lTotalTemp[i] = 0;
                }

                dTotalTemp = new double[ROW_TOTAL.length];
                for (int i = 0; i < ROW_TOTAL.length; i++) {
                    dTotalTemp[i] = 0;
                }
            } else {
                lTotalTemp = new long[1];
                dTotalTemp = new double[1];
            }

            //-----------------------------------------Part 1 Add space-----------------------------------------//
            //Add space
            Paragraph pSpace = new Paragraph(" ",
                                             FontFactory.getFont(
                    FontFactory.
                    HELVETICA, 9, Font.BOLD,
                    new Color(0, 0, 0)));
            //if no barcode print, add blank
            if (!barcodePrint()) {
                pSpace.setAlignment(Element.ALIGN_CENTER);
                document.add(pSpace);
                pSpace.setAlignment(Element.ALIGN_CENTER);
                document.add(pSpace);
            }
            //-----------------------------------------Part 2 Add Title-----------------------------------------//
            //Add Title
            if (ORDER_FORM_LEFT.length > 0) {
                //Get max row of left column
                int iNumRowLeftColumn = ORDER_FORM_LEFT.length;
                int iNumRowRightColumn = ORDER_FORM_RIGHT.length;
                int iNumTitle = iNumRowLeftColumn - iNumRowRightColumn;
                int iPosRightColumn = 0;
                PdfPTable tableInfo = new PdfPTable(fOrderFormInfoSkew);
                PdfPCell cell = new PdfPCell();

                for (int i = 0; i < iNumTitle; i++) {
                    for (int j = 1; j <= 3; j++) {
                        if (j % 3 == 0) {
                            if (i == 0) {
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        ORDER_FORM_TITLE[i],
                                        FONT_ORDER_FORM_TITLE)));
                                cell.setBorder(0);
                                cell.setHorizontalAlignment(Element.
                                        ALIGN_CENTER);
                                tableInfo.addCell(cell);
                            } else if (i == 1) {
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        ORDER_FORM_TITLE[i],
                                        FONT_HEADER)));
                                cell.setBorder(0);
                                cell.setHorizontalAlignment(Element.
                                        ALIGN_CENTER);
                                tableInfo.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        "",
                                        FONT_HEADER)));
                                cell.setBorder(0);
                                cell.setHorizontalAlignment(Element.
                                        ALIGN_CENTER);
                                tableInfo.addCell(cell);
                            }
                        } else if (j % 2 == 0) {
                            cell = new PdfPCell(new Paragraph(new Chunk(
                                    "",
                                    FONT_HEADER)));
                            cell.setBorder(0);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tableInfo.addCell(cell);
                        } else {
                            if (i == 0) {
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        ORDER_FORM_LEFT[i],
                                        FONT_ORDER_FORM_LEFT)));
                                cell.setBorder(0);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                tableInfo.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        ORDER_FORM_LEFT[i],
                                        FONT_HEADER)));
                                cell.setBorder(0);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                tableInfo.addCell(cell);
                            }
                        }
                    }
                }
                for (int i = iNumTitle; i < iNumRowLeftColumn; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (j == 0) {
                            cell = new PdfPCell(new Paragraph(new Chunk(
                                    ORDER_FORM_LEFT[i],
                                    FONT_HEADER)));
                            cell.setBorder(0);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            tableInfo.addCell(cell);
                        } else if (j == 1) {
                            cell = new PdfPCell(new Paragraph(new Chunk(
                                    "",
                                    FONT_HEADER)));
                            cell.setBorder(0);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tableInfo.addCell(cell);
                        } else {
                            if (i == iNumTitle) {
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        ORDER_FORM_RIGHT[iPosRightColumn],
                                        FONT_ORDER_FORM_LEFT)));
                                cell.setBorder(0);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                tableInfo.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        ORDER_FORM_RIGHT[iPosRightColumn],
                                        FONT_HEADER)));
                                cell.setBorder(0);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                tableInfo.addCell(cell);
                            }
                            iPosRightColumn++;
                        }
                    }
                }
                tableInfo.setTotalWidth(MAX_SIZE_TABLE);
                tableInfo.setLockedWidth(true);
                document.add(tableInfo);

                //-----------------------------------------Part 3 Add space-----------------------------------------//
                //Add space
                pSpace = new Paragraph(" ",
                                       FontFactory.getFont(
                                               FontFactory.
                                               HELVETICA, 9, Font.BOLD,
                                               new Color(0, 0, 0)));
                pSpace.setAlignment(Element.ALIGN_CENTER);
                document.add(pSpace);
//        pSpace.setAlignment(Element.ALIGN_CENTER);
//        document.add(pSpace);
                //-----------------------------------------Part 4 Add Info Top-----------------------------------------//
                //Add Info Top
                for (int i = 0; i < ORDER_FORM_INFO_TOP.length; i++) {
                    pSpace = new Paragraph(ORDER_FORM_INFO_TOP[i],
                                           FontFactory.getFont(
                            FontFactory.
                            HELVETICA, 8, Font.NORMAL,
                            new Color(0, 0, 0)));
                    pSpace.setAlignment(Element.ALIGN_LEFT);
                    document.add(pSpace);
                }
                pSpace = new Paragraph(" ",
                                       FontFactory.getFont(
                                               FontFactory.
                                               HELVETICA, 2, Font.BOLD,
                                               new Color(0, 0, 0)));
                pSpace.setAlignment(Element.ALIGN_CENTER);
                document.add(pSpace);
            }
            //-----------------------------------------Part 5 Add Table Item-----------------------------------------//
            for (currentPage = 1; currentPage <= iPage; currentPage++) {

                //-----------------------------------------Part 1-----------------------------------------//
                //Add New Page
                if (currentPage != 1) {
                    document.newPage();
                }

                int iBeforeFor = 0;
                //calculate begin row of page
                if (currentPage == 1) {
                    iBeforeFor = 0;
                } else {
                    iBeforeFor = iTotalRowFirstPage +
                                 TOTAL_ROW_PO_IN_PAGE * (currentPage - 2);
                }
                int iEndFor = 0;

                //calculate end row of page
                if (currentPage == 1) {
                    iEndFor = iTotalRowFirstPage;
                } else {
                    iEndFor = iTotalRowFirstPage +
                              TOTAL_ROW_PO_IN_PAGE * (currentPage - 1);
                }
                if (iEndFor > iNum) {
                    iEndFor = iNum;
                }

                totalRowLastPage = iEndFor - iBeforeFor;
//      System.out.println("===="+totalRowLastPage);
                //Add Table Item
                PdfPTable tableItem = new PdfPTable(fTableSkew);
                PdfPCell cellItem;

                //table 100 rows, 7 columns => i=0..100; j=0..7; l=0..1 ((100 i) x (7 j) x (1 L))

//      for (int i = 0; i < TABLE_ITEM_TEMP.length; i++) {
                for (int i = iBeforeFor; i < iEndFor; i++) {
                    //Add Table Title
                    if (i == 0) {
                        for (int j = 0; j < TABLE_ITEM_TEMP[0].length; j++) {
                            cellItem = new PdfPCell(new Paragraph(new Chunk(
                                    TABLE_TITLE[j],
                                    FONT_IN_TABLE_TITLE)));
                            cellItem.setBorderColor(ORDER_FORM_BORDER_TABLE);
                            cellItem.setHorizontalAlignment(
                                    Element.ALIGN_CENTER);
                            cellItem.setBackgroundColor(
                                    COLOR_BACKGROUND_TABLE_TITLE);
                            cellItem.setBorder(1);
                            tableItem.addCell(cellItem);

                        }
                    }

                    //Add data to table
                    for (int j = 0; j < TABLE_ITEM_TEMP[i].length; j++) {
                        //Total Data
                        if (IS_VIEW_TOTAL) {
                            for (int l = 0; l < ROW_TOTAL.length; l++) {
                                if (j == ROW_TOTAL[l]) {
                                    String strTemp = String.valueOf(
                                            TABLE_ITEM_TEMP[i][j]).
                                            toString();
                                    if (FORMAT_COLUMN[j].equalsIgnoreCase(
                                            "money") ||
                                        FORMAT_COLUMN[j].equalsIgnoreCase(
                                            "double")) {
                                        dTotalTemp[l] +=
                                                Double.parseDouble(strTemp);
                                    } else {
                                        lTotalTemp[l] += Long.parseLong(strTemp);
                                    }
                                }

                            }
                        }
                        if (FORMAT_COLUMN[j].equalsIgnoreCase("double")) {
                            cellItem = new PdfPCell(new Paragraph(new Chunk(
                                    ut.formatNumbervn(String.valueOf(
                                    TABLE_ITEM_TEMP[i][j]).
                                    toString()),
                                    FONT_IN_TABLE)));
                        } else if (FORMAT_COLUMN[j].equalsIgnoreCase("money")) {
                            cellItem = new PdfPCell(new Paragraph(new Chunk(
                                    ut.formatNumber(String.valueOf(
                                    TABLE_ITEM_TEMP[i][j]).
                                    toString()),
                                    FONT_IN_TABLE)));
                        } else if (FORMAT_COLUMN[j].equalsIgnoreCase("int")) {
                            cellItem = new PdfPCell(new Paragraph(new Chunk(
                                    String.valueOf(Integer.parseInt(
                                    TABLE_ITEM_TEMP[i][j])).
                                    toString(),
                                    FONT_IN_TABLE)));
                        } else {
                            cellItem = new PdfPCell(new Paragraph(new Chunk(
                                    fixStringWithWidth(String.valueOf(
                                    TABLE_ITEM_TEMP[i][j]).
                                    toString(), FONT_IN_TABLE, TABLE_SKEW[j],
                                    MAX_SIZE_TABLE),
                                    FONT_IN_TABLE)));
                        }
                        cellItem.setBorderColor(ORDER_FORM_BORDER_TABLE);
                        cellItem.setHorizontalAlignment(
                                iTableAlight[j]);
                        cellItem.setBackgroundColor(
                                COLOR_BACKGROUND_TABLE);
                        cellItem.setGrayFill(0f);
                        tableItem.addCell(cellItem);
                    }
                }

                //last page, add Total and VAT
                if ((IS_VIEW_TOTAL && iPage == currentPage) ||
                    (IS_VIEW_TOTAL_IN_PAGE)) {
                    //number page >1, add new page for print Total if need
                    if (iNum > 1) {
                        //use last page if :total recs + row for print Total fit to page
                        if ((totalRowLastPage + TOTAL_ROW_PRINT_TOTAL_VAT) >
                            TOTAL_ROW_PO_IN_PAGE) {
                            //print item data in table for last page
                            tableItem.setTotalWidth(MAX_SIZE_TABLE);
                            tableItem.setLockedWidth(true);
                            document.add(tableItem);

                            //new page for print Total
                            document.newPage();
                            //Add Table Item
                            tableItem = new PdfPTable(fTableSkew);
                        }
                    }

                    //Add Total
                    //Find min of row total
                    int minrow = ROW_TOTAL[0];
                    for (int l = 0; l < ROW_TOTAL.length; l++) {
                        if (minrow > ROW_TOTAL[l]) {
                            minrow = ROW_TOTAL[l];
                        }
                    }

                    PdfPCell cell = new PdfPCell(new Paragraph(TOTAL_NAME,
                            FONT_IN_TABLE_TOTAL));
                    cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                    cell.setColspan(minrow);
                    cell.setBackgroundColor(COLOR_BACKGROUND_TOTAL);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableItem.getDefaultCell().setVerticalAlignment(Element.
                            ALIGN_MIDDLE);
                    tableItem.addCell(cell);

                    for (int l = minrow; l < TABLE_ITEM_TEMP[0].length; l++) {
                        if (checkCol(l, ROW_TOTAL)) {

                            if (FORMAT_COLUMN[l].equalsIgnoreCase("double")) {
                                cell = new PdfPCell(new Paragraph(new Chunk(ut.
                                        formatNumbervn((
                                                String.
                                                valueOf(dTotalTemp[getID(l,
                                        ROW_TOTAL)]).
                                                toString())),
                                        FONT_IN_TABLE_TOTAL)));

                            } else if (FORMAT_COLUMN[l].equalsIgnoreCase(
                                    "money")) {
                                cell = new PdfPCell(new Paragraph(new Chunk(ut.
                                        formatNumber((
                                                String.
                                                valueOf(lTotalTemp[getID(l,
                                        ROW_TOTAL)]).
                                                toString())),
                                        FONT_IN_TABLE_TOTAL)));

                            }

                            else if (FORMAT_COLUMN[l].equalsIgnoreCase("int")) {
                                String strTemp = String.
                                                 valueOf(dTotalTemp[getID(l,
                                        ROW_TOTAL)]).
                                                 toString();
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        strTemp,
                                        FONT_IN_TABLE_TOTAL)));
                            } else {
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        String.
                                        valueOf(lTotalTemp[getID(l, ROW_TOTAL)]).
                                        toString(),
                                        FONT_IN_TABLE_TOTAL)));
                            }
                            cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                            cell.setHorizontalAlignment(iTableAlight[l]);
                            tableItem.getDefaultCell().setVerticalAlignment(
                                    Element.
                                    ALIGN_MIDDLE);
                            tableItem.addCell(cell);
                        } else {
                            cell = new PdfPCell(new Paragraph(new Chunk(" ",
                                    FONT_IN_TABLE_TOTAL)));
                            cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                            tableItem.getDefaultCell().setVerticalAlignment(
                                    Element.
                                    ALIGN_MIDDLE);
                            tableItem.addCell(cell);
                        }
                    }
                    if (SHOW_VAT) {
                        //Show First VAT-----------------------------------------------------
                        minrow = ROW_TOTAL[0];
                        for (int l = 0; l < ROW_TOTAL.length; l++) {
                            if (minrow > ROW_TOTAL[l]) {
                                minrow = ROW_TOTAL[l];
                            }
                        }
                        cell = new PdfPCell(new Paragraph(VAT_LABEL_FIRST,
                                FONT_IN_TABLE_TOTAL));
                        cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                        cell.setColspan(minrow);
                        cell.setBackgroundColor(COLOR_BACKGROUND_TOTAL);
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        tableItem.getDefaultCell().setVerticalAlignment(Element.
                                ALIGN_MIDDLE);
                        tableItem.addCell(cell);
                        for (int l = minrow; l < TABLE_ITEM_TEMP[0].length; l++) {
                            if (l == minrow) {
                                cell = new PdfPCell(new Paragraph(new Chunk(ut.
                                        formatNumbervn(VAT_TEXT_FIRST),
                                        FONT_IN_TABLE_TOTAL)));
                                cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                                cell.setHorizontalAlignment(iTableAlight[l]);
                                tableItem.getDefaultCell().setVerticalAlignment(
                                        Element.
                                        ALIGN_MIDDLE);
                                tableItem.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Chunk(" ",
                                        FONT_IN_TABLE_TOTAL)));
                                cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                                tableItem.getDefaultCell().setVerticalAlignment(
                                        Element.
                                        ALIGN_MIDDLE);
                                tableItem.addCell(cell);
                            }
                        }
                        //Show Second VAT-----------------------------------------------------
                        minrow = ROW_TOTAL[0];
                        for (int l = 0; l < ROW_TOTAL.length; l++) {
                            if (minrow > ROW_TOTAL[l]) {
                                minrow = ROW_TOTAL[l];
                            }
                        }
                        cell = new PdfPCell(new Paragraph(VAT_LABEL_SECOND,
                                FONT_IN_TABLE_TOTAL));
                        cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                        cell.setColspan(minrow);
                        cell.setBackgroundColor(COLOR_BACKGROUND_TOTAL);
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        tableItem.getDefaultCell().setVerticalAlignment(Element.
                                ALIGN_MIDDLE);
                        tableItem.addCell(cell);
                        for (int l = minrow; l < TABLE_ITEM_TEMP[0].length; l++) {
                            if (l == minrow) {
                                cell = new PdfPCell(new Paragraph(new Chunk(ut.
                                        formatNumbervn(VAT_TEXT_SECOND),
                                        FONT_IN_TABLE_TOTAL)));
                                cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                                cell.setHorizontalAlignment(iTableAlight[l]);
                                tableItem.getDefaultCell().setVerticalAlignment(
                                        Element.
                                        ALIGN_MIDDLE);
                                tableItem.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Chunk(" ",
                                        FONT_IN_TABLE_TOTAL)));
                                cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                cell.setBorderColor(ORDER_FORM_BORDER_TABLE);
                                tableItem.getDefaultCell().setVerticalAlignment(
                                        Element.
                                        ALIGN_MIDDLE);
                                tableItem.addCell(cell);
                            }
                        }
                    }

                    tableItem.setTotalWidth(MAX_SIZE_TABLE);
                    tableItem.setLockedWidth(true);
                    document.add(tableItem);
                    //not a last page
                } else {
                    tableItem.setTotalWidth(MAX_SIZE_TABLE);
                    tableItem.setLockedWidth(true);
                    document.add(tableItem);
                }
            }

            //-----------------------------------------Part 4 Add Info Bottom-----------------------------------------//
            //Add Info Bottom
            pSpace = new Paragraph(" ",
                                   FontFactory.getFont(
                                           FontFactory.
                                           HELVETICA, 4, Font.BOLD,
                                           new Color(0, 0, 0)));
            pSpace.setAlignment(Element.ALIGN_CENTER);
            document.add(pSpace);

            for (int i = 0; i < ORDER_FORM_INFO_BOTTOM.length; i++) {
                pSpace = new Paragraph(ORDER_FORM_INFO_BOTTOM[i],
                                       FontFactory.getFont(
                                               FontFactory.
                                               HELVETICA, 9, Font.BOLD,
                                               new Color(0, 0, 0)));
                pSpace.setAlignment(Element.ALIGN_LEFT);
                document.add(pSpace);
            }
            pSpace = new Paragraph(" ",
                                   FontFactory.getFont(
                                           FontFactory.
                                           HELVETICA, 2, Font.BOLD,
                                           new Color(0, 0, 0)));
            pSpace.setAlignment(Element.ALIGN_CENTER);
            document.add(pSpace);

            //-----------------------------------------Part 5 Add space-----------------------------------------//
            //Add space
            pSpace = new Paragraph(" ",
                                   FontFactory.getFont(
                                           FontFactory.
                                           HELVETICA, 9, Font.BOLD,
                                           new Color(0, 0, 0)));
            pSpace.setAlignment(Element.ALIGN_CENTER);
            document.add(pSpace);
//      pSpace.setAlignment(Element.ALIGN_CENTER);
//      document.add(pSpace);

            //-----------------------------------------Part 6 Add SIGNAL-----------------------------------------//
            //Add SIGNAL
            PdfPTable tableSign = new PdfPTable(2);
            tableSign.getDefaultCell().setHorizontalAlignment(Element.
                    ALIGN_CENTER);
            tableSign.getDefaultCell().setGrayFill(0f);
            tableSign.getDefaultCell().setBorderColor(new Color(255, 255,
                    255));
            tableSign.addCell(LEFT_SIGN);
            tableSign.getDefaultCell().setHorizontalAlignment(Element.
                    ALIGN_CENTER);
            tableSign.getDefaultCell().setGrayFill(0f);
            tableSign.getDefaultCell().setBorderColor(new Color(255, 255,
                    255));
            tableSign.addCell(RIGHT_SIGN);
            document.add(tableSign);

            //-----------------------------------------Part 7 End Document-----------------------------------------//
            document.close();
            //-----------------------------------------Part 8 Add Header-----------------------------------------//
            //Add Header
            PdfReader reader = new PdfReader(sFileNameTemp);
            int iTotalPage = reader.getNumberOfPages();
            PdfStamper stamp = new PdfStamper(reader,
                                              new FileOutputStream(FILE_NAME));
            PdfContentByte over;

            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
                                              BaseFont.WINANSI,
                                              BaseFont.EMBEDDED);
            int i = 0;
            while (i < iTotalPage) {
                i++;
                over = stamp.getOverContent(i);
                over.beginText();
                over.setFontAndSize(bf, 9);
                over.showTextAligned(Element.ALIGN_LEFT, HEADER[0][0], 36, 810,
                                     0);
                over.showTextAligned(Element.ALIGN_LEFT,
                                     "Trang: " + i + "/" + iTotalPage, 505, 810,
                                     0);
                over.endText();
            }
            stamp.close();
            deleteFile(sFileNameTemp);
        } catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
            document.close();
            return;
        }

    }

    //Create file PDF, for A4 POS print
    public void createFilePDF() {
        int i, j, l;
        Utils ut = new Utils();
        String TABLE_ITEM_TEMP[][];
        if (FILE_NAME.equals("")) {
            return;
        }
        try {
            document.setPageSize(PAGE_SIZE);
            if (IS_MAGIN) {
                document.setMargins(MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP,
                                    MARGIN_BOTTOM);
            }
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(FILE_NAME));
            document.open();

            if (IS_GROUP_BY_ART_NUMBER) {
                int k, m, n, o, iPos = 0;
                String[] TABLE_TEMP = new String[TABLE_ITEM[0].length];
                //Sort ASC of TABLE_ITEM when group with Art#
                for (k = 0; k < TABLE_ITEM.length; k++) {
                    for (m = 0; m < TABLE_ITEM.length; m++) {
                        if (Integer.parseInt(TABLE_ITEM[k][GROUP_AT]) <
                            Integer.parseInt(TABLE_ITEM[m][GROUP_AT])) {
                            for (n = 0; n < TABLE_ITEM[k].length; n++) {
                                TABLE_TEMP[n] = TABLE_ITEM[k][n];
                            }
                            for (n = 0; n < TABLE_ITEM[m].length; n++) {
                                TABLE_ITEM[k][n] = TABLE_ITEM[m][n];
                            }
                            for (n = 0; n < TABLE_ITEM[k].length; n++) {
                                TABLE_ITEM[m][n] = TABLE_TEMP[n];
                            }
                        }
                    }
                }
                //Count group Art Number
                int iCountGroup = countGroupArtNumber(TABLE_ITEM, GROUP_AT);
                TABLE_ITEM_TEMP = new String[TABLE_ITEM.length +
                                  iCountGroup][TABLE_ITEM[0].length];
                long[][] lTotalGroup = new long[ROW_TOTAL.length][iCountGroup];
                double[][] dTotalGroup = new double[ROW_TOTAL.length][
                                         iCountGroup]; //Type money
                //Sort id of matrix
                for (k = 0; k < TABLE_ITEM.length; k++) {
                    TABLE_ITEM[k][0] = String.valueOf(k + 1);
                }
                //Insert Table of Item to Table of Item Temp
                iPos = 0;
                for (k = 0; k < TABLE_ITEM.length - 1; k++) {
                    if (!TABLE_ITEM[k][GROUP_AT].equals(TABLE_ITEM[k +
                            1][GROUP_AT])) {
                        for (m = 0; m < TABLE_ITEM[k].length; m++) {
                            TABLE_ITEM_TEMP[iPos][m] = TABLE_ITEM[k][m];
                        }
                        iPos++;
                        for (m = 0; m < TABLE_ITEM[k].length; m++) {
                            TABLE_ITEM_TEMP[iPos][m] = " ";
                        }
                        iPos++;
                    } else {
                        for (m = 0; m < TABLE_ITEM[k].length; m++) {
                            TABLE_ITEM_TEMP[iPos][m] = TABLE_ITEM[k][m];
                        }
                        iPos++;
                    }
                }
                //Add end row
                for (m = 0; m < TABLE_ITEM[TABLE_ITEM.length - 1].length; m++) {
                    TABLE_ITEM_TEMP[iPos][m] = TABLE_ITEM[TABLE_ITEM.length -
                                               1][m];
                }
                iPos++;
                for (m = 0; m < TABLE_ITEM[TABLE_ITEM.length - 1].length; m++) {
                    TABLE_ITEM_TEMP[iPos][m] = " ";
                }
                for (o = 0; o < ROW_TOTAL.length; o++) {

                }
                for (m = 0; m < ROW_TOTAL.length; m++) {
                    //Total group Art#
                    for (k = 0; k < iCountGroup; k++) {
                        lTotalGroup[m][k] = 0;
                        dTotalGroup[m][k] = 0;
                    }

                    iPos = 0;
                    for (k = 0; k < TABLE_ITEM_TEMP.length; k++) {
                        if (TABLE_ITEM_TEMP[k][0].equals(" ")) {
                            if (FORMAT_COLUMN[ROW_TOTAL[m]].equalsIgnoreCase(
                                    "money")) {
                                TABLE_ITEM_TEMP[k][ROW_TOTAL[m]] = "" +
                                        dTotalGroup[m][iPos];
                            } else {
                                TABLE_ITEM_TEMP[k][ROW_TOTAL[m]] = "" +
                                        lTotalGroup[m][iPos];
                            }
                            iPos++;
                        } else {
                            if (FORMAT_COLUMN[ROW_TOTAL[m]].equalsIgnoreCase(
                                    "money")) {
                                dTotalGroup[m][iPos] +=
                                        Double.parseDouble(TABLE_ITEM_TEMP[k][
                                        ROW_TOTAL[m]]);
                            } else {
                                lTotalGroup[m][iPos] +=
                                        Long.parseLong(TABLE_ITEM_TEMP[k][
                                        ROW_TOTAL[m]]);
                            }
                        }
                    }
                }
            } else {
                int k, m, n;
                TABLE_ITEM_TEMP = new String[TABLE_ITEM.length][TABLE_ITEM[0].
                                  length];
                //Insert Table of Item to Table of Item Temp
                for (k = 0; k < TABLE_ITEM.length; k++) {
                    for (m = 0; m < TABLE_ITEM[k].length; m++) {
                        TABLE_ITEM_TEMP[k][m] = TABLE_ITEM[k][m];
                    }
                }
            }

            //Total page
            int iNum = TABLE_ITEM_TEMP.length;

            if (IS_GROUP_BY_ART_NUMBER) {
                iNum = TABLE_ITEM_TEMP.length;
            }

            int iTotalRowView = TOTAL_ROW_IN_PAGE - TOTAL_ROW_FOR_SIGNAL -
                                HEADER.length -
                                Math.round((float) (TITLE.length * 1.25)) - 5;
            int iPage = iNum / iTotalRowView + 1;
            int iTotalRowUnView = iTotalRowView * iPage - iNum;

            int currentPage = 0;
            //Table Skew
            float[] fTableSkew = new float[TABLE_ITEM_TEMP[0].length];
            int[] iTableAlight = new int[TABLE_ITEM_TEMP[0].length];

            for (i = 0; i < TABLE_ITEM_TEMP[0].length; i++) {
                fTableSkew[i] = (float) TABLE_SKEW[i] / 100;
            }

            for (i = 0; i < TABLE_ITEM_TEMP[0].length; i++) {
                if (TABLE_ALIGHT[i].equalsIgnoreCase("LEFT")) {
                    iTableAlight[i] = Element.ALIGN_LEFT;
                } else if (TABLE_ALIGHT[i].equalsIgnoreCase("RIGHT")) {
                    iTableAlight[i] = Element.ALIGN_RIGHT;
                } else if (TABLE_ALIGHT[i].equalsIgnoreCase("CENTER")) {
                    iTableAlight[i] = Element.ALIGN_CENTER;
                }
            }

            //Total Temp
            double[] fTotalTemp;
            double[] mTotalTemp;
            if (IS_VIEW_TOTAL) {
                fTotalTemp = new double[ROW_TOTAL.length];
                for (i = 0; i < ROW_TOTAL.length; i++) {
                    fTotalTemp[i] = 0;
                }

                mTotalTemp = new double[ROW_TOTAL.length];
                for (i = 0; i < ROW_TOTAL.length; i++) {
                    mTotalTemp[i] = 0;
                }
            } else {
                fTotalTemp = new double[1];
                mTotalTemp = new double[1];
            }

            for (currentPage = 1; currentPage <= iPage; currentPage++) {

                //-----------------------------------------Part 1-----------------------------------------//
                //Add New Page
                if (currentPage != 1) {
                    document.newPage();
                }

                //-----------------------------------------Part 2-----------------------------------------//
                //Add Header
                if (HEADER.length != 0) {
                    PdfPTable tableGroup;
                    if ((HEADER[0].length - 1) == 0) {
                        tableGroup = new PdfPTable(2);
                    } else {
                        tableGroup = new PdfPTable(HEADER[0].length);
                    }
                    for (i = 0; i < HEADER.length; i++) {
                        tableGroup.getDefaultCell().setGrayFill(0f);
                        tableGroup.getDefaultCell().setBorderColor(
                                COLOR_BORDER_TABLE_HEADER);
                        for (j = 0; j < HEADER[i].length; j++) {
                            if ((HEADER.length - 1) == 0 &&
                                (HEADER[i].length - 1) == 0) {
                                tableGroup.getDefaultCell().
                                        setHorizontalAlignment(
                                                Element.ALIGN_LEFT);
                                tableGroup.addCell(new Paragraph(new Chunk(
                                        HEADER[i][
                                        j], FONT_HEADER)));

                                tableGroup.getDefaultCell().
                                        setHorizontalAlignment(
                                                Element.ALIGN_RIGHT);
                                tableGroup.addCell(new Paragraph(new Chunk(
                                        PAGE_STRING1 + " " +
                                        currentPage +
                                        " " + PAGE_STRING2 + " " + iPage,
                                        FONT_HEADER)));
                            } else {
                                if (j == 0) {
                                    tableGroup.getDefaultCell().
                                            setHorizontalAlignment(
                                            Element.ALIGN_LEFT);
                                } else if (j == (HEADER[i].length - 1)) {
                                    tableGroup.getDefaultCell().
                                            setHorizontalAlignment(
                                            Element.ALIGN_RIGHT);
                                } else {
                                    tableGroup.getDefaultCell().
                                            setHorizontalAlignment(
                                            Element.ALIGN_CENTER);
                                }
                                if (i == 0 && j == HEADER[i].length - 1) {
                                    tableGroup.addCell(new Paragraph(new Chunk(
                                            "Page: " +
                                            currentPage +
                                            " of " + iPage, FONT_HEADER)));
                                } else {
                                    tableGroup.addCell(new Paragraph(new Chunk(
                                            HEADER[i][
                                            j], FONT_HEADER)));
                                }
                            }
                        }
                    }
                    tableGroup.setTotalWidth(MAX_SIZE_TABLE);
                    tableGroup.setLockedWidth(true);
                    document.add(tableGroup);
                }

                //-----------------------------------------Part 3-----------------------------------------//
                //Add Title
                for (i = 0; i < TITLE.length; i++) {
                    Paragraph pTitle;

                    if (i == (TITLE.length - 1)) {
                        pTitle = new Paragraph(
                                TITLE[i] + "\n\n",
                                FONT_TITLE);
                    } else {
                        pTitle = new Paragraph(
                                TITLE[i],
                                FONT_TITLE);
                    }
                    pTitle.setAlignment(Element.ALIGN_CENTER);
                    document.add(pTitle);
                }

                //-----------------------------------------Part 4-----------------------------------------//
                //Ad Info
                if (INFO.length != 0) {
                    PdfPTable tableInfo = new PdfPTable(INFO[0].length);

                    for (i = 0; i < INFO.length; i++) {
                        tableInfo.getDefaultCell().setGrayFill(0f);
                        tableInfo.getDefaultCell().setBorderColor(
                                COLOR_BORDER_TABLE_INFO);
                        for (j = 0; j < INFO[i].length; j++) {
                            if (j == 0) {
                                tableInfo.getDefaultCell().
                                        setHorizontalAlignment(
                                                Element.ALIGN_LEFT);
                            } else if (j == (INFO[i].length - 1)) {
                                tableInfo.getDefaultCell().
                                        setHorizontalAlignment(
                                                Element.ALIGN_LEFT);
                            } else {
                                tableInfo.getDefaultCell().
                                        setHorizontalAlignment(
                                                Element.ALIGN_LEFT);
                            }

                            tableInfo.addCell(new Paragraph(new Chunk(
                                    INFO[i][j], FONT_INFO)));

                        }
                    }
                    tableInfo.setTotalWidth(MAX_SIZE_TABLE);
                    tableInfo.setLockedWidth(true);
                    document.add(tableInfo);
                }

                //-----------------------------------------Part 5-----------------------------------------//
                //Add space
                Paragraph pSpace = new Paragraph(" ",
                                                 FontFactory.getFont(
                        FontFactory.
                        HELVETICA, 9, Font.BOLD,
                        new Color(0, 0, 0)));
                pSpace.setAlignment(Element.ALIGN_CENTER);
                document.add(pSpace);

                //-----------------------------------------Part 6-----------------------------------------//
                int iNo = iTotalRowView * (currentPage - 1);
                int iBeforeFor = iTotalRowView * (currentPage - 1);
                int iEndFor = 0;
                int iRemain = 0;

                if (currentPage == iPage) {
                    iRemain = TABLE_ITEM_TEMP.length -
                              iTotalRowView * (currentPage - 1);
                } else {
                    iRemain = iTotalRowView;
                }

                iEndFor = iTotalRowView * (currentPage - 1) + iRemain;

                //Add Table Item
                PdfPTable tableItem = new PdfPTable(fTableSkew);
                PdfPCell cellItem;

                for (i = iBeforeFor; i < iEndFor; i++) {
                    if (i == iBeforeFor) {
                        for (j = 0; j < TABLE_ITEM_TEMP[0].length; j++) {

                            //Add Table Title
                            cellItem = new PdfPCell(new Paragraph(new Chunk(
                                    TABLE_TITLE[j],
                                    FONT_IN_TABLE_TITLE)));
                            cellItem.setBorderColor(COLOR_BORDER_TABLE);
                            cellItem.setHorizontalAlignment(
                                    Element.ALIGN_CENTER);
                            cellItem.setBackgroundColor(
                                    COLOR_BACKGROUND_TABLE_TITLE);
                            cellItem.setGrayFill(0f);
                            tableItem.addCell(cellItem);
                        }
                    }

                    if (TABLE_ITEM_TEMP[i][0].equals(" ") &&
                        IS_GROUP_BY_ART_NUMBER) {
                        int k;
                        //Print total group name
                        PdfPCell cell = new PdfPCell(new Paragraph(
                                TOTAL_GROUP_NAME,
                                FONT_IN_TABLE_TOTAL_GROUP_ART));
                        cell.setBorderColor(COLOR_BORDER_TABLE);
                        cell.setColspan(3);
                        cell.setBackgroundColor(COLOR_BACKGROUND_TOTAL);
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        tableItem.getDefaultCell().setVerticalAlignment(Element.
                                ALIGN_MIDDLE);
                        tableItem.addCell(cell);

                        for (k = 3; k < TABLE_ITEM_TEMP[0].length; k++) {
                            if (checkCol(k, ROW_TOTAL)) {

                                if (FORMAT_COLUMN[k].equalsIgnoreCase("double")) {
                                    cell = new PdfPCell(new Paragraph(new Chunk(
                                            ut.formatNumbervn((
                                            String.
                                            valueOf(TABLE_ITEM_TEMP[i][k]).
                                            toString())),
                                            FONT_IN_TABLE_TOTAL_GROUP_ART)));
                                } else if (FORMAT_COLUMN[k].equalsIgnoreCase(
                                        "money")) {
                                    cell = new PdfPCell(new Paragraph(new Chunk(
                                            ut.formatNumber((
                                            String.
                                            valueOf(TABLE_ITEM_TEMP[i][k]).
                                            toString())),
                                            FONT_IN_TABLE_TOTAL_GROUP_ART)));
                                } else if (FORMAT_COLUMN[k].equalsIgnoreCase(
                                        "int")) {
                                    String strTemp = String.
                                            valueOf(TABLE_ITEM_TEMP[i][k]).
                                            toString();
                                    cell = new PdfPCell(new Paragraph(new Chunk(
                                            strTemp,
                                            FONT_IN_TABLE_TOTAL_GROUP_ART)));
                                } else {
                                    cell = new PdfPCell(new Paragraph(new Chunk(
                                            fixStringWithWidth(String.
                                            valueOf(TABLE_ITEM_TEMP[i][k]).
                                            toString(),
                                            FONT_IN_TABLE_TOTAL_GROUP_ART,
                                            TABLE_SKEW[k], MAX_SIZE_TABLE),
                                            FONT_IN_TABLE_TOTAL_GROUP_ART)));
                                }
                                cell.setBorderColor(COLOR_BORDER_TABLE);
                                cell.setHorizontalAlignment(iTableAlight[k]);
                                tableItem.getDefaultCell().setVerticalAlignment(
                                        Element.
                                        ALIGN_MIDDLE);
                                tableItem.addCell(cell);
                            } else {
                                cell = new PdfPCell(new Paragraph(new Chunk(" ",
                                        FONT_IN_TABLE_TOTAL_GROUP_ART)));
                                cell.setBorderColor(COLOR_TABLE_ITEM);
                                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                cell.setBorderColor(COLOR_BORDER_TABLE);
                                tableItem.getDefaultCell().setVerticalAlignment(
                                        Element.
                                        ALIGN_MIDDLE);
                                tableItem.addCell(cell);
                            }
                        }
                    } else {
                        for (j = 0; j < TABLE_ITEM_TEMP[0].length; j++) {
                            //Total Data
                            if (IS_VIEW_TOTAL) {
                                for (l = 0; l < ROW_TOTAL.length; l++) {
                                    if (j == ROW_TOTAL[l]) {
                                        String strTemp = String.valueOf(
                                                TABLE_ITEM_TEMP[i][j]).
                                                toString();
                                        if (FORMAT_COLUMN[j].equalsIgnoreCase(
                                                "money")) {
                                            mTotalTemp[l] +=
                                                    ut.convertToDouble(strTemp);
                                        } else {
                                            fTotalTemp[l] +=
                                                    ut.convertToDouble(strTemp);
                                        }
                                    }
                                }
                            }
                            if (FORMAT_COLUMN[j].equalsIgnoreCase("double")) {
                                cellItem = new PdfPCell(new Paragraph(new Chunk(
                                        ut.formatNumbervn(String.valueOf(
                                        TABLE_ITEM_TEMP[i][j]).
                                        toString()),
                                        FONT_IN_TABLE)));
                            } else if (FORMAT_COLUMN[j].equalsIgnoreCase(
                                    "money")) {
                                cellItem = new PdfPCell(new Paragraph(new Chunk(
                                        ut.formatNumber(String.valueOf(
                                        TABLE_ITEM_TEMP[i][j]).
                                        toString()),
                                        FONT_IN_TABLE)));
                            } else if (FORMAT_COLUMN[j].equalsIgnoreCase("int")) {
                                cellItem = new PdfPCell(new Paragraph(new Chunk(
                                        String.valueOf(Integer.parseInt(
                                        TABLE_ITEM_TEMP[i][j])).
                                        toString(),
                                        FONT_IN_TABLE)));
                            } else {
                                cellItem = new PdfPCell(new Paragraph(new Chunk(
                                        fixStringWithWidth(String.valueOf(
                                        TABLE_ITEM_TEMP[i][j]).
                                        toString(), FONT_IN_TABLE, TABLE_SKEW[j],
                                        MAX_SIZE_TABLE),
                                        FONT_IN_TABLE)));
                            }
                            cellItem.setBorderColor(COLOR_BORDER_TABLE);
                            cellItem.setHorizontalAlignment(
                                    iTableAlight[j]);
                            cellItem.setBackgroundColor(
                                    COLOR_BACKGROUND_TABLE);
                            cellItem.setGrayFill(0f);
                            tableItem.addCell(cellItem);
                        }
                    }
                }
                //Add Total
                //Find min of row total
                int minrow = ROW_TOTAL[0];
                for (i = 0; i < ROW_TOTAL.length; i++) {
                    if (minrow > ROW_TOTAL[i]) {
                        minrow = ROW_TOTAL[i];
                    }
                }
                if ((IS_VIEW_TOTAL && iPage == currentPage) ||
                    (IS_VIEW_TOTAL_IN_PAGE)) {

                    PdfPCell cell = new PdfPCell(new Paragraph(TOTAL_NAME,
                            FONT_IN_TABLE_TOTAL));
                    cell.setBorderColor(COLOR_BORDER_TABLE);
                    cell.setColspan(minrow);
                    cell.setBackgroundColor(COLOR_BACKGROUND_TOTAL);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableItem.getDefaultCell().setVerticalAlignment(Element.
                            ALIGN_MIDDLE);
                    tableItem.addCell(cell);

                    for (i = minrow; i < TABLE_ITEM_TEMP[0].length; i++) {
                        if (checkCol(i, ROW_TOTAL)) {

                            if (FORMAT_COLUMN[i].equalsIgnoreCase("double")) {
                                cell = new PdfPCell(new Paragraph(new Chunk(ut.
                                        formatNumbervn((
                                                String.
                                                valueOf(fTotalTemp[getID(i,
                                        ROW_TOTAL)]).
                                                toString())),
                                        FONT_IN_TABLE_TOTAL)));

                            } else if (FORMAT_COLUMN[i].equalsIgnoreCase(
                                    "money")) {
                                cell = new PdfPCell(new Paragraph(new Chunk(ut.
                                        formatNumber((
                                                String.
                                                valueOf(mTotalTemp[getID(i,
                                        ROW_TOTAL)]).
                                                toString())),
                                        FONT_IN_TABLE_TOTAL)));

                            }

                            else if (FORMAT_COLUMN[i].equalsIgnoreCase("int")) {
                                String strTemp = String.
                                                 valueOf(fTotalTemp[getID(i,
                                        ROW_TOTAL)]).
                                                 toString();
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        strTemp,
                                        FONT_IN_TABLE_TOTAL)));
                            } else {
                                cell = new PdfPCell(new Paragraph(new Chunk(
                                        String.
                                        valueOf(fTotalTemp[getID(i, ROW_TOTAL)]).
                                        toString(),
                                        FONT_IN_TABLE_TOTAL)));
                            }
                            cell.setBorderColor(COLOR_BORDER_TABLE);
                            cell.setHorizontalAlignment(iTableAlight[i]);
                            tableItem.getDefaultCell().setVerticalAlignment(
                                    Element.
                                    ALIGN_MIDDLE);
                            tableItem.addCell(cell);
                        } else {
                            cell = new PdfPCell(new Paragraph(new Chunk(" ",
                                    FONT_IN_TABLE_TOTAL)));
                            cell.setBorderColor(COLOR_TABLE_ITEM);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorderColor(COLOR_BORDER_TABLE);
                            tableItem.getDefaultCell().setVerticalAlignment(
                                    Element.
                                    ALIGN_MIDDLE);
                            tableItem.addCell(cell);
                        }
                    }
                    tableItem.setTotalWidth(MAX_SIZE_TABLE);
                    tableItem.setLockedWidth(true);
                    document.add(tableItem);
                } else {
                    tableItem.setTotalWidth(MAX_SIZE_TABLE);
                    tableItem.setLockedWidth(true);
                    document.add(tableItem);
                }
                //-----------------------------------------Part 7-----------------------------------------//
                //Add space
                document.add(pSpace);
                //Add row space
                if (iPage == currentPage && iTotalRowUnView > 0) {
                    int iEndForUnView = iTotalRowUnView - 1;
                    for (i = 0; i < iEndForUnView; i++) {
                        PdfPTable tableGroup;
                        tableGroup = new PdfPTable(1);
                        tableGroup.getDefaultCell().setGrayFill(0f);
                        tableGroup.getDefaultCell().setBorderColor(
                                COLOR_BORDER_TABLE_UNVIEW);
                        tableGroup.addCell(new Paragraph(new Chunk(
                                " ",
                                FONT_IN_TABLE_TOTAL)));
                        tableGroup.setTotalWidth(MAX_SIZE_TABLE);
                        tableGroup.setLockedWidth(true);
                        document.add(tableGroup);
                    }
                }
                //-----------------------------------------Part 8-----------------------------------------//
                //Add space
                document.add(pSpace);
                //Add SIGNAL
                PdfPTable tableSign = new PdfPTable(2);
                tableSign.getDefaultCell().setHorizontalAlignment(Element.
                        ALIGN_CENTER);
                tableSign.getDefaultCell().setGrayFill(0f);
                tableSign.getDefaultCell().setBorderColor(new Color(255, 255,
                        255));
                tableSign.addCell(LEFT_SIGN);
                tableSign.getDefaultCell().setHorizontalAlignment(Element.
                        ALIGN_CENTER);
                tableSign.getDefaultCell().setGrayFill(0f);
                tableSign.getDefaultCell().setBorderColor(new Color(255, 255,
                        255));
                tableSign.addCell(RIGHT_SIGN);
                document.add(tableSign);
            }
            document.close();
        } catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
            document.close();
            return;
        }
    }

    /**
     * Print receipt has type TXN
     * Print bill for custumer
     */
    private void createFileReceiptTXN() {
        if (FILE_NAME.equals("")) {
            return;
        }
        if (TYPE_RECEIPT != 2) {
            return;
        }
        try {
            int i, j;
            Utils ut = new Utils();
            float[] fTableSkew = new float[TABLE_TNX_SKEW.length];
            int[] iTableAlight = new int[TABLE_ALIGHT_TNX.length];

            for (i = 0; i < TABLE_TNX_SKEW.length; i++) {
                fTableSkew[i] = (float) TABLE_TNX_SKEW[i] / 100;
            }

            document.setPageSize(PAGE_SIZE_TNX);
            document.setMargins(MARGIN_LEFT_TNX, MARGIN_RIGHT_TNX,
                                MARGIN_TOP_TNX,
                                MARGIN_BOTTOM_TNX);

            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(FILE_NAME));
            document.open();

            for (i = 0; i < TABLE_ALIGHT_TNX.length; i++) {
                if (TABLE_ALIGHT_TNX[i].equalsIgnoreCase("LEFT")) {
                    iTableAlight[i] = Element.ALIGN_LEFT;
                } else if (TABLE_ALIGHT_TNX[i].equalsIgnoreCase("RIGHT")) {
                    iTableAlight[i] = Element.ALIGN_RIGHT;
                } else if (TABLE_ALIGHT_TNX[i].equalsIgnoreCase("CENTER")) {
                    iTableAlight[i] = Element.ALIGN_CENTER;
                }
            }

            //Header of receipt
            float ftableHeader[] = {0.42f, 0.25f, 0.33f};
            PdfPTable tableHeader = new PdfPTable(ftableHeader);
            //Pos name
            PdfPCell cell = new PdfPCell(new Paragraph(new Chunk("",
                    FONT_ITEM_TNX)));
            tableHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            //Add header of receipt (Store name - store number) - line 1
            cell = new PdfPCell(new Paragraph(new Chunk(TITLE_TNX + " " +
                    HEADER_RECEIPT[0],
                    FONT_HEADER_TNX)));
            cell.setColspan(4);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableHeader.getDefaultCell().setVerticalAlignment(Element.
                    ALIGN_MIDDLE);
            tableHeader.addCell(cell);

            //Add header of receipt(Address of Store)
            cell = new PdfPCell(new Paragraph(new Chunk(HEADER_RECEIPT[1],
                    FONT_HEADER_TNX)));
            cell.setColspan(4);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableHeader.getDefaultCell().setVerticalAlignment(Element.
                    ALIGN_MIDDLE);
            tableHeader.addCell(cell);

            //Empty line
            cell = new PdfPCell(new Paragraph(new Chunk("", FONT_HEADER_TNX)));
            cell.setColspan(4);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableHeader.getDefaultCell().setVerticalAlignment(Element.
                    ALIGN_MIDDLE);
            tableHeader.addCell(cell);

            //HOA DON BAN LE
            cell = new PdfPCell(new Paragraph(new Chunk("HOA DON BAN LE",
                    FONT_HEADER_TNX)));
            cell.setColspan(4);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableHeader.getDefaultCell().setVerticalAlignment(Element.
                    ALIGN_MIDDLE);
            tableHeader.addCell(cell);

            //Empty line
            cell = new PdfPCell(new Paragraph(new Chunk("", FONT_HEADER_TNX)));
            cell.setColspan(4);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableHeader.getDefaultCell().setVerticalAlignment(Element.
                    ALIGN_MIDDLE);
            tableHeader.addCell(cell);

            /*
             Header details
             Ngay: yyyy-mm-dd  hh:mm:ss NV: admin
             */
            for (i = 2; i < HEADER_RECEIPT.length; i++) {
                cell = new PdfPCell(new Paragraph(new Chunk(HEADER_RECEIPT[i],
                        FONT_ITEM_TNX)));
                cell.setBorderColor(COLOR_ITEM_TNX);
                if (i == 2) {
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                } else if (i == 3) {
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                } else {
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                }
                tableHeader.getDefaultCell().setVerticalAlignment(
                        Element.ALIGN_MIDDLE);
                tableHeader.addCell(cell);
            }

            //Add Customer Info
            cell = new PdfPCell(new Paragraph(new Chunk(CUST_INFO,
                    FONT_HEADER_TNX)));
            cell.setColspan(4);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableHeader.getDefaultCell().setVerticalAlignment(Element.
                    ALIGN_MIDDLE);
            tableHeader.addCell(cell);

            tableHeader.setTotalWidth(MAX_SIZE_TABLE_TNX);
            tableHeader.setLockedWidth(true);
            document.add(tableHeader);

            //-----------------------------

            PdfPTable tableItem = new PdfPTable(fTableSkew);
            tableItem.setHorizontalAlignment(Element.ALIGN_LEFT);

            //Add line 1 ---------------------------------------------------------
            cell = new PdfPCell(new Paragraph(new Chunk(
                    "---------------------------------------------------------",
                    FONT_ITEM_TNX)));
            cell.setColspan(4);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableItem.addCell(cell);
            //Add items detail

            for (i = 0; i < ITEM_INFO_TNX.length; i++) {
                if (i % 2 != 0) { /*2 ---Detail of receipt*/
                    for (j = 0; j < ITEM_INFO_TNX[i].length; j++) {
                        if (((j == ITEM_INFO_TNX[i].length - 1) ||
                             (j == ITEM_INFO_TNX[i].length - 3)) &&
                            TYPE_CURRENCY.equalsIgnoreCase("VND")) {
                            cell = new PdfPCell(new Paragraph(new Chunk(ut.
                                    formatNumbervn(String.valueOf(ITEM_INFO_TNX[i][j]).
                                    toString()), FONT_ITEM_TNX)));
                        } else {
                            cell = new PdfPCell(new Paragraph(new Chunk(
                                    ITEM_INFO_TNX[i][j], FONT_ITEM_TNX)));
                        }

                        if (j == 2) { //Unit Price
                            cell.setColspan(2);
                        } else if (j == 4) {
                            cell.setColspan(3);
                        }

                        cell.setHorizontalAlignment(iTableAlight[j]);
                        cell.setBorderColor(COLOR_ITEM_TNX);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        tableItem.addCell(cell);
                    }
                } else { //1 ---Header of item
                    String strTemp = "";
                    for (j = 0; j < ITEM_INFO_TNX[i].length - 3; j++) {
                        strTemp += ITEM_INFO_TNX[i][j] + " ";
                    }
                    cell = new PdfPCell(new Paragraph(new Chunk(strTemp,
                            FONT_ITEM_TNX)));

                    cell.setColspan(2);
                    cell.setBorderColor(COLOR_ITEM_TNX);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableItem.addCell(cell);

                    //Add discount
                    cell = new PdfPCell(new Paragraph(new Chunk(ITEM_INFO_TNX[i][2],
                            FONT_ITEM_TNX)));

                    cell.setBorderColor(COLOR_ITEM_TNX);
                    cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableItem.addCell(cell);

                    if (!ut.formatNumbervn(String.valueOf(ITEM_INFO_TNX[i][3]).
                                           toString()).equals("0")) {
                        cell = new PdfPCell(new Paragraph(new Chunk(ut.
                                formatNumbervn(
                                        String.valueOf(ITEM_INFO_TNX[i][3]).
                                        toString()),
                                FONT_ITEM_TNX)));
                    } else {
                        cell = new PdfPCell(new Paragraph(new Chunk("",
                                FONT_ITEM_TNX)));
                    }

                    cell.setBorderColor(COLOR_ITEM_TNX);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setVerticalAlignment(
                            Element.ALIGN_MIDDLE);
                    tableItem.addCell(cell);

                }
            }
            //Add line 2
            cell = new PdfPCell(new Paragraph(new Chunk(
                    "---------------------------------------------------------",
                    FONT_ITEM_TNX)));
            cell.setColspan(4);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableItem.addCell(cell);

            tableItem.setTotalWidth(MAX_SIZE_TABLE_TNX);
            tableItem.setLockedWidth(true);
            document.add(tableItem);
            //------------------------------------------------------------------------------
            float[] fEndSkew = {
                               0.35f, 0.15f, 0.5f};
            PdfPTable tableEnding = new PdfPTable(fEndSkew);
            tableEnding.setHorizontalAlignment(Element.ALIGN_LEFT);

            //Subtotal
            cell = new PdfPCell(new Paragraph(new Chunk("Tong tien:",
                    FONT_ITEM_TNX)));
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            cell = new PdfPCell(new Paragraph(new Chunk(TYPE_CURRENCY,
                    FONT_ITEM_TNX)));
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            if (TYPE_CURRENCY.equalsIgnoreCase("VND")) {
                cell = new PdfPCell(new Paragraph(new Chunk(ut.formatNumbervn(
                        String.valueOf(TOTAL_TNX[0]).
                        toString()),
                        FONT_ITEM_BOLD_TNX)));
            } else {
                cell = new PdfPCell(new Paragraph(new Chunk(TOTAL_TNX[0],
                        FONT_ITEM_BOLD_TNX)));
            }
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

//Mark this: Trung 11-11-2008?
//            //Subtotal
//            cell = new PdfPCell(new Paragraph(new Chunk("Tong hang mua:",
//                    FONT_ITEM_TNX)));
//            cell.setBorderColor(COLOR_ITEM_TNX);
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            tableEnding.addCell(cell);
//
//            //Empty Cell
//            cell = new PdfPCell(new Paragraph(new Chunk("",
//                    FONT_ITEM_TNX)));
//            cell.setBorderColor(COLOR_ITEM_TNX);
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            tableEnding.addCell(cell);
//
//            //TotalQuantity
//            cell = new PdfPCell(new Paragraph(new Chunk(TOTAL_TNX[3],
//                    FONT_ITEM_BOLD_TNX)));
//            cell.setBorderColor(COLOR_ITEM_TNX);
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            tableEnding.addCell(cell);

            //Discount
            cell = new PdfPCell(new Paragraph(new Chunk("Tien giam:",
                    FONT_ITEM_TNX)));
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            cell = new PdfPCell(new Paragraph(new Chunk(
                    TYPE_CURRENCY,
                    FONT_ITEM_TNX)));
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            if (TYPE_CURRENCY.equalsIgnoreCase("VND")) {
                cell = new PdfPCell(new Paragraph(new Chunk(ut.formatNumbervn(
                        String.valueOf(TOTAL_TNX[1]).
                        toString()),
                        FONT_ITEM_BOLD_TNX)));
            } else {
                cell = new PdfPCell(new Paragraph(new Chunk(TOTAL_TNX[1],
                        FONT_ITEM_BOLD_TNX)));
            }
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            //Total
            cell = new PdfPCell(new Paragraph(new Chunk("Phai thu:",
                    FONT_ITEM_TNX)));
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            cell = new PdfPCell(new Paragraph(new Chunk(
                    TYPE_CURRENCY,
                    FONT_ITEM_TNX)));
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            if (TYPE_CURRENCY.equalsIgnoreCase("VND")) {
                cell = new PdfPCell(new Paragraph(new Chunk(ut.formatNumbervn(
                        String.valueOf(TOTAL_TNX[2]).
                        toString()),
                        FONT_ITEM_BOLD_TNX)));
            } else {
                cell = new PdfPCell(new Paragraph(new Chunk(TOTAL_TNX[2],
                        FONT_ITEM_BOLD_TNX)));
            }
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);
            if (IS_SHOW_AMOUNT) {
                //Amount Tendered
                cell = new PdfPCell(new Paragraph(new Chunk(
                        "Nhan vao:       ",
                        FONT_ITEM_TNX)));
                cell.setBorderColor(COLOR_ITEM_TNX);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(
                        Element.ALIGN_MIDDLE);
                tableEnding.addCell(cell);

                cell = new PdfPCell(new Paragraph(new Chunk(
                        TYPE_CURRENCY,
                        FONT_ITEM_TNX)));
                cell.setBorderColor(COLOR_ITEM_TNX);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(
                        Element.ALIGN_MIDDLE);
                tableEnding.addCell(cell);

                if (TYPE_CURRENCY.equalsIgnoreCase("VND")) {
                    cell = new PdfPCell(new Paragraph(new Chunk(AMOUNT_TENDERED,
                            FONT_ITEM_BOLD_TNX)));
                } else {
                    cell = new PdfPCell(new Paragraph(new Chunk(
                            AMOUNT_TENDERED,
                            FONT_ITEM_BOLD_TNX)));
                }
                cell.setBorderColor(COLOR_ITEM_TNX);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(
                        Element.ALIGN_MIDDLE);
                tableEnding.addCell(cell);

                //Changed Due
                cell = new PdfPCell(new Paragraph(new Chunk(
                        "Tra lai:       ",
                        FONT_ITEM_TNX)));
                cell.setBorderColor(COLOR_ITEM_TNX);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(
                        Element.ALIGN_MIDDLE);
                tableEnding.addCell(cell);

                cell = new PdfPCell(new Paragraph(new Chunk(
                        TYPE_CURRENCY,
                        FONT_ITEM_TNX)));
                cell.setBorderColor(COLOR_ITEM_TNX);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(
                        Element.ALIGN_MIDDLE);
                tableEnding.addCell(cell);

                if (TYPE_CURRENCY.equalsIgnoreCase("VND")) {
                    cell = new PdfPCell(new Paragraph(new Chunk(
                            CHANGED_DUE,
                            FONT_ITEM_BOLD_TNX)));
                } else {
                    cell = new PdfPCell(new Paragraph(new Chunk(
                            CHANGED_DUE,
                            FONT_ITEM_BOLD_TNX)));
                }
                cell.setBorderColor(COLOR_ITEM_TNX);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(
                        Element.ALIGN_MIDDLE);
                tableEnding.addCell(cell);
            }
            //Space
            cell = new PdfPCell(new Paragraph(new Chunk(
                    " ",
                    FONT_ITEM_BOLD_TNX)));
            cell.setColspan(3);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            //Thank you
            cell = new PdfPCell(new Paragraph(new Chunk(
                    "GIO MO CUA: 9h00 - 22h00",
                    FONT_ITEM_BOLD_TNX)));
            cell.setColspan(3);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);
            //Thank you
            cell = new PdfPCell(new Paragraph(new Chunk(
                    "CAM ON QUY KHACH!",
                    FONT_ITEM_BOLD_TNX)));
            cell.setColspan(3);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            //ID
            if (IS_PRINT_BARCODE) {
                PdfContentByte cb = writer.getDirectContent();
                Barcode39 code = new Barcode39();
                code.setCodeType(code.EAN13);
                code.setCode(ID_TNX);
                code.setN(1.7f);
                Image imageEAN = code.createImageWithBarcode(cb, null, null);
                cell = new PdfPCell(new Phrase(new Chunk(imageEAN, 0, 0)));
                /*
                         BarcodeEAN codeEAN = new BarcodeEAN();
                         codeEAN.setCodeType(codeEAN.EAN13);
                         codeEAN.setCode("*"+ID_TNX+"*");
                 Image imageEAN = codeEAN.createImageWithBarcode(cb, null, null);
                         //document.add(new Phrase(new Chunk(imageEAN, 0, 0)));
                 cell = new PdfPCell(new Phrase(new Chunk(imageEAN, 0, 0)));*/
            } else {
                cell = new PdfPCell(new Paragraph(new Chunk(
                        ID_TNX,
                        FONT_ITEM_TNX)));
            }

            cell.setColspan(3);
            cell.setBorderColor(COLOR_ITEM_TNX);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(
                    Element.ALIGN_MIDDLE);
            tableEnding.addCell(cell);

            tableEnding.setTotalWidth(MAX_SIZE_TABLE_TNX);
            tableEnding.setLockedWidth(true);
            document.add(tableEnding);

            /*
                   //Add Barcode
                   PdfContentByte cb = writer.getDirectContent();
                   BarcodeEAN codeEAN = new BarcodeEAN();
                   codeEAN.setCodeType(codeEAN.EAN13);
                   codeEAN.setCode("9780201615883");
             Image imageEAN = codeEAN.createImageWithBarcode(cb, null, null);
                   document.add(new Phrase(new Chunk(imageEAN, 0, 0)));*/

            document.close();
        } catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
            document.close();
            return;
        }
    }

    //Fix character input with width of table
    private String fixStringWithWidth(String sInput, Font fView, int iPercent,
                                      int iWidthMax) {
        String sTemp = "";
        //1 point = 0.65 pixel
        int iPixelText = ((int) fView.size() * 65 / 100);
        int iCount = (iWidthMax * iPercent / 100) / iPixelText;
        char cTemp[] = new char[sInput.length()];
        cTemp = sInput.toCharArray();

        //Fix length
        if (iCount > sInput.length()) {
            iCount = sInput.length();
        }

        for (int i = 0; i < iCount; i++) {
            sTemp += cTemp[i];
        }
        return sTemp;
    }

    //Count how many does group in matrix node
    private int countGroupArtNumber(String[][] saNode, int iPos) {
        int iCount = 0, i;
        for (i = 0; i < saNode.length - 1; i++) {
            int j = i + 1;
            if (!saNode[i][iPos].equals(saNode[j][iPos])) {
                iCount++;
            }
        }

        if (saNode.length > 0) {
            iCount++;
        }

        return iCount;
    }

    //Print PDF
    public void print() {
        Utils ut = new Utils();
        //print PDF file
        try {
            //print for PO
            if (PRINT_ORDER_FORM) {
                createOrderForm();
            } else if (TYPE_RECEIPT == 1) {
                createFilePDF();
            } else {
                createFileReceiptTXN();
            }

            //print for SIM, aks before printing
            //use jPDFprint lib
            if (SHOW_DIALOG) {
                //print for PO only, include barcode
                if (barcodePrint()) {
                    PrinterJob pJob = PrinterJob.getPrinterJob();
                    PDFPrint.setKey("effd26c13183");
                    PDFPrint pdfPrint = new PDFPrint(FILE_NAME, null);
                    pdfPrint.setPrintSettings(new PrintSettings(true, false, true, true));
                    pJob.setPrintable(pdfPrint);
//System.out.println("----"+mainFrame);
                    //show number of copy to print
                    DialogInputValue dlgCheckAccount = new DialogInputValue(
                            mainFrame,
                            "Print - Number of copy: ", true, mainFrame);
                    dlgCheckAccount.setLocation(210, 170);
                    dlgCheckAccount.setVisible(true);
//          System.out.println(    dlgCheckAccount.value);

                    if (dlgCheckAccount.value > 0) {
                        for (int i = 0; i < dlgCheckAccount.value; i++) {
                            pJob.print();
//              System.out.println("print");
                        }
                    }

                    //print for others like: PL, Transfer,Receipt,
                } else {
                    String[] arg = {
                                   FILE_NAME};
                    PrintPDF.main(arg);

                    //Delete file
                    if (IS_DELETE_FILE_AFTER_PRINT) {
                        deleteFile(FILE_NAME);
                    }
                }
                //print for POS
            } else {

                PrinterJob pJob = PrinterJob.getPrinterJob();
                PDFPrint.setKey("effd26c13183");
                PDFPrint pdfPrint = new PDFPrint(FILE_NAME, null);
                pdfPrint.setPrintSettings(new PrintSettings(true, false, true, true));
                pJob.setPrintable(pdfPrint);
                pJob.print();

            }

        } catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
            return;
        }
    }

    //check if  print barcode (now for PO only)
    //return true if barcode include 13 digits
    boolean barcodePrint() {
        boolean barcodePrint = false;
        Utils ut = new Utils();
        if (BARCODE.length() == 13 && ut.isLongIntString(BARCODE)) {
            barcodePrint = true;
        }
        return barcodePrint;
    }

    //Return id of a value
    private int getID(int iValue, int[] iCheck) {
        int iID = 0;
        for (int i = 0; i < iCheck.length; i++) {
            if (iValue == iCheck[i]) {
                return i;
            }
        }

        return iID;
    }

    //Check row
    private boolean checkCol(int iN, int[] iCheck) {
        for (int i = 0; i < iCheck.length; i++) {
            if (iN == iCheck[i]) {
                return true;
            }
        }
        return false;
    }

    //Set Page String of Receipt
    public void setPageString(String sPage1, String sPage2) {
        PAGE_STRING1 = sPage1;
        PAGE_STRING2 = sPage2;
    }

    //Get Page String of Receipt
    public String[] getPageString() {
        String[] saTemp = {
                          PAGE_STRING1, PAGE_STRING2};
        return saTemp;
    }

    //Set Page Size of Receipt
    public void setPageSize(Rectangle ps) {
        PAGE_SIZE = ps;
    }

    //Get Page Size of Receipt
    public Rectangle getPageSize() {
        return PAGE_SIZE;
    }

    //Set Margin of Page Receipt TNX
    public void setMarginsPageTNX(int iLeft, int iRight, int iTop, int iBottom) {
        MARGIN_LEFT_TNX = iLeft;
        MARGIN_RIGHT_TNX = iRight;
        MARGIN_TOP_TNX = iTop;
        MARGIN_BOTTOM_TNX = iBottom;
    }

    //Get Margin of Page Receipt TNX
    public int[] getMarginsPageTNX() {
        int[] m = {
                  MARGIN_LEFT_TNX, MARGIN_RIGHT_TNX, MARGIN_TOP_TNX,
                  MARGIN_BOTTOM_TNX};
        return m;
    }

    //Set Margin of Page Receipt
    public void setMarginsPage(int iLeft, int iRight, int iTop, int iBottom) {
        MARGIN_LEFT = iLeft;
        MARGIN_RIGHT = iRight;
        MARGIN_TOP = iTop;
        MARGIN_BOTTOM = iBottom;
    }

    //Get Margin of Page Receipt
    public int[] getMarginsPage() {
        int[] m = {
                  MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM};
        return m;
    }

    //Set file name
    public void setOrderFormInfoBottom(String sFileName[]) {
        ORDER_FORM_INFO_BOTTOM = sFileName;
    }

    //Get file name
    public String[] getOrderFormInfoBottom() {
        return ORDER_FORM_INFO_BOTTOM;
    }

    //Set file name
    public void setFileName(String sFileName) {
        FILE_NAME = sFileName;
    }

    //Get file name
    public String getFileName() {
        return FILE_NAME;
    }

    //Set Header
    public void setHeader(String sHeader[][]) {
        HEADER = sHeader;
    }

    //Get Header
    public String[][] getHeader() {
        return HEADER;
    }

    //Set Barcode
    public void setBarcode(String barcode) {
        this.BARCODE = barcode;
    }

    //Get Barcode
    public String getBarcode() {
        return BARCODE;
    }


    //Set Title
    public void setTitle(String sTitle[]) {
        TITLE = sTitle;
    }

    //Get Title
    public String[] getTitle() {
        return TITLE;
    }

    //Set Info
    public void setInfo(String sInfo[][]) {
        INFO = sInfo;
    }

    //Get Info
    public String[][] getInfo() {
        return INFO;
    }

    //Set font Header
    public void setFontHeader(Font ffHeader) {
        FONT_HEADER = ffHeader;
    }

    //Get font Header
    public Font getFontHeader() {
        return FONT_HEADER;
    }

    //Set max size Table
    public void setMaxSizeTable(int iMaxSize) {
        MAX_SIZE_TABLE = iMaxSize;
    }

    //Get max size Table
    public int getMaxSizeTable() {
        return MAX_SIZE_TABLE;
    }

    //Set color border table Header
    public void setBorderColorTableHeader(Color cBorder) {
        COLOR_BORDER_TABLE_HEADER = cBorder;
    }

    //Get color border table Header
    public Color getBorderColorTableHeader() {
        return COLOR_BORDER_TABLE_HEADER;
    }

    //Set color border table Info
    public void setBorderColorTableInfo(Color cBorder) {
        COLOR_BORDER_TABLE_INFO = cBorder;
    }

    //Get color border table Info
    public Color getBorderColorTableInfo() {
        return COLOR_BORDER_TABLE_INFO;
    }

    //Set total row in page
    public void setTotalRowInPage(int iTotal) {
        TOTAL_ROW_IN_PAGE = iTotal;
    }

    //Get total row in page
    public int getTotalRowInPage() {
        return TOTAL_ROW_IN_PAGE;
    }

    //Set total row for signal
    public void setTotalRowForSignal(int iTotal) {
        TOTAL_ROW_FOR_SIGNAL = iTotal;
    }

    //Get total row for signal
    public int getTotalRowForSignal() {
        return TOTAL_ROW_FOR_SIGNAL;
    }

    //Get type of receipt
    public int getTypeReceipt() {
        return TYPE_RECEIPT;
    }

    //Set type of receipt
    public void setTypeReceipt(int iType) {
        TYPE_RECEIPT = iType;
    }

    //Set name of right signal
    public void setRightSign(String sSign) {
        RIGHT_SIGN = sSign;
    }

    //Get name of right signal
    public String getRightSign() {
        return RIGHT_SIGN;
    }

    //Set title of tnx
    public void setTitleTNX(String sTitle) {
        TITLE_TNX = sTitle;
    }

    //Get title of tnx
    public String getAcrobatPath() {
        return ACROBAT_PATH;
    }

    //Set name of left signal
    public void setAcrobatPath(String sPath) {
        ACROBAT_PATH = sPath;
    }

    //Get title of tnx
    public String getTitleTNX() {
        return TITLE_TNX;
    }

    //Set name of left signal
    public void setLeftSign(String sSign) {
        LEFT_SIGN = sSign;
    }

    //Get name of left signal
    public String getLeftSign() {
        return LEFT_SIGN;
    }

    //Set Order Form Info
    public void setOrderFormInfoTop(String[] sNode) {
        ORDER_FORM_INFO_TOP = sNode;
    }

    //Get Order Form Info
    public String[] OrderFormInfoTop() {
        return ORDER_FORM_INFO_TOP;
    }

    //Set table align
    public void setTableAlign(String[] sNode) {
        TABLE_ALIGHT = sNode;
    }

    //Get table align
    public String[] getTableAlign() {
        return TABLE_ALIGHT;
    }

    //Set font in table
    public void setFontInTable(Font fFont) {
        FONT_IN_TABLE = fFont;
    }

    //Get name of left signal
    public Font getFontInTable() {
        return FONT_IN_TABLE;
    }

    //Set font in table
    public void setFontInTableTotal(Font fNode) {
        FONT_IN_TABLE_TOTAL = fNode;
    }

    //Get name of left signal
    public Font getFontInTableTotal() {
        return FONT_IN_TABLE_TOTAL;
    }

    //Set font in table
    public void setFontOrderFromTitle(Font fNode) {
        FONT_ORDER_FORM_TITLE = fNode;
    }

    //Get name of left signal
    public Font getFontOrderFromTitle() {
        return FONT_ORDER_FORM_TITLE;
    }

    //Set font in table
    public void setFontOrderFromLeft(Font fNode) {
        FONT_ORDER_FORM_LEFT = fNode;
    }

    //Get name of left signal
    public Font getFontOrderFromLeft() {
        return FONT_ORDER_FORM_LEFT;
    }

    //Set font in table
    public void setFontInTableTotalGroupArt(Font fNode) {
        FONT_IN_TABLE_TOTAL_GROUP_ART = fNode;
    }

    //Get name of left signal
    public Font getFontInTableTotalGroupArt() {
        return FONT_IN_TABLE_TOTAL_GROUP_ART;
    }

    //Set font in table
    public void setFontInTableTitle(Font fNode) {
        FONT_IN_TABLE_TITLE = fNode;
    }

    //Get name of left signal
    public Font getFontInTableTitle() {
        return FONT_IN_TABLE_TITLE;
    }

    //Set font in table
    public void setBorderTableUnview(Color cBorder) {
        COLOR_BORDER_TABLE_UNVIEW = cBorder;
    }

    //Get name of left signal
    public Color getBorderTableUnview() {
        return COLOR_BORDER_TABLE_UNVIEW;
    }

    //Delete once file
    public void deleteFile(String sFileName) {
        try {
            File file = new File(sFileName);
            if (file.isFile()) {
                file.deleteOnExit();
                if (!file.delete()) {
                    file.deleteOnExit();
                }
            }
        } catch (Exception e) {
            ExceptionHandle eh = new ExceptionHandle();
            eh.ouputError(e);
            return;
        }
    }

    //Get Order From Info skew
    public int[] getOrderFormInfoSkew() {
        return ORDER_FORM_INFO_SKEW;
    }

    //Set Order From Info skew
    public void setOrderFormInfoSkew(int[] iTableSkew) {
        ORDER_FORM_INFO_SKEW = iTableSkew;
    }

    //Get table skew
    public int[] getTableSkew() {
        return TABLE_SKEW;
    }

    //Set table skew
    public void setTableSkew(int[] iTableSkew) {
        TABLE_SKEW = iTableSkew;
    }

    //Get table align tnx
    public String[] getTableAlignTNX() {
        return TABLE_ALIGHT_TNX;
    }

    //Set table align tnx
    public void setTableAlignTNX(String[] sTableAlign) {
        TABLE_ALIGHT_TNX = sTableAlign;
    }

    //Set order form title
    public void setOrderFormTitle(String[] sOrderFormTitle) {
        ORDER_FORM_TITLE = sOrderFormTitle;
    }

    //Get order form title
    public String[] getOrderFormTitle() {
        return ORDER_FORM_TITLE;
    }

    //Set order form right
    public void setOrderFormRight(String[] sOrderFormRight) {
        ORDER_FORM_RIGHT = sOrderFormRight;
    }

    //Get order form right
    public String[] getOrderFormRight() {
        return ORDER_FORM_RIGHT;
    }

    //Get order form left
    public String[] getOrderFormLeft() {
        return ORDER_FORM_LEFT;
    }

    //Set order form left
    public void setOrderFormLeft(String[] sOrderFormLeft) {
        ORDER_FORM_LEFT = sOrderFormLeft;
    }

    //Get table title
    public String[] getTableTitle() {
        return TABLE_TITLE;
    }

    //Set table title
    public void setTableTitle(String[] sTableTitle) {
        TABLE_TITLE = sTableTitle;
    }

    //Get table item
    public String[][] getTableItem() {
        return TABLE_ITEM;
    }

    //Set table item
    public void setTableItem(String[][] sTableItem) {
        TABLE_ITEM = sTableItem;
    }

    //Get color of item in table TNX
    public Color getColorItemTNX() {
        return COLOR_ITEM_TNX;
    }

    //Set color of item in table TNX
    public void setColorItemTNX(Color cColor) {
        COLOR_ITEM_TNX = cColor;
    }

    //Get font item bold tnx
    public Font getFontItemBoldTNX() {
        return FONT_ITEM_BOLD_TNX;
    }

    //Set font item bold tnx
    public void setFontItemBoldTNX(Font fFont) {
        FONT_ITEM_BOLD_TNX = fFont;
    }

    //Get font item tnx
    public Font getFontItemTNX() {
        return FONT_ITEM_TNX;
    }

    //Set font item tnx
    public void setVATLabelFirst(String sNode) {
        VAT_LABEL_FIRST = sNode;
    }

    //Get is view total
    public String getVATLabelFirst() {
        return VAT_LABEL_FIRST;
    }

    //Set font item tnx
    public void setVATLabelSecond(String sNode) {
        VAT_LABEL_SECOND = sNode;
    }

    //Get is view total
    public String getVATLabelSecond() {
        return VAT_LABEL_SECOND;
    }

    //Set font item tnx
    public void setVATTextFirst(String sNode) {
        VAT_TEXT_FIRST = sNode;
    }

    //Get is view total
    public String getVATTextFirst() {
        return VAT_TEXT_FIRST;
    }

    //Set font item tnx
    public void setVATTextSecond(String sNode) {
        VAT_TEXT_SECOND = sNode;
    }

    //Get is view total
    public String getVATTextSecond() {
        return VAT_TEXT_SECOND;
    }

    //Set font vat
    public void setShowVAT(boolean bFlag) {
        SHOW_VAT = bFlag;
    }

    //Get is vat
    public boolean getShowVAT() {
        return SHOW_VAT;
    }

    //Set font item tnx
    public void setFontItemTNX(Font fFont) {
        FONT_ITEM_TNX = fFont;
    }

    //Get is view total
    public String getTurnOffAcrobat() {
        return TURN_OFF_ACROBAT;
    }

    //Set is view total
    public void setTurnOffAcrobat(String bFlag) {
        TURN_OFF_ACROBAT = bFlag;
    }

    //Get is view total
    public boolean getIsShowAmount() {
        return IS_SHOW_AMOUNT;
    }

    //Set is view total
    public void setIsShowAmount(boolean bIs) {
        IS_SHOW_AMOUNT = bIs;
    }

    //Get is view total
    public boolean getIsViewTotal() {
        return IS_VIEW_TOTAL;
    }

    //Set is view total
    public void setIsViewTotal(boolean bIs) {
        IS_VIEW_TOTAL = bIs;
    }

    //Get is rows total
    public int[] getRowsTotal() {
        return ROW_TOTAL;
    }

//Set color Background of table title
    public void setOrderFormBorderTable(Color cColor) {
        ORDER_FORM_BORDER_TABLE = cColor;
    }

    //Get color Background of table title
    public Color setOrderFormBorderTable() {
        return ORDER_FORM_BORDER_TABLE;
    }

    //Set color Background of table title
    public void setColorBackgroundTableTitle(Color cColor) {
        COLOR_BACKGROUND_TABLE_TITLE = cColor;
    }

    //Get color Background of table title
    public Color getColorBackgroundTableTitle() {
        return COLOR_BACKGROUND_TABLE_TITLE;
    }

    //Set color Background of table
    public void setColorBackgroundTable(Color cColor) {
        COLOR_BACKGROUND_TABLE = cColor;
    }

    //Get color Background of table
    public Color getColorBackgroundTable() {
        return COLOR_BACKGROUND_TABLE;
    }

    //Set color Background of total
    public void setColorBorderTable(Color cColor) {
        COLOR_BORDER_TABLE = cColor;
    }

    //Get color Background of total
    public Color getColorBorderTable() {
        return COLOR_BORDER_TABLE;
    }

    //Set color Background of total
    public void setColorBackgroundTotal(Color cColor) {
        COLOR_BACKGROUND_TOTAL = cColor;
    }

    //Get color Background of total
    public Color getColorBackgroundTotal() {
        return COLOR_BACKGROUND_TOTAL;
    }

    //Set format column
    public void setFormatColumn(String[] sFormat) {
        FORMAT_COLUMN = sFormat;
    }

    //Get format column
    public String[] getFormatColumn() {
        return FORMAT_COLUMN;
    }

    //Set is rows total
    public void setRowsTotal(int[] iTotal) {
        ROW_TOTAL = iTotal;
    }

    //Set ID TNX
    public void setIDTNX(String sID) {
        ID_TNX = sID;
    }

    //Get ID TNX
    public String getIDTNX() {
        return ID_TNX;
    }

    //Get is rows total
    public String getTotalName() {
        return TOTAL_NAME;
    }

    //Set is rows total
    public void setTotalName(String sName) {
        TOTAL_NAME = sName;
    }

    //Get color of table item
    public Color getColorTableItem() {
        return COLOR_TABLE_ITEM;
    }

    //Set color of table item
    public void setColorTableItem(Color cColor) {
        COLOR_TABLE_ITEM = cColor;
    }

    //Get is view total in each page
    public boolean getIsViewTotalInEachPage() {
        return IS_VIEW_TOTAL_IN_PAGE;
    }

    //Set is view total in each page
    public void setIsViewTotalInEachPage(boolean bFlag) {
        IS_VIEW_TOTAL_IN_PAGE = bFlag;
    }

    //Set is print barcode
    public void setIsPrintBarcode(boolean bFlag) {
        IS_PRINT_BARCODE = bFlag;
    }

    //Get is print barcode
    public boolean getIsPrintBarcode() {
        return IS_PRINT_BARCODE;
    }

    //Set is print order form
    public void setPrintOrderForm(boolean bFlag) {
        PRINT_ORDER_FORM = bFlag;
    }

    //Get is print order form
    public boolean getPrintOrderForm() {
        return PRINT_ORDER_FORM;
    }

    //Set is group by art number
    public void setIsGroupByArtNumber(boolean bFlag) {
        IS_GROUP_BY_ART_NUMBER = bFlag;
    }

    //Get is group by art number
    public boolean getIsGroupByArtNumber() {
        return IS_GROUP_BY_ART_NUMBER;
    }

    //Set table tnx skew
    public void setTableTNXSkew(int[] iKew) {
        TABLE_TNX_SKEW = iKew;
    }

    //Get table tnx skew
    public int[] getTableTNXSkew() {
        return TABLE_TNX_SKEW;
    }

    //Set group at num
    public void setGroupAt(int iNumRow) {
        GROUP_AT = iNumRow;
    }

    //Get group at num
    public int getGroupAt() {
        return GROUP_AT;
    }

    //Get item list
    public Vector getReceiptID() {
        return ITEM_LIST;
    }

    //Set item list
    public void setReceiptID(Vector sID) {
        ITEM_LIST = sID;
    }

    //Get font of header of TNX
    public Font getFontHeaderTNX() {
        return FONT_HEADER_TNX;
    }

    //Set font of header of TNX
    public void setFontHeaderTNX(Font fFont) {
        FONT_HEADER_TNX = fFont;
    }

    //Get item of type tnx
    public String[][] getItemTNX() {
        return ITEM_INFO_TNX;
    }

    //Set item of type tnx
    public void setItemTNX(String[][] saItem) {
        ITEM_INFO_TNX = saItem;
    }

    //Get
    public String getChangedDue() {
        return CHANGED_DUE;
    }

    //Set
    public void setChangedDue(String sChanged) {
        CHANGED_DUE = sChanged;
    }

    //Get
    public String getAmountTendered() {
        return AMOUNT_TENDERED;
    }

    //Set
    public void setAmountTendered(String sAmount) {
        AMOUNT_TENDERED = sAmount;
    }

    //Get type currency
    public String getTypeCurrency() {
        return TYPE_CURRENCY;
    }

    //Set type currency
    public void setTypeCurrency(String sType) {
        TYPE_CURRENCY = sType;
    }

    //Get receipt id has type TXN
    public boolean getShowDialog() {
        return SHOW_DIALOG;
    }

    //Set receipt id has type TXN
    public void setShowDialog(boolean bFlag) {
        SHOW_DIALOG = bFlag;
    }

    //Get receipt id has type TXN
    public String getReceiptIDTXN() {
        return RECEIPT_ID;
    }

    //Set receipt id has type TXN
    public void setReceiptIDTXN(String sID) {
        RECEIPT_ID = sID;
    }

    //Set header of receipt has type TXN
    public void setHeaderReceipt(String[] sHeader) {
        HEADER_RECEIPT = sHeader;
    }

    //Get header of receipt has type TXN
    public String[] getHeaderReceipt() {
        return HEADER_RECEIPT;
    }

    //Set CustInfo of receipt has type TXN
    public void setCustInfo(String sCustInfo) {
        CUST_INFO = sCustInfo;
    }

    //Get CustInfo of receipt has type TXN
    public String getCustInfo() {
        return CUST_INFO;
    }

    //Set max size table tnx
    public void setTotalTNX(String[] sTotal) {
        TOTAL_TNX = sTotal;
    }

    //Get max size table tnx
    public int getDelayBeforePrint() {
        return DELAY_BEFORE_PRINT;
    }

    //Set max size table tnx
    public void setDelayBeforePrint(int iTime) {
        DELAY_BEFORE_PRINT = iTime;
    }

//Get max size table tnx
    public int getDelayAfterPrint() {
        return DELAY_AFTER_PRINT;
    }

    //Set max size table tnx
    public void setDelayAfterPrint(int iTime) {
        DELAY_AFTER_PRINT = iTime;
    }

    //Get max size table tnx
    public String[] getTotalTNX() {
        return TOTAL_TNX;
    }

    //Set max size table tnx
    public void setAlightItemTNX(String[] sAlign) {
        ALIGN_ITEM_TNX = sAlign;
    }

    //Get max size table tnx
    public String[] getAlightItemTNX() {
        return ALIGN_ITEM_TNX;
    }

    //Set max size table tnx
    public void setMaxSizeTableTNX(int iSize) {
        MAX_SIZE_TABLE_TNX = iSize;
    }

    //Get max size table tnx
    public int getMaxSizeTableTNX() {
        return MAX_SIZE_TABLE_TNX;
    }

    //Set total group name
    public void setTotalGroupName(String sName) {
        TOTAL_GROUP_NAME = sName;
    }

    //Get total group name
    public String getTotalGroupName() {
        return TOTAL_GROUP_NAME;
    }

    //Get is delete file after print
    public boolean getIsMagin() {
        return IS_MAGIN;
    }

    //Set is delete file after print
    public void setIsMagin(boolean bFlag) {
        IS_MAGIN = bFlag;
    }

    //Get is delete file after print
    public boolean getDeleteFileAfterPrint() {
        return IS_DELETE_FILE_AFTER_PRINT;
    }

    //Set is delete file after print
    public void setDeleteFileAfterPrint(boolean bFlag) {
        IS_DELETE_FILE_AFTER_PRINT = bFlag;
    }

    //Set is delete file after print
    public void setMainFrame(FrameMainSim fr) {
        this.mainFrame = fr;
    }

    //Delete some file deleteFiles("c:/test/", ".gif");
    public void deleteFiles(String d, String e) {
        ExtensionFilter filter = new ExtensionFilter(e);
        File dir = new File(d);

        String[] list = dir.list(filter);
        File file;
        if (list.length == 0) {
            return;
        }

        for (int i = 0; i < list.length; i++) {
            file = new File(d + list[i]);
            boolean isdeleted = file.delete();
        }
    }
}


class ExtensionFilter implements FilenameFilter {
    private String extension;
    public ExtensionFilter(String extension) {
        this.extension = extension;
    }

    public boolean accept(File dir, String name) {
        return (name.endsWith(extension));
    }
}
