package basic.control;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InputController implements Initializable {
	@FXML
	TextField txtTitle;
	@FXML
	PasswordField txtPassword;
	@FXML
	ComboBox<String> comboPublic;// 제너릭타입
	@FXML
	DatePicker dateExit;
	@FXML
	TextArea txtContent;
	@FXML
	Button btnReg, btnCancel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		txtTitle.setText("안녕하세요");
//		comboPublic.setValue("public");
		btnReg.setOnAction((event) -> handleBtnRegAction());
	}

	public void handleBtnRegAction() {
		if (txtTitle.getText() == null || txtTitle.getText().equals("")) {
			showPopup("타이틀을 입력하세요!!");
		} else if (txtPassword.getText() == null || txtPassword.getText().equals("")) {
			showPopup("비밀번호를 입력하세요");
		} else if (comboPublic.getValue() == null || comboPublic.getValue().equals("")) {
			showPopup("공개여부를 지정하세요");
		} else if (txtContent.getText() == null || txtContent.getText().equals("")) {
			showPopup("내용을 입력하세요");
		} else if (dateExit.getValue() == null) {
			showCustomDialog("날짜를 입력하세요");
		} else {// 등록코드
			insetData();
		}
	}

	public void insetData() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr", passwd = "hr";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String sql = "insert into new_board values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txtTitle.getText());
			pstmt.setString(2, txtPassword.getText());
			pstmt.setString(3, comboPublic.getValue());
			pstmt.setString(4, dateExit.getValue().toString());
			pstmt.setString(5, txtContent.getText());

			int r = pstmt.executeUpdate();
			System.out.println(r + "건 입력됨.");

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

	public void showCustomDialog(String msg) {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnReg.getScene().getWindow());

		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(400, 150);

		ImageView iv = new ImageView();
		iv.setImage(new Image("/basic/images/dialog-info.png"));
		iv.setFitWidth(50);
		iv.setFitHeight(50);
		iv.setLayoutX(15);
		iv.setLayoutY(15);
		iv.setPreserveRatio(true);

		Button btnOk = new Button("확인");
		btnOk.setLayoutX(336);
		btnOk.setLayoutY(104);
		btnOk.setOnAction((e) -> stage.close());

		Label label = new Label(msg);
		label.setLayoutX(87);
		label.setLayoutY(33);
		label.setPrefSize(290, 15);

		ap.getChildren().addAll(iv, btnOk, label);

		Scene scene = new Scene(ap);
		stage.setScene(scene);
		stage.show();
	}

	public void showPopup(String msg) {
		// popup 타이틀 등록
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: black; -fx-background-radious: 20;");
		hbox.setAlignment(Pos.CENTER);

		ImageView iv = new ImageView();
		iv.setImage(new Image("/basic/images/dialog-info.png"));

		Label label = new Label();
		label.setText(msg);
		label.setStyle("-fx-text-fill: red; ");

		hbox.getChildren().addAll(iv, label);

		Popup pop = new Popup();
		pop.getContent().add(hbox);
		pop.setAutoHide(true);// 다른곳을 클릭하면 사라짐
		pop.show(btnReg.getScene().getWindow());// primarStage임
	}
}
