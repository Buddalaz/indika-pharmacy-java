package lk.indikapharmacy.view.tm;

public class CustomerTM {

    private String custID;
    private String custName;
    private String custPhoneNumber;
    private String custAddress;

    public CustomerTM() {
    }

    public CustomerTM(String custID, String custName, String custPhoneNumber, String custAddress) {
        this.custID = custID;
        this.custName = custName;
        this.custPhoneNumber = custPhoneNumber;
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

    public String getCustPhoneNumber() {
        return custPhoneNumber;
    }

    public void setCustPhoneNumber(String custPhoneNumber) {
        this.custPhoneNumber = custPhoneNumber;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
}
