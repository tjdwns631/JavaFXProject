package basic.database.console;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Board {
	SimpleIntegerProperty date;
	SimpleIntegerProperty time;
	SimpleStringProperty name;
	SimpleStringProperty pro;
	SimpleStringProperty num;
	
	public Board() {
		
	}
	public Board(int date, int time,String name,String pro ,String num) {
		super();
		this.date =new SimpleIntegerProperty(date);
		this.time =new SimpleIntegerProperty(time);
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

	public int getDate() {
		return this.date.get();
	}

	public void setDate(int date) {
		this.date.set(date);
	}

	public int getTime() {
		return this.time.get();
	}

	public void setTime(int time) {
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
