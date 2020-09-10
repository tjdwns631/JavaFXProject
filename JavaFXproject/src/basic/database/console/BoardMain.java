package basic.database.console;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardList.fxml"));
		Parent root = loader.load();
		//parent 모든 타입을 받을수있음
		BoardController controller =loader.getController();
		controller.setPrimaryStage(primaryStage);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("예약 리스트");
		primaryStage.show();
		primaryStage.setResizable(false);
		
	}
public static void main(String[] args) {
	launch(args);
}
}
