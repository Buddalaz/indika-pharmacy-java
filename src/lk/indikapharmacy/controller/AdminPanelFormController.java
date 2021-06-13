package lk.indikapharmacy.controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AdminPanelFormController {

    public AnchorPane context;
    public JFXButton btnBack;
    public TextField txtCurrentDate;


    public void initialize() throws IOException {
        loadDefault();
        genarateTime();
    }

    private void genarateTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            txtCurrentDate.setText(LocalTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        txtCurrentDate.setText(LocalDate.now().toString());

    }


    private void loadDefault() throws IOException {
        setUi("AdminDashBoaedForm");
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddItemForm");
    }

    private void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/indikapharmacy/view/" + location + ".fxml")));
        new FadeIn(context).play();
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        loadDefault();
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddSupplerForm");
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Indika Pharmacy Medicine");
        Image image = new Image("/lk/indikapharmacy/assets/logo-pharmacy-png-favpng-wqN3RkFh6GsviFssfVsmdecmi.jpg");
        primaryStage.getIcons().add(image);

        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    public void settingsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SettingsForm");
    }
}
