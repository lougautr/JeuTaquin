package vue;

import modele.*;
import javax.swing.*;
import java.awt.*;

public class ModeleGUI extends JFrame{
  protected ModeleTaquin puzzle;

  /**
   * Initialise une interface graphique du puzzle Taquin
   * @param puzzle le puzzle à afficher
   */
  public ModeleGUI(ModeleTaquin puzzle){
    this.setLayout(new BorderLayout());
    //On crée une fenêtre pop-up pour saisir le nom de l'image à utiliser
    String srcImg = JOptionPane.showInputDialog(this, "Saisissez le nom de votre image jpeg sans mettre l'extension");
    //On crée une instance de la classe Image pour redimensionner et découper l'image choisie
    Image image = new Image("image/"+srcImg+".jpeg", 500, 500);
    //On crée une instance de la classe VueModel pour afficher le jeu
    VueModel vuePuzzle = new VueModel(puzzle, puzzle.getNbLigne(), puzzle.getNbColonne(), srcImg, this);
    this.add(vuePuzzle, BorderLayout.CENTER);
    //On ajoute un écouteur au ModeleTaquin
    puzzle.ajoutEcouteur(vuePuzzle);
    this.setSize(500,500);
    this.setVisible(true);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }
}
