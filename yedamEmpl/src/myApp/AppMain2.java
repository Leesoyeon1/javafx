package myApp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppMain2 extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Label label = new Label();// 라벨
		label.setText("Hello, javaFx");
		label.setFont(new Font(50));

		Button button = new Button();// 버튼
		button.setText("확인");
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
		});

		VBox root = new VBox();// 컨테이너안에 라벨,버튼 담기
		root.setPrefWidth(350);// 넓이지정
		root.setPrefHeight(150);// 높이지정
		root.setAlignment(Pos.CENTER);// 좌우정렬
		root.setSpacing(20);

		root.getChildren().add(label);
		root.getChildren().add(button);
		// 컨테이너를 Scene의 매개값으로
		Scene scene = new Scene(root);
		// Stage의 매개값으로 Scene 달아줌
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}
