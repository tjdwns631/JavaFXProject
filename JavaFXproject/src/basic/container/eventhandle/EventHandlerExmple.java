package basic.container.eventhandle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EventHandlerExmple extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
BorderPane root = FXMLLoader.load(getClass().getResource("Root.fxml"));
		// 읽어올fxml파일 읽어 오기
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
