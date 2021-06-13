package lk.indikapharmacy.dto;

public class MedicineReturnDTO {

    private String returnID;
    private String date;
    private String orderID;
    private String itemID;
    private int qty;

    public MedicineReturnDTO() {
    }

    public MedicineReturnDTO(String returnID, String date, String orderID, String itemID, int qty) {
        this.returnID = returnID;
        this.date = date;
        this.orderID = orderID;
        this.itemID = itemID;
        this.qty = qty;
    }

    public String getReturnID() {
        return returnID;
    }

    public void setReturnID(String returnID) {
        this.returnID = returnID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
}
