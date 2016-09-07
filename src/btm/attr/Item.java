package btm.attr;
import java.util.Currency;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: BTM International Software</p>
 * @author
 * @version 1.0
 */

public class Item {
  private String id="";
  private String description="";
  private String artNo="";
  private String attribute1=""; // Tuan Anh
  private String attribute2="";// Tuan Anh
  private double unitPrice=0;
//  private int quantity=0;
  //an other variable 'quantityR' with double type, used for HomeApp too
  private double quantity = 0;//use when return item (negative number)
  private double markdownAmount=0;
  private double discountAmount=0;
  private double discQty = 0;
  private double markdownPer =0;
  private double amountDue=0;
  private String markdownReason="";
  private long custID=-1;
  private double VAT = 0;
  private long saleID=-1;

  public Item(){
  }
  public Item(String id,String artNo,String desc,String attr2,double quantity,double price){
        this.id= id;
        this.artNo=artNo;
        this.description= desc;
        this.attribute2=attr2;//size
        this.unitPrice =price;
        this.quantity =quantity;
      }



//  public Item(String id,String desc,double price,int quantity,double markdown,String markdownReason,double discount,double amountdue,long custID,String att1,String att2){
//    this.id= id;
//    this.description= desc;
//    this.attribute1=att1;
//    this.attribute2=att2;
//    this.unitPrice =price;
//    this.quantity =quantity;
//    this.markdownAmount= markdown;
//    this.markdownReason =markdownReason;
//    this.discountAmount=discount;
//    this.amountDue = amountdue;
//    this.custID = custID;
//  }
//init Item with type of 'quantity' is double, used for Home App
  public Item(String id,String desc,double price,double quantity,double markdown,String markdownReason,double discount,double amountdue,long custID,String att1,String att2){
    this.id= id;
    this.description= desc;
    this.attribute1= att1; //TA
    this.attribute2=att2;  //TA
    this.unitPrice =price;
    this.quantity = quantity;
    this.markdownAmount= markdown;
    this.markdownReason =markdownReason;
    this.discountAmount=discount;
    this.amountDue = amountdue;
    this.custID = custID;
  }


  //BTMProd
  public Item(String id,String desc,double price,double quantity,double markdown,String markdownReason,double discount,double amountdue,long custID,String att1,String att2, double VAT,long saleID){
    this.id= id;
    this.description= desc;
    this.attribute1= att1; //TA
    this.attribute2=att2;  //TA
    this.unitPrice =price;
    this.quantity = quantity;
    this.markdownAmount= markdown;
    this.markdownReason =markdownReason;
    this.discountAmount=discount;
    this.amountDue = amountdue;
    this.custID = custID;
    this.VAT = VAT;
    this.saleID = saleID;
  }

//BTMProd
  //init Item with type of 'quantity' is double, discount qty, markdown per, used for Home App
    public Item(String id,String artNo, String desc,double price,double quantity,double disc_qty,double markdown,double markdownPer,String markdownReason,double discount,double amountdue,long custID,String att1,String att2,long saleID){
      this.id= id;
      this.artNo=artNo;
      this.description= desc;
      this.attribute1= att1; //TA
      this.attribute2=att2;  //TA
      this.unitPrice =price;
      this.quantity = quantity;
      this.discQty=disc_qty;
      this.markdownPer=markdownPer;
      this.markdownAmount= markdown;
      this.markdownReason =markdownReason;
      this.discountAmount=discount;
      this.amountDue = amountdue;
      this.custID = custID;
      this.saleID = saleID;
  }
  public Item(String id,String desc,double price,double quantity,double markdown,String markdownReason,double discount,double amountdue,long custID,String artNo,String att1,String att2){
    this.id= id;
    this.description= desc;
    this.unitPrice =price;
    this.quantity = quantity;
    this.markdownAmount= markdown;
    this.markdownReason =markdownReason;
    this.discountAmount=discount;
    this.amountDue = amountdue;
    this.custID = custID;
    this.artNo= artNo; //TA
    this.attribute1= att1; //TA
    this.attribute2=att2;  //TA

  }

  public Item(String id,String desc,double price,double quantity,double markdown,String markdownReason,double discount,double amountdue,long custID){
    this.id= id;
    this.description= desc;
    this.unitPrice =price;
    this.quantity =quantity;
    this.markdownAmount= markdown;
    this.markdownReason =markdownReason;
    this.discountAmount=discount;
    this.amountDue = amountdue;
    this.custID = custID;
  }


//  public Item(String id,String desc,double price,double quantityR,double markdown,String markdownReason,double discount,double amountdue,long custID){
//    this.id= id;
//    this.description= desc;
//    this.unitPrice =price;
//    this.quantityR = quantityR;
//    this.markdownAmount= markdown;
//    this.markdownReason =markdownReason;
//    this.discountAmount=discount;
//    this.amountDue = amountdue;
//    this.custID = custID;
//  }

//===============================GET
  public String getId(){
      return id;
  }
  public String getDescription(){
      return description;
  }

  public String getArtNo(){
      return artNo;
  }
  public String getattribute1(){
     return attribute1;
 }
 public String getattribute2(){
      return attribute2;
  }

  public double getunitPrice(){
      return unitPrice;
  }
  public double getMarkdownAmount(){
      return markdownAmount;
  }
  public String getMarkdownReason(){
      return markdownReason;
  }
  public double getDiscountAmount(){
      return discountAmount;
  }
  public double getMarkdownPer(){
      return markdownPer;
  }
//  public int getQuantity(){
//      return quantity;
//  }
  public double getQuantity(){
    return quantity;
  }
//for Home
  public double getDiscountQuantity(){
    return discQty;
  }
  public double getAmountDue(){
        return amountDue;
  }
  public long getCustID(){
    return custID;
  }
  public long getSaleID(){
    return saleID;
  }

  public double getVAT(){
    return VAT;
  }

//===============================SET
  public void setId(String id){
      this.id=id;
  }
  public void setDescription(String desc){
      this.description=desc;
  }
  public void setAttribute1(String att1){
    this.attribute1=att1;
  }
  public void setAttribute2(String att2){
      this.attribute2=att2;
  }

  public void setUnitPrice(double price){
      this.unitPrice=price;
  }
  public void setMarkdownAmount(double markdown){
      this.markdownAmount=markdown;
  }
  public void setMarkdownReason(String markdownReason){
      this.markdownReason =markdownReason;
  }
  public void setDiscountAmount(double discount){
      this.discountAmount=discount;
  }
//  public void setQuantity(int quantity){
//      this.quantity=quantity;
//  }
  public void setQuantity(double quantity){
    this.quantity = quantity;
  }
  public void setAmountDue(double amountDue){
        this.amountDue=amountDue;
  }
  public void setCustID(long custID){
    this.custID = custID;
  }
  public void setSaleID(long saleID){
    this.saleID = saleID;
  }

  public void setVAT(double VAT){
    this.VAT = VAT;
  }
}

