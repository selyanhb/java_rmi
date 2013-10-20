
public class Message {
	protected Client sender;
	protected int id;
	protected String msg;



public Client getSender() {
		return sender;
	}

	public void setSender(Client sender) {
		this.sender = sender;
	}

public Message(Client sender, int id, String msg) {
	super();
	this.sender = sender;
	this.id = id;
	this.msg = msg;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}
	



}
