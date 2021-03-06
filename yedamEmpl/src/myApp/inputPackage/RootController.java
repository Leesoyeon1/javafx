package myApp.inputPackage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.InputBoardVO;
import common.InputDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RootController implements Initializable {
	@FXML
	private TableView<score>

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

	public void handleBtnRegAction(ActionEvent e) {
		String title = txtTitle.getText();
		System.out.println("title:" + title);

		String password = txtPassword.getText();
		System.out.println("password:" + password);

		String strPublic = comboPublic.getValue();
		System.out.println("public:" + strPublic);

		String localDate = exitDate.getValue().toString();
//		if(localDate !=null) {
//			System.out.println("dateExit: " + localDate.toString());
//			
//		}
		String content = txtContent.getText();
		System.out.println("content:" + content);

		InputBoardVO bo = new InputBoardVO();
		bo.setContents(content);
		bo.setPasswd(password);
		bo.setPublicity(strPublic);
		bo.setExitDate(exitDate);//?????
		bo.setTitle(title);

		InputDAO.insertBoard(bo);

	}

	public void handleBtnCancelAction(ActionEvent e) {
		Platform.exit();
	}
}
