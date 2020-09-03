package basic.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//ui View
//Control ViewController
public class ViewExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane ap = FXMLLoader.load(getClass().getResource("View.fxml"));
		Scene scene = new Scene(ap);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
public static void main(String[] args) {
	Application.launch(args);
}
}
