package lk.indikapharmacy.dto;

public class MedicineOrdersOnlyDTO {
    private String orderID;
    private String orderDate;
    private String custID;
    private String userID;

    public MedicineOrdersOnlyDTO() {
    }

    public MedicineOrdersOnlyDTO(String orderID, String orderDate, String custID, String userID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.custID = custID;
        this.userID = userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
