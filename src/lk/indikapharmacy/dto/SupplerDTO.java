package lk.indikapharmacy.dto;

public class SupplerDTO {

    private String supplyID;
    private String address;
    private String supplyName;
    private String email;
    private String contact;

    public SupplerDTO() {
    }

    public SupplerDTO(String supplyID, String address, String supplyName, String email, String contact) {
        this.supplyID = supplyID;
        this.address = address;
        this.supplyName = supplyName;
        this.email = email;
        this.contact = contact;
    }

    public String getSupplyID() { return supplyID; }

    public void setSupplyID(String supplyID) { this.supplyID = supplyID; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getSupplyName() { return supplyName; }

    public void setSupplyName(String supplyName) { this.supplyName = supplyName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getContact() { return contact; }

    public void setContact(String contact) { this.contact = contact; }
}
