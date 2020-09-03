package basic.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	@FXML
	TableView<Student> tableView;
	@FXML
	Button btnAdd, btnBarChart;
	ObservableList<Student> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn<Student, ?> tc = tableView.getColumns().get(0);// 첫번째 칼럼 name칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("korean"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("math"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("english"));
		// 데이터 가져올떄 setItems
		list = FXCollections.observableArrayList();
		// 값을 담는건 add화면에서 list에다 성적 저장
		tableView.setItems(list);

		// 추가버튼.이벤트추가 매개값이 이벤트핸들러
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleBtnAddAction();// 밑에서 호출

			}
		});
		btnBarChart.setOnAction(e->handleBtnAddAction());

	}
	public void handleBtnChartAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
	}

	// 추가화면 보여주는 작업
	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);// 화면을 만듬
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());// 이 화면이 어느 윈도우에 소속되있는지 지정

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
			// 추가화면의 컨트롤러사용
			//추가화면의 저장버튼
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");//id 값을 찾아오는것 lookup
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
					Student student = new Student(txtName.getText(), Integer.parseInt(txtKorean.getText()),
							Integer.parseInt(txtMath.getText()), Integer.parseInt(txtEnglish.getText()));

					list.add(student);
					//추가화면 닫기
					stage.close();

				}
			});
			//추가화면의 취소버튼
			Button btnFormCancel = (Button)parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e->{
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtKorean = (TextField) parent.lookup("#txtKorean");
				TextField txtMath = (TextField) parent.lookup("#txtMath");
				TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
				
				txtName.clear();
				txtKorean.clear();
				txtMath.clear();
				txtEnglish.clear();
				
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
