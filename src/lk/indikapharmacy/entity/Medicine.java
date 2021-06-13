package lk.indikapharmacy.entity;

public class Medicine {

    private String itemID;
    private String description;
    private String productDate;
    private String expireDate;
    private double unitPrice;
    private int qtyOnHand;

    public Medicine() {
    }

    public Medicine(String itemID, String description, String productDate, String expireDate, double unitPrice, int qtyOnHand) {
        this.itemID = itemID;
        this.description = description;
        this.productDate = productDate;
        this.expireDate = expireDate;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
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

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}

