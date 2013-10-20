import java.io.Console;
import java.net.InetAddress;
import java.rmi.*;
import java.util.Scanner;

public class Client {	
private static String url_serveur_local;

private static String id="";

public static void main(String args[]){
	
	
	Scanner keyboard=new Scanner(System.in);
	Communication comm=null;
	
	//Recherche d'un serveur
	comm=initialisation(keyboard);
		
	//Boucle de communication
	if(comm!=null){
	boucleCommandes(keyboard,comm);
	}
	
	//Fin du programme, fermeture des flux
	keyboard.close();
	System.out.println("Fin du programme");
		}
		
public static Communication initialisation(Scanner keyboard){
	Communication comm=null;
	System.out.println("Bienvenue dans l'application de chat");
	//Initialisation de l'url serveur local
	try{
		url_serveur_local="//"+InetAddress.getLocalHost().getHostName()+":"+
				80+"/mon_serveur";
		}catch(Exception e){
		System.out.println("Avertissement: probl�me lors de la cr�ation de l'url du serveur local");	
		}
	//Boucle d'essais de connexion � un serveur
	do{
		System.out.print("Tapez l'URL du serveur ('quit' pour arr�ter,'local' pour l'url serveur local):");
		String input=keyboard.nextLine();
		System.out.println();
		if(input.equals("quit")){return null;}
		if(input.equals("local")){
			System.out.println("Serveur local choisi");input=url_serveur_local;}
		try{
		comm=(Communication)Naming.lookup(input);
		System.out.println("Connexion r�ussie au serveur: "+input);
		System.out.println("-----------------------");
		}catch(Exception e){
		System.out.println("Probl�me lors de la recherche du serveur, r�essayez...");
		}
	}while(comm==null);
	return comm;
}
		
public static void boucleCommandes(Scanner keyboard, Communication comm){
	
	
	System.out.println("Appuyer sur Entr�e pour taper vos commandes ('help' pour l'aide et 'quit' pour arr�ter");
	String input=null;
	while(true){
		keyboard.nextLine();
		//TODO G�rer le thread d'affichage des autres messages
		
		//On empeche e thread d'afficher des messages
		System.out.print("user "+id+" input=>");
		input=keyboard.nextLine();
		
		//On analyse la commande
		if(input.equals("quit")){return;}
		
		else if(input.equals("who")){
			//TODO Afficher la liste des id des participants
			System.out.println("Affichage des participants");
		}
		
		else if(input.equals("help")){
			System.out.println("Affichage de l'aide:");
			//TODO Afficher l'aide
		
		}else if(input.equals("bye")){
			if(!id.equals("")){
				//TODO Retirer l'id dans la liste du serveur
				id="";
				System.out.println("Log out effectu�, tapez entr�e puis 'quit' pour fermer le prog");
			}else{
				System.out.println("Pas identifi� sur le serveur");
			}
			
		
		}else{
		String command=(input.split(" "))[0];

		if(command.equals("id")){
			//TODO Faire la connexion
			System.out.println("Connect� sous le pseudo "+id);
		
		}else if(command.equals("send")){
			if(!id.equals("")){
			//TODO Envoyer un message
			System.out.println("Message envoy�");
			}else{
				System.out.println("Veuillez d'abord vous identifier avec 'id'");
			}
		}else{
			//Commande inconnue
			System.out.println("Commande inconnue, taper entr�e puis 'help' pour le listing");
		}
		
		}
	}
}
	
		



	//
	/*
	String URL="";
	Integer idd=5;
	try{
		URL = "//BORIS-PC:80/mon_serveur";
		Communication serv=(Communication) (Naming.lookup(URL));
		serv.connect(idd);
		serv.who();
		serv.bye(idd);
		serv.who();
		
	}catch (Exception e){
		System.out.println(e.getMessage() + URL);
	}
	*/
}

	


