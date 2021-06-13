package lk.indikapharmacy.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.indikapharmacy.bo.BOFactory;
import lk.indikapharmacy.bo.custom.MedicineCatogeryBO;
import lk.indikapharmacy.dto.CustomDTO;
import lk.indikapharmacy.view.tm.MediSearchCatoTM;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SerachMedicineFormController implements Initializable {

    public JFXTextField txtMediName;
    public TableView<MediSearchCatoTM> tblMediCatoGorySearch;

    MedicineCatogeryBO medicineCatogeryBO = (MedicineCatogeryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEDICINECATOGERY);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblMediCatoGorySearch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("catogoryID"));
        tblMediCatoGorySearch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("mediID"));
        tblMediCatoGorySearch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("mediName"));
        tblMediCatoGorySearch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mediUseDescription"));
        tblMediCatoGorySearch.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("catogoryName"));

    }

    public void mediNameSearchOnAction(ActionEvent actionEvent) {
        String mediNameText = txtMediName.getText();
        try {
            ArrayList<CustomDTO> customDTOS = medicineCatogeryBO.searchOrderFromID(mediNameText);
            ObservableList<MediSearchCatoTM> mediSearchCatoTMS = FXCollections.observableArrayList();
            for (CustomDTO customDTO : customDTOS) {
                mediSearchCatoTMS.add(new MediSearchCatoTM(customDTO.getCatogoryID(),customDTO.getMediID(),customDTO.getMediName(),customDTO.getMediUseDescription(),
                        customDTO.getCatogoryName()));
            }
            tblMediCatoGorySearch.setItems(mediSearchCatoTMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
