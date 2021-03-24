package myApp.Student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RootController implements Initializable{
	@FXML
	private TableView<Score> tableview;
	@FXML
	private TableColumn<Score , String> txtName;
	@FXML
	private TableColumn<Score , Integer> txtKor;
	@FXML
	private TableColumn<Score , Integer> txtMath;
	@FXML
	private TableColumn<Score , Integer> txtEng;
	
	ObservableList<Score> data=FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
