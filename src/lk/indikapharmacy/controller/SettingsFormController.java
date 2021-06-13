package lk.indikapharmacy.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.indikapharmacy.bo.BOFactory;
import lk.indikapharmacy.bo.custom.UserBO;
import lk.indikapharmacy.dto.UsersDTO;

public class SettingsFormController {
    public JFXTextField txtUserId;
    public JFXTextField txtName;
    public JFXTextField txtUserName;
    public JFXPasswordField txtUserPassword;

    UserBO useBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);


    public void addUserOnAction(ActionEvent actionEvent) {
        String userIdText = txtUserId.getText();
        String userNameText = txtName.getText();
        String useNameText = txtUserName.getText();
        String userPasswordText = txtUserPassword.getText();

        UsersDTO usersDTO = new UsersDTO(userIdText,userNameText,useNameText,userPasswordText);

        try {
            boolean userAdd = useBO.addUser(usersDTO);
            if (userAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"User Added Succssefully....", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"User Added Unsuccssefully....", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateUserOnAction(ActionEvent actionEvent) {
    }

    public void deleteUserOnAction(ActionEvent actionEvent) {
    }

    public void serachUserOnAction(ActionEvent actionEvent) {
    }
}
