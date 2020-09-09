package basic.database.console;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Board {
	SimpleStringProperty date;
	SimpleStringProperty time;
	SimpleStringProperty name;
	SimpleStringProperty pro;
	SimpleStringProperty num;
	
	public Board() {
		
	}
	public Board(String date, String time,String name,String pro ,String num) {
		super();
		this.date =new SimpleStringProperty(date);
		this.time =new SimpleStringProperty(time);
		this.name =new SimpleStringProperty(name);
		this.pro =new SimpleStringProperty(pro);
		this.num =new SimpleStringProperty(num);
	}

	public String getNum() {
		return this.num.get();
	}

	public void setNum(String num) {
		this.num.set(num);
	}

	public String getDate() {
		return this.date.get();
	}

	public void setDate(String date) {
		this.date.set(date);
	}

	public String getTime() {
		return this.time.get();
	}

	public void setTime(String time) {
		this.time.set(time);
	}

	public String getName() {
		return this.name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getPro() {
		return this.pro.get();
	}

	public void setPro(String pro) {
		this.pro.set(pro);
	}

	
}
