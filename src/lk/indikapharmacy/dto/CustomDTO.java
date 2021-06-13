package lk.indikapharmacy.dto;

public class CustomDTO {
    private String catogoryID;
    private String mediID;
    private String mediName;
    private String mediUseDescription;
    private String catogoryName;

    public CustomDTO() {
    }

    public CustomDTO(String catogoryID, String mediID, String mediName, String mediUseDescription, String catogoryName) {
        this.catogoryID = catogoryID;
        this.mediID = mediID;
        this.mediName = mediName;
        this.mediUseDescription = mediUseDescription;
        this.catogoryName = catogoryName;
    }

    public String getCatogoryID() {
        return catogoryID;
    }

    public void setCatogoryID(String catogoryID) {
        this.catogoryID = catogoryID;
    }

    public String getMediID() {
        return mediID;
    }

    public void setMediID(String mediID) {
        this.mediID = mediID;
    }

    public String getMediName() {
        return mediName;
    }

    public void setMediName(String mediName) {
        this.mediName = mediName;
    }

    public String getMediUseDescription() {
        return mediUseDescription;
    }

    public void setMediUseDescription(String mediUseDescription) {
        this.mediUseDescription = mediUseDescription;
    }

    public String getCatogoryName() {
        return catogoryName;
    }

    public void setCatogoryName(String catogoryName) {
        this.catogoryName = catogoryName;
    }
}
