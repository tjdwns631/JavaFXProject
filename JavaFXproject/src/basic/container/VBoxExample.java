package basic.container;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxExample extends Application {//875page이것이자바다

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		root.setPadding(new Insets(10,10,10,10));
		
		ImageView iv = new ImageView();
		iv.setFitWidth(300);
		iv.setPreserveRatio(true);
		iv.setImage(new Image("/basic/images/fruit1.jpg"));
		
		HBox hbox=new HBox();
		hbox.setAlignment(Pos.CENTER);//가운데정렬
		hbox.setSpacing(20);
		
		Button btwPrev = new Button();
		btwPrev.setText("이전");
		Button btwNext = new Button("다음");
		
		
		HBox.setHgrow(btwNext,Priority.ALWAYS);
		btwNext.setMaxWidth(Double.MAX_VALUE);
		hbox.getChildren().add(btwPrev);
		hbox.getChildren().add(btwNext);
		VBox.setMargin(hbox,new Insets(10));
		
		//이벤트 핸들러를 해당컨드롤에 등록.
		btwPrev.setOnAction(new EventHandler<ActionEvent>() {
				int loc = 1;
			@Override
			public void handle(ActionEvent arg0) {
				if(loc==1)
					loc =8;
				iv.setImage(new Image("/basic/images/fruit" + loc-- + ".jpg"));
			}
			
		});
		btwNext.setOnAction(new EventHandler<ActionEvent>() {
			int loc=1;
			@Override
			public void handle(ActionEvent ae) {
				if(loc==8)
					loc = 1;
				iv.setImage(new Image("/basic/images/fruit" + loc++ + ".jpg"));
				
			}
		});
	//  btwNext.setOnAction((ae)->{
	//	System.out.println(ae.getSource());
	//	});
		root.getChildren().add(iv);
		root.getChildren().add(hbox);
		///컨테이너를 만듬
		Scene scene = new Scene(root);//신을 스테이지에 담아줌 
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("VBox 예제");
		primaryStage.setResizable(false);
	}
	public static void main(String[] args) {
		Application.launch(args);//런치 = start라는 메소드 호출
	}

}
