package basic.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	@FXML
	TableView<Student> tableView;
	@FXML
	Button btnAdd, btnBarChart, selectbutton;
	ObservableList<Student> list;
	Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		TableColumn<Student, ?> tc = tableView.getColumns().get(0);// 첫번째 칼럼 name칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("korean"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("math"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("english"));
		// 데이터 가져올떄 setItems
		list = FXCollections.observableArrayList();
		// 값을 담는건 add화면에서 list에다 성적 저장
		tableView.setItems(list);
		

		// 추가버튼.이벤트추가 매개값이 이벤트핸들러
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				handleBtnAddAction();// 밑에서 호출
			}
		});
		// 차트버튼
		btnBarChart.setOnAction(e -> handleBtnChartAction());
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					String selectedName = tableView.getSelectionModel().getSelectedItem().getName();
					handleDoubleClickAction(selectedName);
				}

			}

		});

	}// end of initiallize

	public void handleDoubleClickAction(String name) {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);

		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(210, 230);

		Label lKorean, lMath, lEnglish;
		TextField tName, tKorean, tMath, tEnglish;
		
		lKorean = new Label("국어");
		lKorean.setLayoutX(35);
		lKorean.setLayoutY(73);

		lMath = new Label("수학");
		lMath.setLayoutX(35);
		lMath.setLayoutY(99);

		lEnglish = new Label("영어");
		lEnglish.setLayoutX(35);
		lEnglish.setLayoutY(132);

		tName = new TextField();
		tName.setPrefWidth(110);
		tName.setLayoutX(72);
		tName.setLayoutY(30);

		tName.setText(name);
		tName.setEditable(false);

		tKorean = new TextField();
		tKorean.setPrefWidth(110);
		tKorean.setLayoutX(72);
		tKorean.setLayoutY(69);

		tMath = new TextField();
		tMath.setPrefWidth(110);
		tMath.setLayoutX(72);
		tMath.setLayoutY(95);

		tEnglish = new TextField();
		tEnglish.setPrefWidth(110);
		tEnglish.setLayoutX(72);
		tEnglish.setLayoutY(128);
		

		Button btnUpdate = new Button("수정");
		btnUpdate.setLayoutX(85);
		btnUpdate.setLayoutY(184);
		
		Button btnClose = new Button("취소");
		btnClose.setLayoutX(130);
		btnClose.setLayoutY(184);

		
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getName().equals(name)) {
						Student student = new Student(name, Integer.parseInt(tKorean.getText()),
								Integer.parseInt(tMath.getText()), Integer.parseInt(tEnglish.getText()));
						list.set(i, student);
					}
				}
				stage.close();
			}
			

		});
		
		btnClose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.close();
				
			}
			
		});
		// 이름 기준으로 국어 수학 영어 점수 화면에 입력
		for (Student stu : list) {
			if (stu.getName().equals(name)) {
				tMath.setText(String.valueOf(stu.getMath()));
				tKorean.setText(String.valueOf(stu.getKorean()));
				tEnglish.setText(String.valueOf(stu.getEnglish()));

			}
		}

		ap.getChildren().addAll(btnUpdate,btnClose, tName, tKorean, tMath, tEnglish, lKorean, lMath, lEnglish);

		Scene scene = new Scene(ap);
		stage.setScene(scene);
		stage.show();
	}
	
//	public ObservableList<Student> getStulist() {
//	      Connection conn = ConnectionDB.getDB();
//	      String sql = "select * from student";
//	      ObservableList<Student> list2 = FXCollections.observableArrayList();
//
//	      try {
//	         PreparedStatement pstmt = conn.prepareStatement(sql);
//	         ResultSet rs = pstmt.executeQuery();
//	         while (rs.next()) {
//	            Student stu = new Student(rs.getString("name"), rs.getInt("korean"),
//	                  rs.getInt("math"), rs.getInt("engilsh"));
//	            list2.add(stu);
//	         }
//	         ;
//	         return list2;
//	      } catch (SQLException e) {
//	         e.printStackTrace();
//	      } finally {
//	         try {
//	            conn.close();
//	         } catch (SQLException e) {
//	            e.printStackTrace();
//	         }
//	      }
//	      return list2;
//	   }
//

	public void handleBtnChartAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		try {
			Parent chart = FXMLLoader.load(getClass().getResource("BarChart.fxml"));
			Scene scene = new Scene(chart);
			stage.setScene(scene);
			stage.show();

			// chart를 가지고 와서 series를 추가해야한다.
			BarChart barChart = (BarChart) chart.lookup("#barChart");
			// 국어인 카테고리 만듬
			XYChart.Series<String, Integer> seriesK = new XYChart.Series<String, Integer>();
			seriesK.setName("국어");

			ObservableList<XYChart.Data<String, Integer>> koreanList = FXCollections.observableArrayList();

			for (int i = 0; i < list.size(); i++) {
				koreanList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getKorean()));
			}

			// 수학인 카테고리 만듬
			XYChart.Series<String, Integer> seriesM = new XYChart.Series<String, Integer>();
			seriesM.setName("수학");

			ObservableList<XYChart.Data<String, Integer>> mathList = FXCollections.observableArrayList();

			for (int i = 0; i < list.size(); i++) {
				mathList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getMath()));
			}

			XYChart.Series<String, Integer> seriesE = new XYChart.Series<String, Integer>();
			seriesE.setName("영어");

			ObservableList<XYChart.Data<String, Integer>> englishList = FXCollections.observableArrayList();

			for (int i = 0; i < list.size(); i++) {
				englishList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getEnglish()));
			}

			seriesK.setData(koreanList);
			barChart.getData().add(seriesK);
			seriesM.setData(mathList);
			barChart.getData().add(seriesM);
			seriesE.setData(englishList);
			barChart.getData().add(seriesE);

			
			Button btnClose = (Button) chart.lookup("#btnClose");// id 값을 찾아오는것 lookup
			btnClose.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					stage.close();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

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
			// 추가화면의 저장버튼
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");// id 값을 찾아오는것 lookup
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
					Student student = new Student(txtName.getText(),
							Integer.parseInt(txtKorean.getText()),
							Integer.parseInt(txtMath.getText()),
							Integer.parseInt(txtEnglish.getText()));

					list.add(student);

					// 추가화면 닫기
					stage.close();

				}
			});
			// 추가화면의 취소버튼
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e -> {
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
