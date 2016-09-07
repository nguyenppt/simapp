package btm.attr;

import java.sql.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author
 * @version 1.0
 */

public class Transaction {
  /*  */
  private long seqNo; // N(6)
  private long transID;// N(10)
  private int storeID;// N(6)
  private int promoID;// N(10)
  private int tenderID;// N(4)
  private String empID;// N(10)
  private Date transDate;// DATE
  private String custID;// N(10)
  private float amount;// N(18,4)
  private String transTypeID;// VA(1)
  private float discAmt;  // N(18,4)
  private int registeryID;// N(10)
  private String saleID;// N(10)
  public Transaction() {
  }
  public Transaction(long transID,int storeID,String empID,String custID,int registeryID,String saleID){
    this.transID =transID;
    this.storeID =storeID;
    this.empID =empID;
    this.custID =custID;
    this.registeryID =registeryID;
    this.saleID =saleID;
  }


  public Transaction(long seqNo,long transID,int storeID,int promoID,
                     int tenderID,String empID,Date transDate,String custID,
                     float amount,String transTypeID,float discAmt,int registeryID,String saleID){
    this.seqNo =seqNo;
    this.transID =transID;
    this.storeID =storeID;
    this.promoID =promoID;
    this.tenderID =tenderID;
    this.empID =empID;
    this.transDate =transDate;
    this.custID =custID;
    this.amount =amount;
    this.transTypeID =transTypeID;
    this.discAmt =discAmt;
    this.registeryID =registeryID;
    this.saleID =saleID;
  }


  //===============================GET
  public long getSeqNo(){
      return seqNo;
  }

  public long getTrans_ID(){
      return transID;
  }

  public long getStore_ID(){
      return storeID;
    }

    public long getPromo_ID(){
      return promoID;
    }

    public long getTender_ID(){
      return tenderID;
    }

    public String getEmpl_ID(){
      return empID;
    }

    public Date getTrans_Date(){
      return transDate;
    }

    public String getCust_ID(){
      return custID;
    }
    public String getSale_ID(){
      return saleID;
    }

    public float getAmount(){
      return amount;
    }

    public String getTrans_Type_ID(){
      return transTypeID;
    }

    public float getDisc_Amt(){
       return discAmt;
    }

    public int getRegister_ID(){
       return registeryID;
    }


    //===============================SET



    public void setSeqNo(long seqNo){
       this.seqNo=seqNo;
    }

    public void setTrans_ID(long transID){
       this.transID=transID;
    }

    public void setStore_ID(int storeID){
       this.storeID=storeID;
    }

    public void setPromo_ID(int promoID){
       this.promoID=promoID;
    }


    public void setTender_ID(int tenderID){
       this.tenderID=tenderID;
    }

    public void setEmpl_ID(String empID){
       this.empID=empID;
    }

    public void setTrans_Date(Date transDate){
       this.transDate=transDate;
    }

    public void setDisc_Amt(float discAmt){
       this.discAmt=discAmt;
    }

    public void setCust_ID(String custID){
       this.custID=custID;
    }
    public void setSale_ID(String saleID){
       this.saleID=saleID;
    }

    public void setAmount(float amount){
       this.amount=amount;
    }

    public void setTrans_Type_ID(String transTypeID){
       this.transTypeID=transTypeID;
    }

    public void setRegister_ID(int registeryID){
       this.registeryID=registeryID;
    }


}
