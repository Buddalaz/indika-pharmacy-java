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
import javafx.scene.paint.Paint;
import lk.indikapharmacy.bo.BOFactory;
import lk.indikapharmacy.bo.custom.CustomerBO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.SupplerDTO;
import lk.indikapharmacy.view.tm.CustomerTM;
import lk.indikapharmacy.view.tm.SupplerTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddCoustomerFormController implements Initializable {

    public JFXTextField txtCustId;
    public JFXTextField txtCustName;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCustPhoneNumber;
    public JFXButton btnAddCustomer;
    public JFXButton btnUpdateCustomer;
    public JFXButton btnDeleteCustomer;
    public JFXButton btnSearchCustomer;
    public TableView<CustomerTM> tblCustomer;

    CustomerBO ctBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            //initzializ the table customer
            tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("custID"));
            tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("custName"));
            tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("custPhoneNumber"));
            tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("custAddress"));

            //get selected row to textField
            tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                txtCustId.setText(newValue.getCustID());
                txtCustName.setText(newValue.getCustName());
                txtCustPhoneNumber.setText(newValue.getCustPhoneNumber());
                txtCustAddress.setText(newValue.getCustAddress());
            });

            //getALLCustomer details
            getAllCustomerDetails();

            genarateAutoCustomerID();

    }

    private void genarateAutoCustomerID() {
        try {
            String lastCustomerID = ctBO.getLastCustomerID();
            if (lastCustomerID != null) {
                lastCustomerID = lastCustomerID.split("[A-Z]")[1];
                lastCustomerID = "C" + (Integer.parseInt(lastCustomerID) + 1);
                txtCustId.setText(lastCustomerID);
            }else {
                txtCustId.setText("Customer ID Not fund..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllCustomerDetails() {
        try {
            ArrayList<CustomerDTO> allCustomers = ctBO.getAllCustomers();
            ObservableList<CustomerTM> list = FXCollections.observableArrayList();
            for (CustomerDTO s : allCustomers) {
                list.add(new CustomerTM(s.getCustID(),s.getCustName(),s.getPhoneNumber(),s.getCustAddress()));
            }
            tblCustomer.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addIdOnAction(ActionEvent actionEvent) {
        txtCustName.requestFocus();
    }

    public void addNameOnAction(ActionEvent actionEvent) {
        txtCustAddress.requestFocus();
    }

    public void addressOnAction(ActionEvent actionEvent) {
        txtCustPhoneNumber.requestFocus();
    }

    public void phoneOnAction(ActionEvent actionEvent) {
        btnAddCustomer.requestFocus();
    }

    public void btnCustAddOnAction(ActionEvent actionEvent) throws Exception {
        //add customer to database
        String custID = txtCustId.getText();
        String custName = txtCustName.getText();
        String custPhone = txtCustPhoneNumber.getText();
        String custAddress = txtCustAddress.getText();

        CustomerDTO custDTO = new CustomerDTO(custID,custName,custPhone,custAddress);

        if(Pattern.compile("^[a-z|A-Z]{1,}$").matcher(custName).matches()){
            txtCustPhoneNumber.requestFocus();
            if(Pattern.compile("^[0-9]{1,10}$").matcher(custPhone).matches()){
                txtCustAddress.requestFocus();
                //if (Pattern.compile("^[A-Z|a-z|0-9|/|0-9(,)[a-z]|[0-9]]$").matcher(custAddress).matches()){

                    boolean add = ctBO.addCoustomer(custDTO);
                    if (add) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved!", ButtonType.OK).show();
                        txtCustId.requestFocus();
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!", ButtonType.OK).show();
                    }

                //}else {
                    //txtCustAddress.setUnFocusColor(Paint.valueOf("red"));
                //}
            }else {
                txtCustPhoneNumber.setUnFocusColor(Paint.valueOf("red"));
            }
        }else {
            txtCustName.setUnFocusColor(Paint.valueOf("red"));
            txtCustPhoneNumber.requestFocus();
        }

        getAllCustomerDetails();
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) { //update customer
        String custID = txtCustId.getText();
        String custName = txtCustName.getText();
        String custPhone = txtCustPhoneNumber.getText();
        String custAddress = txtCustAddress.getText();

        CustomerDTO custDTO = new CustomerDTO(custID,custName,custPhone,custAddress);
        try {
            if(Pattern.compile("^[a-z|A-Z]{1,}$").matcher(custName).matches()){
                txtCustPhoneNumber.requestFocus();
                if(Pattern.compile("^[0-9]{1,10}$").matcher(custPhone).matches()){
                    txtCustAddress.requestFocus();
                    //if (Pattern.compile("^[A-Z|a-z|0-9|/|0-9(,)[a-z]|[0-9]]$").matcher(custAddress).matches()){

                    boolean update = ctBO.updateCustomer(custDTO);
                    if (update) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Customer Update Succusfully.....", ButtonType.OK).show();
                    }else{
                        new Alert(Alert.AlertType.WARNING, "Can't find Customer 0r Customer Update Unsuccusfully.....", ButtonType.OK).show();
                    }

                    //}else {
                    //txtCustAddress.setUnFocusColor(Paint.valueOf("red"));
                    //}
                }else {
                    txtCustPhoneNumber.setUnFocusColor(Paint.valueOf("red"));
                }
            }else {
                txtCustName.setUnFocusColor(Paint.valueOf("red"));
                txtCustPhoneNumber.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getAllCustomerDetails();
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) { //delete customer
        String custID = txtCustId.getText();
        try {
            boolean ustdelete = ctBO.deleteCustomer(custID);
            if (ustdelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Delete Succusfully.....", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Delete Unsuccusfully.....", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchCustomerOnAction(ActionEvent actionEvent) { //search customer in database
        String custIdText = txtCustId.getText();
        try {
            CustomerDTO customerDTO = ctBO.searchCustomer(custIdText);
            if (customerDTO != null){
                txtCustId.setText(customerDTO.getCustID());
                txtCustName.setText(customerDTO.getCustName());
                txtCustAddress.setText(customerDTO.getCustAddress());
                txtCustPhoneNumber.setText(customerDTO.getPhoneNumber());
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "No Such Customer Found..", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
