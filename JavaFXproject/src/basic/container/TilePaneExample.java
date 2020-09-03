package basic.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		TilePane root = new TilePane();
		root.setPrefHeight(100);
		root.setPrefWidth(100);
		
		ImageView iv = new ImageView();
		iv.setImage(new Image("/basic/images/AreaChart.png"));
		ImageView iv1 = new ImageView();
		iv1.setImage(new Image("/basic/images/BarChart.png"));
		ImageView iv2 = new ImageView();
		iv2.setImage(new Image("/basic/images/BubbleChart.png"));
		ImageView iv3 = new ImageView();
		iv3.setImage(new Image("/basic/images/dialog-error.png"));
		ImageView iv4 = new ImageView();
		iv4.setImage(new Image("/basic/images/dialog-help.png"));
		
		root.getChildren().add(iv);
		root.getChildren().add(iv1);
		root.getChildren().add(iv2);
		root.getChildren().add(iv3);
		root.getChildren().add(iv4);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("틸판");
	//	primaryStage.setResizable(false);
		
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
