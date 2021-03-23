package myApp.viewPackage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.BoardVO;
import common.InputBoardVO;
import common.InputDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class BoardController implements Initializable {

	@FXML
	TextField boardNo, title;
	@FXML
	TableView<BoardVO> tableView;
	@FXML
	TextArea contents;
	@FXML
	ComboBox<String> publicity;
	@FXML
	DatePicker exitDate;
	@FXML
	Button updateBtn, deleteBtn, addBtn;

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		deleteBtn.setOnAction(e -> deleteBtnAction(e));
		addBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				addBtnAction(arg0);

			}

		});

		ObservableList<BoardVO> list = InputDAO.boardList();

		tableView.setPrefWidth(450);// prefWidth="30"
		tableView.setLayoutX(30);// setLayoutX

		TableColumn<BoardVO, Integer> tcBoardNo = //
				(TableColumn<BoardVO, Integer>) tableView.getColumns().get(0);
		tcBoardNo.setCellValueFactory(new PropertyValueFactory<BoardVO, Integer>("boardNo"));

		tcBoardNo.setPrefWidth(70);
		TableColumn<BoardVO, String> tcTitle = //
				(TableColumn<BoardVO, String>) tableView.getColumns().get(1);
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<BoardVO, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<BoardVO, String> param) {
				return param.getValue().titleProperty();
			}

		});

		TableColumn<BoardVO, String> tcpub = new TableColumn<BoardVO, String>("공개");
		tcpub.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("publicity"));
		tableView.getColumns().add(tcpub);

		tableView.setItems(list);

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BoardVO>() {

			@Override
			public void changed(ObservableValue<? extends BoardVO> arg0, BoardVO oldVal, BoardVO newVal) {
				if (newVal != null) {
					boardNo.setText(String.valueOf(newVal.getBoardNo()));
					boardNo.setText(String.valueOf(newVal.getBoardNo()));
					title.setText(newVal.getTitle());
					publicity.setValue(newVal.getPublicity());
					contents.setText(newVal.getContents());
					exitDate.setValue(LocalDate.parse(newVal.getExitDate()));
				}
			}

		});

	}

	public void updateBtnAction(ActionEvent e) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(boardNo.getText()));
		vo.setContents(contents.getText());
		vo.setExitDate(exitDate.getValue().toString());
		vo.setPublicity(publicity.getValue());
		InputDAO.updateBoard(vo);

		tableView.setItems(InputDAO.boardList());

	}

	public void deleteBtnAction(ActionEvent e) {// 삭제기능
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(boardNo.getText()));
		InputDAO bo = new InputDAO();
		bo.deleteBoard(vo);
		tableView.setItems(InputDAO.boardList());
	}
	

	public void addBtnAction(ActionEvent e) {
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);

		try {
			AnchorPane ap = FXMLLoader.load(getClass().getResource("BoardAdd.fxml"));
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.show();

			Button btnReg = (Button) ap.lookup("#btnReg");
			btnReg.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtTitle = (TextField) ap.lookup("#txtTitle");
					PasswordField tp = (PasswordField) ap.lookup("#txtPassword");
					ComboBox cf = (ComboBox) ap.lookup("#comboPublic");
					DatePicker dp = (DatePicker) ap.lookup("#dateExit");
					TextArea ta = (TextArea) ap.lookup("#txtContent");

					InputBoardVO vo = new InputBoardVO();
					vo.setTitle(txtTitle.getText());
					vo.setPasswd(tp.getText());
					vo.setPublicity(cf.getValue().toString());
					vo.setExitDate(dp.getValue().toString());
					vo.setContents(ta.getText());

					InputDAO.insertBoard(vo);
					tableView.setItems(InputDAO.boardList());
					stage.close();
				}

			});

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
