package lk.indikapharmacy.dto;

public class MedicineCatogoryDTO {

    private String catID;
    private String itemID;
    private String description;
    private String catogryName;

    public MedicineCatogoryDTO() {
    }

    public MedicineCatogoryDTO(String catID, String itemID, String description, String catogryName) {
        this.catID = catID;
        this.itemID = itemID;
        this.description = description;
        this.catogryName = catogryName;
    }

    public String getCatID() { return catID; }

    public void setCatID(String catID) {this.catID = catID; }

    public String getItemID() { return itemID; }

    public void setItemID(String itemID) { this.itemID = itemID; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCatogryName() { return catogryName; }

    public void setCatogryName(String catogryName) { this.catogryName = catogryName; }
}
