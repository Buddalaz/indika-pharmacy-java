package lk.indikapharmacy.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.indikapharmacy.bo.BOFactory;
import lk.indikapharmacy.bo.custom.CustomerBO;
import lk.indikapharmacy.bo.custom.PurcharseOrderBO;
import lk.indikapharmacy.db.DBConnection;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.MedicineDTO;
import lk.indikapharmacy.dto.MedicineDetailsDTO;
import lk.indikapharmacy.dto.MedicineOrdersDTO;
import lk.indikapharmacy.view.tm.OrderDetailTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DefaultFormController implements Initializable {

    public JFXDatePicker txtCurrentDate;
    public JFXTextField txtOrderId;
    public JFXComboBox cmbCustomerId;
    public JFXTextField txtItemId;
    public JFXTextField txtItemDescription;
    public JFXTextField txtQty;
    public JFXTextField txtProductDate;
    public JFXTextField txtExpireDate;
    public JFXTextField txtUnitPrice;
    public JFXComboBox<String> cmbItemCode;
    public TableView<OrderDetailTM> tblOrderDetail;
    public JFXTextField txtUserID;
    public TextField txtOrderTotal;
    public TextField txtOrderCash;
    public TextField txtOrderBalance;
    ObservableList<OrderDetailTM> data = FXCollections.observableArrayList();
    private double total;
    private String oldOrderID;


    PurcharseOrderBO purcOrdBO = (PurcharseOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHARSEORDER);

    public void initialize(URL location, ResourceBundle resources) {
        //genarate current date in to datePicker
        genarateDate();

        //load customerID to combobox
        genarateCustomerID();

        //load MedicineID to comboBox
        genarateMedicineID();

        //initialize orderdetail table
        tblOrderDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemID"));
        tblOrderDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("desc"));
        tblOrderDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("tot"));

        genarateOrderID();
    }

    private void genarateOrderID() {
        try {
            String lastOrderID = purcOrdBO.getLastOrderID();
            if (lastOrderID != null){
                lastOrderID = lastOrderID.split("[A-Z]")[1];
                lastOrderID = "D" + (Integer.parseInt(lastOrderID) + 1);
                txtOrderId.setText(lastOrderID);
            }else {
                txtOrderId.setText("Order Can't Continue..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genarateMedicineID() {
        try {
            ArrayList<MedicineDTO> allMedicine = purcOrdBO.getAllMedicine();
            ArrayList<String> medicineList = new ArrayList<>();
            for (MedicineDTO medi : allMedicine) {
                medicineList.add(medi.getItemID());
            }
            cmbItemCode.setItems(FXCollections.observableArrayList(medicineList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genarateCustomerID() {
        try {
            ArrayList<CustomerDTO> allCustomers = purcOrdBO.getAllCustomers();
            ArrayList<String> customerList = new ArrayList<>();
            for (CustomerDTO cust : allCustomers) {
                customerList.add(cust.getCustID());
            }
            cmbCustomerId.setItems(FXCollections.observableArrayList(customerList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genarateDate() {
        txtCurrentDate.setValue(LocalDate.now());
    }

    public void addOrderDetailOnAction(ActionEvent actionEvent) {
        String itemID = txtItemId.getText();
        String itemDesc = txtItemDescription.getText();
        double itemUnitPrice = Double.parseDouble(txtUnitPrice.getText());
        int itemQty = Integer.parseInt(txtQty.getText());
        double itemTotal;

        itemTotal = itemQty * itemUnitPrice;

        OrderDetailTM orderDetailTM = new OrderDetailTM(itemID,itemDesc,itemUnitPrice,itemQty,itemTotal);

        data.add(new OrderDetailTM(itemID,itemDesc,itemUnitPrice,itemQty,itemTotal));


        //tblOrderDetail.setItems(FXCollections.observableArrayList(orderDetailTM)); did work
        //tblOrderDetail.setItems(data.add(new OrderDetailTM(itemID,itemDesc,itemUnitPrice,itemQty,itemTotal)));

        tblOrderDetail.getItems().add(orderDetailTM);
        /*ObservableList<OrderDetailTM> items = tblOrderDetail.getItems();
        items.get(0).*/

        caltotal();

    }

    public void selectItemOnAction(ActionEvent actionEvent) {

        String selectedItem = cmbItemCode.getSelectionModel().getSelectedItem();
        try {
            MedicineDTO medicineDTO = purcOrdBO.searchMedicine(selectedItem);
            if (medicineDTO!=null) {
                txtItemId.setText(medicineDTO.getItemID());
                txtItemDescription.setText(medicineDTO.getDescription());
                txtProductDate.setText(medicineDTO.getProductDate());
                txtExpireDate.setText(medicineDTO.getExpireDate());
                txtUnitPrice.setText(medicineDTO.getUnitPrice()+"");
            }else{
                new Alert(Alert.AlertType.WARNING, "Medicine Not Found", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
        String ordID = txtOrderId.getText();
        String purchDate = txtCurrentDate.getValue().toString();
        String custID = cmbCustomerId.getSelectionModel().getSelectedItem().toString();
        String useID = txtUserID.getText();
        double ordTotal = Double.parseDouble(txtOrderTotal.getText());
        double ordCash = Double.parseDouble(txtOrderCash.getText());
        double ordBalance = Double.parseDouble(txtOrderBalance.getText());

        ArrayList<MedicineDetailsDTO> detailList = new ArrayList<>();
        //ObservableList<OrderDetailTM> items = tblOrderDetail.getItems();
        for (OrderDetailTM s : data) {
            MedicineDetailsDTO medicineDetailsDTO = new MedicineDetailsDTO(ordID,s.getItemID(),s.getDesc(),s.getUnitPrice(),s.getQty(),s.getTot());
            detailList.add(medicineDetailsDTO);
        }

        MedicineOrdersDTO medicineOrdersDTO = new MedicineOrdersDTO(ordID,purchDate,custID,useID,ordTotal,ordCash,ordBalance,detailList);
        System.out.println("Hcheck :"+medicineOrdersDTO);

        try {
            if (cashPayments()) {
                boolean purchesAdd = purcOrdBO.placeOrder(medicineOrdersDTO);
                if (purchesAdd) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Purches Succssfully", ButtonType.OK).show();
                }else {
                    new Alert(Alert.AlertType.WARNING, "Order Purches UnSuccessful Cheack it again....", ButtonType.OK).show();
                }
            }else {
               new Alert(Alert.AlertType.WARNING,"Cash Should be Greater than total....", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        oldOrderID = ordID;
        genarateOrderID();
    }

    public boolean cashPayments(){
        double total = Double.parseDouble(txtOrderTotal.getText());
        double cash = Double.parseDouble(txtOrderCash.getText());
        return (cash > total) ? true : false;
    }

    private void caltotal(){
        total=0;
        for (OrderDetailTM item : tblOrderDetail.getItems()) {
            total+=item.getTot();
        }
        //convert double value to round 2
        total=total*100;
        total=(double)((int)total);
        total=total/100;
        txtOrderTotal.setText(Double.toString(total));
    }


    public void calculateBalance(ActionEvent actionEvent) {
        double orderTotal = Double.parseDouble(txtOrderTotal.getText());
        double custCash = Double.parseDouble(txtOrderCash.getText());
        double cashBalance = custCash - orderTotal;

        txtOrderBalance.setText(Double.toString(cashBalance));
    }

    public void printOnAction(ActionEvent actionEvent) throws JRException {
        Map<String,Object> orderRpt = new HashMap<>();
        orderRpt.put("orderId", oldOrderID);
        InputStream is = this.getClass().getResourceAsStream("/lk/indikapharmacy/report/rptOrderDetails.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(is);
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, orderRpt, DBConnection.getInstance().getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JasperViewer.viewReport(jasperPrint);
    }
}
