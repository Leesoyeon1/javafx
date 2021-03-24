package myApp.Student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AppMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Student/Root.fxml"));

        Scene scene = new Scene(root, 600, 400);

       

       

        primaryStage.setScene(scene);

        primaryStage.show();

       

}



public static void main(String[] args) {

        launch(args);

}

		
	}


