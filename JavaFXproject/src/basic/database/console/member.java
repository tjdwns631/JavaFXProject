package basic.database.console;

import javafx.beans.property.SimpleStringProperty;

public class member {
SimpleStringProperty id;
SimpleStringProperty pass;
public member(String id, String pass) {
	super();
	this.id =new SimpleStringProperty(id);
	this.pass =new SimpleStringProperty(pass);
}
public String getId() {
	return this.id.get();
}
public void setId(String id) {
	this.id.set(id);
}
public String getPass() {
	return this.pass.get();
}
public void setPass(String pass) {
	this.pass.set(pass);
}


}
