package basic.database.console;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Board {
	SimpleStringProperty dat;
	SimpleStringProperty time;
	SimpleStringProperty name;
	SimpleStringProperty pro;
	SimpleStringProperty num;
	
	public Board() {
		
	}
	public Board(String dat, String time,String name,String pro ,String num) {
		super();
		this.dat =new SimpleStringProperty(dat);
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

	public String getDat() {
		return this.dat.get();
	}

	public void setDat(String dat) {
		this.dat.set(dat);
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
