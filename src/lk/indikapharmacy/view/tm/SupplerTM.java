package lk.indikapharmacy.view.tm;

public class SupplerTM {

    private String suppID;
    private String address;
    private String name;
    private String email;
    private String contact;

    public SupplerTM() {
    }

    public SupplerTM(String suppID, String address, String name, String email, String contact) {
        this.suppID = suppID;
        this.address = address;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public String getSuppID() {
        return suppID;
    }

    public void setSuppID(String suppID) {
        this.suppID = suppID;
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
