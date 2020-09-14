package basic.database.console;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import basic.example.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginConlloer implements Initializable {
	@FXML
	private TextField id;
	@FXML
	private PasswordField pass;
	@FXML
	Button lg, si;
	ObservableList<member> list;
	Connection conn = ConnectionDB.getDB();
	Stage primaryStage;
	public void setPrimaryStage(Stage primaryStage) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	lg.setOnAction(s->Login());
	}

	public void Login() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(lg.getScene().getWindow());
		ObservableList<member> list = FXCollections.observableArrayList();
		String sql = "select * from member";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				member mem = new member(rs.getString("ID"),
						rs.getString("PASS"));
				list.add(mem);
			};
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(member m : list) {
			if(m.getId().equals(id.getText())&& m.getPass().equals(pass.getText())) {
				try {	
					Parent parent;
					parent = FXMLLoader.load(getClass().getResource("Boardlist.fxml"));
					Scene scene = new Scene(parent);
					stage.setTitle("예약");
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
}
}