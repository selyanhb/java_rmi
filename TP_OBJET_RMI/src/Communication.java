
public interface Communication extends java.rmi.Remote {
public void connect(Integer id) throws java.rmi.RemoteException;
public void who() throws java.rmi.RemoteException;
public void bye(Integer id)throws java.rmi.RemoteException;
}


