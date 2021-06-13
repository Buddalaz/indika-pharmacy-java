import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Indika Pharmacy Medicine");
        Image image = new Image("/lk/indikapharmacy/assets/logo-pharmacy-png-favpng-wqN3RkFh6GsviFssfVsmdecmi.jpg");
        primaryStage.getIcons().add(image);
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("lk/indikapharmacy/view/LoginForm.fxml"))));
        primaryStage.show();
    }
}
