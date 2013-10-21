import java.util.ArrayList;

public interface Communication extends java.rmi.Remote {
	public boolean connect(String pseudo) throws java.rmi.RemoteException;

	public ArrayList<String> who() throws java.rmi.RemoteException;

	public boolean bye(String pseudo) throws java.rmi.RemoteException;
	
	public void send(String pseudo,String msg)throws java.rmi.RemoteException;
}
