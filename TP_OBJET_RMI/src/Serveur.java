import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Serveur extends UnicastRemoteObject implements Communication {

	protected ArrayList<String> id_clients;
	protected ArrayList<Message> messages;

	public Serveur() throws java.rmi.RemoteException {
		id_clients = new ArrayList<String>();
		messages = new ArrayList<Message>();
	}

	public boolean connect(String pseudo) throws java.rmi.RemoteException {
		if(id_clients.contains(pseudo)){
			
			return false;
		}else {
			id_clients.add(pseudo);
			
			return true;
		}
		
		
	}

	public ArrayList<String> who() throws java.rmi.RemoteException {
		return id_clients;
		
	}

	public boolean bye(String pseudo) throws java.rmi.RemoteException {
		if(id_clients.contains(pseudo)){
			id_clients.remove(pseudo);
			return true;
		}else{
			return false;
		}
		
	}
	
	public void send(String pseudo,String input)throws java.rmi.RemoteException{
		
		Message message=new Message(pseudo,input);
		messages.add(message);
		message.setId(messages.size());
		System.out.println("Message recu");
		
		
	}

	public static void main(String args[]) {
		int port = 80;
		String URL;

		try {
			URL = "//" + InetAddress.getLocalHost().getHostName() + ":" + port
					+ "/mon_serveur";

			// Création du serveur de nom - rmiregistry
			Registry registry = LocateRegistry.createRegistry(port);
			// Création d ’une instance de l’objet serveur
			Serveur serv = new Serveur();
			// Calcul de l’URL du serveur

			Naming.rebind(URL, serv);
			System.out.println("Serveur bound: " + URL);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
