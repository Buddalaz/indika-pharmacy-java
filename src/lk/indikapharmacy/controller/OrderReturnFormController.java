package lk.indikapharmacy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.indikapharmacy.bo.BOFactory;
import lk.indikapharmacy.bo.custom.MedicineBO;
import lk.indikapharmacy.bo.custom.OrderReturnBO;
import lk.indikapharmacy.bo.custom.PurcharseOrderBO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.MedicineOrdersOnlyDTO;
import lk.indikapharmacy.dto.MedicineReturnDTO;
import lk.indikapharmacy.view.tm.CustomerTM;
import lk.indikapharmacy.view.tm.MedicineOrdersTM;
import lk.indikapharmacy.view.tm.OrderDetailTM;
import lk.indikapharmacy.view.tm.OrderReturnTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderReturnFormController implements Initializable {

    public TableView<OrderReturnTM> tblOrderReturn;
    public JFXTextField txtReturnID;
    public JFXTextField txtOrderID;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXDatePicker txtOrderReturnDate;
    public JFXTextField txtItemID;
    public JFXTextField txtSerachWord;
    public TableView<MedicineOrdersTM> tblAllOrder;
    public JFXTextField txtQuentity;

    ObservableList<OrderReturnTM> data = FXCollections.observableArrayList();
    ObservableList<MedicineOrdersTM> medicineOrdersTMS = FXCollections.observableArrayList();

    OrderReturnBO orderReturnBO = (OrderReturnBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERRETURN);
    PurcharseOrderBO purBO = (PurcharseOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHARSEORDER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initilaizing table return orders
        tblOrderReturn.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("returnID"));
        tblOrderReturn.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblOrderReturn.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblOrderReturn.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        tblOrderReturn.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));


        tblOrderReturn.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtReturnID.setText(newValue.getReturnID());
            txtOrderReturnDate.setAccessibleText(newValue.getDate());
            txtOrderID.setText(newValue.getOrderID());
            txtItemID.setText(newValue.getItemID());
            txtQuentity.setText(newValue.getQty()+"");
        });

        //genarate current date to datepicker
        genarateDate();

        //auto genarate returnOrderID
        genarateReturnID();

        //filterdData();
    }

    private void genarateReturnID() {
        try {
            String lastOrderReturnID = orderReturnBO.getLastOrderReturnID();
            if (lastOrderReturnID != null) {
                lastOrderReturnID = lastOrderReturnID.split("[A-Z]")[1];
                lastOrderReturnID = "R" + (Integer.parseInt(lastOrderReturnID) + 1);
                txtReturnID.setText(lastOrderReturnID);
            }else {
                txtReturnID.setText("Customer ID Not fund..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void genarateDate() {
        txtOrderReturnDate.setValue(LocalDate.now());
    }


    public void AddOrderReturnOnAction(ActionEvent actionEvent) {

        String returnIDText = txtReturnID.getText();
        String returnDate = txtOrderReturnDate.getValue().toString();
        String orderIDText = txtOrderID.getText();
        String itemIDText = txtItemID.getText();
        int iteQty = Integer.parseInt(txtQuentity.getText());

        OrderReturnTM orderReturnTM = new OrderReturnTM(returnIDText, returnDate, orderIDText, itemIDText, iteQty);
        data.add(new OrderReturnTM(returnIDText,returnDate,orderIDText,itemIDText,iteQty));

        //adding data to table
        tblOrderReturn.getItems().add(orderReturnTM);

        //sending dto values to BO
        MedicineReturnDTO mediRetDTO = new MedicineReturnDTO(returnIDText, returnDate, orderIDText, itemIDText, iteQty);
        try {
            boolean addOrderReturn = orderReturnBO.addOrderReturn(mediRetDTO);
            if (addOrderReturn) {
                new Alert(Alert.AlertType.CONFIRMATION, "Return Added Succssesfuly", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Return Added Succssesfuly", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        genarateReturnID();
    }

    public void UpdateOrderReturnOnAction(ActionEvent actionEvent) {
        String returnIDText = txtReturnID.getText();
        String returnDate = txtOrderReturnDate.getValue().toString();
        String orderIDText = txtOrderID.getText();
        String itemIDText = txtItemID.getText();
        int iteQty = Integer.parseInt(txtQuentity.getText());

        MedicineReturnDTO mediRetDTO = new MedicineReturnDTO(returnIDText, returnDate, orderIDText, itemIDText, iteQty);

        try {
            boolean updateOrderReturn = orderReturnBO.updateOrderReturn(mediRetDTO);
            if (updateOrderReturn){
                new Alert(Alert.AlertType.CONFIRMATION, "Return Order Update Succssesfuly", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void filterdData(){
        FilteredList<MedicineOrdersTM> filteredList = new FilteredList<>(medicineOrdersTMS, e -> true);

        txtSerachWord.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (employee.getMedicineOrderID().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else if (employee.getMedicineCustID().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (String.valueOf(employee.getUserID()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else {
                    return false;
                }

            });
        });
        SortedList<MedicineOrdersTM> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(tblAllOrder.comparatorProperty());
        tblAllOrder.setItems(sortedData);
    }
}
