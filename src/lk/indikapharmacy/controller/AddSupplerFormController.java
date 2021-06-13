package lk.indikapharmacy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.indikapharmacy.bo.BOFactory;
import lk.indikapharmacy.bo.custom.SupplerBO;
import lk.indikapharmacy.dao.custom.SupplierDAO;
import lk.indikapharmacy.dto.SupplerDTO;
import lk.indikapharmacy.view.tm.SupplerTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddSupplerFormController implements Initializable {

    //public TableView supplierTabelModel;
    public JFXTextField txtSuppId;
    public JFXTextField txtSuppName;
    public JFXTextField txtSuppAddress;
    public JFXTextField txtSuppEmail;
    public JFXTextField txtSuppContact;
    public TableView<SupplerTM> tblSuppler;
    public JFXButton btnDeleteSupplier;

    SupplerBO supBO = (SupplerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize table
        tblSuppler.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("suppID"));
        tblSuppler.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblSuppler.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSuppler.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblSuppler.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));

        //set suppler details to table
        try {
            getSupplerDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //onClick to table
        tblSuppler.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtSuppId.setText(newValue.getSuppID());
            txtSuppName.setText(newValue.getName());
            txtSuppAddress.setText(newValue.getAddress());
            txtSuppEmail.setText(newValue.getEmail());
            txtSuppContact.setText(newValue.getContact());

        });

        //autoGenaratesupplier id from the database
        genarateSupplierID();
    }

    private void genarateSupplierID() {
        try {
            String lastSupplierID = supBO.getLastSupplierID();
            if (lastSupplierID != null){
                lastSupplierID = lastSupplierID.split("[A-Z]")[1];
                lastSupplierID = "S"+ (Integer.parseInt(lastSupplierID)+1);
                txtSuppId.setText(lastSupplierID);
            }else {
                txtSuppId.setText("Supplier Id not fond");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getSupplerDetails() throws Exception {
        ArrayList<SupplerDTO> allSupplier = supBO.getAllSupplier();
        ObservableList<SupplerTM> list = FXCollections.observableArrayList();
        for (SupplerDTO s : allSupplier) {
            list.add(new SupplerTM(s.getSupplyID(),s.getAddress(),s.getSupplyName(),s.getEmail(),s.getContact()));
        }
        tblSuppler.setItems(list);
    }

    public void addSuppOnAction(ActionEvent actionEvent) throws Exception {
        try {
            String suppId = txtSuppId.getText();
            String suppAddress = txtSuppAddress.getText();
            String suppName = txtSuppName.getText();
            String suppEmail = txtSuppEmail.getText();
            String suppContact= txtSuppContact.getText();

            SupplerDTO supDTO = new SupplerDTO(suppId,suppAddress,suppName,suppEmail,suppContact);
            boolean addSupplier = supBO.addSupplier(supDTO);
            if (addSupplier) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!", ButtonType.OK).show();
                //txtCustId.requestFocus();
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again!", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //load new data with table
        getSupplerDetails();

        //genarate supplirID to the supplerid text filed
        genarateSupplierID();
    }

    public void updateSuppOnAction(ActionEvent actionEvent) throws Exception {
        try {
            String upSuppId = txtSuppId.getText();
            String upSuppAddress = txtSuppAddress.getText();
            String upSuppName = txtSuppName.getText();
            String upSuppEmail = txtSuppEmail.getText();
            String upSuppContact= txtSuppContact.getText();

            SupplerDTO upSupDTO = new SupplerDTO(upSuppId,upSuppAddress,upSuppName,upSuppEmail,upSuppContact);

            boolean updateSupplier = supBO.updateSupplier(upSupDTO);
            if (updateSupplier) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Update Successfully....", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Update Unsuccessfully....", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSupplerDetails();
    }

    public void deleteSuppOnAction(ActionEvent actionEvent) throws Exception {
        String suppIdText = txtSuppId.getText();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Supplier");
            alert.setHeaderText("Are you Sure You Want To Delete This Supplier");
            alert.setResizable(false);
            alert.setContentText("Select okay or cancel this alert.");
            alert.showAndWait();

            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            //cheack before deleting supplier validation
            if (button == ButtonType.OK) {
                //System.out.println("Ok pressed");
                boolean deleteSupplier = supBO.deleteSupplier(suppIdText);
                if (deleteSupplier){
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Delete Successfully....", ButtonType.OK).show();
                }else {
                    new Alert(Alert.AlertType.WARNING, "Supplier Delete Unsuccessfully....", ButtonType.OK).show();
                }
            } else {
                alert.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //reload supplier table after deleting a supplier
        getSupplerDetails();
    }

    public void seracSuppOnAction(ActionEvent actionEvent) {
        String suppIdText = txtSuppId.getText();
        try {
            SupplerDTO supplerDTO = supBO.searchSupplier(suppIdText);
            if (supplerDTO != null){
                txtSuppId.setText(supplerDTO.getSupplyID());
                txtSuppAddress.setText(supplerDTO.getAddress());
                txtSuppName.setText(supplerDTO.getSupplyName());
                txtSuppEmail.setText(supplerDTO.getEmail());
                txtSuppContact.setText(supplerDTO.getContact());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
