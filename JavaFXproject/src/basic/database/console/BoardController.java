package basic.database.console;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
import javafx.scene.control.DatePicker;
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
	ObservableList<Board> list;
	Stage primaryStage;
	public void setPrimaryStage(Stage primaryStage) {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn<Board, ?> tc = tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("date"));
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("time"));
		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("pro"));
		
		list =FXCollections.observableArrayList();
		tableView.setItems(list);
	
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handlebtnAddAction();
			}
		});
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2) {
					String selectedName = 
					tableView.getSelectionModel().getSelectedItem().getName();
					handleDoubleClickAction(selectedName);	
				}
			}
		});
	}
	public void handleDoubleClickAction(String name) {//예약 정보
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
	         TextField txtTime = (TextField) parent.lookup("#txtTime");
	         TextField txtName = (TextField) parent.lookup("#txtName");
	         TextField txtPro = (TextField) parent.lookup("#txtPro");
	         TextField txtNum = (TextField) parent.lookup("#txtNum");
	         for(Board boardlist : list) {
	        	 if(boardlist.getName().equals(name)) {
	        		 txtDate.setValue(LocalDate.parse(boardlist.getDate()));
	        		 txtTime.setText(String.valueOf(boardlist.getTime()));
	        		 txtName.setText(String.valueOf(boardlist.getName()));
	        		 txtPro.setText(String.valueOf(boardlist.getPro()));
	        		 txtNum.setText(String.valueOf(boardlist.getNum()));
	        	 }
	        	 
	         } 
	         Button btnUpdate = (Button) parent.lookup("#btnUpdate");
	         	btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						for(int i=0; i<list.size(); i++) {
				        	 if(list.get(i).getName().equals(name)) {
				        		 Board board = new Board(txtDate.getValue().toString(),
				        				 txtTime.getText(),
				        				 txtName.getText(),
				        				 txtPro.getText(),
				        				 txtNum.getText());
				        		 list.set(i, board);
				        	 } 
				         }stage.close(); 					
					}
					
				});
	         Button btnDel = (Button) parent.lookup("#btnDel");
	         btnDel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					for(int i = 0; i<list.size(); i++) {
						if(list.get(i).getName().equals(name)) {
							list.remove(i);
						}
					}	
				stage.close();
				}
				
			});
	}catch (IOException e) {
		e.printStackTrace();
	 }
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
			         TextField txtTime = (TextField) parent.lookup("#txtTime");
			         TextField txtName = (TextField) parent.lookup("#txtName");
			         TextField txtPro = (TextField) parent.lookup("#txtPro");
			         TextField txtNum = (TextField) parent.lookup("#txtNum");
			         
			         Board board = new Board(txtDate.getValue().toString(),
				             				txtTime.getText(),
				             					txtName.getText(),
				                                txtPro.getText(),
				                                txtNum.getText());
			                  list.add(board);
			         stage.close();
				}
			});

	}catch (IOException e) {
		e.printStackTrace();
	}
 }
}