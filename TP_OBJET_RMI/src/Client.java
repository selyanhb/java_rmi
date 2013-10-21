import java.io.Console;
import java.net.InetAddress;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	private static String url_serveur_local;

	private static String pseudo = "";

	public static void main(String args[]) {

		Scanner keyboard = new Scanner(System.in);
		Communication comm = null;

		// Recherche d'un serveur
		comm = initialisation(keyboard);

		// Boucle de communication
		if (comm != null) {
			boucleCommandes(keyboard, comm);
		}

		// Fin du programme, fermeture des flux
		keyboard.close();
		System.out.println("Fin du programme");
	}

	public static Communication initialisation(Scanner keyboard) {
		Communication comm = null;
		System.out.println("Bienvenue dans l'application de chat");
		// Initialisation de l'url serveur local
		try {
			url_serveur_local = "//" + InetAddress.getLocalHost().getHostName()
					+ ":" + 80 + "/mon_serveur";
		} catch (Exception e) {
			System.out
					.println("Avertissement: problème lors de la création de l'url du serveur local");
		}
		// Boucle d'essais de connexion à un serveur
		do {
			System.out
					.print("Tapez l'URL du serveur ('quit' pour arrêter,'local' pour l'url serveur local):");
			String input = keyboard.nextLine();
			System.out.println();
			if (input.equals("quit")) {
				return null;
			}
			if (input.equals("local")) {
				System.out.println("Serveur local choisi");
				input = url_serveur_local;
			}
			try {
				comm = (Communication) Naming.lookup(input);
				System.out.println("Connexion réussie au serveur: " + input);
				System.out.println("-----------------------");
			} catch (Exception e) {
				System.out
						.println("Problème lors de la recherche du serveur, réessayez...");
			}
		} while (comm == null);
		return comm;
	}

	public static void boucleCommandes(Scanner keyboard, Communication comm) {

		System.out
				.println("Appuyer sur Entrée pour taper vos commandes ('help' pour l'aide et 'quit' pour arrêter");
		String input = null;
		while (true) {
			try{
			keyboard.nextLine();
			// TODO Gérer le thread d'affichage des autres messages

			// On empeche e thread d'afficher des messages
			System.out.print("user " + pseudo + " input=>");
			input = keyboard.nextLine();

			// On analyse la commande
			if (input.equals("quit")) {
				return;
			}

			else if (input.equals("who")) {
				ArrayList<String> list=comm.who();
				for (int i = 0; i < list.size(); i++) {
					System.out.println("client n° " + (i + 1) + " pseudo: "
							+ list.get(i));
				}
				System.out.println("Affichage des participants");
				System.out.println("Il y a " + list.size() + " connectés");
			}

			else if (input.equals("help")) {
				System.out.println("Affichage de l'aide:");
				// TODO Afficher l'aide

			} else if (input.equals("bye")) {
				if (!pseudo.equals("")) {
					if(comm.bye(pseudo)){
						pseudo="";
						System.out.println("Log out effectué, tapez entrée puis 'quit' pour fermer le prog");
					}else{
						System.out.println("Problème d'exécution sur le serveur");
					}
					
					
					
					
				} else {
					System.out.println("Pas identifié sur le serveur");
				}

			} else {
				String command = (input.split(" "))[0];

				if (command.equals("id")) {
					if(pseudo.equals("")){
						if(comm.connect((input.split(" "))[1])){
							pseudo=input.split(" ")[1];
						}
						System.out.println("Vous etes identifié sous le pseudo "+pseudo);
					}else{
						System.out.println("Pseudo pas disponible, veuillez en choisir un autre");
					}
					

				} else if (command.equals("send")) {
					if (!pseudo.equals("")) {
						String msg=input.replaceFirst("send ","");
						comm.send(pseudo,input);
						System.out.println("Message Envoyé");
						//System.out.println(message.getId()+ " "+ message.getSender()+ " says: "+message.getMsg());
					} else {
						System.out
								.println("Veuillez d'abord vous identifier avec 'id'");
					}
				} else {
					// Commande inconnue
					System.out
							.println("Commande inconnue, taper entrée puis 'help' pour le listing");
				}

			}
			}catch(Exception e){
				System.out.println("Erreur lors de l'envoi de la commande");
			}
		}
	}

	//
	/*
	 * String URL=""; Integer idd=5; try{ URL = "//BORIS-PC:80/mon_serveur";
	 * Communication serv=(Communication) (Naming.lookup(URL));
	 * serv.connect(idd); serv.who(); serv.bye(idd); serv.who();
	 * 
	 * }catch (Exception e){ System.out.println(e.getMessage() + URL); }
	 */
}
