package lk.indikapharmacy.entity;

public class Customer {
    private String custID;
    private String custName;
    private String phoneNumber;
    private String custAddress;

    public Customer() {
    }

    public Customer(String custID, String custName, String phoneNumber, String custAddress) {
        this.custID = custID;
        this.custName = custName;
        this.phoneNumber = phoneNumber;
        this.custAddress = custAddress;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
}
