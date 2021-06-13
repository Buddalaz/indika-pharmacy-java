package lk.indikapharmacy.entity;

public class SupplierReturn {

    private String supplyID;
    private int qty;
    private String descriptin;
    private String returnDate;

    public SupplierReturn() {
    }

    public SupplierReturn(String supplyID, int qty, String descriptin, String returnDate) {
        this.supplyID = supplyID;
        this.qty = qty;
        this.descriptin = descriptin;
        this.returnDate = returnDate;
    }

    public String getSupplyID() {
        return supplyID;
    }

    public void setSupplyID(String supplyID) {
        this.supplyID = supplyID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescriptin() {
        return descriptin;
    }

    public void setDescriptin(String descriptin) {
        this.descriptin = descriptin;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
