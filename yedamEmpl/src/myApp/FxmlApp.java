package myApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FxmlApp extends Application {

   @Override
   public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("AppMain.Student/Root.fxml"));
      
      // 컨테이너를 scene의 매개값으로
      Scene scene = new Scene(root);

      // Stage의 매개값으로 Scene을 달아줌.
      primaryStage.setScene(scene);
      primaryStage.show();

   }
   
   public static void main(String[] args) {
      Application.launch(args);
   }

}