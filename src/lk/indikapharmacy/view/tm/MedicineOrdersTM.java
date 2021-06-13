package lk.indikapharmacy.view.tm;

public class MedicineOrdersTM {
    private String medicineOrderID;
    private String medicineOrderDate;
    private String medicineCustID;
    private String userID;

    public MedicineOrdersTM() {
    }

    public MedicineOrdersTM(String medicineOrderID, String medicineOrderDate, String medicineCustID, String userID) {
        this.medicineOrderID = medicineOrderID;
        this.medicineOrderDate = medicineOrderDate;
        this.medicineCustID = medicineCustID;
        this.userID = userID;
    }

    public String getMedicineOrderID() {
        return medicineOrderID;
    }

    public void setMedicineOrderID(String medicineOrderID) {
        this.medicineOrderID = medicineOrderID;
    }

    public String getMedicineOrderDate() {
        return medicineOrderDate;
    }

    public void setMedicineOrderDate(String medicineOrderDate) {
        this.medicineOrderDate = medicineOrderDate;
    }

    public String getMedicineCustID() {
        return medicineCustID;
    }

    public void setMedicineCustID(String medicineCustID) {
        this.medicineCustID = medicineCustID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
