package controle;

import modele.ModeleTaquin;
import vue.ModeleGUI;

/**
 * Cette classe permet d'afficher le programme en mode graphique.
 */
public class Fenetre {
    
    public static void main(String[] args) {

        int n = 3;
        int m = 3;

        ModeleTaquin puzzle = new ModeleTaquin(n, m);

        ModeleGUI affichage = new ModeleGUI(puzzle);



        
    }
}
