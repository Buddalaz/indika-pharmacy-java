package lk.indikapharmacy.controller;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.indikapharmacy.bo.BOFactory;
import lk.indikapharmacy.bo.custom.UserBO;

import java.io.IOException;

public class LoginFormController {

    public JFXTextField txtUserName;
    public AnchorPane root;
    public JFXButton btnCancel;
    public JFXPasswordField txtPass;
    public JFXButton btnLogin;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        String userName = txtUserName.getText();
        String userPass = txtPass.getText();

        try {
            if (!txtUserName.getText().isEmpty() || !txtPass.getText().isEmpty()) {
                boolean validation = userBO.validateUser(userName, userPass);
                if (validation) {
                    if (userName.equalsIgnoreCase("admin")) {
                        Stage window = (Stage) this.root.getScene().getWindow();
                        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/indikapharmacy/view/AdminPanelForm.fxml"))));
                        window.centerOnScreen();
                    } else {
                        Stage window = (Stage) this.root.getScene().getWindow();
                        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/indikapharmacy/view/DashBoardForm.fxml"))));
                        window.centerOnScreen();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!", ButtonType.OK).show();
                }
            } else {
                new Shake(txtUserName).play();
                new Shake(txtPass).play();
                new Alert(Alert.AlertType.WARNING, "Plaese Enter UserName And Password", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void cancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void userTxtOnAction(ActionEvent actionEvent) {
        txtPass.requestFocus();
    }

    public void passTxtOnAction(ActionEvent actionEvent) {
        btnLogin.requestFocus();
    }
}
