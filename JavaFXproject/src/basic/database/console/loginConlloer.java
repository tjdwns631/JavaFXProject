package basic.database.console;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginConlloer implements Initializable {
@FXML
private TextField UserName;
private PasswordField PassWord;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public void Login(ActionEvent event)throws Exception{
		if(UserName.getText().equals("id")&& PassWord.getText().equals("pass")) {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardList.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		}
			
	}
	

}
