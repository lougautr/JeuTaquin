package modele;

import java.util.Random;
import controle.*;


public class ModeleTaquin extends AbstractModeleEcouteur{

    public int n; //nombre de lignes
    public int m; //nombre de colonnes

    //coordonnées de la tuile vide
    public int x_B;
    public int y_B;

    public int[][] grille;

    /**
     * Initialise un puzzle mélangé
     *
     * @param n est le nombre de lignes
     * @param m est le nombre de colonnes
     */

    public ModeleTaquin(int n, int m){
        super();
        this.n = n;
        this.m = m;
        this.grille = new int[n][m];

        //initialisation de la grille en remplissant les cases
        int cpt = 1;

        for(int i = 0; i < grille.length; i++){
            for(int j = 0; j< grille[i].length; j++){
                grille[i][j] = cpt;
                cpt++;
            }
        }
        this.grille[this.n-1][this.m-1] = 0;
        x_B = this.n-1;
        y_B = this.m-1;

        //mélange de la grille
        this.shuffle();
    }

    /**
     * Méthode qui permet d'accéder à la grille en l'état actuel
     * @return int[][] la grille des nombres du puzzle
     */

    public int[][] getGrille(){
        return this.grille;
    }

    /**
     * Méthode qui permet d'accéder au nombre de lignes du puzzle
     * @return int le nombre de lignes
     */
    public int getNbLigne(){
        return this.n;
    }

    /**
     * Méthode qui permet d'accéder au nombre de colonnes du puzzle
     * @return int le nimbre de colonnes
     */
    public int getNbColonne(){
        return this.m;
    }

    /**
     * Méthode qui permet d'accéder à la coordonnée x en ligne de la case vide
     * @return int la coordonnée x de la case vide
     */
    public int getBx(){
        return this.x_B;
    }

    /**
     * Méthode qui permet d'accéder à la coordonnée y en colonne de la case vide
     * @return int la coordonnée y de la case vide
     */
    public int getBy(){
        return this.y_B;
    }

    /**
     * Méthode qui permet d'afficher la grille dans la console au format Strinf
     * @return String la grille au format String
     */
    public String toString(){
        String str = "\n";
        for(int i = 0; i < n; i++){
            for(int j = 0; j< m; j++){
                System.out.print(grille[i][j]+" ");
            }
            System.out.print("\n");
        }
        return str;

    }

    /**
     * Méthode qui permet d'échanger la case de coordonnées (i,j) avec la case vide
     * @param i la coordonnée en ligne de la case à échanger
     * @param j la coordonnée en colonne de la case à échanger
     */
    public void swap(int i, int j){
        this.grille[x_B][y_B] = this.grille[i][j];
        this.grille[i][j] = 0;
        x_B = i;
        y_B = j;
        puzzleChange();
    }

    /**
     * Méthode qui permet de mélanger la grille avec la méthode swap un nombre aléatoire de fois
     */
    private void shuffle(){
        Random r = new Random();
        int nb_R = r.nextInt(500 - 200) + 200;
        int u;
        int d;
        for(int k = 0; k < nb_R ; k++){
            int indice = r.nextInt(4);
            
            //ensemble des mouvements possibles autour de la tuile vide
            int[][] move = {{x_B,y_B-1},{x_B-1,y_B},{x_B+1,y_B},{x_B,y_B+1}};
            u = move[indice][0];
            d = move[indice][1];

            if(u <= n-1 && u >= 0 && d <= m-1 && d >= 0){
                swap(u,d);
            }else{
                while(!(u <= n-1 && u >= 0 && d <= m-1 && d >= 0)){
                    indice = r.nextInt(4);
                    u = move[indice][0];
                    d = move[indice][1];
                }
            }

        }

    }

    /**
     * Méthode qui permet de déterminer si la partie est terminée. C'est-à-dire si la grille est en position finale.
     * @return true si le jeu est terminé, false sinon
     */
    public boolean isOver(){
        int[][] f = new int[n][m];
        int cpt = 1;
        for(int i = 0; i < f.length; i++){
            for(int j = 0; j< f[i].length; j++){
                f[i][j] = cpt;
                cpt++;
            }
        }
        f[this.n-1][this.m-1] = 0;

        for(int i = 0; i < f.length; i++){
            for(int j = 0; j< f[i].length; j++){
                if(f[i][j] != this.grille[i][j]){
                    return false;
                }
            }
        }
        return true;

    }



}
