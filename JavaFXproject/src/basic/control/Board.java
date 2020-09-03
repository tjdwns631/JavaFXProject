package basic.control;

import javafx.beans.property.SimpleStringProperty;

public class Board {
private SimpleStringProperty title;
private SimpleStringProperty password;
private SimpleStringProperty publicity;
private SimpleStringProperty exitDate;
private SimpleStringProperty content;

public Board(String title, String password, String publicity, String exitDate, String content) {
	this.title = new SimpleStringProperty(title);
	this.password = new SimpleStringProperty(password);
	this.publicity = new SimpleStringProperty(publicity);
	this.exitDate = new SimpleStringProperty(exitDate);
	this.content = new SimpleStringProperty(content);
}

public String getTitle() {
	return this.title.get();
}
public void setTitle(String title) {
	this.title.set(title);
}
public SimpleStringProperty titleProperty() {
	return this.title;
}
public String getpassword() {
	return this.password.get();
}
public void setpassword(String password) {
	this.password.set(password);

}
public SimpleStringProperty passwordProperty() {
	return this.password;
}
public String getpublicity() {
	return this.publicity.get();
}
public void setpublicity(String publicity) {
	this.publicity.set(publicity);

}
public SimpleStringProperty publicityProperty() {
	return this.publicity;
}
public String getexitDate() {
	return this.exitDate.get();
}
public void setexitDate(String exitDate) {
	this.exitDate.set(exitDate);

}
public SimpleStringProperty exitDateProperty() {
	return this.exitDate;
}
public String getcontent() {
	return this.content.get();
}
public void setcontent(String content) {
	this.content.set(content);

}
public SimpleStringProperty contentProperty() {
	return this.content;
}

}
