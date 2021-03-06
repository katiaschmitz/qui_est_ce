/**
 *
 *  Cette classe fait partie du logiciel "Qui est ce",adaptation du jeu de société
 *  en réseau.</p> <p>
 *
 *  Un "AnalyseurServeur" permet d'analyser les différentes requete qu'un joueur
 *  (en particulier) peut faire auprès du serveur. Il est utilisé dans la classe
 *  "ThreadJOueur".
 *  Il est caractérisé par le ThreadJOueur (correspondant à un joueur) qui lui
 *  correspond.
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ListIterator;

public class AnalyseurServeur
{
	private ThreadJoueur th;
	private boolean actif;


/*******************************************************/
/***               CONSTRUCTEUR                      ***/
/*******************************************************/

	/**
	 *  Initialise un AnalyseurServeur avec le ThreadJoueur donné en argument
	 *  Cette méthode peut être utilisée lors de l'initialisation du ThreadJoueur.
	 *
	 * @param  th le ThreadJoueur à associer
	 */
	public AnalyseurServeur(ThreadJoueur th)
	{
		//COMPILATION
		System.out.println(" debut méthodeAnalyseurServeur constructeur ");
		this.th = th;
		this.actif=true;
	}
	/**
	 *  renvoie si l analyseur est actif
	 *
	 * @return true si on continue d attendre false sinon
	 */
	public boolean getActif()
	{
		return this.actif;
	}

/*******************************************************/
/***             FONCTIONS PRINCIPALES               ***/
/*******************************************************/


	/**
	 *  Attends qu'une requête soit fait par le joueur à travers la socket
	 *  associée à ce dernier. Ensuite elle fait appel à des méthodes de ThreadJOueur.
	 *
	 */
	public void principale()
	{
		//System.out.println(" debut méthodeAnalyseurServeur principale ");

		String[] infos;
		String demande;
		String recu="";

		try
		{
			/* message pour compilation*/
			System.out.println("attente message client");
			/*recuperation du message*/
			recu = th.getInput().readLine();// entrée de la socket
			/* affichage */System.out.println("reception "+recu);/* affichage */

		  if(recu != null)
		  {
			infos=recu.split(":");// decoupage de la chaine de caractère
			demande=infos[0];// numero de la demande

			/*traitement des différentes demandes*/
			if(demande.equals("1"))//demande inscription
			{
				th.inscription(infos[1],infos[2]);
			}
			else if(demande.equals("2"))//demande connexion
			{
				th.connexion(infos[1],infos[2]);
			}
			else if(demande.equals("3"))//demande des joueurs en attente de partie
			{
				th.listeAttente();
			}
			else if(demande.equals("4"))
			{
				th.choixAdversaire(infos[1]);//proposer une partie

			}
			else if(demande.equals("5"))//accepter ou non la partie
			{
				th.demandeAccept(infos[1]);
			}

			else if(demande.equals("7"))//choisir le personnage a deviner
			{
				th.choisirPerso(infos[1]);

			}
			else if(demande.equals("9"))// envoyer les cases baissées à l'adversaire
			{
				th.envoiCaseBaissee(recu);
			}
			else if(demande.equals("10"))// proposer une reponse
			{
				th.proposeReponse(infos[1]);
			}
			else if(demande.equals("11"))// parler a travers la boite de dialogue
			{
				th.envoiTexte(infos[1]);
			}
			else if(demande.equals("13"))// envoyer une image
			{
				th.envoiImage(infos[1]);
			}
			else if(demande.equals("14"))// demande afficher scores
			{
				th.afficherScores();
			}
			else if(demande.equals("15"))// deconnexion
			{
				th.deconnexion();
			}
			else if(demande.equals("30"))// creer le joueur automatique
			{
				th.creationJoueurAuto(infos[1]);
			}
			else if(demande.equals("31"))// permet au joueur ( et joueur automatique) de poser une question
			{
				th.envoiQuestionAuto(infos[1]);
			}
			else if(demande.equals("33"))// envoyer la liste de question automatique
			{
				th.envoiQuestion();
			}
			else if(demande.equals("34"))// choix du mode
			{
				th.choisirMode(infos[1]);
			}
			else if(demande.equals("36"))// envoi liste de favoris
			{
				th.listeFavoris();
			}

			else if(demande.equals("19"))// envoyer les images du mode
			{
				th.envoyerImageMode(infos[1]);
			}
			else if(demande.equals("40"))// quitter la page de partie
			{
				th.quitterPartie();
			}
			else if(demande.equals("50"))// afficher scores joueur
			{
				th.afficherScoreJoueur();
			}
			/*remise à zero des informations*/
			recu="";
			for(int i=0;i<infos.length;i++)
				infos[i]="";
		  }
		  else
		  {
			th.deconnexion();
			this.actif = false;
		  }

		}catch (IOException e){ }
		
	}
}

