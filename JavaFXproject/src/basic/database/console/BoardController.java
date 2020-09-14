package basic.database.console;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

import basic.example.ConnectionDB;
import basic.example.Student;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BoardController implements Initializable {
	@FXML
	TableView<Board> tableView;
	@FXML
	Button btnAdd;
	@FXML
	ComboBox<String> comboPublic;
	ObservableList<Board> list;

	Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn<Board, ?> tc = tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("dat"));
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("time"));
		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("pro"));

		list = FXCollections.observableArrayList();
		tableView.setItems(getRelist());
		list = getRelist();

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handlebtnAddAction();
			}
		});
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					String selectedName = tableView.getSelectionModel().getSelectedItem().getName();
					handleDoubleClickAction(selectedName);
				}
			}
		});
	}

	public void getUpdate(Board c) {
		Connection conn = ConnectionDB.getDB();

		String sql = "update reserve set dat=? , time =? ,pro=? ,num=?  where name =? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getDat());
			pstmt.setString(2, c.getTime());
			pstmt.setString(3, c.getPro());
			pstmt.setString(4, c.getNum());
			pstmt.setString(5, c.getName());

			int r = pstmt.executeUpdate();
			System.out.println(r + " 건 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			tableView.setItems(getRelist());
		}
	}

	public ObservableList<Board> getRelist() {
		Connection conn = ConnectionDB.getDB();
		String sql = "select * from reserve";
		ObservableList<Board> list2 = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board bor = new Board(rs.getString("dat"),
						rs.getString("time"),
						rs.getString("name"),
						rs.getString("pro"),
						rs.getString("num"));
				list2.add(bor);
			};
			return list2;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list2;
	}

	public void insetData(Board b) {
		Connection conn = ConnectionDB.getDB();

		String sql = "insert into reserve values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getDat());
			pstmt.setString(2, b.getTime());
			pstmt.setString(3, b.getName());
			pstmt.setString(4, b.getPro());
			pstmt.setString(5, b.getNum());

			int r = pstmt.executeUpdate();
			System.out.println(r + " 건 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void handleDoubleClickAction(String name) {// 예약 정보
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BoardImpor.fxml"));
			Scene scene = new Scene(parent);
			stage.setTitle("예약");
			stage.setScene(scene);
			stage.show();
			DatePicker txtDate = (DatePicker) parent.lookup("#dateExit");
			ComboBox txtTime = (ComboBox) parent.lookup("#comboPublic");
			TextField txtName = (TextField) parent.lookup("#txtName");
			TextField txtPro = (TextField) parent.lookup("#txtPro");
			TextField txtNum = (TextField) parent.lookup("#txtNum");

			for (Board boardlist : getRelist()) {

				if (boardlist.getName().equals(name)) {
					txtDate.setValue(LocalDate.parse(boardlist.getDat()));
					txtTime.setValue(String.valueOf(boardlist.getTime()));
					txtName.setText(String.valueOf(boardlist.getName()));
					txtPro.setText(String.valueOf(boardlist.getPro()));
					txtNum.setText(String.valueOf(boardlist.getNum()));
				}

			}
			Button btnUpdate = (Button) parent.lookup("#btnUpdate");
			btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getName().equals(name)) {
							Board board = new Board(txtDate.getValue().toString(), txtTime.getValue().toString(),
									txtName.getText(), txtPro.getText(), txtNum.getText());
							getUpdate(board);
						}
					}
					stage.close();
				}

			});
			Button btnDel = (Button) parent.lookup("#btnDel");
			btnDel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getName().equals(name)) {
							list.remove(i);
							getDel(name);
							tableView.setItems(getRelist());
						}
					}
					stage.close();
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Board> getDel(String d) {
		Connection conn = ConnectionDB.getDB();
		String sql = "delete from reserve where name=?";
		ObservableList<Board> list2 = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d);
			int r = pstmt.executeUpdate();
			System.out.println(r+"삭제되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list2;
	}
	///////////////////////////////////////////////////////////////////////////
	public void handlebtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("BoardAdd.fxml"));
			Scene scene = new Scene(parent);
			stage.setTitle("예약");
			stage.setScene(scene);
			stage.show();
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					DatePicker txtDate = (DatePicker) parent.lookup("#dateExit");
					ComboBox txtTime = (ComboBox) parent.lookup("#comboPublic");
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtPro = (TextField) parent.lookup("#txtPro");
					TextField txtNum = (TextField) parent.lookup("#txtNum");

					Board board = new Board(txtDate.getValue().toString(), txtTime.getValue().toString(),
							txtName.getText(), txtPro.getText(), txtNum.getText());
					list.add(board);
					insetData(board);
					tableView.setItems(getRelist());
					stage.close();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}