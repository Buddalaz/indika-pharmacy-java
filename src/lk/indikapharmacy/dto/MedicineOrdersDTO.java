package lk.indikapharmacy.dto;

import java.util.ArrayList;

public class MedicineOrdersDTO {

    private String orderID;
    private String orderDate;
    private String custID;
    private String userID;
    private double total;
    private double cash;
    private double balance;
    ArrayList<MedicineDetailsDTO> medicineDetailsDTOS;

    public MedicineOrdersDTO() {
    }

    public MedicineOrdersDTO(String orderID, String orderDate, String custID, String userID, double total, double cash, double balance, ArrayList<MedicineDetailsDTO> medicineDetailsDTOS) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.custID = custID;
        this.userID = userID;
        this.total = total;
        this.cash = cash;
        this.balance = balance;
        this.medicineDetailsDTOS = medicineDetailsDTOS;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<MedicineDetailsDTO> getMedicineDetailsDTOS() {
        return medicineDetailsDTOS;
    }

    public void setMedicineDetailsDTOS(ArrayList<MedicineDetailsDTO> medicineDetailsDTOS) {
        this.medicineDetailsDTOS = medicineDetailsDTOS;
    }
}
