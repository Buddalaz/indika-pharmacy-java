package lk.indikapharmacy.entity;

public class Suppler {

    private String supplyID;
    private String address;
    private String name;
    private String email;
    private String contact;

    public Suppler() {
    }

    public Suppler(String supplyID, String address, String name, String email, String contact) {
        this.supplyID = supplyID;
        this.address = address;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public String getSupplyID() {
        return supplyID;
    }

    public void setSupplyID(String supplyID) {
        this.supplyID = supplyID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
