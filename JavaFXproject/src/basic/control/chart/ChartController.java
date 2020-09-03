package basic.control.chart;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import basic.common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;

public class ChartController implements Initializable {// Initializable를 구현
	@FXML
	PieChart pieChart;
	@FXML
	BarChart barChart;
	@FXML
	AreaChart areaChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Data> list = FXCollections.observableArrayList(new PieChart.Data("AWT", 10),
				new PieChart.Data("Swing", 30), new PieChart.Data("SWT", 25), new PieChart.Data("JavaFX", 35));
		// observableArrayList로 인스턴스를 만들어줌

//		list.add(new PieChart.Data("AWT",10));
//		list.add(new PieChart.Data("Swing",30));
//		list.add(new PieChart.Data("SWT",25));
//		list.add(new PieChart.Data("JavaFX",35));

		pieChart.setData(list);
       
		XYChart.Series<String, Integer> s1=new XYChart.Series<>();
		s1.setData(getSerise1());
		s1.setName("남자");
		XYChart.Series<String, Integer> s2=new XYChart.Series<>();
		s2.setData(getSerise2());
		s2.setName("여자");
		
		barChart.getData().add(s1);
		barChart.getData().add(s2);
		//area chart
		XYChart.Series<String, Integer> s3=new XYChart.Series<>();
		s3.setData(getSerise3());
		s3.setName("온도");
		XYChart.Series<String, Integer> s4=new XYChart.Series<>();
		s4.setData(getSerise4());
		s4.setName("Covid19");
		
		areaChart.getData().add(s3);
		areaChart.getData().add(s4);

	}

	public ObservableList<XYChart.Data<String, Integer>> getSerise1() {
		ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
		list.add(new XYChart.Data<String, Integer>("2015", 70));
		list.add(new XYChart.Data<String, Integer>("2016", 40));
		list.add(new XYChart.Data<String, Integer>("2017", 50));
		list.add(new XYChart.Data<String, Integer>("2018", 30));
		return list;
	}
	public ObservableList<XYChart.Data<String, Integer>> getSerise2() {
		ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
		list.add(new XYChart.Data<String, Integer>("2015", 80));
		list.add(new XYChart.Data<String, Integer>("2016", 60));
		list.add(new XYChart.Data<String, Integer>("2017", 10));
		list.add(new XYChart.Data<String, Integer>("2018", 50));
		return list;
}
	public ObservableList<XYChart.Data<String, Integer>> getSerise3() {
		Connection conn = ConnectionDB.getDB();
		String sql = "select * from receipt";
		ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()) {
				list.add(new XYChart.Data<>(rs.getString("receipt_month"),
											rs.getInt("receipt_qty")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
//		list.add(new XYChart.Data<String, Integer>("09", 25));
//		list.add(new XYChart.Data<String, Integer>("10", 18));
//		list.add(new XYChart.Data<String, Integer>("11", 5));
//		list.add(new XYChart.Data<String, Integer>("12", 3));
}
	public ObservableList<XYChart.Data<String, Integer>> getSerise4() {
		ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
		list.add(new XYChart.Data<String, Integer>("09", 5));
		list.add(new XYChart.Data<String, Integer>("10", 15));
		list.add(new XYChart.Data<String, Integer>("11", 17));
		list.add(new XYChart.Data<String, Integer>("1", 25));
		return list;
}
}
