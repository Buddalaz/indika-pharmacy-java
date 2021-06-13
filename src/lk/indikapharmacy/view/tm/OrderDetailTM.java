package lk.indikapharmacy.view.tm;

public class OrderDetailTM {
        private String itemID;
        private String desc;
        private double unitPrice;
        private int qty;
        private double tot;

    public OrderDetailTM() {
    }

    public OrderDetailTM(String itemID, String desc, double unitPrice, int qty, double tot) {
        this.itemID = itemID;
        this.desc = desc;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.tot = tot;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }


    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "itemID='" + itemID + '\'' +
                ", desc='" + desc + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", tot=" + tot +
                '}';
    }
}
