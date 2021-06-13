package lk.indikapharmacy.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.indikapharmacy.bo.BOFactory;
import lk.indikapharmacy.bo.custom.CustomerBO;
import lk.indikapharmacy.bo.custom.MedicineBO;
import lk.indikapharmacy.bo.custom.PurcharseOrderBO;
import lk.indikapharmacy.bo.custom.StockBO;
import lk.indikapharmacy.dto.CustomerDTO;
import lk.indikapharmacy.dto.StockDTO;
import lk.indikapharmacy.view.tm.CustomerTM;
import lk.indikapharmacy.view.tm.StockTM;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminDashBoaedFormController implements Initializable{

    public Label lblMedicineCount;
    public Label lblCustomerCount;
    public Label lblOrderCount;
    public JFXTextField txtStockID;
    public JFXTextField txtItemID;
    public JFXTextField txtBatchID;
    public JFXTextField txtQty;
    public JFXTextField txtSuppId;
    public JFXTextField txtPayment;
    public TableView<StockTM> tblStock;

    MedicineBO mediBO = (MedicineBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEDICINE);
    CustomerBO custBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    PurcharseOrderBO purBO = (PurcharseOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PURCHARSEORDER);
    StockBO stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("stockID"));
        tblStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("batchID"));
        tblStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("supplyID"));
        tblStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemID"));
        tblStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblStock.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("payment"));

        tblStock.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtStockID.setText(newValue.getStockID());
            txtBatchID.setText(newValue.getBatchID());
            txtSuppId.setText(newValue.getSupplyID());
            txtItemID.setText(newValue.getItemID());
            txtQty.setText(newValue.getQty()+"");
            txtPayment.setText(newValue.getPayment()+"");
        });

        //get all count from the item table
        getItemCount();

        //get all count from the customer table
        getCoustomerCount();

        //get all count from the order table
        getOrderCount();

        getAllStockDetails();
    }

    private void getAllStockDetails() {
        try {
            ArrayList<StockDTO> allStock = stockBO.getAllStock();
            ObservableList<StockTM> stockList = FXCollections.observableArrayList();
            for (StockDTO stockDTO : allStock) {
                stockList.add(new StockTM(stockDTO.getStockID(),stockDTO.getBatchID(),stockDTO.getSupplyID(),stockDTO.getItemID(),stockDTO.getQty(),stockDTO.getPayment()));
            }
            tblStock.setItems(stockList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*ArrayList<CustomerDTO> allCustomers = ctBO.getAllCustomers();
        ObservableList<CustomerTM> list = FXCollections.observableArrayList();
        for (CustomerDTO s : allCustomers) {
            list.add(new CustomerTM(s.getCustID(),s.getCustName(),s.getPhoneNumber(),s.getCustAddress()));
        }
        tblCustomer.setItems(list);*/
    }

    private void getOrderCount() {
        try {
            String orderCount = purBO.orderCount();
            lblOrderCount.setText(orderCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCoustomerCount() {
        try {
            String customerCount = custBO.customerCount();
            lblCustomerCount.setText(customerCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getItemCount() {
        try {
            String itemCount = mediBO.itemCount();
            lblMedicineCount.setText(itemCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStockOnAction(ActionEvent actionEvent) {
        String stId = txtStockID.getText();
        String stBaID = txtBatchID.getText();
        String stSuppID = txtSuppId.getText();
        String itemIDText = txtItemID.getText();
        int stQty = Integer.parseInt(txtQty.getText());
        double stPay = Double.parseDouble(txtPayment.getText());

        StockDTO stockDTO = new StockDTO(stId,stBaID,stSuppID,itemIDText,stQty,stPay);
        try {
            boolean addStock = stockBO.addStock(stockDTO);
            if (addStock){
                new Alert(Alert.AlertType.CONFIRMATION,"Stock Details Added SuccessFull...", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Stock Details Added UnsuccessFull...", ButtonType.CANCEL).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String stockIDText = txtStockID.getText();
        try {
            boolean deleteStock = stockBO.deleteStock(stockIDText);
            if (deleteStock){
                new Alert(Alert.AlertType.CONFIRMATION,"Stock Details Delete SuccessFull...", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Stock Details Delete UnsuccessFull...", ButtonType.CANCEL).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String stId = txtStockID.getText();
        String stBaID = txtBatchID.getText();
        String stSuppID = txtSuppId.getText();
        String itemIDText = txtItemID.getText();
        int stQty = Integer.parseInt(txtQty.getText());
        double stPay = Double.parseDouble(txtPayment.getText());

        StockDTO stockDTO = new StockDTO(stId,stBaID,stSuppID,itemIDText,stQty,stPay);
        try {
            boolean updateStock = stockBO.updateStock(stockDTO);
            if (updateStock){
                new Alert(Alert.AlertType.CONFIRMATION,"Stock Details Update SuccessFull...", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Stock Details Update UnsuccessFull...", ButtonType.CANCEL).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String stockIDText = txtStockID.getText();
        try {
            StockDTO stockDTO = stockBO.searchStock(stockIDText);
            if (stockDTO != null){
                txtStockID.setText(stockDTO.getStockID());
                txtBatchID.setText(stockDTO.getBatchID());
                txtSuppId.setText(stockDTO.getSupplyID());
                txtItemID.setText(stockDTO.getItemID());
                txtQty.setText(stockDTO.getQty()+"");
                txtPayment.setText(stockDTO.getPayment()+"");
            }else {
                new Alert(Alert.AlertType.WARNING,"Stock Details Can't find...", ButtonType.CANCEL).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
