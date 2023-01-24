package controle;

import java.util.Scanner;
import modele.ModeleTaquin;


/**
 * Cette classe permet d'exécuter le programme dans le terminal, avec des commandes texte.
 */

public class Terminal{

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in).useDelimiter("\n");
      int n = 3;
      int m = 3;

      //On crée un Taquin de 3 par 3 et on l'affiche
      ModeleTaquin test = new ModeleTaquin(n, m);
      System.out.println(test);

      //On demande à l'utilisateur de choisir une commande à effectuer pour bouger la pièce "vide"
      System.out.println("Vous pouvez choisir les commandes suivantes: monter la pièce vide (haut), la descendre (bas), la mettre à gauche (gauche), la mettre à droite (droite)");

      //Tant que le puzzle n'est pas finit, le terminal continuera de demander de rentrer des commandes à l'utilisateur
      while (!test.isOver() == true) {
          System.out.println("Entrez une commande: ");
          String comm = scanner.next();

          if(comm.equals("quit")){
            System.exit(0);
          }
          /*On regarde si il y a un mur autour de la pièce et si il n'y en a pas, la pièce vide s'échange avec la pièce voulue
          via la fonction swap()*/
          else if (comm.equals("haut") && test.x_B - 1 >= 0 && test.x_B - 1 < n) {
            test.swap(test.x_B - 1, test.y_B);
          }
          else if (comm.equals("bas") && test.x_B + 1 >= 0 && test.x_B + 1 < n) {
            test.swap(test.x_B + 1, test.y_B);
          }
          else if (comm.equals("gauche") && test.y_B - 1 >= 0 && test.y_B - 1 < m) {
            test.swap(test.x_B, test.y_B - 1);
          }
          else if (comm.equals("droite") && test.y_B + 1 >= 0 && test.y_B + 1 < m) {
            test.swap(test.x_B, test.y_B + 1);
          }
          /*Si il y a un mur là où l'utilisateur voulait envoyer la pièce vide, ou si la commande n'existe pas, on redemande à
          l'utilisateur de rentrer une commande, mais une commande correcte cette fois ci*/
          else {
            System.out.println("Veuillez rentrer une commande correcte parmi les suivantes: haut, bas, gauche, droite");
          }

          System.out.println("Grille : \n" + test);
      }

      //Une fois les pièces du puzzle au bon endroit, le jeu se termine
      System.out.println("Jeu terminé");
      scanner.close();

  }


}
