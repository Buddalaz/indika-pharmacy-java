package lk.indikapharmacy.dto;

public class StockDTO {

    private String stockID;
    private String batchID;
    private String supplyID;
    private String itemID;
    private int qty;
    private double payment;

    public StockDTO() {
    }

    public StockDTO(String stockID, String batchID, String supplyID, String itemID, int qty, double payment) {
        this.stockID = stockID;
        this.batchID = batchID;
        this.supplyID = supplyID;
        this.itemID = itemID;
        this.qty = qty;
        this.payment = payment;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getSupplyID() {
        return supplyID;
    }

    public void setSupplyID(String supplyID) {
        this.supplyID = supplyID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
