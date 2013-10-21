public class Message {
	protected String sender;
	protected int id;
	protected String msg;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Message(String sender, String msg) {
		super();
		this.sender = sender;
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
