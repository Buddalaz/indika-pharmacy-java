package lk.indikapharmacy.dto;

public class MedicineDetailsDTO {

    private String orderID;
    private String itemID;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;

    public MedicineDetailsDTO() {
    }

    public MedicineDetailsDTO(String orderID, String itemID, String description, double unitPrice, int qty, double total) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
