package basic.control.chart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//ui:Chart.fxml(925page)
//Control: ChartController
public class ChartExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox ap
		= FXMLLoader.load(this.getClass().getResource("Chart.fxml"));
		Scene scene = new Scene(ap);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
