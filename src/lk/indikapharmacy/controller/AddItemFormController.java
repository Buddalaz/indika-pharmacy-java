package lk.indikapharmacy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.indikapharmacy.bo.BOFactory;
import lk.indikapharmacy.bo.custom.MedicineBO;
import lk.indikapharmacy.bo.custom.MedicineCatogeryBO;
import lk.indikapharmacy.dto.CustomDTO;
import lk.indikapharmacy.dto.MedicineCatogoryDTO;
import lk.indikapharmacy.dto.MedicineDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddItemFormController implements Initializable {

    public JFXTextField txtMediId;
    public JFXTextField txtMediDescription;
    public JFXTextField txtMediUnitPrice;
    public JFXTextField txtMediQtyOnHand;
    public JFXButton btnAddMedicine;
    public JFXDatePicker txtMediProductDate;
    public JFXDatePicker txtMediExpireDate;
    public JFXTextField txtCatoId;
    public JFXTextField txtItemID;
    public JFXTextField txtCatoDescription;
    public JFXTextField txtCatogoryName;


    MedicineBO medi = (MedicineBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEDICINE);
    MedicineCatogeryBO medicineCatogeryBO = (MedicineCatogeryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEDICINECATOGERY);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genarateMediID();
    }

    private void genarateMediID() {
        try {
            String mediID = medi.genarateMediID();
            if (mediID != null){
                mediID = mediID.split("[A-Z]")[1];
                mediID = "M" + (Integer.parseInt(mediID)+1);
                txtMediId.setText(mediID);
            }else {
                txtMediId.setText("medicine id can't genarated");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void mediIdOnAction(ActionEvent actionEvent) {
        txtMediDescription.requestFocus();
    }

    public void mediDescOnAction(ActionEvent actionEvent) {
        txtMediProductDate.requestFocus();;
    }

    public void mediUnitOnAction(ActionEvent actionEvent) {
        txtMediQtyOnHand.requestFocus();
    }

    public void mediQtyOnHandOnAction(ActionEvent actionEvent) {
        btnAddMedicine.requestFocus();
    }

    public void addMedicineOnAction(ActionEvent actionEvent) throws Exception {
        String mediID = txtMediId.getText();
        String mediDescription = txtMediDescription.getText();
        String prodDate = txtMediProductDate.getValue().toString();
        String expireDate = txtMediExpireDate.getValue().toString();
        double unitPrice = Double.parseDouble(txtMediUnitPrice.getText());
        int qty = Integer.parseInt(txtMediQtyOnHand.getText());

        MedicineDTO mediDTO = new MedicineDTO(mediID,mediDescription,prodDate,expireDate,unitPrice,qty);
        boolean add = medi.addMedicine(mediDTO);
        if (add) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!", ButtonType.OK).show();
            txtMediId.requestFocus();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again!", ButtonType.OK).show();
        }

        genarateMediID();
    }

    public void updateMediOnAction(ActionEvent actionEvent) {
        String mediID = txtMediId.getText();
        String mediDescription = txtMediDescription.getText();
        String prodDate = txtMediProductDate.getValue().toString();
        String expireDate = txtMediExpireDate.getValue().toString();
        double unitPrice = Double.parseDouble(txtMediUnitPrice.getText());
        int qty = Integer.parseInt(txtMediQtyOnHand.getText());

        MedicineDTO mediDTO = new MedicineDTO(mediID,mediDescription,prodDate,expireDate,unitPrice,qty);
        try {
            boolean updateItem = medi.updateItem(mediDTO);
            if (updateItem){
                new Alert(Alert.AlertType.CONFIRMATION, "Update Succssfully...", ButtonType.OK).show();
                txtMediId.requestFocus();
            }else {
                new Alert(Alert.AlertType.WARNING, "Update Succssfully...", ButtonType.OK,ButtonType.CLOSE).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteMediOnAction(ActionEvent actionEvent) {
        String mediIdText = txtMediId.getText();
        try {
            boolean deleteItem = medi.deleteItem(mediIdText);
            if (deleteItem){
                new Alert(Alert.AlertType.CONFIRMATION, "Delete Succssfully...", ButtonType.OK).show();
                txtMediId.requestFocus();
            }else {
                new Alert(Alert.AlertType.WARNING, "Unable to Delete Medicine", ButtonType.OK,ButtonType.CLOSE).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleaerMediOnAction(ActionEvent actionEvent) {
        txtMediDescription.clear();
        txtMediProductDate.setAccessibleText("");
        txtMediExpireDate.setAccessibleText("");
        txtMediUnitPrice.clear();
        txtMediQtyOnHand.clear();
    }

    public void addCatoOnAction(ActionEvent actionEvent) {
        String mediCatID = txtCatoId.getText();
        String itemIDText = txtItemID.getText();
        String mediCatoDesc = txtCatoDescription.getText();
        String mediCatoName = txtCatogoryName.getText();

        MedicineCatogoryDTO meCoryDTO = new MedicineCatogoryDTO(mediCatID,itemIDText,mediCatoDesc,mediCatoName);
        try {
            boolean addMediCato = medicineCatogeryBO.addMediCatogory(meCoryDTO);
            if (addMediCato){
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine Catogory Added Succssfully...", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Unable to Added Medicine Catogory", ButtonType.OK,ButtonType.CLOSE).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMediCatoOnAction(ActionEvent actionEvent) {
        String mediCatID = txtCatoId.getText();
        String itemIDText = txtItemID.getText();
        String mediCatoDesc = txtCatoDescription.getText();
        String mediCatoName = txtCatogoryName.getText();

        MedicineCatogoryDTO meCoryDTO = new MedicineCatogoryDTO(mediCatID,itemIDText,mediCatoDesc,mediCatoName);
        try {
            boolean mediUpdate = medicineCatogeryBO.updateMediCatogory(meCoryDTO);
            if (mediUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine Catogory Update Succssfully...", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Unable to Update Medicine Catogory", ButtonType.OK,ButtonType.CLOSE).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMediCatoOnAction(ActionEvent actionEvent) {
        String mediCatId = txtCatoId.getText();
        try {
            boolean mediCatoDelete = medicineCatogeryBO.deleteMediCatogory(mediCatId);
            if (mediCatoDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine Catogory Delete Succssfully...", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Unable to Delete Medicine Catogory", ButtonType.OK,ButtonType.CLOSE).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void serachMediOnAction(ActionEvent actionEvent) {
        String mediCatId = txtCatoId.getText();
        try {
            MedicineCatogoryDTO medicineCatogoryDTO = medicineCatogeryBO.searchMediCatogory(mediCatId);
            if (medicineCatogoryDTO != null){
                txtCatoId.setText(medicineCatogoryDTO.getCatID());
                txtItemID.setText(medicineCatogoryDTO.getItemID());
                txtCatoDescription.setText(medicineCatogoryDTO.getDescription());
                txtCatogoryName.setText(medicineCatogoryDTO.getCatogryName());
            }else {
                new Alert(Alert.AlertType.WARNING, "No Such Medicine Catogory Found..", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
