package vue;

import modele.*;
import controle.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.io.File;


public class VueModel extends JPanel implements EcouteurModele{
  protected ModeleTaquin modeleActuel; //le modèle du jeu
  protected int n; //le nombre de lignes
  protected int m; //le nombre de colonnes
  protected JFrame frame; //la JFrame affiché
  protected String image; //nom de l'image
  protected JButton[][] tabBoutons; //un tableau de JButton
  protected int i_zero; //la position i de la case vide
  protected int j_zero; //la position j de la case vide
  protected int i; //la position en i de notre bouton
  protected int j; //la postion en j de notr bouton
  protected HashMap<Integer, int[]> mapCoord; //une map qui contient les coordonnées de chaque cases

    /**
     * Initialise une vue sur le modèle
     * @param modeleActuel est le plateu de jeu actuel
     * @param n est le nombre de lignes
     * @param m est le nombre de colonnes
     * @param frame est la JFrame affiché
     */
  public VueModel(ModeleTaquin modeleActuel, int n, int m, String image, JFrame frame){
    this.modeleActuel = modeleActuel;
    this.n = n;
    this.m = m;
    this.frame = frame;
    this.image = image;
    GridLayout grid = new GridLayout(n,m);
    this.setLayout(grid);
    affichageGrille(modeleActuel,n,m);
  }

  /**
   * Méthode qui permet d'afficher la grille est d'intérargir avec
   * @param modeleActuel le modele avec lequel on interagit
   * @param n le nombre de lignes du modele
   * @param m le nombre de colonnes du modele
   */
  public void affichageGrille(ModeleTaquin modeleActuel, int n, int m){
    mapCoord = new HashMap<>();
    tabBoutons = new JButton[n][m];
    //On parcourt la grille du modele actuel
    for(i = 0; i < modeleActuel.getGrille().length; i++){
      for(j = 0; j < modeleActuel.getGrille()[i].length; j++){
        int[] coord = new int[2];
        coord[0] = i;
        coord[1] = j;
        //On remplit la map avec les coordonnées
        mapCoord.put(modeleActuel.getGrille()[i][j],coord);
        //On vérifie si nous sommes sur la case vide
        if(modeleActuel.getGrille()[i][j] == 0){
          tabBoutons[i][j] = new JButton();
          tabBoutons[i][j].setName(Integer.toString(modeleActuel.getGrille()[i][j]));
          //On ajoute l'événement pour pouvoir déplacer la case vide à l'aide des flèches haut, bas, gauche, droite
          tabBoutons[i][j].addKeyListener(new KeyAdapter(){
      			public void keyPressed(KeyEvent e) {
              int keyCode = e.getKeyCode();
              //Suivant la flèche on ajoute ou enlève 1 aux coordonnées de la case vide puis on appel swap
              if(keyCode == KeyEvent.VK_UP){
                modeleActuel.swap(i_zero-1,j_zero);
              }
              if(keyCode == KeyEvent.VK_DOWN){
                modeleActuel.swap(i_zero+1,j_zero);
              }
              if(keyCode == KeyEvent.VK_LEFT){
                modeleActuel.swap(i_zero,j_zero-1);
              }
              if(keyCode == KeyEvent.VK_RIGHT){
                modeleActuel.swap(i_zero,j_zero+1);
              }
             }
      		});
          //On ajoute le bouton au JPanel
          add(tabBoutons[i][j]);
        }else{
          tabBoutons[i][j] = new JButton(new ImageIcon("image/"+image+Integer.toString(modeleActuel.getGrille()[i][j])+".jpeg"));
          tabBoutons[i][j].setName(Integer.toString(modeleActuel.getGrille()[i][j]));
          //On ajoute l'événement pour pouvoir échanger la case avec la case vide à l'aide des flèches haut, bas, gauche, droite
          tabBoutons[i][j].addMouseListener(new MouseAdapter(){
      			public void mouseClicked(MouseEvent e) {
              //On répcupère les coordonnées de notre case
              int pos_i = mapCoord.get(Integer.valueOf(((JButton) e.getSource()).getName()))[0];
              int pos_j = mapCoord.get(Integer.valueOf(((JButton) e.getSource()).getName()))[1];
              //On vérifie si la case cliqué est adjacente à la case vide
              if((((pos_i == i_zero-1)||(pos_i == i_zero + 1)) && (pos_j == j_zero))||(((pos_j == j_zero-1)||(pos_j == j_zero + 1)) && (pos_i == i_zero))){
                  modeleActuel.swap(pos_i,pos_j);
              }
      			}
      		});
          add(tabBoutons[i][j]);
        }
      }
    }
    //On récupère les coordonnées de la case vide
    i_zero = mapCoord.get(0)[0];
    j_zero = mapCoord.get(0)[1];
  }

  /**
   * Méthode qui permet d'actualiser la vue
   * @param c l'écouteur
   */
  public void modeleMisAJour(Object c){
    //On supprime tout les boutons de l'affichage
    removeAll();
    //On rappele affichageGrille pour créer les boutons avec notre grille actualisée
    affichageGrille(this.modeleActuel, this.n, this.m);
    //On redessine le tableau de bouton
    revalidate();
    //Si la partie est finie on affiche un message et ferme l'application
    if(modeleActuel.isOver()){
      JOptionPane.showMessageDialog(frame,"Vous avez gagné");
      deleteImg();
      System.exit(1);
    }
  }

  /**
   * Méthode qui permet de supprimer les sous-images
   */
  public void deleteImg(){
    for(int i = 1; i<=9; i++){
      File file = new File("image/"+this.image+Integer.valueOf(i).toString()+".jpeg");
      file.delete();
    }
  }

}
