import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Serveur extends UnicastRemoteObject implements Communication {

	protected ArrayList<Integer> id_clients;
	protected ArrayList<Message> messages;

	public Serveur() throws java.rmi.RemoteException {
		id_clients = new ArrayList<Integer>();
		messages = new ArrayList<Message>();
	}

	public void connect(Integer id) throws java.rmi.RemoteException {
		System.out.println("Vous etes connecté au serveur!");
		id_clients.add(id);
	}

	public void who() throws java.rmi.RemoteException {
		for (int i = 0; i < id_clients.size(); i++) {
			System.out.println("client n° " + (i + 1) + " id: "
					+ id_clients.get(i));
		}
		System.out.println("Il y a " + id_clients.size() + " connectés");
	}

	public void bye(Integer id) throws java.rmi.RemoteException {
		System.out.println("Vous êtes bien déconnecté du serveur!");
		id_clients.remove(id);
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
